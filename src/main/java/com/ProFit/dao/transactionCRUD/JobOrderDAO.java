package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.JobOrderBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class JobOrderDAO {

    private Session session;

    public JobOrderDAO(Session session) {
        this.session = session;
    }

    public List<JobOrderBean> getAllOrders() {
        Query<JobOrderBean> query = session.createQuery("from JobOrderBean order by jobOrderDate desc", JobOrderBean.class);
        return query.list();
    }

    public boolean insertOrder(JobOrderBean order) {
        Transaction tx = session.beginTransaction();
        try {
            order.setJobOrdersId(UUID.randomUUID().toString());  // 自動生成UUID作為主鍵
            order.setJobOrderDate(new Timestamp(System.currentTimeMillis()));  // 訂單日期
            session.persist(order);
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

    public boolean updateOrder(JobOrderBean order) {
        Transaction tx = session.beginTransaction();
        try {
            session.update(order);
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

    public boolean deleteOrder(String jobOrdersId) {
        Transaction tx = session.beginTransaction();
        try {
            JobOrderBean order = session.get(JobOrderBean.class, jobOrdersId);
            if (order != null) {
                session.remove(order);
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

    public JobOrderBean getOrderById(String jobOrdersId) {
        return session.get(JobOrderBean.class, jobOrdersId);
    }

    public List<JobOrderBean> searchOrdersByCriteria(Integer jobApplicationId, Timestamp startDate, Timestamp endDate, String jobOrderStatus) {
        StringBuilder hql = new StringBuilder("from JobOrderBean where 1=1");

        if (jobApplicationId != null) {
            hql.append(" and jobApplicationId = :jobApplicationId");
        }
        if (startDate != null) {
            hql.append(" and jobOrderDate >= :startDate");
        }
        if (endDate != null) {
            hql.append(" and jobOrderDate <= :endDate");
        }
        if (jobOrderStatus != null && !jobOrderStatus.isEmpty()) {
            hql.append(" and jobOrderStatus = :jobOrderStatus");
        }

        Query<JobOrderBean> query = session.createQuery(hql.toString(), JobOrderBean.class);

        if (jobApplicationId != null) {
            query.setParameter("jobApplicationId", jobApplicationId);
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        if (jobOrderStatus != null && !jobOrderStatus.isEmpty()) {
            query.setParameter("jobOrderStatus", jobOrderStatus);
        }

        return query.list();
    }
}
