package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.InvoiceBean;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class InvoiceDAO {

    private Session session;

    public InvoiceDAO(Session session) {
        this.session = session;
    }

    public List<InvoiceBean> getAllInvoices() {
        Query<InvoiceBean> query = session.createQuery("from InvoiceBean order by issuedDate desc", InvoiceBean.class);
        return query.list();
    }

    public List<InvoiceBean> searchInvoices(String invoiceNumber, String invoiceStatus, String transactionId, String jobOrderId, String courseOrderId, String eventOrderId) {
        StringBuilder hql = new StringBuilder("from InvoiceBean where 1=1 ");
        
        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            hql.append("and invoiceNumber = :invoiceNumber ");
        }
        if (invoiceStatus != null && !invoiceStatus.isEmpty()) {
            hql.append("and invoiceStatus = :invoiceStatus ");
        }
        if (transactionId != null && !transactionId.isEmpty()) {
            hql.append("and transactionId = :transactionId ");
        }
        if (jobOrderId != null && !jobOrderId.isEmpty()) {
            hql.append("and jobOrderId = :jobOrderId ");
        }
        if (courseOrderId != null && !courseOrderId.isEmpty()) {
            hql.append("and courseOrderId = :courseOrderId ");
        }
        if (eventOrderId != null && !eventOrderId.isEmpty()) {
            hql.append("and eventOrderId = :eventOrderId ");
        }

        Query<InvoiceBean> query = session.createQuery(hql.toString(), InvoiceBean.class);

        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            query.setParameter("invoiceNumber", invoiceNumber);
        }
        if (invoiceStatus != null && !invoiceStatus.isEmpty()) {
            query.setParameter("invoiceStatus", invoiceStatus);
        }
        if (transactionId != null && !transactionId.isEmpty()) {
            query.setParameter("transactionId", transactionId);
        }
        if (jobOrderId != null && !jobOrderId.isEmpty()) {
            query.setParameter("jobOrderId", jobOrderId);
        }
        if (courseOrderId != null && !courseOrderId.isEmpty()) {
            query.setParameter("courseOrderId", courseOrderId);
        }
        if (eventOrderId != null && !eventOrderId.isEmpty()) {
            query.setParameter("eventOrderId", eventOrderId);
        }

        return query.list();
    }
}
