package com.ProFit.bean.servicesBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.ProFit.bean.majorsBean.UserMajorBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service")
public class ServiceBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private Integer serviceId; // 主鍵，服務ID

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
			@JoinColumn(name = "major_id", referencedColumnName = "major_id") })
	private UserMajorBean userMajor;

	/**
	 * @Column(name = "user_id") private Integer userId; // 使用者ID
	 * 
	 * @Column(name = "major_id") private Integer majorId; // 專業ID
	 **/

	@Column(name = "service_title")
	private String serviceTitle; // 服務標題

	@Column(name = "service_content")
	private String serviceContent; // 服務內容

	@Column(name = "service_price", nullable = false)
	private Integer servicePrice; // 服務價格

	@Column(name = "service_unit_name")
	private String serviceUnitName; // 服務單位名稱

	@Column(name = "service_duration")
	private Double serviceDuration; // 服務時長

	@Column(name = "service_createdate")
	private LocalDateTime serviceCreateDate; // 服務建立日期

	@Column(name = "service_updatedate")
	private LocalDateTime serviceUpdateDate; // 服務更新日期

	@Column(name = "service_pictureURL_1")
	private String servicePictureURL1; // 服務樣本1

	@Column(name = "service_pictureURL_2")
	private String servicePictureURL2; // 服務樣本2

	@Column(name = "service_pictureURL_3")
	private String servicePictureURL3; // 服務樣本3

	@Column(name = "service_status", nullable = false)
	private Integer serviceStatus = 0;

	//無參數建構子
	public ServiceBean() {
	}
	
	//主要參數建構子
	public ServiceBean(UserMajorBean userMajorBean, String serviceTitle, String serviceContent, Integer servicePrice,
			String serviceUnitName, Double serviceDuration, LocalDateTime serviceCreateDate,
			LocalDateTime serviceUpdateDate) {
		super();
		this.userMajor = userMajorBean;
		this.serviceTitle = serviceTitle;
		this.serviceContent = serviceContent;
		this.servicePrice = servicePrice;
		this.serviceUnitName = serviceUnitName;
		this.serviceDuration = serviceDuration;
		this.serviceCreateDate = serviceCreateDate;
		this.serviceUpdateDate = serviceUpdateDate;
	}

	//更多參數建構子
	public ServiceBean(UserMajorBean userMajorBean, String serviceTitle, String serviceContent, Integer servicePrice,
			String serviceUnitName, Double serviceDuration, LocalDateTime serviceCreateDate,
			LocalDateTime serviceUpdateDate, String servicePictureURL1, String servicePictureURL2,
			String servicePictureURL3) {
		super();
		this.userMajor = userMajorBean;
		this.serviceTitle = serviceTitle;
		this.serviceContent = serviceContent;
		this.servicePrice = servicePrice;
		this.serviceUnitName = serviceUnitName;
		this.serviceDuration = serviceDuration;
		this.serviceCreateDate = serviceCreateDate;
		this.serviceUpdateDate = serviceUpdateDate;
		this.servicePictureURL1 = servicePictureURL1;
		this.servicePictureURL2 = servicePictureURL2;
		this.servicePictureURL3 = servicePictureURL3;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public UserMajorBean getUserMajor() {
		return userMajor;
	}

	public void setUserMajor(UserMajorBean userMajorBean) {
		this.userMajor = userMajorBean;
	}

	public String getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public Integer getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Integer servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getServiceUnitName() {
		return serviceUnitName;
	}

	public void setServiceUnitName(String serviceUnitName) {
		this.serviceUnitName = serviceUnitName;
	}

	public Double getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(Double serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public LocalDateTime getServiceCreateDate() {
		return serviceCreateDate;
	}

	public void setServiceCreateDate(LocalDateTime serviceCreateDate) {
		this.serviceCreateDate = serviceCreateDate;
	}

	public LocalDateTime getServiceUpdateDate() {
		return serviceUpdateDate;
	}

	public void setServiceUpdateDate(LocalDateTime serviceUpdateDate) {
		this.serviceUpdateDate = serviceUpdateDate;
	}

	public String getServicePictureURL1() {
		return servicePictureURL1;
	}

	public void setServicePictureURL1(String servicePictureURL1) {
		this.servicePictureURL1 = servicePictureURL1;
	}

	public String getServicePictureURL2() {
		return servicePictureURL2;
	}

	public void setServicePictureURL2(String servicePictureURL2) {
		this.servicePictureURL2 = servicePictureURL2;
	}

	public String getServicePictureURL3() {
		return servicePictureURL3;
	}

	public void setServicePictureURL3(String servicePictureURL3) {
		this.servicePictureURL3 = servicePictureURL3;
	}

	public Integer getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(Integer serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	
	
	@Override
    public String toString() {
        return "ServiceBean{" +
                "serviceId=" + serviceId +
                ", userMajor=" + userMajor +
                ", serviceTitle='" + serviceTitle + '\'' +
                ", serviceContent='" + serviceContent + '\'' +
                ", servicePrice=" + servicePrice +
                ", serviceUnitName='" + serviceUnitName + '\'' +
                ", serviceDuration=" + serviceDuration +
                ", serviceCreatedate=" + serviceCreateDate +
                ", serviceUpdatedate=" + serviceUpdateDate +
                ", servicePictureURL1='" + servicePictureURL1 + '\'' +
                ", servicePictureURL2='" + servicePictureURL2 + '\'' +
                ", servicePictureURL3='" + servicePictureURL3 + '\'' +
                ", serviceStatus=" + serviceStatus +
                '}';
    }
	

}
