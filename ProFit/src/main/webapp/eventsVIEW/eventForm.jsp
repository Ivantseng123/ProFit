<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>變更表格</title>
    <link rel="stylesheet" href="./model/model.css">
    <style>
        /* .form-group label {
            display: inline-block;
            width: 30%;
            margin-right: 10px;
            vertical-align: top;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            display: inline-block; 
            width: 60%;
        } */
    </style>
</head>

<body>
    <jsp:include page="../model/header&sidebar.jsp"></jsp:include>
    <main>
        <div class="dashboard-header">
            <h2>變更表格</h2>
        </div>
        <form class="form-group form-container" action="events" method="post">
            <input type="hidden" name="eventId" value="${event.eventId}">
            <label for="eventName">活動名稱</label><input type="text" id="eventName" name="eventName" value="${event.eventName}">
            <hr>
            <label for="isEventActive">狀態</label>
            <span>活動是否開始: </span><input style="width:20px;" type="checkbox" id="isEventActive" name="isEventActive" value="1" ${event.isEventActive==1 ? 'checked' : '' }>
            <hr>
            <label for="eventMajor">專業類別</label>
            <input type="number" id="eventMajor" name="eventMajor" value="${event.eventMajor}">
            <hr>
            <label for="eventStartDate">開始日期</label>
            <input type="datetime-local" id="eventStartDate" name="eventStartDate" value="${event.eventStartDate}">
            <hr>
            <label for="eventEndDate">結束日期</label>
            <input type="datetime-local" id="eventEndDate" name="eventEndDate" value="${event.eventEndDate}">
            <hr>
            
            <label for="eventPartStartDate">報名開始日期</label>
            <input type="datetime-local" id="eventPartStartDate" name="eventPartStartDate" value="${event.eventPartStartDate}">
            <hr>
            <label for="eventPartEndDate">報名結束日期</label>
            <input type="datetime-local" id="eventPartEndDate" name="eventPartEndDate" value="${event.eventPartEndDate}">
            <hr>
            <label for="eventAmount">金額</label>
            <input type="number" id="eventAmount" name="eventAmount" value="${event.eventAmount}">
            <hr>
            <label for="eventLocation">地點</label>
            <input type="text" id="eventLocation" name="eventLocation" value="${event.eventLocation}">
            <hr>
            <label for="eventParticipantMaximum">參加人數上限</label>
            <input type="number" id="eventParticipantMaximum" name="eventParticipantMaximum"
            value="${event.eventParticipantMaximum}">
            <hr>
            <label for="eventDescription">描述</label>
            <textarea id="eventDescription" name="eventDescription">${event.eventDescription}</textarea>
            <hr>
            <label for="eventNote">備註</label>
            <textarea id="eventNote" name="eventNote">${event.eventNote}</textarea>
            <hr>
            <button type="submit" value="Save">保存</button>
        </form>
    </main>
</body>

</html>