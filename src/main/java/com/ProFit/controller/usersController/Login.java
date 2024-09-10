package com.ProFit.controller.usersController;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.dao.usersDao.HUserDao;
import com.ProFit.hibernateutil.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public Login() {
        super();

    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_email = request.getParameter("user_email");
	    String user_password = request.getParameter("user_password");
	    
	    SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
	    
		HUserDao userDao = new HUserDao(session);
		
	    String user_pictureURL = userDao.getUserPictureByEmail(user_email);
	   
	    try {
			String user_passwordHash = toHexString(getSHA(user_password));
			System.out.println(user_passwordHash);
			if(userDao.validate(user_email , user_passwordHash)){
				System.out.println("登入成功");
				request.getSession().setAttribute("user_email", user_email);
				request.getSession().setAttribute("user_pictureURL", user_pictureURL);

				request.getRequestDispatcher("GetAlluser").forward(request, response);
			}
			else{
				System.out.println("登入失敗");
				request.getRequestDispatcher("/usersVIEW/Login.jsp").forward(request, response);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	 public static byte[] getSHA(String pwd) throws NoSuchAlgorithmException
	    {
	        // Static getInstance method is called with hashing SHA
	        MessageDigest md = MessageDigest.getInstance("SHA-256");

	        // digest() method called
	        // to calculate message digest of an input
	        // and return array of byte
	        return md.digest(pwd.getBytes(StandardCharsets.UTF_8));
	    }

	 public static String toHexString(byte[] hash)
	    {
	        // Convert byte array into signum representation
	        BigInteger number = new BigInteger(1, hash);

	        // Convert message digest into hex value
	        StringBuilder hexString = new StringBuilder(number.toString(16));

	        // Pad with leading zeros
	        while (hexString.length() < 64)
	        {
	            hexString.insert(0, '0');
	        }

	        return hexString.toString();
	    }


}
