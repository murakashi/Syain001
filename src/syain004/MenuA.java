package syain004;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MenuA extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuA frame = new MenuA();
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
	public MenuA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("メニュー画面（一般）");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 32));
		label.setBounds(104, 11, 322, 49);
		contentPane.add(label);

		//検索ボタン（検索画面に遷移）
		JButton searchButton = new JButton("検索");
		searchButton.setFont(new Font("MS UI Gothic", Font.BOLD, 29));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result.setFixJudge(false);
				Result.setDelteJudge(false);
				Result.setBackJudge(false);
				Result result = new Result();
				//result.setFixJudge(false);
				result.setVisible(true);

				Component c = (Component)e.getSource();
				 Window w = SwingUtilities.getWindowAncestor(c);
				 w.dispose();
			}
		});
		searchButton.setBounds(113, 89, 246, 58);
		contentPane.add(searchButton);

		//終了ボタン（ウィンドウ閉じる）
		JButton endButton = new JButton("終了");
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component c = (Component)e.getSource();
				 Window w = SwingUtilities.getWindowAncestor(c);
				 w.dispose();
			}
		});
		endButton.setFont(new Font("MS UI Gothic", Font.BOLD, 29));
		endButton.setBounds(112, 171, 247, 58);
		contentPane.add(endButton);
	}
}
