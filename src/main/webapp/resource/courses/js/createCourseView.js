$(document).ready(function() {
	let enrollmentDateInput = document.getElementById("courseEnrollmentDate");

	if (enrollmentDateInput) {
		let now = new Date();
		let year = now.getFullYear();
		let month = String(now.getMonth() + 1).padStart(2, '0');
		let day = String(now.getDate()).padStart(2, '0');
		let formattedDate = `${year}-${month}-${day}`;
		enrollmentDateInput.value = formattedDate;

		console.log("Enrollment Date:", enrollmentDateInput.value);
	} else {
		console.error('enrollmentDateInput element not found');
	};
	
	$(document).ready(function() {
	    $("#tabs").tabs();
	    $("#dialog-form").dialog();  // 如果需要初始化 dialog
	});

});

// 日期轉換函数
function convertToSQLDateTimeFormat(datetimeLocal) {
	// 將 "T" 替換成 " "，將 "YYYY-MM-DDTHH:MM" 轉換為 "YYYY-MM-DD HH:MM"
	return datetimeLocal.replace("T", " ") + ":00"; // 加上秒部分，確保格式 "YYYY-MM-DD HH:MM:SS"
};


	$('#createBtn').on('click', function(event)  {
		console.log('Create button clicked');
		let courseStartDate = $('#courseStartDate').val();
		let courseEndDate = $('#courseEndDate').val();

		courseStartDate = convertToSQLDateTimeFormat(courseStartDate);
		courseEndDate = convertToSQLDateTimeFormat(courseEndDate);

		let data = {
			courseName: $('#courseName').val(),
			courseCategory: $('#courseMajor').val(),
			courseCreateUserId: $('#courseCreateUserId').val(),
			courseInformation: $('#courseInformation').val(),
			courseDescription: $('#courseDescription').val(),
			courseEnrollmentDate: $('#courseEnrollmentDate').val(),
			courseStartDate: courseStartDate,
			courseEndDate: courseEndDate,
			coursePrice: $('#coursePrice').val(),
			courseStatus: $('#courseStatus').val()
		};
		
		// Manually check form validity
		if ($('form')[0].checkValidity()) {
			// Continue with your AJAX form submission
			$.ajax({
				url: contextPath + '/courses/insert',
				data: data,
				dataType: 'json',
				type: 'POST',
				success: function(response) {
				    if (response && response.courseName) { // 根據 CourseBean 的屬性進行判斷
				        window.alert('課程新增成功');
				        console.log('新增的课程信息:', response);
				        window.location.href = contextPath + '/courses?clickButton=true';
				    } else {
				        window.alert('課程新增失敗');
				    }
				},
				error: function(xhr, status, error) {
					console.error('發生錯誤:', error);
				}
			});
		} else {
			// If the form is not valid, you can show an error message or let the browser handle it
			$('form')[0].reportValidity(); // This will display the validation error messages
		}
	});