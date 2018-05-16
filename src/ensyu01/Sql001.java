package ensyu01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Sql001 {

	public static void main(String[] args) {
		try {
			//JDBCドライバを設定
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String connUrl = "jdbc:sqlserver://STRA-CL0061\\SQLEXPRESS2012;database=DBENSYU;"+
							"integratedSecurity=false;user=sa;password=Step2154822";

			//接続開始
			Connection objCon = DriverManager.getConnection(connUrl);

			//SQL作る
			String sql = "select * from 商品マスタ";

			//ステートメント
			PreparedStatement ps = objCon.prepareStatement(sql);

			//リザルトセット
			ResultSet rs = ps.executeQuery();

			//抽出データが無くなるまで
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String tanka = rs.getString(3);
				System.out.println(id +" "+ name +" "+ tanka);
			}

			ps.close();
			rs.close();
			objCon.close();

		}catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() +":"+ objEx.getMessage());
		}

	}

}
