package com.ProFit.dao.coursesCRUD;

import java.util.List;

import com.ProFit.bean.coursesBean.CourseModuleBean;


public interface IHcourseModuleDao {
	
	// 新增課程章節
	public CourseModuleBean insertCourse(CourseModuleBean courseModule);

	// 刪除課程章節 by id
	public boolean deleteCourseByID(String courseModuleId);

	// 更新課程章節 by id
	public boolean updateCourseById(CourseModuleBean newCourseModule);

	// 查詢單筆課程章節By courseModuleId
	public CourseModuleBean searchOneCourseGradeContentById(int courseModuleId);

	// 查詢全部 多形
	public List<CourseModuleBean> searchCourseGradeContents();

	// 查詢全部 By 課程id
	public List<CourseModuleBean> searchCourseGradeContents(String course_id);

}
