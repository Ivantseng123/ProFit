package com.ProFit.service.transactionService;

import com.ProFit.bean.transactionBean.UserTransactionBean;
import com.ProFit.dao.transactionCRUD.UserTransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class UserTransactionService {

    @Autowired
    private UserTransactionDAO transactionDAO;

    // 獲取所有交易記錄
    public List<UserTransactionBean> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    // 根據條件篩選交易記錄
    public List<UserTransactionBean> getTransactionsByFilters(Integer userId, String transactionType, String transactionStatus, Timestamp startDate, Timestamp endDate) {
        return transactionDAO.getTransactionsByFilters(userId, transactionType, transactionStatus, startDate, endDate);
    }

    // 新增交易
    public void insertTransaction(UserTransactionBean transaction) {
        transactionDAO.insertTransaction(transaction);
    }

    // 更新交易
    public void updateTransaction(UserTransactionBean transaction) {
        if ("completed".equals(transaction.getTransactionStatus())) {
            transaction.setCompletionAt(new Timestamp(System.currentTimeMillis()));
        }
        transactionDAO.updateTransaction(transaction);
    }

    // 刪除交易
    public void deleteTransaction(String transactionId) {
        transactionDAO.deleteTransaction(transactionId);
    }

    // 根據ID獲取交易
    public UserTransactionBean getTransactionById(String transactionId) {
        return transactionDAO.getTransactionById(transactionId);
    }
}
