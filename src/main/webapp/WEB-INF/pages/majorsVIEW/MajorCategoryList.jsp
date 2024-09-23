<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Major Categories - ProFit</title>
    <link rel="stylesheet"
        href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
    <jsp:include page="/model/header&sidebar.jsp" />

    <main>
        <div class="dashboard-header">
            <h2>專業類別</h2>
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
        <div class="action-buttons">
        <button class="edit" onclick="location.href='${pageContext.request.contextPath}/majorCategory/new'">新增專業類別</button>
        </div>
        <div class="table-container">
            <table>
                <tr>
                    <th>類別ID</th>
                    <th>類別名稱</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="category" items="${listMajorCategory}">
                    <tr>
                        <td><c:out value="${category.majorCategoryId}" /></td>
                        <td><c:out value="${category.categoryName}" /></td>
                        <td class="action-buttons">
                        <button class="edit" onclick="location.href='${pageContext.request.contextPath}/majorCategory/edit?id=<c:out value='${category.majorCategoryId}'/>&&name=<c:out value='${category.categoryName}'/>'">更改類別名稱</button>
                        <button class="delete" onclick="if(confirm('確定刪除此項分類?')) location.href='${pageContext.request.contextPath}/majorCategory/delete?id=<c:out value='${category.majorCategoryId}' />'">刪除</button>
                        <button class="view" onclick="location.href='${pageContext.request.contextPath}/majorCategory/majors?categoryId=<c:out value='${category.majorCategoryId}' />'">檢視該類別的專業</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</body>
</html>