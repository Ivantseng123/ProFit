package com.ProFit.controller.usersController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.hibernateutil.HibernateUtil;
import com.ProFit.dao.usersDao.HUserDao;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUser() {
		super();

	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String user_id = request.getParameter("user_id");

		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		HashMap<String, String> json = gson.fromJson(reader, HashMap.class);

		int user_id = Integer.valueOf(json.get("user_id"));
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		HUserDao userDao = new HUserDao(session);
		try {

			userDao.deleteUserInfo(user_id);

			// request.getRequestDispatcher("/GetAlluser").forward(request, response);
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
