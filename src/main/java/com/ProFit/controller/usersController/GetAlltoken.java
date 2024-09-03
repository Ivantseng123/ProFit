package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ProFit.dao.usersDao.PwdResetTokensDao;

@WebServlet("/GetAlltoken")
public class GetAlltoken extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetAlltoken() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PwdResetTokensDao pwdResetTokensDao = new PwdResetTokensDao();
		request.setAttribute("tokens", pwdResetTokensDao.getAllTokensInfo());
		request.getRequestDispatcher("/usersVIEW/AllresetTokens.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
