package com.ProFit.bean.usersBean;

import java.io.Serializable;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name="employer_profile")
@Component
@Scope("prototype")
public class Employer_profile implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @Column(name="employer_profile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employerProfileId;
		
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id",insertable = false,updatable = false)
	private Users user;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="company_address")
	private String companyAddress;
	
	@Column(name="company_category")
	private String companyCategory;
	
	@Column(name="company_phoneNumber")
	private String companyPhoneNumber;
	
	@Column(name="company_taxID")
	private String companyTaxID;
	
	@Column(name="company_numberOfemployee")
	private String companyNumberOfemployee;
	
	@Column(name="company_capital")
	private String companyCaptital;
	
	@Column(name="company_description")
	private String companyDescription;
	
	@Column(name="company_photoURL")
	private String companyPhotoURL;


	public Employer_profile(Integer employer_profile_id, Integer user_id, String company_name, String company_address,
			String company_category, String company_phoneNumber, String company_taxID, String company_numberOfemployee,
			String company_captital, String company_description, String company_photoURL) {
		super();
		this.employerProfileId = employer_profile_id;
		this.userId = user_id;
		this.companyName = company_name;
		this.companyAddress = company_address;
		this.companyCategory = company_category;
		this.companyPhoneNumber = company_phoneNumber;
		this.companyTaxID = company_taxID;
		this.companyNumberOfemployee = company_numberOfemployee;
		this.companyCaptital = company_captital;
		this.companyDescription = company_description;
		this.companyPhotoURL = company_photoURL;
	}

	

	public Employer_profile(Integer user_id, String company_name, String company_address,
			String company_category, String company_phoneNumber, String company_taxID) {
		super();
		this.userId = user_id;
		this.companyName = company_name;
		this.companyAddress = company_address;
		this.companyCategory = company_category;
		this.companyPhoneNumber = company_phoneNumber;
		this.companyTaxID = company_taxID;
	}

	public Employer_profile() {}



	public Integer getEmployerProfileId() {
		return employerProfileId;
	}



	public void setEmployerProfileId(Integer employerProfileId) {
		this.employerProfileId = employerProfileId;
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



	public String getCompanyNumberOfemployee() {
		return companyNumberOfemployee;
	}



	public void setCompanyNumberOfemployee(String companyNumberOfemployee) {
		this.companyNumberOfemployee = companyNumberOfemployee;
	}



	public String getCompanyCaptital() {
		return companyCaptital;
	}



	public void setCompanyCaptital(String companyCaptital) {
		this.companyCaptital = companyCaptital;
	}



	public String getCompanyDescription() {
		return companyDescription;
	}



	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}



	public String getCompanyPhotoURL() {
		return companyPhotoURL;
	}


	public void setCompanyPhotoURL(String companyPhotoURL) {
		this.companyPhotoURL = companyPhotoURL;
	}

	
}
