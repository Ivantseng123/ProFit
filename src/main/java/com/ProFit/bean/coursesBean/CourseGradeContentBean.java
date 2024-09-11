package com.ProFit.bean.coursesBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="course_grade_content")
public class CourseGradeContentBean implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="course_grade_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseGradeId;
	
	@Column(name="course_id")
	private String courseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_id",insertable = false, updatable = false)
	private CourseBean course;
	
	@Column(name="student_id")
	private String studentId;
	
	@Column(name="course_grade_score")
	private Integer courseGradeScore;

	@Column(name="course_grade_comment")
	private String courseGradeComment;

	public CourseGradeContentBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseGradeContentBean(Integer courseGradeId, String courseId, String studentId, Integer courseGradeScore,String courseGradeComment) {
		super();
		this.courseGradeId = courseGradeId;
		this.courseId = courseId;
		this.studentId = studentId;
		this.courseGradeScore = courseGradeScore;
		this.courseGradeComment=courseGradeComment;
	}

	public CourseBean getCourse() {
		return course;
	}
	
	public void setCourse(CourseBean course) {
		this.course = course;
	}
	public Integer getCourseGradeId() {
		return courseGradeId;
	}

	public void setCourseGradeId(Integer courseGradeId) {
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

	public Integer getCourseGradeScore() {
		return courseGradeScore;
	}

	public void setCourseGradeScore(Integer courseGradeScore) {
		this.courseGradeScore = courseGradeScore;
	}
	
	public String getCourseGradeComment() {
		return courseGradeComment;
	}

	public void setCourseGradeComment(String courseGradeComment) {
		this.courseGradeComment = courseGradeComment;
	}
	
	@Override
	public String toString() {
		return "CourseGradeContentBean [courseGradeId=" + courseGradeId + ", courseId=" + courseId + ", studentId="
				+ studentId + ", courseGradeScore=" + courseGradeScore + "]";
	}



}
