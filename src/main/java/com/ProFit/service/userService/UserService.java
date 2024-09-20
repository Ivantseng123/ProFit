package com.ProFit.service.userService;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.IHUserDao;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private IHUserDao userDao;
	
 // 新增user
    @Override
	public Users saveUserInfo(Users user) {
    	return userDao.saveUserInfo(user);
    }

 	// 刪除user By ID
    @Override
	public boolean deleteUserInfo(int user_id) {
    	return userDao.deleteUserInfo(user_id);
    }

 	// 修改user密碼
    @Override
	public boolean updateUserPwd(String pwd, int user_id) {
    	return userDao.updateUserPwd(pwd, user_id);
    }

 	//修改user資料
    @Override
	public boolean updateUserInfo(Users user) {
    	return userDao.updateUserInfo(user);
    }

 	//給予user企業資格
    @Override
	public boolean updateUserIdentity(int user_id) {
    	return userDao.updateUserIdentity(user_id);
    }
	
    @Override
	public List<Users> getAllUserInfo(){
    	return userDao.getAllUserInfo();
    }
	

    @Override
	public boolean validate(String userEmail, String userPasswordHash) {
    	return userDao.validate(userEmail, userPasswordHash);
    }
	

    @Override
	public String getUserPictureByEmail(String userEmail) {
    	return userDao.getUserPictureByEmail(userEmail);
    }
	

    @Override
	public Users getUserInfoByID(Integer user_id) {
    	return userDao.getUserInfoByID(user_id);
    }
	

    @Override
	public Users getUserByEmail(String user_email) {
    	return userDao.getUserByEmail(user_email);
    }
    
    @Override
	public byte[] getSHA(String pwd) throws NoSuchAlgorithmException {
		return userDao.getSHA(pwd);
	}
    
    @Override
	public String toHexString(byte[] hash) {
    	return userDao.toHexString(hash);
    }
}
