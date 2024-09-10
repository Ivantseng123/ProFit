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
@Table(name="course_lessons")
public class CourseLessonBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="course_lesson_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String courseLessonId;
	
	@Column(name="course_module_id",insertable = false,updatable = false)
	private String courseModuleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_module_id")
	private CourseModuleBean courseModule;
	
	@Column(name="course_id",insertable = false,updatable = false)
	private String courseId;

	@Column(name="course_lesson_name")
	private String courseLessonName;
	
	@Column(name="course_lesson_sort")
	private String courseLessonSort;
	
	@Column(name="lesson_media_url")
	private String lessonMediaUrl;
	
	@Column(name="lesson_media_type")
	private String lessonMediaType;
	
	@Column(name = "lesson_media_duration")
	private String mediaDuration;


	public CourseLessonBean() {
		super();
	}

	public CourseLessonBean(String courseLessonId, String courseModuleId, String courseId, String courseLessonName,
			String courseLessonSort, String lessonMediaUrl, String lessonMediaType, String mediaDuration) {
		super();
		this.courseLessonId = courseLessonId;
		this.courseModuleId = courseModuleId;
		this.courseId = courseId;
		this.courseLessonName = courseLessonName;
		this.courseLessonSort = courseLessonSort;
		this.lessonMediaUrl = lessonMediaUrl;
		this.lessonMediaType = lessonMediaType;
		this.mediaDuration = mediaDuration;
	}
	

	public CourseLessonBean(String courseLessonName, String courseLessonSort,
			String lessonMediaUrl, String lessonMediaType, String mediaDuration) {
		super();
		this.courseLessonName = courseLessonName;
		this.courseLessonSort = courseLessonSort;
		this.lessonMediaUrl = lessonMediaUrl;
		this.lessonMediaType = lessonMediaType;
		this.mediaDuration = mediaDuration;
	}

	public CourseModuleBean getCourseModule() {
		return courseModule;
	}
	
	public void setCourseModule(CourseModuleBean courseModule) {
		this.courseModule = courseModule;
	}
	public String getCourseLessonId() {
		return courseLessonId;
	}

	public void setCourseLessonId(String courseLessonId) {
		this.courseLessonId = courseLessonId;
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

	public String getCourseLessonName() {
		return courseLessonName;
	}

	public void setCourseLessonName(String courseLessonName) {
		this.courseLessonName = courseLessonName;
	}

	public String getCourseLessonSort() {
		return courseLessonSort;
	}

	public void setCourseLessonSort(String courseLessonSort) {
		this.courseLessonSort = courseLessonSort;
	}

	public String getLessonMediaUrl() {
		return lessonMediaUrl;
	}

	public void setLessonMediaUrl(String lessonMediaUrl) {
		this.lessonMediaUrl = lessonMediaUrl;
	}

	public String getLessonMediaType() {
		return lessonMediaType;
	}

	public void setLessonMediaType(String lessonMediaType) {
		this.lessonMediaType = lessonMediaType;
	}

	public String getMediaDuration() {
		return mediaDuration;
	}

	public void setMediaDuration(String mediaDuration) {
		this.mediaDuration = mediaDuration;
	}

	@Override
	public String toString() {
		return "CourseLessonBean [courseLessonId=" + courseLessonId + ", courseModuleId=" + courseModuleId
				+ ", courseId=" + courseId + ", courseLessonName=" + courseLessonName + ", courseLessonSort="
				+ courseLessonSort + ", lessonMediaUrl=" + lessonMediaUrl + ", lessonMediaType=" + lessonMediaType
				+ ", mediaDuration=" + mediaDuration + "]";
	}


}
