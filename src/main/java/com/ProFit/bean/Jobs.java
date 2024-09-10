package com.ProFit.bean;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "jobs")
public class Jobs {
	private static final long serialVersionUID = 1L;//序列化，就像是身分證


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobs_id")
    private Integer jobsId;

	@OneToMany(fetch = FetchType.LAZY)//一個職缺對應到多個應徵者
	@JoinColumn(name = "jobs_user_id")
    private Integer jobsUserId;

    
    @Column(name = "jobs_required_skills")
    private String jobsRequiredSkills;

    @Column(name = "jobs_title")
    private String jobsTitle;

    @Temporal(TemporalType.DATE)
    @Column(name = "jobs_posting_date",insertable = false,updatable = false)
    private Date jobsPostingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "jobs_application_deadline")
    private Date jobsApplicationDeadline;

    @Column(name = "jobs_description")
    private String jobsDescription;

    @Column(name = "jobs_status")
    private Byte jobsStatus = 0;

    @Column(name = "jobs_location")
    private String jobsLocation;

    @Column(name = "jobs_max_salary")
    private Integer jobsMaxSalary;

    @Column(name = "jobs_min_salary")
    private Integer jobsMinSalary;

    @Column(name = "jobs_worktime")
    private String jobsWorktime;

    @Column(name = "jobs_number_of_openings")
    private Integer jobsNumberOfOpenings;
    
    
    
	public Jobs() {
		super();
		// TODO Auto-generated constructor stub
	}
   
	public Jobs(Integer jobsId, Integer jobsUserId, String jobsRequiredSkills, String jobsTitle, Date jobsPostingDate,
			Date jobsApplicationDeadline, String jobsDescription, Byte jobsStatus, String jobsLocation,
			Integer jobsMaxSalary, Integer jobsMinSalary, String jobsWorktime, Integer jobsNumberOfOpenings) {
		super();
		this.jobsId = jobsId;
		this.jobsUserId = jobsUserId;
		this.jobsRequiredSkills = jobsRequiredSkills;
		this.jobsTitle = jobsTitle;
		this.jobsPostingDate = jobsPostingDate;
		this.jobsApplicationDeadline = jobsApplicationDeadline;
		this.jobsDescription = jobsDescription;
		this.jobsStatus = jobsStatus;
		this.jobsLocation = jobsLocation;
		this.jobsMaxSalary = jobsMaxSalary;
		this.jobsMinSalary = jobsMinSalary;
		this.jobsWorktime = jobsWorktime;
		this.jobsNumberOfOpenings = jobsNumberOfOpenings;
	}
	
	
 // Getters and setters remain the same




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

	public String getJobsRequiredSkills() {
		return jobsRequiredSkills;
	}

	public void setJobsRequiredSkills(String jobsRequiredSkills) {
		this.jobsRequiredSkills = jobsRequiredSkills;
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