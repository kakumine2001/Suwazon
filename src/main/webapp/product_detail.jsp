<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="entity.Product"%>
<%@ page import="service.ProductService"%>
<%
// パラメータから商品ID取得
String pidStr = request.getParameter("product_id");
String backUrl = request.getParameter("previous_page");
Product product = null;

if (pidStr != null && backUrl != null) {
	try {
		int productId = Integer.parseInt(pidStr);
		product = ProductService.getInstance().getProductById(productId);
		backUrl = backUrl.equals("product_list") ? "/Suwazon_zuichan/product_list" : "/Suwazon_zuichan/cart";
	} catch (Exception e) {
		out.println("不正な商品IDです。");
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<style>
body {
	font-family: sans-serif;
	margin: 30px;
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

.product-container {
	width: 600px;
}

.product-name {
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 10px;
}

.price {
	font-size: 20px;
	margin: 15px 0;
}

.image-box {
	float: right;
	margin-left: 20px;
	width: 200px;
	height: 150px;
	background-color: #eef3f8;
	display: flex;
	align-items: center;
	justify-content: center;
}

.description {
	clear: both;
	border: 1px solid #aaa;
	padding: 15px;
	margin-top: 20px;
}

.back-link {
	margin-top: 20px;
	display: block;
}

.cart-button {
	padding: 6px 14px;
	font-size: 14px;
	background-color: #e0e0e0;
	border: 1px solid #888;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="header">
		<div class="logo">
			SUWA<span>ZON</span>
		</div>
	</div>
	<div class="product-container">
		<h2>商品詳細</h2>

		<%
		if (product != null) {
		%>
		<div class="product-name"><%=product.getProductName()%></div>

		<div class="image-box">
			<img src="<%=product.getImageDirectory()%>" alt="商品画像" width="150"
				height="100">
		</div>

		<div class="price"><%=product.getPrice()%>円
		</div>

		<form action="/Suwazon_zuichan/cart" method="post">
			<input type="hidden" name="product_id"
				value="<%=product.getProductId()%>"> <input type="hidden"
				name="action" value="addProduct"> <input type="submit"
				class="cart-button" value="カートに入れる">
		</form>

		<div class="description">
			<%=product.getDescription()%>
		</div>

		<a class="back-link" href="<%=backUrl%>">戻る</a>

		<%
		} else {
		%>
		<p>商品が見つかりませんでした。</p>
		<a class="back-link" href="product_list.jsp">戻る</a>
		<%
		}
		%>
	</div>
</body>
</html>
