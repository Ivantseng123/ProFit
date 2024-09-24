package com.ProFit.service.userService;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.usersBean.Pwd_reset_tokens;
import com.ProFit.dao.usersDao.IHPwdResetTokensDao;

@Service
@Transactional
public class PwdresetService implements IPwdresetService {
	
	@Autowired
	private IHPwdResetTokensDao pwdResetTokensDao;
	
	//新增token
	@Override
	public Pwd_reset_tokens saveTokensInfo(Pwd_reset_tokens token) {
		
		return pwdResetTokensDao.saveTokensInfo(token);
	}
	
	// 刪除token By ID
	@Override
	public boolean deleteTokensInfo(int token_id) {
		return pwdResetTokensDao.deleteTokensInfo(token_id);
	}

	//查詢全部tokens
	@Override
	public List<Pwd_reset_tokens> getAllTokensInfo() {
		
		return pwdResetTokensDao.getAllTokensInfo();
	}
	
	//產生token
	@Override
	public String generateToken() throws NoSuchAlgorithmException {
		return pwdResetTokensDao.generateToken();	
	}
}
