package project.vehicle.management.dto;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Table{

    public static class MyModel extends AbstractTableModel {

        private ArrayList<Object[]> data;
    	//List<String>
    	//private ArrayList<Car> data;
        private List<String> columnNames;

        public MyModel(List<String> columnNames, ArrayList<Object[]> data) {
            super();
            this.columnNames = columnNames;
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.size();
        //	return columnNames.length();
        }

        @Override
        public String getColumnName(int column) {
            return columnNames.get(column);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex)[columnIndex];
        }

    }

}