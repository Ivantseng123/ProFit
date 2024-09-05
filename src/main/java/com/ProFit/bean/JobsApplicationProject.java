package com.ProFit.bean;


public class JobsApplicationProject {
//	資料型態對應：
//	INT: Java 的 Integer
//	NVARCHAR: Java 的 String
//	DATETIME: Java 的 Date
//	TINYINT: Java 的 Byte (假設 TINYINT 用於表示狀態)

	private Integer jobsApplicationProjectId  ;
	private Integer jobsApplicationId;
	private Byte jobsApplicationStatus = 0;
	private String jobsProject;
	private Integer jobsAmount;

	public Integer getJobsApplicationProjectId() {
		return jobsApplicationProjectId;
	}
	public void setJobsApplicationProjectId(Integer jobsApplicationProjectId) {
		this.jobsApplicationProjectId = jobsApplicationProjectId;
	}
	public Integer getJobsApplicationId() {
		return jobsApplicationId;
	}
	public void setJobsApplicationId(Integer jobsApplicationId) {
		this.jobsApplicationId = jobsApplicationId;
	}
	public Byte getJobsApplicationStatus() {
		return jobsApplicationStatus;
	}
	public void setJobsApplicationStatus(Byte jobsApplicationStatus) {
		this.jobsApplicationStatus = jobsApplicationStatus;
	}
	public String getJobsProject() {
		return jobsProject;
	}
	public void setJobsProject(String jobsProject) {
		this.jobsProject = jobsProject;
	}
	public Integer getJobsAmount() {
		return jobsAmount;
	}
	public void setJobsAmount(Integer jobsAmount) {
		this.jobsAmount = jobsAmount;
	}

	@Override
	public String toString() {
		return "JobsApplicationProject [jobsApplicationProjectId=" + jobsApplicationProjectId + ", jobsApplicationId="
				+ jobsApplicationId + ", jobsApplicationStatus=" + jobsApplicationStatus + ", jobsProject="
				+ jobsProject + ", jobsAmount=" + jobsAmount + "]";
	}


}
