package ensyu01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBEnsyu2 {

	public static void main(String[] args) {

		//コンソールに「接続開始」を表示
		System.out.println("接続開始");

		try {
			//JDBCドライバを設定
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			//データベース名、ユーザ名、パスワード
			String connUrl = "jdbc:sqlserver://STRA-CL0061\\SQLEXPRESS2012;database=DBENSYU;" +
					"integratedSecurity=false;user=sa;password=Step2154822";

			//接続開始
			Connection objCon = DriverManager.getConnection(connUrl);

			//コンソールに「接続成功」を表示
			System.out.println("接続成功");

			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			String sql = "select * from 商品マスタ";

			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			while (rs.next()) {
				System.out.println(rs.getString("商品ID"));
				System.out.println(rs.getString("商品名"));
			}

			rs.close();
			stmt.close();

		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
	}
}
