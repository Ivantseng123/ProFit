package com.ProFit.dao.coursesCRUD;

import java.util.List;

import org.hibernate.Session;

import com.ProFit.bean.coursesBean.CourseModuleBean;

public class HcourseModuleDao implements IHcourseModuleDao {
	
	private Session session;
	
	public HcourseModuleDao(Session session) {
		this.session = session;
	}
	
	@Override
	public CourseModuleBean insertCourseModule(CourseModuleBean courseModule) {

		return null;
	}

	@Override
	public boolean deleteCourseModuleByID(String courseModuleId) {

		return false;
	}

	@Override
	public boolean updateCourseModuleById(CourseModuleBean newCourseModule) {

		return false;
	}

	@Override
	public CourseModuleBean searchOneCourseGradeContentById(int courseModuleId) {
		return session.get(CourseModuleBean.class, courseModuleId);
	}

	@Override
	public List<CourseModuleBean> searchCourseGradeContents() {

		return null;
	}

	@Override
	public List<CourseModuleBean> searchCourseGradeContents(String course_id) {

		return null;
	}

}
