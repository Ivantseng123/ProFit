package com.ProFit.dao.coursesCRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ProFit.bean.coursesBean.CourseLessonBean;

public class HcourseLessonDao implements IHcourseLessonDao {
	
	private Session session;
	
	public HcourseLessonDao(Session session) {
		this.session = session;
	}
	
	//新增章節單元
	@Override
	public CourseLessonBean insertCourseLesson(CourseLessonBean courseLesson) {
		if(courseLesson!=null) {
			session.persist(courseLesson);
			return courseLesson;
		}
		return null;
	}

	//刪除章節單元By id
	@Override
	public boolean deleteCourseLessonByID(String courseLessonId) {
		CourseLessonBean courseLessonBean = session.get(CourseLessonBean.class, courseLessonId);
		if(courseLessonBean!=null) {
			session.remove(courseLessonBean);
			return true;
		}
		return false;
	}
	
	//更新課程單元
	@Override
	public boolean updateCourseLessonById(CourseLessonBean newCourseLesson) {
		CourseLessonBean oldCourseLesson = session.get(CourseLessonBean.class, newCourseLesson.getCourseLessonId());
		
		if(oldCourseLesson==null) {
			System.out.println("Lesson does not exist");
			return false;
		}
		
		//對比新舊物件的屬性
		oldCourseLesson.setCourseLessonName(
				newCourseLesson.getCourseLessonName() == null || newCourseLesson.getCourseLessonName().isEmpty()
					?oldCourseLesson.getCourseLessonName()
					:newCourseLesson.getCourseLessonName());
		
		oldCourseLesson.setCourseLessonSort(
				newCourseLesson.getCourseLessonSort() == null || newCourseLesson.getCourseLessonSort().isEmpty()
					?oldCourseLesson.getCourseLessonSort()
					:newCourseLesson.getCourseLessonSort());
		
		oldCourseLesson.setLessonMediaType(
				newCourseLesson.getLessonMediaType() == null || newCourseLesson.getLessonMediaType().isEmpty()
					?oldCourseLesson.getLessonMediaType()
					:newCourseLesson.getLessonMediaType());
		
		oldCourseLesson.setLessonMediaUrl(
				newCourseLesson.getLessonMediaUrl() == null || newCourseLesson.getLessonMediaUrl().isEmpty()
					?oldCourseLesson.getLessonMediaUrl()
					:newCourseLesson.getLessonMediaUrl());
		
		oldCourseLesson.setMediaDuration(
				newCourseLesson.getMediaDuration() ==null || newCourseLesson.getMediaDuration().isEmpty()
					?oldCourseLesson.getMediaDuration()
					:newCourseLesson.getMediaDuration());
		
		session.merge(oldCourseLesson);
		return true;
	}
	
	//搜尋單個單元by id
	@Override
	public CourseLessonBean searchOneCourseLessonById(int courseLessonId) {
		return session.get(CourseLessonBean.class, courseLessonId);
	}

	//搜尋全部單元
	@Override
	public List<CourseLessonBean> searchCourseLessons() {
		Query<CourseLessonBean> query = session.createQuery("from CourseLessonBean",CourseLessonBean.class);
		return query.list();
	}

	//搜尋全部單元by courseModule id
	@Override
	public List<CourseLessonBean> searchCourseLessonsByModuleId(int courseModuleid) {
		Query<CourseLessonBean> query = session.createQuery("from CourseLessonBean where courseModule.courseModuleId = :courseModuleId",CourseLessonBean.class);
		query.setParameter("courseModuleId", courseModuleid);
		return query.getResultList();
	}

}
