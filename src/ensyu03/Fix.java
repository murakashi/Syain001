package ensyu03;

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

	/*******発行したSQLが正しかったかどうかを格納する（例外起きてたらfalse）*******/
	boolean b = false;

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
		setBounds(100, 100, 678, 578);
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

				Component c = (Component)e.getSource();
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
		label_1.setBounds(67, 132, 142, 31);
		contentPane.add(label_1);

		JLabel lblid = new JLabel("氏名");
		lblid.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblid.setBounds(186, 139, 82, 21);
		contentPane.add(lblid);

		JLabel label_2 = new JLabel("フリガナ");
		label_2.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_2.setBounds(173, 184, 82, 21);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("都道府県");
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_3.setBounds(147, 221, 120, 31);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("市町村");
		label_4.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_4.setBounds(164, 274, 82, 21);
		contentPane.add(label_4);

		JLabel lblid_1 = new JLabel("部門ID");
		lblid_1.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblid_1.setBounds(169, 326, 82, 21);
		contentPane.add(lblid_1);



		JComboBox kengenComboBox = new JComboBox();
		kengenComboBox.setModel(new DefaultComboBoxModel(new String[] {"未選択", "無効（データ復元）"}));
		kengenComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kengenComboBox.setBounds(259, 376, 196, 41);
		contentPane.add(kengenComboBox);

		//都道府県コンボボックス
		JComboBox kenComboBox = new JComboBox();
		kenComboBox.setModel(new DefaultComboBoxModel(new String[] {"未選択", "北海道 ", "青森県 ", "岩手県 ", "宮城県 ", "秋田県 ", "山形県 ", "福島県 ", "茨城県 ", "栃木県 ", "群馬県 ", "埼玉県 ", "千葉県 ", "東京都 ", "神奈川県 ", "新潟県 ", "富山県 ", "石川県 ", "福井県 ", "山梨県 ", "長野県 ", "岐阜県 ", "静岡県 ", "愛知県 ", "三重県 ", "滋賀県 ", "京都府 ", "大阪府 ", "兵庫県 ", "奈良県 ", "和歌山県 ", "鳥取県 ", "島根県 ", "岡山県 ", "広島県 ", "山口県 ", "徳島県 ", "香川県 ", "愛媛県 ", "高知県 ", "福岡県 ", "佐賀県 ", "長崎県  ", "熊本県 ", "大分県 ", "宮崎県 ", "鹿児島県 ", "沖縄県 "}));
		kenComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kenComboBox.setBounds(255, 217, 202, 41);
		contentPane.add(kenComboBox);

		//部門IDコンボボックス
		JComboBox deptComboBox = new JComboBox();
		deptComboBox.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deptComboBox.setModel(new DefaultComboBoxModel(new String[] {"未選択", "0001", "0002", "0003", "0004", "0005", "0006"}));
		deptComboBox.setBounds(255, 319, 199, 40);
		contentPane.add(deptComboBox);

		//修正エラー表示用ラベル
		JLabel fixErrorLabel = new JLabel("");
		fixErrorLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
		fixErrorLabel.setBounds(169, 427, 431, 43);
		contentPane.add(fixErrorLabel);

		/**************修正ボタン（入力項目を修正して結果を反映して検索結果画面に戻る）*****************/
		JButton button = new JButton("修正");
		button.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = nameField.getText();
				String kana = kanaField.getText();
				String ken = (String)kenComboBox.getSelectedItem();
				String city = cityField.getText();
				String dept = (String)deptComboBox.getSelectedItem();
				String flag = (String)kengenComboBox.getSelectedItem();

				//都道府県コンボボックス未選択時処理
				if(ken.equals("未選択")) {
					ken = "";
				}

				//部門IDコンボボックス未選択時処理
				if(dept.equals("未選択")) {
					dept = "";
				}

				//権限コンボボックス未選択時処理
				if(flag.equals("未選択")) {
					flag = "";
				}else {
					flag = "0";
				}

				String id = fixidField.getText();

				//未入力チェック用（何1つ入力されていなかったらfalse）
				boolean check = true;

				/**********************修正項目入力チェック********************************/
				if (name.equals("") && kana.equals("") && kana.equals("") && city.equals("")
						&& dept.equals("") && flag.equals("")) {
					fixErrorLabel.setText("修正項目が入力されていません");
					check = false;
				}

				if (check == true) {
					DBCls1 db = new DBCls1();

					b = db.fix(name, kana, ken, city, dept, flag, id);
					if (b == false) {
						fixErrorLabel.setText("正しい入力値を入力してください");
					} else {
						Result result = new Result();
						result.setVisible(true);

						Component c = (Component)e.getSource();
						 Window w = SwingUtilities.getWindowAncestor(c);
						 w.dispose();
					}
				}
				/**************修正ボタン（入力項目を修正して結果を反映して検索結果画面に戻る）*****************/
			}
		});
		button.setBounds(261, 483, 131, 40);
		contentPane.add(button);

		nameField = new JTextField();
		nameField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		nameField.setBounds(253, 130, 202, 37);
		contentPane.add(nameField);
		nameField.setColumns(10);

		kanaField = new JTextField();
		kanaField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		kanaField.setColumns(10);
		kanaField.setBounds(253, 175, 202, 37);
		contentPane.add(kanaField);

		cityField = new JTextField();
		cityField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		cityField.setColumns(10);
		cityField.setBounds(254, 267, 201, 41);
		contentPane.add(cityField);

		JLabel lblNewLabel_1 = new JLabel("社員ID");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblNewLabel_1.setBounds(132, 76, 102, 27);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("部門ID");
		lblNewLabel_2.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		lblNewLabel_2.setBounds(489, 78, 82, 31);
		contentPane.add(lblNewLabel_2);

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

		JLabel label_5 = new JLabel("削除フラグ");
		label_5.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_5.setBounds(147, 377, 111, 33);
		contentPane.add(label_5);
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
