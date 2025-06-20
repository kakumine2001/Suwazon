package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;

public class CategoryDAO extends CommonDAO {
	private static final CategoryDAO instance = new CategoryDAO();

	private CategoryDAO() {}

	public static CategoryDAO getInstance() {
		return instance;
	}

	//↑↑↑↑↑Singleton パターン、分かんない場合または興味がなく面倒くさいなら消してよい↑↑↑↑
	
	// INSERT
	public int exeInsert(Category c) {
		String sql = "INSERT INTO categories (category_id, category_name) VALUES (?, ?);";
		int result = 0;

		try (Connection conn = createConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, c.getCategoryId());
			stmt.setString(2, c.getCategoryName());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n" + e);
		}
		return result;
	}

	// SELECT ALL
	public List<Category> exeSelectAll() {
		String sql = "SELECT * FROM categories ORDER BY category_id;";
		List<Category> categoryList = new ArrayList<>();

		try (Connection conn = createConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Category c = new Category();
				c.setCategoryId(rs.getInt("category_id"));
				c.setCategoryName(rs.getString("category_name"));
				categoryList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n" + e);
		}
		return categoryList;
	}

	// SELECT by ID
	public Category exeSelectById(int id) {
		String sql = "SELECT * FROM categories WHERE category_id = ?;";
		Category c = null;

		try (Connection conn = createConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c = new Category();
				c.setCategoryId(rs.getInt("category_id"));
				c.setCategoryName(rs.getString("category_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n" + e);
		}
		return c;
	}

	// UPDATE category_nameのみ
	public int exeUpdateName(int category_id, String newName) {
		String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?;";
		int result = 0;

		try (Connection conn = createConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, newName);
			stmt.setInt(2, category_id);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n" + e);
		}
		return result;
	}

	// DELETE
	public int exeDelete(int category_id) {
		String sql = "DELETE FROM categories WHERE category_id = ?;";
		int result = 0;

		try (Connection conn = createConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, category_id);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n" + e);
		}
		return result;
	}
}
