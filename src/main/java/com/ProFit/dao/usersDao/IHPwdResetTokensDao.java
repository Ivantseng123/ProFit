package com.ProFit.dao.usersDao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.ProFit.bean.usersBean.Pwd_reset_tokens;

public interface IHPwdResetTokensDao {

	Pwd_reset_tokens saveTokensInfo(Pwd_reset_tokens token);

	boolean deleteTokensInfo(int token_id);

	List<Pwd_reset_tokens> getAllTokensInfo();

	String generateToken() throws NoSuchAlgorithmException;

}