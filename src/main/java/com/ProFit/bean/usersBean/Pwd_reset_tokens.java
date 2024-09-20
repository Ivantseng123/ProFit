package com.ProFit.bean.usersBean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="password_reset_tokens")
@Component
public class Pwd_reset_tokens implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @Column(name="token_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tokenId;
	
	@Column(name="user_id")
	private Integer userId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id",insertable = false,updatable = false)
	private Users user;
	
	@Column(name="user_tokenHash")
	private String userTokenHash;
	
	@Column(name="expiration_time", updatable = false, insertable = false)
	private String expirationTime;



	public Pwd_reset_tokens(Integer token_id, Integer user_id,String user_tokenHash,
			String expiration_time) {
		super();
		this.tokenId = token_id;
		this.userId = user_id;	
		this.userTokenHash = user_tokenHash;
		this.expirationTime = expiration_time;
	}

	public Pwd_reset_tokens(Integer user_id, String user_tokenHash
			) {
		super();
		this.userId = user_id;
		this.userTokenHash = user_tokenHash;
	}

	public Pwd_reset_tokens() {

	}

	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getUserTokenHash() {
		return userTokenHash;
	}

	public void setUserTokenHash(String userTokenHash) {
		this.userTokenHash = userTokenHash;
	}

	public String getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	

}
