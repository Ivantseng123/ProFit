package com.ProFit.controller.usersController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.dao.usersDao.HempApplDao;
import com.ProFit.util.hibernateutil.HibernateUtil;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmpAppl")
public class DeleteEmpAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteEmpAppl() {
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
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HempApplDao empappldao = new HempApplDao(session);

		try {
			empappldao.deleteEmpInfo(employer_application_id);
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
