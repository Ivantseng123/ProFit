package com.ProFit.bean.transactionBean;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "user_transactions")
public class UserTransactionBean {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_amount")
    private int transactionAmount;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "completion_at")  
    private Timestamp completionAt;
    
    @OneToOne(mappedBy = "userTransactionBean", fetch = FetchType.LAZY, optional = true)
    private InvoiceBean invoiceBean;
    
    public UserTransactionBean() {
        this.transactionId = UUID.randomUUID().toString();
    }

    public UserTransactionBean(int userId, String transactionType, int transactionAmount, String transactionStatus, Timestamp createdAt) {
        this.transactionId = UUID.randomUUID().toString();
        this.userId = userId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionStatus = transactionStatus;
        this.createdAt = createdAt;
    }

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
    
    public InvoiceBean getInvoiceBean() {
        return invoiceBean;
    }

    public void setInvoiceBean(InvoiceBean invoiceBean) {
        this.invoiceBean = invoiceBean;
    }
}
