<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<style>
body {
	background-color: #d2e4e8; /* 画像に近い薄い水色 */
	font-family: "メイリオ", sans-serif;
}

.header {
	width: 100%;
	padding: 20px;
	text-align: left;
}

.logo {
	font-family: Impact, sans-serif;
	font-size: 28px;
	margin-left: 30px;
}

.logo span {
	color: orange;
}

.container {
	width: 400px;
	margin: 100px auto;
	background-color: transparent;
}

h1 {
	text-align: center;
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 40px;
}

form {
	display: flex;
	flex-direction: column;
}

.form-group {
	display: flex;
	margin-bottom: 20px;
	align-items: center;
}

.form-label {
	width: 100px;
	text-align: right;
	padding-right: 10px;
	line-height: 1.2;
}

.form-input {
	flex: 1;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 8px;
	font-size: 14px;
}

.button-container {
	text-align: right;
	margin-top: 10px;
}

input[type="submit"] {
	padding: 6px 20px;
	font-size: 14px;
}

.link-container {
	margin-top: 20px;
}

.register-link {
	font-size: 14px;
	color: blue;
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="header">
		<div class="logo">
			SUWA<span>ZON</span>
		</div>
	</div>
	<%
	String message = (String) request.getAttribute("message");
	%>
	<div class="container">
		<h1>ログイン画面</h1>
		<span><%=message != null ? message : ""%></span>
		<form action="/Suwazon_zuichan/login" method="POST">
			<div class="form-group">
				<div class="form-label">
					ユーザーID<br>
				</div>
				<div class="form-input">
					<input type="text" name="user_id" maxlength="50" required>
				</div>
			</div>
			<div class="form-group">
				<div class="form-label">
					パスワード<br>
				</div>
				<div class="form-input">
					<input type="password" name="password" maxlength="10" required>
				</div>
			</div>
			<input type="hidden" name="action" value="login">
			<div class="button-container">
				<input type="submit" value="ログイン">
			</div>
		</form>
		<div class="link-container">
			<a href="registration.jsp" class="register-link">または新規登録</a>
		</div>
	</div>
</body>
</html>
