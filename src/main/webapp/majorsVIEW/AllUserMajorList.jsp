<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All User Majors - ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
	<jsp:include page="/model/header&sidebar.jsp" />

	<main>
		<div class="dashboard-header">
			<h2>所有用戶-專業</h2>
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
			<form class="form-group"
				action="${pageContext.request.contextPath}/userMajor/userMajors"
				method="get">
				<label for="userName">根據用戶名稱查詢</label> <select id="userName"
					name="userName" required>
					<option value="">選擇用戶</option>
					<c:forEach var="user" items="${allUsers}">
						<option value="${user.key}">${user.value}</option>
					</c:forEach>
				</select><button type="submit">查詢</button>
			</form>
			<form class="form-group"
				action="${pageContext.request.contextPath}/userMajor/majorUsers"
				method="get">
				<br> <label for="majorName">根據專業名稱查詢</label> <select
					id="majorName" name="majorName" required>
					<option value="">選擇專業</option>
					<c:forEach var="major" items="${allMajors}">
						<option value="${major.key}">${major.value}</option>
					</c:forEach>
				</select> <button type="submit">查詢</button>
			</form>
			<hr>
			<div class="action-buttons">
				<button class="edit"
					onclick="location.href='${pageContext.request.contextPath}/major/new'">新增專業</button>
				<button class="view"
					onclick="location.href='${pageContext.request.contextPath}/major/list'">顯示所有專業</button>
			</div>

		</div>
<br>
		<div class="table-container">
			<table>
				<table>
					<tr>
						<th>用戶 ID</th>
						<th>用戶名稱</th>
						<th>專業 ID</th>
						<th>專業名稱</th>
						<th>Actions</th>
					</tr>
					<c:forEach var="userMajor" items="${allUserMajors}">
						<tr>
							<td><c:out value="${userMajor.userId}" /></td>
							<td><c:out value="${userMajor.userName}" /></td>
							<td><c:out value="${userMajor.majorId}" /></td>
							<td><c:out value="${userMajor.majorName}" /></td>
							<td class="action-buttons">
								<button class="delete"
									onclick="location.href='${pageContext.request.contextPath}/userMajor/delete?userId=${userMajor.userId}&majorId=${userMajor.majorId}'">刪除</button>
								<button class="view"
									onclick="location.href='${pageContext.request.contextPath}/service/search?userId=${userMajor.userId}&majorId=${userMajor.majorId}'">檢視服務</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</table>
		</div>
	</main>
</body>
</html>