package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.UserTransactionBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

public class UserTransactionDAO {

    private Session session;

    public UserTransactionDAO(Session session) {
        this.session = session;
    }

    public List<UserTransactionBean> getAllTransactions() {
        Query<UserTransactionBean> query = session.createQuery(
                "from UserTransactionBean order by transactionId desc", UserTransactionBean.class);
        return query.list();
    }

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
        hql.append("order by transactionId desc");

        Query<UserTransactionBean> query = session.createQuery(hql.toString(), UserTransactionBean.class);
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

    public boolean insertTransaction(UserTransactionBean transaction) {
        Transaction tx = session.beginTransaction();
        try {
            session.persist(transaction);
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

    public boolean updateTransaction(UserTransactionBean transaction) {
        Transaction tx = session.beginTransaction();
        try {
            session.update(transaction);
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

    public boolean deleteTransaction(String transactionId) {
        Transaction tx = session.beginTransaction();
        try {
            UserTransactionBean transaction = session.get(UserTransactionBean.class, transactionId);
            if (transaction != null) {
                session.remove(transaction);
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

    public UserTransactionBean getTransactionById(String transactionId) {
        return session.get(UserTransactionBean.class, transactionId);
    }
}
