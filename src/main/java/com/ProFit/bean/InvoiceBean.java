package com.ProFit.bean;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "invoices")
public class InvoiceBean {

    @Id
    @Column(name = "invoice_number")  // 改成對應資料庫中的主鍵
    private String invoiceNumber;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "job_order_id")
    private String jobOrderId;

    @Column(name = "course_order_id")
    private String courseOrderId;

    @Column(name = "event_order_id")
    private String eventOrderId;

    @Column(name = "invoice_amount")
    private int invoiceAmount;

    @Column(name = "issued_date")
    private Timestamp issuedDate;

    @Column(name = "invoice_status")
    private String invoiceStatus;
    
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getJobOrderId() {
        return jobOrderId;
    }

    public void setJobOrderId(String jobOrderId) {
        this.jobOrderId = jobOrderId;
    }

    public String getCourseOrderId() {
        return courseOrderId;
    }

    public void setCourseOrderId(String courseOrderId) {
        this.courseOrderId = courseOrderId;
    }

    public String getEventOrderId() {
        return eventOrderId;
    }

    public void setEventOrderId(String eventOrderId) {
        this.eventOrderId = eventOrderId;
    }

    public int getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(int invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Timestamp getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Timestamp issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
