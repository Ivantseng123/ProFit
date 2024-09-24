package com.ProFit.dao.coursesCRUD;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.coursesBean.CourseModuleBean;

@Repository
@Transactional
public class HcourseModuleDao implements IHcourseModuleDao {

	@Autowired
	private SessionFactory factory;
//	private Session session;

//	public HcourseModuleDao(Session session) {
//		this.session = session;
//	}

	// 新增課程章節
	@Override
	public CourseModuleBean insertCourseModule(CourseModuleBean courseModule) {
		Session session = factory.openSession();
		if (courseModule != null) {
			session.persist(courseModule);
			return courseModule;
		}
		return null;
	}

	// 刪除課程章節By id
	@Override
	public boolean deleteCourseModuleById(String courseModuleId) {
		Session session = factory.openSession();
		CourseModuleBean courseModuleBean = session.get(CourseModuleBean.class, courseModuleId);
		if (courseModuleBean != null) {
			session.remove(courseModuleBean);
			return true;
		}
		return false;
	}

	// 更新課程章節
	@Override
	public boolean updateCourseModuleById(CourseModuleBean newCourseModule) {
		Session session = factory.openSession();
		CourseModuleBean oldCourseModule = session.get(CourseModuleBean.class, newCourseModule.getCourseModuleId());

		if (oldCourseModule == null) {
			System.out.println("Module does not exist");
			return false;
		}

		// 對比新舊物件的屬性
		oldCourseModule.setCourseModuleName(
				newCourseModule.getCourseModuleName() == null || newCourseModule.getCourseModuleName().isEmpty()
						? oldCourseModule.getCourseModuleName()
						: newCourseModule.getCourseModuleName());

		session.merge(oldCourseModule);
		return true;
	}

	// 搜尋課程章節by id
	@Override
	public CourseModuleBean searchOneCourseModuleById(int courseModuleId) {
		Session session = factory.openSession();
		return session.get(CourseModuleBean.class, courseModuleId);
	}

	// 搜尋全部課程章節
	@Override
	public List<CourseModuleBean> searchCourseModules() {
		Session session = factory.openSession();
		Query<CourseModuleBean> query = session.createQuery("from CourseModuleBean", CourseModuleBean.class);
		return query.list();
	}

	// 搜尋課程章節by courseId
	@Override
	public List<CourseModuleBean> searchCourseModules(String course_id) {
		Session session = factory.openSession();
		Query<CourseModuleBean> query = session.createQuery("from CourseModuleBean where course.courseId = :courseid",
				CourseModuleBean.class);
		query.setParameter("courseid", course_id); // 使用 setParameter 設定參數的值
		return query.getResultList(); // 執行查詢並返回結果
	}

}
