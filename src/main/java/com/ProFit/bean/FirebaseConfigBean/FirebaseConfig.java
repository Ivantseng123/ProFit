package com.ProFit.bean.FirebaseConfigBean;

import org.springframework.stereotype.Component;

@Component
public class FirebaseConfig {
	
	private String apiKey;
	private String authDomain;
	private String projectId;
	private String storageBucket;
	private String messagingSenderId;
	private String appId;
	private String measurementId;
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getAuthDomain() {
		return authDomain;
	}
	public void setAuthDomain(String authDomain) {
		this.authDomain = authDomain;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getStorageBucket() {
		return storageBucket;
	}
	public void setStorageBucket(String storageBucket) {
		this.storageBucket = storageBucket;
	}
	public String getMessagingSenderId() {
		return messagingSenderId;
	}
	public void setMessagingSenderId(String messagingSenderId) {
		this.messagingSenderId = messagingSenderId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMeasurementId() {
		return measurementId;
	}
	public void setMeasurementId(String measurementId) {
		this.measurementId = measurementId;
	}
}
