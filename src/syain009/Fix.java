package syain009;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Fix extends JFrame {

	private JPanel contentPane;

	/***********修正項目入力用テキストフィールド******************/
	private JTextField nameField;
	private JTextField kanaField;
	private JTextField cityField;

	/*******結果画面で選択されたJテーブルの行のデータを受け取るための配列*********/
	/******（修正対象社員の情報を表示するための準備）*****************************/
	public String[] fixes = new String[8];

	/*************対象社員データ表示用テキストフィールド**********/
	private JTextField fixidField;
	private JTextField fixnameField;
	private JTextField fixdeptField;

	/*******発行したSQLが正しかったかどうかを格納する（例外起きてたら2）*******/
	int num = 0;

	private JTextField passField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fix frame = new Fix();
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
	public Fix() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("修正画面");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 32));
		label.setBounds(249, 12, 142, 41);
		contentPane.add(label);

		//戻るボタン
		JButton backButton = new JButton("戻る");
		backButton.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result result = new Result();
				result.setVisible(true);

				Component c = (Component) e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
			}
		});
		backButton.setBounds(487, 14, 122, 40);
		contentPane.add(backButton);

		JLabel lblNewLabel = new JLabel("対象社員：");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblNewLabel.setBounds(23, 74, 118, 31);
		contentPane.add(lblNewLabel);

		//修正対象となる社員名を表示する
		JLabel fixNameLabel = new JLabel("氏名");
		fixNameLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		fixNameLabel.setBounds(303, 76, 70, 31);
		contentPane.add(fixNameLabel);

		JLabel label_1 = new JLabel("修正項目 ： ");
		label_1.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_1.setBounds(26, 132, 142, 31);
		contentPane.add(label_1);

		JLabel lblid = new JLabel("氏名");
		lblid.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblid.setBounds(145, 139, 82, 21);
		contentPane.add(lblid);

		JLabel label_2 = new JLabel("フリガナ");
		label_2.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_2.setBounds(369, 139, 82, 21);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("都道府県");
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_3.setBounds(106, 181, 120, 31);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("市町村");
		label_4.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_4.setBounds(369, 186, 82, 21);
		contentPane.add(label_4);

		JLabel lblid_1 = new JLabel("部門ID");
		lblid_1.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblid_1.setBounds(366, 240, 82, 21);
		contentPane.add(lblid_1);

		//都道府県コンボボックス
		JComboBox kenComboBox = new JComboBox();
		kenComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "北海道 ", "青森県 ", "岩手県 ", "宮城県 ", "秋田県 ",
				"山形県 ", "福島県 ", "茨城県 ", "栃木県 ", "群馬県 ", "埼玉県 ", "千葉県 ", "東京都 ", "神奈川県 ", "新潟県 ", "富山県 ", "石川県 ", "福井県 ",
				"山梨県 ", "長野県 ", "岐阜県 ", "静岡県 ", "愛知県 ", "三重県 ", "滋賀県 ", "京都府 ", "大阪府 ", "兵庫県 ", "奈良県 ", "和歌山県 ", "鳥取県 ",
				"島根県 ", "岡山県 ", "広島県 ", "山口県 ", "徳島県 ", "香川県 ", "愛媛県 ", "高知県 ", "福岡県 ", "佐賀県 ", "長崎県  ", "熊本県 ", "大分県 ",
				"宮崎県 ", "鹿児島県 ", "沖縄県 " }));
		kenComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kenComboBox.setBounds(214, 177, 143, 41);
		contentPane.add(kenComboBox);

		//部門IDコンボボックス
		JComboBox deptComboBox = new JComboBox();
		deptComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deptComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "未選択", "0001", "0002", "0003", "0004", "0005", "0006" }));
		deptComboBox.setBounds(452, 233, 143, 40);
		contentPane.add(deptComboBox);

		//パスワード入力用テキストフィールド
		passField = new JTextField();
		passField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		passField.setColumns(10);
		passField.setBounds(211, 342, 146, 48);
		contentPane.add(passField);

		//性別コンボボックス
		JComboBox seiComboBox = new JComboBox();
		seiComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "男", "女" }));
		seiComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		seiComboBox.setBounds(213, 232, 146, 42);
		contentPane.add(seiComboBox);

		//年コンボボックス
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "1948", "1949", "1950", "1951", "1952",
				"1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965",
				"1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978",
				"1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991",
				"1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004",
				"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
				"2018" }));
		yearComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		yearComboBox.setBounds(213, 285, 144, 48);
		contentPane.add(yearComboBox);

		//月コンボボックス
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "未選択", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "12" }));
		monthComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		monthComboBox.setBounds(390, 286, 97, 45);
		contentPane.add(monthComboBox);

		//日コンボボックス
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "26", "27", "28", "29", "30", "31" }));
		dayComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		dayComboBox.setBounds(523, 287, 99, 45);
		contentPane.add(dayComboBox);

		//権限コンボボックス
		JComboBox kengenComboBox = new JComboBox();
		kengenComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "一般", "管理者" }));
		kengenComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kengenComboBox.setBounds(454, 347, 146, 45);
		contentPane.add(kengenComboBox);

		/**************ここから修正ボタンイベント（入力項目を修正して結果を反映して検索結果画面に戻る）*****************/
		JButton fixButton = new JButton("修正");
		fixButton.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
		fixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = nameField.getText();
				String kana = kanaField.getText();
				String ken = (String) kenComboBox.getSelectedItem();
				String city = cityField.getText();
				String dept = (String) deptComboBox.getSelectedItem();
				String pass = passField.getText();
				String sei = (String) seiComboBox.getSelectedItem();//性別コンボボックスの値
				String kengen = (String) kengenComboBox.getSelectedItem();

				//都道府県コンボボックス未選択時処理
				if (ken.equals("未選択")) {
					ken = "";
				}

				//部門IDコンボボックス未選択時処理
				if (dept.equals("未選択")) {
					dept = "";
				}

				//性別コンボボックス未選択時処理
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

				//権限コンボボックス未選択時処理
				switch (kengen) {
				case "未選択":
					kengen = "";
					break;
				case "一般":
					kengen = "0";
					break;
				case "管理者":
					kengen = "1";
					break;
				default:
					break;
				}

				/*************ここから年月日コンボボックスの値を格納する処理********************/
				String birth = "";
				String year = yearComboBox.getSelectedItem().toString();
				String month = monthComboBox.getSelectedItem().toString();
				String day = dayComboBox.getSelectedItem().toString();
				//日付の入力チェック結果を格納しておく
				boolean x = true;

				switch (year) {
				case "未選択":
					year = "";
					break;
				default:
					break;
				}

				switch (month) {
				case "未選択":
					month = "";
					break;
				default:
					break;
				}

				switch (day) {
				case "未選択":
					day = "";
					break;
				default:
					break;
				}

				if (year.equals("") || month.equals("") || day.equals("")) {
					x = false;
				} else {
					birth = year + "-" + month + "-" + day;
					x = true;
				}

				/************ここから年月日コンボボックスの値を格納する処理***********/

				//修正対象社員の社員IDを取得する（アップデートの条件にするため）
				String id = fixidField.getText();

				//未入力チェック用（あとで1つ入力されていなかったらfalseにする）
				boolean check = true;

				/**********************ここから修正項目入力チェック********************************/
				if (name.equals("") && kana.equals("") && ken.equals("") && city.equals("")
						&& dept.equals("") && pass.equals("") && sei.equals("") && birth.equals("")
						&& kengen.equals("")) {
					JOptionPane.showMessageDialog(fixButton, "修正項目に不備があります");
					check = false;
				}
				/**********************ここまで修正項目入力チェック********************************/

				int option = 0;
				//不備がなければDBクラス側にSQLに使う引数を渡す
				if (check == true || x == true) {
					/*********************ここから確認メッセージ処理****************************/

					option = JOptionPane.showConfirmDialog(fixButton, "この内容で修正しますか？", "最終確認", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);

					if (option == JOptionPane.YES_OPTION) {
						DBCls1 db = new DBCls1();

						/**********ここからSQLが正常に処理されるかどうかで分岐する*****************/
						num = db.fix(name, kana, ken, city, dept, pass, sei, birth, kengen, id);
						if (num == 0) {
							JOptionPane.showMessageDialog(fixButton, "このデータは削除されているため、\n修正することができません");
						} else if (num == 2) {
							JOptionPane.showMessageDialog(fixButton, "入力値が正しくありません");
						} else {
							Result result = new Result();
							result.setVisible(true);

							Component c = (Component) e.getSource();
							Window w = SwingUtilities.getWindowAncestor(c);
							w.dispose();
						}
						/**********ここまでSQLが正常に処理されるかどうかで分岐する*****************/
					} else if (option == JOptionPane.NO_OPTION) {

					}
					/*********************ここまで確認メッセージ表示****************************/
				}
				/**************ここまで修正ボタンイベント（入力項目を修正して結果を反映して検索結果画面に戻る）*****************/
			}
		});
		fixButton.setBounds(184, 419, 138, 40);
		contentPane.add(fixButton);

		//データ復元ボタン
		JButton recoveryButton = new JButton("データ復元");
		recoveryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				option = JOptionPane.showConfirmDialog(recoveryButton, "このデータを復元しますか？", "最終確認", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (option == JOptionPane.YES_OPTION) {

					DBCls1 db = new DBCls1();
					int num = db.recovery(fixidField.getText());

					if (num == 0) {
						JOptionPane.showMessageDialog(fixButton, "このデータは削除されていません");
						Result result = new Result();
						result.setVisible(true);

						Component c = (Component) e.getSource();
						Window w = SwingUtilities.getWindowAncestor(c);
						w.dispose();
					} else {
						Result result = new Result();
						result.setVisible(true);

						/*********************画面閉じる*****************************/
						Component c = (Component) e.getSource();
						Window w = SwingUtilities.getWindowAncestor(c);
						w.dispose();
						/*********************画面閉じる*****************************/
					}
				}
			}
		});
		recoveryButton.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
		recoveryButton.setBounds(345, 419, 161, 40);
		contentPane.add(recoveryButton);

		/******************ここから修正情報入力用テキストフィールド群*******************************/
		nameField = new JTextField();
		nameField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		nameField.setBounds(212, 130, 145, 37);
		contentPane.add(nameField);
		nameField.setColumns(10);

		kanaField = new JTextField();
		kanaField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kanaField.setColumns(10);
		kanaField.setBounds(449, 130, 145, 37);
		contentPane.add(kanaField);

		cityField = new JTextField();
		cityField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		cityField.setColumns(10);
		cityField.setBounds(450, 181, 144, 41);
		contentPane.add(cityField);
		/******************ここまで修正情報入力用テキストフィールド群*******************************/

		JLabel lblNewLabel_1 = new JLabel("社員ID");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblNewLabel_1.setBounds(132, 76, 102, 27);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("部門ID");
		lblNewLabel_2.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblNewLabel_2.setBounds(489, 78, 82, 31);
		contentPane.add(lblNewLabel_2);

		/******************ここから１番上に出す修正対象である社員の見出しテキストフィールド**********************/
		fixidField = new JTextField();
		fixidField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		fixidField.setForeground(Color.BLACK);
		fixidField.setEnabled(false);
		fixidField.setColumns(10);
		fixidField.setBounds(209, 79, 82, 26);
		contentPane.add(fixidField);

		fixnameField = new JTextField();
		fixnameField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		fixnameField.setEnabled(false);
		fixnameField.setColumns(10);
		fixnameField.setBounds(357, 79, 126, 28);
		contentPane.add(fixnameField);

		fixdeptField = new JTextField();
		fixdeptField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		fixdeptField.setEnabled(false);
		fixdeptField.setColumns(10);
		fixdeptField.setBounds(565, 79, 82, 31);
		contentPane.add(fixdeptField);

		JLabel label_6 = new JLabel("性別");
		label_6.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_6.setBounds(138, 234, 73, 40);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("パスワード");
		label_7.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_7.setBounds(96, 348, 107, 37);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("生年月日");
		label_8.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_8.setBounds(99, 285, 109, 40);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("年");
		label_9.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_9.setBounds(360, 293, 32, 39);
		contentPane.add(label_9);

		JLabel label_10 = new JLabel("月");
		label_10.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_10.setBounds(493, 292, 32, 39);
		contentPane.add(label_10);

		JLabel label_11 = new JLabel("日");
		label_11.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_11.setBounds(629, 292, 32, 39);
		contentPane.add(label_11);

		JLabel label_12 = new JLabel("権限");
		label_12.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_12.setBounds(377, 347, 73, 40);
		contentPane.add(label_12);
		/******************ここまでラベル群*********************************/

	}

	//fixesのセッター
	public void setFixes(String[] ss) {
		for (int i = 0; i < ss.length; i++) {
			fixes[i] = ss[i];
		}
		fixidField.setText(fixes[0]);
		fixnameField.setText(fixes[1]);
		fixdeptField.setText(fixes[7]);
	}
}
