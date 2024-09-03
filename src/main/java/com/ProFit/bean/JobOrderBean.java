package com.ProFit.bean;

import java.sql.Timestamp;

public class JobOrderBean {
    private String jobOrdersId;
    private int jobApplicationId;
    private Timestamp jobOrderDate;
    private String jobOrderStatus;
    private String jobNotes;
    private int totalAmount;

    public JobOrderBean() {
        super();
    }

    public JobOrderBean(String jobOrdersId, int jobApplicationId, Timestamp jobOrderDate, String jobOrderStatus, String jobNotes, int totalAmount) {
        this.jobOrdersId = jobOrdersId;
        this.jobApplicationId = jobApplicationId;
        this.jobOrderDate = jobOrderDate;
        this.jobOrderStatus = jobOrderStatus;
        this.jobNotes = jobNotes;
        this.totalAmount = totalAmount;
    }

    public String getJobOrdersId() {
        return jobOrdersId;
    }

    public void setJobOrdersId(String jobOrdersId) {
        this.jobOrdersId = jobOrdersId;
    }

    public int getJobApplicationId() {
        return jobApplicationId;
    }

    public void setJobApplicationId(int jobApplicationId) {
        this.jobApplicationId = jobApplicationId;
    }

    public Timestamp getJobOrderDate() {
        return jobOrderDate;
    }

    public void setJobOrderDate(Timestamp jobOrderDate) {
        this.jobOrderDate = jobOrderDate;
    }

    public String getJobOrderStatus() {
        return jobOrderStatus;
    }

    public void setJobOrderStatus(String jobOrderStatus) {
        this.jobOrderStatus = jobOrderStatus;
    }

    public String getJobNotes() {
        return jobNotes;
    }

    public void setJobNotes(String jobNotes) {
        this.jobNotes = jobNotes;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
