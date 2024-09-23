<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Major Form - ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
	<jsp:include page="/model/header&sidebar.jsp" />

	<main>
		<div class="dashboard-header">
			<h2>專業 - 新增/修改</h2>
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

			<c:if test="${not empty error}">
				<div class="error-message">
					<c:out value="${error}" />
				</div>
			</c:if>

			<c:if test="${major != null}">
				<form class="form-group" action="${pageContext.request.contextPath}/major/update"
					method="post">
			</c:if>
			<c:if test="${major == null}">
				<form class="form-group"
					action="${pageContext.request.contextPath}/major/insert"
					method="post">
			</c:if>
			<table>
				<tr>
					<th>專業ID:</th>
					<td><c:if test="${major != null}">
							<input type="number" name="majorId"
								value="<c:out value='${major.majorId}' />" readonly="readonly" />
						</c:if> <c:if test="${major == null}">
							<input type="number" name="majorId" required="required" />
						</c:if></td>
				</tr>
				<tr>
					<th>專業名稱:</th>
					<td><input type="text" name="majorName" size="45"
						value="<c:out value='${major.majorName}' />" /></td>
				</tr>
				<tr>
					<th>專業類別ID:</th>
					<td>
					
						<c:if test="${major != null}">
							<input type="number" name="majorCategoryId"
								value="<c:out value='${major.majorCategoryId}' />" readonly="readonly" />
						</c:if> 
						<c:if test="${major == null}">
							<input type="number" name="majorCategoryId" required="required" />
						</c:if>
					</td>
				</tr>
				<tr>
					<th>專業簡述:</th>
					<td><textarea name="majorDescription" rows="5" cols="50"><c:out
								value='${major.majorDescription}' /></textarea></td>
				</tr>
				<tr class="action-buttons">
					<td colspan="2" align="center"><button type="submit">送出</button>
						<button class="delete"
							onclick="location.href='${pageContext.request.contextPath}/major/'">取消</button>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</main>
</body>
</html>