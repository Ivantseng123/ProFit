package com.ProFit.bean.majorsBean;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "major_category")
public class MajorCategoryBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "major_category_id")
	private Integer majorCategoryId; // 主鍵，專業_類別ID

	@Column(name = "category_name")
	private String categoryName; // 類別名稱

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "majorCategory", cascade = CascadeType.ALL)
	private Set<MajorBean> majors = new HashSet<MajorBean>(0);

	public MajorCategoryBean() {
		super();
	}

	// 帶id建構子
	public MajorCategoryBean(Integer majorCategoryId, String categoryName) {
		super();
		this.majorCategoryId = majorCategoryId;
		this.categoryName = categoryName;
	}

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

	public Set<MajorBean> getMajors() {
		return majors;
	}

	public void setMajors(Set<MajorBean> majors) {
		this.majors = majors;
	}

	@Override
	public String toString() {
		return "MajorCategoryBean [majorCategoryId=" + majorCategoryId + ", categoryName=" + categoryName + "]";
	}

}
