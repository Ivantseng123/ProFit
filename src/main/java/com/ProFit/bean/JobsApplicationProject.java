package com.ProFit.bean;

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
@Table(name = "jobs_application_project")
public class JobsApplicationProject {
//	資料型態對應：
//	INT: Java 的 Integer
//	NVARCHAR: Java 的 String
//	DATETIME: Java 的 Date
//	TINYINT: Java 的 Byte (假設 TINYINT 用於表示狀態)



	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "jobs_application_project_id")
	    private Integer jobsApplicationProjectId;

	    @OneToOne(fetch = FetchType.LAZY)//一個申請對應到一個申請項目
	    @JoinColumn(name = "jobs_application_id")
	    private Integer jobsApplicationId;

	    @Column(name = "jobs_application_status")
	    private Byte jobsApplicationStatus = 0;

	    @Column(name = "jobs_project")
	    private String jobsProject;

	    @Column(name = "jobs_amount")
	    private Integer jobsAmount;
	    
	    
	    
	    
	    public JobsApplicationProject() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	public JobsApplicationProject(Integer jobsApplicationProjectId, Integer jobsApplicationId,
				Byte jobsApplicationStatus, String jobsProject, Integer jobsAmount) {
			super();
			this.jobsApplicationProjectId = jobsApplicationProjectId;
			this.jobsApplicationId = jobsApplicationId;
			this.jobsApplicationStatus = jobsApplicationStatus;
			this.jobsProject = jobsProject;
			this.jobsAmount = jobsAmount;
		}





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
