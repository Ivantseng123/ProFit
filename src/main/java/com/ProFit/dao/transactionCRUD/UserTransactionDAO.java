package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.transactionBean.UserTransactionBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository  
public class UserTransactionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    // 獲取所有交易記錄
    public List<UserTransactionBean> getAllTransactions() {
        return getCurrentSession().createQuery("from UserTransactionBean order by createdAt desc", UserTransactionBean.class).list();
    }

    // 按條件篩選交易記錄
    public List<UserTransactionBean> getTransactionsByFilters(Integer userId, String transactionType, String transactionStatus, Timestamp startDate, Timestamp endDate) {
        StringBuilder hql = new StringBuilder("from UserTransactionBean where 1=1 ");

        if (userId != null) {
            hql.append("and userId = :userId ");
        }
        if (transactionType != null && !transactionType.isEmpty()) {
            hql.append("and transactionType = :transactionType ");
        }
        if (transactionStatus != null && !transactionStatus.isEmpty()) {
            hql.append("and transactionStatus = :transactionStatus ");
        }
        if (startDate != null) {
            hql.append("and createdAt >= :startDate ");
        }
        if (endDate != null) {
            hql.append("and createdAt <= :endDate ");
        }
        hql.append("order by createdAt desc");

        var query = getCurrentSession().createQuery(hql.toString(), UserTransactionBean.class);

        if (userId != null) {
            query.setParameter("userId", userId);
        }
        if (transactionType != null && !transactionType.isEmpty()) {
            query.setParameter("transactionType", transactionType);
        }
        if (transactionStatus != null && !transactionStatus.isEmpty()) {
            query.setParameter("transactionStatus", transactionStatus);
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }

        return query.list();
    }

    // 插入交易
    public boolean insertTransaction(UserTransactionBean transaction) {
        Transaction tx = getCurrentSession().beginTransaction();
        try {
            getCurrentSession().save(transaction);
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

    // 更新交易
    public boolean updateTransaction(UserTransactionBean transaction) {
        Transaction tx = getCurrentSession().beginTransaction();
        try {
            if ("completed".equals(transaction.getTransactionStatus())) {
                transaction.setCompletionAt(new Timestamp(System.currentTimeMillis()));
            }
            getCurrentSession().update(transaction);  // 使用 update 而不是 merge
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

    // 刪除交易
    public boolean deleteTransaction(String transactionId) {
        Transaction tx = getCurrentSession().beginTransaction();
        try {
            UserTransactionBean transaction = getCurrentSession().get(UserTransactionBean.class, transactionId);
            if (transaction != null) {
                getCurrentSession().delete(transaction);
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

    // 根據ID獲取交易
    public UserTransactionBean getTransactionById(String transactionId) {
        return getCurrentSession().get(UserTransactionBean.class, transactionId);
    }
}
