<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../model/model.css">
</head>
<body>
    <jsp:include page="../model/header&sidebar.jsp"></jsp:include>
    

    <!-- 主要內容區域 -->
    <main>
        <div class="dashboard-header">
            <h2>${empty job ? '更改資訊' : '編輯職缺'}</h2><!-- 如果 job 為空，顯示 "更改資訊"，否則顯示 "編輯職缺" -->
        </div>
        <div class="form-container">
            <form action="../jobsServlet" method="post">
                <!-- 隱藏，如果 job 為空，action 的值為 'add'，否则為 'update' -->
                <input type="hidden" name="action" value="${empty job ? 'add' : 'update'}">
                <!-- 如果 job 不為空 -->
                <c:if test="${not empty job}">
                    <input type="hidden" name="jobsId" value="${job.jobsId}">
                </c:if>

                <label for="jobsUserId">會員ID：</label>
                <input type="number" id="jobsUserId" name="jobsUserId" value="${job.users.userId}" required><br> 

                <label for="jobsTitle">標題：</label>
                <input type="text" id="jobsTitle" name="jobsTitle" value="${job.jobsTitle}" required><br>

                <label for="jobsPostingDate">發布日期：</label>
                <input type="date" id="jobsPostingDate" name="jobsPostingDate" value="${job.jobsPostingDate}" required><br>

                <label for="jobsApplicationDeadline">截止日期：</label>
                <input type="date" id="jobsApplicationDeadline" name="jobsApplicationDeadline" value="${job.jobsApplicationDeadline}" required><br>

                <label for="jobsDescription">描述：</label>
                <textarea id="jobsDescription" name="jobsDescription" required>${job.jobsDescription}</textarea><br>

                <label for="jobsStatus">狀態：</label>
                <select id="jobsStatus" name="jobsStatus" required>
                    <option value="1" ${job.jobsStatus == 1 ? 'selected' : ''}>開放</option>
                    <option value="0" ${job.jobsStatus == 0 ? 'selected' : ''}>關閉</option>
                </select><br>

                <label for="jobsRequiredSkills">所需技能：</label>
                <input type="text" id="jobsRequiredSkills" name="jobsRequiredSkills" value="${job.jobsRequiredSkills}" required><br>

                <label for="jobsLocation">工作地點：</label>
                <input type="text" id="jobsLocation" name="jobsLocation" value="${job.jobsLocation}" required><br>

                <label for="jobsMaxSalary">最高薪資：</label>
                <input type="number" id="jobsMaxSalary" name="jobsMaxSalary" value="${job.jobsMaxSalary}" required><br>

                <label for="jobsMinSalary">最低薪資：</label>
                <input type="number" id="jobsMinSalary" name="jobsMinSalary" value="${job.jobsMinSalary}" required><br>

                <label for="jobsWorktime">工作時間：</label>
                <input type="text" id="jobsWorktime" name="jobsWorktime" value="${job.jobsWorktime}" required><br>

                <label for="jobsNumberOfOpenings">開放職位數：</label>
                <input type="number" id="jobsNumberOfOpenings" name="jobsNumberOfOpenings" value="${job.jobsNumberOfOpenings}" required><br>

                <input type="submit" value="提交">
                <button onclick="window.location.href='jobsList.jsp'">取消</button>
            </form>
        </div>
    </main>
</body>
</html>
