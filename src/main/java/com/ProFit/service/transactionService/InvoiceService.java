package com.ProFit.service.transactionService;

import com.ProFit.bean.transactionBean.InvoiceBean;
import com.ProFit.dao.transactionCRUD.InvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceDAO invoiceDAO;

    // 顯示所有發票記錄
    @Transactional(readOnly = true)
    public List<InvoiceBean> getAllInvoices() {
        return invoiceDAO.getAllInvoices();
    }

    // 根據條件篩選發票
    @Transactional(readOnly = true)
    public List<InvoiceBean> searchInvoices(String invoiceNumber, String invoiceStatus, String idType, String idValue) {
        return invoiceDAO.searchInvoices(invoiceNumber, invoiceStatus, idType, idValue);
    }

    // 新增發票
    @Transactional
    public boolean insertInvoice(InvoiceBean invoice) {
        return invoiceDAO.insertInvoice(invoice); 
    }

    // 刪除發票
    @Transactional
    public boolean deleteInvoice(String invoiceNumber) {
        return invoiceDAO.deleteInvoice(invoiceNumber);
    }

    // 根據發票號碼查詢發票
    @Transactional(readOnly = true)
    public InvoiceBean getInvoiceById(String invoiceNumber) {
        return invoiceDAO.getInvoiceById(invoiceNumber);
    }
}
