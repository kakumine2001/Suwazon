<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
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
<title>ユーザー管理画面</title>
<style>
body {
	font-family: "メイリオ", sans-serif;
	padding: 40px;
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

h1 {
	font-size: 24px;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 30px;
}

th, td {
	border: 1px solid #333;
	padding: 10px;
	text-align: center;
}

th {
	background-color: #f3f3f3;
}

td:first-child {
	background-color: #ddd;
}

.action-button {
	padding: 6px 12px;
	font-size: 14px;
	font-weight: bold;
	border-radius: 4px;
	text-decoration: none;
	margin: 0 2px;
}

.edit {
	background-color: #cbe2eb;
	border: 1px solid #555;
	color: black;
}

.delete {
	background-color: #f3a661;
	border: 1px solid #555;
	color: black;
}

.history {
	background-color: #ccc;
	border: 1px solid #555;
	color: black;
}

.add-button {
	float: right;
	margin-bottom: 20px;
	padding: 8px 16px;
	font-size: 14px;
}

.back-link {
	color: blue;
	font-size: 14px;
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
	<h1>ユーザー管理画面</h1>
<%String message = (String)request.getAttribute("message");
	if(message != null){	%>
		<span><%=message %></span>
	<%} %>

	<form action="/Suwazon_zuichan/managerJSP/user_register.jsp" method="GET"
		style="text-align: right;">
		 <input type="submit" value="新規追加" class="add-button">
	</form>

	<table>
		<tr>
			<th>ユーザーID</th>
			<th>ユーザー名</th>
			<th>年齢</th>
			<th>性別</th>
			<!-- <th>権限</th> -->
			<th colspan="3">操作</th>
		</tr>

		<%
		List<User> userList = (List<User>) request.getAttribute("userList");
		if (userList != null) {
			for (User u : userList) {
		%>
		<tr>
			<td><%=u.getUserId()%></td>
			<td><%=u.getUserName()%></td>
			<td><%=u.getAge()%></td>
			<td><%=u.getGender()%></td>
			<!-- <td><%=u.isAdmin() ? "管理者" : "一般"%></td> -->
			<td>
			<!-- ユーザー変更 -->
				<form action="/Suwazon_zuichan/managerJSP/user_update.jsp" method="GET"
					style="display: inline;">
					<input type="hidden" name="userId" value="<%=u.getUserId()%>">
					<input type="submit" value="変更" class="action-button edit">
				</form>
			</td>
			<td>
			<!-- ユーザー削除 -->
				<form action="/Suwazon_zuichan/userManagement" method="POST"
					style="display: inline;" onsubmit="return confirm('本当に削除しますか？');">
					<input type="hidden" name="action" value="delete"> 
					<input type="hidden" name="userId" value="<%=u.getUserId()%>"> 
					<input type="submit" value="削除" class="action-button delete">
				</form>
			</td>
			<td>
			<!-- ユーザーの購入履歴を閲覧--未実装 -->
				<form action="/Suwazon_zuichan/user_purchaseHistory" method="GET"
					style="display: inline;">
					<input type="hidden" name="userId" value="<%=u.getUserId()%>">
					<input type="submit" value="購入履歴" class="action-button history">
				</form>
			</td>
		</tr>
		<%
		}
		}
		%>
	</table>

	<a href="/Suwazon_zuichan/managerJSP/admin_home.jsp" class="back-link">管理者ホーム画面に戻る</a>
</body>
</html>
