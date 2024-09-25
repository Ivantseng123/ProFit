<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增課程 - ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/courses/css/updateCourseView.css">
<style>
    #enrollmentDate[readonly] {
        color: black !important;
        background-color: white !important;
        -webkit-text-fill-color: black !important;
    }
</style>
</head>
<body>
	<jsp:include page="../model/header&sidebar.jsp"></jsp:include>

	<!-- 主要內容區域 -->
	<main>
		<div class="dashboard-header">
			<h2>修改課程</h2>
		</div>
		<div class="form-container">
		</div>
	</main>
	<script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="${pageContext.request.contextPath}/resource/courses/js/updateCourseView.js"></script>
</body>
</html>