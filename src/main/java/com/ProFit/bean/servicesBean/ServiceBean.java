package com.ProFit.bean.servicesBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ServiceBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer serviceId; // 主鍵，服務ID
    private Integer userId; // 使用者ID
    private Integer majorId; // 專業ID
    private String serviceTitle; // 服務標題
    private String serviceContent; // 服務內容
    private Double servicePrice; // 服務價格
    private String serviceUnitName; // 服務單位名稱
    private Double serviceDuration; // 服務時長
    private LocalDateTime serviceCreateDate; // 服務建立日期
    private LocalDateTime serviceUpdateDate; // 服務更新日期
    private byte[] serviceSample1; // 服務樣本1
    private byte[] serviceSample2; // 服務樣本2
    private byte[] serviceSample3; // 服務樣本3



	public ServiceBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
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
	public Double getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(Double servicePrice) {
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

	public Date getServiceCreateDateAsDate() {
        if (serviceCreateDate == null) {
            return null;
        }
        return Date.from(serviceCreateDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getServiceUpdateDateAsDate() {
        if (serviceUpdateDate == null) {
            return null;
        }
        return Date.from(serviceUpdateDate.atZone(ZoneId.systemDefault()).toInstant());
    }


	public LocalDateTime getServiceUpdateDate() {
		return serviceUpdateDate;
	}
	public void setServiceUpdateDate(LocalDateTime serviceUpdateDate) {
		this.serviceUpdateDate = serviceUpdateDate;
	}
	public byte[] getServiceSample1() {
		return serviceSample1;
	}
	public void setServiceSample1(byte[] serviceSample1) {
		this.serviceSample1 = serviceSample1;
	}
	public byte[] getServiceSample2() {
		return serviceSample2;
	}
	public void setServiceSample2(byte[] serviceSample2) {
		this.serviceSample2 = serviceSample2;
	}
	public byte[] getServiceSample3() {
		return serviceSample3;
	}
	public void setServiceSample3(byte[] serviceSample3) {
		this.serviceSample3 = serviceSample3;
	}


}
