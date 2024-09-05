package com.ProFit.bean.usersBean;

import java.io.Serializable;

public class Employer_profile implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer employer_profile_id;
	private Integer user_id;
	private String  user_email ;
	private String  company_name ;
	private String company_address;
	private String company_category;
	private String company_phoneNumber;
	private String company_taxID ;
	private String company_numberOfemployee;
	private String company_captital;
	private String company_description;
	private String company_photoURL;


	public Employer_profile(Integer employer_profile_id, Integer user_id, String company_name, String company_address,
			String company_category, String company_phoneNumber, String company_taxID, String company_numberOfemployee,
			String company_captital, String company_description, String company_photoURL) {
		super();
		this.employer_profile_id = employer_profile_id;
		this.user_id = user_id;
		this.company_name = company_name;
		this.company_address = company_address;
		this.company_category = company_category;
		this.company_phoneNumber = company_phoneNumber;
		this.company_taxID = company_taxID;
		this.company_numberOfemployee = company_numberOfemployee;
		this.company_captital = company_captital;
		this.company_description = company_description;
		this.company_photoURL = company_photoURL;
	}

	public Employer_profile(Integer employer_profile_id, Integer user_id, String user_email,String company_name, String company_address,String company_category, String company_phoneNumber, String company_taxID, String company_numberOfemployee,
			String company_captital, String company_description, String company_photoURL) {
		super();
		this.employer_profile_id = employer_profile_id;
		this.user_id = user_id;
		this.user_email = user_email;
		this.company_name = company_name;
		this.company_address = company_address;
		this.company_category = company_category;
		this.company_phoneNumber = company_phoneNumber;
		this.company_taxID = company_taxID;
		this.company_numberOfemployee = company_numberOfemployee;
		this.company_captital = company_captital;
		this.company_description = company_description;
		this.company_photoURL = company_photoURL;
	}

	public Employer_profile(Integer user_id, String company_name, String company_address,
			String company_category, String company_phoneNumber, String company_taxID) {
		super();
		this.user_id = user_id;
		this.company_name = company_name;
		this.company_address = company_address;
		this.company_category = company_category;
		this.company_phoneNumber = company_phoneNumber;
		this.company_taxID = company_taxID;
	}

	public Employer_profile() {}

	public Integer getEmployer_profile_id() {
		return employer_profile_id;
	}
	public void setEmployer_profile_id(Integer employer_profile_id) {
		this.employer_profile_id = employer_profile_id;
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
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_category() {
		return company_category;
	}
	public void setCompany_category(String company_category) {
		this.company_category = company_category;
	}
	public String getCompany_phoneNumber() {
		return company_phoneNumber;
	}
	public void setCompany_phoneNumber(String company_phoneNumber) {
		this.company_phoneNumber = company_phoneNumber;
	}
	public String getCompany_taxID() {
		return company_taxID;
	}
	public void setCompany_taxID(String company_taxID) {
		this.company_taxID = company_taxID;
	}
	public String getCompany_numberOfemployee() {
		return company_numberOfemployee;
	}
	public void setCompany_numberOfemployee(String company_numberOfemployee) {
		this.company_numberOfemployee = company_numberOfemployee;
	}
	public String getCompany_captital() {
		return company_captital;
	}
	public void setCompany_captital(String company_captital) {
		this.company_captital = company_captital;
	}
	public String getCompany_description() {
		return company_description;
	}
	public void setCompany_description(String company_description) {
		this.company_description = company_description;
	}
	public String getCompany_photoURL() {
		return company_photoURL;
	}
	public void setCompany_photoURL(String company_photoURL) {
		this.company_photoURL = company_photoURL;
	}
}
