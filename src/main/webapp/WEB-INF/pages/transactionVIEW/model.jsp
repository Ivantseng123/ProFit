<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>ProFit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/model/model.css">
</head>
<body>
	
	<!-- 主要內容區域 -->
    <!-- 只是一個範例，裡面布局可以更改 -->
    <main>
        <div class="dashboard-header">
            <h2>專案總覽</h2>
        </div>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>項目名稱</th>
                        <th>說明</th>
                        <th>進度</th>
                        <th>狀態</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>專案 A</td>
                        <td>描述 A</td>
                        <td>進行中</td>
                        <td><span class="status">正常</span></td>
                        <td class="action-buttons">
                            <button class="view">查看</button>
                            <button class="edit">編輯</button>
                            <button class="delete">刪除</button>
                        </td>
                    </tr>
                    <!-- 更多行 -->
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>