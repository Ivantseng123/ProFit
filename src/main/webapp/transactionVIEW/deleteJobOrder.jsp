<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>刪除職缺訂單</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/model/header&sidebar.jsp" />

    <main>
        <div class="container">
            <h2>確認刪除職缺訂單</h2>
            <form method="post" action="${pageContext.request.contextPath}/JobOrderServlet">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="job_orders_id" value="${param.job_orders_id}">
                <p>確定要刪除這筆訂單嗎？</p>
                <button type="submit" class="btn btn-danger">確定</button>
                <a href="jobOrders.jsp" class="btn btn-secondary">取消</a>
            </form>
        </div>
    </main>
</body>
</html>
