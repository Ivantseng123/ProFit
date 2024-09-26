package com.ProFit.controller.transactions;

import com.ProFit.bean.transactionBean.UserTransactionBean;
import com.ProFit.service.transactionService.IUserTransactionService;
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
    private IUserTransactionService userTransactionService;

    // 顯示所有交易記錄
    @GetMapping("/list")
    public String listTransactions(Model model) {
        List<UserTransactionBean> transactions = userTransactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transactionVIEW/userTransactions"; // 將視圖名映射到 userTransactions.jsp
    }

    // 根據篩選條件查詢交易記錄
    @GetMapping("/search")
    public String searchTransactions(@RequestParam String userId,
                                     @RequestParam String transactionType,
                                     @RequestParam String transactionStatus,
                                     @RequestParam String startDate,
                                     @RequestParam String endDate,
                                     Model model) {
        Timestamp start = null;
        Timestamp end = null;

        if (startDate != null && !startDate.isEmpty()) {
            start = Timestamp.valueOf(startDate + " 00:00:00");
        }
        if (endDate != null && !endDate.isEmpty()) {
            end = Timestamp.valueOf(endDate + " 23:59:59");
        }

        List<UserTransactionBean> transactions = userTransactionService.getTransactionsBySearchs(userId, transactionType, transactionStatus, start, end);
        model.addAttribute("transactions", transactions);
        return "transactionVIEW/userTransactions";
    }

    // 插入新交易
    @PostMapping("/insert")
    public String insertTransaction(@ModelAttribute UserTransactionBean transaction, Model model) {
        if (transaction.getTransactionType() == null || transaction.getTransactionType().isEmpty()) {
            model.addAttribute("error", "交易類型不能為空");
            return "transactionVIEW/userTransactions";
        }
        if (transaction.getTransactionAmount() <= 0) {
            model.addAttribute("error", "交易金額必須大於 0");
            return "transactionVIEW/userTransactions";
        }
        if (transaction.getTransactionStatus() == null || transaction.getTransactionStatus().isEmpty()) {
            model.addAttribute("error", "交易狀態不能為空");
            return "transactionVIEW/userTransactions";
        }

        // 設置創建時間
        transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userTransactionService.insertTransaction(transaction);
        return "redirect:/transactions/list";
    }


    // 刪除交易
    @PostMapping("/delete")
    public String deleteTransaction(@RequestParam String transactionId) {
        userTransactionService.deleteTransaction(transactionId);
        return "redirect:/transactions/list";
    }
    // 更新交易
    @PostMapping("/update")
    public String updateTransaction(@ModelAttribute UserTransactionBean transaction, Model model) {
        if (transaction.getTransactionAmount() <= 0) {
            model.addAttribute("error", "交易金額必須大於 0");
            return "transactionVIEW/userTransactions";
        }

        userTransactionService.updateTransaction(transaction);
        return "redirect:/transactions/list";
    }

   
}
