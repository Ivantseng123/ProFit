package com.ProFit.controller.usersController;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.bean.usersBean.Pwd_reset_tokens;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.HPwdResetTokensDao;
import com.ProFit.dao.usersDao.HUserDao;
import com.ProFit.hibernateutil.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertToken")
public class InsertToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertToken() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		Integer user_id = Integer.valueOf(request.getParameter("user_id"));
		HPwdResetTokensDao pwdResetTokensDao = new HPwdResetTokensDao(session);
		HUserDao userDao = new HUserDao(session);
		try {
			
			Users existuser = userDao.getUserInfoByID(user_id);
					
			if(existuser != null) {
				
				Pwd_reset_tokens pwd_reset_tokens = new Pwd_reset_tokens(user_id, generateToken());
				
				pwdResetTokensDao.saveTokensInfo(pwd_reset_tokens);
				
				session.flush();  // 將緩存的更改同步到數據庫
				session.clear();  // 清空緩存，強制從數據庫重新查詢
			}
			
			

			
			request.getRequestDispatcher("/GetAlltoken").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static String generateToken() throws NoSuchAlgorithmException {
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
