package syain009;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class Result extends JFrame {

	private JPanel contentPane;

	/**めちゃ使う****/
	public JTable table;
	public OriginalTableModel model;
	private JTextField nameField;

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
		setBounds(100, 100, 940, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel("検索結果");
		label_1.setFont(new Font("ＭＳ Ｐゴシック", Font.BOLD, 23));
		label_1.setBounds(402, 200, 96, 44);
		contentPane.add(label_1);

		JButton fixButton = new JButton("修正・復元");
		fixButton.setFont(new Font("ＭＳ Ｐゴシック", Font.BOLD, 23));
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

					Component c = (Component) e.getSource();
					Window w = SwingUtilities.getWindowAncestor(c);
					w.dispose();

				} catch (ArrayIndexOutOfBoundsException arrE) {
					JOptionPane.showMessageDialog(fixButton, "修正したいデータを選択してください");
					//selectErrorLabel.setText("修正したいデータを選択してください");
				}
				/**************************ここまで修正画面に選択した行のデータを渡す処理************************/
			}
		});
		fixButton.setBounds(292, 478, 166, 36);
		contentPane.add(fixButton);

		JButton deleteButton = new JButton("削除");
		deleteButton.setFont(new Font("ＭＳ Ｐゴシック", Font.BOLD, 23));
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

					Component c = (Component) e.getSource();
					Window w = SwingUtilities.getWindowAncestor(c);
					w.dispose();

				} catch (ArrayIndexOutOfBoundsException arr) {
					JOptionPane.showMessageDialog(deleteButton, "削除したいデータを選択してください");
					//selectErrorLabel.setText("削除したいデータを選択してください");
				}

				/**************************ここまで削除画面に選択した行のデータを渡す処理************************/
			}
		});
		deleteButton.setBounds(473, 478, 152, 36);
		contentPane.add(deleteButton);

		//スクロールペイン
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(40, 248, 823, 210);
		contentPane.add(scrollPane);

		/*************************初回Jテーブル関係****************************************************************/
		model = new OriginalTableModel();

		//見出し設定
		model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

		DBCls1 db = new DBCls1();

		ArrayList<String[]> list = db.getSyainData();

		for (int i = 0; i < list.size(); i++) {
			model.addRow((String[]) list.get(i));
		}

		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*****************ここからヘッダ（列）を移動できないようにする設定***********/
		JTableHeader jheader = table.getTableHeader();
		jheader.setReorderingAllowed(false);
		/*****************ここまでヘッダ（列）を移動できないようにする設定***********/
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

				Component c = (Component) e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
			}
		});
		backbutton.setBounds(522, 22, 125, 40);
		contentPane.add(backbutton);

		JButton logoutButton = new JButton("ログアウト");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = 0;

				option = JOptionPane.showConfirmDialog(logoutButton, "ログアウトしますか？", "ログアウト確認", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (option == JOptionPane.YES_OPTION) {
					Login logout = new Login();
					logout.setVisible(true);

					Component c = (Component)e.getSource();
					 Window w = SwingUtilities.getWindowAncestor(c);
					 w.dispose();
				}
			}

		});
		logoutButton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		logoutButton.setBounds(659, 22, 134, 40);
		contentPane.add(logoutButton);

		JLabel label_3 = new JLabel("氏名（シメイ）");
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_3.setBounds(116, 91, 134, 24);
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

		JLabel label_5 = new JLabel("性別");
		label_5.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_5.setBounds(612, 90, 58, 27);
		contentPane.add(label_5);

		//コンボボックスためし
		JComboBox seiComboBox = new JComboBox();
		seiComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		/*seiComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) seiComboBox.getSelectedItem();
			}
		});*/
		seiComboBox.setModel(new DefaultComboBoxModel(new String[] {"未選択", "男", "女"}));
		seiComboBox.setBounds(668, 86, 111, 35);
		contentPane.add(seiComboBox);


		//部門IDコンボボックス
		JComboBox deptComboBox = new JComboBox();
		deptComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deptComboBox.setModel(new DefaultComboBoxModel(new String[] {"未選択", "0001", "0002", "0003", "0004", "0005", "0006"}));
		deptComboBox.setBounds(455, 85, 145, 40);
		contentPane.add(deptComboBox);

		//完全一致ボタン
		JButton searchAbutton = new JButton("AND検索");
		searchAbutton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		searchAbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**************ここから完全一致検索のてきとうためし***************************************/
				//Jテーブル関係
				model = new OriginalTableModel();

				//見出し設定
				model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

				String name = nameField.getText();
				//String dept = deptField.getText();
				//String sei = seiField.getText();
				String sei = (String) seiComboBox.getSelectedItem();//コンボボックスの値
				String dept = (String)deptComboBox.getSelectedItem();

				switch (sei) {
				case "男":
					sei = "M";
					break;
				case "女":
					sei = "F";
					break;
				default:
					sei = "";
					break;
				}

				switch (dept) {
				case "未選択":
					dept = "";
					break;
				default:
					break;
				}

				if (name.equals("") && dept.equals("") && sei.equals("")) {
					JOptionPane.showMessageDialog(searchAbutton, "検索条件が入力されていません");
					//searchErrorLabel.setText("条件が入力されていません");
				} else {
					DBCls1 db = new DBCls1();

					ArrayList<String[]> list = db.getSyainData2(name, dept, sei);

					for (int i = 0; i < list.size(); i++) {
						model.addRow((String[]) list.get(i));
					}

					table = new JTable(model);
					scrollPane.setViewportView(table);
					//１行しか選択できないようにする
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					/*****************ここからヘッダ（列）を移動できないようにする設定***********/
					JTableHeader jheader = table.getTableHeader();
					jheader.setReorderingAllowed(false);
					/*****************ここまでヘッダ（列）を移動できないようにする設定***********/
					/*nameField.setText("");
					deptField.setText("");
					seiComboBox.setSelectedIndex(0);*/
					//searchErrorLabel.setText("");
				}

				/*********************完全一致検索ここまでためし*********************************/
			}
		});
		searchAbutton.setBounds(29, 147, 209, 38);
		contentPane.add(searchAbutton);

		//部分一致ボタン
		JButton searchBbutton = new JButton("OR検索");
		searchBbutton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		searchBbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**************ここから部分一致検索のてきとうためし***************************************/
				//Jテーブル関係
				model = new OriginalTableModel();

				//見出し設定
				model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

				String name = nameField.getText();
				String dept = (String)deptComboBox.getSelectedItem();
				String sei = (String) seiComboBox.getSelectedItem();//コンボボックスの値

				switch (sei) {
				case "男":
					sei = "M";
					break;
				case "女":
					sei = "F";
					break;
				default:
					sei = "";
					break;
				}

				switch (dept) {
				case "未選択":
					dept = "";
					break;
				default:
					break;
				}

				if (name.equals("") && dept.equals("") && sei.equals("")) {
					JOptionPane.showMessageDialog(searchBbutton, "検索条件が入力されていません");
					//searchErrorLabel.setText("条件が入力されていません");
				} else {
					DBCls1 db = new DBCls1();

					ArrayList<String[]> list = db.getSyainData3(name, dept, sei);

					for (int i = 0; i < list.size(); i++) {
						model.addRow((String[]) list.get(i));
					}

					table = new JTable(model);
					//１行しか選択できないようにする
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					/*****************ここからヘッダ（列）を移動できないようにする設定***********/
					JTableHeader jheader = table.getTableHeader();
					jheader.setReorderingAllowed(false);
					/*****************ここまでヘッダ（列）を移動できないようにする設定***********/
					scrollPane.setViewportView(table);

					/*nameField.setText("");
					deptField.setText("");
					seiComboBox.setSelectedIndex(0);*/
					//searchErrorLabel.setText("");
				}
				/*********************部分一致検索ここまでためし*********************************/
			}
		});
		searchBbutton.setBounds(254, 147, 202, 38);
		contentPane.add(searchBbutton);

		//一覧表示ボタン
		JButton AllSearchbutton = new JButton("全件検索");
		AllSearchbutton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		AllSearchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**************一覧検索のてきとうためし***************************************/
				//Jテーブル関係
				model = new OriginalTableModel();

				//見出し設定
				model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

				DBCls1 db = new DBCls1();

				ArrayList<String[]> list = db.getSyainData();

				for (int i = 0; i < list.size(); i++) {
					model.addRow((String[]) list.get(i));
				}

				//searchErrorLabel.setText("");

				table = new JTable(model);
				//１行しか選択できないようにする
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				/*****************ここからヘッダ（列）を移動できないようにする設定***********/
				JTableHeader jheader = table.getTableHeader();
				jheader.setReorderingAllowed(false);
				/*****************ここまでヘッダ（列）を移動できないようにする設定***********/
				scrollPane.setViewportView(table);

				/*********************一覧検索ここまでためし*********************************/
			}
		});
		AllSearchbutton.setBounds(467, 147, 179, 38);
		contentPane.add(AllSearchbutton);

		JButton kakobutton = new JButton("過去社員データ表示");
		kakobutton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kakobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*********************過去社員を表示する***************************************/
				//Jテーブル関係
				model = new OriginalTableModel();

				//見出し設定
				model.setColumnIdentifiers(new String[] { "社員ID", "氏名", "フリガナ", "性別", "生年月日", "都道府県", "市町村", "部門ID" });

				DBCls1 db = new DBCls1();

				ArrayList<String[]> list = db.getPastSyainData();

				for (int i = 0; i < list.size(); i++) {
					model.addRow((String[]) list.get(i));
				}

				//searchErrorLabel.setText("");

				table = new JTable(model);
				//１行しか選択できないようにする
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				/*****************ここからヘッダ（列）を移動できないようにする設定***********/
				JTableHeader jheader = table.getTableHeader();
				jheader.setReorderingAllowed(false);
				/*****************ここまでヘッダ（列）を移動できないようにする設定***********/
				scrollPane.setViewportView(table);
				/*******************ここまで過去社員を表示する***********************************/
			}
		});
		kakobutton.setBounds(658, 147, 248, 38);
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
