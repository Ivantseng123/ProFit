package com.ProFit.controller.usersController;

import java.io.IOException;

import com.ProFit.dao.usersDao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/GetAlluser")
public class GetAlluser extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public GetAlluser() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDao userDao = new UserDao();
		request.setAttribute("users", userDao.getAllUserInfo());
		request.getRequestDispatcher("/usersVIEW/Allusers.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
