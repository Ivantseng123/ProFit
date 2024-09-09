<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>發票管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../model/header&sidebar.jsp" />
    <main>
        <div class="course-header">
            <h2>發票管理</h2>
        </div>

        <!-- 查詢表單 -->
        <div class="dashboard-header">
            <form method="get" action="${pageContext.request.contextPath}/InvoiceServlet">
                <input type="hidden" name="action" value="filter">

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="invoice_number">發票號碼:</label> 
                        <input type="text" id="invoice_number" name="invoice_number" class="form-control">
                    </div>

                    <div class="col-md-6">
                        <label for="invoice_status">發票狀態:</label> 
                        <select id="invoice_status" name="invoice_status" class="form-select">
                            <option value="">全部</option>
                            <option value="open">開立</option>
                            <option value="canceled">取消</option>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="id_type">選擇ID類型:</label> 
                        <select id="id_type" name="id_type" class="form-select" onchange="toggleIdInputFields()">
                            <option value="">選擇ID類型</option>
                            <option value="transaction_id">交易ID</option>
                            <option value="job_order_id">職缺訂單ID</option>
                            <option value="course_order_id">課程訂單ID</option>
                            <option value="event_order_id">活動訂單ID</option>
                        </select>
                    </div>

                    <div class="col-md-6">
                        <label for="id_value" id="id_value_label">輸入ID:</label>
                        <input type="text" id="id_value" name="id_value" class="form-control">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-12 text-center">
                        <input type="submit" value="查詢" class="btn btn-primary">
                    </div>
                </div>
            </form>
        </div>

        <!-- 發票表格 -->
        <div class="table-container">
            <table id="invoicesTable" class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th>發票ID</th>
                        <th>發票號碼</th>
                        <th>發票金額</th>
                        <th>發票日期</th>
                        <th>發票狀態</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="invoice" items="${invoices}">
                        <tr>
                            <td>${invoice.invoiceId}</td>
                            <td>${invoice.invoiceNumber}</td>
                            <td>${invoice.invoiceAmount}</td>
                            <td><fmt:formatDate value="${invoice.issuedDate}" pattern="yyyy-MM-dd" /></td>
                            <td>${invoice.invoiceStatus}</td>
                            <td>
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" 
                                    data-bs-target="#updateInvoiceModal"
                                    onclick="populateUpdateForm('${invoice.invoiceId}', '${invoice.invoiceNumber}', ${invoice.invoiceAmount}, '${invoice.invoiceStatus}', '${invoice.issuedDate}')">更新</button>
                                <form method="post" action="${pageContext.request.contextPath}/InvoiceServlet" style="display: inline;" onsubmit="return confirm('確定要刪除這張發票嗎？');">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="invoice_id" value="${invoice.invoiceId}">
                                    <button type="submit" class="btn btn-danger">刪除</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>

    <script>
        function toggleIdInputFields() {
            const idType = document.getElementById('id_type').value;
            const idValueLabel = document.getElementById('id_value_label');
            const idValueInput = document.getElementById('id_value');

            if (idType === 'transaction_id') {
                idValueLabel.textContent = '輸入交易ID:';
                idValueInput.placeholder = '輸入交易ID';
            } else if (idType === 'job_order_id') {
                idValueLabel.textContent = '輸入職缺訂單ID:';
                idValueInput.placeholder = '輸入職缺訂單ID';
            } else if (idType === 'course_order_id') {
                idValueLabel.textContent = '輸入課程訂單ID:';
                idValueInput.placeholder = '輸入課程訂單ID';
            } else if (idType === 'event_order_id') {
                idValueLabel.textContent = '輸入活動訂單ID:';
                idValueInput.placeholder = '輸入活動訂單ID';
            } else {
                idValueLabel.textContent = '輸入ID:';
                idValueInput.placeholder = '選擇ID類型後輸入';
            }
        }

        function populateUpdateForm(invoiceId, invoiceNumber, invoiceAmount, invoiceStatus, issuedDate) {
            document.getElementById('updateInvoiceId').value = invoiceId;
            document.getElementById('updateInvoiceNumber').value = invoiceNumber;
            document.getElementById('updateInvoiceAmount').value = invoiceAmount;
            document.getElementById('updateInvoiceStatus').value = invoiceStatus;
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
