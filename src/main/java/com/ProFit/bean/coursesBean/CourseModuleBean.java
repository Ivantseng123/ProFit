package com.ProFit.bean.coursesBean;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="course_module")
public class CourseModuleBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="course_module_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String courseModuleId;
	
	@Column(name="course_id",insertable = false,updatable = false)
	private String courseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_id")
	private CourseBean course;
	
	@Column(name="course_module_name")
	private String courseModuleName;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "courseModule",cascade = CascadeType.ALL)
	private List<CourseLessonBean> moduleLessons;

	public CourseModuleBean() {
		super();
	}

	public CourseModuleBean(String courseModuleId, String courseId, String courseModuleName) {
		super();
		this.courseModuleId = courseModuleId;
		this.courseId = courseId;
		this.courseModuleName = courseModuleName;
	}
	

	public CourseModuleBean(String courseId, String courseModuleName) {
		super();
		this.courseId = courseId;
		this.courseModuleName = courseModuleName;
	}

	public List<CourseLessonBean> getModuleLessons() {
		return moduleLessons;
	}
	
	public void setModuleLessons(List<CourseLessonBean> moduleLessons) {
		this.moduleLessons = moduleLessons;
	}
	public CourseBean getCourse() {
		return course;
	}
	
	public void setCourse(CourseBean course) {
		this.course = course;
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