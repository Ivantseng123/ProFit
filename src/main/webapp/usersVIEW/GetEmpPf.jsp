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
<jsp:useBean id="emp" scope="request" class="com.ProFit.bean.usersBean.Employer_profile"/>
</head>
<body>
	<jsp:include page="../model/header&sidebar.jsp"></jsp:include>
     <main>
        <div class="dashboard-header">
            <h2>會員管理</h2>
        </div>
		<h2 style="color: orange;">企業申請資訊</h2>
		<div class="form-container" >
					<h3>企業資訊ID: </h3> 					
						<p>${emp.employerProfileId}</p>
					<h3>會員ID: </h3> 
						<p>${emp.userId}</p>
						
					<h3>公司名稱:</h3>
						<p>${emp.companyName}</p> 
				
					<h3>公司地址: </h3>
						<p>${emp.companyAddress}</p>  
						
					<h3>公司分類:</h3>
						<p>${emp.companyCategory}</p> 						
						
					<h3>公司電話:</h3>
						<p>${emp.companyPhoneNumber}</p>
						
					<h3>公司統編:</h3>
						<p>${emp.companyTaxID}</p>	
										
					<h3>公司員工數:</h3>
						<p>${emp.companyNumberOfemployee}</p>
						
					<h3>公司資本額:</h3>
						<p>${emp.companyCaptital}</p>
						
					<h3>公司簡介:</h3>
						<p>${emp.companyDescription}</p>	
								
					<h3>公司照片:</h3>
					<div style="text-align: center;">
					<img src="${emp.companyPhotoURL}" alt="Profile Image"/> 	
				 	</div>																
		</div>
	</main>
	<script src="https://kit.fontawesome.com/4caf09a569.js" crossorigin="anonymous"></script>
</body>
</html>