<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Major Management - ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
	<jsp:include page="/model/header&sidebar.jsp" />

	<main>
		<div class="dashboard-header">
			<h2>專業管理</h2>
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
		<div class="form-container">
		
			 <form class="form-group" action="${pageContext.request.contextPath}/major/category" method="get">
                <label for="categoryId">根據類別ID查詢</label>
                
                <input type="number" id="categoryId" name="categoryId" required>
                <button class="" type="submit">查詢</button>
            </form>
			<hr>
            <div class="action-buttons">
			<button class="edit" onclick="location.href='${pageContext.request.contextPath}/major/new'">新增專業</button>
			<button class="view" onclick="location.href='${pageContext.request.contextPath}/major/'">顯示所有專業</button>
			<button class="delete" onclick="location.href='${pageContext.request.contextPath}/majorCategory/new'">新增專業類別</button>
            <button class="view" onclick="location.href='${pageContext.request.contextPath}/majorCategory/list'">顯示所有專業類別</button>
			
			</div>
		
		</div>
		<br>
		
		<div class="table-container">
			<table>
			<c:if test="${not empty error}">
                <div class="error-message">
                    <c:out value="${error}" />
                </div>
            </c:if>

            <c:if test="${not empty categoryId}">
                <h3>類別ID 為 ${categoryId} 的專業</h3>
            </c:if>
				<tr>
					<th>專業ID</th>
					<th>專業名稱</th>
					<th>類別ID</th>
					<th>類別名稱</th>
					<th>簡介</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="major" items="${listMajor}">
					<tr>
						<td><c:out value="${major.majorId}" /></td>
						<td><c:out value="${major.majorName}" /></td>
						<td><c:out value="${major.majorCategoryId}" /></td>
						<td><c:out value="${major.majorCategory.categoryName}" /></td>
						<td><c:out value="${major.majorDescription}" /></td>
						<td class="action-buttons">
							<button class="edit"
								onclick="location.href='${pageContext.request.contextPath}/major/edit?id=<c:out value='${major.majorId}' />'">編輯</button>
							<button class="delete"
								onclick="location.href='${pageContext.request.contextPath}/major/delete?id=<c:out value='${major.majorId}' />'">刪除</button>
							<button class="view"
								onclick="location.href='${pageContext.request.contextPath}/major/view?id=<c:out value='${major.majorId}' />'">檢視</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</main>
</body>
</html>