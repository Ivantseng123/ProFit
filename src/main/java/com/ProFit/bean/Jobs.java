package com.ProFit.bean;

import java.sql.Date;

public class Jobs {
//	資料型態對應：
//	INT: Java 的 Integer
//	NVARCHAR: Java 的 String
//	DATETIME: Java 的 Date
//	TINYINT: Java 的 Byte (假設 TINYINT 用於表示狀態)
	private Integer jobsId;
	private Integer jobsUserId;
	private String jobsRequiredSkills;
	private String jobsTitle;
	private Date jobsPostingDate;
	private Date jobsApplicationDeadline;
	private String jobsDescription;
	private Byte jobsStatus = (byte) 0;
	private String jobsLocation;
	private Integer jobsMaxSalary ;
	private Integer jobsMinSalary ;
	private String jobsWorktime;
	private Integer jobsNumberOfOpenings ;


	public Integer getJobsId() {
		return jobsId;
	}
	public void setJobsId(Integer jobsId) {
		this.jobsId = jobsId;
	}
	public Integer getJobsUserId() {
		return jobsUserId;
	}
	public void setJobsUserId(Integer jobsUserId) {
		this.jobsUserId = jobsUserId;
	}
	public String getJobsTitle() {
		return jobsTitle;
	}
	public void setJobsTitle(String jobsTitle) {
		this.jobsTitle = jobsTitle;
	}
	public Date getJobsPostingDate() {
		return jobsPostingDate;
	}
	public void setJobsPostingDate(Date jobsPostingDate) {
		this.jobsPostingDate = jobsPostingDate;
	}
	public Date getJobsApplicationDeadline() {
		return jobsApplicationDeadline;
	}
	public void setJobsApplicationDeadline(Date jobsApplicationDeadline) {
		this.jobsApplicationDeadline = jobsApplicationDeadline;
	}
	public String getJobsDescription() {
		return jobsDescription;
	}
	public void setJobsDescription(String jobsDescription) {
		this.jobsDescription = jobsDescription;
	}
	public Byte getJobsStatus() {
		return jobsStatus;
	}
	public void setJobsStatus(Byte jobsStatus) {
		this.jobsStatus = jobsStatus;
	}
	public String getJobsRequiredSkills() {
		return jobsRequiredSkills;
	}
	public void setJobsRequiredSkills(String jobsRequiredSkills) {
		this.jobsRequiredSkills = jobsRequiredSkills;
	}
	public String getJobsLocation() {
		return jobsLocation;
	}
	public void setJobsLocation(String jobsLocation) {
		this.jobsLocation = jobsLocation;
	}
	public Integer getJobsMaxSalary() {
		return jobsMaxSalary;
	}
	public void setJobsMaxSalary(Integer jobsMaxSalary) {
		this.jobsMaxSalary = jobsMaxSalary;
	}
	public Integer getJobsMinSalary() {
		return jobsMinSalary;
	}
	public void setJobsMinSalary(Integer jobsMinSalary) {
		this.jobsMinSalary = jobsMinSalary;
	}
	public String getJobsWorktime() {
		return jobsWorktime;
	}
	public void setJobsWorktime(String jobsWorktime) {
		this.jobsWorktime = jobsWorktime;
	}
	public Integer getJobsNumberOfOpenings() {
		return jobsNumberOfOpenings;
	}
	public void setJobsNumberOfOpenings(Integer jobsNumberOfOpenings) {
		this.jobsNumberOfOpenings = jobsNumberOfOpenings;
	}

	@Override
	public String toString() {
		return "Jobs [jobsId=" + jobsId + ", jobsUserId=" + jobsUserId + ", jobsRequiredSkills=" + jobsRequiredSkills
				+ ", jobsTitle=" + jobsTitle + ", jobsPostingDate=" + jobsPostingDate + ", jobsApplicationDeadline="
				+ jobsApplicationDeadline + ", jobsDescription=" + jobsDescription + ", jobsStatus=" + jobsStatus
				+ ", jobsLocation=" + jobsLocation + ", jobsMaxSalary=" + jobsMaxSalary + ", jobsMinSalary="
				+ jobsMinSalary + ", jobsWorktime=" + jobsWorktime + ", jobsNumberOfOpenings=" + jobsNumberOfOpenings
				+ "]";
	}



}
