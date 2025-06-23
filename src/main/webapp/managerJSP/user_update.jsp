<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="entity.User,service.UserService"%>
<%@ page import="entity.User"%>
<%
User user = (User) session.getAttribute("user");
if (user == null || !user.isAdmin()) {
%>
<h2 style="color: red;">このページには管理者のみアクセスできます。</h2>
<p>
	<a href="login.jsp">ログイン画面へ</a>
</p>
<%
return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報変更</title>
<style>
/* 新規登録JSPと同様のデザイン */
body {
	background-color: #d4edf3;
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
	border: 2px solid #444;
	width: 600px;
	margin: 40px auto;
	padding: 25px 40px;
	background: #d4edf3;
}

h1 {
	text-align: center;
	font-size: 28px;
	margin-bottom: 30px;
}

.message {
	text-align: center;
	color: blue;
	font-size: 16px;
	margin-bottom: 20px;
}

.form-group {
	display: flex;
	align-items: center;
	margin-bottom: 18px;
}

.form-label {
	width: 120px;
	font-size: 16px;
	font-weight: bold;
}

.form-input, .form-select {
	flex: 1;
	padding: 8px;
	font-size: 16px;
}

.button-container {
	text-align: right;
	margin-top: 30px;
}

input[type="submit"] {
	font-size: 18px;
	padding: 8px 35px;
}

.back-link {
	color: blue;
	font-size: 16px;
	display: inline-block;
	margin-top: 22px;
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
		<h1>ユーザー情報変更</h1>
		<%
		if (message != null) {
		%>
		<div class="message"><%=message%></div>
		<%
		}
		%>
		<%
		String userId = (String) request.getParameter("userId");
		User targetUser = UserService.getInstance().getUserById(userId);
		%>
		<form action="/Suwazon_zuichan/userManagement" method="POST">
			<div class="form-group">
				<div class="form-label">ユーザーID</div>
				<input class="form-input" type="text" name="userId"
					value="<%=targetUser.getUserId()%>" readonly>
			</div>
			<div class="form-group">
				<div class="form-label">ユーザー名</div>
				<input class="form-input" type="text" name="userName"
					value="<%=targetUser.getUserName()%>" required>
			</div>
			<div class="form-group">
				<div class="form-label">性別</div>
				<select class="form-select" name="gender">
					<option value="男"
						<%="男".equals(targetUser.getGender()) ? "selected" : ""%>>男</option>
					<option value="女"
						<%="女".equals(targetUser.getGender()) ? "selected" : ""%>>女</option>
				</select>
			</div>
			<div class="form-group">
				<div class="form-label">年齢</div>
				<input class="form-input" type="number" name="age"
					value="<%=targetUser.getAge()%>" min="0" required>
			</div>
			<div class="form-group">
				<div class="form-label">パスワード</div>
				<input class="form-input" type="text" name="password"
					value="<%=targetUser.getPassword()%>" required>
			</div>
			<div class="form-group">
				<div class="form-label">権限</div>
				<select class="form-select" name="isAdmin">
					<option value="false" <%=!targetUser.isAdmin() ? "selected" : ""%>>一般ユーザー</option>
					<option value="true" <%=targetUser.isAdmin() ? "selected" : ""%>>管理者</option>
				</select>
			</div>
			<div class="button-container">
				<input type="hidden" name="action" value="update"> <input
					type="submit" value="更新">
			</div>
		</form>
		<a href="/Suwazon_zuichan/userManagement" class="back-link">ユーザー管理画面へ</a>
	</div>
</body>
</html>
