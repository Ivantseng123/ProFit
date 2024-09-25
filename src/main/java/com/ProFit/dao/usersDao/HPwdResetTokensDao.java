package com.ProFit.dao.usersDao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.usersBean.Pwd_reset_tokens;

@Repository
@Transactional
public class HPwdResetTokensDao implements IHPwdResetTokensDao {

	@Autowired
	private SessionFactory factory;
	
	
	
	//新增token
	@Override
	public Pwd_reset_tokens saveTokensInfo(Pwd_reset_tokens token) {
		Session session = factory.getCurrentSession();
		session.persist(token);
		session.flush();
		return token;
	}
	
	// 刪除token By ID
	@Override
	public boolean deleteTokensInfo(int token_id) {
		Session session = factory.getCurrentSession();
		Pwd_reset_tokens resultBean = session.get(Pwd_reset_tokens.class, token_id);
		if (resultBean != null) {
			session.remove(resultBean);
			session.flush();
			return true;
		}
		return false;
	}

	//查詢全部tokens
	@Override
	public List<Pwd_reset_tokens> getAllTokensInfo() {
		Session session = factory.getCurrentSession();
		List<Pwd_reset_tokens> tokens = new ArrayList<>();
		String hql = "SELECT t FROM Pwd_reset_tokens t JOIN t.user u";
		Query<Pwd_reset_tokens> query = session.createQuery(hql, Pwd_reset_tokens.class);
		tokens = query.getResultList();
		return tokens;
	}
	
	//產生token
	@Override
	public String generateToken() throws NoSuchAlgorithmException {
		// 生成隨機字節數組
		SecureRandom secureRandom = new SecureRandom();
		byte[] randomBytes = new byte[32]; // 生成 32 個字節的隨機數據
		secureRandom.nextBytes(randomBytes);

		// 使用 SHA-256 進行哈希
		MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
		byte[] hashedBytes = sha256Digest.digest(randomBytes);

		// 取前 16 個字節並轉換為十六進制字符串
		StringBuilder token = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			token.append(String.format("%02x", hashedBytes[i]));
		}

		return token.toString();
	}

}
