package com.ProFit.bean.usersBean;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Users implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id @Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	
	private String user_name;
	private String user_email ;
	private String user_passwordHash;
	private String user_phoneNumber;
	private String user_city;
	private Integer user_identity ;
	private String user_register_time;

	private String user_pictureURL;
	private Integer user_balance;
	private String freelancer_location_prefer;
	private String freelancer_exprience ;
	private String freelancer_identity;
	private Integer freelancer_profile_status;
	private String  freelancer_disc;

	public Users() {
	}


	public Users(Integer user_id, String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time, String user_pictureURL,
			Integer user_balance, String freelancer_location_prefer, String freelancer_exprience,
			String freelancer_identity, Integer freelancer_profile_status, String freelancer_disc) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_passwordHash = user_passwordHash;
		this.user_phoneNumber = user_phoneNumber;
		this.user_city = user_city;
		this.user_identity = user_identity;
		this.user_register_time = user_register_time;
		this.user_pictureURL = user_pictureURL;
		this.user_balance = user_balance;
		this.freelancer_location_prefer = freelancer_location_prefer;
		this.freelancer_exprience = freelancer_exprience;
		this.freelancer_identity = freelancer_identity;
		this.freelancer_profile_status = freelancer_profile_status;
		this.freelancer_disc = freelancer_disc;
	}


	public Users(Integer user_id, String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time,
			Integer user_balance, String freelancer_location_prefer, String freelancer_exprience,
			String freelancer_identity, Integer freelancer_profile_status, String freelancer_disc) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_passwordHash = user_passwordHash;
		this.user_phoneNumber = user_phoneNumber;
		this.user_city = user_city;
		this.user_identity = user_identity;
		this.user_register_time = user_register_time;
		this.user_balance = user_balance;
		this.freelancer_location_prefer = freelancer_location_prefer;
		this.freelancer_exprience = freelancer_exprience;
		this.freelancer_identity = freelancer_identity;
		this.freelancer_profile_status = freelancer_profile_status;
		this.freelancer_disc = freelancer_disc;
	}

	public Users(String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time, Integer freelancer_profile_status) {
		super();
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_passwordHash = user_passwordHash;
		this.user_phoneNumber = user_phoneNumber;
		this.user_city = user_city;
		this.user_register_time = user_register_time;
		this.user_identity = user_identity;
		this.freelancer_profile_status = freelancer_profile_status;
	}

	public Users(String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, Integer freelancer_profile_status) {
		super();
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_passwordHash = user_passwordHash;
		this.user_phoneNumber = user_phoneNumber;
		this.user_city = user_city;
		this.user_identity = user_identity;
		this.freelancer_profile_status = freelancer_profile_status;
	}


	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_passwordHash() {
		return user_passwordHash;
	}
	public void setUser_passwordHash(String user_passwordHash) {
		this.user_passwordHash = user_passwordHash;
	}
	public String getUser_phoneNumber() {
		return user_phoneNumber;
	}
	public void setUser_phoneNumber(String user_phoneNumber) {
		this.user_phoneNumber = user_phoneNumber;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}

	public String getUser_register_time() {
		return user_register_time;
	}


	public void setUser_register_time(String user_register_time) {
		this.user_register_time = user_register_time;
	}

	public Integer getUser_identity() {
		return user_identity;
	}
	public void setUser_identity(Integer user_identity) {
		this.user_identity = user_identity;
	}
	public String getUser_pictureURL() {
		return user_pictureURL;
	}
	public void setUser_pictureURL(String user_pictureURL) {
		this.user_pictureURL = user_pictureURL;
	}
	public Integer getUser_balance() {
		return user_balance;
	}
	public void setUser_balance(Integer user_balance) {
		this.user_balance = user_balance;
	}
	public String getFreelancer_location_prefer() {
		return freelancer_location_prefer;
	}
	public void setFreelancer_location_prefer(String freelancer_location_prefer) {
		this.freelancer_location_prefer = freelancer_location_prefer;
	}
	public String getFreelancer_exprience() {
		return freelancer_exprience;
	}
	public void setFreelancer_exprience(String freelancer_exprience) {
		this.freelancer_exprience = freelancer_exprience;
	}
	public String getFreelancer_identity() {
		return freelancer_identity;
	}
	public void setFreelancer_identity(String freelancer_identity) {
		this.freelancer_identity = freelancer_identity;
	}
	public Integer getFreelancer_profile_status() {
		return freelancer_profile_status;
	}
	public void setFreelancer_profile_status(Integer freelancer_profile_status) {
		this.freelancer_profile_status = freelancer_profile_status;
	}
	public String getFreelancer_disc() {
		return freelancer_disc;
	}
	public void setFreelancer_disc(String freelancer_discy) {
		this.freelancer_disc = freelancer_discy;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", user_name=" + user_name + ", user_email=" + user_email
				+ ", user_passwordHash=" + user_passwordHash + ", user_phoneNumber=" + user_phoneNumber + ", user_city="
				+ user_city + ", user_identity=" + user_identity + ", user_register_time=" + user_register_time
				+ ", user_pictureURL=" + user_pictureURL + ", user_balance=" + user_balance
				+ ", freelancer_location_prefer=" + freelancer_location_prefer + ", freelancer_exprience="
				+ freelancer_exprience + ", freelancer_identity=" + freelancer_identity + ", freelancer_profile_status="
				+ freelancer_profile_status + ", freelancer_disc=" + freelancer_disc + "]";
	}


}
