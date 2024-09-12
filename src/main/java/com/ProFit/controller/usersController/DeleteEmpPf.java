package com.ProFit.controller.usersController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.dao.usersDao.HempProfileDao;
import com.ProFit.dao.usersDao.IHempProfileDao;
import com.ProFit.util.hibernateutil.HibernateUtil;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmpPf")
public class DeleteEmpPf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteEmpPf() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		HashMap<String, String> json = gson.fromJson(reader, HashMap.class);

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		//System.out.println(json.get("employer_profile_id"));
		int employer_profile_id = Integer.valueOf(json.get("employer_profile_id"));

		IHempProfileDao empdao = new HempProfileDao(session);

		try {
			empdao.deleteEmpInfo(employer_profile_id);
		} catch (Exception e) {

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
