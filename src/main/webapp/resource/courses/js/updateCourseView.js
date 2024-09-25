// 新增課程後回傳帶參數的 URL，確認參數是否存在
$(document).ready(function() {
    // 獲取URL中的參數
    let params = new URLSearchParams(window.location.search);
    let oldCourseId = params.get('courseId');

    // 如果 courseId 存在且不為空字串，則發送 AJAX 請求獲取課程信息
    if (oldCourseId) {
		
		
		
        $.ajax({
            url: contextPath + '/courses/search/' + oldCourseId, // 使用 contextPath 和路徑變數
            dataType: 'json',
            type: 'GET',
            success: function(response) {
                console.log(response);
				
				// 完整的日期和时间
				let courseStartDate = `${response.courseStartDate.split('.')[0]}`;
				let courseEndDate = `${response.courseEndDate.split('.')[0]}`;

				// 拼接成完整的字符串
				let courseStartDateTime = `${courseStartDate}`;
				let courseEndDateTime = `${courseEndDate}`;

				
                $('.form-container').append(`
                    <form>
                        <div class="form-group">
                            <label for="courseName">課程名稱:</label>
                            <input type="text" id="courseName" name="courseName" value="${response.courseName}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="courseMajor">課程類別:</label>
                            <select id="courseMajor" name="courseMajor" required>
                                <option value="">請選擇類別</option>
                                <option value="100">程式設計</option>
                                <option value="2">類別2</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="courseCreateUserId">課程創建者ID:</label>
                            <input type="text" id="courseCreateUserId" name="courseCreateUserId" value="${response.courseCreaterId}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="courseInformation">課程資訊:</label>
                            <textarea id="courseInformation" name="courseInformation" rows="4" cols="50" readonly>${response.courseInformation}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="courseDescription">課程描述:</label>
                            <textarea id="courseDescription" name="courseDescription" rows="6" cols="50" readonly>${response.courseDescription}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="courseEnrollmentDate">修改日期: (自動帶入)</label>
                            <input type="date" id="courseEnrollmentDate" name="courseEnrollmentDate" readonly>
                        </div>
                        <div class="form-group">
                            <label for="courseStartDate">開始日期:</label>
                            <input type="datetime-local" id="courseStartDate" name="courseStartDate" value="${courseStartDateTime}">
                        </div>
                        <div class="form-group">
                            <label for="courseEndDate">結束日期:</label>
                            <input type="datetime-local" id="courseEndDate" name="courseEndDate" value="${courseEndDateTime}">
                        </div>
                        <div class="form-group">
                            <label for="coursePrice">課程價格:</label>
                            <input type="number" id="coursePrice" name="coursePrice" value="${response.coursePrice}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="courseStatus">課程狀態:</label>
                            <select id="courseStatus" name="courseStatus" required>
                                <option value="">請選擇狀態</option>
                                <option value="Active">啟用</option>
                                <option value="Pending">審核中</option>
                                <option value="Closed">已關閉</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <a href="${contextPath}/courses"><button id='cancelBtn' type="button" style="margin-right:380px;">取消修改</button></a>
                            <button id="editBtn" name="editBtn" type="submit">修改課程</button>
                        </div>
                    </form>
                `);

                // 設置修改日期為當前日期
                let enrollmentDateInput = document.getElementById("courseEnrollmentDate");
                if (enrollmentDateInput) {
                    let now = new Date();
                    let year = now.getFullYear();
                    let month = String(now.getMonth() + 1).padStart(2, '0'); // 月份從0開始，所以要+1
                    let day = String(now.getDate()).padStart(2, '0');
                    let formattedDate = `${year}-${month}-${day}`;
                    enrollmentDateInput.value = formattedDate;

                    console.log("Enrollment Date:", enrollmentDateInput.value);
                } else {
                    console.error('enrollmentDateInput element not found');
                }

                // 設置課程類別和狀態
                $('#courseMajor').val(response.courseCategoryId);
                $('#courseStatus').val(response.courseStatus);
            },
            error: function(error) {
                console.error('Error fetching course for editing:', error);
            }
        });
    }
});

// 日期轉換函數
function convertToSQLDateTimeFormat(datetimeLocal) {
    // 將 "T" 替換成 " "，將 "YYYY-MM-DDTHH:MM" 轉換為 "YYYY-MM-DD HH:MM"
    return datetimeLocal.replace("T", " ") + ":00"; // 加上秒部分，確保格式 "YYYY-MM-DD HH:MM:SS"
};

// 執行修改課程
$(document).on('click', '#editBtn', function(event) {
    event.preventDefault(); // 防止表單默認提交行為
    
    // 獲取 URL 中的 courseId 參數
    let params = new URLSearchParams(window.location.search);
    let oldCourseId = params.get('courseId');
    
    if (!oldCourseId) {
        alert('無法獲取課程 ID');
        return;
    }
    
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
    
    console.log(data);

    $.ajax({
        url: contextPath + '/courses/update/' + oldCourseId, // 使用 contextPath 變數和路徑變數
        data: data,
        dataType: 'json',
        type: 'POST', // 使用 POST 方法
        success: function(response) {
            if (response) {
                window.alert('課程修改成功');
                console.log('更新成功:', response);
                window.location.href = contextPath + '/courses?clickButton=true';
            } else {
                window.alert('課程修改失敗');
            }
        },
        error: function(xhr, status, error) {
            console.error('發生錯誤:', error);
            alert('課程修改失敗，請重試。');
        }
    });
});