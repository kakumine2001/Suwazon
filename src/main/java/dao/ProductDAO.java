package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDAO extends CommonDAO {
    private static final ProductDAO instance = new ProductDAO();

    private ProductDAO() {}

    public static ProductDAO getInstance() {
        return instance;
    }
	//↑↑↑↑↑Singleton パターン、分かんない場合または興味がなく面倒くさいなら消してよい↑↑↑↑
    // INSERT
    public int exeInsert(Product p) {
        String sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?);";
        int result = 0;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getProduct_id());
            stmt.setString(2, p.getProduct_name());
            stmt.setInt(3, p.getPrice());
            stmt.setInt(4, p.getStock());
            stmt.setString(5, p.getDescription());
            stmt.setString(6, p.getImage_directory());
            stmt.setInt(7, p.getCategory_id());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // SELECT by ID
    public Product exeSelect(int productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?;";
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
        String sql = "SELECT * FROM product ORDER BY product_id;";
        List<Product> list = new ArrayList<>();
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
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // DELETE
    public int exeDelete(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?;";
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
        String sql = "UPDATE product SET product_name = ?, price = ?, stock = ?, description = ?, image_directory = ?, category_id = ? WHERE product_id = ?;";
        int result = 0;
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getProduct_name());
            stmt.setInt(2, p.getPrice());
            stmt.setInt(3, p.getStock());
            stmt.setString(4, p.getDescription());
            stmt.setString(5, p.getImage_directory());
            stmt.setInt(6, p.getCategory_id());
            stmt.setInt(7, p.getProduct_id());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // UPDATE stock
    public int exeUpdateStock(int productId, int stock) {
        String sql = "UPDATE product SET stock = ? WHERE product_id = ?;";
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
        String sql = "UPDATE product SET product_name = ? WHERE product_id = ?;";
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
