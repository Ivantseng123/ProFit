package com.ProFit.bean.coursesBean;

public class CourseOrderBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String courseOrderId;
	private String courseId;
	private String studentId;
	private String courseOrderPrice;
	private String courseOrderCreateDate;

	public CourseOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseOrderBean(String courseOrderId, String courseId, String studentId, String courseOrderPrice,
			String courseOrderCreateDate) {
		super();
		this.courseOrderId = courseOrderId;
		this.courseId = courseId;
		this.studentId = studentId;
		this.courseOrderPrice = courseOrderPrice;
		this.courseOrderCreateDate = courseOrderCreateDate;
	}

	public String getCourseOrderId() {
		return courseOrderId;
	}

	public void setCourseOrderId(String courseOrderId) {
		this.courseOrderId = courseOrderId;
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

	public String getCourseOrderPrice() {
		return courseOrderPrice;
	}

	public void setCourseOrderPrice(String courseOrderPrice) {
		this.courseOrderPrice = courseOrderPrice;
	}

	public String getCourseOrderCreateDate() {
		return courseOrderCreateDate;
	}

	public void setCourseOrderCreateDate(String courseOrderCreateDate) {
		this.courseOrderCreateDate = courseOrderCreateDate;
	}



}
