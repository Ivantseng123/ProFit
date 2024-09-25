$('#searchBtn').click(function() {
	// 收集表單數據
	let data = {
		courseMajor: $('#id-courseMajor').val(),
		courseName: $('#id-courseName').val(),
		courseStatus: $('#id-courseStatus').val(),
		courseCreateUserId: $('#id-courseCreateUserId').val(),
		courseCreateUserName: $('#id-courseCreateUserName').val(),
	};

	// 發送 AJAX 請求
	$.ajax({
		url: contextPath+ '/courses/search',
		data: data,
		dataType: 'json',
		type: 'GET',
		success: function(response) {
			console.log(response);

			// 清空當前表格
			$('#search-results').empty();

			let tableHtml = `<h3>搜尋結果如下 :</h3>
        	                <table>
        	                    <thead>
        	                        <tr>
        	                            <th>ID</th>
        	                            <th>課程名稱</th>
        	                            <th>課程創建者</th>
        	                            <th>說明</th>
        	                            <th>價格</th>
        	                            <th>狀態</th>
        	                            <th>操作</th>
        	                        </tr>
        	                    </thead>
        	                    <tbody id="table-body">
        	                    </tbody>
        	                </table>`;

			$('#search-results').append(tableHtml);

			response.forEach(function(response) {
				console.log("Serialized JSON: " + response.courseCreaterName);
				$('#table-body').append(` 
        		                    <tr>
        	                        <td class="result-courseId" name="courseId">${response.courseId}</td>
        	                        <td class="result-courseName" name="courseName">${response.courseName}</td>
									<td>${response.courseCreaterName}</td>
        	                        <td>${response.courseDescription}</td>
        	                        <td>${response.coursePrice}</td>
        	                        <td><span class="status">${response.courseStatus}</span></td>
        	                        <td class="action-buttons">
        	                            <button class="view">查看</button>
        	                            <button class="edit">編輯</button>
        	                            <button class="delete">刪除</button>
        	                       	</td>
        	                    </tr>
        	                ` );
			});


		},
		error: function(jqXHR, textStatus, errorThrown) {
			// 處理錯誤
			console.error('查詢失敗:', textStatus, errorThrown);
			alert('查詢失敗，請重試。');
		}
	});
});

//新增課程後回傳帶參數的url，確認參數是否存在
$(document).ready(function() {
	console.log("Document is ready");
	// 獲取URL中的參數
	let params = new URLSearchParams(window.location.search);
	let clickButton = params.get('clickButton');

	// 如果clickButton存在且為true，則觸發按鈕點擊事件
	if (clickButton === 'true' || 1 == 1) {
		$('#searchBtn').click();
	}

	$('#id-courseMajor,#id-courseName,#id-courseStatus,#id-courseCreateUserName').change(function() {
		$('#searchBtn').click();
	})
});


//按下刪除按鈕，抓取欄位的值傳給server
$(document).on('click', '.delete', function() {
	var courseId = $(this).closest('tr').find('.result-courseId').text();

	console.log("Selected Course ID for Deletion: " + courseId);

	$.ajax({
		url: contextPath+ '/courses/delete/'+courseId,
		data: { courseId: courseId },
		type: 'get',
		success: function(response) {
			if (response) {
				window.alert('課程刪除成功');
				console.log('新增的课程信息:', response);
				window.location.href = contextPath + '/courses?clickButton=true';
			} else {
				window.alert('課程刪除失敗');
			}
		},
		error: function(error) {
			console.error('Error deleting course:', error);
		}
	});
});

// 編輯課程流程
$(document).on('click', '.edit', function() {
    var courseId = $(this).closest('tr').find('.result-courseId').text();
    
    console.log("Selected Course ID for Editing: " + courseId);
    
    // 不需要發送 AJAX 請求來獲取課程信息，直接轉發到控制器方法
    window.location.href = contextPath + '/courses/viewUpdate?courseId=' + courseId;
});


//按下查看按鈕，抓取欄位的值傳給server
$(document).on('click', '.view', function() {
	var courseId = $(this).closest('tr').find('.result-courseId').text();

	console.log("Selected Course ID for Deletion: " + courseId);

	$.ajax({
		url: contextPath+ '/courses/search/'+courseId,
		data: {
			courseId: courseId
		},
		success: function(response) {
			// 清空當前表格
			$('.form-container').empty();

			// 完整的日期和时间
			let courseStartDate = `${response.courseStartDate.split('.')[0]}`;
			let courseEndDate = `${response.courseEndDate.split('.')[0]}`;

			// 拼接成完整的字符串
			let courseStartDateTime = `${courseStartDate}`;
			let courseEndDateTime = `${courseEndDate}`;

			console.log(response.courseStatus);
			$('.form-container').append(`<form>
				    <div class="form-group">
			        <label for="courseName">課程名稱:</label>
			        <input type="text" id="courseName" name="courseName" value=${response.courseName} readonly>
			    </div>
			    <div class="form-group">
			        <label for="courseMajor">課程類別:</label>
			        <select id="courseMajor" name="courseMajor" required disabled>
			            <option value="">請選擇類別</option>
			            <option value="100">程式設計</option>
			            <option value="2">類別2</option>
			        </select>
			    </div>
			    <div class="form-group">
			        <label for="courseCreateUserId">課程創建者ID:</label>
			        <input type="text" id="courseCreateUserId" name="courseCreateUserId" value=${response.courseCreaterId} readonly>
			    </div>
			    <div class="form-group">
			        <label for="courseInformation">課程資訊:</label>
			        <textarea id="courseInformation" name="courseInformation" rows="4" cols="50" readonly></textarea>
			    </div>
			    <div class="form-group">
			        <label for="courseDescription">課程描述:</label>
			        <textarea id="courseDescription" name="courseDescription" rows="6" cols="50" readonly></textarea>
			    </div>
			    <div class="form-group">
			        <label for="courseEnrollmentDate">最後修改日期: (自動帶入)</label>
			        <input type="date" id="courseEnrollmentDate" name="courseEnrollmentDate" value=${response.courseEnrollmentDate} readonly>
			    </div>
			    <div class="form-group">
			        <label for="courseStartDate">開始日期:</label>
			        <input type="text" id="courseStartDate" name="courseStartDate" readonly>
			    </div>
			    <div class="form-group">
			        <label for="courseEndDate">結束日期:</label>
			        <input type="text" id="courseEndDate" name="courseEndDate" readonly>
			    </div>
			    <div class="form-group">
			        <label for="coursePrice">課程價格:</label>
			        <input type="number" id="coursePrice" name="coursePrice" value=${response.coursePrice} readonly>
			    </div>
			    <div class="form-group">
			        <label for="courseStatus">課程狀態:</label>
			        <select id="courseStatus" name="courseStatus" required disabled>
			            <option value="">請選擇狀態</option>
			            <option value="Active">啟用</option>
			            <option value="Pending">審核中</option>
			            <option value="inactive">停用</option>
			        </select>
			    </div>
				<button id="closePopupBtn">關閉</button>
			</form>`)
			$('#courseMajor').val(response.courseCategoryId);
			$('#courseStatus').val(response.courseStatus);
			$('#courseInformation').val(response.courseInformation);
			$('#courseDescription').val(response.courseDescription);
			$('#courseStartDate').val(courseStartDateTime);
			$('#courseEndDate').val(courseEndDateTime);
		},
		error: function(error) {
			console.error('Error deleting course:', error);
		}
	});
});