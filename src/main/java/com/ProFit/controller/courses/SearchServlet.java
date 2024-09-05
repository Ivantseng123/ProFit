package com.ProFit.controller.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ProFit.bean.CourseBean;
import com.ProFit.dao.coursesCRUD.CourseDao;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
		super();
	}

	//處理所有查詢的邏輯
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String searchLogic = request.getParameter("searchLogic");

		if("searchAll".equals(searchLogic)) {
			doSearchAll(request, response);
		}else if("searchOne".equals(searchLogic)){
			doSearchOne(request, response);
		}else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "未知的查詢邏輯");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// 搜尋全部的方法
	protected void doSearchAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseName = request.getParameter("courseName");
		String courseCreateUserName = request.getParameter("courseCreateUserName");
		String courseStatus = request.getParameter("courseStatus");
		String courseCreateUserId = request.getParameter("courseCreateUserId");
		String courseCategory = request.getParameter("courseMajor");



		CourseDao courseDao = new CourseDao();
		List<CourseBean> searchCourses = courseDao.searchCourses(courseName, courseCreateUserName, courseStatus, courseCreateUserId, courseCategory);

		// 調試輸出
	    System.out.println("Returned courses: " + searchCourses.size());

		// 設置回應類型為 JSON
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    // 將結果轉換為 JSON 格式並寫回響應中
	    PrintWriter out = response.getWriter();
	    String searchCoursesJson = new Gson().toJson(searchCourses);
	    System.out.println("Serialized JSON: " + searchCoursesJson);
	    out.print(searchCoursesJson);
	    out.flush();
	}

	// 搜尋單筆的方法
	protected void doSearchOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseId = request.getParameter("courseId");

		CourseDao courseDao = new CourseDao();
		CourseBean singleCourseById = courseDao.findSingleCourseById(courseId);

		// 設置回應類型為 JSON
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    // 將結果轉換為 JSON 格式並寫回響應中
	    PrintWriter out = response.getWriter();
	    String searchOneCoursesJson = new Gson().toJson(singleCourseById);
	    System.out.println("Serialized JSON: " + searchOneCoursesJson);
	    out.print(searchOneCoursesJson);
	    out.flush();
	}
}
