package project.vehicle.management.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

public class TestTable {

    public static class MyModel extends AbstractTableModel {

        private List<Object[]> data;
        private List<String> columnNames;

        public MyModel(List<String> columnNames, List<Object[]> data) {
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

    protected void initUI(String dealerName) {
    	CarSearchManager csm = new CarSearchManager();
        JFrame frame = new JFrame(TestTable.class.getSimpleName());
        List<String> columns = Arrays.asList("ACTION", "VIN", "CONDITION", "YEAR", "MAKE", "MODEL", "TRIM", "TYPE","PRICE");
        ArrayList<Car> dataList = csm.listCarsByDealer(dealerName);
        Object[] o = dataList.toArray();
        List<Object[]> data = dataList; 
        for (int i = 0; i < data.size(); i++) {
            Object[] value = new Object[9];
            value[0] = "";
            value[1] = dataList.get(i).getVIN();
            value[2] = dataList.get(i).getCondition();
            value[3] = dataList.get(i).getYear();
            value[4] = dataList.get(i).getMake();
            value[5] = dataList.get(i).getModel();
            value[6] = dataList.get(i).getTrim();
            value[7] = dataList.get(i).getType();
            value[8] = dataList.get(i).getPrice();
           data.add(value);
            
        }
        JTable table = new JTable(new MyModel(columns, data));
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TestTable().initUI("gmps-gilroy");
            }
        });
    }
}