package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.PurchaseHistory;
//@Repository
public final class PurchaseHistoryDAO extends CommonDAO {
	private static final PurchaseHistoryDAO instance = new PurchaseHistoryDAO();

	private PurchaseHistoryDAO() {
	}

	public static PurchaseHistoryDAO getInstance() {
		return instance;
	}

	//↑↑↑↑↑Singleton パターン、分かんない場合または興味がなく面倒くさいなら消してよい↑↑↑↑

	// 全件取得
	public List<PurchaseHistory> exeSelectAll() {
		String sql = "SELECT * FROM purchase_histories ORDER BY user_id, product_id;";
		List<PurchaseHistory> historyList = new ArrayList<>();

		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PurchaseHistory history = new PurchaseHistory();
				history.setUserId(rs.getString(1));
				history.setPruductId(rs.getInt(2));
				history.setDate(rs.getDate(3).toLocalDate());
				history.setNumber(rs.getInt(4));
				historyList.add(history);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}

		return historyList;
	}

	// ユーザーの購入履歴を全件取得
	public List<PurchaseHistory> exeSelectByUserid(String user_id) {
		String sql = "SELECT * FROM purchase_histories "
				+ "Where user_id = ? ORDER BY user_id, product_id;";

		List<PurchaseHistory> historyList = new ArrayList<>();

		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PurchaseHistory history = new PurchaseHistory();
				history.setUserId(rs.getString(1));
				history.setPruductId(rs.getInt(2));
				history.setDate(rs.getDate(3).toLocalDate());
				history.setNumber(rs.getInt(4));
				historyList.add(history);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		return historyList;
	}


	//ユーザーidと商品idと日付で検索
	public PurchaseHistory exeSelectByUserIdAndProductIdAndDate(String userId,int productId,LocalDate date) {
		String sql = "SELECT * FROM purchase_histories "
				+ "Where user_id = ? AND product_id = ? AND purchased_at = ?;";

		PurchaseHistory ph = null;

		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, userId);
			stmt.setInt(2, productId);
			stmt.setDate(3, java.sql.Date.valueOf(date));
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				ph = new PurchaseHistory();
				ph.setUserId(rs.getString(1));
				ph.setPruductId(rs.getInt(2));
				ph.setDate(rs.getDate(3).toLocalDate());
				ph.setNumber(rs.getInt(4));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		return ph;
	}
	
	
	//購入履歴を新規登録,日付は購入処理が行われた日になる
	public int exeInsert(int product_id, String user_id, int quantity) {
		String sql = "INSERT INTO purchase_histories VALUES (?,?,?,?);";
		int result = 0;
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user_id);
			stmt.setInt(2, product_id);
			stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			stmt.setInt(4, quantity);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "\n" + e);
			e.printStackTrace();
		}
		return result;
	}

	
	//同じユーザーと同じ商品、同じ日付のレコードが存在する場合、数字だけ変更
	public int updatePurchaseHistory(int quantity,String userId,int productId) {
		return updatePurchaseHistoryNumber(quantity, userId, productId);
	}

	//同じユーザーと同じ商品、同じ日付のレコードが存在する場合、数字だけ変更
	public int updatePurchaseHistoryNumber(int quantity,String userId,int productId) {
		String sql = "UPDATE purchase_histories SET number = ?"
				+ " WHERE user_id = ? AND product_id = ?;";
		int result = 0;
		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, quantity);
			stmt.setString(2,userId);
			stmt.setInt(3,productId);
			result = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage() + "\n" + ex);
		}
		System.out.println(result);
		return result;
	}
}
