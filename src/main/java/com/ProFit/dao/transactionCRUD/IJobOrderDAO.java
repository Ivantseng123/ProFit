package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.transactionBean.JobOrderBean;
import java.sql.Timestamp;
import java.util.List;

public interface IJobOrderDAO {

    List<JobOrderBean> getAllOrders();

    void insertOrder(JobOrderBean order);

    void updateOrder(JobOrderBean order);

    void deleteOrder(String jobOrdersId);

    JobOrderBean getOrderById(String jobOrdersId);

    List<JobOrderBean> searchOrdersByCriteria(Integer jobApplicationId, Timestamp startDate, Timestamp endDate, String jobOrderStatus);
}
