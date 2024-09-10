package com.ProFit.controller.usersController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.dao.usersDao.HPwdResetTokensDao;
import com.ProFit.hibernateutil.HibernateUtil;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteToken")
public class DeleteToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteToken() {
        super();

    }

	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 BufferedReader reader = request.getReader();
	     Gson gson = new Gson();
		 HashMap<String, String> json = gson.fromJson(reader, HashMap.class);

	     
	     SessionFactory factory = HibernateUtil.getSessionFactory();
		 Session session = factory.getCurrentSession();
	     
	     System.out.println(json.get("token_id"));
	     int token_id = Integer.valueOf(json.get("token_id"));

	     HPwdResetTokensDao pwdResetTokensDao = new HPwdResetTokensDao(session);

		 try {
			 pwdResetTokensDao.deleteTokensInfo(token_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
