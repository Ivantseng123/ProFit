<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>職缺訂單管理</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/transactionVIEW/jobOrder.css">
</head>
<body>
    <!-- 包含header和sidebar -->
    <jsp:include page="../model/header&sidebar.jsp" />

    <!-- 主內容區域 -->
    <main>
        <div class="course-header">
            <h2>職缺訂單管理</h2>
        </div>

        <!-- 查詢表單 -->
        <div class="dashboard-header">
            <form method="get" action="${pageContext.request.contextPath}/JobOrderServlet">
                <input type="hidden" name="action" value="combinedSearch">
                <div class="row mb-3">
                    <!-- 職缺申請ID -->
                    <div class="col-md-4">
                        <label for="job_application_id">職缺申請ID:</label>
                        <input type="number" id="job_application_id" name="job_application_id" class="form-control">
                    </div>
                    <!-- 訂單日期範圍 -->
                    <div class="col-md-4">
                        <label for="start_date">訂單日期從:</label>
                        <input type="date" id="start_date" name="start_date" class="form-control">
                    </div>
                    <div class="col-md-4">
                        <label for="end_date">到:</label>
                        <input type="date" id="end_date" name="end_date" class="form-control">
                    </div>
                </div>

                <div class="row mb-3">
                    <!-- 訂單狀態 -->
                    <div class="col-md-6">
                        <label for="job_order_status">訂單狀態:</label>
                        <select id="job_order_status" name="job_order_status" class="form-select">
                            <option value="">全部</option>
                            <option value="Processing">處理中</option>
                            <option value="Completed">完成</option>
                            <option value="Canceled">取消</option>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-12 text-center">
                        <input type="submit" value="查詢" class="btn btn-primary">
                    </div>
                </div>
            </form>
        </div>

        <!-- 訂單表格 -->
        <div class="table-container">
            <table id="ordersTable" class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th>職缺訂單ID</th>
                        <th>職缺申請ID</th>
                        <th>訂單日期</th>
                        <th>訂單狀態</th>
                        <th>訂單備註</th>
                        <th>訂單總金額</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.jobOrdersId}</td>
                            <td>${order.jobApplicationId}</td>
                            <td><fmt:formatDate value="${order.jobOrderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>${order.jobOrderStatus}</td>
                            <td>${order.jobNotes}</td>
                            <td>${order.jobAmount}</td>
                            <td>
                                <c:if test="${order.jobOrderStatus == 'Processing'}">
                                    <a href="updateJobOrder.jsp?job_orders_id=${order.jobOrdersId}" class="btn btn-primary">編輯</a>
                                    <form method="post" action="${pageContext.request.contextPath}/JobOrderServlet" style="display:inline;" onsubmit="return confirm('確定要刪除這筆訂單嗎？');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="job_orders_id" value="${order.jobOrdersId}">
                                        <button type="submit" class="btn btn-danger">刪除</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>
