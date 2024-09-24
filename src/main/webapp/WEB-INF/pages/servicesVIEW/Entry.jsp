<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>技能服務管理 - ProFit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
    <style>
        .entry-options {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            padding: 20px;
        }
        .entry-option {
            background-color: #f0f0f0;
            border-radius: 5px;
            padding: 20px;
            margin: 10px;
            width: 200px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            transition: all 0.3s ease;
        }
        .entry-option:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        }
        .entry-option a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <jsp:include page="/model/header&sidebar.jsp" />
    
    <main>
        <div class="dashboard-header">
            <h2>專業服務管理</h2>
        </div>
        
        <div class="entry-options">
            <div class="entry-option">
                <a href="${pageContext.request.contextPath}/service/search">技能服務管理</a>
            </div>
            <div class="entry-option">
                <a href="${pageContext.request.contextPath}/userMajor/">用戶-專業</a>
            </div>
             <div class="entry-option">
                <a href="${pageContext.request.contextPath}/major/">專業管理</a>
            </div>
             <div class="entry-option">
                <a href="${pageContext.request.contextPath}/majorCategory/list">專業類別管理</a>
            </div>
        </div>
    </main>
</body>
</html>