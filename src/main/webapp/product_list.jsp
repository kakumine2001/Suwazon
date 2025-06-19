<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*, entity.Product"%>
<%@page import="service.CategoryService"%>
<%
List<Product> products = (List<Product>) request.getAttribute("products");
CategoryService categoryService = (CategoryService)request.getAttribute("categoryService");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧 - SUWAZON</title>
<style>
body {
	background-color: #d2e4e8;
	font-family: "メイリオ", sans-serif;
	margin: 0;
	padding: 0;
}

header {
	background-color: #d2e4e8;
	padding: 10px 20px;
	display: flex;
	align-items: center;
	gap: 10px;
}

.logo {
	font-family: Impact, sans-serif;
	font-size: 32px;
}

.logo span {
	color: orange;
}

.search-bar {
	flex: 1;
	display: flex;
	align-items: center;
	gap: 5px;
}

select, input[type="text"], input[type="submit"] {
	padding: 5px;
	font-size: 14px;
}

.header-buttons {
	display: flex;
	gap: 10px;
}

.product-list {
	padding: 20px;
}

h1 {
	font-size: 28px;
	margin-bottom: 20px;
}

.grid {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	gap: 20px;
}

.card {
	background-color: white;
	border: 1px solid #000;
	padding: 10px;
	text-align: center;
}

.image-placeholder {
	width: 100%;
	height: 100px;
	background-color: #ddd;
	line-height: 100px;
}

.product-name {
	font-weight: bold;
	font-size: 14px;
	margin-top: 5px;
}

.price {
	color: red;
	font-weight: bold;
	margin: 5px 0;
}

.stock-out {
	background-color: orange;
	padding: 5px;
	font-weight: bold;
}

.btn-cart {
	background-color: lightblue;
	padding: 5px;
	margin: 5px 0;
	cursor: pointer;
}

.detail-link {
	font-size: 12px;
	color: blue;
	text-decoration: underline;
}
</style>
</head>
<body>
	<header>
		<div class="logo">
			SUWA<span>ZON</span>
		</div>
		<div class="search-bar">
			<select name="category">
				<option>カテゴリー</option>
				<!-- 例: <option value="1">掃除</option> -->
			</select> <input type="text" placeholder="キーワード検索"> <input
				type="submit" value="検索">
		</div>
		<div class="header-buttons">
			<!-- 書き換える -->
			<form action="/Suwazon_zuichan/cart" method="get">
				<input type="submit" value="カートを見る">
			</form>
			<!-- 書き換える -->
			<form action="/Suwazon_zuichan/login" method="post">
			<input type="hidden" name="action" value="logout">
				<input type="submit" value="ログアウト">
			</form>
		</div>
	</header>

	<div class="product-list">
		<h1>商品一覧</h1>
		<%String message = (String)request.getAttribute("message"); %>
		<%if(message != null){ %>
		<p><%=message %></p>
		<%} %>
		<div class="grid">
			<%
			for (Product p : products) {
			%>
			<div class="card">
				<div class="image-placeholder">商品画像</div>
				<div class="product-name"><%=p.getProduct_name()%><br>
					<!-- ↓サービスクラスでカテゴリを取得し、名前を出力↓ -->
					<span style="font-size: 12px;"><%=categoryService.getCategoryById(p.getCategory_id()).getCategory_name()%></span>
				</div>
				<div class="price"><%=p.getPrice()%>円
				</div>
				<%
				if (p.getStock() > 0) {
				%>
				
				<form action="/Suwazon_zuichan/cart" method="post">
					<!-- カート追加機能 -->
					<input type="hidden" name="product_id"
						value="<%=p.getProduct_id()%>">
					<input type="hidden" name="action" value="addProduct">
					<div class="btn-cart">
						<input type="submit" value="カートへ">
					</div>
				</form>
				<%
				} else {
				%>
				<div class="stock-out">在庫切れ</div>
				<%
				}
				%>
				<!-- 商品詳細 -->
				<a class="detail-link"
					href="productDetail.jsp?id=<%=p.getProduct_id()%>">商品詳細へ</a>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>
