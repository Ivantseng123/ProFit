package com.ProFit.controller.transactions;

import com.ProFit.bean.InvoiceBean;
import com.ProFit.dao.transactionCRUD.InvoiceDAO;
import org.hibernate.Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
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

        if ("filter".equals(action)) {
            filterInvoices(request, response);
        } else {
            listInvoices(request, response);
        }
    }

    private void filterInvoices(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String invoiceNumber = request.getParameter("invoice_number");
        String invoiceStatus = request.getParameter("invoice_status");
        String transactionId = request.getParameter("transaction_id");
        String jobOrderId = request.getParameter("job_order_id");
        String courseOrderId = request.getParameter("course_order_id");
        String eventOrderId = request.getParameter("event_order_id");

        List<InvoiceBean> invoices = invoiceDAO.searchInvoices(invoiceNumber, invoiceStatus, transactionId, jobOrderId, courseOrderId, eventOrderId);
        request.setAttribute("invoices", invoices);
        request.getRequestDispatcher("/transactionVIEW/invoices.jsp").forward(request, response);
    }

    private void listInvoices(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<InvoiceBean> invoices = invoiceDAO.getAllInvoices();
        request.setAttribute("invoices", invoices);
        request.getRequestDispatcher("/transactionVIEW/invoices.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        if (session != null) {
            session.close();
        }
    }
}
