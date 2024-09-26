<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>職缺訂單管理</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
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
            <form method="get" action="${pageContext.request.contextPath}/jobOrders/search" onsubmit="return validateSearchForm();">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="start_date">訂單日期從:</label>
                        <input type="date" id="start_date" name="startDate" class="form-control" value="${param.start_date}">
                    </div>
                    <div class="col-md-6">
                        <label for="end_date">到:</label>
                        <input type="date" id="end_date" name="endDate" class="form-control" value="${param.end_date}">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="job_application_id">職缺申請ID:</label>
                        <input type="number" id="job_application_id" name="jobApplicationId" class="form-control" min="1" value="${param.job_application_id}">
                    </div>
                    <div class="col-md-6">
                        <label for="job_order_status">訂單狀態:</label>
                        <select id="job_order_status" name="jobOrderStatus" class="form-select">
                            <option value="">全部</option>
                            <option value="Processing" ${param.job_order_status == 'Processing' ? 'selected' : ''}>處理中</option>
                            <option value="Completed" ${param.job_order_status == 'Completed' ? 'selected' : ''}>完成</option>
                            <option value="Canceled" ${param.job_order_status == 'Canceled' ? 'selected' : ''}>取消</option>
                        </select>
                    </div>
                </div>

                <div class="row mb-3 text-center">
                    <div class="col-md-12">
                        <input type="submit" value="查詢" class="btn btn-primary">
                    </div>
                </div>
            </form>
        </div>

        <!-- 新增訂單按鈕 -->
        <div align="center" class="mb-3">
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addOrderModal">新增訂單</button>
        </div>

        <!-- 新增訂單模態框 -->
        <div class="modal fade" id="addOrderModal" tabindex="-1" aria-labelledby="addOrderModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="post" action="${pageContext.request.contextPath}/jobOrders/insert" id="addOrderForm" onsubmit="return validateAddForm();">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addOrderModalLabel">新增訂單</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            職缺申請ID: <input type="number" id="jobApplicationId" name="job_application_id" required class="form-control" min="1"><br>
                            訂單狀態: <select id="jobOrderStatus" name="job_order_status" required class="form-select">
                                <option value="Processing">處理中</option>
                                <option value="Completed">完成</option>
                                <option value="Canceled">取消</option>
                            </select><br>
                            訂單備註: <input type="text" id="jobNotes" name="job_notes" class="form-control" maxlength="500"><br>
                            總金額: <input type="number" id="totalAmount" name="total_amount" required class="form-control" min="0"><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary">新增</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- 訂單表格 -->
        <div class="table-container mt-4">
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
                                <!-- 只有狀態為 Processing 才顯示編輯和刪除按鈕 -->
                                <c:if test="${order.jobOrderStatus == 'Processing'}">
                                    <div class="action-buttons" style="display: flex; justify-content: center; align-items: center;">
                                        <button type="button" class="view" data-bs-toggle="modal" data-bs-target="#updateModal"
                                            onclick="populateUpdateForm('${order.jobOrdersId}', '${order.jobApplicationId}', '${order.jobOrderStatus}', '${order.jobNotes}', '${order.jobAmount}')">編輯</button>
                                        
                                        <form method="post" action="${pageContext.request.contextPath}/jobOrders/delete" style="display: inline;" onsubmit="return confirm('確定要刪除這筆訂單嗎？');">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <input type="hidden" name="job_orders_id" value="${order.jobOrdersId}">
                                            <button type="submit" class="btn btn-danger">刪除</button>
                                        </form>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- 更新訂單模態框 -->
        <div class="modal fade" id="updateOrderModal" tabindex="-1" aria-labelledby="updateOrderModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="post" action="${pageContext.request.contextPath}/jobOrders/update" id="updateOrderForm" onsubmit="return confirm('確定要更新這筆訂單嗎？');">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updateOrderModalLabel">更新訂單</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="job_orders_id" id="updateJobOrdersId">

                            職缺申請ID: <input type="number" id="updateJobApplicationId" name="job_application_id" required class="form-control" min="1"><br>
                            訂單狀態: <select id="updateJobOrderStatus" name="job_order_status" required class="form-select">
                                <option value="Processing">處理中</option>
                                <option value="Completed">完成</option>
                                <option value="Canceled">取消</option>
                            </select><br>
                            訂單備註: <input type="text" id="updateJobNotes" name="job_notes" class="form-control" maxlength="500"><br>
                            總金額: <input type="number" id="updateTotalAmount" name="total_amount" required class="form-control" min="0"><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary">更新</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // 填充更新表單的數據
        function populateUpdateForm(jobOrdersId, jobApplicationId, jobOrderStatus, jobNotes, jobAmount) {
            document.getElementById('updateJobOrdersId').value = jobOrdersId;
            document.getElementById('updateJobApplicationId').value = jobApplicationId;
            document.getElementById('updateJobOrderStatus').value = jobOrderStatus;
            document.getElementById('updateJobNotes').value = jobNotes;
            document.getElementById('updateTotalAmount').value = jobAmount;
        }

        // 驗證查詢表單
        function validateSearchForm() {
            var startDate = document.getElementById('start_date').value;
            var endDate = document.getElementById('end_date').value;
            if (startDate && endDate && startDate > endDate) {
                alert('開始日期不能晚於結束日期');
                return false;
            }
            return true;
        }

        // 驗證新增表單
        function validateAddForm() {
            var amount = document.getElementById("totalAmount").value;
            if (amount <= 0) {
                alert("金額必須大於 0");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
