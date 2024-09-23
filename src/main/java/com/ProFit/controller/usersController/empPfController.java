package com.ProFit.controller.usersController;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.service.userService.IEmpPfService;
import com.ProFit.service.userService.IUserService;

@Controller
public class empPfController {

	@Autowired
	private IEmpPfService empPfService;

	@Autowired
	private IUserService userService;

	@GetMapping("allemppf")
	public String GetAllEmpPf(Model model) {

		model.addAttribute("emps", empPfService.getAllEmpInfo());

		return "usersVIEW/AllemployerProfile";
	}

	@PostMapping("insertemppf")
	public String InsertEmpPf(@RequestParam("user_id") String user_id,
			@RequestParam("company_name") String company_name, @RequestParam("company_address") String company_address,
			@RequestParam("company_address1") String company_address1,
			@RequestParam("company_category") String company_category,
			@RequestParam("company_phoneNumber") String company_phoneNumber,
			@RequestParam("company_taxID") String company_taxID) {

		Integer userId = Integer.valueOf(user_id);

		String companyAddress = company_address + company_address1;
		Employer_profile emp = new Employer_profile();
		Users user = new Users();
		emp.setUserId(userId);
		emp.setCompanyName(company_name);
		emp.setCompanyAddress(companyAddress);
		emp.setCompanyCategory(company_category);
		emp.setCompanyPhoneNumber(company_phoneNumber);
		emp.setCompanyTaxID(company_taxID);

		user = userService.getUserInfoByID(userId);

		Employer_profile existempPf = empPfService.getEmpPfInfoByUserId(userId);

		if (user != null && existempPf == null) {
			empPfService.saveEmployerInfo(emp);
		}

		return "redirect:/allemppf";
	}

	@PostMapping("deleteemppf")
	public String DeleteEmpPf(@RequestBody Map<String, String> emp) {

		int employer_profile_id = Integer.parseInt(emp.get("employer_profile_id"));

		empPfService.deleteEmpInfo(employer_profile_id);

		return "redirect/allemppf";
	}

	@PostMapping("updateemppf")
	public String UpdateEmpPf(@RequestParam("employer_profile_id") String employer_profile_id,
			@RequestParam("user_id") String user_id, @RequestParam("company_name") String company_name,
			@RequestParam("company_address") String company_address,
			@RequestParam("company_address1") String company_address1,
			@RequestParam("company_category") String company_category,
			@RequestParam("company_phoneNumber") String company_phoneNumber,
			@RequestParam("company_taxID") String company_taxID,
			@RequestParam("company_numberOfemployee") String company_numberOfemployee,
			@RequestParam("company_captital") String company_captital,
			@RequestParam("company_description") String company_description,
			@RequestParam("company_photoURL") String company_photoURL) {

		Integer employerProfileId = Integer.valueOf(employer_profile_id);
		Integer userId = Integer.valueOf(user_id);
		String address = company_address + company_address1;
		
		Employer_profile emp = new Employer_profile();

		emp.setEmployerProfileId(employerProfileId);
		emp.setUserId(userId);
		emp.setCompanyName(company_name);
		emp.setCompanyAddress(address);
		emp.setCompanyCategory(company_category);
		emp.setCompanyPhoneNumber(company_phoneNumber);
		emp.setCompanyTaxID(company_taxID);
		emp.setCompanyNumberOfemployee(company_numberOfemployee);
		emp.setCompanyCaptital(company_captital);
		emp.setCompanyDescription(company_description);
		emp.setCompanyPhotoURL(company_photoURL);

		empPfService.updateEmpInfo(emp);

		return "redirect:/allemppf";
	}

	@GetMapping("/getemppf")
	public String GetEmpPf(@RequestParam("employer_profile_id") String employer_profile_id,
			@RequestParam("action") String action, Model model) {

		Integer employerProfileId = Integer.valueOf(employer_profile_id);

		model.addAttribute("emp", empPfService.getEmpPfInfoByID(employerProfileId));

		if (action.equals("edit")) {
			return "usersVIEW/UpdateEmpPf";
		} else {

			return "usersVIEW/GetEmpPf";
		}

	}

}
