package com.ProFit.bean.usersBean;

import java.io.Serializable;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name="users")
public class Users implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id @Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_email")
	private String userEmail ;
	
	@Column(name="user_passwordHash")
	private String userPasswordHash;
	
	@Column(name="user_phoneNumber")
	private String userPhoneNumber;
	
	@Column(name="user_city")
	private String userCity;
	
	@Column(name="user_identity")
	private Integer userIdentity;
	
	@Column(name="user_register_time",updatable = false, insertable = false)
	private String userRegisterTime;
	
	@Column(name="user_pictureURL")
	private String userPictureURL;
	
	@Column(name="user_balance")
	private Integer userBalance;
	
	@Column(name="freelancer_location_prefer")
	private String freelancerLocationPrefer;
	
	@Column(name="freelancer_exprience")
	private String freelancerExprience ;
	
	@Column(name="freelancer_identity")
	private String freelancerIdentity;
	
	@Column(name="freelancer_profile_status")
	private Integer freelancerProfileStatus;
	
	@Column(name="freelancer_disc")
	private String freelancerDisc;

	public Users() {
	}


	public Users(Integer user_id, String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time, String user_pictureURL,
			Integer user_balance, String freelancer_location_prefer, String freelancer_exprience,
			String freelancer_identity, Integer freelancer_profile_status, String freelancer_disc) {
		super();
		this.user_id = user_id;
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.userRegisterTime = user_register_time;
		this.userPictureURL = user_pictureURL;
		this.userBalance = user_balance;
		this.freelancerLocationPrefer = freelancer_location_prefer;
		this.freelancerExprience = freelancer_exprience;
		this.freelancerIdentity = freelancer_identity;
		this.freelancerProfileStatus = freelancer_profile_status;
		this.freelancerDisc = freelancer_disc;
	}


	public Users(Integer user_id, String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time,
			Integer user_balance, String freelancer_location_prefer, String freelancer_exprience,
			String freelancer_identity, Integer freelancer_profile_status, String freelancer_disc) {
		super();
		this.user_id = user_id;
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.userRegisterTime = user_register_time;
		this.userBalance = user_balance;
		this.freelancerLocationPrefer = freelancer_location_prefer;
		this.freelancerExprience = freelancer_exprience;
		this.freelancerIdentity = freelancer_identity;
		this.freelancerProfileStatus = freelancer_profile_status;
		this.freelancerDisc = freelancer_disc;
	}

	public Users(String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time, Integer freelancer_profile_status) {
		super();
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.userRegisterTime = user_register_time;
		this.freelancerProfileStatus = freelancer_profile_status;
	}

	public Users(String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, Integer freelancer_profile_status) {
		super();
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.freelancerProfileStatus = freelancer_profile_status;
	}


	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return userName;
	}
	public void setUser_name(String user_name) {
		this.userName = user_name;
	}
	public String getUser_email() {
		return userEmail;
	}
	public void setUser_email(String user_email) {
		this.userEmail = user_email;
	}
	public String getUser_passwordHash() {
		return userPasswordHash;
	}
	public void setUser_passwordHash(String user_passwordHash) {
		this.userPasswordHash = user_passwordHash;
	}
	public String getUser_phoneNumber() {
		return userPhoneNumber;
	}
	public void setUser_phoneNumber(String user_phoneNumber) {
		this.userPhoneNumber = user_phoneNumber;
	}
	public String getUser_city() {
		return userCity;
	}
	public void setUser_city(String user_city) {
		this.userCity = user_city;
	}

	public String getUser_register_time() {
		return userRegisterTime;
	}


	public void setUser_register_time(String user_register_time) {
		this.userRegisterTime = user_register_time;
	}

	public Integer getUser_identity() {
		return userIdentity;
	}
	public void setUser_identity(Integer user_identity) {
		this.userIdentity = user_identity;
	}
	public String getUser_pictureURL() {
		return userPictureURL;
	}
	public void setUser_pictureURL(String user_pictureURL) {
		this.userPictureURL = user_pictureURL;
	}
	public Integer getUser_balance() {
		return userBalance;
	}
	public void setUser_balance(Integer user_balance) {
		this.userBalance = user_balance;
	}
	public String getFreelancer_location_prefer() {
		return freelancerLocationPrefer;
	}
	public void setFreelancer_location_prefer(String freelancer_location_prefer) {
		this.freelancerLocationPrefer = freelancer_location_prefer;
	}
	public String getFreelancer_exprience() {
		return freelancerExprience;
	}
	public void setFreelancer_exprience(String freelancer_exprience) {
		this.freelancerExprience = freelancer_exprience;
	}
	public String getFreelancer_identity() {
		return freelancerIdentity;
	}
	public void setFreelancer_identity(String freelancer_identity) {
		this.freelancerIdentity = freelancer_identity;
	}
	public Integer getFreelancer_profile_status() {
		return freelancerProfileStatus;	
	}
	public void setFreelancer_profile_status(Integer freelancer_profile_status) {
		this.freelancerProfileStatus = freelancer_profile_status;
	}
	public String getFreelancer_disc() {
		return freelancerDisc;
	}
	public void setFreelancer_disc(String freelancer_discy) {
		this.freelancerDisc = freelancer_discy;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", user_name=" + userName + ", user_email=" + userEmail
				+ ", user_passwordHash=" + userPasswordHash + ", user_phoneNumber=" + userPhoneNumber + ", user_city="
				+ userCity + ", user_identity=" + userIdentity + ", user_register_time=" + userRegisterTime
				+ ", user_pictureURL=" + userPictureURL + ", user_balance=" + userBalance
				+ ", freelancer_location_prefer=" + freelancerLocationPrefer + ", freelancer_exprience="
				+ freelancerExprience + ", freelancer_identity=" + freelancerIdentity + ", freelancer_profile_status="
				+ freelancerProfileStatus + ", freelancer_disc=" + freelancerDisc + "]";
	}


}
