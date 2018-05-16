package syain008;

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

public class Insert extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField kanaField;
	private JTextField cityField;
	private JTextField passField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Insert frame = new Insert();
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
	public Insert() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("追加画面");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 32));
		lblNewLabel.setBounds(212, 10, 162, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("社員ID");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		lblNewLabel_1.setBounds(40, 73, 85, 29);
		contentPane.add(lblNewLabel_1);

		/************ここから社員IDを最大値の＋１から始めさせたい処理*********/
		DBCls1 d = new DBCls1();

		String nextId = d.selectMax();//社員IDの最大値を取得

		/***********変換処理*******************/
		/*if (nextId.substring(1, 1).equals("0")) {
			nextId = nextId.substring(2);
		} else {
			nextId = nextId.substring(1);
		}
		 */
		//数値に変換して加算
		int num = Integer.parseInt(nextId);

		//数値に応じて左に0を詰めた文字列にする
		if (num >= 100) {
			nextId = num + "";
		} else if (num >= 10) {
			nextId = "0" + num;
		} else {
			nextId = "00" + num;
		}
		/************ここまで社員IDを最大値の＋１から始めさせたい処理*********/

		idField = new JTextField(nextId);
		idField.setEnabled(false);
		idField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		idField.setBounds(126, 70, 146, 40);
		contentPane.add(idField);
		idField.setColumns(10);

		JLabel label = new JLabel("氏名");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label.setBounds(46, 128, 67, 31);
		contentPane.add(label);

		nameField = new JTextField();
		nameField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		nameField.setColumns(10);
		nameField.setBounds(126, 120, 146, 48);
		contentPane.add(nameField);

		JLabel label_1 = new JLabel("フリガナ");
		label_1.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_1.setBounds(40, 187, 85, 29);
		contentPane.add(label_1);

		kanaField = new JTextField();
		kanaField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		kanaField.setColumns(10);
		kanaField.setBounds(126, 178, 146, 48);
		contentPane.add(kanaField);

		JLabel label_2 = new JLabel("性別");
		label_2.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_2.setBounds(51, 244, 73, 40);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("生年月日");
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_3.setBounds(12, 309, 109, 40);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("都道府県");
		label_4.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_4.setBounds(284, 75, 109, 31);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("市町村");
		label_5.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_5.setBounds(296, 132, 96, 29);
		contentPane.add(label_5);

		cityField = new JTextField();
		cityField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		cityField.setColumns(10);
		cityField.setBounds(400, 123, 146, 45);
		contentPane.add(cityField);

		JLabel lblid = new JLabel("部門ID");
		lblid.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		lblid.setBounds(298, 185, 86, 35);
		contentPane.add(lblid);

		JLabel label_7 = new JLabel("パスワード");
		label_7.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_7.setBounds(286, 246, 107, 37);
		contentPane.add(label_7);

		passField = new JTextField();
		passField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		passField.setColumns(10);
		passField.setBounds(401, 240, 146, 48);
		contentPane.add(passField);

		JLabel label_8 = new JLabel("権限");
		label_8.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_8.setBounds(38, 371, 73, 40);
		contentPane.add(label_8);

		//性別コンボボックス
		JComboBox seiComboBox = new JComboBox();
		seiComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "男", "女" }));
		seiComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		seiComboBox.setBounds(126, 242, 146, 48);
		contentPane.add(seiComboBox);

		//権限コンボボックス
		JComboBox kengenComboBox = new JComboBox();
		kengenComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kengenComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "一般", "管理者" }));
		kengenComboBox.setBounds(127, 371, 146, 45);
		contentPane.add(kengenComboBox);

		//都道府県コンボボックス
		JComboBox kenComboBox = new JComboBox();
		kenComboBox.setModel(new DefaultComboBoxModel(new String[] {"未選択", "北海道 ", "青森県 ", "岩手県 ", "宮城県 ", "秋田県 ", "山形県  ", "福島県 ", "茨城県 ", "栃木県 ", "群馬県 ", "埼玉県 ", "千葉県 ", "東京都 ", "神奈川県 ", "新潟県 ", "富山県 ", "石川県 ", "福井県 ", "山梨県 ", "長野県 ", "岐阜県 ", "静岡県 ", "愛知県 ", "三重県 ", "滋賀県 ", "京都府  ", "大阪府 ", "兵庫県 ", "奈良県 ", "和歌山県 ", "鳥取県 ", "島根県 ", "岡山県 ", "広島県 ", "山口県 ", "徳島県  ", "香川県 ", "愛媛県 ", "高知県 ", "福岡県 ", "佐賀県 ", "長崎県 ", "熊本県 ", "大分県 ", "宮崎県  ", "鹿児島県 ", "沖縄県"}));
		kenComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kenComboBox.setBounds(400, 67, 146, 48);
		contentPane.add(kenComboBox);

		//部署IDコンボボックス
		JComboBox deptComboBox = new JComboBox();
		deptComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "未選択", "0001", "0002", "0003", "0004", "0005", "0006" }));
		deptComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deptComboBox.setBounds(400, 178, 146, 48);
		contentPane.add(deptComboBox);

		//年コンボボックス
		JComboBox yearCcomboBox = new JComboBox();
		yearCcomboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "1948", "1949", "1950", "1951", "1952",
				"1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965",
				"1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978",
				"1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991",
				"1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004",
				"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
				"2018" }));
		yearCcomboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		yearCcomboBox.setBounds(126, 309, 120, 48);
		contentPane.add(yearCcomboBox);

		//月コンボボックス
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "未選択", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		monthComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		monthComboBox.setBounds(282, 310, 97, 45);
		contentPane.add(monthComboBox);

		//日コンボボックス
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "26", "27", "28", "29", "30", "31" }));
		dayComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		dayComboBox.setBounds(415, 311, 99, 45);
		contentPane.add(dayComboBox);

		//追加エラーメッセージ表示用ラベル
		JLabel insertErrorLabel = new JLabel("");
		insertErrorLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
		insertErrorLabel.setBounds(142, 437, 384, 56);
		contentPane.add(insertErrorLabel);

		//追加ボタン
		JButton insertButton = new JButton("追加");
		insertButton.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//入力項目取得
				String id = idField.getText();
				String name = nameField.getText();
				String kana = kanaField.getText();
				String sei = (String) seiComboBox.getSelectedItem();//性別コンボボックスの値
				/*************ここから年月日を格納する処理********************/
				//String birth = birthdayField.getText();
				String birth = "";
				String year = yearCcomboBox.getSelectedItem().toString();
				String month = monthComboBox.getSelectedItem().toString();
				String day = dayComboBox.getSelectedItem().toString();

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
					insertErrorLabel.setText("日付に不備があります。");
				} else {
					birth = year + "-" + month + "-" + day;
				}

				/************ここまで年月日を格納するする処理***********/
				String ken = (String) kenComboBox.getSelectedItem();
				String city = cityField.getText();
				String dept = (String) deptComboBox.getSelectedItem();
				String pass = passField.getText();
				String kengen = (String) kengenComboBox.getSelectedItem();//権限コンボボックスの値

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

				//都道府県コンボボックス未選択時処理
				switch (ken) {
				case "未選択":
					ken = "";
					break;
				default:
					break;
				}

				//部署IDコンボボックス未選択時処理
				switch (dept) {
				case "未選択":
					dept = "";
					break;
				default:
					break;
				}

				//権限コンボボックス未選択時処理
				switch (kengen) {
				case "一般":
					kengen = "0";
					break;
				case "管理者":
					kengen = "1";
					break;
				default:
					kengen = "";
					break;
				}

				//発行したSQLが成功したかどうかの結果を格納する（例外とか起きてたらfalseになる）
				boolean b = false;

				boolean check = true;

				if (id.equals("") || name.equals("") || sei.equals("") || birth.equals("") || ken.equals("")
						|| city.equals("") || pass.equals("") || kengen.equals("")) {

					insertErrorLabel.setText("入力項目に不備があります");
					check = false;
				}

				if (check == true) {
					//DB接続
					int option = 0;
					option = JOptionPane.showConfirmDialog(insertButton, "このデータを追加しますか？", "最終確認", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);

					if (option == JOptionPane.YES_OPTION) {

						DBCls1 db = new DBCls1();
						b = db.insert(id, name, kana, sei, birth, ken, city, dept, pass, kengen);

						if (b == false) {
							insertErrorLabel.setText("正しい入力値を入力してください");
						} else {
							/*****結果画面の戻る、修正、削除ボタンを使用可能にする******/
							Result.setFixJudge(true);
							Result.setDelteJudge(true);
							Result.setBackJudge(true);
							/*****結果画面の戻る、修正、削除ボタンを使用可能にする******/
							Result result = new Result();
							result.setVisible(true);

							Component c = (Component) e.getSource();
							Window w = SwingUtilities.getWindowAncestor(c);
							w.dispose();
						}
					}
				}
			}
		});
		insertButton.setBounds(215, 512, 170, 62);
		contentPane.add(insertButton);

		JButton backButton = new JButton("戻る");
		backButton.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuB mB = new MenuB();
				mB.setVisible(true);

				Component c = (Component) e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
			}
		});
		backButton.setBounds(386, 11, 120, 40);
		contentPane.add(backButton);

		JLabel lblNewLabel_2 = new JLabel("年");
		lblNewLabel_2.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblNewLabel_2.setBounds(253, 314, 32, 39);
		contentPane.add(lblNewLabel_2);

		JLabel label_6 = new JLabel("月");
		label_6.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_6.setBounds(385, 316, 32, 39);
		contentPane.add(label_6);

		JLabel label_9 = new JLabel("日");
		label_9.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_9.setBounds(521, 316, 32, 39);
		contentPane.add(label_9);

	}
}
