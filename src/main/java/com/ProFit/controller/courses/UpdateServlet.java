package com.ProFit.controller.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ProFit.bean.coursesBean.CourseBean;
import com.ProFit.dao.coursesCRUD.CourseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public UpdateServlet() {
		super();
	}

	//處理所有更新的邏輯
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseId = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String courseCategory = request.getParameter("courseCategory");
		String courseCreateUserId = request.getParameter("courseCreateUserId");
		String courseInformation =request.getParameter("courseInformation");
		String courseDescription = request.getParameter("courseDescription");
		// 獲取日期時間並轉換成 LocalDateTime
        String enrollmentDateString = request.getParameter("courseEnrollmentDate");
        LocalDateTime courseEnrollmentDate = enrollmentDateString != null ? LocalDateTime.parse(enrollmentDateString, formatter) : null;

        String startDateString = request.getParameter("courseStartDate");
        LocalDateTime courseStartDate = startDateString != null ? LocalDateTime.parse(startDateString, formatter) : null;

        String endDateString = request.getParameter("courseEndDate");
        LocalDateTime courseEndDate = endDateString != null ? LocalDateTime.parse(endDateString, formatter) : null;
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
