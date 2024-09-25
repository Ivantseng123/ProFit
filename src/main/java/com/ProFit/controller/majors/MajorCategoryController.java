package com.ProFit.controller.majors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.bean.majorsBean.MajorCategoryBean;
import com.ProFit.service.majorService.IMajorCategoryService;
import com.ProFit.service.majorService.IMajorService;


@Controller
@RequestMapping("/majorCategory")
public class MajorCategoryController {

	@Autowired
	private IMajorCategoryService majorCategoryService;

	@Autowired
	private IMajorService majorService;

	// 顯示所有類別
	@GetMapping("/list")
	public String listMajorCategories(Model model) {
		List<MajorCategoryBean> listMajorCategory = majorCategoryService.findAllMajorCategories();
		model.addAttribute("listMajorCategory", listMajorCategory);
		return "majorsVIEW/MajorCategoryList";
	}

	// 根據選擇的類別顯示專業
	@GetMapping("/majors")
	public String listMajorsByCategory(@RequestParam("categoryId") int categoryId, Model model) {
		List<MajorBean> listMajor = majorService.findMajorsByCategoryId(categoryId);
		MajorCategoryBean category = majorCategoryService.findMajorCategoryById(categoryId);
		model.addAttribute("listMajor", listMajor);
		model.addAttribute("category", category);
		return "majorsVIEW/MajorListByCategory";
	}

	// 顯示新增專業類別表單
	@GetMapping("/new")
	public String showNewForm() {
		return "majorsVIEW/MajorCategoryForm";
	}

	// 新增類別
	@PostMapping("/insert")
	public String insertMajorCategory(@ModelAttribute MajorCategoryBean majorCategory, Model model) {
		try {	
			//System.out.println(majorCategory);			
			majorCategoryService.insertMajorCategory(majorCategory);
			return "redirect:/majorCategory/list";
		} catch (Exception e) {
			model.addAttribute("error", "新增類別失敗，可能是 ID 已存在。");
			return "majorsVIEW/MajorCategoryForm";
		}
	}

	// 刪除類別
	@GetMapping("/delete")
	public String deleteMajorCategory(@RequestParam("id") int id) {
		majorCategoryService.deleteMajorCategory(id);
		return "redirect:/majorCategory/list";
	}

	// 顯示類別更新表單
	@GetMapping("/edit")
	public String showEditForm(@RequestParam("id") int id, Model model) {
	    MajorCategoryBean majorCategory = majorCategoryService.findMajorCategoryById(id);
	    if (majorCategory != null) {
	        model.addAttribute("category", majorCategory);
	        return "majorsVIEW/MajorCategoryForm";
	    } else {
	        model.addAttribute("error", "找不到指定的類別");
	        return "redirect:/majorCategory/list";
	    }
	}
	
	// 更新專業類別
	@PostMapping("/update")
	public String updateMajorCategory(@ModelAttribute("category") MajorCategoryBean majorCategory, 
	                                  BindingResult result, 
	                                  Model model) {
	    if (majorCategory.getMajorCategoryId() == null || majorCategory.getMajorCategoryId() == 0) {
	        model.addAttribute("error", "更新失敗：無效的類別 ID。");
	        return "majorsVIEW/MajorCategoryForm";
	    }
	    
	    if (result.hasErrors()) {
	        return "majorsVIEW/MajorCategoryForm";
	    }
	    
	    if (majorCategoryService.updateMajorCategory(majorCategory)) {
	        return "redirect:/majorCategory/list";
	    } else {
	        model.addAttribute("error", "更新失敗。");
	        return "majorsVIEW/MajorCategoryForm";
	    }
	}
	
	
}