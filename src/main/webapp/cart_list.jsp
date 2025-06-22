<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.*, entity.Product"%>
<%@page import="service.CategoryService"%>
<%@ page import="entity.User"%>
<%
//ログインしているかを認証
User user = (User) session.getAttribute("user");
if (user == null) {
%>
<h2 style="color: red;">ログインしてください</h2>
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
<title>カート内商品一覧</title>
<style>

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

.product-container {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
}

.product-image {
	width: 100px;
	height: 100px;
	background-color: #d3e0e5;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-right: 15px;
}

.product-details {
	flex-grow: 1;
}

.product-name {
	font-weight: bold;
	font-size: 1.1em;
}

.action-buttons {
	margin-top: 5px;
}

.action-buttons form {
	display: inline;
}

.quantity {
	margin-left: 10px;
}

.return-link {
	margin-top: 30px;
	display: inline-block;
	color: blue;
}

.purchase-button {
	display: block;
	margin-top: 20px;
	font-size: 1.2em;
	padding: 10px 20px;
}

.message {
	color: #0066cc;
	margin-left: 20px;
}
</style>
</head>
<body>
<div class="header">
	<div class="logo">
		SUWA<span>ZON</span>
	</div>
</div>
	<h2>カート内商品一覧</h2>

	<%
	// セッションからカートと個数取得
	Map<Product, Integer> cartProducts = (Map<Product, Integer>) session.getAttribute("cartProducts");

	// リクエスト属性から削除メッセージ取得
	String deleteMessage = (String) request.getAttribute("deleteMessage");

	//リクエスト属性からカテゴリーサービスクラス取得
	CategoryService categoryService = CategoryService.getInstance();

	if (deleteMessage != null) {
	%>
	<span class="message">商品を削除しました：<%=deleteMessage%></span>
	<%
	}

	if (cartProducts == null || cartProducts.isEmpty()) {
	%>
	<p>カート内に商品が追加されていません</p>
	<a href="/Suwazon_zuichan/product_list" class="return-link">商品一覧に戻る</a>
</body>
</html>
<%
return; // 以下の処理をスキップ
}
%>

<%
for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
	Product product = entry.getKey();
	int quantity = entry.getValue();
%>
<div class="product-container">
	<div class="product-image">画像</div>
	<div class="product-details">
		<div class="product-name"><%=product.getProductName()%></div>
		<div><%=categoryService.getCategoryById(product.getCategoryId()).getCategoryName()%></div>
		<div><%=product.getPrice()%>円
		</div>
		<div class="action-buttons">
			<form action="product_detail.jsp" method="get">
				<!-- 商品詳細 -->
				<input type="hidden" name="product_id"
					value="<%=product.getProductId()%>" /> <input type="hidden"
					name="previous_page" value="cart_list"> <input
					type="submit" value="詳細" />
			</form>

			<form action="/Suwazon_zuichan/cart" method="post">
				<!-- 商品削除 -->
				<input type="hidden" name="product_id"
					value="<%=product.getProductId()%>" /> <input type="hidden"
					name="action" value="delete"> <input type="submit"
					value="削除" />
			</form>

			<span class="quantity">×<%=quantity%></span>
		</div>
	</div>
</div>
<%
}
%>

<a href="/Suwazon_zuichan/product_list" class="return-link">商品一覧に戻る</a>

<form action="/Suwazon_zuichan/cart" method="post">
	<!-- 購入 -->
	<input type="hidden" name="action" value="purchase"> <input
		type="submit" class="purchase-button" value="購入する" />
</form>
</body>
</html>
