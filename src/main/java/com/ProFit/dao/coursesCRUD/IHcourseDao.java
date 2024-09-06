package com.ProFit.dao.coursesCRUD;

import java.util.List;

import com.ProFit.bean.coursesBean.CourseBean;

public interface IHcourseDao {

	// 新增課程
	public CourseBean insertCourse(CourseBean course);

	// 刪除課程
	public boolean deleteCourseByID(String courseId);

	// 更新課程
	public boolean updateCourseById(CourseBean newCourse,CourseBean oldCourse);

	// 查詢單筆By couseId
	public CourseBean searchOneCourseById(String courseId);

	// 查詢全部(未測試)
	public List<CourseBean> searchCourses();

	// 查詢全部By 多條件查詢
	public List<CourseBean> searchCourses(String courseName, String userName, String status, String userId, String category);


}
