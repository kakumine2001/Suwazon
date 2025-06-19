package servlet;

import java.io.IOException;
import java.util.List;

import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.ProductService;

@WebServlet("/product_list")
public class ProductServlet extends HttpServlet {
	private static final ProductService productService = ProductService.getInstance();
//	private static final CategoryService categoryService = CategoryService.getInstance();
	
	@Override//商品を全件取得してカテゴリーサービスクラスと一緒に返す
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> products = productService.getAllProducts();
		request.setAttribute("products", products);
		request.setAttribute("categoryService", CategoryService.getInstance());
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // POSTもGETの処理で代用
	}
	
	
}
