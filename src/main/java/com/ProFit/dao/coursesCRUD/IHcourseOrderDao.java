package com.ProFit.dao.coursesCRUD;

import java.util.List;

import com.ProFit.bean.coursesBean.CourseOrderBean;


public interface IHcourseOrderDao {

	// 新增課程訂單
	public CourseOrderBean insertCourseOrder(CourseOrderBean course);

	// 刪除課程訂單
	public boolean deleteCourseOrderByID(String courseId);

	// 更新課程訂單
	public boolean updateCourseOrderById(CourseOrderBean newCourseOrder);

	// 查詢單筆課程訂單 By courseOrderId
	public CourseOrderBean searchOneCourseOrderById(String courseOrderId);

	// 查詢全部
	public List<CourseOrderBean> searchCourseOrders();

	// 查詢全部By courseOrderId
	public List<CourseOrderBean> searchCourseOrders(String courseId);
}
