package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import com.ProFit.dao.usersDao.empApplDao;
import com.ProFit.dao.usersDao.empProfileDao;
import com.google.gson.Gson;

@WebServlet("/DeleteEmpPf")
public class DeleteEmpPf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteEmpPf() {
        super();     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
	     Gson gson = new Gson();
	     HashMap<String, String> json = gson.fromJson(reader, HashMap.class);
	     
	     
	     System.out.println(json.get("employer_profile_id"));
	     int employer_profile_id = Integer.valueOf(json.get("employer_profile_id"));
	     
	     empProfileDao empdao = new empProfileDao();
	     
		 try {
			 empdao.deleteEmpInfo(employer_profile_id);
		} catch (Exception e) {
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
