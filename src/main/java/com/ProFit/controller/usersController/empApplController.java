package com.ProFit.controller.usersController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.service.userService.IEmpApplService;
import com.ProFit.service.userService.IEmpPfService;

@Controller
public class empApplController {

	@Autowired
	private IEmpApplService empApplService;

	@Autowired
	private IEmpPfService empPfService;

	@GetMapping("/allempappl")
	public String GetAllEmpAppl(Model model) {

		model.addAttribute("emps", empApplService.getAllEmpAppl());

		return "usersVIEW/AllemployerAppl";
	}

	@PostMapping("/insertempappl")
	public String InsertAllEmpAppl(@RequestParam("user_id") String user_id,
			@RequestParam("company_name") String company_name,
			@RequestParam("company_phoneNumber") String company_phoneNumber,
			@RequestParam("company_taxID") String company_taxID,
			@RequestParam("company_address") String company_address,
			@RequestParam("company_address1") String company_address1,
			@RequestParam("company_category") String company_category,
			@RequestParam("user_national_id") String user_national_id,
			@RequestParam("company_taxID_docURL") String company_taxID_docURL,
			@RequestParam("idCard_pictureURL_1") String idCard_pictureURL_1,
			@RequestParam("idCard_pictureURL_2") String idCard_pictureURL_2) {

		Integer userId = Integer.valueOf(user_id);

		String address = company_address + company_address1;
		
		Employer_application emp = new Employer_application();
		emp.setUserId(userId);
		emp.setCompanyName(company_name);
		emp.setCompanyPhoneNumber(company_phoneNumber);
		emp.setCompanyAddress(address);
		emp.setCompanyTaxID(company_taxID);
		emp.setCompanyCategory(company_category);
		emp.setUserNationalId(user_national_id);
		emp.setCompanyTaxIdDocURL(company_taxID_docURL);
		emp.setIdCardPictureURL1(idCard_pictureURL_1);
		emp.setIdCardPictureURL2(idCard_pictureURL_2);
		emp.setApplicationCheck(0);

		empApplService.saveEmpApplInfo(emp);

		return "redirect:/allempappl";
	}

	@PostMapping("/deleteempappl")
	public String DeleteEmpAppl(@RequestBody Map<String, String> emp) {

		int employer_application_id = Integer.parseInt(emp.get("employer_application_id"));

		empApplService.deleteEmpInfo(employer_application_id);

		return "redirect:/allempappl";
	}

	@PostMapping("/updateempappl")
	public String UpdateEmpAppl(@RequestParam("employer_application_id") String employer_application_id,
			@RequestParam("user_id") String user_id, @RequestParam("company_name") String company_name,
			@RequestParam("company_address") String company_address,
			@RequestParam("company_address1") String company_address1,
			@RequestParam("company_category") String company_category,
			@RequestParam("company_taxID") String company_taxID,
			@RequestParam("user_national_id") String user_national_id,
			@RequestParam("company_phoneNumber") String company_phoneNumber,
			@RequestParam("company_taxID_docURL") String company_taxID_docURL,
			@RequestParam("idCard_pictureURL_1") String idCard_pictureURL_1,
			@RequestParam("idCard_pictureURL_2") String idCard_pictureURL_2) {

		int employerApplicationId = Integer.valueOf(employer_application_id);
		int userId = Integer.valueOf(user_id);
		String address = company_address + company_address1;
		
		Employer_application emp = new Employer_application();
		emp.setEmployerApplicationId(employerApplicationId);
		emp.setUserId(userId);
		emp.setCompanyName(company_name);
		emp.setCompanyPhoneNumber(company_phoneNumber);
		emp.setCompanyAddress(address);
		emp.setCompanyTaxID(company_taxID);
		emp.setCompanyCategory(company_category);
		emp.setUserNationalId(user_national_id);
		emp.setCompanyTaxIdDocURL(company_taxID_docURL);
		emp.setIdCardPictureURL1(idCard_pictureURL_1);
		emp.setIdCardPictureURL2(idCard_pictureURL_2);

		empApplService.updateEmpApplInfo(emp);

		return "redirect:/allempappl";

	}

	@GetMapping("/getempappl")
	public String GetEmpAppl(@RequestParam("employer_application_id") String employer_application_id,
			@RequestParam("action") String action, Model model) {

		Integer employerApplicationId = Integer.valueOf(employer_application_id);

		model.addAttribute("emp", empApplService.getEmpApplInfoByID(employerApplicationId));

		if (action.equals("edit")) {
			return "usersVIEW/UpdateEmpAppl";
		} else {

			return "usersVIEW/GetEmpAppl";
		}

	}

	@PostMapping("/checkempappl")
	public String CheckEmpAppl(@RequestBody HashMap<String, String> emp) {

		int employer_application_id = Integer.parseInt(emp.get("employer_application_id"));
		int user_id = Integer.valueOf(emp.get("user_id"));
		int check = Integer.valueOf(emp.get("check"));

		Employer_application empappl = empApplService.getEmpApplInfoByID(employer_application_id);
		Employer_profile existingProfile = empPfService.getEmpPfInfoByUserId(user_id);

		String company_name = empappl.getCompanyName();

		String company_phoneNumber = empappl.getCompanyPhoneNumber();

		String taxID = empappl.getCompanyTaxID();

		String company_address = empappl.getCompanyAddress();

		String company_category = empappl.getCompanyCategory();
		
		Employer_profile employer_profile = new Employer_profile();
		employer_profile.setUserId(user_id);
		employer_profile.setCompanyName(company_name);
		employer_profile.setCompanyAddress(company_address);
		employer_profile.setCompanyCategory(company_category);
		employer_profile.setCompanyPhoneNumber(company_phoneNumber);
		employer_profile.setCompanyTaxID(taxID);
		
		if (check == 1) {

			empApplService.updateEmpApplcheck_pass(employer_application_id);

			if (existingProfile != null) {
				existingProfile.setCompanyName(company_name);
				existingProfile.setCompanyAddress(company_address);
				existingProfile.setCompanyCategory(company_category);
				existingProfile.setCompanyPhoneNumber(company_phoneNumber);
				existingProfile.setCompanyTaxID(taxID);
				
				empPfService.updateEmpInfo(existingProfile);
			}else {
				empPfService.saveEmployerInfo(employer_profile);
			}

		} else {

			empApplService.updateEmpApplcheck_reject(employer_application_id);
		}

		return "redirect:/allempappl";
	}

}
