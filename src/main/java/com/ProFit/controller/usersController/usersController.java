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
		Users user = new Users();
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
	public String GetUser(@RequestParam("user_id") String user_id, @RequestParam("action") String action, Model model) {

		Integer userId = Integer.valueOf(user_id);

		model.addAttribute("user", userService.getUserInfoByID(userId));

		if (action.equals("edit")) {
			return "usersVIEW/UpdateUser";
		} else {
			return "usersVIEW/GetUser";
		}

	}


	// 編輯會員
	@PostMapping(path = "/updateuser")
	public String UpdateUser(@RequestParam("user_id") String user_id,
			@RequestParam("user_pictureURL") String user_pictureURL, @RequestParam("user_name") String user_name,
			@RequestParam("user_email") String user_email, @RequestParam("user_passwordHash") String user_passwordHash,
			@RequestParam("user_phoneNumber") String user_phoneNumber, @RequestParam("user_city") String user_city,
			@RequestParam("user_identity") String user_identity, @RequestParam("user_balance") String user_balance,
			@RequestParam("freelancer_location_prefer") String freelancer_location_prefer,
			@RequestParam("freelancer_exprience") String freelancer_exprience,
			@RequestParam(value = "freelancer_identity", required = false) String freelancer_identity,
			@RequestParam("freelancer_profile_status") String freelancer_profile_status,
			@RequestParam("freelancer_disc") String freelancer_disc,
			@RequestParam("user_register_time") String user_register_time, Model model)
			throws NoSuchAlgorithmException {

		Integer userId = Integer.valueOf(user_id);
		Integer userIdentity = Integer.valueOf(user_identity);
		Integer userBalance = Integer.valueOf(user_balance);
		Integer freelancerProfileStatus = Integer.valueOf(freelancer_profile_status);
		
		Users user = new Users();
		user.setUserId(userId);
		user.setUserName(user_name);
		user.setUserEmail(user_email);
		user.setUserPasswordHash(user_passwordHash);
		user.setUserPhoneNumber(user_phoneNumber);
		user.setUserCity(user_city);
		user.setUserPictureURL(user_pictureURL);
		user.setFreelancerLocationPrefer(freelancer_location_prefer);
		user.setFreelancerExprience(freelancer_exprience);
		user.setFreelancerDisc(freelancer_disc);
		user.setFreelancerIdentity(freelancer_identity);
		user.setUserIdentity(userIdentity);
		user.setUserBalance(userBalance);
		user.setFreelancerProfileStatus(freelancerProfileStatus);

		userService.updateUserInfo(user);

		return "redirect:/alluser";
	}

	// 編輯會員
	@PostMapping(path = "/updateuserpwd")
	public String UpdateUserPwd(@RequestParam("user_id") String user_id,
			@RequestParam("user_password") String user_password, Model model) throws NoSuchAlgorithmException {

		Integer userId = Integer.valueOf(user_id);
		String user_passwordHash = userService.toHexString(userService.getSHA(user_password));

		Users existuser = userService.getUserInfoByID(userId);
		if (existuser != null) {

			userService.updateUserPwd(user_passwordHash, userId);
		}

		return "redirect:/getupdateuser?user_id=" + userId;
	}

}
