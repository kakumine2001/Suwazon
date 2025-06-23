package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
//@Repository
public final  class ProductDAO extends CommonDAO {
    private static final ProductDAO instance = new ProductDAO();

    private ProductDAO() {}

    public static ProductDAO getInstance() {
        return instance;
    }
	//↑↑↑↑↑Singleton パターン、分かんない場合または興味がなく面倒くさいなら消してよい↑↑↑↑
    // INSERT
    public int exeInsert(Product p) {
        String sql = "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?);";
        int result = 0;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getProductId());
            stmt.setString(2, p.getProductName());
            stmt.setInt(3, p.getPrice());
            stmt.setInt(4, p.getStock());
            stmt.setString(5, p.getDescription());
            stmt.setString(6, p.getImageDirectory());
            stmt.setInt(7, p.getCategoryId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    
    
    
    // SELECT by Keyword AND Category
    public List<Product> exeSelectByKeywordAndCategory(String keyword,int categoryId) {
    	String sql = "SELECT * FROM products WHERE product_name LIKE ? AND category_id = ?";
        List<Product> products = new ArrayList<Product>();
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setInt(2,categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
                );
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    // SELECT by Keyword
    public List<Product> exeSelectByKeyword(String keyword) {
    	String sql = "SELECT * FROM products WHERE product_name LIKE ?;";
        List<Product> products = new ArrayList<Product>();
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
                );
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    // SELECT by Category
    public List<Product> exeSelectByCategory(int categoryId) {
    	String sql = "SELECT * FROM products WHERE category_id = ?";
        List<Product> products = new ArrayList<Product>();
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
                );
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    // SELECT by ID
	public Product exeSelect(int productId) {
		return exeSelectById(productId);
	}

	// SELECT by ID
    public Product exeSelectById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?;";
        Product p = null;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p = new Product(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    // SELECT ALL
    public List<Product> exeSelectAll() {
        String sql = "SELECT * FROM products ORDER BY product_id;";
        List<Product> products = new ArrayList<>();
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
                );
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // DELETE
    public int exeDelete(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?;";
        int result = 0;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // UPDATE ALL
    public int exeUpdateAll(Product p) {
        String sql = "UPDATE products SET product_name = ?, price = ?, stock = ?, description = ?, image_directory = ?, category_id = ? WHERE product_id = ?;";
        int result = 0;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getProductName());
            stmt.setInt(2, p.getPrice());
            stmt.setInt(3, p.getStock());
            stmt.setString(4, p.getDescription());
            stmt.setString(5, p.getImageDirectory());
            stmt.setInt(6, p.getCategoryId());
            stmt.setInt(7, p.getProductId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // UPDATE stock
    public int exeUpdateStock(int productId, int stock) {
        String sql = "UPDATE products SET stock = ? WHERE product_id = ?;";
        int result = 0;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stock);
            stmt.setInt(2, productId);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // UPDATE price
    public int exeUpdatePrice(int productId, int price) {
        String sql = "UPDATE product SET price = ? WHERE product_id = ?;";
        int result = 0;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, price);
            stmt.setInt(2, productId);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // UPDATE name
    public int exeUpdateName(int productId, String name) {
        String sql = "UPDATE products SET product_name = ? WHERE product_id = ?;";
        int result = 0;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, productId);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

   


}
