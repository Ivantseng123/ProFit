package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.transactionBean.UserTransactionBean;
import java.sql.Timestamp;
import java.util.List;

public interface IUserTransactionDAO {

    List<UserTransactionBean> getAllTransactions();

    List<UserTransactionBean> getTransactionsByFilters(String userId, String transactionType, String transactionStatus, Timestamp startDate, Timestamp endDate);

    void insertTransaction(UserTransactionBean transaction);

    void updateTransaction(UserTransactionBean transaction);

    void deleteTransaction(String transactionId);
}
