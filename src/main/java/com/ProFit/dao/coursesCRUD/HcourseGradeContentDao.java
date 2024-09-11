package com.ProFit.dao.coursesCRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ProFit.bean.coursesBean.CourseGradeContentBean;

public class HcourseGradeContentDao implements IHcourseGradeContentDao {

	private Session session;
	
	public HcourseGradeContentDao(Session session) {
		this.session = session;
	}
	
	// 新增課程評價
	@Override
	public CourseGradeContentBean insertCourse(CourseGradeContentBean courseGradeContent) {
		if(courseGradeContent!=null) {
			session.persist(courseGradeContent);
		}
		return courseGradeContent;
	}
	
	// 刪除課程評價 by id
	@Override
	public boolean deleteCourseByID(Integer courseGradeId) {
		if( courseGradeId!=null) {
			session.remove(courseGradeId);
			return true;
		}
		return false;
	}
	
	// 更新課程評價 by id
	@Override
	public boolean updateCourseById(CourseGradeContentBean newCourseGrade) {
		//查詢原始資料，確認存在
		CourseGradeContentBean oldGrade = session.get(CourseGradeContentBean.class, newCourseGrade);
		
		if(oldGrade == null) {
			System.out.println("CourseGradeId "+newCourseGrade.getCourseGradeId()+"does not exist");
			return false;
		}
		
		oldGrade.setCourseGradeScore(newCourseGrade.getCourseGradeScore()==null || newCourseGrade.getCourseGradeScore().toString().isEmpty()
				?oldGrade.getCourseGradeScore()
				:newCourseGrade.getCourseGradeScore());
		
		oldGrade.setCourseGradeComment(newCourseGrade.getCourseGradeComment()==null|| newCourseGrade.getCourseGradeComment().isEmpty()
				?oldGrade.getCourseGradeComment()
				:newCourseGrade.getCourseGradeComment());
		
		session.merge(oldGrade);
		
		return true;
	}
	
	// 查詢單筆評價By courseGradeId
	@Override
	public CourseGradeContentBean searchOneCourseGradeContentById(Integer courseGradeId) {
		return session.get(CourseGradeContentBean.class,courseGradeId);
	}
	
	// 查詢全部 多形
	@Override
	public List<CourseGradeContentBean> searchCourseGradeContents() {
		Query<CourseGradeContentBean> query = session.createQuery("from CourseGradeContentBean",CourseGradeContentBean.class);
		return query.list();
	}
	
	// 查詢全部 By 課程評價分數
	@Override
	public List<CourseGradeContentBean> searchCourseGradeContents(String courseId) {
		StringBuilder hql = new StringBuilder("FROM CourseGradeContentBean cg WHERE 1=1");
		
		if(courseId!=null && !courseId.trim().isEmpty()) {
			hql.append(" AND cg.courseId = :courseId");
		}
		
		Query<CourseGradeContentBean> query = session.createQuery(hql.toString(),CourseGradeContentBean.class);
		
		if(courseId!=null && !courseId.trim().isEmpty()) {
			query.setParameter("courseId", courseId);
		}
		
		return query.getResultList();
	}

}
