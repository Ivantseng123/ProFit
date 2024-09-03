package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.UserDao;
import com.ProFit.dao.usersDao.empProfileDao;

@WebServlet("/GetEmpPf")
public class GetEmpPf extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetEmpPf() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer employer_profile_id = Integer.valueOf(request.getParameter("employer_profile_id")) ;
		

		
		empProfileDao empDao = new empProfileDao();
		Employer_profile getemp = empDao.getEmpPfInfoByID(employer_profile_id);
//		System.out.println(getemp.getCompany_numberOfemployee());
//		System.out.println(getemp.getCompany_captital());
//		System.out.println(getemp.getCompany_description());
		
		request.setAttribute("emp", getemp);
		request.getRequestDispatcher("/usersVIEW/GetEmpPf.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
