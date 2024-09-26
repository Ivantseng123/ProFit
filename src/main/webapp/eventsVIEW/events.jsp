<%@ page import="java.util.List" %>
<%@ page import="com.ProFit.bean.eventsBean.EventsBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>活動列表</title>
    <link rel="stylesheet" href="./model/model.css">
    <style>
        .btn {
            margin: 3px;
        }

        .view {
            margin: 0px 10px 20px 10px;
        }
    </style>
</head>

<body>
    <jsp:include page="../model/header&sidebar.jsp"></jsp:include>
    <main>
        <div class="dashboard-header">
            <h2>活動列表</h2>
        </div>
        <div class="action-buttons">
            <button class="view btn" href="events/get/">新增</button>
        </div>
        <div class="table-container">
            <table>
                <tr>
                    <th>ID</th>
                    <th>活動名稱</th>
                    <th>狀態</th>
                    <th>專業類別</th>
                    <th>開始日期</th>
                    <th>結束日期</th>
                    <th>報名開始日期</th>
                    <th>報名結束日期</th>
                    <th>金額</th>
                    <th>地點</th>
                    <th>人數上限</th>
                    <th>描述</th>
                    <th>備註</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="event" items="${events}">
                        <tr>
                            <td>${event.eventId}</td>
                            <td>${event.eventName}</td>
                            <td>${event.isEventActive}</td>
                            <td>${event.eventMajor}</td>
                            <td>${event.eventStartDate}</td>
                            <td>${event.eventEndDate}</td>
                            <td>${event.eventPartStartDate}</td>
                            <td>${event.eventPartEndDate}</td>
                            <td>${event.eventAmount}</td>
                            <td>${event.eventLocation}</td>
                            <td>${event.eventParticipantMaximum}</td>
                            <td>${event.eventDescription}</td>
                            <td>${event.eventNote}</td>
                            <td class="action-buttons">
                                <button class="edit btn"
                                    href="events/get/${event.eventId}">編輯</button>
                                <button class="delete btn"
                                    href="events/delete/${event.eventId}">刪除</button>
                            </td>
                        </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
    $(function () {
        $(".btn").click(function (event) {
            event.preventDefault();
            var href = $(this).attr("href");
            window.location.href = href;
        })
    })
</script>

</html>