package com.ProFit.dao.usersDao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;


import com.ProFit.bean.usersBean.Users;

public class HUserDao implements IHUserDao {

	private Session session;

	public HUserDao(Session session) {
		this.session = session;
	}

	// 新增user
	@Override
	public Users saveUserInfo(Users user) {

		session.persist(user);
		return user;

	}

	// 刪除user By ID
	@Override
	public boolean deleteUserInfo(int user_id) {
		Users resultBean = session.get(Users.class, user_id);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	// 修改user密碼
	@Override
	public boolean updateUserPwd(String pwd, int user_id) {
		Users resultBean = session.get(Users.class, user_id);

		if (resultBean == null) {
			return false;
		}
		resultBean.setUser_passwordHash(pwd);
		session.merge(resultBean);
		return true;
	}

	// 修改user資料
	@Override
	public boolean updateUserInfo(Users user) {
		session.merge(user);
		return true;
	}

	// 給予user企業資格
	@Override
	public boolean updateUserIdentity(int user_id) {
		Users resultBean = session.get(Users.class, user_id);

		if (resultBean == null) {
			return false;
		}
		resultBean.setUser_identity(2);
		session.merge(resultBean);
		return true;
	}

	//查詢全部user
	@Override
	public List<Users> getAllUserInfo() {
		Query<Users> query = session.createQuery("from Users", Users.class);
		return query.list();
	}

	@Override
	public boolean validate(String userEmail, String userPasswordHash) {
		boolean status = false;
		String hql = "FROM Users u WHERE u.userEmail = :email AND u.userPasswordHash = :password AND u.userIdentity = :identity";
		Query<Users> query = session.createQuery(hql, Users.class);
		query.setParameter("email", userEmail);
		query.setParameter("password", userPasswordHash);
		query.setParameter("identity", 3);

		// 查詢結果
		Users user = query.uniqueResult();
		status = (user != null);
		return status;
	}

	@Override
	public String getUserPictureByEmail(String userEmail) {
		String pictureURL = null;

		String hql = "SELECT u.userPictureURL FROM Users u WHERE u.userEmail LIKE :email";
		Query<String> query = session.createQuery(hql, String.class);
		query.setParameter("email", "%" + userEmail + "%");

		// 獲取查詢結果
		pictureURL = query.uniqueResult();

		return pictureURL;
	}

	@Override
	public Users getUserInfoByID(Integer user_id) {

		return session.get(Users.class, user_id);
	}

}
