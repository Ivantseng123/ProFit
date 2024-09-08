<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../model/header&sidebar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用戶交易管理系統</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/transactionVIEW/jobOrder.css">
</head>
<body>
    <!-- 主內容區域 -->
    <main>
        <div class="dashboard-header">
            <h2>歡迎使用用戶交易管理系統</h2>
        </div>
        
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/transactionVIEW/jobOrders.jsp" class="btn-custom view">進入訂單管理</a>
  			<br>
            <a href="${pageContext.request.contextPath}/transactionVIEW/userTransactions.jsp" class="btn-custom edit">進入用戶交易管理</a>
            <br>
            <a href="${pageContext.request.contextPath}/transactionVIEW/invoices.jsp" class="btn-custom edit">進入用戶交易管理</a>   
        </div>
    </main>

   
</body>
</html>
