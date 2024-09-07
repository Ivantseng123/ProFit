package com.ProFit.dao.coursesCRUD;

import java.util.List;

import org.hibernate.Session;

import com.ProFit.bean.coursesBean.CourseOrderBean;

public class HcourseOrderDao implements IHcourseOrderDao {
	
	private Session session;
	
	public HcourseOrderDao(Session session) {
		this.session = session;
	}

	@Override
	public CourseOrderBean insertCourseOrder(CourseOrderBean course) {
		return null;
	}

	@Override
	public boolean deleteCourseOrderByID(String courseId) {
		return false;
	}

	@Override
	public boolean updateCourseOrderById(CourseOrderBean newCourseOrder) {
		return false;
	}

	@Override
	public CourseOrderBean searchOneCourseOrderById(String courseOrderId) {
		return session.get(CourseOrderBean.class, courseOrderId);
	}

	@Override
	public List<CourseOrderBean> searchCourseOrders() {
		return null;
	}

	@Override
	public List<CourseOrderBean> searchCourseOrders(String courseId) {
		return null;
	}

}
