<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Service Form - ProFit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <style>
        .image-preview {
            max-width: 200px;
            max-height: 200px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <jsp:include page="/model/header&sidebar.jsp" />
    
    <main>
        <div class="dashboard-header">
            <h2>技能服務-新增/修改</h2>
        </div>
            <div class="entry-options1">
            <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/service/search">技能服務管理</a>
            </div>
            <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/userMajor/">用戶-專業</a>
            </div>
             <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/major/">專業管理</a>
            </div>
             <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/majorCategory/list">專業類別管理</a>
            </div>
	</div>
        <div class="table-container form-container">
            <form class="form-group" id="userForm" action="${pageContext.request.contextPath}/service/selectUser" method="get">
                <table>
                    <tr>
                        <td><label for="userId">用戶:</label></td>
                        <td>
                            <select name="userId" id="userId" onchange="this.form.submit()">
                                <option value="">選擇用戶</option>
                                <c:forEach var="user" items="${users}">
                                    <option value="${user.key}" ${selectedUserId == user.key ? 'selected' : ''}>${user.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>

            <c:if test="${not empty selectedUserId}">
                <form class="form-group" action="${pageContext.request.contextPath}/service/${service != null ? 'update' : 'insert'}" method="post">
                    <c:if test="${service != null}">
                        <input type="hidden" name="serviceId" value="<c:out value='${service.serviceId}' />" />
                    </c:if>
                    <input type="hidden" name="userId" value="${selectedUserId}" />
                    
                    <table>
                        <tr>
                            <td><label for="majorId">專業:</label></td>
                            <td>
                                <select name="majorId" id="majorId">
                                    <option value="">選擇專業</option>
                                    <c:forEach var="major" items="${majors}">
                                        <option value="${major.key}" ${service.majorId == major.key ? 'selected' : ''}>${major.value}</option>
                                    </c:forEach>
                                </select>
                                
                            </td>
                        </tr>
                        <!-- 其他字段... -->
                        <tr>
                        <td><label for="serviceTitle">服務標題:</label></td>
                        <td><input type="text" name="serviceTitle" value="<c:out value='${service.serviceTitle}' />" required></td>
                    </tr>
                    <tr>
                        <td><label for="servicePrice">價格(單價):</label></td>
                        <td><input type="number" step="0.01" name="servicePrice" value="<c:out value='${service.servicePrice}' />" required></td>
                    </tr>
                    <tr>
                        <td><label for="serviceUnitName">單位:</label></td>
                        <td><input type="text" name="serviceUnitName" value="<c:out value='${service.serviceUnitName}' />" required></td>
                    </tr>
                    <tr>
                        <td><label for="serviceDuration">時程(天):</label></td>
                        <td><input type="number" step="0.1" name="serviceDuration" value="<c:out value='${service.serviceDuration}' />" required></td>
                    </tr>
                    <tr>
                        <td><label for="serviceContent">內容:</label></td>
                        <td><textarea name="serviceContent" required><c:out value='${service.serviceContent}' /></textarea></td>
                    </tr>
                    <tr>
                        <td><label for="serviceSample1">範例 1:</label></td>
                        <td>
                        	<input type="file" name="serviceSample1" id="serviceSample1" accept="image/*" onchange="previewImage(this, 'imagePreview1')">
                        	<img id="imagePreview1" class="image-preview" src="#" alt="Image preview" style="display:none;"/>
                        	<input type="hidden" id="serviceSample1Url" name="serviceSample1Url">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="serviceSample2">範例 2:</label></td>
                        <td>
                        	<input type="file" name="serviceSample2" id="serviceSample2" accept="image/*" onchange="previewImage(this, 'imagePreview2')">
                        	<img id="imagePreview2" class="image-preview" src="#" alt="Image preview" style="display:none;"/>
                        	<input type="hidden" id="serviceSample2Url" name="serviceSample2Url">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="serviceSample3">範例 3:</label></td>
                        <td>
                        	<input type="file" name="serviceSample3" id="serviceSample3" accept="image/*" onchange="previewImage(this, 'imagePreview3')">
                        	<img id="imagePreview3" class="image-preview" src="#" alt="Image preview" style="display:none;"/>
                        	<input type="hidden" id="serviceSample3Url" name="serviceSample3Url">
                        </td>
                    </tr>
                        <tr>
                            <td colspan="2" style="text-align: center;">
                            <button type="submit">儲存</button>
                             
                            </td>
                        </tr>
                    </table>
                </form>
            </c:if>
        </div>
    </main>
    
    <!-- 引入 Firebase SDK -->
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-storage.js"></script>
    <script>   
    
 	// 初始化 Firebase
    // 從服務器獲取 Firebase 配置
    fetch('${pageContext.request.contextPath}/FirebaseConfigServ')
        .then(response => response.json())
        .then(config => {
            // 使用獲取的配置初始化 Firebase
            firebase.initializeApp(config);
        })
    	.catch(error => console.error('Error initializing Firebase:', error));
    // 預覽並上傳圖片
    function previewImage(input, previewId) {
        var preview = document.getElementById(previewId);
        var file = input.files[0];
        var reader = new FileReader();

        // 當文件讀取完成時顯示預覽
        reader.onloadend = function () {
            preview.src = reader.result;
            preview.style.display = 'block';
        }

        if (file) {
            // 讀取文件以顯示預覽
            reader.readAsDataURL(file);
            // 上傳圖片到 Firebase
            uploadImageToFirebase(file, input.name);
        } else {
            // 如果沒有選擇文件，隱藏預覽
            preview.src = "";
            preview.style.display = 'none';
        }
    }

    // 上傳圖片到 Firebase Storage
    function uploadImageToFirebase(file, inputName) {
        var storage = firebase.storage();
        var storageRef = storage.ref('service_images/' + file.name);
        var uploadTask = storageRef.put(file);

        // 監聽上傳狀態
        uploadTask.on('state_changed', 
            (snapshot) => {
            	const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            	console.log("snapshot:", progress);
            }, 
            (error) => {
                // 處理上傳錯誤
                console.error("Upload failed:", error);
            }, 
            () => {
                // 上傳成功，獲取下載 URL
                uploadTask.snapshot.ref.getDownloadURL().then((downloadURL) => {
                    console.log('File available at', downloadURL);
                    // 將 URL 存儲在隱藏的輸入字段中
                    var urlInput = document.getElementById(inputName + 'Url');
                    if (urlInput) {
                        urlInput.value = downloadURL;
                    } else {
                        console.error('Element not found:', inputName + 'Url');
                    }
                });
            }
        );
    }
    </script>
</body>
</html>