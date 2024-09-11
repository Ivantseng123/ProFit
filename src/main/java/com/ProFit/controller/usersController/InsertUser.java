package com.ProFit.controller.usersController;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.hibernateutil.HibernateUtil;
import com.ProFit.dao.usersDao.HUserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertUser")
public class InsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertUser() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		String user_password = request.getParameter("user_password");
		String user_phonenumber = request.getParameter("user_phonenumber");
		String user_city = request.getParameter("user_city");
		Integer user_identity = 1;
		Integer user_balance = 0;
		Integer freelancer_profile_status = 0;

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		HUserDao userDao = new HUserDao(session);

		try {

			Users existuser = userDao.getUserByEmail(user_email);

			if (existuser == null) {

				String user_passwordHash = toHexString(getSHA(user_password));

				Users user = new Users(user_name, user_email, user_passwordHash, user_phonenumber, user_city,
						user_identity, user_balance, freelancer_profile_status);

				userDao.saveUserInfo(user);

				session.flush(); // 將緩存的更改同步到數據庫
				session.clear(); // 清空緩存，強制從數據庫重新查詢
			}

			request.getRequestDispatcher("/GetAlluser").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public static byte[] getSHA(String pwd) throws NoSuchAlgorithmException {
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		return md.digest(pwd.getBytes(StandardCharsets.UTF_8));
	}

	public static String toHexString(byte[] hash) {
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
