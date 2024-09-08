package com.ProFit.dao.coursesCRUD;

import java.util.List;

import com.ProFit.bean.coursesBean.CourseGradeContentBean;

public interface IHcourseGradeContentDao {
	
	// 新增課程評價
	public CourseGradeContentBean insertCourse(CourseGradeContentBean courseGradeContent);

	// 刪除課程評價 by id
	public boolean deleteCourseByID(String courseGradeId);

	// 更新課程評價 by id
	public boolean updateCourseById(CourseGradeContentBean newCourseGrade);

	// 查詢單筆評價By courseGradeId
	public CourseGradeContentBean searchOneCourseGradeContentById(int courseGradeId);

	// 查詢全部 多形
	public List<CourseGradeContentBean> searchCourseGradeContents();

	// 查詢全部 By 課程評價分數
	public List<CourseGradeContentBean> searchCourseGradeContents(String scope,int courseGradeScore);

}