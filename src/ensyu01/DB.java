package ensyu01;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

	//DBにつなぐ
		public void connectDB() {
			try {
				//JDBCドライバを設定
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

				String connUrl = "jdbc:sqlserver://STRA-CL0061\\SQLEXPRESS2012;database=DBENSYU;" +
						"integratedSecurity=false;user=sa;password=Step2154822";

				//接続開始
				Connection objCon = DriverManager.getConnection(connUrl);

				//コンソールに「接続成功」を表示
				System.out.println("接続成功");
			} catch (Exception objEx) {
				//コンソールに「接続エラー内容」を表示
				System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
			}
		}



}
