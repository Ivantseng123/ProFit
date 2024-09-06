package com.ProFit.dao.coursesCRUD;

import java.util.List;

import org.hibernate.Session;

import com.ProFit.bean.coursesBean.CourseBean;

public class HcourseDao implements IHcourseDao {

	private Session session;

	public HcourseDao(Session session) {
		this.session = session;
	}

	@Override
	public CourseBean insertCourse(CourseBean course) {


		return null;
	}

	@Override
	public boolean deleteCourseByID(String courseId) {


		return false;
	}

	@Override
	public boolean updateCourseById(CourseBean newCourse, CourseBean oldCourse) {


		return false;
	}

	@Override
	public CourseBean findSingleCourseById(String courseId) {
		return session.get(CourseBean.class, courseId);
	}

	@Override
	public List<CourseBean> searchCourses() {


		return null;
	}

	@Override
	public List<CourseBean> searchCourses(String courseName, String userName, String status, String userId,
			String category) {


		return null;
	}

}
