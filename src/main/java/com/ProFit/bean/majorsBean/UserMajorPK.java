package com.ProFit.bean.majorsBean;

import java.io.Serializable;
import java.util.Objects;

import com.ProFit.bean.usersBean.Users;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class UserMajorPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "user_id") // Users的@Id變數名稱
	private Users user;

	@ManyToOne
	@JoinColumn(name = "major_id") // MajorBean的@Id變數名稱
	private MajorBean major;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserMajorPK))
			return false;
		UserMajorPK that = (UserMajorPK) o;
		return (major.getMajorId() == that.major.getMajorId() && 
				user.getUserId() == that.user.getUserId());
	}

	@Override
    public int hashCode() {
        return Objects.hash(major.getMajorId(), user.getUserId());
    }
	
	public UserMajorPK() {
	}
	
	public UserMajorPK(Users user, MajorBean major) {
        this.user = user;
        this.major = major;
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
	
	 @Override
	    public String toString() {
	        return "UserMajorPK [user=" + user.getUserId() + ", major=" + major.getMajorId() + "]";
	    }

}
