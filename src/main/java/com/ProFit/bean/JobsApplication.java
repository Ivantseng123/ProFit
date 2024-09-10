package com.ProFit.bean;

import java.sql.Blob;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "jobsApplication")
public class JobsApplication {
//	資料型態對應：
//	INT: Java 的 Integer
//	NVARCHAR: Java 的 String
//	DATETIME: Java 的 Date
//	TINYINT: Java 的 Byte (假設 TINYINT 用於表示狀態)
//	VARBINARY(MAX)
//	如果需要對資料進行隨機存取，byte[] 是更好的選擇。
//	如果只需要逐個位元組地讀取資料，InputStream 是一個不錯的選擇。
//	如果需要直接與資料庫進行互動，Blob 是最適合的選擇。
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "jobs_application_id")
	  private Integer jobsApplicationId;

	  @ManyToOne(fetch = FetchType.LAZY)//多個申請對應到一個職缺
	  @JoinColumn(name = "jobs_application_posting_id")
	  private Integer jobsApplicationPostingId;

	  @OneToOne(fetch = FetchType.LAZY)//ㄧ個申請對應到一個應徵者
	  @JoinColumn(name = "jobs_application_member_id")
	  private Integer jobsApplicationMemberId;

	  @Temporal(TemporalType.DATE)
	  @Column(name = "jobs_application_date",insertable = false,updatable = false)
	  private Date jobsApplicationDate;

	  @Column(name = "jobs_application_status")
	  private Byte jobsApplicationStatus;

	  @Lob // Use @Lob for Blob data type
	  @Column(name = "jobs_application_contract")
	  private Blob jobsApplicationContract;
	
	
	  
	  
	  public JobsApplication() {
			super();
			// TODO Auto-generated constructor stub
		}
	

	public JobsApplication(Integer jobsApplicationId, Integer jobsApplicationPostingId, Integer jobsApplicationMemberId,
			Date jobsApplicationDate, Byte jobsApplicationStatus, Blob jobsApplicationContract) {
		super();
		this.jobsApplicationId = jobsApplicationId;
		this.jobsApplicationPostingId = jobsApplicationPostingId;
		this.jobsApplicationMemberId = jobsApplicationMemberId;
		this.jobsApplicationDate = jobsApplicationDate;
		this.jobsApplicationStatus = jobsApplicationStatus;
		this.jobsApplicationContract = jobsApplicationContract;
	}









	public Integer getJobsApplicationId() {
		return jobsApplicationId;
	}
	public void setJobsApplicationId(Integer jobsApplicationId) {
		this.jobsApplicationId = jobsApplicationId;
	}
	public Integer getJobsApplicationPostingId() {
		return jobsApplicationPostingId;
	}
	public void setJobsApplicationPostingId(Integer jobsApplicationPostingId) {
		this.jobsApplicationPostingId = jobsApplicationPostingId;
	}
	public Integer getJobsApplicationMemberId() {
		return jobsApplicationMemberId;
	}
	public void setJobsApplicationMemberId(Integer jobsApplicationMemberId) {
		this.jobsApplicationMemberId = jobsApplicationMemberId;
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



	@Override
	public String toString() {
		return "JobsApplication [jobsApplicationId=" + jobsApplicationId + ", jobsApplicationPostingId="
				+ jobsApplicationPostingId + ", jobsApplicationMemberId=" + jobsApplicationMemberId
				+ ", jobsApplicationDate=" + jobsApplicationDate + ", jobsApplicationStatus=" + jobsApplicationStatus
				+ ", jobsApplicationContract=" + jobsApplicationContract + "]";
	}






}
