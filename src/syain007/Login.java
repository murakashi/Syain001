package syain007;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("社員管理システム");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
		label.setBounds(170, 11, 313, 49);
		contentPane.add(label);

		JLabel label_1 = new JLabel("社員ID");
		label_1.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
		label_1.setBounds(109, 92, 84, 26);
		contentPane.add(label_1);

		//社員ID入力テキストフィールド
		idField = new JTextField();
		idField.setFont(new Font("MS UI Gothic", Font.PLAIN, 28));
		idField.setColumns(10);
		idField.setBounds(205, 81, 180, 49);
		contentPane.add(idField);

		JLabel label_2 = new JLabel("パスワード");
		label_2.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
		label_2.setBounds(81, 161, 112, 26);
		contentPane.add(label_2);

		//ログインエラーメッセージ表示用ラベル
		JLabel loginErrorLabel = new JLabel("");
		loginErrorLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
		loginErrorLabel.setBounds(109, 209, 529, 55);
		contentPane.add(loginErrorLabel);

		//ログインボタン（メニュー画面に遷移する）
		JButton loginButton = new JButton("ログイン");
		loginButton.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ログイン成功時に一般か管理者かでメニュー画面を振り分ける
				DBCls1 db = new DBCls1();

				String id = idField.getText();
				id = change(id);
				String pass = new String(passwordField.getPassword());
				String sql = "select * from 社員マスタ where 削除フラグ = 0 and 社員ID = '" + id + "' and パスワード = '" + pass + "'";
				String flag = "";

				/**************IDが有効かどうかの判断*********************/
				if (!(id.equals("")) && !(pass.equals(""))) {

					flag = db.getFlag(id);
					if (flag.equals("1")) {
						//loginErrorLabel.setText("この社員IDは無効です");
						JOptionPane.showMessageDialog(loginButton, "この社員IDは無効です");

						idField.setText("");
						passwordField.setText("");
					} else {
						String s = db.login(sql);//ログイン試みる

						if (s.equals("")) {//ログイン失敗
							//loginErrorLabel.setText("社員IDまたはパスワードが間違っています。");
							JOptionPane.showMessageDialog(loginButton, "社員IDまたはパスワードが間違っています");
							idField.setText("");
							passwordField.setText("");
						} else if (s.equals("0")) {//一般でログイン成功の場合
							MenuA mA = new MenuA();
							mA.setVisible(true);
							/*********************ログイン画面閉じる*****************************/
							Component c = (Component) e.getSource();
							Window w = SwingUtilities.getWindowAncestor(c);
							w.dispose();
							/*********************ログイン画面閉じる*****************************/
						} else {//管理者でログイン成功の場合
							MenuB mB = new MenuB();
							mB.setVisible(true);
							/*********************ログイン画面閉じる*****************************/
							Component c = (Component) e.getSource();
							Window w = SwingUtilities.getWindowAncestor(c);
							w.dispose();
							/*********************ログイン画面閉じる*****************************/
						}
					}
				} else {
					JOptionPane.showMessageDialog(loginButton, "入力に不備があります");
				}
			}
		});
		loginButton.setBounds(223, 279, 150, 55);
		contentPane.add(loginButton);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("MS UI Gothic", Font.PLAIN, 28));
		passwordField.setBounds(205, 150, 180, 49);
		contentPane.add(passwordField);

		JButton endButton = new JButton("終了");
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int option = 0;

				option = JOptionPane.showConfirmDialog(endButton, "システムを終了しますか？", "終了確認", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (option == JOptionPane.YES_OPTION) {
					/*********************ログイン画面閉じる*****************************/
					Component c = (Component) e.getSource();
					Window w = SwingUtilities.getWindowAncestor(c);
					w.dispose();
					/*********************ログイン画面閉じる*****************************/
				}
			}
		});
		endButton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		endButton.setBounds(459, 14, 136, 49);
		contentPane.add(endButton);
	}

	public static String change(String s) {
		String hankaku = null;
		if (s != null) {
			StringBuilder sb = new StringBuilder(s);
			for (int i = 0; i < sb.length(); i++) {
				int c = (int) sb.charAt(i);
				if (c >= 0xFF10 && c <= 0xFF19) {
					sb.setCharAt(i, (char) (c - 0xFEE0));
				}
			}
			hankaku = sb.toString();
		}
		return hankaku;
	}
}
