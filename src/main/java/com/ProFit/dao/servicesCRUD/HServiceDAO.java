package com.ProFit.dao.servicesCRUD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.servicesBean.ServiceBean;

@Repository
@Transactional
public class HServiceDAO implements IHServiceDAO {

	@Autowired
	private SessionFactory factory;

	// 新增服務
	@Override
	public boolean insertService(ServiceBean service) {
		Session session = factory.getCurrentSession();
		session.persist(service);
		return true;
	}

	// 更新現有服務
	@Override
	public boolean updateService(ServiceBean service) {
		Session session = factory.getCurrentSession();
		session.merge(service);
		return true;
	}

	// 刪除服務
	@Override
	public boolean deleteService(int id) {
		Session session = factory.getCurrentSession();
		ServiceBean service = session.get(ServiceBean.class, id);
		if (service != null) {
			session.remove(service);
			return true;
		}
		return false;
	}

	// 尋找所有服務
	@Override
	public List<ServiceBean> findAllServices() {
		Session session = factory.getCurrentSession();
		return session.createQuery("FROM ServiceBean", ServiceBean.class).list();
	}

	// 按 ID 尋找服務
	@Override
	public ServiceBean findServiceById(int id) {
		Session session = factory.getCurrentSession();
		return session.get(ServiceBean.class, id);
	}

	// 搜尋服務（可按標題、內容、專業ID和使用者ID搜尋）
	@Override
	public List<ServiceBean> searchServices(String titleKeyword, String contentKeyword, Integer majorId,
			Integer userId) {
		StringBuilder hql = new StringBuilder("FROM ServiceBean s WHERE 1=1");
		Map<String, Object> params = new HashMap<>();

		if (titleKeyword != null && !titleKeyword.isEmpty()) {
			hql.append(" AND s.serviceTitle LIKE :title");
			params.put("title", "%" + titleKeyword + "%");
		}
		if (contentKeyword != null && !contentKeyword.isEmpty()) {
			hql.append(" AND s.serviceContent LIKE :content");
			params.put("content", "%" + contentKeyword + "%");
		}
		if (majorId != null) {
			hql.append(" AND s.userMajor.id.major.majorId = :majorId");
			params.put("majorId", majorId);
		}
		if (userId != null) {
			hql.append(" AND s.userMajor.id.user.userId = :userId");
			params.put("userId", userId);
		}

		Session session = factory.getCurrentSession();
		Query<ServiceBean> query = session.createQuery(hql.toString(), ServiceBean.class);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.list();
	}

	/** 以下為棄用的方法 **/
//	// 取得所有用戶
//	public Map<Integer, String> getAllUsers() {
//		Session session = factory.getCurrentSession();
//		List<Users> users = session.createQuery("FROM Users", Users.class).list();
//		Map<Integer, String> userMap = new HashMap<>();
//		for (Users user : users) {
//			userMap.put(user.getUserId(), user.getUserName());
//		}
//		return userMap;
//	}
//
//	// 取得所有專業
//	public Map<Integer, String> getAllMajors() {
//		Session session = factory.getCurrentSession();
//		List<MajorBean> majors = session.createQuery("FROM MajorBean", MajorBean.class).list();
//		Map<Integer, String> majorMap = new HashMap<>();
//		for (MajorBean major : majors) {
//			majorMap.put(major.getMajorId(), major.getMajorName());
//		}
//		return majorMap;
//	}
//
//	// 取得特定使用者的所有專業
//	public Map<Integer, String> getMajorsByUserId(int userId) {
//		Session session = factory.getCurrentSession();
//		String hql = "SELECT um.id.major FROM UserMajorBean um WHERE um.id.user.userId = :userId";
//		List<MajorBean> majors = session.createQuery(hql, MajorBean.class).setParameter("userId", userId).list();
//		Map<Integer, String> majorMap = new HashMap<>();
//		for (MajorBean major : majors) {
//			majorMap.put(major.getMajorId(), major.getMajorName());
//		}
//		return majorMap;
//	}

}