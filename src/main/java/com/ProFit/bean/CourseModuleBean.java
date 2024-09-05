package com.ProFit.bean;

public class CourseModuleBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String courseModuleId;
	private String courseId;
	private String courseModuleName;

	public CourseModuleBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseModuleBean(String courseModuleId, String courseId, String courseModuleName) {
		super();
		this.courseModuleId = courseModuleId;
		this.courseId = courseId;
		this.courseModuleName = courseModuleName;
	}

	public String getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(String courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseModuleName() {
		return courseModuleName;
	}

	public void setCourseModuleName(String courseModuleName) {
		this.courseModuleName = courseModuleName;
	}

	@Override
	public String toString() {
		return "CourseModule [courseModuleId=" + courseModuleId + ", courseId=" + courseId + ", courseModuleName="
				+ courseModuleName + "]";
	}



}