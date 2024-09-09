package com.ProFit.bean;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "invoices")
public class InvoiceBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動生成ID
    @Column(name = "invoice_id")
    private String invoiceId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "job_order_id")
    private String jobOrderId;

    @Column(name = "course_order_id")
    private String courseOrderId;

    @Column(name = "event_order_id")
    private String eventOrderId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "invoice_amount")
    private int invoiceAmount;

    @Column(name = "issued_date")
    private Timestamp issuedDate;

    @Column(name = "invoice_status")
    private String invoiceStatus;

    // Getters and setters omitted for brevity...
}
