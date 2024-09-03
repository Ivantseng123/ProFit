package com.ProFit.bean;

public class CourseLessonBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String courseLessonId;
	private String courseModuleId;
	private String courseId;
	private String courseLessonName;
	private String courseLessonSort;
	private String lessonMediaUrl;
	private String lessonMediaType;
	private String mediaDuration;
	
	public CourseLessonBean() {
		super();
		// TODO Auto-generated constructor stub
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
