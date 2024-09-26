package com.ProFit.service.transactionService;

import com.ProFit.bean.transactionBean.UserTransactionBean;
import com.ProFit.dao.transactionCRUD.IUserTransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class UserTransactionService implements IUserTransactionService {

    @Autowired
    private IUserTransactionDAO userTransactionDAO;

    @Override
    public List<UserTransactionBean> getAllTransactions() {
        return userTransactionDAO.getAllTransactions();
    }

    @Override
    public List<UserTransactionBean> getTransactionsBySearchs(String userId, String transactionType, String transactionStatus, Timestamp startDate, Timestamp endDate) {
        return userTransactionDAO.getTransactionsByFilters(userId, transactionType, transactionStatus, startDate, endDate);
    }

    @Override
    public void insertTransaction(UserTransactionBean transaction) {
        if (transaction.getTransactionStatus() == null || transaction.getTransactionStatus().isEmpty()) {
            throw new IllegalArgumentException("交易狀態不能為空");
        }
        userTransactionDAO.insertTransaction(transaction);
    }

    @Override
    public void updateTransaction(UserTransactionBean transaction) {
        if (transaction.getTransactionStatus() == null || transaction.getTransactionStatus().isEmpty()) {
            throw new IllegalArgumentException("交易狀態不能為空");
        }
        userTransactionDAO.updateTransaction(transaction);
    }

    @Override
    public void deleteTransaction(String transactionId) {
        userTransactionDAO.deleteTransaction(transactionId);
    }
}
