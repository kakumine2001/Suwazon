package service;

import java.util.List;

import dao.ProductDAO;
import entity.Product;

public class ProductService {
    private static final ProductService instance = new ProductService();
    private ProductService() {}
    public static ProductService getInstance() {
        return instance;
    }
  
    private static final ProductDAO dao = ProductDAO.getInstance();

    // 商品を追加する
    public int addProduct(Product p) {
        return dao.exeInsert(p);
    }

    // 商品をIDで取得する
    public Product getProductById(int id) {
        return dao.exeSelect(id);
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
    
    
}
