package com.ProFit.controller.transactions;

import com.ProFit.bean.JobOrderBean;
import com.ProFit.dao.transactionCRUD.JobOrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/JobOrderServlet")
public class JobOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JobOrderDAO jobOrderDAO;

    public JobOrderServlet() {
        this.jobOrderDAO = new JobOrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || "list".equals(action)) {
            listOrders(request, response);
        } else if ("searchByApplicationId".equals(action)) {
            searchByApplicationId(request, response);
        } else if ("searchByDate".equals(action)) {
            searchByDate(request, response);
        } else if ("searchByStatus".equals(action)) {
            searchByStatus(request, response);
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

    private void searchByApplicationId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobApplicationIdStr = request.getParameter("job_application_id");
        int jobApplicationId = -1;
        if (jobApplicationIdStr != null && !jobApplicationIdStr.isEmpty()) {
            try {
                jobApplicationId = Integer.parseInt(jobApplicationIdStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "職缺申請ID格式不正確");
                return;
            }
        }
        List<JobOrderBean> orders = jobOrderDAO.searchOrdersByApplicationId(jobApplicationId);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/transactionVIEW/jobOrders.jsp").forward(request, response);
    }

    private void searchByDate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDateStr = request.getParameter("start_date");
        String endDateStr = request.getParameter("end_date");

        Timestamp startDate = null;
        Timestamp endDate = null;
        if (startDateStr != null && !startDateStr.isEmpty()) {
            startDate = Timestamp.valueOf(startDateStr + " 00:00:00");
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            endDate = Timestamp.valueOf(endDateStr + " 23:59:59");
        }

        List<JobOrderBean> orders = jobOrderDAO.searchOrdersByDate(startDate, endDate);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/transactionVIEW/jobOrders.jsp").forward(request, response);
    }

    private void searchByStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobOrderStatus = request.getParameter("job_order_status");
        List<JobOrderBean> orders = jobOrderDAO.searchOrdersByStatus(jobOrderStatus);
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
            try {
                jobApplicationId = Integer.parseInt(jobApplicationIdStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "職缺申請ID格式不正確");
                return;
            }
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

    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String jobApplicationId = request.getParameter("job_application_id");
            String jobOrderStatus = request.getParameter("job_order_status");
            String jobNotes = request.getParameter("job_notes");
            String totalAmountStr = request.getParameter("total_amount");

            int jobApplicationIdParsed = Integer.parseInt(jobApplicationId);
            int totalAmountParsed = Integer.parseInt(totalAmountStr);

            Timestamp jobOrderDate = new Timestamp(System.currentTimeMillis());

            JobOrderBean order = new JobOrderBean(
                null, 
                jobApplicationIdParsed,
                jobOrderDate,
                jobOrderStatus,
                jobNotes,
                totalAmountParsed
            );

            jobOrderDAO.insertOrder(order);
            response.sendRedirect(request.getContextPath() + "/JobOrderServlet?action=list");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "數字格式不正確。請確認所有數字輸入皆為正確數字。");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "新增訂單失敗。");
        }
    }

    private void applyUpdateOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobOrdersId = request.getParameter("job_orders_id");
        String jobApplicationId = request.getParameter("job_application_id");
        String jobOrderStatus = request.getParameter("job_order_status");
        String jobNotes = request.getParameter("job_notes");
        String totalAmountStr = request.getParameter("total_amount");

        try {
            int jobApplicationIdParsed = Integer.parseInt(jobApplicationId);
            int totalAmountParsed = Integer.parseInt(totalAmountStr);

            
            Timestamp jobOrderTimestamp = new Timestamp(System.currentTimeMillis());

            
            JobOrderBean existingOrder = jobOrderDAO.getOrderById(jobOrdersId);
            if (existingOrder == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "找不到此訂單。");
                return;
            }

           
            if (!"Processing".equals(existingOrder.getJobOrderStatus())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "僅允許更新狀態為 'Processing' 的訂單。");
                return;
            }

            JobOrderBean order = new JobOrderBean(
                jobOrdersId,
                jobApplicationIdParsed,
                jobOrderTimestamp,  
                jobOrderStatus,
                jobNotes,
                totalAmountParsed
            );

            boolean isUpdated = jobOrderDAO.updateOrder(order);

            if (isUpdated) {
                response.sendRedirect(request.getContextPath() + "/JobOrderServlet?action=list");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新訂單失敗");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "數字格式不正確。請確認所有數字輸入皆為正確數字。");
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobOrdersId = request.getParameter("job_orders_id");

        // 取得訂單的現有狀態
        JobOrderBean existingOrder = jobOrderDAO.getOrderById(jobOrdersId);
        if (existingOrder == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "找不到此訂單。");
            return;
        }

        // 僅在狀態為 Processing 時才允許刪除
        if (!"Processing".equals(existingOrder.getJobOrderStatus())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "僅允許刪除狀態為 'Processing' 的訂單。");
            return;
        }

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
}
