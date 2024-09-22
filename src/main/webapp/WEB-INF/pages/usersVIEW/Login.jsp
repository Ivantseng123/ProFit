<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProFit</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Cool+Goggles"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	font-family: 'Cool Goggles', sans-serif;
	background-color: #2c3e50;
}

.container {
	max-width: 400px;
	margin: 0 auto;
	margin-top: 100px;
}

.login-form {
	margin: 0 auto;
	width: 100%;
	height: 300px;
	display: flex;
	flex-direction: column;
	align-items: center;
	border: 1px solid #d1d1d1;
	padding: 20px;
	border-radius: 5px;
	background: white;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.login-form h1 {
	color: #2c3e50;
}

.login-form p {
	font-size: 14px;
}

.btn {
	background-color: #2c3e50;
	border-color: #2c3e50;
}

/* Media Query for Tablets and Smaller Screens */
@media ( max-width : 768px) {
	.container {
		max-width: 100%;
	}
}
</style>
<title>User Login</title>
</head>
<body>
	<div class="container">

		<div class="login-form">

			<h1>ProFit</h1>
			<h3>後台系統</h3>

			<form id="loginForm" method="post" action="login">

				<div class="form-group">
					<input type="text" id="username" class="form-control"
						placeholder="帳號" name="user_email" required>
				</div>
				<div class="form-group">
					<input type="password" id="password" class="form-control"
						placeholder="密碼" name="user_password" required>
				</div>
				<button type="submit" class="btn btn-primary btn-block">登入</button>
			</form>
		</div>
	</div>


</body>
</html>