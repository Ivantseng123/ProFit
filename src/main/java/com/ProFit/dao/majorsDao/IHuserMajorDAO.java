package com.ProFit.dao.majorsDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.majorsBean.UserMajorPK;

public interface IHuserMajorDAO {

	// 插入 UserMajor
	UserMajorBean insertUserMajor(UserMajorBean userMajor);

	// 删除 UserMajor(by userId & majorId)
	boolean deleteUserMajor(int userId, int majorId);

	// 查詢所有用戶
	Map<Integer, String> getAllUsers();

	// 查詢所有專業
	Map<Integer, String> getAllMajors() throws SQLException;

	// 查找特定user的所有 Major
	List<UserMajorBean> findMajorsByUserId(int userId);

	// 查找特定 Major 的所有 User
	List<UserMajorBean> findUsersByMajorId(int majorId);

	// 查找所有 UserMajor
	List<UserMajorBean> findAllUserMajors();

	// 根據user、Major查找單一 UserMajor
	UserMajorBean findUserMajorByUserIdMajorId(UserMajorPK userMajorPK);

}