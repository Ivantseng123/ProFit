<%@ page import="java.util.List" %>
<%@ page import="com.ProFit.bean.eventsBean.EventsBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
            <button class="view btn" href="events?action=new">新增</button>
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
                <% List<EventsBean> events = (List<EventsBean>) request.getAttribute("events");
                        if (events != null) {
                        for (EventsBean event : events) {
                        %>
                        <tr>
                            <td>
                                <%= event.getEventId() %>
                            </td>
                            <td>
                                <%= event.getEventName() %>
                            </td>
                            <td>
                                <%= event.getIsEventActive() %>
                            </td>
                            <td>
                                <%= event.getEventMajor() %>
                            </td>
                            <td>
                                <%= event.getEventStartDate() %>
                            </td>
                            <td>
                                <%= event.getEventEndDate() %>
                            </td>
                            <td>
                                <%= event.getEventPartStartDate() %>
                            </td>
                            <td>
                                <%= event.getEventPartEndDate() %>
                            </td>
                            <td>
                                <%= event.getEventDescription() %>
                            </td>
                            <td>
                                <%= event.getEventAmount() %>
                            </td>
                            <td>
                                <%= event.getEventLocation() %>
                            </td>
                            <td>
                                <%= event.getEventParticipantMaximum() %>
                            </td>
                            <td>
                                <%= event.getEventNote() %>
                            </td>
                            <td class="action-buttons">
                                <button class="edit btn"
                                    href="events?action=edit&eventId=<%= event.getEventId() %>">編輯</button>
                                <button class="delete btn"
                                    href="events?action=delete&eventId=<%= event.getEventId() %>">刪除</button>
                            </td>
                        </tr>
                        <% } } %>
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