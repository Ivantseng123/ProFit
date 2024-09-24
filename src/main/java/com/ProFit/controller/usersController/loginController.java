package com.ProFit.controller.usersController;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProFit.service.userService.IUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class loginController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("loginpage")
	public String LoginPage() {
		return "usersVIEW/Login";
	}

	@PostMapping("/login")
	public String Login(@RequestParam("user_email") String user_email,
			@RequestParam("user_password") String user_password, HttpSession session) throws NoSuchAlgorithmException {

		String user_pictureURL = userService.getUserPictureByEmail(user_email);
		String user_passwordHash = userService.toHexString(userService.getSHA(user_password));
		if (userService.validate(user_email, user_passwordHash)) {

			System.out.println("登入成功");
			session.setAttribute("user_email", user_email);

			session.setAttribute("user_pictureURL", user_pictureURL);

			return "redirect:/alluser";

		} else {
			System.out.println("登入失敗");
			return "redirect:/loginpage";
		}

	}
	
	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		
		if(session.getAttribute("user_email") != null ) {
			session.invalidate();
		}
		return "redirect:/loginpage";

	}

}
