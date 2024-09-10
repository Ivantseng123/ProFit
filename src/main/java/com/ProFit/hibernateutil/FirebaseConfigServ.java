package com.ProFit.hibernateutil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;

@WebServlet("/FirebaseConfigServ")
public class FirebaseConfigServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FirebaseConfigServ() {
        super();
       
    }

 // Firebase 配置信息類別
    class FirebaseConfig {
        final String apiKey = "AIzaSyAx5jaHY_1aLNuqhjEDQEFGNfe5CdIDtdI";
        final String authDomain = "profit-e686b.firebaseapp.com";
        final String projectId = "profit-e686b";
        final String storageBucket = "profit-e686b.appspot.com";
        final String messagingSenderId = "5209696765";
        final String appId = "1:5209696765:web:48fd43b6d8180e2d0c5194";
        final String measurementId = "G-BHSWBE52Y6";
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final FirebaseConfig firebaseConfig = new FirebaseConfig();
		
		// 使用 Gson 將對象轉換為 JSON
        Gson gson = new Gson();
        String json = gson.toJson(firebaseConfig);

        // 設置回應的內容類型為 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow all origins
        // 將 JSON 寫入回應
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}