package com.ProFit.dao.usersDao;

import java.util.List;

import com.ProFit.bean.usersBean.Users;

public interface IHUserDao {

	// 新增user
	Users saveUserInfo(Users user);

	// 刪除user By ID
	boolean deleteUserInfo(int user_id);

	// 修改user密碼
	boolean updateUserPwd(String pwd, int user_id);

	//修改user資料
	boolean updateUserInfo(Users user);

	//給予user企業資格
	boolean updateUserIdentity(int user_id);

	List<Users> getAllUserInfo();

	boolean validate(String userEmail, String userPasswordHash);

	String getUserPictureByEmail(String userEmail);

	Users getUserInfoByID(Integer user_id);

	Users getUserByEmail(String user_email);

}