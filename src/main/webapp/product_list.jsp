<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collector"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*, entity.Product,entity.Category"%>
<%@page import="service.CategoryService"%>
<%
CategoryService categoryService = CategoryService.getInstance();

List<Product> products = (List<Product>) request.getAttribute("products");
List<Category> categories = categoryService.getAllCategories();
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

		<!-- 商品検索 -->
		<div class="search-bar">
			<form action="/Suwazon_zuichan/product_list" method="POST">
				<select name="categoryId">
					<option value="0">カテゴリー</option>
					<!-- カテゴリーなしで検索のとき、0を送信 -->
					<%
					for (Category c : categories) {
					%>
					<option value="<%=c.getCategoryId()%>"><%=c.getCategoryName()%></option>
					<%
					}
					%>
				</select> <input type="text" name="keyword" placeholder="キーワード検索"> <input
					type="hidden" name="action" value="search"> <input
					type="submit" value="検索">
			</form>
			<%
			Object isSearched = request.getAttribute("isSearched");
			if (isSearched != null && (boolean) isSearched) {
			%>
			<form action="/Suwazon_zuichan/product_list" method="GET">
				<input type="submit" value="全商品一覧へ戻る">
			</form>
			<%
			}
			%>
		</div>

		<!-- 各メニュー -->
		<div class="header-buttons">
			<!-- 購入履歴へ -->
			<form action="/Suwazon_zuichan/purchaseHistory" method="GET">
				<input type="submit" value="購入履歴">
			</form>
			<!-- カート画面へ -->
			<form action="/Suwazon_zuichan/cart" method="GET">
				<input type="submit" value="カートを見る">
			</form>
			<!-- ログアウト -->
			<form action="/Suwazon_zuichan/login" method="POST">
				<input type="hidden" name="action" value="logout"> <input
					type="submit" value="ログアウト">
			</form>
		</div>
	</header>

	<div class="product-list">
		<h1>商品一覧</h1>
		<%
		String message = (String) request.getAttribute("message");
		%>
		<%
		if (message != null) {
		%>
		<p><%=message%></p>
		<%
		}
		%>
		<div class="grid">
			<%
			if (products.isEmpty()) {
			%>
			<h3>商品が見つかりませんでした</h3>
			<%
			} else {
			for (Product p : products) {
			%>
			<div class="card">
				<!-- 画像表示 -->
				<div class="image-placeholder">
					<img alt="商品画像" src="images/<%=p.getImageDirectory() %>" width=200px height=100px>
				</div>
				<div class="product-name"><%=p.getProductName()%><br>
					<!-- ↓サービスクラスでカテゴリを取得し、名前を出力↓ -->
					<span style="font-size: 12px;"><%=categoryService.getCategoryById(p.getCategoryId()).getCategoryName()%></span>
				</div>
				<div class="price"><%=p.getPrice()%>円
				</div>
				<%
				if (p.getStock() > 0) {
				%>
				<form action="/Suwazon_zuichan/cart" method="POST">
					<!-- カート追加機能 -->
					<input type="hidden" name="product_id"
						value="<%=p.getProductId()%>"> <input type="hidden"
						name="action" value="addProduct">
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
					href="product_detail.jsp?product_id=<%=p.getProductId()%>
					&previous_page=product_list">商品詳細へ</a>
			</div>
			<%
			}
			}
			%>
		</div>
	</div>
</body>
</html>
