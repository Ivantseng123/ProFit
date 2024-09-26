package com.ProFit.service.transactionService;

import com.ProFit.bean.transactionBean.InvoiceBean;
import com.ProFit.dao.transactionCRUD.IInvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceDAO invoiceDAO;

    @Override
    public List<InvoiceBean> getAllInvoices() {
        return invoiceDAO.getAllInvoices();
    }

    @Override
    public List<InvoiceBean> searchInvoices(String invoiceNumber, String invoiceStatus, String idType, String idValue) {
        return invoiceDAO.searchInvoices(invoiceNumber, invoiceStatus, idType, idValue);
    }

    @Override
    public boolean insertInvoice(InvoiceBean invoice) {
        return invoiceDAO.insertInvoice(invoice);
    }

    @Override
    public boolean deleteInvoice(String invoiceNumber) {
        return invoiceDAO.deleteInvoice(invoiceNumber);
    }

    @Override
    public InvoiceBean getInvoiceById(String invoiceNumber) {
        return invoiceDAO.getInvoiceById(invoiceNumber);
    }

    @Override
    public boolean updateInvoice(InvoiceBean invoice) {
        return invoiceDAO.updateInvoice(invoice);
    }
}
