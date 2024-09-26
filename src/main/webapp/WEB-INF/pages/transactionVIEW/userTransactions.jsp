<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>用戶交易管理</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/model/model.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<!-- 包含header和sidebar -->
	<jsp:include page="/model/header&sidebar.jsp" />

	<!-- 主內容區域 -->
	<main>
		<div class="course-header">
			<h2>用戶交易管理</h2>
		</div>

		<!-- 查詢表單 -->
		<div class="dashboard-header">
			<form method="get"
				action="${pageContext.request.contextPath}/transactions/filter">
				<div class="row mb-3">
					<!-- 日期範圍查詢 -->
					<div class="col-md-6">
						<label for="start_date">開始日期:</label> <input type="date"
							id="start_date" name="startDate" class="form-control">
					</div>
					<div class="col-md-6">
						<label for="end_date">結束日期:</label> <input type="date"
							id="end_date" name="endDate" class="form-control">
					</div>
				</div>

				<div class="row mb-3">
					<!-- 用戶ID查詢 -->
					<div class="col-md-6">
						<label for="user_id">用戶ID:</label> <input type="text" id="user_id"
							name="userId" class="form-control">
					</div>
					<!-- 交易類型查詢 -->
					<div class="col-md-6">
						<label for="transaction_type">交易類型:</label> <select
							id="transaction_type" name="transactionType" class="form-select">
							<option value="">所有</option>
							<option value="deposit">存入</option>
							<option value="withdrawal">取出</option>
							<option value="payment">付款</option>
							<option value="refund">退款</option>
						</select>
					</div>
				</div>

				<div class="row mb-3">
					<!-- 交易狀態查詢 -->
					<div class="col-md-6">
						<label for="transaction_status">交易狀態:</label> <select
							id="transaction_status" name="transactionStatus"
							class="form-select">
							<option value="">所有</option>
							<option value="pending">待處理</option>
							<option value="completed">已完成</option>
							<option value="failed">失敗</option>
						</select>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col-md-12 text-center">
						<input type="submit" value="查詢" class="btn btn-primary">
					</div>
				</div>
			</form>
		</div>

		<!-- 顯示後端錯誤 -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>

		<!-- 新增交易按鈕 -->
		<div align="center">
			<button type="button" class="btn btn-success" data-bs-toggle="modal"
				data-bs-target="#addModal">新增交易</button>
		</div>

		<!-- 新增交易模態框 -->
		<div class="modal fade" id="addModal" tabindex="-1"
			aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form method="post"
						action="${pageContext.request.contextPath}/transactions/insert"
						id="addTransactionForm" onsubmit="return confirm('確定要新增這筆交易嗎？');">
						<div class="modal-header">
							<h5 class="modal-title" id="addModalLabel">新增交易</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							用戶ID: <input type="text" id="addUserId" name="userId" required
								class="form-control"><br>
							<br> 交易類型: <select id="addTransactionType"
								name="transactionType" required class="form-select">
								<option value="deposit">存入</option>
								<option value="withdrawal">取出</option>
								<option value="payment">付款</option>
								<option value="refund">退款</option>
							</select><br>
							<br> 金額: <input type="number" id="addAmount"
								name="transactionAmount" required class="form-control" min="1"><br>
							<br> 交易狀態: <select id="addTransactionStatus"
								name="transactionStatus" required class="form-select">
								<option value="pending">待處理</option>
								<option value="completed">已完成</option>
								<option value="failed">失敗</option>
							</select><br>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary">新增</button>
						</div>
					</form>
				</div>
			</div>
		</div>


		<!-- 更新交易模態框 -->
		<div class="modal fade" id="updateModal" tabindex="-1"
			aria-labelledby="updateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form method="post"
						action="${pageContext.request.contextPath}/transactions/update"
						id="updateTransactionForm"
						onsubmit="return confirm('確定要更新這筆交易嗎？');">
						<div class="modal-header">
							<h5 class="modal-title" id="updateModalLabel">更新交易</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<input type="hidden" name="transactionId"
								id="updateTransactionId"> 用戶ID: <input type="text"
								id="updateUserId" name="userId" required class="form-control"><br>

							交易類型: <select id="updateTransactionType" name="transactionType"
								required class="form-select">
								<option value="deposit">存入</option>
								<option value="withdrawal">取出</option>
								<option value="payment">付款</option>
								<option value="refund">退款</option>
							</select><br> 金額: <input type="number" id="updateAmount"
								name="transactionAmount" required class="form-control" min="1"><br>

							交易狀態: <select id="updateTransactionStatus"
								name="transactionStatus" required class="form-select">
								<option value="pending">待處理</option>
								<option value="completed">已完成</option>
								<option value="failed">失敗</option>
							</select><br> 創建時間: <input type="text" id="updateCreatedAt"
								name="createdAt" class="form-control" readonly><br>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary">更新</button>
						</div>
					</form>
				</div>
			</div>
		</div>


		<!-- 交易記錄表格 -->
		<div class="table-container">
			<table id="transactionsTable"
				class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th>交易ID</th>
						<th>用戶ID</th>
						<th>交易類型</th>
						<th>金額</th>
						<th>交易狀態</th>
						<th>創建時間</th>
						<th>完成時間</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="transaction" items="${transactions}">
						<tr>
							<td><c:out value="${transaction.transactionId}" /></td>
							<td><c:out value="${transaction.userId}" /></td>
							<td><c:out value="${transaction.transactionType}" /></td>
							<td><c:out value="${transaction.transactionAmount}" /></td>
							<td><c:out value="${transaction.transactionStatus}" /></td>
							<td><fmt:formatDate value="${transaction.createdAt}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${transaction.completionAt}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td class="text-center"><c:if
									test="${transaction.transactionStatus == 'pending'}">
									<div class="action-buttons"
										style="display: flex; justify-content: center; align-items: center;">
										<button type="button" class="view" data-bs-toggle="modal"
											data-bs-target="#updateModal"
											onclick="populateUpdateForm('${transaction.transactionId}', '${transaction.userId}', '${transaction.transactionType}', '${transaction.transactionAmount}', '${transaction.transactionStatus}', '${transaction.createdAt}')">
											更新</button>
										<form method="post"
											action="${pageContext.request.contextPath}/transactions/delete"
											style="display: inline;"
											onsubmit="return confirm('確定要刪除這筆交易嗎？');">
											<input type="hidden" name="transactionId"
												value="${transaction.transactionId}">
											<button type="submit" class="btn btn-danger">刪除</button>
										</form>
									</div>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		// 填充更新表單
		function populateUpdateForm(transactionId, userId, transactionType,
				transactionAmount, transactionStatus, createdAt) {
			document.getElementById('updateTransactionId').value = transactionId;
			document.getElementById('updateUserId').value = userId;
			document.getElementById('updateTransactionType').value = transactionType;
			document.getElementById('updateAmount').value = transactionAmount;
			document.getElementById('updateTransactionStatus').value = transactionStatus;
			document.getElementById('updateCreatedAt').value = createdAt;
		}
	</script>
</body>
</html>
