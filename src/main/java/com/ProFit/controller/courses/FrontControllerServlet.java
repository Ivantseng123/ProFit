package com.ProFit.controller.courses;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller/courses/*")
public class FrontControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 從請求中獲取操作的類型
        String action = request.getPathInfo();

        // 根據不同的操作類型，轉發請求到不同的 Servlet
        if ("/create".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CreateServlet");
            dispatcher.forward(request, response);
        } else if ("/search".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchServlet");
            dispatcher.forward(request, response);
        } else if ("/update".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateServlet");
            dispatcher.forward(request, response);
        } else if ("/delete".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/DeleteServlet");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}