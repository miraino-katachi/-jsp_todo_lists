package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.TodoItemModel;

/**
 * TODOリストDAOクラス
 * @author tadashi
 *
 */
public class TodoItemDAO {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	// 文字コードをutf8mb4_general_ciに設定
	private final String JDBC_URL = "jdbc:mysql://localhost/jsp_todo_list?connectionCollation=utf8mb4_general_ci";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	/**
	 * TODOリストを全件取得します
	 * @return TODOリスト
	 */
	public List<TodoItemModel> findAll() {

		List<TodoItemModel> list = new ArrayList<TodoItemModel>();
		String sql = "select "
				+ "* "
				+ "from todo_items "
				+ "where is_deleted=0 "
				+ "order by expiration_date asc, id desc";

		try {
			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);

			// try-with-resourcesの構文
			// データベースへ接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// SQLを実行
				try (PreparedStatement stmt = conn.prepareStatement(sql);ResultSet rs = stmt.executeQuery()) {
					// SQLの実行結果をArrayListに格納
					while (rs.next()) {
						TodoItemModel model = new TodoItemModel();
						model.setId(rs.getInt("id"));
						model.setExpirationDate(Date.valueOf(rs.getString("expiration_date")));
						model.setTodoItem(rs.getString("todo_item"));
						model.setIsCompleted(rs.getInt("is_completed"));
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setCreateDateTime(Timestamp.valueOf(rs.getString("create_date_time")));
						model.setUpdateDateTime(Timestamp.valueOf(rs.getString("update_date_time")));

						list.add(model);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	/**
	 * TODOリストを追加します
	 * @param model
	 * @return
	 */
	public boolean create(TodoItemModel model) {
		Connection conn = null;

		try {
			// データベースに接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// インサート文の準備（idは自動連番なので指定しなくて良い）
			String sql = "insert into todo_items (expiration_date, todo_item) values (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// パラメータを設定
			stmt.setDate(1, model.getExpirationDate());
			stmt.setString(2, model.getTodoItem());

			// インサート文を実行
			int result = stmt.executeUpdate();

			// 結果行数が1でなかったら、falseを返却
			if (result != 1) {
				return false;
			}

			// PreparedStatementを明示的に閉じる
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * TODOリストを更新します
	 * @param model
	 * @return
	 */
	public boolean update(TodoItemModel model) {
		Connection conn = null;

		try {
			// データベースに接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// インサート文の準備（idは自動連番なので指定しなくて良い）
			String sql = "update todo_items set "
					+ "is_completed=?,"
					+ "is_deleted=? "
					+ "where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// パラメータを設定
			stmt.setInt(1, model.getIsCompleted());
			stmt.setInt(2, model.getIsDeleted());
			stmt.setInt(3, model.getId());

			// アップデート文を実行
			int result = stmt.executeUpdate();

			// 結果行数が1でなかったら、falseを返却
			if (result != 1) {
				return false;
			}

			// PreparedStatementを明示的に閉じる
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
