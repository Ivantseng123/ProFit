<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service Details - ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.image-preview {
	max-width: 200px;
	max-height: 200px;
	margin-top: 10px;
}
</style>

<script>
	$(document)
			.ready(
					function() {
						$('.view')
								.click(
										function(e) {
											e.preventDefault();
											window.location.href = '${pageContext.request.contextPath}/service/search';
										});
					});
</script>
</head>
<body>
	<jsp:include page="/model/header&sidebar.jsp" />
	<main>
		<div class="dashboard-header">
			<h2>Service Details</h2>
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
		<div class="table-container">
			<table>
				<tr>
					<th>ID:</th>
					<td><c:out value="${service.serviceId}" /></td>
				</tr>
				<tr>
					<th>服務標題:</th>
					<td><c:out value="${service.serviceTitle}" /></td>
				</tr>
				<tr>
					<th>用戶:</th>
					<td><c:out value="${service.userMajor.id.user.userName}" /></td>
				</tr>
				<tr>
					<th>技能:</th>
					<td><c:out value="${service.userMajor.id.major.majorName}" /></td>
				</tr>
				<tr>
					<th>價格(單價):</th>
					<td><fmt:formatNumber value="${service.servicePrice}"
							type="currency" currencySymbol="$" /></td>
				</tr>
				<tr>
					<th>單位:</th>
					<td><c:out value="${service.serviceUnitName}" /></td>
				</tr>
				<tr>
					<th>時程:</th>
					<td><c:out value="${service.serviceDuration}" /> 天</td>
				</tr>

				<c:set var="dateTimePattern" value="yyyy/MM/dd HH:mm" />

				<tr>
					<th>創建時間:</th>
					<td><c:choose>
							<c:when test="${not empty service.serviceCreateDate}">
								<c:out value="${formattedCreateDate}" />
							</c:when>
							<c:otherwise>N/A</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th>更新時間:</th>
					<td><c:choose>
							<c:when test="${not empty service.serviceUpdateDate}">
								<c:out value="${formattedUpdateDate}" />
							</c:when>
							<c:otherwise>N/A</c:otherwise>
						</c:choose></td>
				</tr>

				<tr>
					<th>內容:</th>
					<td><c:out value="${service.serviceContent}" /></td>
				</tr>
				<tr>
					<th>範例:</th>
					<td><c:if test="${not empty service.servicePictureURL1}">
							<img id="imagePreview1" class="image-preview" src="${service.servicePictureURL1}"
								alt="Image1"/>
							<br>
						</c:if> <c:if test="${not empty service.servicePictureURL2}">
							<img id="imagePreview2" class="image-preview" src="${service.servicePictureURL2}"
								alt="Image2"/>
							<br>
						</c:if> <c:if test="${not empty service.servicePictureURL3}">
							<img id="imagePreview2" class="image-preview" src="${service.servicePictureURL3}"
								alt="Image3"/>
						</c:if></td>
				</tr>
			</table>

			<div class="action-buttons">
				<button class="edit"
					onclick="location.href='${pageContext.request.contextPath}/service/edit?id=${service.serviceId}'">編輯</button>
				<button class="delete"
					onclick="if(confirm('確定刪除此項服務?')) location.href='${pageContext.request.contextPath}/service/delete?id=${service.serviceId}'">刪除</button>
				<button class="view">返回服務清單</button>
			</div>
		</div>
	</main>
</body>
</html>