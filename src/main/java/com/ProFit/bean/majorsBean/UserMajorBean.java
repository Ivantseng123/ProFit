package com.ProFit.bean.majorsBean;

public class UserMajorBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer majorId;
	private String userName;  // 新增字段
    private String majorName; // 新增字段

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

}
