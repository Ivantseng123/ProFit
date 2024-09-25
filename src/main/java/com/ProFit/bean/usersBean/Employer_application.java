package com.ProFit.bean.usersBean;

import java.io.Serializable;
import org.hibernate.annotations.DynamicUpdate;
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
@DynamicUpdate
@Table(name="employer_application")
public class Employer_application implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="employer_application_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employerApplicationId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id",insertable = false,updatable = false)
	private Users user;
	
	@Column(name="user_id")
	private Integer userId;
	
	
	@Column(name="company_name")
	private String  companyName ;
	
	@Column(name="company_address")
	private String companyAddress;
	
	@Column(name="company_category")
	private String companyCategory;
	
	@Column(name="company_phoneNumber")
	private String companyPhoneNumber;
	
	@Column(name="company_taxID")
	private String companyTaxID;
	
	@Column(name="company_taxID_docURL")
	private String companyTaxIdDocURL;
	
	@Column(name="user_national_id")
	private String userNationalId;
	
	@Column(name="idCard_pictureURL_1")
	private String idCardPictureURL1;
	
	@Column(name="idCard_pictureURL_2")
	private String idCardPictureURL2;
	
	@Column(name="application_check")
	private Integer applicationCheck;
	
	public Employer_application() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employer_application(Integer employer_application_id, Integer user_id,String  user_email, String company_name,
			String company_address, String company_category, String company_phoneNumber, String company_taxID,
			String company_taxID_docURL, String user_national_id, String idCard_pictureURL_1,
			String idCard_pictureURL_2, Integer application_check) {
		super();
		this.employerApplicationId = employer_application_id;
		this.userId = user_id;
		this.companyName = company_name;
		this.companyAddress = company_address;
		this.companyCategory = company_category;
		this.companyPhoneNumber = company_phoneNumber;
		this.companyTaxID = company_taxID;
		this.companyTaxIdDocURL = company_taxID_docURL;
		this.userNationalId = user_national_id;
		this.idCardPictureURL1 = idCard_pictureURL_1;
		this.idCardPictureURL2 = idCard_pictureURL_2;
		this.applicationCheck = application_check;
	}


	public Employer_application(Integer user_id, String company_name,
			String company_address, String company_category, String company_phoneNumber, String company_taxID,
			String company_taxID_docURL, String user_national_id, String idCard_pictureURL_1,
			String idCard_pictureURL_2, Integer application_check) {
		super();
		this.userId = user_id;
		this.companyName = company_name;
		this.companyAddress = company_address;
		this.companyCategory = company_category;
		this.companyPhoneNumber = company_phoneNumber;
		this.companyTaxID = company_taxID;
		this.companyTaxIdDocURL = company_taxID_docURL;
		this.userNationalId = user_national_id;
		this.idCardPictureURL1 = idCard_pictureURL_1;
		this.idCardPictureURL2 = idCard_pictureURL_2;
		this.applicationCheck = application_check;
	}

	public Employer_application(Integer employer_application_id,Integer user_id, String company_name,
			String company_address, String company_category, String company_phoneNumber, String company_taxID,
			String company_taxID_docURL, String user_national_id, String idCard_pictureURL_1,
			String idCard_pictureURL_2) {
		super();
		this.employerApplicationId = employer_application_id;
		this.userId = user_id;
		this.companyName = company_name;
		this.companyAddress = company_address;
		this.companyCategory = company_category;
		this.companyPhoneNumber = company_phoneNumber;
		this.companyTaxID = company_taxID;
		this.companyTaxIdDocURL = company_taxID_docURL;
		this.userNationalId = user_national_id;
		this.idCardPictureURL1 = idCard_pictureURL_1;
		this.idCardPictureURL2 = idCard_pictureURL_2;
	}

	

	public Integer getEmployerApplicationId() {
		return employerApplicationId;
	}


	public void setEmployerApplicationId(Integer employerApplicationId) {
		this.employerApplicationId = employerApplicationId;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanyAddress() {
		return companyAddress;
	}


	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}


	public String getCompanyCategory() {
		return companyCategory;
	}


	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}


	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}


	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}


	public String getCompanyTaxID() {
		return companyTaxID;
	}


	public void setCompanyTaxID(String companyTaxID) {
		this.companyTaxID = companyTaxID;
	}


	public String getCompanyTaxIdDocURL() {
		return companyTaxIdDocURL;
	}


	public void setCompanyTaxIdDocURL(String companyTaxIdDocURL) {
		this.companyTaxIdDocURL = companyTaxIdDocURL;
	}


	public String getUserNationalId() {
		return userNationalId;
	}


	public void setUserNationalId(String userNationalId) {
		this.userNationalId = userNationalId;
	}


	public String getIdCardPictureURL1() {
		return idCardPictureURL1;
	}


	public void setIdCardPictureURL1(String idCardPictureURL1) {
		this.idCardPictureURL1 = idCardPictureURL1;
	}


	public String getIdCardPictureURL2() {
		return idCardPictureURL2;
	}


	public void setIdCardPictureURL2(String idCardPictureURL2) {
		this.idCardPictureURL2 = idCardPictureURL2;
	}


	public Integer getApplicationCheck() {
		return applicationCheck;
	}


	public void setApplicationCheck(Integer applicationCheck) {
		this.applicationCheck = applicationCheck;
	}


	@Override
	public String toString() {
		return "Employer_application [employer_application_id=" + employerApplicationId + ", user_id=" + userId
				+ ", user_email=" + ", company_name=" + companyName + ", company_address="
				+ companyAddress + ", company_category=" + companyCategory + ", company_phoneNumber="
				+ companyPhoneNumber + ", company_taxID=" + companyTaxID + ", company_taxID_docURL="
				+ companyTaxIdDocURL + ", user_national_id=" + userNationalId + ", idCard_pictureURL_1="
				+ idCardPictureURL1 + ", idCard_pictureURL_2=" + idCardPictureURL2 + ", application_check="
				+ applicationCheck + "]";
	}


}
