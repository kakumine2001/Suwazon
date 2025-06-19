package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.PurchaseHistory;

public class PurchaseHistoryDAO extends CommonDAO {
	public static final PurchaseHistoryDAO instance = new PurchaseHistoryDAO();

	private PurchaseHistoryDAO() {
	}

	public static PurchaseHistoryDAO getInstance() {
		return instance;
	}
	
	//↑↑↑↑↑Singleton パターン、分かんない場合または興味がなく面倒くさいなら消してよい↑↑↑↑
	
	// 全件取得
	public List<PurchaseHistory> exeSelectAll() {
		String sql = "SELECT * FROM purchase_history ORDER BY user_id, pruduct_id;";
		List<PurchaseHistory> historyList = new ArrayList<>();

		try (Connection conn = createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PurchaseHistory history = new PurchaseHistory();
				history.setUser_id(rs.getString(1));
				history.setPruduct_id(rs.getInt(2));
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
}
