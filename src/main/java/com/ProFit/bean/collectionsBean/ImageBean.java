package com.ProFit.bean.collectionsBean;

import java.sql.Blob;

public class ImageBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer imageId;
	private Integer collectionId;
	private Blob imageFile;

	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public Blob getImageFile() {
		return imageFile;
	}
	public void setImageFile(Blob imageFile) {
		this.imageFile = imageFile;
	}

}
