package com.ProFit.controller.transactions;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.ProFit.bean.UserTransactionBean;
import com.ProFit.dao.transactionCRUD.UserTransactionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

@WebServlet("/UserTransactionServlet")
public class UserTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserTransactionDAO userTransactionDAO;
    private Session session;

    public UserTransactionServlet() {
        this.session = com.ProFit.hibernateutil.HibernateUtil.getSessionFactory().openSession();
        this.userTransactionDAO = new UserTransactionDAO(session);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || "list".equals(action)) {
            listTransactions(request, response);
        } else if ("filter".equals(action)) {
            filterTransactions(request, response);
        } else if ("edit".equals(action)) {
            showUpdateForm(request, response);
        } else if ("delete".equals(action)) {
            showDeleteConfirmation(request, response);
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
                insertTransaction(request, response);
                break;
            case "applyUpdate":
                applyUpdateTransaction(request, response);
                break;
            case "delete":
                deleteTransaction(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無效的操作");
                break;
        }
    }

    private void listTransactions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserTransactionBean> transactions = userTransactionDAO.getAllTransactions();
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("/transactionVIEW/userTransactions.jsp").forward(request, response);
    }

    private void filterTransactions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDateStr = request.getParameter("start_date");
        String endDateStr = request.getParameter("end_date");
        String userId = request.getParameter("user_id");
        String transactionType = request.getParameter("transaction_type");
        String transactionStatus = request.getParameter("transaction_status");

        Timestamp startDate = null;
        Timestamp endDate = null;

        try {
            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = Timestamp.valueOf(startDateStr + " 00:00:00");
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = Timestamp.valueOf(endDateStr + " 23:59:59");
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "日期格式不正確，請輸入yyyy-MM-dd格式的日期");
            return;
        }

        List<UserTransactionBean> transactions = userTransactionDAO.getTransactionsByFilters(
                userId, transactionType, transactionStatus, startDate, endDate);

        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("/transactionVIEW/userTransactions.jsp").forward(request, response);
    }

    private void insertTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("user_id");
        String transactionType = request.getParameter("transaction_type");
        int amount = Integer.parseInt(request.getParameter("amount")); 
        String transactionStatus = request.getParameter("transaction_status");
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        UserTransactionBean transaction = new UserTransactionBean(null, Integer.parseInt(userId), transactionType, amount, transactionStatus, createdAt);

        boolean isInserted = userTransactionDAO.insertTransaction(transaction);

        if (isInserted) {
            response.sendRedirect(request.getContextPath() + "/UserTransactionServlet?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "新增交易失敗");
        }
    }

    private void applyUpdateTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String transactionId = request.getParameter("transaction_id");
        String userId = request.getParameter("user_id");
        String transactionType = request.getParameter("transaction_type");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String transactionStatus = request.getParameter("transaction_status");
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        UserTransactionBean transaction = new UserTransactionBean(transactionId, Integer.parseInt(userId), transactionType, amount, transactionStatus, createdAt);

        boolean isUpdated = userTransactionDAO.updateTransaction(transaction);

        if (isUpdated) {
            response.sendRedirect(request.getContextPath() + "/UserTransactionServlet?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新交易失敗");
        }
    }

    private void deleteTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String transactionId = request.getParameter("transaction_id");

        boolean isDeleted = userTransactionDAO.deleteTransaction(transactionId);
        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/UserTransactionServlet?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "刪除交易失敗");
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String transactionId = request.getParameter("transaction_id");
        request.setAttribute("transaction_id", transactionId);
        request.getRequestDispatcher("/transactionVIEW/updateTransaction.jsp").forward(request, response);
    }

    private void showDeleteConfirmation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String transactionId = request.getParameter("transaction_id");
        request.setAttribute("transaction_id", transactionId);
        request.getRequestDispatcher("/transactionVIEW/deleteTransaction.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        if (session != null) {
            session.close();
        }
    }
}
