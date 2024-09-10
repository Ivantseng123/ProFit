package com.ProFit.controller.usersController;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.dao.usersDao.HPwdResetTokensDao;
import com.ProFit.hibernateutil.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAlltoken")
public class GetAlltoken extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetAlltoken() {
        super();

    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HPwdResetTokensDao pwdResetTokensDao = new HPwdResetTokensDao(session);
		request.setAttribute("tokens", pwdResetTokensDao.getAllTokensInfo());
		request.getRequestDispatcher("/usersVIEW/AllresetTokens.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
