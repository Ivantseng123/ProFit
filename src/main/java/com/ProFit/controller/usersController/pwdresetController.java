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

import com.ProFit.bean.usersBean.Pwd_reset_tokens;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.service.userService.IPwdresetService;
import com.ProFit.service.userService.IUserService;


@Controller
public class pwdresetController {
	
	@Autowired
	private IPwdresetService pwdresetService;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/alltoken")
	public String getAlltoken(Model model) {
		
		
		model.addAttribute("tokens", pwdresetService.getAllTokensInfo());
		
		
		return "usersVIEW/AllresetTokens";
	}
	
	@PostMapping("/insertToken")
	public String insertToken(@RequestParam("user_id") String user_id) throws NoSuchAlgorithmException {
		
		Integer userId = Integer.valueOf(user_id);
		Pwd_reset_tokens tokens = new Pwd_reset_tokens();
		tokens.setUserId(userId);
		tokens.setUserTokenHash(pwdresetService.generateToken());
		
		Users existuser = userService.getUserInfoByID(userId);
		if (existuser != null) {
			
			pwdresetService.saveTokensInfo(tokens);
			
		}
				
		return "redirect:/alltoken";
	}
	
	
	@PostMapping("/deletetoken")
	public String deleteToken(@RequestBody Map<String, String> token) throws NoSuchAlgorithmException {
		
		Integer tokenId = Integer.valueOf(token.get("token_id"));
		
		pwdresetService.deleteTokensInfo(tokenId);
		
		return "redirect:/alltoken";
	}
	
	
	
}
