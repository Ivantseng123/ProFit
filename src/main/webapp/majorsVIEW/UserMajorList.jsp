<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Majors - ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
	<jsp:include page="/model/header&sidebar.jsp" />

	<main>
		<div class="dashboard-header">
			<h2>用戶: ${userName} 擁有的專業</h2>
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
			<button
				onclick="location.href='${pageContext.request.contextPath}/userMajor/'">返回所有用戶專業</button>
				
			<h3>為用戶新增專業</h3>
			<form action="${pageContext.request.contextPath}/userMajor/add"
				method="post">
				<input type="hidden" name="userId" value="${userId}"> <label
					for="majorId">專業:</label> <select id="majorId" name="majorId"
					required>
					<option value="">選擇專業</option>
					<c:forEach var="major" items="${availableMajors}">
						<option value="${major.key}">${major.value}</option>
					</c:forEach>
				</select> <input type="submit" value="新增">
			</form>
		</div>


		<div class="table-container">
			<table>
				<tr>
					<th>用戶名稱</th>
					<th>專業名稱</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="userMajor" items="${userMajors}">
					<tr>
						<td><c:out value="${userMajor.id.user.userName}" /></td>
						<td><c:out value="${userMajor.id.major.majorName}" /></td>
						<td class="action-buttons">
							<button class="delete"
								onclick="location.href='${pageContext.request.contextPath}/userMajor/delete?userId=${userMajor.id.user.userId}&majorId=${userMajor.id.major.majorId}'">刪除</button>
							<button class="view"
								onclick="location.href='${pageContext.request.contextPath}/service/search?userId=${userMajor.id.user.userId}&majorId=${userMajor.id.major.majorId}'">檢視服務</button>
						</td>
					</tr>
				</c:forEach>
			</table>



			<!-- 添加這個調試信息 -->
			<c:if test="${empty availableMajors}">
				<p style="color: red;">沒有可新增的專業</p>
			</c:if>
		</div>
	</main>
</body>
</html>