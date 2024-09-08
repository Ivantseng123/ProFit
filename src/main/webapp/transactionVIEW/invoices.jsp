<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>發票管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/transactionVIEW/jobOrder.css">
</head>
<body>
    <div class="container">
        <h2>發票管理</h2>

        <!-- 查詢表單 -->
        <form method="get" action="InvoiceServlet">
            <input type="hidden" name="action" value="filter">
            <div class="row">
                <div class="col-md-3">
                    <label for="invoice_number">發票號碼:</label>
                    <input type="text" id="invoice_number" name="invoice_number" class="form-control">
                </div>
                <div class="col-md-3">
                    <label for="invoice_status">發票狀態:</label>
                    <select id="invoice_status" name="invoice_status" class="form-select">
                        <option value="">全部</option>
                        <option value="open">開立</option>
                        <option value="canceled">取消</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <label for="transaction_id">交易ID:</label>
                    <input type="text" id="transaction_id" name="transaction_id" class="form-control">
                </div>
                <div class="col-md-3">
                    <label for="job_order_id">職缺訂單ID:</label>
                    <input type="text" id="job_order_id" name="job_order_id" class="form-control">
                </div>
                <div class="col-md-3">
                    <label for="course_order_id">課程訂單ID:</label>
                    <input type="text" id="course_order_id" name="course_order_id" class="form-control">
                </div>
                <div class="col-md-3">
                    <label for="event_order_id">活動訂單ID:</label>
                    <input type="text" id="event_order_id" name="event_order_id" class="form-control">
                </div>
            </div>
            <div class="mt-3">
                <button type="submit" class="btn btn-primary">查詢</button>
            </div>
        </form>

        <!-- 發票列表 -->
        <table class="table table-bordered mt-3">
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
                            <a href="InvoiceServlet?action=edit&invoice_id=${invoice.invoiceId}" class="btn btn-primary">編輯</a>
                            <form method="post" action="InvoiceServlet?action=delete" style="display:inline;">
                                <input type="hidden" name="invoice_id" value="${invoice.invoiceId}">
                                <button type="submit" class="btn btn-danger" onclick="return confirm('確定要刪除嗎？')">刪除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
