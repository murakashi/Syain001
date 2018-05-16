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
		setBounds(100, 100, 633, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("追加画面");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 32));
		lblNewLabel.setBounds(238, 10, 162, 40);
		contentPane.add(lblNewLabel);

		JLabel idLabel = new JLabel("社員ID");
		idLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		idLabel.setBounds(58, 74, 85, 29);
		contentPane.add(idLabel);

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
		idField.setBounds(144, 71, 146, 40);
		contentPane.add(idField);
		idField.setColumns(10);

		JLabel nameLabel = new JLabel("氏名");
		nameLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		nameLabel.setBounds(64, 129, 67, 31);
		contentPane.add(nameLabel);

		nameField = new JTextField();
		nameField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		nameField.setColumns(10);
		nameField.setBounds(144, 121, 146, 48);
		contentPane.add(nameField);

		JLabel kanaLabel = new JLabel("フリガナ");
		kanaLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		kanaLabel.setBounds(331, 130, 85, 29);
		contentPane.add(kanaLabel);

		kanaField = new JTextField();
		kanaField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		kanaField.setColumns(10);
		kanaField.setBounds(417, 121, 146, 48);
		contentPane.add(kanaField);

		JLabel seiLabel = new JLabel("性別");
		seiLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		seiLabel.setBounds(69, 245, 73, 40);
		contentPane.add(seiLabel);

		JLabel birthLabel = new JLabel("生年月日");
		birthLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		birthLabel.setBounds(30, 311, 109, 40);
		contentPane.add(birthLabel);

		JLabel kenLabel = new JLabel("都道府県");
		kenLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		kenLabel.setBounds(30, 187, 109, 31);
		contentPane.add(kenLabel);

		JLabel cityLabel = new JLabel("市町村");
		cityLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		cityLabel.setBounds(313, 188, 96, 29);
		contentPane.add(cityLabel);

		cityField = new JTextField();
		cityField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		cityField.setColumns(10);
		cityField.setBounds(417, 179, 146, 48);
		contentPane.add(cityField);

		JLabel deptLabel = new JLabel("部門ID");
		deptLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		deptLabel.setBounds(315, 250, 86, 35);
		contentPane.add(deptLabel);

		JLabel passLabel = new JLabel("パスワード");
		passLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		passLabel.setBounds(30, 384, 107, 37);
		contentPane.add(passLabel);

		passField = new JTextField();
		passField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		passField.setColumns(10);
		passField.setBounds(145, 378, 146, 48);
		contentPane.add(passField);

		JLabel kengenLabel = new JLabel("権限");
		kengenLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		kengenLabel.setBounds(340, 381, 73, 40);
		contentPane.add(kengenLabel);

		//性別コンボボックス
		JComboBox seiComboBox = new JComboBox();
		seiComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "男", "女" }));
		seiComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		seiComboBox.setBounds(144, 243, 146, 48);
		contentPane.add(seiComboBox);

		//権限コンボボックス
		JComboBox kengenComboBox = new JComboBox();
		kengenComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kengenComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "一般", "管理者" }));
		kengenComboBox.setBounds(417, 381, 146, 45);
		contentPane.add(kengenComboBox);

		//都道府県コンボボックス
		JComboBox kenComboBox = new JComboBox();
		kenComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "北海道 ", "青森県 ", "岩手県 ", "宮城県 ", "秋田県 ",
				"山形県  ", "福島県 ", "茨城県 ", "栃木県 ", "群馬県 ", "埼玉県 ", "千葉県 ", "東京都 ", "神奈川県 ", "新潟県 ", "富山県 ", "石川県 ",
				"福井県 ", "山梨県 ", "長野県 ", "岐阜県 ", "静岡県 ", "愛知県 ", "三重県 ", "滋賀県 ", "京都府  ", "大阪府 ", "兵庫県 ", "奈良県 ",
				"和歌山県 ", "鳥取県 ", "島根県 ", "岡山県 ", "広島県 ", "山口県 ", "徳島県  ", "香川県 ", "愛媛県 ", "高知県 ", "福岡県 ", "佐賀県 ",
				"長崎県 ", "熊本県 ", "大分県 ", "宮崎県  ", "鹿児島県 ", "沖縄県" }));
		kenComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kenComboBox.setBounds(146, 179, 146, 48);
		contentPane.add(kenComboBox);

		//部署IDコンボボックス
		JComboBox deptComboBox = new JComboBox();
		deptComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "未選択", "0001", "0002", "0003", "0004", "0005", "0006" }));
		deptComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deptComboBox.setBounds(417, 243, 146, 48);
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
		yearCcomboBox.setBounds(144, 311, 120, 48);
		contentPane.add(yearCcomboBox);

		//月コンボボックス
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "未選択", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		monthComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		monthComboBox.setBounds(300, 312, 97, 45);
		contentPane.add(monthComboBox);

		//日コンボボックス
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] { "未選択", "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "26", "27", "28", "29", "30", "31" }));
		dayComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		dayComboBox.setBounds(433, 313, 99, 45);
		contentPane.add(dayComboBox);

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

				/*if (year.equals("") || month.equals("") || day.equals("")) {
					JOptionPane.showMessageDialog(insertButton, "日付に不備があります");
				} else {*/
				birth = year + "-" + month + "-" + day;
				//}

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

					JOptionPane.showMessageDialog(insertButton, "入力に不備があります");
					check = false;
				} else if (year.equals("") || month.equals("") || day.equals("")) {
					JOptionPane.showMessageDialog(insertButton, "日付に不備があります");
					birthLabel.setForeground(Color.RED);
				} else if (check == true && pass.length() <= 8 && name.length() <= 20 && kana.length() <= 50
						&& city.length() <= 50) {
					//DB接続
					int option = 0;
					option = JOptionPane.showConfirmDialog(insertButton, "このデータを追加しますか？", "最終確認",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);

					if (option == JOptionPane.YES_OPTION) {

						DBCls1 db = new DBCls1();
						b = db.insert(id, name, kana, sei, birth, ken, city, dept, pass, kengen);

						if (b == false) {
							JOptionPane.showMessageDialog(insertButton, "正しい値を入力してください");
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
				} else if (name.length() > 20 || name.equals("")) {
					nameLabel.setForeground(Color.RED);
					JOptionPane.showMessageDialog(insertButton, "氏名が長すぎます");
				} else if (kana.length() > 50) {
					kanaLabel.setForeground(Color.RED);
					JOptionPane.showMessageDialog(insertButton, "フリガナが長すぎます");
				} else if (city.length() > 50) {
					cityLabel.setForeground(Color.RED);
					JOptionPane.showMessageDialog(insertButton, "市町村が長すぎます");
				} else if (pass.length() > 8) {
					passLabel.setForeground(Color.RED);
					JOptionPane.showMessageDialog(insertButton, "パスワードが長すぎます");
				}

			}
		});
		insertButton.setBounds(212, 453, 170, 62);
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
		backButton.setBounds(412, 11, 120, 40);
		contentPane.add(backButton);

		JLabel lblNewLabel_2 = new JLabel("年");
		lblNewLabel_2.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblNewLabel_2.setBounds(271, 316, 32, 39);
		contentPane.add(lblNewLabel_2);

		JLabel label_6 = new JLabel("月");
		label_6.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_6.setBounds(403, 318, 32, 39);
		contentPane.add(label_6);

		JLabel label_9 = new JLabel("日");
		label_9.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_9.setBounds(539, 318, 32, 39);
		contentPane.add(label_9);

	}
}
