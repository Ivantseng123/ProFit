package com.ProFit.controller.majors;

import java.io.IOException;
import java.util.List;

import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.dao.majorsDao.HMajorDAO;
import com.ProFit.dao.majorsDao.IHMajorDAO;
import com.ProFit.service.majorService.IMajorCategoryService;
import com.ProFit.service.majorService.IMajorService;
import com.ProFit.util.hibernateutil.HibernateUtil;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/major")
public class MajorController {

	@Autowired
	private IMajorService majorService;

	@Autowired
	private IMajorCategoryService majorCategoryService;

	// 列出所有專業
	@GetMapping("/")
	public String listMajor(Model model) {
		List<MajorBean> listMajor = majorService.findAllMajors();
		// System.out.println(listMajor);
		model.addAttribute("listMajor", listMajor);
		return "majorsVIEW/MajorList";
	}

	// 顯示新增專業表單
	@GetMapping("/new")
	public String showNewForm() {
		return "majorsVIEW/MajorForm";
	}

	// 新增專業
	@PostMapping("/insert")
	public String insertMajor(@ModelAttribute MajorBean major, Model model, RedirectAttributes redirectAttributes) {

		if (majorService.findMajorById(major.getMajorId()) != null) {
			redirectAttributes.addFlashAttribute("error", "已存在此專業ID, 請輸入不重複有效數字");
			return "redirect:/major/new";
		}

		if (major.getMajorCategoryId() == null
				|| majorCategoryService.findMajorCategoryById(major.getMajorCategoryId()) == null) {
			// System.out.println("mcId出錯");
			redirectAttributes.addFlashAttribute("error", "無此專業類別ID, 請輸入有效數字");
			return "redirect:/major/new";
		}

		if (major.getMajorName() == null || major.getMajorName().trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "專業名稱不能為空");
			return "redirect:/major/new";
		}

		try {
			MajorBean insertedMajor = majorService.insertMajor(major);
			if (insertedMajor != null) {
				return "redirect:/major/";
			} else {
				redirectAttributes.addFlashAttribute("error", "新增專業失敗");
				return "redirect:/major/new";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "新增專業失敗，請確認欄位內容填寫正確");
			return "redirect:/major/new";
		}

	}

	// 顯示編輯專業表單
	@GetMapping("/edit")
	public String showEditForm(@RequestParam("id") Integer id, Model model) {
		MajorBean majorById = majorService.findMajorById(id);

		model.addAttribute("major", majorById);

		return "/majorsVIEW/MajorForm";
	}

	// 更新專業
	@PostMapping("/update")
	public String updateMajor(@ModelAttribute MajorBean major) {

		majorService.updateMajor(major);

		return "redirect:/major/";
	}

	// 刪除專業
	@GetMapping("/delete")
	public String deleteMajor(@RequestParam("id") Integer id) {
		majorService.deleteMajor(id);

		return "redirect:/major/";
	}

	// 查看專業詳情
	@GetMapping("/view")
	public String viewMajor(@RequestParam("id") Integer majorId, Model model) {
		MajorBean majorById = majorService.findMajorById(majorId);
		model.addAttribute("major", majorById);
		return "/majorsVIEW/MajorView";
	}

	// 列出特定類別下的所有專業
	@GetMapping("/category")
	public String listMajorsByCategory(@RequestParam("categoryId") Integer categoryId, Model model) {
		List<MajorBean> majors = majorService.findMajorsByCategoryId(categoryId);

		if (categoryId != null) {
			if (majorCategoryService.findMajorCategoryById(categoryId) == null) {
				model.addAttribute("error", "無此id之專業類別");
				return "/majorsVIEW/MajorList";
			}

			try {
				model.addAttribute("listMajor", majors);
				model.addAttribute("categoryId", categoryId);
				return "/majorsVIEW/MajorList";
			} catch (Exception e) {
				model.addAttribute("error", "請輸入有效數字");
				return "/majorsVIEW/MajorList";
			}
		} else {
			model.addAttribute("error", "必須輸入類別id");
			return "/majorsVIEW/MajorList";
		}
	}
}