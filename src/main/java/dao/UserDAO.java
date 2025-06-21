package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
//@Repository
public final class UserDAO extends CommonDAO {
	private static final UserDAO instance = new UserDAO();

	private UserDAO() {}

	public static UserDAO getInstance() {
		return instance;
	}
	//↑↑↑↑↑Singleton パターン、分かんない場合または興味がなく面倒くさいなら消してよい↑↑↑↑
	//INSERT
	public int exeInsert(User u) {
		String sql = "INSERT INTO users VALUES (?,?,?,?,?,?);";
		int result = 0;
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, u.getUserId());
			stmt.setString(2, u.getUserName());
			stmt.setInt(3, u.getAge());
			stmt.setString(4,u.getGender());
			stmt.setString(5, u.getPassword());
			stmt.setBoolean(6, u.isAdmin());
			System.out.println(u);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "\n" + e);
			e.printStackTrace();
		}
		return result;
	}

	//selectById
	public User exeSelect(String userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?;";
		User u = null;
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getString(1));
				u.setUserName(rs.getString(2));
				u.setAge(rs.getInt(3));
				u.setGender(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setAdmin(rs.getBoolean(6));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "\n" + e);
			e.printStackTrace();
		}
		return u;
	}

	//SELECT *
	public List<User> exeSeleteAllUsers() {
		String sql = "SELECT * FROM users ORDER BY user_id;";
		List<User> users = new ArrayList<User>();

		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getString(1));
				u.setUserName(rs.getString(2));
				u.setAge(rs.getInt(3));
				u.setGender(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setAdmin(rs.getBoolean(6));
				users.add(u);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		return users;
	}

	//DELETE処理
	public int exeDelete(int user_id) {
		String sql = "DELETE FROM users WHERE user_id = ?;";
		int result = 0;
		try (Connection conn = createConnection(); //try with resources
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, user_id);
			result = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		return result;
	}

	//UPDATEALL
	public int exeUpdateAll(User u) {
		String sql = "UPDATE users SET user_name = ?,"
				+ "age = ?,gender = ?,password = ?,is_admin = ? WHERE user_id = ?;";
		int result = 0;
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, u.getUserName());
			stmt.setInt(2, u.getAge());
			stmt.setString(3,u.getGender());
			stmt.setString(4, u.getPassword());
			stmt.setBoolean(5, u.isAdmin());
			stmt.setString(6, u.getUserId());
			result = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		return result;
	}

	//UPDATE Password
	public int exeUpdatePassword(int user_id, String password) {
		String sql = "UPDATE users SET password = ? WHERE user_id = ?;";
		int result = 0;
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, password);
			stmt.setInt(2, user_id);
			result = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		return result;
	}

	//UPDATE Age
	public int exeUpdateAge(int user_id, int age) {
		String sql = "UPDATE users SET age = ? WHERE user_id = ?;";
		int result = 0;
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, age);
			stmt.setInt(2, user_id);
			result = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		return result;
	}

	//UPDATE Name
	public int exeUpdateName(int user_id, String name) {
		String sql = "UPDATE users SET user_name = ? WHERE user_id = ?;";
		int result = 0;
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setInt(2, user_id);
			result = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		return result;
	}

}
