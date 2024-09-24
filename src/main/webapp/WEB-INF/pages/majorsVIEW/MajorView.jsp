<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Major - ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
	<jsp:include page="/model/header&sidebar.jsp" />

	<main>
		<div class="dashboard-header">
			<h2>專業詳情</h2>
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
			<button class="delete"
					onclick="location.href='${pageContext.request.contextPath}/major/'">返回專業管理</button>			
		</div>
		<div class="table-container">
			<table>
				<tr>
					<th>專業ID:</th>
					<td><c:out value="${major.majorId}" /></td>
				</tr>
				<tr>
					<th>專業名稱:</th>
					<td><c:out value="${major.majorName}" /></td>
				</tr>
				<tr>
					<th>專業分類 ID:</th>
					<td><c:out value="${major.majorCategoryId}" /></td>
				</tr>
				<tr>
					<th>專業描述:</th>
					<td><c:out value="${major.majorDescription}" /></td>
				</tr>
			</table>
			<div class="action-buttons">
				<button class="edit"
					onclick="location.href='${pageContext.request.contextPath}/major/edit?id=<c:out value='${major.majorId}' />'">編輯</button>
				<button class="delete"
					onclick="location.href='${pageContext.request.contextPath}/major/delete?id=<c:out value='${major.majorId}' />'">刪除</button>
			</div>
		</div>
	</main>
</body>
</html>