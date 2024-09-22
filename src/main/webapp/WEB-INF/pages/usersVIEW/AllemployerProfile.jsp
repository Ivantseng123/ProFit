<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*,com.ProFit.bean.usersBean.Employer_profile"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProFit</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/usersVIEW/userVIEW.css"/> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css"/> 
  <link rel="stylesheet" href="https://cdn.datatables.net/2.1.4/css/dataTables.dataTables.css" />
</head>
<style>
.input-row{
    width: 100%;
    display: flex; 
    align-items: center; 
    gap: 10px;
}
.input-row-picture{
    width: 100%;
    height : 30%;
    display: flex; 
    align-items: center; 
    gap: 10px;
}
  </style>
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
                        <th>公司名稱</th>
                        <th>公司電話</th>
                        <th>公司地址</th>                      
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${emps}" var="emp" varStatus="s">
                    	<tr>
                    		<td>${emp.employerProfileId}</td>
							<td>${emp.userId}</td>
							<td>${emp.getUser().userEmail}</td>
							<td>${emp.companyName}</td>
							<td>${emp.companyPhoneNumber}</td>
							<td>${emp.companyAddress}</td>                                  	                               
                        	<td class="action-buttons">
                        		<a style="text-decoration:none;" href="getemppf?employer_profile_id=${emp.employerProfileId}&action=search">
                        		<button class="view" id="viewUserBtn">查看</button>
                        		</a>
                            	<a style="text-decoration:none;" href="getemppf?employer_profile_id=${emp.employerProfileId}&action=edit">
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
                		<th>會員ID</th>
                		<th>會員帳號</th>
                		<th>公司名稱</th>
                		<th>公司電話</th>
                		<th>公司地址</th>               		
           			</tr>
        		</tfoot>
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
            <a href="alltoken" class="employerProfile" id="employerProfileBtn">
            <button class="tohref" >重設密碼資訊</button></a>
        </diV>
        <div id="popupOverlay" class="overlay-container" style="z-index: 1002">
        	<div class="popup-box-insertEmpPf">
				<h2 style="color: orange;">新增企業資訊</h2>
				<form class="form-group" style="margin:0; overflow: auto" method="post" action="insertemppf">
					
						<label class="form-label" for="user_id">會員ID:</label> 
							<input
							class="form-input-insert" type="text" placeholder="輸入會員ID"
							id="user_id" name="user_id" required> 
						<label class="form-label"for="company_name">公司名稱:</label>
							<input class="form-input-insert" type="text" placeholder="公司名稱" 
							id="company_name" name="company_name" required>				
						<label class="form-label"for="company_phoneNumber">公司電話:</label> 
							<input class="form-input-insert" type="text" placeholder="輸入公司電話" 
							id="company_phoneNumber" name="company_phoneNumber" required>
						<label class="form-label"for="company_taxID">公司統編:</label> 
							<input class="form-input-insert" type="text" placeholder="輸入公司統編" 
							id="company_taxID" name="company_taxID" required>
													
						<label class="form-label" for="company_address">公司地址:</label>
						<select name="company_address">
							<option>臺北市</option><option>新北市</option><option>基隆市</option>
							<option>桃園市</option><option>桃園市</option><option>新竹縣</option>
							<option>新竹市</option><option>宜蘭縣</option><option>臺中市</option>
							<option>苗栗縣</option><option>彰化縣</option><option>南投縣</option>
							<option>雲林縣</option><option>高雄市</option><option>臺南市</option>
							<option>嘉義市</option><option>嘉義縣</option><option>屏東縣</option>
							<option>澎湖縣</option><option>花蓮縣</option><option>臺東縣</option>
						</select>  
							<input
						class="form-input-insert" type="text" placeholder="輸入公司地址"
						id="company_address" name="company_address1" required> 
						<label class="form-label"for="company_category">公司分類:</label> 
						<select name="company_category" style="margin-bottom: 20px">
							<option>農、林、漁、牧業</option><option>礦業及土石採取業</option><option>製造業</option>
							<option>水電燃氣業</option><option>營造及工程業</option><option>批發、零售及餐飲業</option>
							<option>運輸、倉儲及通信業</option><option>金融、保險及不動產業</option>
							<option>專業、科學及技術服務業</option>
							<option>文化、運動、休閒及其他服務業</option><option>其他</option>
						</select>
									
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
				 let table = $('#userTable').DataTable({
					
					"language" : {
						"lengthMenu" : "顯示 _MENU_ 項結果",
						"zeroRecords" : "沒有符合的結果",
						"info" : "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
						"infoEmpty" : "顯示第 0 至 0 項結果，共 0 項",
						"search" : "搜尋:"
					}
				});
				 
				 $('#userTable tfoot th').each(function (i) {
				        let title = $('#userTable thead th')
				            .eq($(this).index())
				            .text();
				       
				        $(this).html('<input type="text" placeholder="' + title + '" data-index="' + i + '" />');
				        
				    }); 
				 
				// 過濾事件處理程序
					$(table.table().container()).on('keyup', 'tfoot input', function () {
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
				
				 // 绑定 change 事件到文件输入元素
		        $('.file-uploader_company').on('change', function() {
		        	let fileInput = $(this)[0];
		        	let previewImage = $('#companyPreview');

		            // 确保有文件被选择
		            if (fileInput.files && fileInput.files[0]) {
		            	let reader = new FileReader();

		                reader.onload = function(e) {
		                    // 当文件读取完成后，将结果作为图片的 src
		                    previewImage.attr('src', e.target.result);
		                }

		                // 读取图片文件
		                reader.readAsDataURL(fileInput.files[0]);
		            }
		        });			
			});
				
			 $('tbody').on('click','#deleteUserBtn',function(){
				 let currentRow = $(this).closest("tr");
				 let employer_profile_id = currentRow.find("td:eq(0)").text();
				 console.log(user_id);
				 
				 const deletedata = { 'employer_profile_id': employer_profile_id };
				 
				 fetch('deleteemppf', {
				        method: 'POST',
				        headers: {
				            'Content-Type': 'application/json'
				        },
				        body: JSON.stringify(deletedata)
				    })
				    .then(response => response.text())  
				    .then(result => {
				    	
				    	$(this).closest('tr').remove();
				   
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