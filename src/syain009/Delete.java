package syain009;

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
import javax.swing.JOptionPane;
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
		setBounds(100, 100, 896, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("削除画面");
		label.setFont(new Font("Dialog", Font.BOLD, 50));
		label.setBounds(334, 11, 232, 84);
		contentPane.add(label);

		//戻るボタン（検索結果画面に戻る）
		JButton backbutton = new JButton("戻る");
		backbutton.setFont(new Font("Dialog", Font.BOLD, 42));
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result result = new Result();
				result.setVisible(true);

				Component c = (Component) e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
			}
		});
		backbutton.setBounds(593, 15, 201, 74);
		contentPane.add(backbutton);

		//削除ボタン（削除フラグを1にして結果を反映して検索結果画面に戻る）
		JButton deleteButton = new JButton("削除");
		deleteButton.setFont(new Font("Dialog", Font.BOLD, 42));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean mJ=true;//削除していいかどうかの判断を行う
				/**********************管理者が１人のときに応じた処理の準備*****************/
				DBCls1 mg = new DBCls1();
				int managers = mg.getManagers();//管理者の人数を取得
				String mid = mg.getKengrn(deleteIdField.getText());//削除対象社員の権限を取得
				String mF = mg.getFlag(deleteIdField.getText());

				if(managers <= 1 && mid.equals("1") && mF.equals("0")) {
					mJ = false;
					JOptionPane.showMessageDialog(deleteButton, "管理者がいなくなってしまうため、\nこのデータを削除することはできません");
					Result result = new Result();
					result.setVisible(true);

					Component c = (Component) e.getSource();
					Window w = SwingUtilities.getWindowAncestor(c);
					w.dispose();
				}else if(mF.equals("1")){
					mJ = false;
					JOptionPane.showMessageDialog(deleteButton, "このデータはすでに削除されているため、\nこの処理を実行することはできません");
					Result result = new Result();
					result.setVisible(true);

					Component c = (Component) e.getSource();
					Window w = SwingUtilities.getWindowAncestor(c);
					w.dispose();
				}else {
				/**********************管理者が１人のときに応じた処理の準備*****************/

				int option = 0;

				option = JOptionPane.showConfirmDialog(deleteButton, "このデータを削除しますか？", "最終確認",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

				if (option == JOptionPane.YES_OPTION && mJ == true) {

					/************削除フラグを1に更新するための条件となる社員IDをDBClsクラスのdeleteメソッドに渡す**************/
					DBCls1 db = new DBCls1();

					int num = db.delete(deleteIdField.getText());

					if (num == 1) {
						Result result = new Result();
						result.setVisible(true);

						Component c = (Component) e.getSource();
						Window w = SwingUtilities.getWindowAncestor(c);
						w.dispose();
					} else {
						JOptionPane.showMessageDialog(deleteButton, "このデータはすでに削除されているため、\nこの処理を実行することはできません");
						Result result = new Result();
						result.setVisible(true);

						Component c = (Component) e.getSource();
						Window w = SwingUtilities.getWindowAncestor(c);
						w.dispose();
					}
					/************ここまで削除フラグを1に更新するための条件となる社員IDをDBClsクラスのdeleteメソッドに渡す**************/
				}
				}
			}
		});
		deleteButton.setBounds(332, 418, 239, 88);
		contentPane.add(deleteButton);

		JLabel label_1 = new JLabel("対象社員：");
		label_1.setFont(new Font("Dialog", Font.BOLD, 42));
		label_1.setBounds(12, 119, 227, 54);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("社員ID");
		label_2.setFont(new Font("Dialog", Font.BOLD, 42));
		label_2.setBounds(219, 123, 143, 55);
		contentPane.add(label_2);

		deleteIdField = new JTextField();
		deleteIdField.setForeground(Color.BLACK);
		deleteIdField.setFont(new Font("Dialog", Font.BOLD, 42));
		deleteIdField.setEnabled(false);
		deleteIdField.setColumns(10);
		deleteIdField.setBounds(360, 117, 287, 71);
		contentPane.add(deleteIdField);

		JLabel label_3 = new JLabel("氏名");
		label_3.setFont(new Font("Dialog", Font.BOLD, 42));
		label_3.setBounds(225, 214, 127, 84);
		contentPane.add(label_3);

		deleteNameField = new JTextField();
		deleteNameField.setFont(new Font("Dialog", Font.BOLD, 42));
		deleteNameField.setEnabled(false);
		deleteNameField.setColumns(10);
		deleteNameField.setBounds(364, 214, 284, 83);
		contentPane.add(deleteNameField);

		JLabel label_4 = new JLabel("部門ID");
		label_4.setFont(new Font("Dialog", Font.BOLD, 42));
		label_4.setBounds(212, 316, 143, 71);
		contentPane.add(label_4);

		deleteDeptField = new JTextField();
		deleteDeptField.setFont(new Font("Dialog", Font.BOLD, 42));
		deleteDeptField.setEnabled(false);
		deleteDeptField.setColumns(10);
		deleteDeptField.setBounds(369, 317, 278, 80);
		contentPane.add(deleteDeptField);

	}

	//deletesのセッター
	public void setDeletes(String[] ss) {
		for (int i = 0; i < ss.length; i++) {
			deletes[i] = ss[i];
		}
		deleteIdField.setText(deletes[0]);
		deleteNameField.setText(deletes[1]);
		deleteDeptField.setText(deletes[7]);
	}

}
