<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page
	import="java.util.*, entity.Product, entity.Category, entity.User"%>
<%@ page import="service.*"%>
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
<title>商品管理画面</title>
<style>
body {
	background-color: #f9f9f9;
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

h1 {
	font-size: 24px;
	margin: 20px;
}

/* 検索エリア全体：中央揃え */
.search-bar {
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 30px;
	margin-bottom: 20px;
}

/* 検索フォーム内の各要素 */
.search-form {
	display: flex;
	align-items: center;
	gap: 10px;
}

/* ボタン共通 */
.btn {
	padding: 6px 14px;
	font-weight: bold;
	border: 1px solid #444;
	cursor: pointer;
	background-color: #ffffff;
}

/* 編集ボタン */
.btn-edit {
	background-color: #d0e7f5;
}

/* 削除ボタン */
.btn-delete {
	background-color: #f5b471;
}

/* 新規追加ボタン（目立つように紫） */
.btn-add {
	background-color: purple;
	color: white;
	font-weight: bold;
	padding: 6px 14px;
	text-decoration: none;
}

/* 戻るリンクの外観 */
.return-link {
	margin: 20px;
	text-align: left;
}

/* テーブルレイアウト */
table {
	width: 90%;
	margin: 20px auto;
	border-collapse: collapse;
	background-color: #ffffff;
}

th, td {
	border: 1px solid #444;
	padding: 10px;
	text-align: center;
}

th {
	background-color: #eee;
}
</style>

</head>
<body>
	<%
	CategoryService categoryService = CategoryService.getInstance();
	List<Product> products = (List<Product>) request.getAttribute("products");
	List<Category> categories = categoryService.getAllCategories();
	%>
	<div class="header">
		<div class="logo">
			SUWA<span>ZON</span>
		</div>
	</div>

	<h1>商品管理画面</h1>
<!-- 商品検索＋新規追加 -->
<div class="search-bar">
	<!-- 検索フォーム -->
	<form action="/Suwazon_zuichan/productManagement" method="POST" class="search-form">
		<select name="categoryId">
			<option value="0">カテゴリー▼</option>
			<%
			if (categories != null) {
				for (Category c : categories) {
			%>
			<option value="<%=c.getCategoryId()%>"><%=c.getCategoryName()%></option>
			<%
				}
			}
			%>
		</select>
		<input type="text" name="keyword" placeholder="検索キーワード" />
		<input type="hidden" name="action" value="search">
		<input type="submit" value="検索" class="btn" />

		<%
		Object isSearched = request.getAttribute("isSearched");
		if (isSearched != null && (boolean) isSearched) {
		%>
			<!-- 全商品一覧へ戻るボタン（検索時のみ表示） -->
			<form action="/Suwazon_zuichan/productManagement" method="GET" style="display:inline;">
				<input type="submit" value="全商品一覧へ戻る" class="btn" />
			</form>
		<%
		}
		%>
	</form>

	<!-- 新規追加ボタン --><!-- 未実装 -->
	<a href="product_register.jsp" class="btn btn-add">新規追加</a>
</div>

	<table>
		<tr>
			<th>商品ID</th>
			<th>商品名</th>
			<th>カテゴリー</th>
			<th colspan="2"></th>
		</tr>
		<%
		if (products != null && !products.isEmpty()) {
			for (Product p : products) {
		%>
		<tr>
			<td><%=p.getProductId()%></td>
			<td><%=p.getProductName()%></td>
			<td><%=categoryService.getCategoryById(p.getCategoryId()).getCategoryName()%></td>
			<td>
			<!-- 商品変更--未実装 -->
				<form action="product_edit.jsp" method="get"
					style="display: inline;">
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="productId" value="" /> <input type="submit"
						value="変更" class="btn btn-edit" />
				</form>
			</td>
			<td>
				<!-- 商品削除--未実装 -->
				<form action="productDelete" method="post" style="display: inline;"
				onsubmit="return confirm('本当に削除しますか？');">
					<input type="hidden" name="productId" value="<%=p.getProductId()%>">
					<input type="submit" value="削除" class="btn btn-delete">
				</form>
			</td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="5">該当する商品がありません。</td>
		</tr>
		<%
		}
		%>
	</table>

	<div class="return-link">
		<a href="managerJSP/admin_home.jsp">管理者ホーム画面に戻る</a>
	</div>
</body>
</html>
