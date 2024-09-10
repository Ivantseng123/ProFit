<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Major Users - ProFit</title>
    <link rel="stylesheet"
        href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
    <jsp:include page="/model/header&sidebar.jsp" />

    <main>
        <div class="dashboard-header">
            <h2>擁有專業:${majorName} 之用戶</h2>
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
	<button onclick="location.href='${pageContext.request.contextPath}/userMajor/'">返回所有用戶專業</button>
        <div class="table-container">
            <table>
                <tr>
                    <th>專業 ID</th>
                    <th>專業名稱</th>
                    <th>用戶 ID</th>
                    <th>用戶名稱</th>
                </tr>
                <c:forEach var="majorUser" items="${majorUsers}">
                    <tr>
                        <td><c:out value="${majorUser.id.major.majorId}" /></td>
                        <td><c:out value="${majorUser.id.major.majorName}" /></td>
                        <td><c:out value="${majorUser.id.user.userId}" /></td>
                        <td><c:out value="${majorUser.id.user.userName}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</body>
</html>