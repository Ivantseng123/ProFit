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

    // 按條件篩選發票
    public List<InvoiceBean> searchInvoices(String invoiceNumber, String invoiceStatus, String idType, String idValue) {
        StringBuilder hql = new StringBuilder("from InvoiceBean where 1=1 ");

        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            hql.append("and invoiceNumber = :invoiceNumber ");
        }
        if (invoiceStatus != null && !invoiceStatus.isEmpty()) {
            hql.append("and invoiceStatus = :invoiceStatus ");
        }

        // 根據選擇的 ID 類型添加篩選條件
        if (idType != null && idValue != null) {
            switch (idType) {
                case "transaction_id":
                    hql.append("and transactionId = :idValue ");
                    break;
                case "job_order_id":
                    hql.append("and jobOrderId = :idValue ");
                    break;
                case "course_order_id":
                    hql.append("and courseOrderId = :idValue ");
                    break;
                case "event_order_id":
                    hql.append("and eventOrderId = :idValue ");
                    break;
            }
        } else if (idType != null) {
            // 如果 idValue 是空的，但選擇了 ID 類型，那麼查詢該類型下的所有發票
            switch (idType) {
                case "transaction_id":
                    hql.append("and transactionId is not null ");
                    break;
                case "job_order_id":
                    hql.append("and jobOrderId is not null ");
                    break;
                case "course_order_id":
                    hql.append("and courseOrderId is not null ");
                    break;
                case "event_order_id":
                    hql.append("and eventOrderId is not null ");
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

    // 更新發票
    public boolean updateInvoice(InvoiceBean invoice) {
        Transaction tx = session.beginTransaction();
        try {
            session.update(invoice);
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
    public boolean deleteInvoice(String invoiceId) {
        Transaction tx = session.beginTransaction();
        try {
            InvoiceBean invoice = session.get(InvoiceBean.class, invoiceId);
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

    // 根據ID查找發票
    public InvoiceBean getInvoiceById(String invoiceId) {
        return session.get(InvoiceBean.class, invoiceId);
    }
}
