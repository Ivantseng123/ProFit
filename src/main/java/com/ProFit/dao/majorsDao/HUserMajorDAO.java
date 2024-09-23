package com.ProFit.dao.majorsDao;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.majorsBean.UserMajorPK;
import com.ProFit.bean.usersBean.Users;

@Repository
@Transactional
public class HUserMajorDAO implements IHuserMajorDAO {

	@Autowired
	private SessionFactory factory;

	// 插入 UserMajor
	@Override
	public UserMajorBean insertUserMajor(UserMajorBean userMajor) {
        try {
        	Session session = factory.getCurrentSession();
        	session.persist(userMajor);
            return userMajor;
        } catch (Exception e) {
            return null;
        }
    }

	// 删除 UserMajor(by userId & majorId)
	@Override
	public boolean deleteUserMajor(int userId, int majorId) {
		Session session = factory.getCurrentSession();
		UserMajorPK id = new UserMajorPK();
		id.setUser(session.get(Users.class, userId));
		id.setMajor(session.get(MajorBean.class, majorId));
		UserMajorBean userMajor = session.get(UserMajorBean.class, id);
		if (userMajor != null) {
			session.remove(userMajor);
			return true;
		}
		return false;
	}

	// 查詢所有用戶
	@Override
	public Map<Integer, String> getAllUsers() {
		Session session = factory.getCurrentSession();
		Map<Integer, String> users = new HashMap<>();
		Query<Users> query = session.createQuery("FROM Users", Users.class);
		List<Users> userList = query.list();
		for (Users user : userList) {
			users.put(user.getUserId(), user.getUserName());
		}
		return users;
	}

	// 查詢所有專業
	@Override
	public Map<Integer, String> getAllMajors() throws SQLException {
		Session session = factory.getCurrentSession();
		Map<Integer, String> majors = new HashMap<>();
		Query<MajorBean> query = session.createQuery("FROM MajorBean", MajorBean.class);
		List<MajorBean> majorList = query.list();
		for (MajorBean major : majorList) {
			majors.put(major.getMajorId(), major.getMajorName());
		}
		return majors;
	}

	// 查找特定user的所有 Major
	@Override
	public List<UserMajorBean> findMajorsByUserId(int userId) {
		Session session = factory.getCurrentSession();
		Query<UserMajorBean> query = session.createQuery("FROM UserMajorBean um WHERE um.id.user.userId = :userId",
				UserMajorBean.class);
		query.setParameter("userId", userId);
		return query.list();
	}

	// 查找特定 Major 的所有 User
	@Override
	public List<UserMajorBean> findUsersByMajorId(int majorId) {
		Session session = factory.getCurrentSession();
		Query<UserMajorBean> query = session.createQuery("FROM UserMajorBean um WHERE um.id.major.majorId = :majorId",
				UserMajorBean.class);
		query.setParameter("majorId", majorId);
		return query.list();
	}

	// 查找所有 UserMajor
	@Override
	public List<UserMajorBean> findAllUserMajors() {
		Session session = factory.getCurrentSession();
		return session.createQuery("FROM UserMajorBean", UserMajorBean.class).list();
	}

}
