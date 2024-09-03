package com.ProFit.controller.courses;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ProFit.bean.CourseBean;
import com.ProFit.dao.coursesCRUD.CourseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateServlet() {
		super();
	}
	
	//處理所有更新的邏輯
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String courseId = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String courseCategory = request.getParameter("courseCategory");
		String courseCreateUserId = request.getParameter("courseCreateUserId");
		String courseInformation =request.getParameter("courseInformation");
		String courseDescription = request.getParameter("courseDescription");
		String courseEnrollmentDate = request.getParameter("courseEnrollmentDate");
		String courseStartDate = request.getParameter("courseStartDate");
		String courseEndDate = request.getParameter("courseEndDate");
		String coursePrice = request.getParameter("coursePrice");
		String courseStatus = request.getParameter("courseStatus");
		
		
		CourseBean updateCourse = new CourseBean(courseName, courseCreateUserId, courseCategory, courseInformation, courseDescription, courseEnrollmentDate, courseStartDate, courseEndDate, coursePrice, courseStatus);
		
		CourseDao courseDao = new CourseDao();
		
		CourseBean oldCourse = courseDao.findSingleCourseById(courseId);
		
		boolean isUpdateCourse = courseDao.updateCourseById(updateCourse, oldCourse);
        JsonObject updateJsonResponse = new JsonObject();
        updateJsonResponse.addProperty("success", isUpdateCourse);
        
        if (isUpdateCourse) {
        	updateJsonResponse.add("updateCourse", new Gson().toJsonTree(updateCourse));
        	updateJsonResponse.add("oldCourse", new Gson().toJsonTree(oldCourse));
        }
        
        //response
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Serialized JSON: " + updateJsonResponse);
        out.print(new Gson().toJson(updateJsonResponse));
        out.flush();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
