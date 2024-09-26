package com.ProFit.bean.jobsBean;

import java.sql.Blob;
import java.sql.Date;

import com.ProFit.bean.usersBean.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
//	資料型態對應：
//	INT: Java 的 Integer
//	NVARCHAR: Java 的 String
//	DATETIME: Java 的 Date
//	TINYINT: Java 的 Byte (假設 TINYINT 用於表示狀態)
//	VARBINARY(MAX)
//	如果需要對資料進行隨機存取，byte[] 是更好的選擇。
//	如果只需要逐個位元組地讀取資料，InputStream 是一個不錯的選擇。
//	如果需要直接與資料庫進行互動，Blob 是最適合的選擇。
import jakarta.persistence.Transient;




@Entity
@Table(name = "jobs_Application")
public class JobsApplication {

	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "jobs_application_id")
	  private int jobsApplicationId;
	  
	  

	  @ManyToOne(fetch = FetchType.LAZY)//FK，對Jobs表，一個職缺對應到多個申請
//	  @JoinColumn(name = "jobs_application_posting_id",insertable = false,updatable = false)
	  @JoinColumn(name = "jobs_application_posting_id")
	  private Users poster;

	  @ManyToOne(fetch = FetchType.LAZY)//FK，對USER表，一個應徵者對應到多個申請
//	  @JoinColumn(name = "jobs_application_member_id",insertable = false,updatable = false)//改名為jobs_application_user_id
	  @JoinColumn(name = "jobs_application_member_id")
	  private Users applicant;
	  
//	  @Column(name = "jobs_application_posting_id")
//	  private Integer jobsApplicationPostingId;
//
//	  @Column(name = "jobs_application_member_id")
//	  private Integer jobsApplicationMemberId;
	  
	  

	  @Temporal(TemporalType.DATE)
	  @Transient
	  @Column(name = "jobs_application_date",insertable = false,updatable = false)
	  private Date jobsApplicationDate;

	  @Column(name = "jobs_application_status")
	  private Byte jobsApplicationStatus=0;

	  @Lob // Use @Lob for Blob data type
	  @Column(name = "jobs_application_contract")
	  private Blob jobsApplicationContract;

	  
	  
	public int getJobsApplicationId() {
		return jobsApplicationId;
	}

	public void setJobsApplicationId(int jobsApplicationId) {
		this.jobsApplicationId = jobsApplicationId;
	}

	public Users getPoster() {
		return poster;
	}

	public void setPoster(Users poster) {
		this.poster = poster;
	}

	public Users getApplicant() {
		return applicant;
	}

	public void setApplicant(Users applicant) {
		this.applicant = applicant;
	}

	public Date getJobsApplicationDate() {
		return jobsApplicationDate;
	}

	public void setJobsApplicationDate(Date jobsApplicationDate) {
		this.jobsApplicationDate = jobsApplicationDate;
	}

	public Byte getJobsApplicationStatus() {
		return jobsApplicationStatus;
	}

	public void setJobsApplicationStatus(Byte jobsApplicationStatus) {
		this.jobsApplicationStatus = jobsApplicationStatus;
	}

	public Blob getJobsApplicationContract() {
		return jobsApplicationContract;
	}

	public void setJobsApplicationContract(Blob jobsApplicationContract) {
		this.jobsApplicationContract = jobsApplicationContract;
	}





}
