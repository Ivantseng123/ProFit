<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ProFit.bean.JobOrderBean, com.ProFit.dao.transactionCRUD.JobOrderDAO" %>
<%
    String jobOrdersId = request.getParameter("job_orders_id");
    JobOrderDAO jobOrderDAO = new JobOrderDAO();
    JobOrderBean order = jobOrderDAO.getOrderById(jobOrdersId);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>更新職缺訂單</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/transactionVIEW/job.css"> 
</head>
<body>
    <jsp:include page="/model/header&sidebar.jsp" />

    <main>
        <div class="container mt-4">
            <h2 class="mb-4">更新職缺訂單</h2>
            <form method="post" action="${pageContext.request.contextPath}/JobOrderServlet">
                <input type="hidden" name="action" value="applyUpdate">
                <input type="hidden" name="job_orders_id" value="<%= order.getJobOrdersId() %>">
                
                <div class="mb-3">
                    <label for="jobApplicationId" class="form-label">職缺申請ID:</label>
                    <input type="number" class="form-control" id="jobApplicationId" name="job_application_id" value="<%= order.getJobApplicationId() %>" required>
                </div>
                
                <div class="mb-3">
                    <label for="jobOrderStatus" class="form-label">訂單狀態:</label>
                    <select class="form-select" id="jobOrderStatus" name="job_order_status" required>
                        <option value="Processing" <%= "Processing".equals(order.getJobOrderStatus()) ? "selected" : "" %>>處理中</option>
                        <option value="Completed" <%= "Completed".equals(order.getJobOrderStatus()) ? "selected" : "" %>>完成</option>
                        <option value="Canceled" <%= "Canceled".equals(order.getJobOrderStatus()) ? "selected" : "" %>>取消</option>
                    </select>
                </div>
                
                <div class="mb-3">
                    <label for="jobNotes" class="form-label">訂單備註:</label>
                    <textarea class="form-control" id="jobNotes" name="job_notes"><%= order.getJobNotes() %></textarea>
                </div>
                
                <div class="mb-3">
                    <label for="totalAmount" class="form-label">訂單總金額:</label>
                    <input type="number" class="form-control" id="totalAmount" name="total_amount" value="<%= order.getTotalAmount() %>" required>
                </div>
                
                <button type="submit" class="btn btn-primary">更新</button>
            </form>
        </div>
    </main>
</body>
</html>
