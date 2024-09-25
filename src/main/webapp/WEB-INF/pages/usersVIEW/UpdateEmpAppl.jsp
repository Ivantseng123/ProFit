<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@page import="java.util.*,com.ProFit.bean.usersBean.Employer_application" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="UTF-8">
					<title>ProFit</title>
					<link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css" />
					<link rel="stylesheet" href="${pageContext.request.contextPath}/usersVIEW/userVIEW.css" />
					<jsp:useBean id="emp" scope="request" class="com.ProFit.bean.usersBean.Employer_application" />
				</head>

				<body>
					<jsp:include page="../model/header&sidebar.jsp"></jsp:include>
					<main>
						<div class="dashboard-header">
							<h2>會員管理</h2>
						</div>
						<div>
							<h2 style="color: orange;">更新企業申請</h2>
							<form class="form-container" method="post" action="updateempappl">
								<input type="hidden" id="idCard_pictureURL_1" name="idCard_pictureURL_1" value="${emp.idCardPictureURL1}">
								<input type="hidden" id="idCard_pictureURL_2" name="idCard_pictureURL_2" value="${emp.idCardPictureURL2}">
								<input type="hidden" id="company_taxID_docURL" name="company_taxID_docURL" value="${emp.companyTaxIdDocURL}">
								<label class="form-label" for="employer_application_id"> 企業申請ID: </label>
								<input class="form-input" type="text" id="employer_application_id"
									name="employer_application_id" value="${emp.employerApplicationId}" readonly>
								<label class="form-label" for="user_id"> 會員ID: </label>
								<input class="form-input" type="text" id="user_id" name="user_id" value="${emp.userId}"
									readonly>
								<label class="form-label" for="company_name">公司名稱:</label>
								<input class="form-input" type="text" placeholder="修改公司名稱" id="company_name"
									name="company_name" value="${emp.companyName}" required>

								<label class="form-label" for="user_password">公司地址:</label>
								<select name="company_address" class="address-option">
									<option value="臺北市">臺北市</option>
									<option value="新北市">新北市</option>
									<option value="基隆市">基隆市</option>
									<option value="桃園市">桃園市</option>
									<option value="桃園市">桃園市</option>
									<option value="新竹縣">新竹縣</option>
									<option value="新竹市">新竹市</option>
									<option value="宜蘭縣">宜蘭縣</option>
									<option value="臺中市">臺中市</option>
									<option value="苗栗縣">苗栗縣</option>
									<option value="彰化縣">彰化縣</option>
									<option value="南投縣">南投縣</option>
									<option value="雲林縣">雲林縣</option>
									<option value="高雄市">高雄市</option>
									<option value="臺南市">臺南市</option>
									<option value="嘉義市">嘉義市</option>
									<option value="嘉義縣">嘉義縣</option>
									<option value="屏東縣">屏東縣</option>
									<option value="澎湖縣">澎湖縣</option>
									<option value="花蓮縣">花蓮縣</option>
									<option value="臺東縣">臺東縣</option>
								</select>
								<input class="form-input" type="text" placeholder="修改公司地址" id="company_address"
									name="company_address1"
									value="${fn:substring(emp.companyAddress, 3, fn:length(emp.companyAddress))}"
									required>

								<label class="form-label" for="company_category">公司分類:</label>
								<select name="company_category" class="category-option" style="margin-bottom: 20px">
									<option value="農、林、漁、牧業">農、林、漁、牧業</option>
									<option value="礦業及土石採取業">礦業及土石採取業</option>
									<option value="製造業">製造業</option>
									<option value="水電燃氣業">水電燃氣業</option>
									<option value="營造及工程">營造及工程業</option>
									<option value="批發、零售及餐飲業">批發、零售及餐飲業</option>
									<option value="運輸、倉儲及通信">運輸、倉儲及通信業</option>
									<option value="金融、保險及不動產業">金融、保險及不動產業</option>
									<option value="專業、科學及技術服務業">專業、科學及技術服務業</option>
									<option value="文化、運動、休閒及其他服務業">文化、運動、休閒及其他服務業</option>
									<option value="其他">其他</option>
								</select>
								<label class="form-label" for="company_phoneNumber">公司電話:</label>
								<input class="form-input-insert" type="text" placeholder="輸入公司電話"
									id="company_phoneNumber" name="company_phoneNumber"
									value="${emp.companyPhoneNumber}" required>
								<label class="form-label" for="company_taxID">公司統編:</label>
								<input class="form-input" type="text" placeholder="修改會員餘額" id="company_taxID"
									name="company_taxID" value="${emp.companyTaxID}" required>

								<label class="form-label" for="taxID-picture">統編文件:</label>
								<div class="taxID-picture" style="">
									<img class="taxID-img" id="taxIDImagePreview" 
										src="${emp.companyTaxIdDocURL}"
										alt="統編證明文件影本" />
								</div>
								<div>
									<input class="file-uploader_taxID" id="fileInput" type="file" name="taxIDImage" accept="image/*" />
								</div>
								<label class="form-label" for="user_national_id">負責人身份證字號:</label>
								<input class="form-input" type="text" placeholder="修改負責人身份證字號" id="user_national_id"
									name="user_national_id" value="${emp.userNationalId}" required>
								<label class="form-label" for="nationalID-img">負責人身份證正面:</label>
								<div class="national_id-picture" style="">
									<img class="nationalID-img" id="nationalIDPreview1"
										src="${emp.idCardPictureURL1}"
										alt="負責人身分證影本" />
								</div>
								<div>
									<input class="file-uploader_nationalID1" id="fileInput1" type="file" name="nationalIDImage1"
										accept="image/*" />
								</div>
								<label class="form-label" for="nationalID-img">負責人身份證正面:</label>
								<div class="national_id-picture" style="">
									<img class="nationalID-img" id="nationalIDPreview2"
										src="${emp.idCardPictureURL2}"
										alt="負責人身分證影本" />
								</div>
								<div>
									<input class="file-uploader_nationalID2" id="fileInput2" type="file" name="nationalIDImage2"
										accept="image/*" />
								</div>
								<button class="insertbtn" type="button" style="margin-top: 20px; margin-bottom: 20px"
									id="fetchButton">更新申請圖片</button>
								<input type="submit" value="確定" class="btn-submit" />
							</form>
						</div>
					</main>
					<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
					<script src="https://www.gstatic.com/firebasejs/9.21.0/firebase-app-compat.js"></script>
					<script src="https://www.gstatic.com/firebasejs/9.21.0/firebase-storage-compat.js"></script>
					<script>
						$(function () {
							$('.file-uploader_taxID').on('change', function () {
								let fileInput = $(this)[0];
								let previewImage = $('#taxIDImagePreview');

								if (fileInput.files && fileInput.files[0]) {
									let reader = new FileReader();
									reader.onload = function (e) {
										previewImage.attr('src', e.target.result);
									}
									reader.readAsDataURL(fileInput.files[0]);
								}
							});

							$('.file-uploader_nationalID1').on('change', function () {
								let fileInput = $(this)[0];
								let previewImage = $('#nationalIDPreview1');

								if (fileInput.files && fileInput.files[0]) {
									let reader = new FileReader();
									reader.onload = function (e) {
										previewImage.attr('src', e.target.result);
									}
									reader.readAsDataURL(fileInput.files[0]);
								}
							});

							$('.file-uploader_nationalID2').on('change', function () {
								let fileInput = $(this)[0];
								let previewImage = $('#nationalIDPreview2');

								if (fileInput.files && fileInput.files[0]) {
									let reader = new FileReader();
									reader.onload = function (e) {
										previewImage.attr('src', e.target.result);
									}
									reader.readAsDataURL(fileInput.files[0]);
								}
							});

							let category = "${emp.companyCategory}";

							$('.category-option option').each(function () {
								if ($(this).val() === category) { // 使用 `$(this).val()` 獲取 `<option>` 的值
									$(this).prop('selected', true); // 使用 `prop` 方法設置 `selected` 屬性
								}
							})

							let address = "${fn:substring(emp.companyAddress, 0, 3)}";

							$('.address-option option').each(function () {
								if ($(this).val() === address) { // 使用 `$(this).val()` 獲取 `<option>` 的值
									$(this).prop('selected', true); // 使用 `prop` 方法設置 `selected` 屬性
								}
							})

						})
						
						document.getElementById('fetchButton').addEventListener('click', () => {
							fetch('FirebaseConfigServ')
								.then(response => response.json())
								.then(firebaseConfig => {
									// 初始化 Firebase
									const app = firebase.initializeApp(firebaseConfig);
									const storage = firebase.storage();
									
									// 保存隐藏输入框的原始值
							        const hiddenInputIds = ['company_taxID_docURL', 'idCard_pictureURL_1', 'idCard_pictureURL_2'];
							        const originalValues = hiddenInputIds.map(id => document.getElementById(id).value);
									
									window.uploadImage = function () {
										console.log('uploadImage function called');
										const fileInput = document.getElementById('fileInput');
										const fileInput1 = document.getElementById('fileInput1');
										const fileInput2 = document.getElementById('fileInput2');
										
										
							            const fileInputElements = [fileInput, fileInput1, fileInput2];
							            const hiddenInputs = hiddenInputIds.map(id => document.getElementById(id));
										
							            fileInputElements.forEach((fileInput, index) => {
							            	
							            	const file = fileInput.files[0];
							            	
											if (file) {
												const storageRef = storage.ref('userUpload/' + file.name);
												const uploadTask = storageRef.put(file);

												
												uploadTask.on('state_changed',
													function (snapshot) {

													},
													function (error) {

													},
													function () {
														uploadTask.snapshot.ref.getDownloadURL().then(function (downloadURL) {


															 document.getElementById(hiddenInputIds[index]).value = downloadURL;
														});
													}
												);
											} else {
												hiddenInputs[index].value = originalValues[index];
												console.log(`No file selected for input ${index + 1}`);
											}
										});
									};

									
									window.uploadImage();
								})
								.catch(error => {
									console.error('Error fetching Firebase config:', error);
								});
						});

					</script>
					<script src="https://kit.fontawesome.com/4caf09a569.js" crossorigin="anonymous"></script>
				</body>

				</html>