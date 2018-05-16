package ensyu01;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Insert extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField kanaField;
	private JTextField seiField;
	private JTextField birthdayField;
	private JTextField kenField;
	private JTextField cityField;
	private JTextField deptField;
	private JTextField passField;
	private JTextField kengenField;

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
		setBounds(100, 100, 597, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("追加画面");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 32));
		lblNewLabel.setBounds(185, 10, 162, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("社員ID");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		lblNewLabel_1.setBounds(40, 73, 85, 29);
		contentPane.add(lblNewLabel_1);

		idField = new JTextField();
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

		seiField = new JTextField();
		seiField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		seiField.setColumns(10);
		seiField.setBounds(128, 243, 146, 48);
		contentPane.add(seiField);

		JLabel label_3 = new JLabel("生年月日");
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_3.setBounds(13, 301, 109, 40);
		contentPane.add(label_3);

		birthdayField = new JTextField();
		birthdayField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		birthdayField.setColumns(10);
		birthdayField.setBounds(128, 306, 146, 48);
		contentPane.add(birthdayField);

		JLabel label_4 = new JLabel("都道府県");
		label_4.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		label_4.setBounds(284, 75, 109, 31);
		contentPane.add(label_4);

		kenField = new JTextField();
		kenField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		kenField.setColumns(10);
		kenField.setBounds(401, 68, 146, 48);
		contentPane.add(kenField);

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

		deptField = new JTextField();
		deptField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		deptField.setColumns(10);
		deptField.setBounds(401, 177, 146, 48);
		contentPane.add(deptField);

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
		label_8.setBounds(312, 309, 73, 40);
		contentPane.add(label_8);

		kengenField = new JTextField();
		kengenField.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		kengenField.setColumns(10);
		kengenField.setBounds(402, 304, 146, 48);
		contentPane.add(kengenField);

		//追加エラーメッセージ表示用ラベル
		JLabel insertErrorLabel = new JLabel("");
		insertErrorLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
		insertErrorLabel.setBounds(139, 371, 384, 56);
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
				String sei = seiField.getText();
				String birth = birthdayField.getText();
				String ken = kenField.getText();
				String city = cityField.getText();
				String dept = deptField.getText();
				String pass = passField.getText();
				String kengen = kengenField.getText();

				//発行したSQLが成功したかどうかの結果を格納する（例外とか起きてたらfalseになる）
				boolean b=false;

				if (id.equals("") || name.equals("") || sei.equals("") || birth.equals("") || ken.equals("")
						|| city.equals("") || pass.equals("") || kengen.equals("")) {

					insertErrorLabel.setText("入力項目に不備があります");

				}
				else{
					//DB接続
					DBCls1 db = new DBCls1();
					b = db.insert(id,name,kana,sei,birth,ken,city,dept,pass,kengen);
				}

				if(b == false) {
					insertErrorLabel.setText("正しい入力値を入力してください");
				}
				else{
					Result result = new Result();
					result.setVisible(true);
				}
			}
		});
		insertButton.setBounds(231, 446, 170, 62);
		contentPane.add(insertButton);

		JButton backButton = new JButton("戻る");
		backButton.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuB mB = new MenuB();
				mB.setVisible(true);
			}
		});
		backButton.setBounds(359, 11, 120, 40);
		contentPane.add(backButton);
	}

}
