package com.ProFit.bean;

import java.sql.Timestamp;

public class UserTransactionBean {
    private String transactionId;
    private String userId;
    private String userName;
    private String transactionType;
    private String amount;
    private String transactionStatus;
    private Timestamp createdAt;

    public UserTransactionBean() {
        super();
    }


    public UserTransactionBean(String transactionId, String userId, String userName, String transactionType, String amount,
                               String transactionStatus, Timestamp createdAt) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.userName = userName;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserTransactionBean{" +
                "transactionId=" + transactionId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    // Getters 和 Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
