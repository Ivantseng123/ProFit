<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*,com.ProFit.bean.usersBean.Users"%>
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
                        <th>會員帳號</th>
                        <th>會員姓名</th>
                        <th>會員密碼</th>
                        <th>會員身分</th>
                        <th>會員註冊時間</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${users}" var="user" varStatus="s">
                    	<tr>
                        	<td>${user.userId}</td>
                        	<td>${user.userEmail}</td>
                        	<td>${user.userName}</td>
                        	<td>${user.userPasswordHash}</td>
                        	<c:choose>
   							 <c:when test="${user.userIdentity == '1'}">
   							 	<td>應徵者</td>
   							 </c:when>
   							 <c:when test="${user.userIdentity == '2'}">
   							 	<td>應徵者/企業主</td>
   							 </c:when>
    						<c:otherwise>
        						<td>管理員</td>
    						</c:otherwise>
							</c:choose>                        
                        	<td>${user.userRegisterTime}</td>
                        	<td class="action-buttons">
                        		<a style="text-decoration:none;" href="getuser?user_id=${user.userId}&action=search">
                        		<button class="view" id="viewUserBtn">查看</button>
                        		</a>
                            	<a style="text-decoration:none;" href="getuser?user_id=${user.userId}&action=edit">
                            	<button class="edit" id="editUserBtn">編輯</button>
                            	</a>
                            	<button class="delete" id="deleteUserBtn">刪除</button>
                        	</td>
                   		</tr>
                   </c:forEach>	
                </tbody>
                <tfoot>
            		<tr>
                		<th>ID</th>
                		<th>會員帳號</th>
                		<th>會員姓名</th>
                		<th>會員密碼</th>
                		<th>會員身分</th>
                		<th>會員註冊時間</th>
           			</tr>
        		</tfoot>
            </table>
        </div>
        
        <diV class="insertBTN">
        	<button class="insertbtn" onclick="togglePopup()">新增</button>
        </diV>
         <diV class="navgation" style="text-align: center; margin-top: 50px" >
        	<a href="alltoken" class="resetTokens" id="resetTokensBtn">
        	<button class="tohref">重設密碼資訊</button></a>
            <a href="allempappl" class="employerAppl" id="employerApplBtn">
            <button class="tohref">企業申請資訊</button></a>
            <a href="allemppf" class="employerProfile" id="employerProfileBtn">
            <button class="tohref" >企業資訊</button></a>
        </diV>
        <div id="popupOverlay" class="overlay-container" style="z-index: 1002">
        	<div class="popup-box-insertuser">
				<h2 style="color: orange;">新增會員</h2>
				<form class="form-group" style="height:700px;margin:0; overflow: auto" method="post" action="insertuser">
					<label class="form-label" for="user_name"> 會員姓名: </label> 
						<input
						class="form-input-insert" type="text" placeholder="輸入會員姓名	"
						id="user_name" name="user_name" required> 
					<label class="form-label"for="user_email">會員信箱:</label> 
						<input class="form-input-insert" type="email" placeholder="輸入會員信箱" 
						id="user_email" name="user_email" required pattern="[a-z0-9._%+\-]+@[a-z0-9.\-]+\.[a-z]{2,}$">
					<label class="form-label" for="user_password"> 會員密碼: </label> 
						<input
						class="form-input-insert" type="password" placeholder="輸入會員密碼"
						id="user_password" name="user_password" required>
					<label class="form-label" for="ConfirmPassword"> 確認密碼: </label> 	
						<input id="ConfirmPassword" class="form-input-insert" type="password" placeholder="確認會員密碼"required="required" oninput="setCustomValidity('');" onchange="if(document.getElementById('user_password').value != document.getElementById('ConfirmPassword').value){setCustomValidity('密碼不吻合');}"/> 
					<label class="form-label"for="user_phonenumber">會員手機號碼:</label> 
						<input class="form-input-insert" type="text" placeholder="輸入會員手機號碼(格式:09XX-000000)" 
						id="user_phonenumber" name="user_phonenumber" required maxlength="11" pattern="09\d{2}-\d{6}">
					<label class="form-label"for="user_city">會員居住城市:</label> 
					<select name="user_city">
						<option>臺北市</option><option>新北市</option><option>基隆市</option>
						<option>桃園市</option><option>新竹縣</option><option>新竹市</option>
						<option>宜蘭縣</option><option>臺中市</option><option>苗栗縣</option>
						<option>彰化縣</option><option>南投縣</option><option>雲林縣</option>
						<option>高雄市</option><option>臺南市</option><option>嘉義市</option>
						<option>嘉義縣</option><option>屏東縣</option><option>澎湖縣</option>
						<option>花蓮縣</option><option>臺東縣</option>
					</select> 
					<input type="submit" value="確定" class="btn-submit" style="margin-top: 20px"/>
				<button class="btn-close-popup" onclick="togglePopup()">
					關閉</button>
				</form>			
			</div>
    	</div>
    </main>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdn.datatables.net/2.1.4/js/dataTables.js"></script>
	<script>
		$(function() {
			$(document).ready(function() {
				let table = $('#userTable').DataTable({
					
					"language" : {
						"lengthMenu" : "顯示 _MENU_ 項結果",
						"zeroRecords" : "沒有符合的結果",
						"info" : "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
						"infoEmpty" : "顯示第 0 至 0 項結果，共 0 項",
						"search" : "搜尋:"
					}
				})
				
				$('#userTable tfoot th').each(function (i) {
					let title = $('#userTable thead th')
			            .eq($(this).index())
			            .text();
			        if (i === 4) { // JavaScript 索引從 0 開始，第五列的索引是 4
			            // 如果是第五列，生成 `<select>` 元素
			            $(this).html(
			                '<select data-index="' + i + '">' +
			                    '<option value="應徵者">應徵者</option>' +
			                    '<option value="應徵者/企業主">應徵者/企業主</option>' +
			                    '<option value="管理員">管理員</option>' +
			                '</select>'
			            );
			        } else {
			            // 其他列生成 `<input>` 元素
			            $(this).html(
			                '<input type="text" placeholder="' + title + '" data-index="' + i + '" />'
			            );
			        }
			    });
				
				// 過濾事件處理程序
				$(table.table().container()).on('keyup change', 'tfoot input, tfoot select', function () {
				    // 取得所需的列索引
				    let index = $(this).data('index');
				    
				    // 根據 input 或 select 的值進行搜尋
				    let value = $(this).val();
				    
				    // 應用搜尋並重新繪製表格
				    table
				        .column(index)
				        .search(value)
				        .draw();
				});

			});
			
			
				
			 $('tbody').on('click','#deleteUserBtn',function(){
				 let currentRow = $(this).closest("tr");
				 let user_id = currentRow.find("td:eq(0)").text();
				 console.log(user_id);
				 
				 const deletedata = { 'user_id': user_id };
				 
				 fetch('deleteuser', {
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