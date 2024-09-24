<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${category != null ? '編輯' : '新增'}專業類別 - ProFit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
    <jsp:include page="/model/header&sidebar.jsp" />

    <main>
        <div class="dashboard-header">
            <h2>${category != null ? '編輯' : '新增'}專業類別</h2>
        </div>
        
            <div class="entry-options1">
            <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/service/search">技能服務管理</a>
            </div>
            <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/userMajor/">用戶-專業</a>
            </div>
             <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/major/">專業管理</a>
            </div>
             <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/majorCategory/list">專業類別管理</a>
            </div>
	</div>
	<div class="action-buttons">
        <button class="delete" onclick="location.href='${pageContext.request.contextPath}/majorCategory/list'">返回類別列表</button>
        </div>
        <div class="form-container">
            <form class="form-group" class="action-buttons" action="${pageContext.request.contextPath}/majorCategory/${category != null ? 'update' : 'insert'}" method="post">
                <label for="categoryId">類別 ID:</label>
                <input type="number" id="categoryId" name="majorCategoryId" value="${category.majorCategoryId}" ${category != null ? 'readonly' : 'required'}>
                <br><br>
                
                <label for="categoryName">類別名稱:</label>
                <input type="text" id="categoryName" name="categoryName" value="${category.categoryName}" required>
                <br><br>
                
                <button class="view" type="submit">${category != null ? '更新' : '新增'}類別</button>
            </form>
        </div>
    </main>
</body>
</html>