package servlet;

import java.io.IOException;
import java.util.List;

import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;

@WebServlet("/product_list")
public class ProductServlet extends HttpServlet {
	private static final ProductService productService = ProductService.getInstance();

	@Override //商品を全件取得してカテゴリーサービスクラスと一緒に返す
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> products = productService.getAllProducts();
		request.setAttribute("products", products);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (!action.equals("search")) {
			doGet(request, response); //検索処理以外は商品一覧画面(全件表示)へ遷移
		} else {
			//検索による商品一覧表示
			String keyword = request.getParameter("keyword");
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			List<Product> products;
			boolean isSearch = true;
			if(categoryId == 0 && keyword.equals("")) { //どちらも指定されなかった場合は全件表示
				products = productService.getAllProducts();
				isSearch = false;
			}else {
				if(categoryId == 0) {  //Keywordのみの検索
					products = productService.searchProductsByKeyword(keyword);
				}else if(keyword.equals("")){ //カテゴリーのみの検索
					products = productService.searchProductsByCategory(categoryId);
				}else { //両方とも指定した検索
					products = productService.searchProductsByKeywordAndCategory(keyword, categoryId);
				}
			}
			request.setAttribute("isSearched", isSearch); //検索が行われたflag変数
			request.setAttribute("products", products);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		}
	}

}
