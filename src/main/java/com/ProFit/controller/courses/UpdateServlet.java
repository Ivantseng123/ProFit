package com.ProFit.controller.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.bean.coursesBean.CourseBean;
import com.ProFit.dao.coursesCRUD.HcourseDao;
import com.ProFit.util.hibernateutil.HibernateUtil;
import com.ProFit.util.hibernateutil.JsonUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public UpdateServlet() {
		super();
	}

	//處理所有更新的邏輯
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    SessionFactory factory = HibernateUtil.getSessionFactory();
	    Session session = factory.getCurrentSession();
		
		String courseId = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String courseCategory = request.getParameter("courseCategory");
		String courseCreateUserId = request.getParameter("courseCreateUserId");
		String courseInformation =request.getParameter("courseInformation");
		String courseDescription = request.getParameter("courseDescription");
		// 獲取日期時間並轉換成 LocalDateTime
        String enrollmentDateString = request.getParameter("courseEnrollmentDate");
        LocalDate courseEnrollmentDate = enrollmentDateString != null ? LocalDate.parse(enrollmentDateString, dateFormatter) : null;

        String startDateString = request.getParameter("courseStartDate");
        LocalDateTime courseStartDate = startDateString != null ? LocalDateTime.parse(startDateString, dateTimeFormatter) : null;

        String endDateString = request.getParameter("courseEndDate");
        LocalDateTime courseEndDate = endDateString != null ? LocalDateTime.parse(endDateString, dateTimeFormatter) : null;
		String coursePrice = request.getParameter("coursePrice");
		String courseStatus = request.getParameter("courseStatus");


		CourseBean updateCourse = new CourseBean(courseId,courseName, courseCreateUserId, courseCategory, courseInformation, courseDescription, courseEnrollmentDate, courseStartDate, courseEndDate, coursePrice, courseStatus);

		HcourseDao hcourseDao = new HcourseDao(session);

		CourseBean oldCourse = hcourseDao.searchOneCourseById(courseId);

		boolean isUpdateCourse = hcourseDao.updateCourseById(updateCourse);
		
		System.out.println(isUpdateCourse);
		
        String jsonData = JsonUtil.toJson(isUpdateCourse);


        //response
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Serialized JSON: " + jsonData);
        out.print(jsonData);
        out.flush();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
