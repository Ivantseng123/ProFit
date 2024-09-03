<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        <!-- 查詢表單區域 -->
        <div class="dashboard-header">
            <!-- 合併所有查詢條件到一行 -->
            <div align="center">
                <h4>查詢職缺訂單</h4>
                <form method="get" action="${pageContext.request.contextPath}/JobOrderServlet">
                    <input type="hidden" name="action" value="combinedSearch">
                    職缺申請ID: <input type="number" name="job_application_id" placeholder="輸入職缺申請ID">
                    訂單日期從: <input type="date" name="start_date">
                    到: <input type="date" name="end_date">
                    訂單狀態: 
                    <select name="job_order_status">
                        <option value="">全部</option>
                        <option value="Processing">處理中</option>
                        <option value="Completed">完成</option>
                        <option value="Canceled">取消</option>
                    </select>
                    <button type="submit" class="btn btn-primary">查詢</button>
                </form>
            </div>
        </div>

        <!-- 新增訂單按鈕 -->
        <div align="center" style="margin-top: 20px;">
            <a href="${pageContext.request.contextPath}/transactionVIEW/addJobOrder.jsp" class="btn btn-success">新增訂單</a>
        </div>

        <!-- 訂單記錄表格 -->
        <div class="table-container">
            <table id="ordersTable" class="table table-bordered">
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
                <tbody id="orderBody">
                    <!-- 使用JSTL來迭代訂單記錄 -->
                    <c:forEach var="order" items="${orders}">
                        <tr id="orderRow${order.jobOrdersId}">
                            <td>${order.jobOrdersId}</td>
                            <td>${order.jobApplicationId}</td>
                            <td>${order.jobOrderDate}</td>
                            <td>${order.jobOrderStatus}</td>
                            <td>${order.jobNotes}</td>
                            <td>${order.totalAmount}</td>
                            <td>
                                <!-- 只有在狀態為 Processing 時才顯示編輯和刪除按鈕 -->
                                <c:if test="${order.jobOrderStatus == 'Processing'}">
                                    <!-- 更新訂單 -->
                                    <a href="${pageContext.request.contextPath}/transactionVIEW/updateJobOrder.jsp?job_orders_id=${order.jobOrdersId}" class="btn btn-primary">編輯</a>
                                    <!-- 刪除訂單 -->
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
