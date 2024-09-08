package com.ProFit.controller.usersController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.dao.usersDao.IHUserDao;
import com.ProFit.dao.usersDao.HUserDao;
import com.ProFit.dao.usersDao.empApplDao;
import com.ProFit.dao.usersDao.empProfileDao;
import com.ProFit.hibernateutil.HibernateUtil;
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

		empApplDao empappldao = new empApplDao();
		HUserDao userDao = new HUserDao(session);
		empProfileDao empProfileDao = new empProfileDao();

		Employer_application empappl = empappldao.getEmpApplInfoByID(employer_application_id);
		String company_name = empappl.getCompany_name();
		String company_phoneNumber = empappl.getCompany_phoneNumber();
		String taxID = empappl.getCompany_taxID();
		String company_address = empappl.getCompany_address();
		String company_category = empappl.getCompany_category();
		try {

			if (check == 1) {

				Employer_profile emppf = new Employer_profile(user_id, company_name, company_address, company_category,
						company_phoneNumber, taxID);
				empappldao.updateEmpApplcheck_pass(employer_application_id);
				userDao.updateUserIdentity(user_id);
				empProfileDao.saveEmployerInfo(emppf);

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
