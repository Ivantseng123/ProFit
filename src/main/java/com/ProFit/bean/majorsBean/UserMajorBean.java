package com.ProFit.bean.majorsBean;

import com.ProFit.bean.usersBean.Users;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_major")
public class UserMajorBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UserMajorPK id;
	
	// 構造函數
    public UserMajorBean() {
    }

    public UserMajorBean(UserMajorPK id) {
        this.id = id;
    }

    // Getter 和 Setter
    public UserMajorPK getId() {
        return id;
    }

    public void setId(UserMajorPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserMajorBean [id=" + id + "]";
    }
	
	
}
