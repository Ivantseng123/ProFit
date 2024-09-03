package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.ProFit.dao.usersDao.UserDao;



@WebServlet("/GetAlluser")
public class GetAlluser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetAlluser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao userDao = new UserDao();
		request.setAttribute("users", userDao.getAllUserInfo());
		request.getRequestDispatcher("/usersVIEW/Allusers.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
