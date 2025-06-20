<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.Map, entity.Product" %>
<%
    Map<Product, Integer> cartProducts = (Map<Product, Integer>) session.getAttribute("cartProducts");
    int total = (int) request.getAttribute("total");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>購入確認画面</title>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 30px;
        }
        .container {
            width: 500px;
            margin-left: 50px; /* 左上集中 */
        }
        h2 {
            font-size: 1.5em;
            margin-bottom: 20px;
        }
        .product-container {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
        .product-image {
            width: 60px;
            height: 60px;
            background-color: #d3e0e5;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 10px;
            flex-shrink: 0;
        }
        .product-name {
            flex: 1;
        }
        .product-qty {
            width: 60px;
            text-align: center;
        }
        .product-subtotal {
            width: 80px;
            text-align: right;
        }
        .total-area {
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-weight: bold;
            font-size: 1.2em;
            margin-top: 30px;
        }
        .purchase-button {
            background-color: #f3f3f3;
            border: 1px solid #ccc;
            padding: 8px 20px;
            cursor: pointer;
        }
        a {
            display: block;
            margin-top: 25px;
            font-size: 0.9em;
            color: blue;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>カート内商品一覧</h2>

    <%
        if (cartProducts != null) {
            for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                int subtotal = product.getPrice() * quantity;
    %>
        <div class="product-container">
         <!-- 画像表示(未実装) -->
        <!--  <img src="<%= product.getImage_directory() %>" alt="画像" width="80" height="80"> -->
            <div class="product-image"><span>画像</span></div>
            <div class="product-name"><%= product.getProduct_name() %></div>
            <div class="product-qty">×<%= quantity %></div>
            <div class="product-subtotal">小計 <%= subtotal %>円</div>
        </div>
    <%
            }
        }
    %>

    <div class="total-area">
    <!-- 合計金額 -->
        <div>合計 <%= total %>円</div>
        <form action="/Suwazon_zuichan/cart" method="post">
        	<input type="hidden" name="action" value="purchaseComplete">
            <input type="submit" value="購入確定" class="purchase-button">
        </form>
    </div>

    <a href="/Suwazon_zuichan/cart">カート内商品一覧に戻る</a>
</div>

</body>
</html>
