<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
<style>
.userPicture{
	width: 100px;
    height: 100px;

	img {
  	vertical-align: middle;
  	border-radius: 50%;
  	padding: 50px;
    width: 100%;
    height: 100%;
    /* 控制元素為內容大小 調整比例*/
    object-fit: cover;
    box-sizing: content-box; /* 將box-sizing設置為content-box以避免被覆蓋 */
	}
}
</style>	
</head>
<body>
	<!-- 頂列區域 -->
	<header>
		<h1>ProFit</h1>
		<div class="user-info">
			<p>${sessionScope.user_email}</p>
			<p>歡迎使用 ProFit！</p>
			<a style="text-decoration: none;" class="loginStatus"
				href="${pageContext.request.contextPath}/Logout">
				<button class="logout">登出</button>
			</a>
		</div>
	</header>

	<!-- 左側邊欄 -->
	<nav class="modelbar">
			<div class="userPicture">
				<img
					src="${pageContext.request.contextPath}/usersVIEW/userupload/${sessionScope.user_pictureURL}"
					alt="Profile Image"/>
			</div>
			<br>
			<br>
			<br>
		<ul>
			<li><a href="${pageContext.request.contextPath}/GetAlluser">會員管理</a></li>
			<li><a href="/ProFit/jobsVIEW/jobsList.jsp">職缺管理</a></li>
			<li><a href="/ProFit/servicesVIEW/Entry.jsp">技能服務管理</a></li>
			<li><a href="/ProFit/coursesVIEW/courseView.jsp">課程管理</a></li>
			<li><a href="${pageContext.request.contextPath}/events">活動管理</a></li>
			<li><a href="/ProFit/transactionVIEW/transactions.jsp">交易管理</a></li>
		</ul>
	</nav>
	<script>
	    document.addEventListener("DOMContentLoaded", function() {
	        var userPictureImg = document.querySelector(".userPicture img");
	        var user_pictureURL = "${sessionScope.user_pictureURL}";
	        if (!user_pictureURL) {
	            userPictureImg.src = "${pageContext.request.contextPath}/usersVIEW/default_user_picture.png";
	        } else {
	            userPictureImg.src = "${pageContext.request.contextPath}/usersVIEW/userupload/${sessionScope.user_pictureURL}";
	        }

	        var logoutButton = document.querySelector(".logout");
	        var user_email = "${sessionScope.user_email}";
	        if (!user_email) {
	            logoutButton.textContent = "登入";
	        } else {
	            logoutButton.textContent = "登出";
	        }

	        var login_status = logoutButton.textContent;
	        if (login_status === "登入") {
	            document.querySelector(".loginStatus").href = "${pageContext.request.contextPath}/usersVIEW/Login.jsp";
	        }
	    });

	</script>
</body>
</html>