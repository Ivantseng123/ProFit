package com.ProFit.dao.coursesCRUD;

import java.util.List;

import org.hibernate.Session;

import com.ProFit.bean.coursesBean.CourseLessonBean;

public class HcourseLessonDao implements IHcourseLessonDao {
	
	private Session session;
	
	public HcourseLessonDao(Session session) {
		this.session = session;
	}
	
	@Override
	public CourseLessonBean insertCourseLesson(CourseLessonBean courseLesson) {
		return null;
	}

	@Override
	public boolean deleteCourseLessonByID(String courseLessonId) {
		return false;
	}

	@Override
	public boolean updateCourseLessonById(CourseLessonBean newCourseLesson) {
		return false;
	}

	@Override
	public CourseLessonBean searchOneCourseLessonById(int courseLessonId) {
		return session.get(CourseLessonBean.class, courseLessonId);
	}

	@Override
	public List<CourseLessonBean> searchCourseLessons() {
		return null;
	}

	@Override
	public List<CourseLessonBean> searchCourseLessonsByModuleId(int courseModuleid) {
		return null;
	}

}
