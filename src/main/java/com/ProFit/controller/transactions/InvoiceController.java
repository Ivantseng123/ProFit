package com.ProFit.controller.transactions;

import com.ProFit.bean.transactionBean.InvoiceBean;
import com.ProFit.service.transactionService.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // 顯示所有發票
    @GetMapping
    public String listInvoices(Model model) {
        List<InvoiceBean> invoices = invoiceService.getAllInvoices();
        model.addAttribute("invoices", invoices);
        return "transactionVIEW/invoices";
    }

    // 根據條件篩選發票
    @PostMapping("/filter")
    public String filterInvoices(
            @RequestParam(required = false) String invoice_number,
            @RequestParam(required = false) String invoice_status,
            @RequestParam(required = false) String id_type,
            @RequestParam(required = false) String id_value,
            Model model) {

        List<InvoiceBean> invoices = invoiceService.searchInvoices(invoice_number, invoice_status, id_type, id_value);  // 調用 Service 層篩選發票
        model.addAttribute("invoices", invoices);
        return "transactionVIEW/invoices";
    }

    // 新增發票
    @PostMapping("/insert")
    public String insertInvoice(
            @RequestParam("invoice_number") String invoiceNumber,
            @RequestParam("invoice_amount") int invoiceAmount,
            @RequestParam("invoice_status") String invoiceStatus,
            @RequestParam("order_type") String orderType,
            @RequestParam("order_id") String orderId) { 

        InvoiceBean invoice = new InvoiceBean();
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceAmount(invoiceAmount);
        invoice.setInvoiceStatus(invoiceStatus);
        invoice.setIssuedDate(new Timestamp(System.currentTimeMillis()));

        switch (orderType) {
            case "transaction_id":
                invoice.setTransactionId(orderId);
                break;
            case "job_order_id":
                invoice.setJobOrderId(orderId);
                break;
            case "course_order_id":
                invoice.setCourseOrderId(orderId);
                break;
            case "event_order_id":
                invoice.setEventOrderId(orderId);
                break;
        }

        boolean isInserted = invoiceService.insertInvoice(invoice);
        return isInserted ? "redirect:/invoices" : "transactionVIEW/error"; 
    }

    // 刪除發票
    @PostMapping("/delete")
    public String deleteInvoice(@RequestParam("invoice_number") String invoiceNumber) {
        boolean isDeleted = invoiceService.deleteInvoice(invoiceNumber);
        return isDeleted ? "redirect:/invoices" : "transactionVIEW/error";
    }
}
