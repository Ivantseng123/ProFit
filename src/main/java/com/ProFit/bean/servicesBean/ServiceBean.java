package com.ProFit.bean.servicesBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class ServiceBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @Column
	private Integer serviceId; // 主鍵，服務ID
    
	
	private Integer userId; // 使用者ID
    
	
	private Integer majorId; // 專業ID
    
	
	private String serviceTitle; // 服務標題
    
	
	private String serviceContent; // 服務內容
    
	
	private Integer servicePrice; // 服務價格
    
	
	private String serviceUnitName; // 服務單位名稱
    
	
	private Double serviceDuration; // 服務時長
    
	
	private LocalDateTime serviceCreateDate; // 服務建立日期
    
	
	private LocalDateTime serviceUpdateDate; // 服務更新日期
    
	
	private String servicePictureURL1; // 服務樣本1
    
	
	private String servicePictureURL2; // 服務樣本2
    
	
	private String servicePictureURL3; // 服務樣本3

	
	private Integer serviceStatus;


	public ServiceBean() {
		super();
	}

	


}
