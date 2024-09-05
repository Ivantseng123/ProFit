package com.ProFit.controller.usersController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import com.ProFit.dao.usersDao.PwdResetTokensDao;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteToken")
public class DeleteToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteToken() {
        super();

    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 BufferedReader reader = request.getReader();
	     Gson gson = new Gson();
	     HashMap<String, String> json = gson.fromJson(reader, HashMap.class);


	     System.out.println(json.get("token_id"));
	     int token_id = Integer.valueOf(json.get("token_id"));

	     PwdResetTokensDao pwdResetTokensDao = new PwdResetTokensDao();

		 try {
			 pwdResetTokensDao.deleteTokensInfo(token_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
