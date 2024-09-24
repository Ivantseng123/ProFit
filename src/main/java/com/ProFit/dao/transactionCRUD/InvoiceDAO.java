package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.transactionBean.InvoiceBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InvoiceDAO {

    @Autowired
    private SessionFactory sessionFactory; 

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    // 查詢所有發票
    @Transactional(readOnly = true)
    public List<InvoiceBean> getAllInvoices() {
        Query<InvoiceBean> query = getCurrentSession().createQuery("from InvoiceBean order by issuedDate desc", InvoiceBean.class);
        return query.list(); 
    }

    // 根據查詢條件搜索發票
    @Transactional(readOnly = true) 
    public List<InvoiceBean> searchInvoices(String invoiceNumber, String invoiceStatus, String idType, String idValue) {
        StringBuilder hql = new StringBuilder("from InvoiceBean where 1=1 ");

        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            hql.append("and invoiceNumber = :invoiceNumber ");
        }
        if (invoiceStatus != null && !invoiceStatus.isEmpty()) {
            hql.append("and invoiceStatus = :invoiceStatus ");
        }

        if (idType != null) {
            switch (idType) {
                case "transaction_id":
                    hql.append(idValue != null ? "and transactionId = :idValue " : "and transactionId is not null ");
                    break;
                case "job_order_id":
                    hql.append(idValue != null ? "and jobOrderId = :idValue " : "and jobOrderId is not null ");
                    break;
                case "course_order_id":
                    hql.append(idValue != null ? "and courseOrderId = :idValue " : "and courseOrderId is not null ");
                    break;
                case "event_order_id":
                    hql.append(idValue != null ? "and eventOrderId = :idValue " : "and eventOrderId is not null ");
                    break;
            }
        }

        Query<InvoiceBean> query = getCurrentSession().createQuery(hql.toString(), InvoiceBean.class);

        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            query.setParameter("invoiceNumber", invoiceNumber); // 設置發票號參數
        }
        if (invoiceStatus != null && !invoiceStatus.isEmpty()) {
            query.setParameter("invoiceStatus", invoiceStatus); 
        }
        if (idValue != null && !idValue.isEmpty()) {
            query.setParameter("idValue", idValue);
        }

        return query.list();
    }

    // 插入發票
    @Transactional 
    public boolean insertInvoice(InvoiceBean invoice) {
        try {
            getCurrentSession().persist(invoice); 
            return true; 
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    // 刪除發票
    @Transactional
    public boolean deleteInvoice(String invoiceNumber) {
        try {
            InvoiceBean invoice = getCurrentSession().get(InvoiceBean.class, invoiceNumber);
            if (invoice != null) {
                getCurrentSession().remove(invoice); 
                return true; 
            }
            return false;  
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    // 根據發票號碼查找發票（在刪除時可能需要）
    @Transactional(readOnly = true) 
    public InvoiceBean getInvoiceById(String invoiceNumber) {
        return getCurrentSession().get(InvoiceBean.class, invoiceNumber); 
    }
}
