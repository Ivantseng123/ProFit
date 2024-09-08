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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

		String courseName = request.getParameter("courseName");
		String courseCreateUserName = request.getParameter("courseCreateUserName");
		String courseStatus = request.getParameter("courseStatus");
		String courseCreateUserId = request.getParameter("courseCreateUserId");
		String courseCategory = request.getParameter("courseMajor");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HcourseDao courseDao = new HcourseDao(session);
		List<CourseBean> searchCourses = courseDao.searchCourses(courseName, courseCreateUserName, courseStatus, courseCreateUserId, courseCategory);

		// 將 CourseBean 轉換為 CoursesDTO
		List<CoursesDTO> searchCoursesDTO = searchCourses.stream()
			.map(CoursesDTO::new) // 使用 DTO 的構造函數
			.collect(Collectors.toList());

		// 設置回應類型為 JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 使用 Jackson 轉換為 JSON 格式
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// 將結果轉換為 JSON 格式
		String searchCoursesJson = objectMapper.writeValueAsString(searchCoursesDTO);
		System.out.println("Serialized JSON: " + searchCoursesJson);

		// 回傳 JSON 給前端
		PrintWriter out = response.getWriter();
		out.print(searchCoursesJson);
		out.flush();
	}

	// 搜尋單筆的方法
	protected void doSearchOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String courseId = request.getParameter("courseId");

	    SessionFactory factory = HibernateUtil.getSessionFactory();
	    Session session = factory.getCurrentSession();
	    
	    HcourseDao HcourseDao = new HcourseDao(session);
	    CourseBean singleCourseById = HcourseDao.searchOneCourseById(courseId);

	    // 使用 DTO 包裝 CourseBean 的數據
	    CoursesDTO coursesDTO = new CoursesDTO(singleCourseById);

	    // 設置回應類型為 JSON
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    // 使用 Jackson 轉換為 JSON 格式
	    ObjectMapper objectMapper = new ObjectMapper();

	    // 註冊 Hibernate 模塊以處理延遲加載對象
	    objectMapper.registerModule(new Hibernate6Module());

	    // 註冊 JavaTimeModule 以處理 LocalDateTime 等 Java 8 時間類型
	    objectMapper.registerModule(new JavaTimeModule());

	    // 禁用寫日期為時間戳的功能，這樣日期會以 ISO 標準格式返回
	    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	    // 將 DTO 轉換為 JSON 格式並寫回響應中
	    String searchOneCoursesJson = objectMapper.writeValueAsString(coursesDTO);
	    System.out.println("Serialized JSON: " + searchOneCoursesJson);

	    // 回傳 JSON 給前端
	    PrintWriter out = response.getWriter();
	    out.print(searchOneCoursesJson);
	    out.flush();
	}
}
