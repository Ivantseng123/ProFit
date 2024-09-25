<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProFit</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/courses/css/coursesView.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
	<jsp:include page="../model/header&sidebar.jsp"></jsp:include>
	<!-- 主要內容區域 -->
	<!-- 只是一個範例，裡面布局可以更改 -->
	<main>
		<div class="course-header">
			<h2>課程管理功能</h2>
			<span><a href="${pageContext.request.contextPath}/courses/addCourse"><button
						id="createBtn">新增課程</button></a></span>
		</div>
		<div class="dashboard-header">
			<h3 style="margin-top: 0px;">查詢課程</h3>
			<span style="margin-right: 30px;">課程分類: <select
				id="id-courseMajor" name="courseMajor"
				style="width: 100px; vertical-align: middle;">
					<option id="option-courseMajor-all" value="">全部</option>
					<option id="option-courseMajor-engineering" value="100">程式設計</option>
			</select>
			</span> <span style="margin-right: 30px;">課程名稱: <input type="text"
				id="id-courseName" name="courseName" style="width: 100px;" />
			</span> <span>課程狀態: <select id="id-courseStatus" name="courseStatus"
				style="width: 100px;">
					<option id="option-courseStatus-all" value="">全部</option>
					<option id="option-courseStatus-Active" value="Active">進行中</option>
					<option id="option-courseStatus-Pending" value="Pending">審核中</option>
					<option id="option-courseStatus-Closed" value="Closed">已關閉</option>
			</select>
			</span> <br> <br> <span style="margin-right: 30px;">課程創建者ID:
				<input type="text" id="id-courseCreateUserId"
				name="courseCreateUserId" value="" style="width: 100px;" />
			</span> <span style="margin-right: 30px;">課程創建者名稱: <input type="text"
				id="id-courseCreateUserName" name="courseCreateUserName" value=""
				style="width: 100px;" />
			</span>
			<button id="searchBtn">送出查詢</button>
		</div>
		
		<!-- 彈出視窗 -->
		<div id="popup" class="popup">
			<div class="popup-content">
				<span class="close-btn">&times;</span>
				<div class="course-header">
					<h2>課程細節</h2>
				</div>
				<div class="form-container"></div>
				
			</div>
		</div>
		
		<div class="table-container" id="search-results"></div>
	</main>
	<script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="${pageContext.request.contextPath}/resource/courses/js/courseView.js"></script>
	<script src="${pageContext.request.contextPath}/model/popup.js"></script>
</body>
</html>