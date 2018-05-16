package syain009;

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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MenuB extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuB frame = new MenuB();
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
	public MenuB() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("メニュー画面（管理者）");
		label.setFont(new Font("Dialog", Font.BOLD, 42));
		label.setBounds(145, 10, 573, 68);
		contentPane.add(label);

		//検索ボタン（検索・結果画面に遷移）
		JButton searchButton = new JButton("検索・修正・削除");
		searchButton.setFont(new Font("Dialog", Font.BOLD, 36));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result.setFixJudge(true);
				Result.setDelteJudge(true);
				Result.setBackJudge(true);
				Result result = new Result();
				//result.setFixJudge(true);
				result.setVisible(true);

				Component c = (Component)e.getSource();
				 Window w = SwingUtilities.getWindowAncestor(c);
				 w.dispose();

			}
		});
		searchButton.setBounds(192, 111, 365, 115);
		contentPane.add(searchButton);

		//ログアウトボタン（ウィンドウ閉じる）
		JButton endButton = new JButton("ログアウト");
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = 0;

				option = JOptionPane.showConfirmDialog(endButton, "ログアウトしますか？", "ログアウト確認", JOptionPane.YES_NO_OPTION,
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
		endButton.setFont(new Font("Dialog", Font.BOLD, 42));
		endButton.setBounds(192, 353, 368, 102);
		contentPane.add(endButton);

		//追加ボタン（追加画面に遷移）
		JButton insertButton = new JButton("追加");
		insertButton.setFont(new Font("Dialog", Font.BOLD, 42));
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert insert = new Insert();
				insert.setVisible(true);

				Component c = (Component)e.getSource();
				 Window w = SwingUtilities.getWindowAncestor(c);
				 w.dispose();
			}
		});
		insertButton.setBounds(192, 239, 368, 102);
		contentPane.add(insertButton);
	}

}
