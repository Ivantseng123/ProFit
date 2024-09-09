package com.ProFit.bean.collectionsBean;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class ImageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private CollectionBean collection;

    @Column(name = "image_pictureURL")
    private String imagePictureURL;

    // 無參構造函數
    public ImageBean() {
    }

    // 帶參數的構造函數
    public ImageBean(CollectionBean collection, String imagePictureURL) {
        this.collection = collection;
        this.imagePictureURL = imagePictureURL;
    }

    // Getter 和 Setter 方法
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public CollectionBean getCollection() {
        return collection;
    }

    public void setCollection(CollectionBean collection) {
        this.collection = collection;
    }

    public String getImagePictureURL() {
        return imagePictureURL;
    }

    public void setImagePictureURL(String imagePictureURL) {
        this.imagePictureURL = imagePictureURL;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "imageId=" + imageId +
                ", collection=" + collection +
                ", imagePictureURL='" + imagePictureURL + '\'' +
                '}';
    }
}