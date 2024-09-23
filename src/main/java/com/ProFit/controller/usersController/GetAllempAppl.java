package com.ProFit.controller.usersController;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.dao.usersDao.HempApplDao;
import com.ProFit.util.hibernateutil.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllempAppl")
public class GetAllempAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public GetAllempAppl() {
        super();

    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		
		HempApplDao empApplDao = new HempApplDao(session);
		//System.out.println(empApplDao.getAllEmpApplInfo());
		request.setAttribute("emps", empApplDao.getAllEmpApplInfo());
		request.getRequestDispatcher("/usersVIEW/AllemployerAppl.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}