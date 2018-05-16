package ensyu01;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Result extends JFrame {

	private JPanel contentPane;

	/**めちゃ使う****/
	public JTable table;
	public DefaultTableModel model;
	private JTextField nameField;
	private JTextField deptField;
	private JTextField seiField;

	/***戻る、修正、削除ボタンの使用可否を判断する***/
	static boolean judgeF;
	static boolean judgeD;
	static boolean judgeB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result frame = new Result();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Result() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel("検索結果");
		label_1.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
		label_1.setBounds(411, 218, 96, 44);
		contentPane.add(label_1);

		JLabel selectErrorLabel = new JLabel("");
		selectErrorLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 14));
		selectErrorLabel.setBounds(310, 454, 350, 36);
		contentPane.add(selectErrorLabel);

		JButton fixButton = new JButton("修正");
		fixButton.setEnabled(judgeF);
		fixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**************************修正画面にJテーブルの選択した行のデータを渡す********************/
				String[] fixData = new String[8];

				try {
					for (int i = 0; i < fixData.length; i++) {
						fixData[i] = (String) table.getValueAt(table.getSelectedRow(), i);
					}
					Fix fix = new Fix();
					fix.setFixes(fixData);
					fix.setVisible(true);
				} catch (ArrayIndexOutOfBoundsException arrE) {
					selectErrorLabel.setText("テーブルのデータが選択されていません");
				}
				/**************************ここまで修正画面に選択した行のデータを渡す処理************************/
			}
		});
		fixButton.setBounds(357, 500, 91, 21);
		contentPane.add(fixButton);

		JButton deleteButton = new JButton("削除");
		deleteButton.setEnabled(judgeF);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**************************削除画面にJテーブルの選択した行のデータを渡す********************/
				String[] deleteData = new String[8];

				try {

					for (int i = 0; i < deleteData.length; i++) {
						deleteData[i] = (String) table.getValueAt(table.getSelectedRow(), i);
					}
					Delete del = new Delete();
					del.setDeletes(deleteData);
					del.setVisible(true);
				} catch (ArrayIndexOutOfBoundsException arr) {
					selectErrorLabel.setText("テーブルのデータが選択されていません");
				}

				/**************************ここまで削除画面に選択した行のデータを渡す処理************************/
			}
		});
		deleteButton.setBounds(468, 500, 91, 21);
		contentPane.add(deleteButton);

		//スクロールペイン
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 261, 823, 183);
		contentPane.add(scrollPane);

		/*************************初回Jテーブル関係****************************************************************/
		model = new DefaultTableModel();

		//見出し設定
		model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

		DBCls1 db = new DBCls1();

		ArrayList<String[]> list = db.getSyainData();

		for (int i = 0; i < list.size(); i++) {
			model.addRow((String[]) list.get(i));
		}

		table = new JTable(model);
		scrollPane.setViewportView(table);
		/**************************ここまで初回Jテーブル関係**********************************************************/

		JLabel label = new JLabel("検索画面");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 32));
		label.setBounds(368, 18, 159, 44);
		contentPane.add(label);

		JButton backbutton = new JButton("戻る");
		backbutton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		backbutton.setEnabled(judgeB);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuB mB = new MenuB();
				mB.setVisible(true);
			}
		});
		backbutton.setBounds(522, 22, 100, 40);
		contentPane.add(backbutton);

		JButton button_1 = new JButton("終了");
		button_1.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		button_1.setBounds(639, 22, 91, 40);
		contentPane.add(button_1);

		JLabel label_3 = new JLabel("シメイ");
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_3.setBounds(193, 91, 62, 24);
		contentPane.add(label_3);

		nameField = new JTextField();
		nameField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		nameField.setColumns(10);
		nameField.setBounds(255, 85, 111, 36);
		contentPane.add(nameField);

		JLabel label_4 = new JLabel("部門ID");
		label_4.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_4.setBounds(378, 88, 86, 30);
		contentPane.add(label_4);

		deptField = new JTextField();
		deptField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deptField.setColumns(10);
		deptField.setBounds(451, 87, 112, 35);
		contentPane.add(deptField);

		JLabel label_5 = new JLabel("性別");
		label_5.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_5.setBounds(568, 93, 58, 27);
		contentPane.add(label_5);

		//性別入力テキストフィールド
		seiField = new JTextField();
		seiField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		seiField.setColumns(10);
		seiField.setBounds(625, 89, 117, 36);
		contentPane.add(seiField);

		JLabel searchErrorLabel = new JLabel("");
		searchErrorLabel.setBounds(319, 112, 50, 13);
		contentPane.add(searchErrorLabel);

		//完全一致ボタン
		JButton searchAbutton = new JButton("完全一致検索");
		searchAbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**************ここから完全一致検索のてきとうためし***************************************/
				//Jテーブル関係
				model = new DefaultTableModel();

				//見出し設定
				model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

				String name = nameField.getText();
				String dept = deptField.getText();
				String sei = seiField.getText();

				DBCls1 db = new DBCls1();

				ArrayList<String[]> list = db.getSyainData2(name, dept, sei);

				for (int i = 0; i < list.size(); i++) {
					model.addRow((String[]) list.get(i));
				}

				table = new JTable(model);
				scrollPane.setViewportView(table);

				nameField.setText("");
				deptField.setText("");
				seiField.setText("");

				/*********************完全一致検索ここまでためし*********************************/
			}
		});
		searchAbutton.setBounds(184, 150, 134, 38);
		contentPane.add(searchAbutton);

		//部分一致ボタン
		JButton searchBbutton = new JButton("部分一致検索");
		searchBbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**************ここから部分一致検索のてきとうためし***************************************/
				//Jテーブル関係
				model = new DefaultTableModel();

				//見出し設定
				model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

				String name = nameField.getText();
				String dept = deptField.getText();
				String sei = seiField.getText();

				DBCls1 db = new DBCls1();

				ArrayList<String[]> list = db.getSyainData3(name, dept, sei);

				for (int i = 0; i < list.size(); i++) {
					model.addRow((String[]) list.get(i));
				}

				table = new JTable(model);
				scrollPane.setViewportView(table);

				nameField.setText("");
				deptField.setText("");
				seiField.setText("");

				/*********************部分一致検索ここまでためし*********************************/
			}
		});
		searchBbutton.setBounds(325, 150, 118, 38);
		contentPane.add(searchBbutton);

		//一覧表示ボタン
		JButton AllSearchbutton = new JButton("一覧表示");
		AllSearchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**************一覧検索のてきとうためし***************************************/
				//Jテーブル関係
				model = new DefaultTableModel();

				//見出し設定
				model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

				DBCls1 db = new DBCls1();

				ArrayList<String[]> list = db.getSyainData();

				for (int i = 0; i < list.size(); i++) {
					model.addRow((String[]) list.get(i));
				}

				table = new JTable(model);
				scrollPane.setViewportView(table);

				/*********************一覧検索ここまでためし*********************************/
			}
		});
		AllSearchbutton.setBounds(452, 150, 118, 38);
		contentPane.add(AllSearchbutton);

		JButton kakobutton = new JButton("過去社員データ表示");
		kakobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*********************過去社員を表示する***************************************/
				//Jテーブル関係
				model = new DefaultTableModel();

				//見出し設定
				model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

				DBCls1 db = new DBCls1();

				ArrayList<String[]> list = db.getPastSyainData();

				for (int i = 0; i < list.size(); i++) {
					model.addRow((String[]) list.get(i));
				}

				table = new JTable(model);
				scrollPane.setViewportView(table);
				/*******************ここまで過去社員を表示する***********************************/
			}
		});
		kakobutton.setBounds(582, 150, 140, 38);
		contentPane.add(kakobutton);
	}

	//戻るボタン使用可否セッター
	public static void setBackJudge(boolean b) {
		judgeB = b;
	}

	//修正ボタン使用可否セッター
	public static void setFixJudge(boolean b) {
		judgeF = b;
	}

	//削除ボタン使用可否セッター
	public static void setDelteJudge(boolean b) {
		judgeD = b;
	}
}
