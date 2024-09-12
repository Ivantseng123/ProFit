//package com.ProFit.controller.jobs;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import com.ProFit.bean.Jobs;
//import com.ProFit.dao.jobsCRUD.HJobsDAO;
//import com.ProFit.hibernateutil.HibernateUtil;
//import com.ProFit.jobService.JobsService;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/JobEdit")
//public class JobEdit extends HttpServlet {
//	private static final long serialVersionUID = 1L;
////	private JobsService jobsService;
////
////	public void init() {
////		jobsService = new JobsService();
////	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		Session session = factory.getCurrentSession();
//		
//		HJobsDAO hJobsDAO = new HJobsDAO(session);
//		List<Jobs> all = hJobsDAO.findAll();
//		
//		for(Jobs job:all) {
//			System.out.println(job);
//		}
//		
//		
//		
//		
////
////		if (request.getParameter("id") != null) {
////			int id = Integer.parseInt(request.getParameter("id"));
////			Jobs job = jobsService.findById(id);//jobsService的findById方法查找id的值，並存在 job 中。
////		if (job != null) {//如果job的值不為null，即透過ID找到了值（即 job 不为空）
////				request.setAttribute("job", job);//將job 的值保存到request中
////			}
////
////		}
////		//用getRequestDispatcher方法創建一個對象，對象會將request轉到jobsForm.jsp頁面，forward 方法會將request和response轉到.jsp 頁面。
////		request.getRequestDispatcher("/jobsVIEW/jobsForm.jsp").forward(request, response);
//
//	}
//}
