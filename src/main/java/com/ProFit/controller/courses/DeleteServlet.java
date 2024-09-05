package com.ProFit.controller.courses;

import java.io.IOException;
import java.io.PrintWriter;

import com.ProFit.dao.coursesCRUD.CourseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteServlet() {
		super();
	}

	//處理所有查詢的邏輯
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseId = request.getParameter("courseId");

		CourseDao courseDao = new CourseDao();
		boolean isDeleteCourse = courseDao.deleteCourseByID(courseId);


        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", isDeleteCourse);

        //response
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Serialized JSON: " + jsonResponse);
        out.print(new Gson().toJson(jsonResponse));
        out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
