package com.ProFit.dao.coursesCRUD;

import java.util.List;

import com.ProFit.bean.coursesBean.CourseLessonBean;


public interface IHcourseLessonDao {
	
	// 新增課程單元
	public CourseLessonBean insertCourseLesson(CourseLessonBean courseLesson);

	// 刪除課程單元 by id
	public boolean deleteCourseLessonByID(String courseLessonId);

	// 更新課程單元 by id
	public boolean updateCourseLessonById(CourseLessonBean newCourseLesson);

	// 查詢單筆課程單元By courseLessonId
	public CourseLessonBean searchOneCourseLessonById(int courseLessonId);

	// 查詢全部 多形
	public List<CourseLessonBean> searchCourseLessons();

	// 查詢全部 By 章節id
	public List<CourseLessonBean> searchCourseLessonsByModuleId(int courseModuleid);
	
}
