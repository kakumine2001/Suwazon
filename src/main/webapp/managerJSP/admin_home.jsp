<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理ホーム画面</title>
<style>
body {
	background-color: #d4edf3;
	font-family: "メイリオ", sans-serif;
	margin: 0;
	padding: 0;
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

.main-content {
	text-align: center;
	padding-top: 30px;
}

h1 {
	font-size: 32px;
	font-weight: bold;
	margin-bottom: 10px;
}

p {
	font-size: 18px;
	margin-bottom: 50px;
}

.admin-button {
	display: block;
	width: 250px;
	margin: 20px auto;
	padding: 20px 0;
	font-size: 24px;
	font-weight: bold;
	background-color: white;
	border: 2px solid black;
	border-radius: 4px;
	cursor: pointer;
	text-decoration: none;
	color: black;
}

.logout-button {
	width: 150px;
	margin-top: 30px;
	padding: 10px 0;
	font-size: 18px;
	background-color: white;
	border: 2px solid black;
	border-radius: 10px;
	cursor: pointer;
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>

	<div class="header">
		<div class="logo">
			SUWA<span>ZON</span>
		</div>
	</div>

	<div class="main-content">
		<h1>管理ホーム画面</h1>
		<p>登録、変更、削除する対象を選択してください</p>

		<a href="/Suwazon_zuichan/userManagement" class="admin-button">ユーザー</a>
		<a href="/Suwazon_zuichan/productManagement" class="admin-button">商品</a>
		<a href="/Suwazon_zuichan/admin/category_list" class="admin-button">カテゴリー</a><!-- 未実装 -->

		<form action="/Suwazon_zuichan/login" method="POST">
			<input type="hidden" name="action" value="logout">
			<input type="submit" value="ログアウト" class="logout-button">
		</form>
	</div>

</body>
</html>
