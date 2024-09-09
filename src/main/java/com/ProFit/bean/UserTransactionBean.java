package com.ProFit.bean;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "user_transactions")
public class UserTransactionBean {

    @Id
    @Column(name = "transaction_id", length = 50)
    private String transactionId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_amount", nullable = false)
    private int transactionAmount;

    @Column(name = "transaction_status", nullable = false)
    private String transactionStatus;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "completion_at")  // 新增完成時間
    private Timestamp completionAt;

    // Default constructor
    public UserTransactionBean() {
        // Automatically generate transactionId
        this.transactionId = UUID.randomUUID().toString();
    }

    // Constructor without transactionId (it will be generated automatically)
    public UserTransactionBean(int userId, String transactionType, int transactionAmount, String transactionStatus, Timestamp createdAt) {
        this.transactionId = UUID.randomUUID().toString();
        this.userId = userId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionStatus = transactionStatus;
        this.createdAt = createdAt;
    }

    // Getter and Setter methods

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
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

    public Timestamp getCompletionAt() {
        return completionAt;
    }

    public void setCompletionAt(Timestamp completionAt) {
        this.completionAt = completionAt;
    }
}
