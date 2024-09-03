package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ProFit.dao.usersDao.empApplDao;
import com.ProFit.dao.usersDao.empProfileDao;

@WebServlet("/GetAllEmpPf")
public class GetAllEmpPf extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    public GetAllEmpPf() {
        super();    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		empProfileDao empDao = new empProfileDao();
		request.setAttribute("emps", empDao.getAllEmpInfo());
		request.getRequestDispatcher("/usersVIEW/AllemployerProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
