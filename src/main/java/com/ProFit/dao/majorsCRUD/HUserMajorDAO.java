package com.ProFit.dao.majorsCRUD;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.majorsBean.UserMajorPK;
import com.ProFit.bean.usersBean.Users;

public class HUserMajorDAO {

	private Session session;

	public HUserMajorDAO(Session session) {
		this.session = session;
	}

	// 插入 UserMajor
	public boolean insertUserMajor(UserMajorBean userMajor) {
		session.persist(userMajor);
		return true;
	}

	// 删除 UserMajor(by userId & majorId)
	public boolean deleteUserMajor(int userId, int majorId) {
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
	public Map<String, String> getAllUsers() {
		Map<String, String> users = new HashMap<>();
		Query<Users> query = session.createQuery("FROM Users", Users.class);
		List<Users> userList = query.list();
		for (Users user : userList) {
			users.put(user.getUserName(), user.getUserName());
		}
		return users;
	}

	// 查詢所有專業
	public Map<String, String> getAllMajors() throws SQLException {
		Map<String, String> majors = new HashMap<>();
		Query<MajorBean> query = session.createQuery("FROM MajorBean", MajorBean.class);
		List<MajorBean> majorList = query.list();
		for (MajorBean major : majorList) {
			majors.put(major.getMajorName(), major.getMajorName());
		}
		return majors;
	}

	// 查找特定user的所有 Major
	public List<UserMajorBean> findMajorsByUserId(int userId) {
		Query<UserMajorBean> query = session.createQuery("FROM UserMajorBean um WHERE um.id.user.userId = :userId",
				UserMajorBean.class);
		query.setParameter("userId", userId);
		return query.list();
	}

	// 查找特定 Major 的所有 User
	public List<UserMajorBean> findUsersByMajorId(int majorId) {
		Query<UserMajorBean> query = session.createQuery("FROM UserMajorBean um WHERE um.id.major.majorId = :majorId",
				UserMajorBean.class);
		query.setParameter("majorId", majorId);
		return query.list();
	}

	// 查找所有 UserMajor
	public List<UserMajorBean> findAllUserMajors() {
		return session.createQuery("FROM UserMajorBean", UserMajorBean.class).list();
	}

	// 不需要
	/*
	 * public int getUserIdByName(String userName) throws SQLException {
	 * Query<Users> query =
	 * session.createQuery("FROM Users WHERE userName = :userName", Users.class);
	 * query.setParameter("userName", userName); Users user = query.uniqueResult();
	 * return user != null ? user.getUserId() : -1; }
	 */

	// 不需要
	/*
	 * public int getMajorIdByName(String majorName) throws SQLException {
	 * Query<MajorBean> query =
	 * session.createQuery("FROM MajorBean WHERE majorName = :majorName",
	 * MajorBean.class); query.setParameter("majorName", majorName); MajorBean major
	 * = query.uniqueResult(); return major != null ? major.getMajorId() : -1; }
	 */

	// 不需要
	/*
	 * public String getUserNameById(int userId) throws SQLException { Users user =
	 * session.get(Users.class, userId); return user != null ? user.getUserName() :
	 * "Unknown User"; }
	 */
	
	// 不需要
	/*
	 * public String getMajorNameById(int majorId) throws SQLException { MajorBean
	 * major = session.get(MajorBean.class, majorId); return major != null ?
	 * major.getMajorName() : "Unknown Major"; }
	 */
}
