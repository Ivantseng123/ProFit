package com.ProFit.bean.collectionsBean;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "video")
public class VideoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Integer videoId;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private CollectionBean collection;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "video_disc")
    private String videoDisc;

    // 無參構造函數
    public VideoBean() {
    }

    // 帶參數的構造函數
    public VideoBean(CollectionBean collection, String videoUrl, String videoDisc) {
        this.collection = collection;
        this.videoUrl = videoUrl;
        this.videoDisc = videoDisc;
    }

    // Getter 和 Setter 方法
    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public CollectionBean getCollection() {
        return collection;
    }

    public void setCollection(CollectionBean collection) {
        this.collection = collection;
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

    @Override
    public String toString() {
        return "VideoBean{" +
                "videoId=" + videoId +
                ", collection=" + collection +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoDisc='" + videoDisc + '\'' +
                '}';
    }
}