package syain007;

import javax.swing.table.DefaultTableModel;

public class OriginalTableModel extends DefaultTableModel {
	@Override
	 public boolean isCellEditable(int row, int column) {
	 return false;
	 }
}
