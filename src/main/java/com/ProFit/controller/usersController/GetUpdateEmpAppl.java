package com.ProFit.controller.usersController;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.dao.usersDao.HempApplDao;
import com.ProFit.hibernateutil.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetUpdateEmpAppl")
public class GetUpdateEmpAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetUpdateEmpAppl() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer employer_application_id = Integer.valueOf(request.getParameter("employer_application_id")) ;

		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HempApplDao empapplDao = new HempApplDao(session);
		Employer_application emp = empapplDao.getEmpApplInfoByID(employer_application_id);

		request.setAttribute("emp",emp);
		request.getRequestDispatcher("/usersVIEW/UpdateEmpAppl.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
