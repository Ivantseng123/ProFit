package com.ProFit.dao.transactionCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ProFit.bean.transactionBean.UserTransactionBean;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserTransactionDAO implements IUserTransactionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    // 獲取當前的 Hibernate Session
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<UserTransactionBean> getAllTransactions() {
        return getCurrentSession().createQuery("from UserTransactionBean order by createdAt desc", UserTransactionBean.class).list();
    }

    @Override
    public List<UserTransactionBean> getTransactionsByFilters(String userId, String transactionType, String transactionStatus, Timestamp startDate, Timestamp endDate) {
        StringBuilder hql = new StringBuilder("from UserTransactionBean where 1=1 ");

        if (userId != null && !userId.isEmpty()) {
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

        var query = getCurrentSession().createQuery(hql.toString(), UserTransactionBean.class);

        if (userId != null && !userId.isEmpty()) {
            query.setParameter("userId", Integer.parseInt(userId));
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

    @Override
    public void insertTransaction(UserTransactionBean transaction) {
        if (transaction.getTransactionStatus() == null || transaction.getTransactionStatus().isEmpty()) {
            throw new IllegalArgumentException("交易狀態不能為空");
        }
        getCurrentSession().save(transaction);  // 插入時確保狀態存在
    }

    @Override
    public void updateTransaction(UserTransactionBean transaction) {
        if (transaction.getTransactionStatus() == null || transaction.getTransactionStatus().isEmpty()) {
            throw new IllegalArgumentException("交易狀態不能為空");
        }
        getCurrentSession().merge(transaction);  // 更新時確保狀態存在
    }

    @Override
    public void deleteTransaction(String transactionId) {
        UserTransactionBean transaction = getCurrentSession().get(UserTransactionBean.class, transactionId);
        if (transaction != null) {
            getCurrentSession().delete(transaction);
        }
    }
}
