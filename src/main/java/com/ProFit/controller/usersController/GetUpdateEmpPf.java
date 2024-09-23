package com.ProFit.controller.usersController;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.dao.usersDao.HempProfileDao;
import com.ProFit.dao.usersDao.IHempProfileDao;
import com.ProFit.util.hibernateutil.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetUpdateEmpPf")
public class GetUpdateEmpPf extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public GetUpdateEmpPf() {
        super();

    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer employer_profile_id = Integer.valueOf(request.getParameter("employer_profile_id"));
		System.out.println(request.getParameter("employer_profile_id"));

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		IHempProfileDao empDao = new HempProfileDao(session);
		Employer_profile emp = empDao.getEmpPfInfoByID(employer_profile_id);
		
		
		request.setAttribute("emp",emp);
		request.getRequestDispatcher("/usersVIEW/UpdateEmpPf.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}