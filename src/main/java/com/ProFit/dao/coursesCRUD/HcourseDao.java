package com.ProFit.dao.coursesCRUD;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.coursesBean.CourseBean;

@Repository
@Transactional
public class HcourseDao implements IHcourseDao {

	@Autowired
	private SessionFactory factory;


	// 新增課程
	@Override
	public CourseBean insertCourse(CourseBean course) {
		Session session = factory.getCurrentSession();
		// 查詢當前最大值的數值部分
		String hql = "SELECT MAX(CAST(SUBSTRING(course.courseId, 2) AS int)) FROM CourseBean course";
		Integer maxCourseIdNumber = (Integer) session.createQuery(hql, Integer.class).uniqueResult();

		// 生成新的course_id
		int newCourseIdNumber = (maxCourseIdNumber == null) ? 100 : maxCourseIdNumber + 1;
		String newCourseId = String.format("C%04d", newCourseIdNumber); // 格式化新的course_id
		System.out.println("New Course ID: " + newCourseId);

		// 設置生成的 courseId 到 courseBean 中
		course.setCourseId(newCourseId);

		// 保存課程
		session.persist(course);
		return course;
	}

	// 刪除課程By course_id
	@Override
	public boolean deleteCourseById(String courseId) {
		Session session = factory.getCurrentSession();
		CourseBean resultBean = session.get(CourseBean.class, courseId);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	// 修改課程
	@Override
	public boolean updateCourseById(CourseBean newCourse) {
		Session session = factory.getCurrentSession();
		// 查詢原始的課程資料
		CourseBean oldCourse = session.get(CourseBean.class, newCourse.getCourseId());

		// 確認查詢結果不為 null（即課程存在）
		if (oldCourse == null) {
			System.out.println("Course with ID " + newCourse.getCourseId() + " does not exist.");
			return false;
		}

		// 對比新舊對象的屬性值，並進行更新操作
		oldCourse.setCourseName(newCourse.getCourseName() == null || newCourse.getCourseName().isEmpty() 
				? oldCourse.getCourseName()
				: newCourse.getCourseName());

		oldCourse.setCourseCreateUserId(newCourse.getCourseCreateUserId() == null || newCourse.getCourseCreateUserId().isEmpty()
				? oldCourse.getCourseCreateUserId()
				: newCourse.getCourseCreateUserId());

		oldCourse.setCourseCategory(newCourse.getCourseCategory() == null || newCourse.getCourseCategory().isEmpty()
				? oldCourse.getCourseCategory()
				: newCourse.getCourseCategory());

		oldCourse.setCourseInformation(newCourse.getCourseInformation() == null || newCourse.getCourseInformation().isEmpty()
				? oldCourse.getCourseInformation()
				: newCourse.getCourseInformation());

		oldCourse.setCourseDescription(newCourse.getCourseDescription() == null || newCourse.getCourseDescription().isEmpty()
				? oldCourse.getCourseDescription()
				: newCourse.getCourseDescription());

		oldCourse.setCourseEnrollmentDate(newCourse.getCourseEnrollmentDate() == null || newCourse.getCourseEnrollmentDate().toString().isEmpty()
				? oldCourse.getCourseEnrollmentDate()
				: newCourse.getCourseEnrollmentDate());

		oldCourse.setCourseStartDate(newCourse.getCourseStartDate() == null || newCourse.getCourseStartDate().toString().isEmpty()
				? oldCourse.getCourseStartDate()
				: newCourse.getCourseStartDate());

		oldCourse.setCourseEndDate(newCourse.getCourseEndDate() == null || newCourse.getCourseEndDate().toString().isEmpty()
				? oldCourse.getCourseEndDate()
				: newCourse.getCourseEndDate());

		oldCourse.setCoursePrice(newCourse.getCoursePrice() == null || newCourse.getCoursePrice().isEmpty() 
				? oldCourse.getCoursePrice()
				: newCourse.getCoursePrice());

		oldCourse.setCourseStatus(newCourse.getCourseStatus() == null || newCourse.getCourseStatus().isEmpty()
				? oldCourse.getCourseStatus()
				: newCourse.getCourseStatus());

		// 保存更改，Hibernate 自動生成更新語句
		session.merge(oldCourse);
		return true;
	}

	// 查詢單筆By couseId
	@Override
	public CourseBean searchOneCourseById(String courseId) {
		Session session = factory.getCurrentSession();
		return session.get(CourseBean.class, courseId);
	}

	// 查詢全部
	@Override
	public List<CourseBean> searchCourses() {
		Session session = factory.getCurrentSession();
		Query<CourseBean> query = session.createQuery("from CourseBean", CourseBean.class);
		return query.list();
	}

	// 查詢全部By 多條件查詢
	@Override
	public List<CourseBean> searchCourses(String courseName, String userName, String status, String userId,
			String category) {
		Session session = factory.getCurrentSession();
		StringBuilder hql = new StringBuilder("SELECT c FROM CourseBean c INNER JOIN c.courseCreater u WHERE 1=1");

		if (courseName != null && !courseName.trim().isEmpty()) {
			hql.append(" AND c.courseName LIKE :courseName");
		}
		if (userName != null && !userName.trim().isEmpty()) {
			hql.append(" AND u.userName LIKE :userName");
		}
		if (status != null && !status.trim().isEmpty()) {
			hql.append(" AND c.courseStatus = :status");
		}
		if (userId != null && !userId.trim().isEmpty()) {
			hql.append(" AND c.courseCreateUserId = :userId");
		}
		if (category != null && !category.trim().isEmpty()) {
			hql.append(" AND c.courseCategory = :category");
		}

		Query<CourseBean> query = session.createQuery(hql.toString(), CourseBean.class);

		// 動態設置參數
		if (courseName != null && !courseName.trim().isEmpty()) {
			query.setParameter("courseName", "%" + courseName + "%");
		}
		if (userName != null && !userName.trim().isEmpty()) {
			query.setParameter("userName", "%" + userName + "%");
		}
		if (status != null && !status.trim().isEmpty()) {
			query.setParameter("status", status);
		}
		if (userId != null && !userId.trim().isEmpty()) {
			query.setParameter("userId", Integer.parseInt(userId));
		}
		if (category != null && !category.trim().isEmpty()) {
			query.setParameter("category", Integer.parseInt(category));
		}

		// 執行查詢
		return query.getResultList();
	}

}
