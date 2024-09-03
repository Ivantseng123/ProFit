<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*,com.ProFit.bean.usersBean.Employer_application"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProFit</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css"/> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/usersVIEW/userVIEW.css"/> 
<jsp:useBean id="emp" scope="request" class="com.ProFit.bean.usersBean.Employer_application"/>
</head>
<body>
	<jsp:include page="../model/header&sidebar.jsp"></jsp:include>
     <main>
        <div class="dashboard-header">
            <h2>會員管理</h2>
        </div>
		<h2 style="color: orange;">企業申請資訊</h2>
		<div class="form-container" >
					<h3>企業申請ID: </h3> 					
						<p>${emp.employer_application_id}</p>
					<h3>會員ID: </h3> 
						<p>${emp.user_id}</p>
						
					<h3>公司名稱:</h3>
						<p>${emp.company_name}</p> 
				
					<h3>公司地址: </h3>
						<p>${emp.company_address}</p>  
						
					<h3>公司分類:</h3>
						<p>${emp.company_category}</p> 						
						
					<h3>公司電話:</h3>
						<p>${emp.company_phoneNumber}</p>
						
					<h3>公司統編:</h3>
						<p>${emp.company_taxID}</p>	
					
					<h3>統編文件:</h3>							
					<div class="profile-picture" style="text-align: center;">						
        				<img src="${pageContext.request.contextPath}/usersVIEW/userupload/${emp.company_taxID_docURL}" 							alt="Profile Image"/> 							
					</div>	
					<h3>負責人身份字號:</h3>
						 <p>${emp.user_national_id}</p>   												
					<h3>負責人身份證正面:</h3>
					<div class="profile-picture" style="text-align: center;">
						<img src="${pageContext.request.contextPath}/usersVIEW/userupload/${emp.idCard_pictureURL_1}" 							alt="Profile Image"/> 	
					</div>	
					<h3>負責人身份證反面:</h3>
					<div class="profile-picture" style="text-align: center;">
					<img src="${pageContext.request.contextPath}/usersVIEW/userupload/${emp.idCard_pictureURL_2}" 							alt="Profile Image"/>  
					</div>	
					<h3>審核狀態:</h3>
					<c:choose>
   						<c:when test="${emp.application_check == '0'}">
   							 <p>未審核</p>
   						</c:when>
   						<c:when test="${emp.application_check == '1'}">
   							 <p>通過</p>
   						</c:when>
    					<c:otherwise>
        					<p>否決</p>
    					</c:otherwise>
						</c:choose>   																	
		</div>
	</main>
	<script src="https://kit.fontawesome.com/4caf09a569.js" crossorigin="anonymous"></script>
</body>
</html>