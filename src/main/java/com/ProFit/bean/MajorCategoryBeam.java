package com.ProFit.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="major_category")
public class MajorCategoryBeam implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="major_category_id")
	private Integer majorCategoryId; // 主鍵，專業_類別ID
	
	private String categoryName; // 類別名稱

	public Integer getMajorCategoryId() {
		return majorCategoryId;
	}
	public void setMajorCategoryId(Integer majorCategoryId) {
		this.majorCategoryId = majorCategoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



}
