package com.ProFit.dto.courseDTO;

import com.ProFit.bean.coursesBean.CourseBean;

public class CoursesDTO {

	private String courseId;            // 添加 courseId 字段
	private String courseName;
	private String courseCreaterId;     // 修正拼寫錯誤: coruseCreaterId -> courseCreaterId
	private String courseCreaterName;   // 修正拼寫錯誤: courseCraeterName -> courseCreaterName
	private String courseCategoryId;
	private String courseCategoryName;
	private String courseInformation;
	private String courseDescription;
	private String courseEnrollmentDate;
	private String courseStartDate;
	private String courseEndDate;
	private String coursePrice;
	private String courseStatus;

	// 無參數構造函數
	public CoursesDTO() {
		super();
	}

	// 帶參數構造函數
	public CoursesDTO(CourseBean courseBean) {
		this.courseId = courseBean.getCourseId();    // 為前端表格中添加 ID
		this.courseName = courseBean.getCourseName();
		this.courseCreaterId = courseBean.getCourseCreater().getUser_id().toString();
		this.courseCreaterName = courseBean.getCourseCreater().getUser_name();
		this.courseCategoryId = courseBean.getMajorCategory().getMajorCategoryId().toString();
		this.courseCategoryName = courseBean.getMajorCategory().getCategoryName();
		this.courseInformation = courseBean.getCourseInformation();
		this.courseDescription = courseBean.getCourseDescription();
		this.courseEnrollmentDate = courseBean.getCourseEnrollmentDate().toString();
		this.courseStartDate = courseBean.getCourseStartDate().toString();
		this.courseEndDate = courseBean.getCourseEndDate().toString();
		this.coursePrice = courseBean.getCoursePrice();
		this.courseStatus = courseBean.getCourseStatus();
	}

	// getters 和 setters

	// 添加 get/setCourseId
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCreaterId() {
		return courseCreaterId;
	}

	public void setCourseCreaterId(String courseCreaterId) {
		this.courseCreaterId = courseCreaterId;
	}

	public String getCourseCreaterName() {
		return courseCreaterName;
	}

	public void setCourseCreaterName(String courseCreaterName) {
		this.courseCreaterName = courseCreaterName;
	}

	public String getCourseCategoryId() {
		return courseCategoryId;
	}

	public void setCourseCategoryId(String courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}

	public String getCourseCategoryName() {
		return courseCategoryName;
	}

	public void setCourseCategoryName(String courseCategoryName) {
		this.courseCategoryName = courseCategoryName;
	}

	public String getCourseInformation() {
		return courseInformation;
	}

	public void setCourseInformation(String courseInformation) {
		this.courseInformation = courseInformation;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseEnrollmentDate() {
		return courseEnrollmentDate;
	}

	public void setCourseEnrollmentDate(String courseEnrollmentDate) {
		this.courseEnrollmentDate = courseEnrollmentDate;
	}

	public String getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public String getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(String courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public String getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
}