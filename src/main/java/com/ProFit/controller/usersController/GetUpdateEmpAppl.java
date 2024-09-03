package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.UserDao;
import com.ProFit.dao.usersDao.empApplDao;

@WebServlet("/GetUpdateEmpAppl")
public class GetUpdateEmpAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public GetUpdateEmpAppl() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer employer_application_id = Integer.valueOf(request.getParameter("employer_application_id")) ;
		

		empApplDao empapplDao = new empApplDao();
		Employer_application emp = empapplDao.getEmpApplInfoByID(employer_application_id);
			
		request.setAttribute("emp",emp);
		request.getRequestDispatcher("/usersVIEW/UpdateEmpAppl.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
