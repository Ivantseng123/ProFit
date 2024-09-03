package com.ProFit.bean;

public class CourseGradeContentBean implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	
	private String courseGradeId;
	private String courseId;
	private String studentId;
	private String courseGradeScore;
	
	public CourseGradeContentBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseGradeContentBean(String courseGradeId, String courseId, String studentId, String courseGradeScore) {
		super();
		this.courseGradeId = courseGradeId;
		this.courseId = courseId;
		this.studentId = studentId;
		this.courseGradeScore = courseGradeScore;
	}

	public String getCourseGradeId() {
		return courseGradeId;
	}

	public void setCourseGradeId(String courseGradeId) {
		this.courseGradeId = courseGradeId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseGradeScore() {
		return courseGradeScore;
	}

	public void setCourseGradeScore(String courseGradeScore) {
		this.courseGradeScore = courseGradeScore;
	}

	@Override
	public String toString() {
		return "CourseGradeContentBean [courseGradeId=" + courseGradeId + ", courseId=" + courseId + ", studentId="
				+ studentId + ", courseGradeScore=" + courseGradeScore + "]";
	}
	
	
	
}
