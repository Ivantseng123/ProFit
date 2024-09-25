<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增課程 - ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
<link rel="stylesheet" href="../model/jquery-ui-1.14.0.custom/jquery-ui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/courses/css/createCourseView.css">
<style>
label, input {
    display: block;
    margin-bottom: 12px;
}

input.text {
    width: 95%;
    padding: .4em;
}

div#users-contain {
    width: 100%;
    margin: 20px 0;
}

div#users-contain table {
    margin: 1em 0;
    border-collapse: collapse;
    width: 50%;
}

div#users-contain table td, div#users-contain table th {
    border: 1px solid #eee;
    padding: .6em 10px;
    text-align: center;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="../model/jquery-ui-1.14.0.custom/jquery-ui.js"></script>
<script src="../model/tabs.js"></script>
<script src="../model/dialog.js"></script>
</head>
<body>
	<jsp:include page="../model/header&sidebar.jsp"></jsp:include>
	<!-- 主要內容區域 -->
	<main>
		<div id="tabs">
			<ul>
				<li><a href="#createCourseTab">新增課程</a></li>
				<li><a href="#createModuleTab">新增章節</a></li>
			</ul>
			<div id="createCourseTab">
				<div class="form-container">
					<form>
						<div class="form-group">
							<label for="courseName">課程名稱:</label> <input type="text"
								id="courseName" name="courseName" required>
						</div>
						<div class="form-group">
							<label for="courseMajor">課程類別:</label> <select id="courseMajor"
								name="courseMajor" required>
								<option value="">請選擇類別</option>
								<option value="100">程式設計</option>
							</select>
						</div>
						<div class="form-group">
							<label for="courseCreateUserId">課程創建者ID:</label> <input
								type="text" id="courseCreateUserId" name="courseCreateUserId" required>
						</div>
						<div class="form-group">
							<label for="courseInformation">課程資訊:</label>
							<textarea id="courseInformation" name="courseInformation"
								rows="4" cols="50"></textarea>
						</div>
						<div class="form-group">
							<label for="courseDescription">課程描述:</label>
							<textarea id="courseDescription" name="courseDescription"
								rows="6" cols="50"></textarea>
						</div>
						<div class="form-group">
							<label for="courseEnrollmentDate">註冊日期: (自動帶入)</label> <input
								type="date" id="courseEnrollmentDate"
								name="courseEnrollmentDate" readonly>
						</div>
						<div class="form-group">
							<label for="courseStartDate">開始日期:</label> <input type="datetime-local"
								id="courseStartDate" name="courseStartDate">
						</div>
						<div class="form-group">
							<label for="courseEndDate">結束日期:</label> <input type="datetime-local"
								id="courseEndDate" name="courseEndDate">
						</div>
						<div class="form-group">
							<label for="coursePrice">課程價格:</label> <input type="number"
								id="coursePrice" name="coursePrice">
						</div>
						<div class="form-group">
							<label for="courseStatus">課程狀態:</label> <select id="courseStatus"
								name="courseStatus" required>
								<option value="">請選擇狀態</option>
								<option value="active">啟用</option>
								<option value="Pending">審核中</option>
								<option value="Closed">已關閉</option>
							</select>
						</div>
						<div class="form-group" style="text-align: right;">
							<a href="/ProFit/coursesVIEW/courseView.jsp"><button id='cancelBtn' type="button" style="margin-right:210px;" >取消新增</button></a>
							<button id="switchBtn" name="switchBtn" type="button">新增章節</button>
							<button id="createBtn" name="createBtn" type="button">略過，後續再新增</button>
						</div>
					</form>
				</div>
			</div>
			<div id="createModuleTab">
				<div id="dialog-form" title="新增章節">
					<form>
						<fieldset>
							<label for="name">章節名稱</label> <input type="text" name="name"
								id="name" value="請輸入章節名稱"
								class="text ui-widget-content ui-corner-all">

							<!-- Allow form submission with keyboard without duplicating the dialog button -->
							<input type="submit" tabindex="-1" style="position: absolute; top: -1000px">
						</fieldset>
					</form>
				</div>
				<div id="users-contain" class="ui-widget">
					<table id="users" class="ui-widget ui-widget-content">
						<thead style="text-align: center">
							<tr class="ui-widget-header">
								<th>章節</th>
								<th>章節名稱</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				   <div class="action-buttons">
				   		<button id="create-Module" class="view">創建新章節</button>
        	      </div>
			</div>
		</div>
	</main>
	<script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
	<script src="${pageContext.request.contextPath}/resource/courses/js/createCourseView.js"></script>
	<script>
		$(document).ready(function() {
			$("#tabs").tabs();

			$("#switchBtn").click(function() {
				$("#tabs").tabs("option", "active", 1);
			});
		});
	</script>
</body>
</html>