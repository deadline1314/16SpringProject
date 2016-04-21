package project.vehicle.management.dto;
/**
 * 
 * @author Sana
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.TableColumn;

public class Table extends JPanel {

	public Table() {
		super(new GridLayout(1, 0));

		JTable table = new JTable(new MyTableModel());

		table.setAutoCreateRowSorter(true);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 900));
		table.setShowGrid(true);
		table.setGridColor(Color.lightGray);
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setCellEditor(new RadioButton());
		column.setCellRenderer(new RadioButton());
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}

class MyTableModel extends AbstractTableModel {

	private final String[] columnNames = { "ACTION", "VIN", "CONDITION", "YEAR", "MAKE", "MODEL", "TRIM", "TYPE",
			"PRICE" };

	private Object[][] data = {
			{ " ", "2354335313", "new", "2015", "Cadillac", "Escalade ESV", "2.0L Turbo I4 RWD Luxury", "SUV",
					"51127.0" },
			{ "", "2570378823", "new", "2019", "Cadillac", "CTS Sedan", "RWD Platinum", "CAR", "89884.0" } };

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public boolean isCellEditable(int row, int col) {

		return true;
	}

	private void setRowData() {

	}

}

