package com.ProFit.bean.collectionsBean;

import java.io.Serializable;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.bean.majorsBean.MajorBean;

import jakarta.persistence.*;

@Entity
@Table(name = "collection")
public class CollectionBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Integer collectionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private MajorBean major;

    @Column(name = "collection_cover_img_id")
    private Integer collectionCoverImgId;

    @Column(name = "collection_name")
    private String collectionName;

    // 無參構造函數
    public CollectionBean() {
    }

    // 帶參數的構造函數
    public CollectionBean(Users user, MajorBean major, Integer collectionCoverImgId, String collectionName) {
        this.user = user;
        this.major = major;
        this.collectionCoverImgId = collectionCoverImgId;
        this.collectionName = collectionName;
    }

    // Getter 和 Setter 方法
    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public MajorBean getMajor() {
        return major;
    }

    public void setMajor(MajorBean major) {
        this.major = major;
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

    @Override
    public String toString() {
        return "CollectionBean{" +
                "collectionId=" + collectionId +
                ", user=" + user +
                ", major=" + major +
                ", collectionCoverImgId=" + collectionCoverImgId +
                ", collectionName='" + collectionName + '\'' +
                '}';
    }
}