<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/transactionVIEW/jobOrder.css">
</head>
<body>
	<!-- 包含header和sidebar -->
	<jsp:include page="../model/header&sidebar.jsp" />

	<!-- 主內容區域 -->
	<main>
		<div class="course-header">
			<h2>用戶交易管理</h2>
		</div>

		<!-- 日期範圍和其他條件查詢表單 -->
		<div class="dashboard-header">
			<form method="get"
				action="${pageContext.request.contextPath}/UserTransactionServlet">
				<input type="hidden" name="action" value="filter">

				<div class="row mb-3">
					<!-- 日期範圍查詢 -->
					<div class="col-md-6">
						<label for="start_date">開始日期:</label> <input type="date"
							id="start_date" name="start_date" class="form-control">
					</div>
					<div class="col-md-6">
						<label for="end_date">結束日期:</label> <input type="date"
							id="end_date" name="end_date" class="form-control">
					</div>
				</div>

				<div class="row mb-3">
					<!-- 用戶ID和用戶名稱查詢 -->
					<div class="col-md-6">
						<label for="user_id">用戶ID:</label> <input type="text" id="user_id"
							name="user_id" class="form-control">
					</div>
					<div class="col-md-6">
						<label for="user_name">用戶名稱:</label> <input type="text"
							id="user_name" name="user_name" class="form-control">
					</div>
				</div>

				<div class="row mb-3">
					<!-- 交易類型查詢 -->
					<div class="col-md-6">
						<label for="transaction_type">交易類型:</label> <select
							id="transaction_type" name="transaction_type" class="form-select">
							<option value="">所有</option>
							<option value="deposit">存入</option>
							<option value="withdrawal">取出</option>
						</select>
					</div>
					<!-- 交易狀態查詢 -->
					<div class="col-md-6">
						<label for="transaction_status">交易狀態:</label> <select
							id="transaction_status" name="transaction_status"
							class="form-select">
							<option value="">所有</option>
							<option value="pending">待處理</option>
							<option value="completed">已完成</option>
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
						action="${pageContext.request.contextPath}/UserTransactionServlet"
						id="addTransactionForm" onsubmit="return confirm('確定要新增這筆交易嗎？');">
						<div class="modal-header">
							<h5 class="modal-title" id="addModalLabel">新增交易</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<input type="hidden" name="action" value="insert"> 用戶ID:
							<input type="text" id="addUserId" name="user_id" required><br>
							<br> 交易類型: <select id="addTransactionType"
								name="transaction_type" required>
								<option value="deposit">存入</option>
								<option value="withdrawal">取出</option>
							</select><br>
							<br> 金額: <input type="text" id="addAmount" name="amount"
								required><br>
							<br> 交易狀態: <input type="text" id="addTransactionStatus"
								name="transaction_status" value="pending" readonly><br>
							<br>
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


		<!-- 交易記錄表格 -->
		<div class="table-container">
			<table id="transactionsTable" class="table table-bordered">
				<thead>
					<tr>
						<th>交易ID</th>
						<th>用戶ID</th>
						<th>用戶名稱</th>
						<th>交易類型</th>
						<th>金額</th>
						<th>交易狀態</th>
						<th>創建時間</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="transactionBody">
					<!-- 使用JSTL來迭代交易記錄 -->
					<c:forEach var="transaction" items="${transactions}">
						<tr id="transactionRow${transaction.transactionId}">
							<td>${transaction.transactionId}</td>
							<td>${transaction.userId}</td>
							<td>${transaction.userName}</td>
							<td>${transaction.transactionType}</td>
							<td>${transaction.amount}</td>
							<td>${transaction.transactionStatus}</td>
							<td>${transaction.createdAt}</td>
							<td>
								<!-- 更新交易：僅當交易狀態為 pending 時才顯示更新按鈕 --> <c:if
									test="${transaction.transactionStatus == 'pending'}">
									<button type="button" class="btn btn-primary"
										data-bs-toggle="modal" data-bs-target="#updateModal"
										onclick="populateUpdateForm('${transaction.transactionId}', '${transaction.userId}', '${transaction.userName}', '${transaction.transactionType}', '${transaction.amount}', '${transaction.transactionStatus}', '${transaction.createdAt}')">更新</button>
									<!-- 更新交易模態框 -->
									<div class="modal fade" id="updateModal" tabindex="-1"
										aria-labelledby="updateModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<form method="post"
													action="${pageContext.request.contextPath}/UserTransactionServlet"
													id="updateTransactionForm"
													onsubmit="return confirm('確定要更新這筆交易嗎？');">
													<div class="modal-header">
														<h5 class="modal-title" id="updateModalLabel">更新交易</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">
														<input type="hidden" name="action" value="applyUpdate">
														<input type="hidden" id="updateTransactionId"
															name="transaction_id"> 用戶ID: <input type="text"
															id="updateUserId" name="user_id" readonly><br>
														<br> 用戶名稱: <input type="text" id="updateUserName"
															name="user_name" readonly><br>
														<br> 交易類型: <select id="updateTransactionType"
															name="transaction_type" required>
															<option value="deposit">存入</option>
															<option value="withdrawal">取出</option>
														</select><br>
														<br> 金額: <input type="text" id="updateAmount"
															name="amount" required><br>
														<br> 交易狀態: <select id="updateTransactionStatus"
															name="transaction_status" required>
															<option value="pending">待處理</option>
															<option value="completed">已完成</option>
															<option value="failed">取消</option>
														</select><br>
														<br> 創建時間: <input type="text" id="updateCreatedAt"
															name="created_at" readonly><br>
														<br>
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
								</c:if> <!-- 刪除交易表單 --> <c:if
									test="${transaction.transactionStatus == 'pending'}">
									<form method="post"
										action="${pageContext.request.contextPath}/UserTransactionServlet"
										style="display: inline;" class="deleteTransactionForm"
										onsubmit="return confirm('確定要刪除這筆交易嗎？');">
										<input type="hidden" name="action" value="delete"> <input
											type="hidden" name="transaction_id"
											value="${transaction.transactionId}">
										<button type="submit" class="btn btn-danger">刪除</button>
									</form>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		// JavaScript函數，用於填充更新表單的數據
		function populateUpdateForm(transactionId, userId, userName,
				transactionType, amount, transactionStatus, createdAt) {
			document.getElementById('updateTransactionId').value = transactionId;
			document.getElementById('updateUserId').value = userId;
			document.getElementById('updateUserName').value = userName;
			document.getElementById('updateTransactionType').value = transactionType;
			document.getElementById('updateAmount').value = amount;
			document.getElementById('updateTransactionStatus').value = transactionStatus;
			document.getElementById('updateCreatedAt').value = createdAt;
		}
	</script>
</body>
</html>