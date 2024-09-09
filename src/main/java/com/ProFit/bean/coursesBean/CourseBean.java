package com.ProFit.bean.coursesBean;

import java.time.LocalDateTime;
import java.util.List;

import com.ProFit.bean.majorsBean.MajorCategoryBeam;
import com.ProFit.bean.usersBean.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="courses")
public class CourseBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Column(name="course_id")
	private String courseId;

	@Column(name="course_name")
	private String courseName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_create_user_id",insertable = false,updatable = false)
	private Users courseCreater;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "course",cascade = CascadeType.ALL)
	private List<CourseModuleBean> courseModules;

	@Column(name="course_create_user_id")
	private String courseCreateUserId;

	//改成hibernate之後要刪掉
	@Transient
	private String createUserName; //table中沒有，為了前端呈現而設定

	@Column(name="course_category", insertable = false,updatable = false)
	private String courseCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_category")
	private MajorCategoryBeam majorCategory;

	@Column(name = "course_information")
	private String courseInformation;

	@Column(name="course_description")
	private String courseDescription;

	@Column(name="course_enrollment_date")
	private LocalDateTime courseEnrollmentDate;

	@Column(name="course_start_date")
	private LocalDateTime courseStartDate;

	@Column(name="course_end_date")
	private LocalDateTime courseEndDate;

	@Column(name="course_price")
	private String coursePrice;

	@Column(name="course_status")
	private String courseStatus;

	public CourseBean() {
		super();
	}
	
	
	public CourseBean(String courseId, String courseName, String courseCreateUserId, String courseCategory,
			String courseInformation, String courseDescription, LocalDateTime courseEnrollmentDate, LocalDateTime courseStartDate,
			LocalDateTime courseEndDate, String coursePrice, String courseStatus) {
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
			String courseDescription, LocalDateTime courseEnrollmentDate, LocalDateTime courseStartDate, LocalDateTime courseEndDate,
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
	
	public Users getCourseCreater() {
		return courseCreater;
	}
	
	
	public void setCourseCreater(Users courseCreater) {
		this.courseCreater = courseCreater;
	}
	
	
	public List<CourseModuleBean> getCourseModules() {
		return courseModules;
	}
	
	
	public void setCourseModules(List<CourseModuleBean> courseModules) {
		this.courseModules = courseModules;
	}
	
	public Users getCreateUsersId() {
		return courseCreater;
	}


	public void setCreateUsersId(Users courseCreater) {
		this.courseCreater = courseCreater;
	}


	public MajorCategoryBeam getMajorCategory() {
		return majorCategory;
	}


	public void setMajorCategory(MajorCategoryBeam majorCategory) {
		this.majorCategory = majorCategory;
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
	public LocalDateTime getCourseEnrollmentDate() {
		return courseEnrollmentDate;
	}

	public void setCourseEnrollmentDate(LocalDateTime courseEnrollmentDate) {
		this.courseEnrollmentDate = courseEnrollmentDate;
	}
	public LocalDateTime getCourseStartDate() {
		return courseStartDate;
	}
	public void setCourseStartDate(LocalDateTime courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	public LocalDateTime getCourseEndDate() {
		return courseEndDate;
	}
	public void setCourseEndDate(LocalDateTime courseEndDate) {
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