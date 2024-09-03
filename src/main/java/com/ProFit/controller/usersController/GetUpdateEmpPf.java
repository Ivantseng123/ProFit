package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.dao.usersDao.empApplDao;
import com.ProFit.dao.usersDao.empProfileDao;

@WebServlet("/GetUpdateEmpPf")
public class GetUpdateEmpPf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetUpdateEmpPf() {
        super();
     
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer employer_profile_id = Integer.valueOf(request.getParameter("employer_profile_id"));
		System.out.println(request.getParameter("employer_profile_id"));
		
		empProfileDao empDao = new empProfileDao();
		Employer_profile emp = empDao.getEmpPfInfoByID(employer_profile_id);
			
		request.setAttribute("emp",emp);
		request.getRequestDispatcher("/usersVIEW/UpdateEmpPf.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
