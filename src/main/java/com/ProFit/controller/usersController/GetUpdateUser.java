package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.UserDao;


@WebServlet("/GetUpdateUser")
public class GetUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetUpdateUser() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer user_id = Integer.valueOf(request.getParameter("user_id")) ;
		
		//Users user = new Users();
		System.out.println(user_id);
		
		UserDao userDao = new UserDao();
		Users getuser = userDao.getUserInfoByID(user_id);
		
		
		System.out.println(getuser.toString());
		
		request.setAttribute("user", getuser);
		request.getRequestDispatcher("/usersVIEW/UpdateUser.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
