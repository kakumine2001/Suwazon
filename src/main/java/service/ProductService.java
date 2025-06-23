package service;

import java.util.List;

import dao.ProductDAO;
import entity.Product;
//@Service
public final class ProductService {
    private static final ProductService instance = new ProductService();
    private ProductService() {}
    public static ProductService getInstance() {
        return instance;
    }
  
    private final ProductDAO dao = ProductDAO.getInstance();

    // 商品を追加する
    public int addProduct(Product p) {
        return dao.exeInsert(p);
    }

    // 商品をIDで取得する
    public Product getProductById(int id) {
        return dao.exeSelectById(id);
    }

    // 全商品を取得する
    public List<Product> getAllProducts() {
        return dao.exeSelectAll();
    }

    // 商品を削除する
    public int deleteProductById(int id) {
        return dao.exeDelete(id);
    }

    // 商品情報をすべて更新する
    public int updateAllProperty(Product p) {
        return dao.exeUpdateAll(p);
    }

    // 商品名を更新する
    public int updateName(int id, String name) {
        return dao.exeUpdateName(id, name);
    }

    // 商品価格を更新する
    public int updatePrice(int id, int price) {
        return dao.exeUpdatePrice(id, price);
    }

    // 在庫数を更新する
    public int updateStock(int id, int stock) {
        return dao.exeUpdateStock(id, stock);
    }
    
    //購入による在庫数減少
    public int updataStockByPurchase(int id,int quantity) {
    	if(getProductById(id).getStock() - quantity < 0) {
    		quantity = 0;
    	}
        return dao.exeUpdateStock(id, quantity);
    }
    
    
    //検索ワードとカテゴリーidで商品検索
    public List<Product> searchProductsByKeywordAndCategory(String keyword,int categoryId) {
    	return dao.exeSelectByKeywordAndCategory(keyword, categoryId);
    }
    
    //検索ワードのみで商品検索
    public List<Product> searchProductsByKeyword(String keyword) {
    	return dao.exeSelectByKeyword(keyword);
    }
    
    //カテゴリーidのみで商品検索
    public List<Product> searchProductsByCategory(int categoryId) {
    	return dao.exeSelectByCategory(categoryId);
    }
}
