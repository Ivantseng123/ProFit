package com.ProFit.service.transactionService;

import com.ProFit.bean.transactionBean.JobOrderBean;
import com.ProFit.dao.transactionCRUD.JobOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class JobOrderService {

    @Autowired
    private JobOrderDAO jobOrderDAO; 

    // 顯示所有訂單記錄
    @Transactional(readOnly = true) 
    public List<JobOrderBean> getAllOrders() {
        return jobOrderDAO.getAllOrders(); 
    }

    // 新增訂單
    @Transactional
    public void insertOrder(JobOrderBean order) {
        jobOrderDAO.insertOrder(order);
    }

    // 更新訂單
    @Transactional
    public void updateOrder(JobOrderBean order) {
        jobOrderDAO.updateOrder(order); 
    }

    // 刪除訂單
    @Transactional
    public void deleteOrder(String jobOrdersId) {
        jobOrderDAO.deleteOrder(jobOrdersId);
    }

    // 根據ID獲取訂單
    @Transactional(readOnly = true)
    public JobOrderBean getOrderById(String jobOrdersId) {
        return jobOrderDAO.getOrderById(jobOrdersId);
    }

    // 根據條件篩選訂單
    @Transactional(readOnly = true)
    public List<JobOrderBean> searchOrdersByCriteria(Integer jobApplicationId, Timestamp startDate, Timestamp endDate, String jobOrderStatus) {
        return jobOrderDAO.searchOrdersByCriteria(jobApplicationId, startDate, endDate, jobOrderStatus);
    }
}
