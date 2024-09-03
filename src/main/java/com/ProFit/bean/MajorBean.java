package com.ProFit.bean;

public class MajorBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer majorId; // 主鍵
    private String majorName; // 專業名稱
    private Integer majorCategoryId; // 專業_類別ID
    private String majorDescription; // 專業描述
    private String categoryName;
    
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public Integer getMajorCategoryId() {
		return majorCategoryId;
	}
	public void setMajorCategoryId(Integer majorCategoryId) {
		this.majorCategoryId = majorCategoryId;
	}
	public String getMajorDescription() {
		return majorDescription;
	}
	public void setMajorDescription(String majorDescription) {
		this.majorDescription = majorDescription;
	}
    
}
