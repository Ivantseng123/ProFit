package com.ProFit.bean.jobsBean;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import com.ProFit.bean.usersBean.Users;


@Entity
@Table(name = "jobs")
public class Jobs implements java.io.Serializable{
	private static final long serialVersionUID = 1L;//序列化，就像是身分證


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobs_id")
    private Integer jobsId;
    
    

    @ManyToOne(fetch = FetchType.EAGER)//FK，對User表，一個公司（user）可以有po很多職缺
    @JoinColumn(name= "jobs_user_id")
    private Users users;//請跟我有關聯的表格組員修改他們的關係語句
    
        
//    @ManyToOne(fetch = FetchType.LAZY)//改FK，對major表，一個技能可以對應很多職缺
//    @Column(name = "jobs_required_skills")
//    private String jobsRequiredSkills;
//    
//    @Column(name = "jobs_user_id")
//    private Integer jobsUserId;
    
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

    @Column(name = "jobs_location")
    private String jobsLocation;

    @Column(name = "jobs_status")
    private Byte jobsStatus = 0;

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
			}





	public Integer getJobsId() {
		return jobsId;
	}





	public void setJobsId(Integer jobsId) {
		this.jobsId = jobsId;
	}





	public Users getUsers() {
		return users;
	}





	public void setUsers(Users users) {
		this.users = users;
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





	public String getJobsLocation() {
		return jobsLocation;
	}





	public void setJobsLocation(String jobsLocation) {
		this.jobsLocation = jobsLocation;
	}





	public String getJobsRequiredSkills() {
		return jobsRequiredSkills;
	}





	public void setJobsRequiredSkills(String jobsRequiredSkills) {
		this.jobsRequiredSkills = jobsRequiredSkills;
	}





	public Byte getJobsStatus() {
		return jobsStatus;
	}





	public void setJobsStatus(Byte jobsStatus) {
		this.jobsStatus = jobsStatus;
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





	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	@Override
	public String toString() {
		return "Jobs [jobsId=" + jobsId + ", users=" + users + ", jobsRequiredSkills=" + jobsRequiredSkills
				+ ", jobsTitle=" + jobsTitle + ", jobsPostingDate=" + jobsPostingDate + ", jobsApplicationDeadline="
				+ jobsApplicationDeadline + ", jobsDescription=" + jobsDescription + ", jobsLocation=" + jobsLocation
				+ ", jobsStatus=" + jobsStatus + ", jobsMaxSalary=" + jobsMaxSalary + ", jobsMinSalary=" + jobsMinSalary
				+ ", jobsWorktime=" + jobsWorktime + ", jobsNumberOfOpenings=" + jobsNumberOfOpenings + "]";
	}
   

}