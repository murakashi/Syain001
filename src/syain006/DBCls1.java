package syain006;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class DBCls1 {

	String sql;

	String data;

	Connection objCon;

	public DBCls1() {
		try {
			//JDBCドライバを設定
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			//データベース名、ユーザ名、パスワード
			String connUrl = "jdbc:sqlserver://STRA-CL0061\\SQLEXPRESS2012;database=DBENSYU;" +
					"integratedSecurity=false;user=sa;password=Step2154822";

			//接続開始
			objCon = DriverManager.getConnection(connUrl);//
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
			System.out.println(sql);
		}
	}

	/*******************ログインメソッド***************************************************/
	public String login(String s) {

		try {
			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			sql = s;

			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			data = "";
			while (rs.next()) {
				data = rs.getString("権限");
				break;
			}
			rs.close();
			stmt.close();
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return data;
	}

	/*****************************インサートメソッド**************************************/
	public boolean insert(String s1, String s2, String s3, String s4, String s5, String s6,
			String s7, String s8,String s9, String s10) {
		String sql = "";
		try {
			//リザルトセット
			Statement stms = objCon.createStatement();

			if(s3.equals("") && s8.equals("")) {
				sql = "insert into 社員マスタ values('"+s1+"','"+s2+"','','"+s4+"','"+s5+"','"+s6+"','"+s7+"','','"+s9+"',"+s10+",0)";
			}else if(s3.equals("") && !(s8.equals(""))) {
				sql = "insert into 社員マスタ values('"+s1+"','"+s2+"','','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"',"+s10+",0)";
			}else if(!(s3.equals("")) && s8.equals("")) {
				sql = "insert into 社員マスタ values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','','"+s9+"',"+s10+",0)";
			}else {
				sql = "insert into 社員マスタ values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"',"+s10+",0)";
			}

			int rs = stms.executeUpdate(sql);

			objCon.close();
			stms.close();

		}catch (SQLServerException e) {
			return false;
		}
		catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return true;
	}

	/**********************現社員の一覧セレクトメソッド***********************************************/
	public ArrayList<String[]> getSyainData() {

		//selectした結果を格納する用
		ArrayList<String[]> list = new ArrayList<String[]>();

		try {
			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			sql = "select * from 社員マスタ where 削除フラグ = 0";

			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			while (rs.next()) {
				String[] recdata = new String[8];
				recdata[0] = rs.getString("社員ID");
				recdata[1] = rs.getString("氏名");
				recdata[2] = rs.getString("フリガナ");
				recdata[3] = rs.getString("性別");
				recdata[4] = rs.getString("生年月日");
				recdata[5] = rs.getString("都道府県");
				recdata[6] = rs.getString("市町村");
				recdata[7] = rs.getString("部門ID");
				list.add(recdata);
			}
			rs.close();
			stmt.close();
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return list;
	}

	/*************************過去社員セレクトメソッド*********************************/
	public ArrayList<String[]> getPastSyainData() {

		//selectした結果を格納する用
		ArrayList<String[]> list = new ArrayList<String[]>();

		try {
			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			sql = "select * from 社員マスタ where 削除フラグ = 1";

			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			while (rs.next()) {
				String[] recdata = new String[8];
				recdata[0] = rs.getString("社員ID");
				recdata[1] = rs.getString("氏名");
				recdata[2] = rs.getString("フリガナ");
				recdata[3] = rs.getString("性別");
				recdata[4] = rs.getString("生年月日");
				recdata[5] = rs.getString("都道府県");
				recdata[6] = rs.getString("市町村");
				recdata[7] = rs.getString("部門ID");
				list.add(recdata);
			}
			rs.close();
			stmt.close();
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return list;
	}

	/***********************完全一致検索セレクトメソッド*****************************/
	public ArrayList<String[]> getSyainData2(String w1, String w2, String w3) {

		String sql = "select * from 社員マスタ where 1 = 1 and 削除フラグ = 0 ";
		String zyoken = "";
		boolean b1 = w1.equals("");
		boolean b2 = w2.equals("");
		boolean b3 = w3.equals("");

		//条件全部入ってる
		if (b1 == false && b2 == false && b3 == false) {
			zyoken = "and (氏名 like '%"+w1+"%' or フリガナ like'%"+w1+"%') and 部門ID = '" + w2 + "' and 性別 = '" + w3 + "'";
		}

		//氏名（シメイ）だけ入ってる
		if (b1 == false && b2 == true && b3 == true) {
			zyoken = "and (氏名 like '%"+w1+"%' or フリガナ like'%"+w1+"%')";
		}

		//部門IDだけ入ってる
		if (b1 == true && b2 == false && b3 == true) {
			zyoken = "and 部門ID = '" + w2+"'";
		}

		//性別だけ入ってる
		if (b1 == true && b2 == true && b3 == false) {
			zyoken = "and 性別 = '" + w3+"'";
		}

		//氏名（シメイ）と部門IDだけ入っている
		if (b1 == false && b2 == false && b3 == true) {
			zyoken = "and (氏名 like '%"+w1+"%' or フリガナ like'%"+w1+"%') and 部門ID = '" + w2 + "'";
		}

		//氏名（シメイ）と性別だけ入っている
		if (b1 == false && b2 == true && b3 == false) {
			zyoken = "and (氏名 like '%"+w1+"%' or フリガナ like'%"+w1+"%') and 性別 = '" + w3 + "'";
		}

		//部門と性別だけ入っている
		if (b1 == true && b2 == false && b3 == false) {
			zyoken = "and 部門ID = '" + w2 + "' and 性別 = '" + w3 + "'";
		}

		//何も入っていない
		if (b1 == true && b2 == true && b3 == true) {
			zyoken = "";
		}

		//System.out.println(sql + zyoken);

		//selectした結果を格納する用
		ArrayList<String[]> list = new ArrayList<String[]>();

		try {
			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			sql = sql + zyoken;

			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			while (rs.next()) {
				String[] recdata = new String[8];
				recdata[0] = rs.getString("社員ID");
				recdata[1] = rs.getString("氏名");
				recdata[2] = rs.getString("フリガナ");
				recdata[3] = rs.getString("性別");
				recdata[4] = rs.getString("生年月日");
				recdata[5] = rs.getString("都道府県");
				recdata[6] = rs.getString("市町村");
				recdata[7] = rs.getString("部門ID");
				list.add(recdata);
			}
			rs.close();
			stmt.close();
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return list;
	}

	/***********************部分一致検索セレクトメソッド************************************/
	public ArrayList<String[]> getSyainData3(String w1, String w2, String w3) {

		String sql = "select * from 社員マスタ where 削除フラグ = 0";
		String zyoken = "";
		boolean b1 = w1.equals("");
		boolean b2 = w2.equals("");
		boolean b3 = w3.equals("");

		//条件全部入ってる
		if (b1 == false && b2 == false && b3 == false) {
			zyoken = " and (氏名 like '%"+w1+"%' or フリガナ like'%" + w1 + "%' or 部門ID = '" + w2 + "' or 性別 = '" + w3 + "')";
		}

		//シメイだけ入ってる
		if (b1 == false && b2 == true && b3 == true) {
			zyoken = " and (氏名 like '%"+w1+"%' or フリガナ like'%" + w1 + "%')";
		}

		//部門IDだけ入ってる
		if (b1 == true && b2 == false && b3 == true) {
			zyoken = " and (部門ID = '" + w2 + "')";
		}

		//性別だけ入ってる
		if (b1 == true && b2 == true && b3 == false) {
			zyoken = " and (性別 = '" + w3 + "')";
		}

		//シメイと部門IDだけ入っている
		if (b1 == false && b2 == false && b3 == true) {
			zyoken = " and (氏名 like '%"+w1+"%' or フリガナ like'%" + w1 + "%' or 部門ID = '" + w2 + "')";
		}

		//シメイと性別だけ入っている
		if (b1 == false && b2 == true && b3 == false) {
			zyoken = " and (氏名 like '%"+w1+"%' or フリガナ like'%" + w1 + "%' or 性別 = '" + w3 + "')";
		}

		//部門と性別だけ入っている
		if (b1 == true && b2 == false && b3 == false) {
			zyoken = " and (部門ID = '" + w2 + "' or 性別 = '" + w3 + "')";
		}

		//何も入っていない
		if (b1 == true && b2 == true && b3 == true) {
			zyoken = "";
		}

		//selectした結果を格納する用
		ArrayList<String[]> list = new ArrayList<String[]>();

		try {
			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			sql = sql + zyoken;

			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			while (rs.next()) {
				String[] recdata = new String[8];
				recdata[0] = rs.getString("社員ID");
				recdata[1] = rs.getString("氏名");
				recdata[2] = rs.getString("フリガナ");
				recdata[3] = rs.getString("性別");
				recdata[4] = rs.getString("生年月日");
				recdata[5] = rs.getString("都道府県");
				recdata[6] = rs.getString("市町村");
				recdata[7] = rs.getString("部門ID");
				list.add(recdata);
			}
			rs.close();
			stmt.close();
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return list;
	}

	/**********************デリートメソッド************************************************/
	public int delete(String w) {
		int rs = 0;
		try {
			//リザルトセット
			Statement stms = objCon.createStatement();

			String sql = "update 社員マスタ set 削除フラグ = 1 where 削除フラグ = 0 and 社員ID = '" + w + "'";

			rs = stms.executeUpdate(sql);

			objCon.close();
			stms.close();
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return rs;
	}

	/**********************修正アップデートメソッド************************************************************/
	/***引数は氏名、フリガナ、都道府県、市町村、部門ID、パスワード、性別、生年月日、権限、修正対象社員IDの順***/
	public int fix(String w1, String w2, String w3, String w4, String w5,String w6,String w7,String w8,String w9,String w10) {

		String zyoken = "";

		String koshin = "";

		String value = "";

		boolean b1 = w1.equals("");
		boolean b2 = w2.equals("");
		boolean b3 = w3.equals("");
		boolean b4 = w4.equals("");
		boolean b5 = w5.equals("");
		boolean b6 = w6.equals("");
		boolean b7 = w7.equals("");
		boolean b8 = w8.equals("");
		boolean b9 = w9.equals("");

		int rs = 0;

		//氏名に値が入っていたら
		if (b1 == false) {
			value = value + ",氏名 = '" + w1 + "'";
		}

		if (b2 == false) {
			value = value + ",フリガナ = '" + w2 + "'";
		}

		if (b3 == false) {
			value = value + ",都道府県 = '" + w3 + "'";
		}

		if (b4 == false) {
			value = value + ",市町村 = '" + w4 + "'";
		}

		if (b5 == false) {
			value = value + ",部門ID = '" + w5 + "'";
		}

		if (b6 == false) {
			value = value + ",パスワード = '" + w6 + "'";
		}

		if (b7 == false) {
			value = value + ",性別 = '" + w7 + "'";
		}

		if (b8 == false) {
			value = value + ",生年月日 = '" + w8 + "'";
		}

		if (b9 == false) {
			value = value + ",権限 = '" + w9 + "'";
		}

		try {
			//リザルトセット
			Statement stms = objCon.createStatement();

			koshin = "update 社員マスタ set 社員ID = '" + w10 + "'" + value;
			zyoken = " where 削除フラグ = 0 and 社員ID = '" + w10 + "'";

			String sql = koshin + zyoken;

			rs = stms.executeUpdate(sql);

			objCon.close();
			stms.close();
		} catch (SQLServerException e) {
			return 2;
		}catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return rs;
	}

	/**********************復元アップデートメソッド************************************************************/
	/***引数は修正対象社員ID***/
	public int recovery(String id) {

		int rs = 0;

		try {
			//リザルトセット
			Statement stms = objCon.createStatement();

			String sql = "update 社員マスタ set 削除フラグ = 0 where 削除フラグ = 1 and 社員ID = " + id;

			rs = stms.executeUpdate(sql);

			objCon.close();
			stms.close();
		} catch (SQLServerException e) {
			return 0;
		}catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return rs;
	}

	/*******************社員IDの最大を返すセレクトメソッド************************************/
	public String selectMax() {

		//selectした結果を格納する用
		String maxId = "";

		try {
			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			String sql = "select max(社員ID) as 最大社員ID from 社員マスタ";

			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			while (rs.next()) {
				maxId = rs.getString("最大社員ID");
				break;
			}
			rs.close();
			stmt.close();
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return maxId;
	}

	/**************管理者ユーザーの人数を返す*****************************************/
	public int getManagers() {

		int mCount = 0;

		//selectした結果を格納する用
		ArrayList<String[]> list = new ArrayList<String[]>();

		try {
			//Statement生成
			Statement stmt = objCon.createStatement();

			//クエリ取得
			sql = "select * from 社員マスタ where 削除フラグ = 0 and 権限 = 1";
			//問い合わせの実行
			ResultSet rs = stmt.executeQuery(sql);

			//結果の取得
			while (rs.next()) {
				String[] recdata = new String[8];
				recdata[0] = rs.getString("社員ID");
				recdata[1] = rs.getString("氏名");
				recdata[2] = rs.getString("フリガナ");
				recdata[3] = rs.getString("性別");
				recdata[4] = rs.getString("生年月日");
				recdata[5] = rs.getString("都道府県");
				recdata[6] = rs.getString("市町村");
				recdata[7] = rs.getString("部門ID");
				list.add(recdata);

				mCount = mCount + 1;
			}
			rs.close();
			stmt.close();
		} catch (Exception objEx) {
			//コンソールに「接続エラー内容」を表示
			System.err.println(objEx.getClass().getName() + ":" + objEx.getMessage());
		}
		return mCount;
	}

}
