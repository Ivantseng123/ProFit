<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,com.ProFit.bean.usersBean.Employer_profile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>ProFit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/usersVIEW/userVIEW.css" />
<jsp:useBean id="emp" scope="request"
	class="com.ProFit.bean.usersBean.Employer_profile" />
</head>

<body>
	<jsp:include page="../model/header&sidebar.jsp"></jsp:include>
	<main>
		<div class="dashboard-header">
			<h2>會員管理</h2>
		</div>
		<div>
			<h2 style="color: orange;">更新企業資訊</h2>
			<form class="form-container" method="post" action="updateemppf">
				<input type="hidden" id="company_photoURL" name="company_photoURL"
					value="${emp.companyPhotoURL}"> <label class="form-label"
					for="employer_profile_id"> 企業資訊ID: </label> <input
					class="form-input" type="text" id="employer_profile_id"
					name="employer_profile_id" value="${emp.employerProfileId}"
					readonly> <label class="form-label" for="user_id">
					會員ID: </label> <input class="form-input" type="text" id="user_id"
					name="user_id" value="${emp.userId}" readonly> <label
					class="form-label" for="company_name">公司名稱:</label> <input
					class="form-input" type="text" placeholder="修改公司名稱"
					id="company_name" name="company_name" value="${emp.companyName}"
					required> <label class="form-label" for="company_address">公司地址:</label>
				<select name="company_address" id="company_address"
					class="address-option">
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
				</select> <input class="form-input" type="text" placeholder="修改公司地址"
					id="company_address" name="company_address1"
					value="${fn:substring(emp.companyAddress, 3, fn:length(emp.companyAddress))}
"
					required> <label class="form-label" for="company_category">公司分類:</label>
				<select name="company_category" class="category-option"
					style="margin-bottom: 20px">
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
				</select> <label class="form-label" for="company_phoneNumber">公司電話:</label> <input
					class="form-input-insert" type="text" placeholder="輸入公司電話"
					id="company_phoneNumber" name="company_phoneNumber"
					value="${emp.companyPhoneNumber}" required> <label
					class="form-label" for="company_taxID">公司統編:</label> <input
					class="form-input" type="text" placeholder="修改公司統編"
					id="company_taxID" name="company_taxID" value="${emp.companyTaxID}"
					required> <label class="form-label"
					for="company_numberOfemployee">公司員工數:</label> <select
					id="numberOfemployee" class="numOfemp-option"
					name="company_numberOfemployee" style="margin-bottom: 20px">
					<option value="1-10">1-10</option>
					<option value="11-50">11-50</option>
					<option value="51-200">51-200</option>
					<option value="201-500">201-500</option>
					<option value="501-1000">501-1000</option>
					<option value="1001-5000">1001-5000</option>
					<option value="5000以上">5000以上</option>
				</select> <label class="form-label" for="company_captital">公司資本額:</label> <input
					class="form-input" type="text" placeholder="修改公司資本額"
					id="company_captital" name="company_captital"
					value="${emp.companyCaptital}"> <label class="form-label"
					for="company_description">公司簡介:</label>
				<textarea rows="10" cols="50" id="company_description"
					name="company_description">${emp.companyDescription}</textarea>
				<label class="form-label" for="nationalID-img">公司照片:</label>
				<div class="company-picture" style="">
					<img class="company-img" id="companyImgPreview"
						src="${emp.companyPhotoURL}" alt="公司照片" />
				</div>
				<div>
					<input class="file-uploader_companyImg" id="fileInput" type="file"
						name="companyPhoto" accept="image/*" />
				</div>
				<button class="insertbtn" type="button"
					style="margin-top: 20px; margin-bottom: 20px" id="fetchButton">上傳公司照片</button>
				<input type="submit" value="確定" class="btn-submit" />
			</form>
		</div>
	</main>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script
		src="https://www.gstatic.com/firebasejs/9.21.0/firebase-app-compat.js"></script>
	<script
		src="https://www.gstatic.com/firebasejs/9.21.0/firebase-storage-compat.js"></script>
	<script>
						$(function () {
							$('.file-uploader_companyImg').on('change', function () {
								let fileInput = $(this)[0];
								let previewImage = $('#companyImgPreview');

								if (fileInput.files && fileInput.files[0]) {
									let reader = new FileReader();
									reader.onload = function (e) {
										previewImage.attr('src', e.target.result);
									}
									reader.readAsDataURL(fileInput.files[0]);
								}
							});

							$('#numberOfemployee').on('change', function () {
								let selectedValue = $(this).val();
								console.log(selectedValue)
								$('#company_numberOfemployee').val(selectedValue);
							});

							let category = "${emp.companyCategory}";

							$('.category-option option').each(function () {
								if ($(this).val() === category) { // 使用 `$(this).val()` 獲取 `<option>` 的值
									$(this).prop('selected', true); // 使用 `prop` 方法設置 `selected` 屬性
								}
							})

							let numOfemp = "${emp.companyNumberOfemployee}";

							$('.numOfemp-option option').each(function () {
								if ($(this).val() === numOfemp) { // 使用 `$(this).val()` 獲取 `<option>` 的值
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

						// 处理点击按钮事件
						document.getElementById('fetchButton').addEventListener('click', () => {
							fetch('FirebaseConfigServ')
								.then(response => response.json())
								.then(firebaseConfig => {
									// 初始化 Firebase
									const app = firebase.initializeApp(firebaseConfig);
									const storage = firebase.storage();

									window.uploadImage = function () {
										console.log('uploadImage function called');
										const fileInput = document.getElementById('fileInput');
										const file = fileInput.files[0];
										if (file) {
											const storageRef = storage.ref('userUpload/' + file.name);
											const uploadTask = storageRef.put(file);

											uploadTask.on('state_changed',
												function (snapshot) {
													const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;

												},
												function (error) {

												},
												function () {
													uploadTask.snapshot.ref.getDownloadURL().then(function (downloadURL) {

														document.getElementById('company_photoURL').value = downloadURL;
													});
												}
											);
										} else {
											//document.getElementById('status').innerText = 'No file selected';
										}
									};


									window.uploadImage();
								})
								.catch(error => {
									console.error('Error fetching Firebase config:', error);
								});
						});
					</script>
	<script src="https://kit.fontawesome.com/4caf09a569.js"
		crossorigin="anonymous"></script>
</body>

</html>