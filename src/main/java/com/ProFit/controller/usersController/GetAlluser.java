package com.ProFit.controller.usersController;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.dao.usersDao.HUserDao;
import com.ProFit.hibernateutil.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/GetAlluser")
public class GetAlluser extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public GetAlluser() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HUserDao userDao = new HUserDao(session);

		//System.out.println(userDao.getAllUserInfo().toString());
		request.setAttribute("users", userDao.getAllUserInfo());
		
		request.getRequestDispatcher("/usersVIEW/Allusers.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
