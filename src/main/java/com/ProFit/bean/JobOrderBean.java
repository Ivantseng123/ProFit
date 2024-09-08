package com.ProFit.bean;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "job_orders")
public class JobOrderBean {

    @Id
    @Column(name = "job_orders_id")
    private String jobOrdersId;

    @Column(name = "job_application_id")
    private int jobApplicationId;

    @Column(name = "job_order_date")
    private Timestamp jobOrderDate;

    @Column(name = "job_order_status")
    private String jobOrderStatus;

    @Column(name = "job_notes")
    private String jobNotes;

    @Column(name = "job_amount")
    private int jobAmount;

    public JobOrderBean() {
    }

    public JobOrderBean(String jobOrdersId, int jobApplicationId, Timestamp jobOrderDate, String jobOrderStatus, String jobNotes, int jobAmount) {
        this.jobOrdersId = jobOrdersId;
        this.jobApplicationId = jobApplicationId;
        this.jobOrderDate = jobOrderDate;
        this.jobOrderStatus = jobOrderStatus;
        this.jobNotes = jobNotes;
        this.jobAmount = jobAmount;
    }

    // Getters 和 Setters
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

    public int getJobAmount() {
        return jobAmount;
    }

    public void setJobAmount(int jobAmount) {
        this.jobAmount = jobAmount;
    }
}
