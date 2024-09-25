package com.ProFit.controller.transactions;

import com.ProFit.bean.transactionBean.UserTransactionBean;
import com.ProFit.service.transactionService.UserTransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class UserTransactionController {

    @Autowired
    private UserTransactionService transactionService;

    // 顯示所有交易記錄
    @GetMapping
    public String listTransactions(Model model) {
        List<UserTransactionBean> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transactions";  // 指向 JSP 頁面名稱
    }

    // 根據篩選條件查詢交易
    @PostMapping("/filter")
    public String filterTransactions(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String transactionType,
            @RequestParam(required = false) String transactionStatus,
            @RequestParam(required = false) Timestamp startDate,
            @RequestParam(required = false) Timestamp endDate,
            Model model) {

        List<UserTransactionBean> transactions = transactionService.getTransactionsByFilters(
                userId, transactionType, transactionStatus, startDate, endDate);
        model.addAttribute("transactions", transactions);
        return "transactions";
    }

    // 新增交易
    @PostMapping("/insert")
    public String insertTransaction(@ModelAttribute UserTransactionBean transaction) {
        transactionService.insertTransaction(transaction);
        return "redirect:/transactions";
    }

    // 更新交易
    @PostMapping("/update")
    public String updateTransaction(@ModelAttribute UserTransactionBean transaction) {
        transactionService.updateTransaction(transaction);
        return "redirect:/transactions";
    }

    // 刪除交易
    @PostMapping("/delete")
    public String deleteTransaction(@RequestParam String transactionId) {
        transactionService.deleteTransaction(transactionId);
        return "redirect:/transactions";
    }
}
