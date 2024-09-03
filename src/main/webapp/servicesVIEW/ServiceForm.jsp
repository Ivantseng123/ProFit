<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Service Form - ProFit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
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
                <a href="${pageContext.request.contextPath}/majㄋor/">專業管理</a>
            </div>
             <div class="entry-option1">
                <a href="${pageContext.request.contextPath}/majorCategory/list">專業類別管理</a>
            </div>
	</div>
        <div class="table-container form-container"">
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
                <form class="form-group" action="${pageContext.request.contextPath}/service/${service != null ? 'update' : 'insert'}" method="post" enctype="multipart/form-data">
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
                        <td><input type="file" name="serviceSample1"></td>
                    </tr>
                    <tr>
                        <td><label for="serviceSample2">範例 2:</label></td>
                        <td><input type="file" name="serviceSample2"></td>
                    </tr>
                    <tr>
                        <td><label for="serviceSample3">範例 3:</label></td>
                        <td><input type="file" name="serviceSample3"></td>
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
</body>
</html>