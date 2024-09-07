package com.ProFit.bean.coursesBean;

import com.ProFit.bean.usersBean.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="course_order")
public class CourseOrderBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="course_order_id")
	private String courseOrderId;
	
	@Column(name="course_id")
	private String courseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_id",insertable = false,updatable = false)
	private CourseBean course;
	
	@Column(name="student_id")
	private String studentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="student_id",insertable = false,updatable = false)
	private Users studnt;
	
	@Column(name="course_order_price")
	private String courseOrderPrice;
	
	@Column(name="course_order_create_date")
	private String courseOrderCreateDate;
	
	@Column(name="course_order_remark")
	private String courseOrderRemark;
	
	@Column(name="course_order_status")
	private String courseOrderStatus;
	
	public CourseOrderBean() {
		super();
	}

	public CourseOrderBean(String courseOrderId, String courseId, String studentId, String courseOrderPrice,
			String courseOrderCreateDate,String courseOrderRemark,String courseOrderStatus) {
		super();
		this.courseOrderId = courseOrderId;
		this.courseId = courseId;
		this.studentId = studentId;
		this.courseOrderPrice = courseOrderPrice;
		this.courseOrderCreateDate = courseOrderCreateDate;
		this.courseOrderRemark = courseOrderRemark;
		this.courseOrderStatus = courseOrderStatus;
	}
	
	public String getCourseOrderStatus() {
		return courseOrderStatus;
	}
	
	public void setCourseOrderStatus(String courseOrderStatus) {
		this.courseOrderStatus = courseOrderStatus;
	}
	public String getCourseOrderRemark() {
		return courseOrderRemark;
	}
	
	public void setCourseOrderRemark(String courseOrderRemark) {
		this.courseOrderRemark = courseOrderRemark;
	}
	public CourseBean getCourse() {
		return course;
	}

	public void setCourse(CourseBean course) {
		this.course = course;
	}

	public Users getStudnt() {
		return studnt;
	}

	public void setStudnt(Users studnt) {
		this.studnt = studnt;
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
