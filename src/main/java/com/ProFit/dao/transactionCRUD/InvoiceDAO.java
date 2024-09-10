package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.InvoiceBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class InvoiceDAO {

    private Session session;

    public InvoiceDAO(Session session) {
        this.session = session;
    }

    // 查詢所有發票
    public List<InvoiceBean> getAllInvoices() {
        Query<InvoiceBean> query = session.createQuery("from InvoiceBean order by issuedDate desc", InvoiceBean.class);
        return query.list();
    }

    // 根據查詢條件搜索發票
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
                    if (idValue != null) {
                        hql.append("and transactionId = :idValue ");
                    } else {
                        hql.append("and transactionId is not null ");
                    }
                    break;
                case "job_order_id":
                    if (idValue != null) {
                        hql.append("and jobOrderId = :idValue ");
                    } else {
                        hql.append("and jobOrderId is not null ");
                    }
                    break;
                case "course_order_id":
                    if (idValue != null) {
                        hql.append("and courseOrderId = :idValue ");
                    } else {
                        hql.append("and courseOrderId is not null ");
                    }
                    break;
                case "event_order_id":
                    if (idValue != null) {
                        hql.append("and eventOrderId = :idValue ");
                    } else {
                        hql.append("and eventOrderId is not null ");
                    }
                    break;
            }
        }

        Query<InvoiceBean> query = session.createQuery(hql.toString(), InvoiceBean.class);

        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            query.setParameter("invoiceNumber", invoiceNumber);
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
    public boolean insertInvoice(InvoiceBean invoice) {
        Transaction tx = session.beginTransaction();
        try {
            session.persist(invoice);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // 刪除發票
    public boolean deleteInvoice(String invoiceNumber) {
        Transaction tx = session.beginTransaction();
        try {
            InvoiceBean invoice = session.get(InvoiceBean.class, invoiceNumber);
            if (invoice != null) {
                session.remove(invoice);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // 根據發票號碼查找發票（在刪除時可能需要）
    public InvoiceBean getInvoiceById(String invoiceNumber) {
        return session.get(InvoiceBean.class, invoiceNumber);
    }
}
