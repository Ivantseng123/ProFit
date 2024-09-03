package com.ProFit.bean;

public class VideoBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer videoId;
	private Integer collectionId;
	private String videoUrl;
	private String videoDisc;
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getVideoDisc() {
		return videoDisc;
	}
	public void setVideoDisc(String videoDisc) {
		this.videoDisc = videoDisc;
	}
	
}
