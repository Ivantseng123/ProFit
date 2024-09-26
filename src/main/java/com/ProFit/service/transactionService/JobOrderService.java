package com.ProFit.service.transactionService;

import com.ProFit.bean.transactionBean.JobOrderBean;
import com.ProFit.dao.transactionCRUD.JobOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class JobOrderService implements IJobOrderService {
    @Autowired
    private JobOrderDAO jobOrderDAO;

    @Override
    @Transactional(readOnly = true)
    public List<JobOrderBean> getAllOrders() {
        return jobOrderDAO.getAllOrders();
    }

    @Override
    @Transactional
    public void insertOrder(JobOrderBean order) {
        jobOrderDAO.insertOrder(order);
    }

    @Override
    @Transactional
    public void updateOrder(JobOrderBean order) {
        jobOrderDAO.updateOrder(order);
    }

    @Override
    @Transactional
    public void deleteOrder(String jobOrdersId) {
        jobOrderDAO.deleteOrder(jobOrdersId);
    }

    @Override
    @Transactional(readOnly = true)
    public JobOrderBean getOrderById(String jobOrdersId) {
        return jobOrderDAO.getOrderById(jobOrdersId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobOrderBean> searchOrdersByCriteria(Integer jobApplicationId, Timestamp startDate, Timestamp endDate, String jobOrderStatus) {
        return jobOrderDAO.searchOrdersByCriteria(jobApplicationId, startDate, endDate, jobOrderStatus);
    }
}
