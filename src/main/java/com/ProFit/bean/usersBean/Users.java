package com.ProFit.bean.usersBean;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import com.ProFit.bean.majorsBean.MajorBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "users")
@Component
@Scope("prototype")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_passwordHash")
	private String userPasswordHash;

	@Column(name = "user_phoneNumber")
	private String userPhoneNumber;

	@Column(name = "user_city")
	private String userCity;

	@Column(name = "user_identity")
	private Integer userIdentity;

	@Column(name = "user_register_time", updatable = false, insertable = false)
	private String userRegisterTime;

	@Column(name = "user_pictureURL")
	private String userPictureURL;

	@Column(name = "user_balance")
	private Integer userBalance;

	@Column(name = "freelancer_location_prefer")
	private String freelancerLocationPrefer;

	@Column(name = "freelancer_exprience")
	private String freelancerExprience;

	@Column(name = "freelancer_identity")
	private String freelancerIdentity;

	@Column(name = "freelancer_profile_status")
	private Integer freelancerProfileStatus;

	@Column(name = "freelancer_description")
	private String freelancerDisc;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Pwd_reset_tokens> pwd_reset_tokens = new LinkedList<Pwd_reset_tokens>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Employer_application> employerApplications = new LinkedList<Employer_application>();
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "user")
	private Employer_profile employer_profile;

	 //多對多關係，中介表user_major
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_major", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
	@JoinColumn(name = "major_id") })
	private Set<MajorBean> majors = new LinkedHashSet<MajorBean>(0);

	public Users() {
	}

	public Users(Integer user_id, String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time,
			String user_pictureURL, Integer user_balance, String freelancer_location_prefer,
			String freelancer_exprience, String freelancer_identity, Integer freelancer_profile_status,
			String freelancer_disc) {
		super();
		this.userId = user_id;
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
		this.userId = user_id;
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

	public Users(String user_name, String user_email, String user_passwordHash, String user_phoneNumber,
			String user_city, Integer user_identity, String user_register_time, Integer freelancer_profile_status) {
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

	public Users(String user_name, String user_email, String user_passwordHash, String user_phoneNumber,
			String user_city, Integer user_identity, Integer user_balance, Integer freelancer_profile_status) {
		super();
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.userBalance = user_balance;
		this.freelancerProfileStatus = freelancer_profile_status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPasswordHash() {
		return userPasswordHash;
	}

	public void setUserPasswordHash(String userPasswordHash) {
		this.userPasswordHash = userPasswordHash;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public Integer getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(Integer userIdentity) {
		this.userIdentity = userIdentity;
	}

	public String getUserRegisterTime() {
		return userRegisterTime;
	}

	public void setUserRegisterTime(String userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}

	public String getUserPictureURL() {
		return userPictureURL;
	}

	public void setUserPictureURL(String userPictureURL) {
		this.userPictureURL = userPictureURL;
	}

	public Integer getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(Integer userBalance) {
		this.userBalance = userBalance;
	}

	public String getFreelancerLocationPrefer() {
		return freelancerLocationPrefer;
	}

	public void setFreelancerLocationPrefer(String freelancerLocationPrefer) {
		this.freelancerLocationPrefer = freelancerLocationPrefer;
	}

	public String getFreelancerExprience() {
		return freelancerExprience;
	}

	public void setFreelancerExprience(String freelancerExprience) {
		this.freelancerExprience = freelancerExprience;
	}

	public String getFreelancerIdentity() {
		return freelancerIdentity;
	}

	public void setFreelancerIdentity(String freelancerIdentity) {
		this.freelancerIdentity = freelancerIdentity;
	}

	public Integer getFreelancerProfileStatus() {
		return freelancerProfileStatus;
	}

	public void setFreelancerProfileStatus(Integer freelancerProfileStatus) {
		this.freelancerProfileStatus = freelancerProfileStatus;
	}

	public String getFreelancerDisc() {
		return freelancerDisc;
	}

	public void setFreelancerDisc(String freelancerDisc) {
		this.freelancerDisc = freelancerDisc;
	}

	public List<Employer_application> getEmployerApplications() {
		return employerApplications;
	}
	
	public void setEmployerApplications(List<Employer_application> employerApplications) {
		this.employerApplications = employerApplications;
	}
	
	
	public Set<MajorBean> getMajors() {
		return majors;
	}

	public void setMajors(Set<MajorBean> majors) {
		this.majors = majors;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + userId + ", user_name=" + userName + ", user_email=" + userEmail
				+ ", user_passwordHash=" + userPasswordHash + ", user_phoneNumber=" + userPhoneNumber + ", user_city="
				+ userCity + ", user_identity=" + userIdentity + ", user_register_time=" + userRegisterTime
				+ ", user_pictureURL=" + userPictureURL + ", user_balance=" + userBalance
				+ ", freelancer_location_prefer=" + freelancerLocationPrefer + ", freelancer_exprience="
				+ freelancerExprience + ", freelancer_identity=" + freelancerIdentity + ", freelancer_profile_status="
				+ freelancerProfileStatus + ", freelancer_disc=" + freelancerDisc + "]";
	}


}
