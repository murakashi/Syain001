package ensyu01;

import java.awt.Color;
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
		setBounds(100, 100, 450, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("削除画面");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
		label.setBounds(159, 10, 123, 41);
		contentPane.add(label);

		//戻るボタン（検索結果画面に戻る）
		JButton backbutton = new JButton("戻る");
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result result = new Result();
				result.setVisible(true);
			}
		});
		backbutton.setBounds(257, 22, 91, 21);
		contentPane.add(backbutton);

		//削除ボタン（削除フラグを1にして結果を反映して検索結果画面に戻る）
		JButton deleteButton = new JButton("削除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/************削除フラグを1に更新するための条件となる社員IDをDBClsクラスのdeleteメソッドに渡す**************/
				DBCls1 db = new DBCls1();

				db.delete(deleteIdField.getText());

				Result result = new Result();
				result.setVisible(true);
				/************ここまで削除フラグを1に更新するための条件となる社員IDをDBClsクラスのdeleteメソッドに渡す**************/
			}
		});
		deleteButton.setBounds(159, 102, 91, 21);
		contentPane.add(deleteButton);

		//削除エラーメッセージ表示用ラベル
		JLabel deleteErrorLabel = new JLabel("");
		deleteErrorLabel.setBounds(169, 133, 50, 13);
		contentPane.add(deleteErrorLabel);

		JLabel label_1 = new JLabel("対象社員：");
		label_1.setBounds(12, 61, 70, 21);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("社員ID");
		label_2.setBounds(67, 65, 50, 13);
		contentPane.add(label_2);

		deleteIdField = new JTextField();
		deleteIdField.setForeground(Color.BLACK);
		deleteIdField.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		deleteIdField.setEnabled(false);
		deleteIdField.setColumns(10);
		deleteIdField.setBounds(112, 62, 50, 19);
		contentPane.add(deleteIdField);

		JLabel label_3 = new JLabel("氏名");
		label_3.setBounds(174, 65, 36, 13);
		contentPane.add(label_3);

		deleteNameField = new JTextField();
		deleteNameField.setEnabled(false);
		deleteNameField.setColumns(10);
		deleteNameField.setBounds(205, 62, 82, 19);
		contentPane.add(deleteNameField);

		JLabel label_4 = new JLabel("部門ID");
		label_4.setBounds(299, 65, 50, 13);
		contentPane.add(label_4);

		deleteDeptField = new JTextField();
		deleteDeptField.setEnabled(false);
		deleteDeptField.setColumns(10);
		deleteDeptField.setBounds(340, 62, 70, 19);
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
