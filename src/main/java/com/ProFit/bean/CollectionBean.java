package com.ProFit.bean;

public class CollectionBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer collectionId;
	private Integer userId;
	private Integer majorId;
	private Integer collectionCoverImgId;
	private String collectionName;

	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
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
	public Integer getCollectionCoverImgId() {
		return collectionCoverImgId;
	}
	public void setCollectionCoverImgId(Integer collectionCoverImgId) {
		this.collectionCoverImgId = collectionCoverImgId;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

}
