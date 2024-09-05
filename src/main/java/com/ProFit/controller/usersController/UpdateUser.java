package com.ProFit.controller.usersController;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UpdateUser")
@MultipartConfig
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UpdateUser() {
        super();

    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		Integer user_id = Integer.valueOf(request.getParameter("user_id"));
		System.out.println(action);
		System.out.println(user_id);

		switch (action) {
		case "nopwd": {

			UpdateNopwd(request, response);
			break;
		}
		case "updatepwd":
			try {
				Updatepwd(user_id,request, response);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}


	}


private void Updatepwd(Integer user_id, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, ServletException, IOException {

		String password = request.getParameter("user_password");
		String user_passwordHash = toHexString(getSHA(password));

		UserDao userDao = new UserDao();

		userDao.updateUserPwd(user_passwordHash, user_id);
		request.setAttribute("user_id", user_id);
		request.getRequestDispatcher("/GetUpdateUser").forward(request, response);

	}


protected void UpdateNopwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user_pictureURL = request.getParameter("user_pictureURL");
		System.out.println(user_pictureURL);

		Integer user_id = Integer.valueOf(request.getParameter("user_id"));
		String user_name = request.getParameter("user_name");

		String user_email = request.getParameter("user_email");
		String user_password = request.getParameter("user_passwordHash");
		String user_phoneNumber = request.getParameter("user_phoneNumber");

		String user_city = request.getParameter("user_city");
		Integer user_identity = Integer.valueOf(request.getParameter("user_identity"));

		Integer user_balance = Integer.valueOf(request.getParameter("user_balance"));

		String freelancer_location_prefer = request.getParameter("freelancer_location_prefer");
		String freelancer_exprience = request.getParameter("freelancer_exprience");

		String freelancer_identity = request.getParameter("freelancer_identity");
		Integer freelancer_profile_status = Integer.valueOf(request.getParameter("freelancer_profile_status"));

		String freelancer_disc = request.getParameter("freelancer_disc");
		String user_register_time = request.getParameter("user_register_time");

		String userFolderPath = "C:\\ProFitServlet\\workspace\\ProFit\\src\\main\\webapp\\usersVIEW\\userupload\\" + user_id; // 本機路徑
        File userFolder = new File(userFolderPath);


        if (!userFolder.exists()) {
            userFolder.mkdirs();
        }

        Part filePart = request.getPart("profileImage");
        String uploadPathtoDB;
        if(filePart != null && filePart.getSize() > 0) {
        	filePart = request.getPart("profileImage");
        	String fileName = filePart.getSubmittedFileName();
        	String uploadPath = userFolderPath + "/" + fileName;
        	uploadPathtoDB =  user_id + "/" + fileName;
        	filePart.write(uploadPath);
        }else {
        	uploadPathtoDB = user_pictureURL;
        }

        UserDao userDao = new UserDao();
		try {

			Users user = new Users(user_id,user_name,user_email,user_password,user_phoneNumber,user_city,user_identity,user_register_time,uploadPathtoDB,user_balance,freelancer_location_prefer,freelancer_exprience,freelancer_identity,freelancer_profile_status,
					freelancer_disc);
			userDao.updateUserInfo(user);

			request.getRequestDispatcher("/GetAlluser").forward(request, response);
		} catch (Exception e) {
		}
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}


	 public static byte[] getSHA(String pwd) throws NoSuchAlgorithmException
	    {

	        MessageDigest md = MessageDigest.getInstance("SHA-256");


	        return md.digest(pwd.getBytes(StandardCharsets.UTF_8));
	    }

	 public static String toHexString(byte[] hash)
	    {
	        BigInteger number = new BigInteger(1, hash);

	        StringBuilder hexString = new StringBuilder(number.toString(16));

	        while (hexString.length() < 32)
	        {
	            hexString.insert(0, '0');
	        }

	        return hexString.toString();
	    }

}
