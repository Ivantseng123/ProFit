package com.ProFit.controller.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProFit.bean.servicesBean.ServiceBean;
import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.majorsBean.UserMajorPK;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.service.serviceService.IServiceService;
import com.ProFit.service.userService.IUserService;
import com.ProFit.service.majorService.IMajorService;
import com.ProFit.service.majorService.IUserMajorService;

@Controller
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private IServiceService serviceService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IMajorService majorService;

	@Autowired
	private IUserMajorService userMajorService;

	// 顯示所有服務
	@GetMapping("/")
	public String listServices(@RequestParam(name = "userId", required = false) Integer userId, Model model) {
		List<ServiceBean> listService = serviceService.findAllServices();
		List<Users> users = userService.getAllUserInfo();
		model.addAttribute("listService", listService);
		model.addAttribute("users", users);

		if (userId != null) {
			List<UserMajorBean> majors = userMajorService.findMajorsByUserId(userId);
			model.addAttribute("majors", majors);
			model.addAttribute("selectedUserId", userId);
		}

		return "servicesVIEW/ServiceList";
	}

	// 顯示新增服務表單
	@GetMapping("/new")
	public String showNewForm(Model model) {
		List<Users> users = userService.getAllUserInfo();
		List<MajorBean> majors = majorService.findAllMajors();
		model.addAttribute("users", users);
		model.addAttribute("majors", majors);
		return "servicesVIEW/ServiceForm";
	}

//	// 插入新服務
//	@PostMapping("/insert")
//	public String insertService(@ModelAttribute ServiceBean newService, @RequestParam int userId,
//			@RequestParam int majorId) {
//		Users user = userService.getUserInfoByID(userId);
//		MajorBean major = majorService.findMajorById(majorId);
//		UserMajorPK userMajorPK = new UserMajorPK(user, major);
//		UserMajorBean userMajor = userMajorService.getUserMajorByPK(userMajorPK);
//
//		newService.setUserMajor(userMajor);
//		newService.setServiceCreateDate(LocalDateTime.now());
//		newService.setServiceUpdateDate(LocalDateTime.now());
//
//		serviceService.insertService(newService);
//		return "redirect:/service/search";
//	}
//
//	 // 顯示編輯服務表單
//	@GetMapping("/edit")
//	public String showEditForm(@RequestParam int id, Model model) {
//		ServiceBean existingService = serviceService.findServiceById(id);
//		Map<Integer, String> users = userService.getAllUserInfo();
//		Map<Integer, String> majors = majorService.getAllMajors();
//
//		model.addAttribute("service", existingService);
//		model.addAttribute("users", users);
//		model.addAttribute("majors", majors);
//		return "servicesVIEW/ServiceEditForm";
//	}
//
//	// 更新服務
//	@PostMapping("/update")
//	public String updateService(@ModelAttribute ServiceBean service, RedirectAttributes redirectAttributes) {
//		service.setServiceUpdateDate(LocalDateTime.now());
//		serviceService.updateService(service);
//		redirectAttributes.addAttribute("id", service.getServiceId());
//		return "redirect:/service/view";
//	}
//
//	// 刪除服務
//	@GetMapping("/delete")
//	public String deleteService(@RequestParam int id) {
//		serviceService.deleteService(id);
//		return "redirect:/service/search";
//	}
//
//	// 搜尋服務
//	@GetMapping("/search")
//	public String searchServices(@RequestParam(required = false) String titleKeyword,
//			@RequestParam(required = false) String contentKeyword, @RequestParam(required = false) Integer userId,
//			@RequestParam(required = false) Integer majorId, Model model) {
//		List<ServiceBean> searchResult = serviceService.searchServices(titleKeyword, contentKeyword, majorId, userId);
//		Map<Integer, String> users = userService.getAllUserInfo();
//		model.addAttribute("listService", searchResult);
//		model.addAttribute("users", users);
//
//		if (userId != null) {
//			Map<Integer, String> majors = userMajorService.findMajorsByUserId(userId);
//			model.addAttribute("majors", majors);
//			model.addAttribute("selectedUserId", userId);
//		} else {
//			Map<Integer, String> majors = majorService.getAllMajors();
//			model.addAttribute("majors", majors);
//		}
//
//		return "servicesVIEW/ServiceList";
//	}
//
//	// 查看服務詳情
//	@GetMapping("/view")
//	public String viewService(@RequestParam int id, Model model) {
//		ServiceBean service = serviceService.findServiceById(id);
//
//		if (service != null) {
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
//			String formattedCreateDate = service.getServiceCreateDate().format(formatter);
//			String formattedUpdateDate = service.getServiceUpdateDate().format(formatter);
//			model.addAttribute("formattedCreateDate", formattedCreateDate);
//			model.addAttribute("formattedUpdateDate", formattedUpdateDate);
//			model.addAttribute("service", service);
//			return "servicesVIEW/ServiceView";
//		} else {
//			return "redirect:/service/";
//		}
//	}
//
	// 選擇用戶(新增服務前)
	@GetMapping("/selectUser")
	public String selectUser(@RequestParam(name = "userId", required = false) Integer userId, Model model) {
		if (userId != null) {
			List<UserMajorBean> majors = userMajorService.findMajorsByUserId(userId);
			List<Users> users = userService.getAllUserInfo();
			System.out.println(majors);
			model.addAttribute("selectedUserId", userId);
			model.addAttribute("majors", majors);
			model.addAttribute("users", users);

			return "servicesVIEW/ServiceForm";
		} else {
			return "redirect:/service/new";
		}
	}
}