<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service Management</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						// 當用戶ID下拉選單改變時自動提交表單
						$('#userId').change(function() {
							$('#searchForm').submit();
						});

						$('#majorId').change(function() {
							$('#searchForm').submit();
						});

						// 使用jQuery處理編輯、刪除和檢視按鈕的點擊事件
						$('.b .edit')
								.click(
										function(e) {
											e.preventDefault();
											var serviceId = $(this).data('id');
											window.location.href = '${pageContext.request.contextPath}/service/edit?id='
													+ serviceId;
										});

						$('.b .delete')
								.click(
										function(e) {
											e.preventDefault();
											var serviceId = $(this).data('id');
											if (confirm('確定要刪除此筆資料?')) {
												window.location.href = '${pageContext.request.contextPath}/service/delete?id='
														+ serviceId;
											}
										});

						$('.b .view')
								.click(
										function(e) {
											e.preventDefault();
											var serviceId = $(this).data('id');
											window.location.href = '${pageContext.request.contextPath}/service/view?id='
													+ serviceId;
										});
					});
</script>
</head>
<body>
	<jsp:include page="/model/header&sidebar.jsp" />
	<main>
		<div class="dashboard-header">
			<h2>技能服務管理</h2>
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


		<section class="form-container">

			<form class=" form-group" id="searchForm"
				action="${pageContext.request.contextPath}/service/search"
				method="get">
				<table>
					<tr>
						<td><label for="userId">根據用戶篩選:</label> <select id="userId"
							name="userId">
								<option value="">選擇用戶</option>
								<c:forEach var="user" items="${users}">
									<option value="${user.key}"
										${user.key == selectedUserId ? 'selected' : ''}>${user.value}</option>
								</c:forEach>
						</select></td>
						<td><label for="majorId">根據專業篩選:</label> <select id="majorId"
							name="majorId">
								<option value="">選擇專業</option>
								<c:forEach var="major" items="${majors}">
									<option value="${major.key}">${major.value}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label for="titleKeyword">標題關鍵字:</label> <input
							type="text" id="titleKeyword" name="titleKeyword"></td>
						<td><label for="contentKeyword">內容關鍵字:</label> <input
							type="text" id="contentKeyword" name="contentKeyword"></td>
					</tr>
				</table>
				<button type="submit">查詢</button>

			</form>
			<hr>
			<div class="action-buttons">
				<button class="edit"
					onclick="location.href='${pageContext.request.contextPath}/service/new'">新增服務</button>
				<button class="view"
					onclick="location.href='${pageContext.request.contextPath}/service/'">顯示所有服務</button>
			</div>
		</section>
		<br>

		<div class="table-container">
			<table>
				<tr>
					<th>ID</th>
					<th>服務標題</th>
					<th>用戶</th>
					<th>技能</th>
					<th>價格(單價)</th>
					<th>單位</th>
					<th>時程(天)</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="service" items="${listService}">
					<tr>
						<td><c:out value="${service.serviceId}" /></td>
						<td><c:out value="${service.serviceTitle}" /></td>
						<td><c:out value="${service.userMajor.id.user.userName}" /></td>
						<td><c:out value="${service.userMajor.id.major.majorName}" /></td>
						<td><c:out value="${service.servicePrice}" /></td>
						<td><c:out value="${service.serviceUnitName}" /></td>
						<td><c:out value="${service.serviceDuration}" /></td>
						<td class="action-buttons b">
							<button class="edit" id="edit-btn" data-id="${service.serviceId}">編輯</button>
							<button class="delete" data-id="${service.serviceId}">刪除</button>
							<button class="view" data-id="${service.serviceId}">檢視</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</main>
</body>
</html>