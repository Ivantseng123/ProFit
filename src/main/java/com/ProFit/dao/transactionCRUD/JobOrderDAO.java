package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.transactionBean.JobOrderBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public class JobOrderDAO implements IJobOrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<JobOrderBean> getAllOrders() {
        Query<JobOrderBean> query = getCurrentSession().createQuery("from JobOrderBean order by jobOrderDate desc", JobOrderBean.class);
        return query.list();
    }

    @Override
    public void insertOrder(JobOrderBean order) {
        order.setJobOrdersId(UUID.randomUUID().toString());
        order.setJobOrderDate(new Timestamp(System.currentTimeMillis()));
        getCurrentSession().persist(order);
    }

    @Override
    public void updateOrder(JobOrderBean order) {
        getCurrentSession().update(order);
    }

    @Override
    public void deleteOrder(String jobOrdersId) {
        JobOrderBean order = getCurrentSession().get(JobOrderBean.class, jobOrdersId);
        if (order != null) {
            getCurrentSession().remove(order);
        }
    }

    @Override
    public JobOrderBean getOrderById(String jobOrdersId) {
        return getCurrentSession().get(JobOrderBean.class, jobOrdersId);
    }

    @Override
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

        Query<JobOrderBean> query = getCurrentSession().createQuery(hql.toString(), JobOrderBean.class);

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
