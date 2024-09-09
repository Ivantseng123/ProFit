package com.ProFit.controller.transactions;

import com.ProFit.bean.JobOrderBean;
import com.ProFit.dao.transactionCRUD.JobOrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/JobOrderServlet")
public class JobOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JobOrderDAO jobOrderDAO;
    private Session session;

    public JobOrderServlet() {
        this.session = com.ProFit.hibernateutil.HibernateUtil.getSessionFactory().openSession();
        this.jobOrderDAO = new JobOrderDAO(session);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || "list".equals(action)) {
            listOrders(request, response);
        } else if ("combinedSearch".equals(action)) {
            combinedSearch(request, response);
        } else if ("edit".equals(action)) {
            showUpdateForm(request, response);
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
                insertOrder(request, response);
                break;
            case "applyUpdate":
                applyUpdateOrder(request, response);
                break;
            case "delete":
                deleteOrder(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無效的操作");
                break;
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<JobOrderBean> orders = jobOrderDAO.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/transactionVIEW/jobOrders.jsp").forward(request, response);
    }

    private void combinedSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobApplicationIdStr = request.getParameter("job_application_id");
        String startDateStr = request.getParameter("start_date");
        String endDateStr = request.getParameter("end_date");
        String jobOrderStatus = request.getParameter("job_order_status");

        Integer jobApplicationId = null;
        if (jobApplicationIdStr != null && !jobApplicationIdStr.isEmpty()) {
            jobApplicationId = Integer.parseInt(jobApplicationIdStr);
        }

        Timestamp startDate = null;
        if (startDateStr != null && !startDateStr.isEmpty()) {
            startDate = Timestamp.valueOf(startDateStr + " 00:00:00");
        }

        Timestamp endDate = null;
        if (endDateStr != null && !endDateStr.isEmpty()) {
            endDate = Timestamp.valueOf(endDateStr + " 23:59:59");
        }

        List<JobOrderBean> orders = jobOrderDAO.searchOrdersByCriteria(jobApplicationId, startDate, endDate, jobOrderStatus);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/transactionVIEW/jobOrders.jsp").forward(request, response);
    }

    // 新增訂單功能
    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String jobApplicationId = request.getParameter("job_application_id");
            String jobOrderStatus = request.getParameter("job_order_status");
            String jobNotes = request.getParameter("job_notes");
            String totalAmountStr = request.getParameter("total_amount");

            int jobApplicationIdParsed = Integer.parseInt(jobApplicationId);
            int totalAmountParsed = Integer.parseInt(totalAmountStr);

            JobOrderBean order = new JobOrderBean(
                    null, jobApplicationIdParsed, new Timestamp(System.currentTimeMillis()),
                    jobOrderStatus, jobNotes, totalAmountParsed
            );

            jobOrderDAO.insertOrder(order);
            response.sendRedirect(request.getContextPath() + "/JobOrderServlet?action=list");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "新增訂單失敗");
        }
    }

    private void applyUpdateOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String jobOrdersId = request.getParameter("job_orders_id");
            String jobApplicationId = request.getParameter("job_application_id");
            String jobOrderStatus = request.getParameter("job_order_status");
            String jobNotes = request.getParameter("job_notes");
            String totalAmountStr = request.getParameter("total_amount");

            int jobApplicationIdParsed = Integer.parseInt(jobApplicationId);
            int totalAmountParsed = Integer.parseInt(totalAmountStr);

            JobOrderBean order = jobOrderDAO.getOrderById(jobOrdersId);
            if (order != null) {
                order.setJobApplicationId(jobApplicationIdParsed);
                order.setJobOrderStatus(jobOrderStatus);
                order.setJobNotes(jobNotes);
                order.setJobAmount(totalAmountParsed);

                jobOrderDAO.updateOrder(order);
                response.sendRedirect(request.getContextPath() + "/JobOrderServlet?action=list");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新訂單失敗");
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobOrdersId = request.getParameter("job_orders_id");
        boolean isDeleted = jobOrderDAO.deleteOrder(jobOrdersId);
        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/JobOrderServlet?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "刪除訂單失敗");
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobOrdersId = request.getParameter("job_orders_id");
        JobOrderBean order = jobOrderDAO.getOrderById(jobOrdersId);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/transactionVIEW/updateJobOrder.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        if (session != null) {
            session.close();
        }
    }
}
