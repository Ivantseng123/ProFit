<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*,com.ProFit.bean.usersBean.Pwd_reset_tokens"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>ProFit</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/usersVIEW/userVIEW.css"/> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css"/> 
  <link rel="stylesheet" href="https://cdn.datatables.net/2.1.4/css/dataTables.dataTables.css" />
</head>
<body>
	<jsp:include page="../model/header&sidebar.jsp"></jsp:include>	
	<!-- 主要內容區域 -->
    <!-- 只是一個範例，裡面布局可以更改 -->
    <main>
        <div class="dashboard-header">
            <h2>會員管理</h2>
            
        </div>
        <div>
            <table id="userTable" class="display">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>會員ID</th>
                        <th>會員帳號</th>
                        <th>Token</th>
                        <th>期限</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${tokens}" var="token" varStatus="s">
                    	<tr>
                        	<td>${token.tokenId}</td>
                        	<td>${token.userId}</td>
                        	<td>${token.getUser().userEmail}</td>
                        	<td>${token.userTokenHash}</td>
                        	<td>${token.expirationTime}</td>                 
                        	<td class="action-buttons">                                         
                            	<button class="delete" id="deleteTokenBtn">刪除</button>
                        	</td>
                   		</tr>
                   </c:forEach>	
                </tbody>
            </table>
        </div>
        
        <diV class="insertBTN">
        	<button class="insertbtn" onclick="togglePopup()">新增</button>
        </diV>
         <div class="navgation" style="text-align: center; margin-Top: 25px;" >
        	<a href="alluser" class="resetTokens" id="resetTokensBtn">
        	<button class="tohref">全部會員資訊</button></a>
            <a href="allempappl" class="employerAppl" id="employerApplBtn">
            <button class="tohref">企業申請資訊</button></a>
            <a href="allemppf" class="employerProfile" id="employerProfileBtn">
            <button class="tohref" >企業資訊</button></a>
        </diV>
        <div id="popupOverlay" class="overlay-container" style="z-index: 1002">
        	<div class="popup-box-inserttoken">
				<h2 style="color: orange;">新增Token</h2>
				<form class="form-container" method="post" action="insertToken">
					<label class="form-label" for="user_name"> 會員ID: </label> 
						<input
						class="form-input" type="text" placeholder="輸入會員ID"
						id="user_id" name="user_id" required/> 			
					<input type="submit" value="確定" class="btn-submit"/>
				</form>
				<br>
				<button class="btn-close-popup" onclick="togglePopup()">
					關閉</button>
			</div>
    	</div>
    	
    	
    </main>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdn.datatables.net/2.1.4/js/dataTables.js"></script>
	<script>
		$(function() {
			$(document).ready(function() {
				$('#userTable').DataTable({
					
					"language" : {
						"lengthMenu" : "顯示 _MENU_ 項結果",
						"zeroRecords" : "沒有符合的結果",
						"info" : "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
						"infoEmpty" : "顯示第 0 至 0 項結果，共 0 項",
						"search" : "搜尋:"
					}
				})
			});
				
			 $('tbody').on('click','#deleteTokenBtn',function(){
				 let currentRow = $(this).closest("tr");
				 let token_id = currentRow.find("td:eq(0)").text();
				 console.log(token_id);
				 
				 const deletedata = { 'token_id': token_id };
				 
				 fetch('deletetoken', {
				        method: 'POST',
				        headers: {
				            'Content-Type': 'application/json'
				        },
				        body: JSON.stringify(deletedata)
				    })
				    .then(response => response.text())  
				    .then(result => {
				    	
				    	$(this).closest('tr').remove();
				        //console.log(result);  
				       
				    })
				    .catch(error => {
				        console.error('Error:', error);
				        
				    });
				 
			 });
			 
				 
			
		})
		function togglePopup() {
            const overlay = document.getElementById('popupOverlay');
            overlay.classList.toggle('show');
        }
	</script>
</body>
</html>