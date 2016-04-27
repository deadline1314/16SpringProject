package project.vehicle.management.dto;

import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.table.AbstractTableModel;

public class Table {

	@SuppressWarnings("serial")
	public static class MyModel extends AbstractTableModel {

		private Object[][] data;
		private List<String> columnNames;

		public MyModel(List<String> columnNames, Object[][] data) {
			super();
			this.columnNames = columnNames;
			this.data = data;
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return columnNames.size();
		}

		@Override
		public String getColumnName(int column) {
			return columnNames.get(column);
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			return data[rowIndex][columnIndex];
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c) != null ? getValueAt(0, c).getClass() : String.class;
		}

		public boolean isCellEditable(int row, int col) {

			return true;
		}

		// try
		@Override
		public void setValueAt(Object value, int row, int col) {
			if ((value != null) && (value.getClass() == JRadioButton.class)) {
				data[row][col] = value;
				fireTableCellUpdated(row, col);
			}
		}

		private void deselectValues(int selectedRow, int col) {
			for (int row = 0; row < getRowCount(); row++) {
				if (getValueAt(row, col).equals(Boolean.TRUE) && row != selectedRow) {
					setValueAt(Boolean.FALSE, row, col);
					fireTableCellUpdated(row, col);
				}
			}
		}
	};

}
