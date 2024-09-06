package com.ProFit.dao.coursesCRUD;

import java.util.List;

import org.hibernate.Session;

import com.ProFit.bean.coursesBean.CourseGradeContentBean;

public class HcourseGradeContentDao implements IHcourseGradeContentDao {

	private Session session;
	
	public HcourseGradeContentDao(Session session) {
		this.session = session;
	}
	
	@Override
	public CourseGradeContentBean insertCourse(CourseGradeContentBean courseGradeContent) {

		return null;
	}

	@Override
	public boolean deleteCourseByID(String courseGradeId) {

		return false;
	}

	@Override
	public boolean updateCourseById(CourseGradeContentBean newCourseGrade) {

		return false;
	}

	@Override
	public CourseGradeContentBean searchOneCourseGradeContentById(int courseGradeId) {
		return session.get(CourseGradeContentBean.class,courseGradeId);
	}

	@Override
	public List<CourseGradeContentBean> searchCourseGradeContents() {

		return null;
	}

	@Override
	public List<CourseGradeContentBean> searchCourseGradeContents(String scope, int courseGradeScore) {

		return null;
	}

}
