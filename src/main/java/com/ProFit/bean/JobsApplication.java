package com.ProFit.bean;

import java.sql.Blob;
import java.sql.Date;

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
	private Integer jobsApplicationId ;
	private Integer jobsApplicationPostingId;
	private Integer jobsApplicationMemberId ;
	private Date jobsApplicationDate;
	private Byte jobsApplicationStatus;
	private Blob jobsApplicationContract;
	
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
