package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import com.ProFit.dao.usersDao.PwdResetTokensDao;
import com.ProFit.dao.usersDao.empApplDao;
import com.google.gson.Gson;

@WebServlet("/DeleteEmpAppl")
public class DeleteEmpAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DeleteEmpAppl() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader reader = request.getReader();
	     Gson gson = new Gson();
	     HashMap<String, String> json = gson.fromJson(reader, HashMap.class);
	       
	     int employer_application_id = Integer.valueOf(json.get("employer_application_id"));
	     
	     empApplDao empappldao = new empApplDao();
	     
		 try {
			 empappldao.deleteEmpInfo(employer_application_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
