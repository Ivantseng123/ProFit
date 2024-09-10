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

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public CreateServlet() {
		super();
	}

	//處理所有新增的邏輯
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		CourseBean courseBean = new CourseBean(courseName, courseCreateUserId, courseCategory, courseInformation, courseDescription, courseEnrollmentDate, courseStartDate, courseEndDate, coursePrice, courseStatus);

		CourseDao courseDao = new CourseDao();

		//新增課程後回傳是否成功
		boolean isNewCourse = courseDao.insertCourse(courseBean);

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", isNewCourse);

        if (isNewCourse) {
            jsonResponse.add("newCourse", new Gson().toJsonTree(courseBean));
        }

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
