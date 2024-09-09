<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*,com.ProFit.bean.usersBean.Users"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProFit</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css"/> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/usersVIEW/userVIEW.css"/> 
<jsp:useBean id="user" scope="request" class="com.ProFit.bean.usersBean.Users"/>
</head>
<body>
    <jsp:include page="../model/header&sidebar.jsp"></jsp:include>
     <main>
        <div class="dashboard-header">
            <h2>會員管理</h2>
        </div>
		<h2 style="color: orange;">會員資訊</h2>
		<div class="form-container" >
				<h3> 會員頭貼: </h3>
				<div class="profile-picture" style="text-align: center;">
						<c:choose>
   							 <c:when test="${not empty user.userPictureURL}">
        						<img src="${user.userPictureURL}" alt="Profile Image"/>
   							 </c:when>
    						<c:otherwise>
        						<img src="https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2Fdefault_user_picture.png?alt=media&token=dd3a8cfa-1a00-48ac-ba30-1f7bb3d783bd" 									alt="Default Profile Image"  />
    						</c:otherwise>
						</c:choose> 
				</div>	
					<h3> 會員ID: </h3> 					
						<p>${user.userId}</p>
					<h3> 會員姓名: </h3> 
						<p>${user.userName}</p>
						
					<h3>會員信箱:</h3>
						<p>${user.userEmail}</p> 
				
					<h3> 會員密碼: </h3>
						<p>${user.userPasswordHash}</p>  
						
					<h3>會員手機號碼:</h3>
						<p>${user.userPhoneNumber}</p> 						
						
					<h3>會員居住城市:</h3>
						<p>${user.userCity}</p>
					
					<h3>會員身份:</h3>
						<c:choose>
							<c:when test="${user.userIdentity == 1}">
								<p>應徵者</p>
							</c:when>
							<c:when test="${user.userIdentity == 2}">
								<p>應徵者/企業主</p>
							</c:when>
							<c:otherwise>
        						<p>管理員</p>
    						</c:otherwise>
						</c:choose> 
					<h3>會員餘額:</h3>
						<p>${user.userBalance}</p> 
						
					<h3>會員工作地點偏好:</h3>
						<p>${user.freelancerLocationPrefer}</p> 
						
					<h3>會員工作經驗:</h3>
						<p>${user.freelancerExprience}</p> 
														
					<h3>會員接案身份:</h3>
						<p>${user.freelancerIdentity}</p> 
														
					<h3>會員接案狀態:</h3>				
						<c:choose>
							<c:when test="${user.freelancerProfileStatus == 0}">
								<p>關閉</p>
							</c:when>
							<c:otherwise>
        						<p>開啟</p>
    						</c:otherwise>
						</c:choose> 
							
					<h3>會員接案描述:</h3>
						<p>${user.freelancerDisc}</p> 
						
					<h3>會員註冊時間:</h3>
						<p>${user.userRegisterTime}</p> 
																
		</div>
	</main>
	<script src="https://kit.fontawesome.com/4caf09a569.js" crossorigin="anonymous"></script>
</body>
</html>