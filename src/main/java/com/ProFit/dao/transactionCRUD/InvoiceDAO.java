package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.transactionBean.InvoiceBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceDAO implements IInvoiceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<InvoiceBean> getAllInvoices() {
        return getCurrentSession().createQuery("from InvoiceBean order by issuedDate desc", InvoiceBean.class).list();
    }

    @Override
    public List<InvoiceBean> searchInvoices(String invoiceNumber, String invoiceStatus, String idType, String idValue) {
        StringBuilder hql = new StringBuilder("from InvoiceBean where 1=1");

        // 發票號碼條件
        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            hql.append(" and invoiceNumber = :invoiceNumber");
        }

        // 發票狀態條件
        if (invoiceStatus != null && !invoiceStatus.isEmpty()) {
            hql.append(" and invoiceStatus = :invoiceStatus");
        }

        // ID類型處理邏輯
        if (idType != null && !idType.isEmpty()) {
            switch (idType) {
                case "transaction_id":
                    hql.append(" and transactionId is not null");
                    if (idValue != null && !idValue.isEmpty()) {
                        hql.append(" and transactionId = :idValue");
                    }
                    break;
                case "job_order_id":
                    hql.append(" and jobOrderId is not null");
                    if (idValue != null && !idValue.isEmpty()) {
                        hql.append(" and jobOrderId = :idValue");
                    }
                    break;
                case "course_order_id":
                    hql.append(" and courseOrderId is not null");
                    if (idValue != null && !idValue.isEmpty()) {
                        hql.append(" and courseOrderId = :idValue");
                    }
                    break;
                case "event_order_id":
                    hql.append(" and eventOrderId is not null");
                    if (idValue != null && !idValue.isEmpty()) {
                        hql.append(" and eventOrderId = :idValue");
                    }
                    break;
            }
        }

        var query = getCurrentSession().createQuery(hql.toString(), InvoiceBean.class);

        // 設置參數
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

    @Override
    public boolean insertInvoice(InvoiceBean invoice) {
        try {
            getCurrentSession().persist(invoice);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
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

    @Override
    public InvoiceBean getInvoiceById(String invoiceNumber) {
        return getCurrentSession().get(InvoiceBean.class, invoiceNumber);
    }

    @Override
    public boolean updateInvoice(InvoiceBean invoice) {
        try {
            getCurrentSession().merge(invoice);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
