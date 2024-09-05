package com.ProFit.bean.usersBean;

import java.io.Serializable;

public class Pwd_reset_tokens implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer token_id;
	private Integer user_id;
	private String user_email;
	private String user_tokenHash;
	private String expiration_time;



	public Pwd_reset_tokens(Integer token_id, Integer user_id, String user_email, String user_tokenHash,
			String expiration_time) {
		super();
		this.token_id = token_id;
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_tokenHash = user_tokenHash;
		this.expiration_time = expiration_time;
	}

	public Pwd_reset_tokens(Integer token_id, Integer user_id, String user_tokenHash,
			String expiration_time) {
		super();
		this.token_id = token_id;
		this.user_id = user_id;
		this.user_tokenHash = user_tokenHash;
		this.expiration_time = expiration_time;
	}


	public Pwd_reset_tokens(Integer user_id, String user_tokenHash
			) {
		super();
		this.user_id = user_id;
		this.user_tokenHash = user_tokenHash;
	}

	public Pwd_reset_tokens() {

	}

	public Integer getToken_id() {
		return token_id;
	}
	public void setToken_id(Integer token_id) {
		this.token_id = token_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_tokenHash() {
		return user_tokenHash;
	}
	public void setUser_tokenHash(String user_tokenHash) {
		this.user_tokenHash = user_tokenHash;
	}
	public String getExpiration_time() {
		return expiration_time;
	}
	public void setExpiration_time(String expiration_time) {
		this.expiration_time = expiration_time;
	}

}
