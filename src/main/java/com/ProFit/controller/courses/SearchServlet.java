package com.ProFit.controller.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.bean.coursesBean.CourseBean;
import com.ProFit.dao.coursesCRUD.HcourseDao;
import com.ProFit.dto.courseDTO.CoursesDTO;
import com.ProFit.hibernateutil.HibernateUtil;
import com.ProFit.hibernateutil.JsonUtil;
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
		doGet(request, response);
	}

	// 搜尋全部的方法
	protected void doSearchAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    SessionFactory factory = HibernateUtil.getSessionFactory();
	    Session session = factory.getCurrentSession();
		
		String courseName = request.getParameter("courseName");
		String courseCreateUserName = request.getParameter("courseCreateUserName");
		String courseStatus = request.getParameter("courseStatus");
		String courseCreateUserId = request.getParameter("courseCreateUserId");
		String courseCategory = request.getParameter("courseMajor");
		
		HcourseDao hcourseDao = new HcourseDao(session);
		List<CourseBean> searchCourses = hcourseDao.searchCourses(courseName, courseCreateUserName, courseStatus, courseCreateUserId, courseCategory);

		// 將 CourseBean 轉換為 CoursesDTO
		List<CoursesDTO> searchCoursesDTO = searchCourses.stream()
			.map(CoursesDTO::new) // 使用 DTO 的構造函數
			.collect(Collectors.toList());

		// 設置回應類型為 JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String jsonDatas = JsonUtil.toJson(searchCoursesDTO);
		System.out.println("Serialized JSON: " + jsonDatas);

		// 回傳 JSON 給前端
		PrintWriter out = response.getWriter();
		out.print(jsonDatas);
		out.flush();
	}

	// 搜尋單筆的方法
	protected void doSearchOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    SessionFactory factory = HibernateUtil.getSessionFactory();
	    Session session = factory.getCurrentSession();
		
		String courseId = request.getParameter("courseId");
	    
	    HcourseDao HcourseDao = new HcourseDao(session);
	    CourseBean singleCourseById = HcourseDao.searchOneCourseById(courseId);

	    // 使用 DTO 包裝 CourseBean 的數據
	    CoursesDTO coursesDTO = new CoursesDTO(singleCourseById);

	    // 設置回應類型為 JSON
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    String jsonData = JsonUtil.toJson(coursesDTO);
	    System.out.println("Serialized JSON: " + jsonData);

	    // 回傳 JSON 給前端
	    PrintWriter out = response.getWriter();
	    out.print(jsonData);
	    out.flush();
	}
}
