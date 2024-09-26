package com.ProFit.service.transactionService;

import com.ProFit.bean.transactionBean.UserTransactionBean;
import java.sql.Timestamp;
import java.util.List;

public interface IUserTransactionService {

    List<UserTransactionBean> getAllTransactions();

    List<UserTransactionBean> getTransactionsBySearchs(String userId, String transactionType, String transactionStatus, Timestamp start, Timestamp end);

    void insertTransaction(UserTransactionBean transaction);

    void updateTransaction(UserTransactionBean transaction);

    void deleteTransaction(String transactionId);
}
