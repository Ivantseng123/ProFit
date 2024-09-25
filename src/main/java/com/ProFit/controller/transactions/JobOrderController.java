package com.ProFit.controller.transactions;

import com.ProFit.bean.transactionBean.JobOrderBean;
import com.ProFit.service.transactionService.JobOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/jobOrders")
public class JobOrderController {

    @Autowired
    private JobOrderService jobOrderService;

    // 顯示所有訂單記錄
    @GetMapping
    public String listOrders(Model model) {
        List<JobOrderBean> orders = jobOrderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "transactionVIEW/jobOrders";
    }

    // 根據條件篩選訂單
    @PostMapping("/search")
    public String searchOrders(
            @RequestParam(required = false) Integer jobApplicationId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String jobOrderStatus,
            Model model) {

        Timestamp startDateParsed = (startDate != null && !startDate.isEmpty()) ? Timestamp.valueOf(startDate + " 00:00:00") : null;
        Timestamp endDateParsed = (endDate != null && !endDate.isEmpty()) ? Timestamp.valueOf(endDate + " 23:59:59") : null;

        List<JobOrderBean> orders = jobOrderService.searchOrdersByCriteria(jobApplicationId, startDateParsed, endDateParsed, jobOrderStatus);
        model.addAttribute("orders", orders); 
        return "transactionVIEW/jobOrders"; 
    }

    // 新增訂單
    @PostMapping("/insert")
    public String insertOrder(
            @RequestParam("job_application_id") Integer jobApplicationId,
            @RequestParam("job_order_status") String jobOrderStatus,
            @RequestParam("job_notes") String jobNotes, 
            @RequestParam("total_amount") Integer totalAmount) {

        JobOrderBean order = new JobOrderBean(null, jobApplicationId, new Timestamp(System.currentTimeMillis()), jobOrderStatus, jobNotes, totalAmount);
        jobOrderService.insertOrder(order);
        return "redirect:/jobOrders";
    }

    // 更新訂單
    @PostMapping("/update")
    public String updateOrder(
            @RequestParam("job_orders_id") String jobOrdersId,
            @RequestParam("job_application_id") Integer jobApplicationId,
            @RequestParam("job_order_status") String jobOrderStatus, 
            @RequestParam("job_notes") String jobNotes, 
            @RequestParam("total_amount") Integer totalAmount) { 

        JobOrderBean order = jobOrderService.getOrderById(jobOrdersId);
        if (order != null) {
            order.setJobApplicationId(jobApplicationId);
            order.setJobOrderStatus(jobOrderStatus);
            order.setJobNotes(jobNotes);
            order.setJobAmount(totalAmount);
            jobOrderService.updateOrder(order);
        }
        return "redirect:/jobOrders";
    }

    // 刪除訂單
    @PostMapping("/delete")
    public String deleteOrder(@RequestParam("job_orders_id") String jobOrdersId) {
        jobOrderService.deleteOrder(jobOrdersId);
        return "redirect:/jobOrders";
    }
}
