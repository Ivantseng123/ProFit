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
        <div>
		<h2 style="color: orange;">更新會員資訊</h2>
		<form class="form-container" method="post" action="UpdateUser?action=nopwd" enctype='multipart/form-data'>
				<input type="hidden" name="user_pictureURL" value="${user.user_pictureURL}">
				<label class="form-label" for=""> 會員頭貼: </label>
				<div class="profile-picture" style="">
						<c:choose>
   							 <c:when test="${not empty user.user_pictureURL}">
        						<img id="profileImagePreview" src="${pageContext.request.contextPath}/usersVIEW/userupload/${user.user_pictureURL}" alt="Profile Image"/>
   							 </c:when>
    						<c:otherwise>
        						<img id="profileImagePreview" src="${pageContext.request.contextPath}/usersVIEW/default_user_picture.png" 									alt="Default Profile Image"  />
    						</c:otherwise>
						</c:choose> 
				</div>
				<div>		
						<input class="file-uploader" type="file" name="profileImage"
							accept="image/*" />
				</div>			
					<label class="form-label" for="user_name"> 會員ID: </label> 
						<input
						class="form-input" type="text"
						id="user_id" name="user_id" value="${user.user_id}" readonly> 
						
					<label class="form-label" for="user_name"> 會員姓名: </label> 
						<input
						class="form-input" type="text" placeholder="修改會員姓名	"
						id="user_name" name="user_name" value="${user.user_name}" required> 
						
					<label class="form-label"for="user_email">會員信箱:</label> 
						<input class="form-input" type="email" placeholder="修改會員信箱" 
						id="user_email" name="user_email" value="${user.user_email}" required>
						
					<label class="form-label" for="user_password"> 會員密碼: </label> 
						<input
						class="form-input" type="password" placeholder="修改會員密碼"
						id="user_password" name="user_passwordHash" value="${user.user_passwordHash}" readonly>
						
					<label class="form-label" for="user_phonenumber">會員手機號碼:</label> 
						<input class="form-input" type="text" placeholder="修改會員手機號碼" 
						id="user_phonenumber" name="user_phoneNumber" value="${user.user_phoneNumber}" required>
				
					<label class="form-label" for="user_city">會員居住城市:</label> 
						<select class="city-option" name="user_city">
							<option value="臺北市">臺北市</option><option value="新北市">新北市</option><option value="基隆市">基隆市</option>
							<option value="桃園市">桃園市</option><option value="桃園市">桃園市</option><option value="新竹縣">新竹縣</option>
							<option value="新竹市">新竹市</option><option value="宜蘭縣">宜蘭縣</option><option value="臺中市">臺中市</option>
							<option value="苗栗縣">苗栗縣</option><option value="彰化縣">彰化縣</option><option value="南投縣">南投縣</option>
							<option value="雲林縣">雲林縣</option><option value="高雄市">高雄市</option><option value="臺南市">臺南市</option>
							<option value="嘉義市">嘉義市</option><option value="嘉義縣">嘉義縣</option><option value="屏東縣">屏東縣</option>
							<option value="澎湖縣">澎湖縣</option><option value="花蓮縣">花蓮縣</option><option value="臺東縣">臺東縣</option>
						</select> 	
					
					<label class="form-label"for="user_identity">會員身份:</label>
						<div class="input-radio">
							<c:choose>
								<c:when test="${user.user_identity == 1}">
									<input type="radio" id="identityChoice1" name="user_identity" value="1" checked/>
									<label for="identityChoice1">應徵者</label> 							
									<input type="radio" id="identityChoice2" name="user_identity" value="2"/> 
									<label for="identityChoice2">應徵者＋企業主</label> 							
									<input type="radio" id="identityChoice3" name="user_identity" value="3" /> 
									<label for="identityChoice3">管理員</label>
								</c:when>
								<c:when test="${user.user_identity == 2}">
									<input type="radio" id="identityChoice1" name="user_identity" value="1" />
									<label for="identityChoice1">應徵者</label> 							
									<input type="radio" id="identityChoice2" name="user_identity" value="2" checked/> 
									<label for="identityChoice2">應徵者＋企業主</label> 							
									<input type="radio" id="identityChoice3" name="user_identity" value="3" /> 
									<label for="identityChoice3">管理員</label>
								</c:when>
								<c:otherwise>
        							<input type="radio" id="identityChoice1" name="user_identity" value="1" />
									<label for="identityChoice1">應徵者</label> 							
									<input type="radio" id="identityChoice2" name="user_identity" value="2" /> 
									<label for="identityChoice2">應徵者＋企業主</label> 							
									<input type="radio" id="identityChoice3" name="user_identity" value="3" checked/> 
									<label for="identityChoice3">管理員</label>
    							</c:otherwise>
							</c:choose> 
						</div>
						
					<label class="form-label"for="user_balance">會員餘額:</label> 
						<input class="form-input" type="number" placeholder="修改會員餘額" 
						id="user_balance" name="user_balance" value="${user.user_balance}" required>	
								
					<label class="form-label"for="freelancer_location_prefer">會員工作地點偏好:</label> 
						<select class="city-prefer-option" name="freelancer_location_prefer" style="margin-bottom: 20px">
						<option value="臺北市">臺北市</option><option value="新北市">新北市</option><option value="基隆市">基隆市</option>
						<option value="桃園市">桃園市</option><option value="桃園市">桃園市</option><option value="新竹縣">新竹縣</option>
						<option value="新竹市">新竹市</option><option value="宜蘭縣">宜蘭縣</option><option value="臺中市">臺中市</option>
						<option value="苗栗縣">苗栗縣</option><option value="彰化縣">彰化縣</option><option value="南投縣">南投縣</option>
						<option value="雲林縣">雲林縣</option><option value="高雄市">高雄市</option><option value="臺南市">臺南市</option>
						<option value="嘉義市">嘉義市</option><option value="嘉義縣">嘉義縣</option><option value="屏東縣">屏東縣</option>
						<option value="澎湖縣">澎湖縣</option><option value="花蓮縣">花蓮縣</option><option value="臺東縣">臺東縣</option>
					</select> 	
					<label class="form-label"for="freelancer_exprience">會員工作經驗:</label>
						<select class="exprience-option" name="freelancer_exprience" style="margin-bottom: 20px">
						<option value="無工作經驗">無工作經驗</option>
						<option value="0-1年工作經驗">0-1年工作經驗</option>
						<option value="1-2年工作經驗">1-2年工作經驗</option>
						<option value="2-3年工作經驗">2-3年工作經驗</option>
						<option value="3-4年工作經驗">3-4年工作經驗</option>
						<option value="4-5年工作經驗">4-5年工作經驗</option>
						<option value="5-6年工作經驗">5-6年工作經驗</option>
						<option value="7-8年工作經驗">7-8年工作經驗</option>
						<option value="8-9年工作經驗">8-9年工作經驗</option>
						<option value="9-10年工作經驗">9-10年工作經驗</option>
						<option value="10年以上年工作經驗">10年以上年工作經驗</option>			
					</select> 								
					<label class="form-label"for="freelancer_identity">會員接案身份:</label> 
						<div class="input-radio">
							<c:choose>
								<c:when test="${user.freelancer_identity == '個人兼職'}">
									<input type="radio" id="identityChoice1" name="freelancer_identity" value="個人兼職" checked/>
									<label for="identityChoice1">個人兼職</label> 							
									<input type="radio" id="identityChoice2" name="freelancer_identity" value="工作室"/> 
									<label for="identityChoice2">工作室</label> 							
									<input type="radio" id="identityChoice3" name="freelancer_identity" value="學生" /> 
									<label for="identityChoice3">學生</label>	
								</c:when>
								<c:when test="${user.freelancer_identity == '工作室'}">
									<input type="radio" id="identityChoice1" name="freelancer_identity" value="個人兼職" />
									<label for="identityChoice1">個人兼職</label> 							
									<input type="radio" id="identityChoice2" name="freelancer_identity" value="工作室" checked/> 
									<label for="identityChoice2">工作室</label> 							
									<input type="radio" id="identityChoice3" name="freelancer_identity" value="學生" /> 
									<label for="identityChoice3">學生</label>	
								</c:when>
								<c:when test="${user.freelancer_identity == '學生'}">
									<input type="radio" id="identityChoice1" name="freelancer_identity" value="個人兼職" />
									<label for="identityChoice1">個人兼職</label> 							
									<input type="radio" id="identityChoice2" name="freelancer_identity" value="工作室"/> 
									<label for="identityChoice2">工作室</label> 							
									<input type="radio" id="identityChoice3" name="freelancer_identity" value="學生"  checked/> 
									<label for="identityChoice3">學生</label>	
								</c:when>
								<c:otherwise>
        							<input type="radio" id="identityChoice1" name="freelancer_identity" value="個人兼職" />
									<label for="identityChoice1">個人兼職</label> 							
									<input type="radio" id="identityChoice2" name="freelancer_identity" value="工作室"/> 
									<label for="identityChoice2">工作室</label> 							
									<input type="radio" id="identityChoice3" name="freelancer_identity" value="學生" /> 
									<label for="identityChoice3">學生</label>	
    							</c:otherwise>
							</c:choose> 					
						</div>		
							
					<label class="form-label"for="freelancer_profile_status">會員接案狀態:</label> 
						<div class="input-radio">
							<c:choose>
								<c:when test="${user.freelancer_profile_status == 0}">
									<input type="radio" id="statusChoice1" name="freelancer_profile_status" value="0" checked/>
									<label for="statusChoice1">關閉</label> 						
									<input type="radio" id="statusChoice2" name="freelancer_profile_status" value="1"/> 
									<label for="statusChoice2">開啓</label> 
								</c:when>
								<c:otherwise>
        							<input type="radio" id="statusChoice1" name="freelancer_profile_status" value="0"/>
									<label for="statusChoice1">關閉</label> 						
									<input type="radio" id="statusChoice2" name="freelancer_profile_status" value="1" checked/> 
									<label for="statusChoice2">開啓</label> 
    							</c:otherwise>
							</c:choose> 
						</div>	
							
					<label class="form-label"for="freelancer_disc">會員接案描述:</label>
						<textarea rows="10" cols="50" id="freelancer_disc" name="freelancer_disc">${user.freelancer_disc}</textarea>	 
					<label class="form-label"for="user_register_time">會員註冊時間:</label> 
						<input class="form-input" type="text" 
						id="user_register_time" name="user_register_time" value="${user.user_register_time}" readonly>		
					<input type="submit" value="確定" class="btn-submit" id="submitBtn"/>
		</form>
		<div style="text-align: center;">
			<button class="insertbtn" style="margin-top: 20px" onclick="togglePopup()">修改密碼</button>
		</div> 
		  <div id="popupOverlay" class="overlay-container" style="z-index: 1002">
        	<div class="popup-box-updatepwd">
				<h2 style="color: orange;">修改密碼</h2>
				<form class="form-group" style="height:200px; margin:0;" method="post" action="UpdateUser?action=updatepwd&user_id=${user.user_id}">
					<label class="form-label" for="user_password">會員密碼:</label> 
						<input
						class="form-input-insert" type="password" placeholder="修改會員密碼"
						id="user_password" name="user_password" required>
					<label class="form-label" for="ConfirmPassword">確認密碼:</label> 	
						<input id="ConfirmPassword" class="form-input-insert" type="password" placeholder="確認會員密碼"required="required" oninput="setCustomValidity('');" onchange="if(document.getElementById('user_password').value != document.getElementById('ConfirmPassword').value){setCustomValidity('密碼不吻合');}"/> 
					<input type="submit" value="確定" class="btn-submit" style="margin-top: 20px"/>
				<button class="btn-close-popup" onclick="togglePopup()">
					關閉</button>
				</form>			
			</div>
    	</div>
	</div>
	</main>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		$(function() {
	 	
			$(document).ready(function() {
			
				$('.file-uploader').on('change', function() {
					var fileInput = $(this)[0];
					var previewImage = $('#profileImagePreview');

					
					if (fileInput.files && fileInput.files[0]) {
						var reader = new FileReader();

						reader.onload = function(e) {
							
							previewImage.attr('src', e.target.result);
						}

						
						reader.readAsDataURL(fileInput.files[0]);
					}
				});
			});
			
			let city = "${user.user_city}";
			$('.city-option option').each(function () {
				if ($(this).val() === city) { // 使用 `$(this).val()` 獲取 `<option>` 的值
			        $(this).prop('selected', true); // 使用 `prop` 方法設置 `selected` 屬性
			    } 
			})
			
			let freelancer_location_prefer = "${user.freelancer_location_prefer}";
			$('.city-prefer-option option').each(function () {
				if ($(this).val() === freelancer_location_prefer) { // 使用 `$(this).val()` 獲取 `<option>` 的值
			        $(this).prop('selected', true); // 使用 `prop` 方法設置 `selected` 屬性
			    } 
			})
			
			let exprience = "${user.freelancer_exprience}";
			$('.exprience-option option').each(function () {
				if ($(this).val() === exprience) { // 使用 `$(this).val()` 獲取 `<option>` 的值
			        $(this).prop('selected', true); // 使用 `prop` 方法設置 `selected` 屬性
			    } 
			})

		})

		function togglePopup() {
			const overlay = document.getElementById('popupOverlay');
			overlay.classList.toggle('show');
		}
	</script>
	<script src="https://kit.fontawesome.com/4caf09a569.js" crossorigin="anonymous"></script>
</body>
</html>