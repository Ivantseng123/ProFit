package com.ProFit.controller.transactions;

import com.ProFit.bean.transactionBean.JobOrderBean;
import com.ProFit.service.transactionService.IJobOrderService;
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
	private IJobOrderService jobOrderService;

    // 顯示所有訂單記錄
    @GetMapping
    public String listOrders(Model model) {
        List<JobOrderBean> orders = jobOrderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "transactionVIEW/jobOrders";  // 返回 JSP 頁面
    }

    // 根據條件篩選訂單
    @GetMapping("/search")
    public String searchOrders(
            @RequestParam(required = false) Integer jobApplicationId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String jobOrderStatus,
            Model model) {

        Timestamp startDateParsed = (startDate != null && !startDate.isEmpty()) ? Timestamp.valueOf(startDate + " 00:00:00") : null;
        Timestamp endDateParsed = (endDate != null && !endDate.isEmpty()) ? Timestamp.valueOf(endDate + " 23:59:59") : null;

        List<JobOrderBean> orders = jobOrderService.searchOrdersByCriteria(jobApplicationId, startDateParsed, endDateParsed, jobOrderStatus);
        model.addAttribute("orders", orders);  // 傳遞訂單結果給 JSP 頁面
        return "transactionVIEW/jobOrders";  // 返回同樣的 JSP 頁面
    }

    // 新增訂單
    @PostMapping("/insert")
    public String insertOrder(
            @RequestParam("job_application_id") Integer jobApplicationId,
            @RequestParam("job_order_status") String jobOrderStatus,
            @RequestParam("job_notes") String jobNotes, 
            @RequestParam("total_amount") Integer totalAmount) {

        // 創建訂單對象並設置屬性
        JobOrderBean order = new JobOrderBean(null, jobApplicationId, new Timestamp(System.currentTimeMillis()), jobOrderStatus, jobNotes, totalAmount);
        jobOrderService.insertOrder(order);
        return "redirect:/jobOrders";  // 新增成功後重定向到訂單列表
    }

    // 更新訂單
    @PostMapping("/update")
    public String updateOrder(
            @RequestParam("job_orders_id") String jobOrdersId,
            @RequestParam("job_application_id") Integer jobApplicationId,
            @RequestParam("job_order_status") String jobOrderStatus, 
            @RequestParam("job_notes") String jobNotes, 
            @RequestParam("total_amount") Integer totalAmount) {

        // 根據 ID 獲取訂單
        JobOrderBean order = jobOrderService.getOrderById(jobOrdersId);
        if (order != null) {
            // 更新訂單屬性
            order.setJobApplicationId(jobApplicationId);
            order.setJobOrderStatus(jobOrderStatus);
            order.setJobNotes(jobNotes);
            order.setJobAmount(totalAmount);
            jobOrderService.updateOrder(order);  // 保存更新
        }
        return "redirect:/jobOrders";  // 更新成功後重定向到訂單列表
    }

    // 刪除訂單
    @PostMapping("/delete")
    public String deleteOrder(@RequestParam("job_orders_id") String jobOrdersId) {
        jobOrderService.deleteOrder(jobOrdersId);  // 刪除訂單
        return "redirect:/jobOrders";  // 刪除成功後重定向
    }
}
