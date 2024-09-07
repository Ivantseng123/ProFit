package com.ProFit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.catalina.core.FrameworkListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.bean.coursesBean.CourseBean;
import com.ProFit.bean.coursesBean.CourseGradeContentBean;
import com.ProFit.bean.coursesBean.CourseLessonBean;
import com.ProFit.bean.coursesBean.CourseModuleBean;
import com.ProFit.bean.coursesBean.CourseOrderBean;
import com.ProFit.dao.coursesCRUD.HcourseDao;
import com.ProFit.dao.coursesCRUD.HcourseGradeContentDao;
import com.ProFit.dao.coursesCRUD.HcourseLessonDao;
import com.ProFit.dao.coursesCRUD.HcourseModuleDao;
import com.ProFit.dao.coursesCRUD.HcourseOrderDao;
import com.ProFit.hibernateutil.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/testCourse")
public class TestCoursesHibernate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processAction(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processAction(request, response);
	}

	public void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		CourseBean courseBean = session.get(CourseBean.class, "C0100");
		
		HcourseDao hcourseDao = new HcourseDao(session);
		CourseBean courseBean1 = hcourseDao.searchOneCourseById("C0100");
		List<CourseModuleBean> courseModules = courseBean1.getCourseModules();
		System.out.println(courseModules.get(0).getCourseModuleName());
		
		HcourseGradeContentDao courseGradeDAO = new HcourseGradeContentDao(session);
		
		CourseGradeContentBean courseGrade = courseGradeDAO.searchOneCourseGradeContentById(100);
		CourseBean gradeCourse = courseGrade.getCourse();
		
		HcourseModuleDao hcourseModuleDao = new HcourseModuleDao(session);
		
		CourseModuleBean courseModule = hcourseModuleDao.searchOneCourseModuleById(1);
		CourseBean Modulecourse = courseModule.getCourse();
		List<CourseLessonBean> moduleLessons = courseModule.getModuleLessons();
		System.out.println(moduleLessons.get(0).getCourseLessonName());
		
		HcourseLessonDao hcourseLessonDao = new HcourseLessonDao(session);
		
		CourseLessonBean courseLesson = hcourseLessonDao.searchOneCourseLessonById(1);
		CourseBean lessonModuleCourse = courseLesson.getCourseModule().getCourse();
		CourseModuleBean moduleLesson = courseLesson.getCourseModule();
		
		HcourseOrderDao hcourseOrderDao = new HcourseOrderDao(session);
		
		CourseOrderBean courseOrder = hcourseOrderDao.searchOneCourseOrderById("CR100");

		if(courseBean!=null) {
			out.write(courseBean.getCourseName() + " " + courseBean.getCoursePrice() + "<br/>");
			out.write(courseGrade.getStudentId()+" "+courseGrade.getCourseId()+" "+gradeCourse.getCourseName()+""+courseGrade.getCourseGradeScore()+"<br/>");
			out.write(Modulecourse.getCourseName()+" "+courseModule.getCourseModuleName()+"<br/>");
			out.write(lessonModuleCourse.getCourseId()+" "+lessonModuleCourse.getCourseName()+" "+moduleLesson.getCourseModuleName()+" "+courseLesson.getCourseLessonName()+"<br/>");
			out.write(courseOrder.getCourseOrderId()+" "+courseOrder.getStudentId()+" "+courseOrder.getCourse().getCourseName()+" "+courseOrder.getCourseOrderPrice());
		}else {
			out.write("no result");
		}

		out.close();
	}
}
