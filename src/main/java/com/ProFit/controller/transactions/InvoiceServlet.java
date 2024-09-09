package com.ProFit.controller.transactions;

import com.ProFit.bean.InvoiceBean;
import com.ProFit.dao.transactionCRUD.InvoiceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InvoiceDAO invoiceDAO;
    private Session session;

    public InvoiceServlet() {
        this.session = com.ProFit.hibernateutil.HibernateUtil.getSessionFactory().openSession();
        this.invoiceDAO = new InvoiceDAO(session);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || "list".equals(action)) {
            listInvoices(request, response);
        } else if ("filter".equals(action)) {
            filterInvoices(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無效的操作");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                insertInvoice(request, response);
                break;
            case "applyUpdate":
                applyUpdateInvoice(request, response);
                break;
            case "delete":
                deleteInvoice(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無效的操作");
                break;
        }
    }

    private void listInvoices(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<InvoiceBean> invoices = invoiceDAO.getAllInvoices();
        request.setAttribute("invoices", invoices);
        request.getRequestDispatcher("/transactionVIEW/invoices.jsp").forward(request, response);
    }

    private void filterInvoices(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String invoiceNumber = request.getParameter("invoice_number");
        String invoiceStatus = request.getParameter("invoice_status");
        String idType = request.getParameter("id_type");
        String idValue = request.getParameter("id_value");

        if (idValue == null || idValue.isEmpty()) {
            idValue = null; 
        }

        List<InvoiceBean> invoices = invoiceDAO.searchInvoices(invoiceNumber, invoiceStatus, idType, idValue);
        request.setAttribute("invoices", invoices);
        request.getRequestDispatcher("/transactionVIEW/invoices.jsp").forward(request, response);
    }

    private void insertInvoice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String invoiceNumber = request.getParameter("invoice_number");
            int invoiceAmount = Integer.parseInt(request.getParameter("invoice_amount"));
            String invoiceStatus = request.getParameter("invoice_status");
            Timestamp issuedDate = new Timestamp(System.currentTimeMillis());

            InvoiceBean invoice = new InvoiceBean(null, null, null, null, null, invoiceNumber, invoiceAmount, issuedDate, invoiceStatus);
            invoiceDAO.insertInvoice(invoice);

            response.sendRedirect(request.getContextPath() + "/InvoiceServlet?action=list");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "新增發票失敗");
        }
    }

    private void applyUpdateInvoice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String invoiceId = request.getParameter("invoice_id");
            String invoiceNumber = request.getParameter("invoice_number");
            int invoiceAmount = Integer.parseInt(request.getParameter("invoice_amount"));
            String invoiceStatus = request.getParameter("invoice_status");

            String orderType = request.getParameter("order_type");
            String orderId = request.getParameter("order_id");

            InvoiceBean invoice = invoiceDAO.getInvoiceById(invoiceId);
            if (invoice != null) {
                invoice.setInvoiceNumber(invoiceNumber);
                invoice.setInvoiceAmount(invoiceAmount);
                invoice.setInvoiceStatus(invoiceStatus);

                if ("transaction_id".equals(orderType)) {
                    invoice.setTransactionId(orderId);
                } else if ("job_order_id".equals(orderType)) {
                    invoice.setJobOrderId(orderId);
                } else if ("course_order_id".equals(orderType)) {
                    invoice.setCourseOrderId(orderId);
                } else if ("event_order_id".equals(orderType)) {
                    invoice.setEventOrderId(orderId);
                }

                invoiceDAO.updateInvoice(invoice);
                response.sendRedirect(request.getContextPath() + "/InvoiceServlet?action=list");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新發票失敗");
        }
    }

    private void deleteInvoice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String invoiceId = request.getParameter("invoice_id");
        boolean isDeleted = invoiceDAO.deleteInvoice(invoiceId);
        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/InvoiceServlet?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "刪除發票失敗");
        }
    }
}
