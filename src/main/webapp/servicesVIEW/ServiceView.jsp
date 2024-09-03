<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Service Details - ProFit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.view').click(function(e) {
                e.preventDefault();
                window.location.href = '${pageContext.request.contextPath}/service/search';
            });
        });
    </script>
</head>
<body>
    <jsp:include page="/model/header&sidebar.jsp" />
    <main>
        <div class="dashboard-header">
            <h2>Service Details</h2>
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
        <div class="table-container">
            <table>
                <tr>
                    <th>ID:</th>
                    <td><c:out value="${service.serviceId}" /></td>
                </tr>
                <tr>
                    <th>服務標題:</th>
                    <td><c:out value="${service.serviceTitle}" /></td>
                </tr>
                <tr>
                    <th>用戶:</th>
                    <td><c:out value="${userName}" /></td>
                </tr>
                <tr>
                    <th>技能:</th>
                    <td><c:out value="${majorName}" /></td>
                </tr>
                <tr>
                    <th>價格(單價):</th>
                    <td><fmt:formatNumber value="${service.servicePrice}" type="currency" currencySymbol="$" /></td>
                </tr>
                <tr>
                    <th>單位:</th>
                    <td><c:out value="${service.serviceUnitName}" /></td>
                </tr>
                <tr>
                    <th>時程:</th>
                    <td><c:out value="${service.serviceDuration}" /> 天</td>
                </tr>
                <tr>
                    <th>創建時間:</th>
   					<td>
      					<c:if test="${not empty service.serviceCreateDateAsDate}">
           					 <fmt:formatDate value="${service.serviceCreateDateAsDate}" pattern="yyyy-MM-dd HH:mm:ss" />
        				</c:if>
        				<c:if test="${empty service.serviceCreateDateAsDate}">
            					N/A
        				</c:if>
    				</td>
                <tr>
                    <th>更新時間:</th>
                    <td>
      					<c:if test="${not empty service.serviceUpdateDateAsDate}">
           					 <fmt:formatDate value="${service.serviceUpdateDateAsDate}" pattern="yyyy-MM-dd HH:mm:ss" />
        				</c:if>
        				<c:if test="${empty service.serviceUpdateDateAsDate}">
            					N/A
        				</c:if>
    				</td>
                </tr>
                <tr>
                    <th>內容:</th>
                    <td><c:out value="${service.serviceContent}" /></td>
                </tr>
                <tr>
                    <th>範例:</th>
                    <td>
                        <c:if test="${not empty service.serviceSample1}">
                            <a href="${pageContext.request.contextPath}/service/download?id=${service.serviceId}&sample=1" target="_blank">範例 1</a><br>
                        </c:if>
                        <c:if test="${not empty service.serviceSample2}">
                            <a href="${pageContext.request.contextPath}/service/download?id=${service.serviceId}&sample=2" target="_blank">範例 2</a><br>
                        </c:if>
                        <c:if test="${not empty service.serviceSample3}">
                            <a href="${pageContext.request.contextPath}/service/download?id=${service.serviceId}&sample=3" target="_blank">範例 3</a>
                        </c:if>
                    </td>
                </tr>
            </table>
            
            <div class="action-buttons">
                <button class="edit" onclick="location.href='${pageContext.request.contextPath}/service/edit?id=${service.serviceId}'">編輯</button>
                <button class="delete" onclick="if(confirm('確定刪除此項服務?')) location.href='${pageContext.request.contextPath}/service/delete?id=${service.serviceId}'">刪除</button>
                <button class="view">返回服務清單</button>
            </div>
        </div>
    </main>
</body>
</html>