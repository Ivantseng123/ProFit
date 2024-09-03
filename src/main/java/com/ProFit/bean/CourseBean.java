package com.ProFit.bean;

import java.util.Date;

public class CourseBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String courseId;
	private String courseName;
	private String courseCreateUserId;
	private String createUserName; //table中沒有，為了前端呈現而設定
	private String courseCategory;
	private String courseInformation;
	private String courseDescription;
	private String courseEnrollmentDate;
	private String courseStartDate;
	private String courseEndDate;
	private String coursePrice;
	private String courseStatus;
	
	public CourseBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CourseBean(String courseId, String courseName, String courseCreateUserId, String courseCategory,
			String courseInformation, String courseDescription, String courseEnrollmentDate, String courseStartDate,
			String courseEndDate, String coursePrice, String courseStatus) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCreateUserId = courseCreateUserId;
		this.courseCategory = courseCategory;
		this.courseInformation = courseInformation;
		this.courseDescription = courseDescription;
		this.courseEnrollmentDate = courseEnrollmentDate;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.coursePrice = coursePrice;
		this.courseStatus = courseStatus;
	}


	// for create course
	public CourseBean(String courseName, String courseCreateUserId, String courseCategory, String courseInformation,
			String courseDescription, String courseEnrollmentDate, String courseStartDate, String courseEndDate,
			String coursePrice, String courseStatus) {
		super();
		this.courseName = courseName;
		this.courseCreateUserId = courseCreateUserId;
		this.courseCategory = courseCategory;
		this.courseInformation = courseInformation;
		this.courseDescription = courseDescription;
		this.courseEnrollmentDate = courseEnrollmentDate;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.coursePrice = coursePrice;
		this.courseStatus = courseStatus;
	}


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
	public String getCourseCreateUserId() {
		return courseCreateUserId;
	}
	public void setCourseCreateUserId(String courseCreateUserId) {
		this.courseCreateUserId = courseCreateUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
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
	
	@Override
	public String toString() {
		return "CourseBean [courseId=" + courseId + ", courseName=" + courseName + ", courseCreateUserId="
				+ courseCreateUserId + ", courseCategory=" + courseCategory + ", courseInformation=" + courseInformation
				+ ", courseDescription=" + courseDescription + ", courseEnrollmentDate=" + courseEnrollmentDate
				+ ", courseStartDate=" + courseStartDate + ", courseEndDate=" + courseEndDate + ", coursePrice="
				+ coursePrice + ", courseStatus=" + courseStatus + "]";
	}
	
}