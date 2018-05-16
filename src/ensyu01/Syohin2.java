package ensyu01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Syohin2 {

	String id;

	String result=null;

	public String setId(String s) {
		id = s;

		try {
			//JDBCドライバを設定
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			//データベース名、ユーザ名、パスワード
			String connUrl = "jdbc:sqlserver://STRA-CL0061\\SQLEXPRESS2012;database=DBENSYU;" +
					"integratedSecurity=false;user=sa;password=Step2154822";

			//接続開始
			Connection objCon = DriverManager.getConnection(connUrl);

			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			String sql = "select * from 商品マスタ where 商品ID = '" + id +"'";

			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			result = "";
			while (rs.next()) {
				result = rs.getString("商品名");
				break;
			}
			rs.close();
			stmt.close();

		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return result;
	}
}
