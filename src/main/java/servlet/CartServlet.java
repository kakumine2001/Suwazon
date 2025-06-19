package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CategoryService;
import service.ProductService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ProductService productService = ProductService.getInstance();

	private static final Map<Product, Integer> cartProducts = new HashMap<>();//カートの商品と数を保持

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		//カートに追加された商品と数量をセッションにセットして、カート画面へ遷移
		//カートへ追加された商品があるかないかを確認
		if (cartProducts.isEmpty()) {
			session.setAttribute("cartProducts", new java.util.HashMap<Product, Integer>());
		} else {
			session.setAttribute("cartProducts", cartProducts);
		}

		System.out.println("カートセッション取得成功");
		request.setAttribute("categoryService", CategoryService.getInstance());//カテゴリークラスを送信
		request.getRequestDispatcher("/cart_list.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		try {
			switch (action) {
			case "addProduct":
				addProduct(request, response);
				break;
			case "purchase":
				purchase(request, response, session);
				break;
			default:
				break;
			}
		} catch (IOException  | ServletException e) {
			System.out.println(e + "\n" + e.getMessage());
		}
	
	}

	//カートへ商品を登録し、商品一覧画面へ戻る
	private void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//送られてきたIDで商品entityを取得
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		Product product = productService.getProductById(product_id);

		//		cartProducts.add(productService.getProductById(product_id));
		//		quantities.put(product_id,quantities.getOrDefault(product_id, 0) + 1);

		//Mapに商品とその数を保存する
		cartProducts.put(product, cartProducts.getOrDefault(product, 0) + 1);

		request.setAttribute("message", "カートへ追加しました:" + product.getProduct_name());
		request.getRequestDispatcher("/product_list").forward(request, response);
	}

	//カートの商品を購入する
	private void purchase(HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException  {
		//Streamでカート内商品の合計金額を計算
		Stream<Map.Entry<Product,Integer>> stream = cartProducts.entrySet().stream();
		int sum = stream.mapToInt(entry -> entry.getKey().getPrice() * entry.getValue()).sum();
		request.setAttribute("total", sum);
		request.getRequestDispatcher("/purchase.jsp").forward(request, response);
	}

}
