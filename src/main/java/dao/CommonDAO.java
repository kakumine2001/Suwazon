package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 全DAO共通の処理を記載する
 * 各DAOで継承することで、メソッドをそのまま使用できるようにする
 */
public class CommonDAO {

	/***
	 * コネクション作成
	 * DBに接続する際に実行する
	 * @return 生成されたコネクションを返す
	 */
	public Connection createConnection() {
		try {
			final String HOST_NAME = "localhost:5432";
			final String DATABASE_NAME = "suwazon";
			final String USER_NAME = "postgres";
			final String PASSWORD = "postgres";
			try {
				// postgreSQLのJDBCドライバを読み込み
				Class.forName("org.postgresql.Driver");

			} catch (ClassNotFoundException e) {
				// JDBCドライバが見つからない場合
				e.printStackTrace();
			}

			String url = "jdbc:postgresql://" + HOST_NAME + "/" + DATABASE_NAME;

			Connection con = DriverManager.getConnection(url, USER_NAME, PASSWORD);

			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
