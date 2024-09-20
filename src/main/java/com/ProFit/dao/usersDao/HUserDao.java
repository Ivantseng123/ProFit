package com.ProFit.dao.usersDao;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.usersBean.Users;

@Repository
@Transactional
public class HUserDao implements IHUserDao {

	@Autowired
	private SessionFactory factory;
	// private Session session;

	// 新增user
	@Override
	public Users saveUserInfo(Users user) {
		Session session = factory.getCurrentSession();
		session.persist(user);
		session.flush();
		return user;

	}

	// 刪除user By ID
	@Override
	public boolean deleteUserInfo(int user_id) {
		Session session = factory.getCurrentSession();
		Users resultBean = session.get(Users.class, user_id);
		if (resultBean != null) {
			session.remove(resultBean);
			session.flush();
			return true;
		}
		return false;
	}

	// 修改user密碼
	@Override
	public boolean updateUserPwd(String pwd, int user_id) {
		Session session = factory.getCurrentSession();
		Users resultBean = session.get(Users.class, user_id);

		if (resultBean == null) {
			return false;
		}
		resultBean.setUserPasswordHash(pwd);
		session.merge(resultBean);
		session.flush();
		return true;
	}

	// 修改user資料
	@Override
	public boolean updateUserInfo(Users user) {
		Session session = factory.getCurrentSession();
		session.merge(user);
		session.flush();
		return true;
	}

	// 給予user企業資格
	@Override
	public boolean updateUserIdentity(int user_id) {
		Session session = factory.getCurrentSession();
		Users resultBean = session.get(Users.class, user_id);

		if (resultBean == null) {
			return false;
		}
		resultBean.setUserIdentity(2);
		session.merge(resultBean);
		session.flush();
		return true;
	}

	// 查詢全部user
	@Override
	public List<Users> getAllUserInfo() {
		Session session = factory.getCurrentSession();
		Query<Users> query = session.createQuery("from Users", Users.class);
		return query.list();
	}

	@Override
	public boolean validate(String userEmail, String userPasswordHash) {
		Session session = factory.getCurrentSession();
		boolean status = false;
		String hql = "FROM Users u WHERE u.userEmail = :email AND u.userPasswordHash = "
				+ ":password AND u.userIdentity = :identity";
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
		Session session = factory.getCurrentSession();
		String pictureURL = null;

		String hql = "SELECT u.userPictureURL FROM Users u WHERE u.userEmail LIKE :email";
		Query<String> query = session.createQuery(hql, String.class);
		query.setParameter("email", "%" + userEmail + "%");

		// 獲取查詢結果
		pictureURL = query.uniqueResult();

		return pictureURL;
	}

	// 查詢全單筆user
	@Override
	public Users getUserInfoByID(Integer user_id) {
		Session session = factory.getCurrentSession();
		return session.get(Users.class, user_id);
	}

	@Override
	public Users getUserByEmail(String user_email) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT u FROM Users u WHERE u.userEmail LIKE :email";
		Query<Users> query = session.createQuery(hql, Users.class);
		query.setParameter("email", "%" + user_email + "%");

		// 獲取查詢結果
		Users existuser = query.uniqueResult();

		return existuser;
	}

	@Override
	public byte[] getSHA(String pwd) throws NoSuchAlgorithmException {
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		return md.digest(pwd.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 64) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

}
