package com.ProFit.controller.transactions;

import com.ProFit.bean.transactionBean.InvoiceBean;
import com.ProFit.service.transactionService.IInvoiceService;
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
	private IInvoiceService invoiceService;

	// 查詢發票
	@GetMapping("/search")
	public String searchInvoices(@RequestParam(value = "invoice_number", required = false) String invoiceNumber,
			@RequestParam(value = "invoice_status", required = false) String invoiceStatus,
			@RequestParam(value = "id_type", required = false) String idType,
			@RequestParam(value = "id_value", required = false) String idValue, Model model) {

		// 打印參數以檢查值是否正確
		System.out.println("Invoice Number: " + invoiceNumber);
		System.out.println("Invoice Status: " + invoiceStatus);
		System.out.println("ID Type: " + idType);
		System.out.println("ID Value: " + idValue);

		// 呼叫服務層進行查詢
		List<InvoiceBean> invoices = invoiceService.searchInvoices(invoiceNumber, invoiceStatus, idType, idValue);
		model.addAttribute("invoices", invoices);

		if (invoices.isEmpty()) {
			model.addAttribute("error", "沒有找到符合條件的發票");
		}

		return "transactionVIEW/invoices";
	}

	// 新增發票
	@PostMapping("/insert")
	public String insertInvoice(@RequestParam("invoice_number") String invoiceNumber,
			@RequestParam("invoice_amount") int invoiceAmount, @RequestParam("invoice_status") String invoiceStatus,
			@RequestParam("order_type") String orderType, @RequestParam("order_id") String orderId, Model model) {

		InvoiceBean invoice = new InvoiceBean();
		invoice.setInvoiceNumber(invoiceNumber);
		invoice.setInvoiceAmount(invoiceAmount);
		invoice.setInvoiceStatus(invoiceStatus);
		invoice.setIssuedDate(new Timestamp(System.currentTimeMillis()));

		// 設定訂單類型和ID
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
		if (isInserted) {
			return "redirect:/invoices/search";
		} else {
			model.addAttribute("errorMessage", "新增發票失敗");
			return "transactionVIEW/error";
		}
	}

	// 更新發票
	@PostMapping("/update")
	public String updateInvoice(@RequestParam("invoice_number") String invoiceNumber,
			@RequestParam("invoice_amount") int invoiceAmount, @RequestParam("invoice_status") String invoiceStatus,
			Model model) {

		// 根據發票號碼查詢發票
		InvoiceBean invoice = invoiceService.getInvoiceById(invoiceNumber);

		// 如果發票存在，則進行更新
		if (invoice != null) {
			invoice.setInvoiceAmount(invoiceAmount);
			invoice.setInvoiceStatus(invoiceStatus);
			invoiceService.updateInvoice(invoice); // 更新發票資料
		}

		return "redirect:/invoices/search"; // 更新成功後重新導向查詢頁面
	}

	// 刪除發票
	@PostMapping("/delete")
	public String deleteInvoice(@RequestParam("invoice_number") String invoiceNumber) {
		invoiceService.deleteInvoice(invoiceNumber);
		return "redirect:/invoices/search";
	}

}
