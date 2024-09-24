package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.transactionBean.JobOrderBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public class JobOrderDAO {

    @Autowired
    private SessionFactory sessionFactory; 

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession(); 
    }

    // 顯示所有訂單記錄
    @Transactional(readOnly = true) 
    public List<JobOrderBean> getAllOrders() {
        Query<JobOrderBean> query = getCurrentSession().createQuery("from JobOrderBean order by jobOrderDate desc", JobOrderBean.class);
        return query.list(); 
    }

    // 新增訂單
    @Transactional 
    public boolean insertOrder(JobOrderBean order) {
        try {
            order.setJobOrdersId(UUID.randomUUID().toString()); 
            order.setJobOrderDate(new Timestamp(System.currentTimeMillis()));
            getCurrentSession().persist(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); 
            return false; 
        }
    }

    // 更新訂單
    @Transactional 
    public boolean updateOrder(JobOrderBean order) {
        try {
            getCurrentSession().update(order); 
            return true;
        } catch (Exception e) {
            e.printStackTrace(); 
            return false; 
        }
    }

    // 刪除訂單
    @Transactional
    public boolean deleteOrder(String jobOrdersId) {
        try {
            JobOrderBean order = getCurrentSession().get(JobOrderBean.class, jobOrdersId);
            if (order != null) {
                getCurrentSession().remove(order);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根據ID獲取訂單
    @Transactional(readOnly = true)
    public JobOrderBean getOrderById(String jobOrdersId) {
        return getCurrentSession().get(JobOrderBean.class, jobOrdersId);
    }

    // 根據條件篩選訂單
    @Transactional(readOnly = true)
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

        // 創建查詢並設置參數
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
