package com.ProFit.controller.courses;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.dao.coursesCRUD.CourseDao;
import com.ProFit.dao.coursesCRUD.HcourseDao;
import com.ProFit.util.hibernateutil.HibernateUtil;
import com.ProFit.util.hibernateutil.JsonUtil;
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
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
	    Session session = factory.getCurrentSession();
		
		String courseId = request.getParameter("courseId");

		HcourseDao hcourseDao = new HcourseDao(session);
		
		boolean isDeleteCourse = hcourseDao.deleteCourseById(courseId);

		String jsonData = JsonUtil.toJson(isDeleteCourse);

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
