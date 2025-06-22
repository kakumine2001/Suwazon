<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.*, java.time.format.DateTimeFormatter"%>
<%@ page import="entity.*"%>
<%@ page import="service.*"%>

<%
// 購入履歴とサービスを取得
List<PurchaseHistory> histories = (List<PurchaseHistory>) request.getAttribute("purchaseHistorys");
ProductService productService = ProductService.getInstance();
CategoryService categoryService = CategoryService.getInstance();

// 日付でグループ化
Map<String, List<PurchaseHistory>> grouped = new LinkedHashMap<>();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

for (PurchaseHistory ph : histories) {
	String dateStr = ph.getDate().format(formatter);
	grouped.computeIfAbsent(dateStr, k -> new ArrayList<>()).add(ph);
}
%>

<html>
<head>
<title>購入履歴一覧</title>
<style>
body {
	font-family: sans-serif;
	text-align: center;
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

.history-box {
	border: 1px solid black;
	margin: 20px auto;
	padding: 10px;
	width: 60%;
}

.item-row {
	display: flex;
	justify-content: space-between;
	padding: 5px 20px;
}

.item-row div {
	flex: 1;
	text-align: center;
}

.back-link {
	margin-top: 30px;
}
</style>
</head>
<body>
	<div class="header">
		<div class="logo">
			SUWA<span>ZON</span>
		</div>
	</div>
	<h2>購入履歴一覧</h2>

	<%
	for (Map.Entry<String, List<PurchaseHistory>> entry : grouped.entrySet()) {
		String date = entry.getKey();
		List<PurchaseHistory> list = entry.getValue();
	%>
	<div class="history-box">
		<div>
			<!-- 日付 -->
			<strong><%=date%></strong>
		</div>
		<hr>
		<%
		for (PurchaseHistory ph : list) { //日付ごとの購入履歴を表示
			Product p = productService.getProductById(ph.getPruductId());
			Category c = categoryService.getCategoryById(p.getCategoryId());
		%>
		<div class="item-row">
			<div><%=p.getProductName()%>
				×<%=ph.getNumber()%></div>
			<div><%=c.getCategoryName()%></div>
			<div>
				<!-- 詳細画面、未実装 -->
				<form action="" method="GET">
					<input type="submit" value="詳細">
				</form>
			</div>
		</div>
		<%
		}
		%>
	</div>
	<%
	}
	%>

	<div class="back-link">
		<a href="/Suwazon_zuichan/product_list">商品一覧画面に戻る</a>
	</div>
</body>
</html>
