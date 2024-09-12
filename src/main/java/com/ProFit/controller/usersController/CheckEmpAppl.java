package com.ProFit.controller.usersController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.dao.usersDao.HUserDao;
import com.ProFit.dao.usersDao.HempApplDao;
import com.ProFit.dao.usersDao.HempProfileDao;
import com.ProFit.util.hibernateutil.HibernateUtil;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CheckEmpAppl")
public class CheckEmpAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckEmpAppl() {
		super();

	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		HashMap<String, String> json = gson.fromJson(reader, HashMap.class);

		int employer_application_id = Integer.valueOf(json.get("employer_application_id"));
		int user_id = Integer.valueOf(json.get("user_id"));
		int check = Integer.valueOf(json.get("check"));

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		

		
		HempApplDao empappldao = new HempApplDao(session);
		HUserDao userDao = new HUserDao(session);
		HempProfileDao empProfileDao = new HempProfileDao(session);

		Employer_application empappl = empappldao.getEmpApplInfoByID(employer_application_id);
		
		String company_name = empappl.getCompanyName();
		
		String company_phoneNumber = empappl.getCompanyPhoneNumber();
		
		String taxID = empappl.getCompanyTaxID();
		
		String company_address = empappl.getCompanyAddress();
		
		String company_category = empappl.getCompanyCategory();
		try {

			Employer_profile emppf = new Employer_profile(user_id, company_name, company_address, company_category,
					company_phoneNumber, taxID);	
			
			Employer_profile existingProfile = empProfileDao.getEmpPfInfoByUserId(user_id);
			
			if (check == 1) {
				
				
				empappldao.updateEmpApplcheck_pass(employer_application_id);
				
				userDao.updateUserIdentity(user_id);
				
				
				System.out.println(existingProfile);
				System.out.println("------------------------------------------------------------------");
				
				if(existingProfile != null) {
					
					existingProfile.setCompanyName(company_name);
				    existingProfile.setCompanyAddress(company_address);
				    existingProfile.setCompanyCategory(company_category);
				    existingProfile.setCompanyPhoneNumber(company_phoneNumber);
				    existingProfile.setCompanyTaxID(taxID);
				    
				    empProfileDao.updateEmpInfo(existingProfile);
					
				}else{
					
					empProfileDao.saveEmployerInfo(emppf);
				}

			} else {
				empappldao.updateEmpApplcheck_reject(employer_application_id);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
