package ensyu02;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Delete extends JFrame {

	private JPanel contentPane;

	//結果画面で選択されたJテーブルの行のデータを受け取るための配列
	//（削除対象社員の情報を表示するための準備）
	public String[] deletes = new String[8];

	/*************削除対象社員データ表示用テキストフィールド**********/
	private JTextField deleteIdField;
	private JTextField deleteNameField;
	private JTextField deleteDeptField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete frame = new Delete();
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
	public Delete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("削除画面");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
		label.setBounds(230, 10, 137, 41);
		contentPane.add(label);

		//戻るボタン（検索結果画面に戻る）
		JButton backbutton = new JButton("戻る");
		backbutton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result result = new Result();
				result.setVisible(true);

				Component c = (Component)e.getSource();
				 Window w = SwingUtilities.getWindowAncestor(c);
				 w.dispose();
			}
		});
		backbutton.setBounds(379, 13, 103, 41);
		contentPane.add(backbutton);

		//削除ボタン（削除フラグを1にして結果を反映して検索結果画面に戻る）
		JButton deleteButton = new JButton("削除");
		deleteButton.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/************削除フラグを1に更新するための条件となる社員IDをDBClsクラスのdeleteメソッドに渡す**************/
				DBCls1 db = new DBCls1();

				db.delete(deleteIdField.getText());

				Result result = new Result();
				result.setVisible(true);

				Component c = (Component)e.getSource();
				 Window w = SwingUtilities.getWindowAncestor(c);
				 w.dispose();
				/************ここまで削除フラグを1に更新するための条件となる社員IDをDBClsクラスのdeleteメソッドに渡す**************/
			}
		});
		deleteButton.setBounds(230, 154, 120, 41);
		contentPane.add(deleteButton);

		//削除エラーメッセージ表示用ラベル
		JLabel deleteErrorLabel = new JLabel("");
		deleteErrorLabel.setBounds(169, 133, 50, 13);
		contentPane.add(deleteErrorLabel);

		JLabel label_1 = new JLabel("対象社員：");
		label_1.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_1.setBounds(8, 81, 120, 35);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("社員ID");
		label_2.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_2.setBounds(115, 79, 90, 41);
		contentPane.add(label_2);

		deleteIdField = new JTextField();
		deleteIdField.setForeground(Color.BLACK);
		deleteIdField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deleteIdField.setEnabled(false);
		deleteIdField.setColumns(10);
		deleteIdField.setBounds(187, 81, 76, 37);
		contentPane.add(deleteIdField);

		JLabel label_3 = new JLabel("氏名");
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_3.setBounds(266, 80, 60, 37);
		contentPane.add(label_3);

		deleteNameField = new JTextField();
		deleteNameField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deleteNameField.setEnabled(false);
		deleteNameField.setColumns(10);
		deleteNameField.setBounds(316, 79, 120, 41);
		contentPane.add(deleteNameField);

		JLabel label_4 = new JLabel("部門ID");
		label_4.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		label_4.setBounds(437, 78, 76, 42);
		contentPane.add(label_4);

		deleteDeptField = new JTextField();
		deleteDeptField.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		deleteDeptField.setEnabled(false);
		deleteDeptField.setColumns(10);
		deleteDeptField.setBounds(512, 77, 103, 43);
		contentPane.add(deleteDeptField);

	}

	//deletesのセッター
		public void setDeletes(String[] ss) {
			for(int i=0;i<ss.length;i++) {
				deletes[i] = ss[i];
			}
			deleteIdField.setText(deletes[0]);
			deleteNameField.setText(deletes[1]);
			deleteDeptField.setText(deletes[7]);
		}

}
