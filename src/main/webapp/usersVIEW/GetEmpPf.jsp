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
						<p>${emp.employer_profile_id}</p>
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
										
					<h3>公司員工數:</h3>
						<p>${emp.company_numberOfemployee}</p>
						
					<h3>公司資本額:</h3>
						<p>${emp.company_captital}</p>
						
					<h3>公司簡介:</h3>
						<p>${emp.company_description}</p>	
								
					<h3>公司照片:</h3>
					<div style="text-align: center;">
					<img src="${pageContext.request.contextPath}/usersVIEW/userupload/${emp.company_photoURL}" 							alt="Profile Image"/> 	
				 	</div>																
		</div>
	</main>
	<script src="https://kit.fontawesome.com/4caf09a569.js" crossorigin="anonymous"></script>
</body>
</html>