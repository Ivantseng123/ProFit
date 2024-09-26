<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.ProFit.bean.jobsBean.Jobs"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProFit - 職缺列表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
<!--href="${pageContext.request.contextPath}/model/model.css"連結後是接一串網址 http://localhost:8080/ProFit 
           action=${pageContext.request.contextPath}/jobsServlet 連結後是接一串網址 http://localhost:8080/ProFit
           href跟action都是接網址
           ${pageContext.request.contextPath}等於http://localhost:8080/ProFit  
      page="/model/header&sidebar.jsp
      page是接檔案，檔案位置從資料夾的webapp路徑開始算
      
       -->


<style>
.search-form {
	margin-bottom: 20px;
}

.search-form input[type="text"] {
	padding: 5px;
	margin-right: 10px;
}

.search-form input[type="submit"] {
	padding: 5px 10px;
	background-color: #4CAF50;
	color: white;
	border: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<jsp:include page="/model/header&sidebar.jsp"></jsp:include>
	<form method="post"
		action="${pageContext.request.contextPath}/jobsServlet"></form>
	<!-- 主要內容區域 -->
	<main>
		<div class="dashboard-header">
			<h2>職缺表格總覽</h2>
			<!-- <a href="${pageContext.request.contextPath}/jobs/edit" class="add-button">新增職缺</a>-->
			<button
				onclick="window.location.href='${pageContext.request.contextPath}/jobs/edit'"
				class="view">新增職缺</button>

		</div>
		<button
<%--				Servlet--%>
<%--			onclick="window.location.href='${pageContext.request.contextPath}/jobsServlet?action=listJobs'"--%>
<%--			class="view">查看全部</button>--%>

<%--				spring--%>
			onclick="window.location.href='${pageContext.request.contextPath}/jobs/all'"
			class="view">查看全部</button>
		<!-- <a href="${pageContext.request.contextPath}/jobsServlet?action=listJobs" class="view">查看全部</a>-->



		<div class="search-form">
			<form action="jobsServlet" method="get">
				<input type="hidden" name="action" value="search"> <input
					type="text" id="searchTerm" name="searchTerm" placeholder="搜尋id"
					value="${param.searchTerm}"> <input type="submit"
					id="search" value="搜尋">
			</form>
		</div>

		<div class="table-container">

			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>會員ID</th>
						<th>標題</th>
						<th>發布日期</th>
						<th>截止日期</th>
						<th>描述</th>
						<th>狀態</th>
						<th>所需技能</th>
						<th>工作地點</th>
						<th>最高薪資</th>
						<th>最低薪資</th>
						<th>工作時間</th>
						<th>開放職位數</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="job" items="${jobsList}">
						<!-- ${jobsList} 等於標籤jobsList-->
						<tr>
							<td>${job.jobsId}</td>
							<td>${job.users.userId}</td>
							<td>${job.jobsTitle}</td>
							<td><fmt:formatDate value="${job.jobsPostingDate}"
									pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatDate value="${job.jobsApplicationDeadline}"
									pattern="yyyy-MM-dd" /></td>
						    <td>${job.jobsDescription}</td>
							<td><span
								class="status ${job.jobsStatus == 1 ? 'active' : 'inactive'}">
									${job.jobsStatus == 1 ? '開放' : '關閉'} </span></td>
							<td>${job.jobsRequiredSkills}</td>
							<td>${job.jobsLocation}</td>
							<td>${job.jobsMaxSalary}</td>
							<td>${job.jobsMinSalary}</td>
							<td>${job.jobsWorktime}</td>
							<td>${job.jobsNumberOfOpenings}</td>
							<td class="action-buttons">
								<!--id=${job.jobsId}將值帶入--> <%-- <a href="jobsServlet?action=view&id=${job.jobsId}" class="view">查看</a>
							    <!--action=view&id=${job.jobsId}舉例，將action=view跟id=${job.jobsId}-->
							
								<a href="jobsServlet?action=delete&id=${job.jobsId}" class="delete"
								onclick="return confirm('確定要刪除這個職位嗎？');">刪除</a> --%>

								<button
									onclick="window.location.href='${pageContext.request.contextPath}/jobs/findOne?id=${job.jobsId}'"
									class="view">查看</button>
								<button
<%--									onclick="window.location.href='jobsServlet?action=edit&id=${job.jobsId}'"--%>
<%--									class="edit">編輯</button> --%>

										onclick="window.location.href='${pageContext.request.contextPath}/jobs/edit?id=${job.jobsId}'"
										class="edit">編輯</button>
								<button
<%--										Servlet--%>
<%--									onclick="if(confirm('確定要刪除這個職位嗎？')) { window.location.href='jobsServlet?action=delete&id=${job.jobsId}'; }"--%>
<%--									class="delete">刪除</button>--%>

<%--										spring--%>
										onclick="if(confirm('確定要刪除這個職位嗎？')) { deleteJobs(this) }"
										id="deleteBtn" data-value="/jobs/delete?id=${job.jobsId}"
										class="delete">刪除</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<script type="text/javascript">
		document.getElementById("search").addEventListener("click", function(e) {
			//document.getElementById("search")這段在找html中藉著id 去search
			//addEventListener("click", function(e)是在偵測使用者點擊事件
			e.preventDefault();
			//阻止默認行為
			let contextPath = "${pageContext.request.contextPath}";
			let value = document.getElementById("searchTerm").value
					.trim();//trim()前後避免空格
			//Servlet
			// location.href = contextPath
			// 		+ "/jobsServlet?action=view&id=" + value;//這段就是前往location.href 後面的網址

			//spring
			location.href = contextPath
					+ "/jobs/findOne?id=" + value;//這段就是前往location.href 後面的網址
		});

		function deleteJobs(button){
			let contextPath = "${pageContext.request.contextPath}";
			const url = contextPath + button.getAttribute('data-value');
			const url_pre = "http://localhost:8080";
			fetch(url_pre + url, {
				method: 'DELETE',
			}).then(response => {
				return response.json(); // 解析响应数据（如果有的话）
			})
				.then(data => {
					window.location.reload();
				})
				.catch(error => {
					window.location.reload();
				});
		}
	</script>
</body>
</html>
