package ensyu01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Sql002 {

	public static void main(String[] args) {
		try {
			//JDBCドライバを設定
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String connUrl = "jdbc:sqlserver://STRA-CL0061\\SQLEXPRESS2012;database=DBENSYU;"+
							"integratedSecurity=false;user=sa;password=Step2154822";

			//接続開始
			Connection objCon = DriverManager.getConnection(connUrl);

			//SQL作る
			String sql = "insert into 商品マスタ values('017','あいうえお',1000)";

			//リザルトセット
			Statement stms = objCon.createStatement();

			int rs = stms.executeUpdate(sql);

			objCon.close();
			stms.close();

		}catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() +":"+ objEx.getMessage());
		}

	}

}
