package com.ProFit.controller.usersController;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProFit.bean.usersBean.Users;
import com.ProFit.service.userService.IUserService;

@Controller
public class usersController {

	@Autowired
	private IUserService userService;

	@Autowired
	private Users user;

	// 全部會員
	@GetMapping(path = "/alluser")
	public String GetAlluser(Model model) {

		model.addAttribute("users", userService.getAllUserInfo());

		return "usersVIEW/Allusers";
	}

	// 新增會員
	@PostMapping(path = "/insertuser")
	public String InsertUser(@RequestParam("user_name") String user_name, @RequestParam("user_email") String user_email,
			@RequestParam("user_password") String user_password,
			@RequestParam("user_phonenumber") String user_phonenumber, @RequestParam("user_city") String user_city,
			Model model) throws NoSuchAlgorithmException {

		String user_passwordHash = userService.toHexString(userService.getSHA(user_password));

		user.setUserName(user_name);
		user.setUserEmail(user_email);
		user.setUserPasswordHash(user_passwordHash);
		user.setUserPhoneNumber(user_phonenumber);
		user.setUserCity(user_city);
		user.setUserIdentity(1);
		user.setUserBalance(0);
		user.setFreelancerProfileStatus(0);

		Users existuser = userService.getUserByEmail(user_email);
		if (existuser == null) {

			userService.saveUserInfo(user);
		}

		return "redirect:/alluser";
	}

	// 刪除會員
	@PostMapping(path = "/deleteuser")
	public String DeleteUser(@RequestBody Map<String, String> user, Model model) {

		int user_id = Integer.parseInt(user.get("user_id"));

		userService.deleteUserInfo(user_id);

		return "redirect:/alluser";
	}

	// 查看單筆會員
	@GetMapping(path = "/getuser")
	public String GetUser(@RequestParam("user_id") String user_id, Model model) {

		Integer userId = Integer.valueOf(user_id);

		model.addAttribute("user", userService.getUserInfoByID(userId));

		return "usersVIEW/GetUser";
	}

	// 取得欲編輯會員
	@GetMapping(path = "/getupdateuser")
	public String GetUpdateUser(@RequestParam("user_id") String user_id, Model model) {

		Integer userId = Integer.valueOf(user_id);

		model.addAttribute("user", userService.getUserInfoByID(userId));

		return "usersVIEW/UpdateUser";
	}

}
