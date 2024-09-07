package com.ProFit.dao.coursesCRUD;

import java.util.List;

import org.hibernate.Session;

import com.ProFit.bean.coursesBean.CourseOrderBean;

public class HcourseOrderDao implements IHcourseOrderDao {
	
	private Session session;
	
	public HcourseOrderDao(Session session) {
		this.session = session;
	}

	// 新增課程訂單
	@Override
	public CourseOrderBean insertCourseOrder(CourseOrderBean courseOrder) {
		//查詢當前最大值的數值部分
		String hql = "SELECT MAX(CAST(SUBSTRING(courseOrder.courseOrderId,2) AS int)) FROM CourseOrderBean courseOrder";
		Integer maxCourseOrderIdNumber = (Integer)session.createQuery(hql,Integer.class).uniqueResult();
		
		//生成新的course_id
		int newCourseOrderIdNumber = (maxCourseOrderIdNumber== null)?100:maxCourseOrderIdNumber+1;
		String newCourseOrderId = String.format("CR%03d", newCourseOrderIdNumber);
		System.out.println("New CourseOrder ID: "+newCourseOrderId);
		
		//設置生成的courseOrderId 到 courseOrderBean中
		courseOrder.setCourseOrderId(newCourseOrderId);
		
		//保存課程
		session.persist(courseOrder);
		return courseOrder;
	}
	
	// 刪除課程訂單
	@Override
	public boolean deleteCourseOrderByID(String courseOrderId) {
		CourseOrderBean resultBean = session.get(CourseOrderBean.class, courseOrderId);
		if(resultBean!=null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	// 更新課程訂單 by id
	@Override
	public boolean updateCourseOrderById(CourseOrderBean newCourseOrder) {
		CourseOrderBean oldCourseOrder = session.get(CourseOrderBean.class, newCourseOrder.getCourseOrderId());
		
		if(oldCourseOrder==null) {
			System.out.println("CourseOrder with ID"+newCourseOrder.getCourseOrderId()+"does not exist");
			return false;
		}
		
		// 對比新舊對象的屬性值
//		oldCourseOrder.set
		
		return true;
	}

	// 搜尋單筆課程訂單 by id
	@Override
	public CourseOrderBean searchOneCourseOrderById(String courseOrderId) {
		return session.get(CourseOrderBean.class, courseOrderId);
	}

	// 搜尋全部課程訂單
	@Override
	public List<CourseOrderBean> searchCourseOrders() {
		return null;
	}
	
	// 搜尋課程訂單by 多條件
	@Override
	public List<CourseOrderBean> searchCourseOrders(String courseId) {
		return null;
	}

}
