package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.UserDao;
import com.google.gson.Gson;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteUser() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String user_id = request.getParameter("user_id");
		
		  BufferedReader reader = request.getReader();
	        Gson gson = new Gson();
	        HashMap<String, String> json = gson.fromJson(reader, HashMap.class);
	        
	        
	        System.out.println(json.get("user_id"));
	        int user_id = Integer.valueOf(json.get("user_id"));
		
		UserDao userDao = new UserDao();
		try {
			
			userDao.deleteUserInfo(user_id);
			
			//request.getRequestDispatcher("/GetAlluser").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
