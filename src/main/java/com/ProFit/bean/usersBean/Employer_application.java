package com.ProFit.bean.usersBean;

import java.io.Serializable;

public class Employer_application implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer employer_application_id;
	private Integer user_id;
	private String  user_email ;
	private String  company_name ;
	private String company_address;
	private String company_category;
	private String company_phoneNumber;
	private String company_taxID ;
	private String company_taxID_docURL;
	private String user_national_id;
	private String idCard_pictureURL_1;
	private String idCard_pictureURL_2;
	private Integer application_check;

	public Employer_application(Integer employer_application_id, Integer user_id,String  user_email, String company_name,
			String company_address, String company_category, String company_phoneNumber, String company_taxID,
			String company_taxID_docURL, String user_national_id, String idCard_pictureURL_1,
			String idCard_pictureURL_2, Integer application_check) {
		super();
		this.employer_application_id = employer_application_id;
		this.user_id = user_id;
		this.user_email = user_email;
		this.company_name = company_name;
		this.company_address = company_address;
		this.company_category = company_category;
		this.company_phoneNumber = company_phoneNumber;
		this.company_taxID = company_taxID;
		this.company_taxID_docURL = company_taxID_docURL;
		this.user_national_id = user_national_id;
		this.idCard_pictureURL_1 = idCard_pictureURL_1;
		this.idCard_pictureURL_2 = idCard_pictureURL_2;
		this.application_check = application_check;
	}


	public Employer_application(Integer user_id, String company_name,
			String company_address, String company_category, String company_phoneNumber, String company_taxID,
			String company_taxID_docURL, String user_national_id, String idCard_pictureURL_1,
			String idCard_pictureURL_2, Integer application_check) {
		super();
		this.user_id = user_id;
		this.company_name = company_name;
		this.company_address = company_address;
		this.company_category = company_category;
		this.company_phoneNumber = company_phoneNumber;
		this.company_taxID = company_taxID;
		this.company_taxID_docURL = company_taxID_docURL;
		this.user_national_id = user_national_id;
		this.idCard_pictureURL_1 = idCard_pictureURL_1;
		this.idCard_pictureURL_2 = idCard_pictureURL_2;
		this.application_check = application_check;
	}

	public Employer_application(Integer employer_application_id, String company_name,
			String company_address, String company_category, String company_phoneNumber, String company_taxID,
			String company_taxID_docURL, String user_national_id, String idCard_pictureURL_1,
			String idCard_pictureURL_2) {
		super();
		this.employer_application_id = employer_application_id;
		this.company_name = company_name;
		this.company_address = company_address;
		this.company_category = company_category;
		this.company_phoneNumber = company_phoneNumber;
		this.company_taxID = company_taxID;
		this.company_taxID_docURL = company_taxID_docURL;
		this.user_national_id = user_national_id;
		this.idCard_pictureURL_1 = idCard_pictureURL_1;
		this.idCard_pictureURL_2 = idCard_pictureURL_2;
	}

	public Employer_application() {}

	public Integer getEmployer_application_id() {
		return employer_application_id;
	}

	public void setEmployer_application_id(Integer employer_application_id) {
		this.employer_application_id = employer_application_id;
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

	public String getCompany_taxID_docURL() {
		return company_taxID_docURL;
	}

	public void setCompany_taxID_docURL(String company_taxID_docURL) {
		this.company_taxID_docURL = company_taxID_docURL;
	}

	public String getUser_national_id() {
		return user_national_id;
	}

	public void setUser_national_id(String user_national_id) {
		this.user_national_id = user_national_id;
	}

	public String getIdCard_pictureURL_1() {
		return idCard_pictureURL_1;
	}

	public void setIdCard_pictureURL_1(String idCard_pictureURL_1) {
		this.idCard_pictureURL_1 = idCard_pictureURL_1;
	}

	public String getIdCard_pictureURL_2() {
		return idCard_pictureURL_2;
	}

	public void setIdCard_pictureURL_2(String idCard_pictureURL_2) {
		this.idCard_pictureURL_2 = idCard_pictureURL_2;
	}

	public Integer getApplication_check() {
		return application_check;
	}

	public void setApplication_check(Integer application_check) {
		this.application_check = application_check;
	}

	@Override
	public String toString() {
		return "Employer_application [employer_application_id=" + employer_application_id + ", user_id=" + user_id
				+ ", user_email=" + user_email + ", company_name=" + company_name + ", company_address="
				+ company_address + ", company_category=" + company_category + ", company_phoneNumber="
				+ company_phoneNumber + ", company_taxID=" + company_taxID + ", company_taxID_docURL="
				+ company_taxID_docURL + ", user_national_id=" + user_national_id + ", idCard_pictureURL_1="
				+ idCard_pictureURL_1 + ", idCard_pictureURL_2=" + idCard_pictureURL_2 + ", application_check="
				+ application_check + "]";
	}


}
