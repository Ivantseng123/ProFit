package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ProFit.dao.usersDao.empApplDao;

@WebServlet("/GetAllempAppl")
public class GetAllempAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetAllempAppl() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		empApplDao empApplDao = new empApplDao();
		System.out.println(empApplDao.getAllEmpApplInfo());
		request.setAttribute("emps", empApplDao.getAllEmpApplInfo());
		request.getRequestDispatcher("/usersVIEW/AllemployerAppl.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
