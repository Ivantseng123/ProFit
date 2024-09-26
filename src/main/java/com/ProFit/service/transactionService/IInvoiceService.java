package com.ProFit.service.transactionService;

import com.ProFit.bean.transactionBean.InvoiceBean;
import java.util.List;

public interface IInvoiceService {
    List<InvoiceBean> getAllInvoices();
    List<InvoiceBean> searchInvoices(String invoiceNumber, String invoiceStatus, String idType, String idValue);
    boolean insertInvoice(InvoiceBean invoice);
    boolean deleteInvoice(String invoiceNumber);
    InvoiceBean getInvoiceById(String invoiceNumber);
    boolean updateInvoice(InvoiceBean invoice);  // 添加更新方法
}