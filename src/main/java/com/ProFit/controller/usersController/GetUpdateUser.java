package com.ProFit.controller.usersController;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.IHUserDao;
import com.ProFit.hibernateutil.HibernateUtil;
import com.ProFit.dao.usersDao.HUserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/GetUpdateUser")
public class GetUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public GetUpdateUser() {
        super();

    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer user_id = Integer.valueOf(request.getParameter("user_id")) ;
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		IHUserDao userDao = new HUserDao(session);
		Users getuser = userDao.getUserInfoByID(user_id);


		System.out.println(getuser.toString());

		request.setAttribute("user", getuser);
		request.getRequestDispatcher("/usersVIEW/UpdateUser.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
