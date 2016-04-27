package project.vehicle.management.dto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import project.vehicle.management.dto.Table.MyModel;

/**
 * 
 * Screen 2 //dialog -> delete warning
 * 
 * @Author: Sana
 * 
 */
@SuppressWarnings("serial")
public class Screen_CarList extends JFrame implements ActionListener {

	JButton add, delete, modify, filter, reset;
	JPanel filterPanel, tablePanel, buttonPanel, filterPanel1, imagePanel;
	JLabel fHeading, fLabel1, fLabel2, fLabel3, fLabel4, fLabel5, fLabel6, imageLabel1;
	Screen_Main getDealer = new Screen_Main();

	JComboBox<String> fCombo1;
	JComboBox<String> fCombo2;
	JComboBox<String> fCombo3;
	JComboBox<String> fCombo4;
	JComboBox<String> fCombo5;
	JComboBox<String> fCombo6;
	ArrayList<Car> data;
	String dealerName;
	Font font = new Font("Arial", Font.BOLD, 24);
	CarFileManager cfm = new CarFileManager();
	JTable table;

	// Font f = new Font(Font.PLAIN, 16);

	Screen_CarList(String dealerName) {
		this.dealerName = dealerName;
		createAddComponents(dealerName);
		addListener();
		makeVisible(dealerName);
	}

	Screen_CarList(ArrayList<Car> data, String dealerName) {
		this.dealerName = dealerName;
		this.data = data;
		createAddComponents(dealerName);
		addListener();
		makeVisible(dealerName);
	}

	private void createAddComponents(String dealerName) {
		add = new JButton("<html><font color='062F8E'>Add</font></html>");
		delete = new JButton("<html><font color='062F8E'>Delete</font></html>");
		modify = new JButton("<html><font color='062F8E'>Modify</font></html>");
		filter = new JButton("<html><font color='062F8E'>Filter</font></html>");
		reset = new JButton("<html><font color='062F8E'>Reset</font></html>");

		filterPanel = new JPanel();
		filterPanel1 = new JPanel();
		tablePanel = new JPanel();
		buttonPanel = new JPanel();

		buttonPanel.add(add);
		buttonPanel.add(delete);
		buttonPanel.add(modify);

		ArrayList<Car> temp = new ArrayList<Car>();
		temp = cfm.readCars("/Users/khutaijashariff/Documents/workspace/16SpringProject/Final_Project/src/project/vehicle/data/"
						+ dealerName);
		CarSearchManager csm = new CarSearchManager(temp);

		List<String> columns = Arrays.asList("ACTION", "VIN", "CONDITION", "YEAR", "MAKE", "MODEL", "TRIM", "TYPE",
				"PRICE");
		data = csm.listCarsByDealer(dealerName);

		Object[][] data1 = new Object[data.size()][columns.size()];
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < columns.size(); j++) {
				switch (j) {
				case 0:
					data1[i][0] = new JRadioButton();
					break;
				case 1:
					data1[i][1] = data.get(i).getVIN();
					break;
				case 2:
					data1[i][2] = data.get(i).getCondition();
					break;
				case 3:
					data1[i][3] = data.get(i).getYear();
					break;
				case 4:
					data1[i][4] = data.get(i).getMake();
					break;
				case 5:
					data1[i][5] = data.get(i).getModel();
					break;
				case 6:
					data1[i][6] = data.get(i).getTrim();
					break;
				case 7:
					data1[i][7] = data.get(i).getType();
					break;
				case 8:
					data1[i][8] = data.get(i).getPrice();
					break;
				}
			}
		}
	    table = new JTable(new MyModel(columns, data1));
		tablePanel.add(new JScrollPane(table));
		table.setAutoCreateRowSorter(true);
		table.setPreferredScrollableViewportSize(new Dimension(1150, 480));
		table.setShowGrid(true);
		table.setGridColor(Color.lightGray);
		table.setEditingColumn(0);
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setCellRenderer(new RadioButtonRenderer());
		column.setCellEditor(new RadioButtonEditor(new JCheckBox()));
		table.setOpaque(true);
		table.getSelectionModel();
		//int test = table.getSelectedRow();
		//System.out.println(test);
	
		// Object test = table.getValueAt(,1);
		// System.out.println(test.toString());
		// column1.setCellEditor();

		fHeading = new JLabel(("<html><font color='062F8E'>Filter Cars</font></html>"));
		filterPanel1.add(fHeading);

		fLabel1 = new JLabel("<html><font color='062F8E'>Condition:</font></html>");

		String[] conditions = new String[] { "Select", "New", "Used", "Certified" };
		fCombo1 = new JComboBox<String>(conditions);

		fLabel2 = new JLabel("<html><font color='062F8E'>Year:</font></html>");

		String[] years = new String[] { "Select" };
		fCombo2 = new JComboBox<String>(years);

		fLabel3 = new JLabel("<html><font color='062F8E'>Make:</font></html>");
		String[] make = new String[] { "Select" };
		fCombo3 = new JComboBox<String>(make);

		fLabel4 = new JLabel("<html><font color='062F8E'>Model:</font></html>");
		String[] model = new String[] { "Select" };
		fCombo4 = new JComboBox<String>(model);

		fLabel5 = new JLabel("<html><font color='062F8E'>Type:</font></html>");
		String[] type = new String[] { "Select" };
		fCombo5 = new JComboBox<String>(type);

		fLabel6 = new JLabel("<html><font color='062F8E'>Price:</font></html>");
		String[] price = new String[] { "Select", "10k - 15k", "15k - 30k", "30k - 45k" };
		fCombo6 = new JComboBox<String>(price);

		GridBagLayout layout = new GridBagLayout();
		filterPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 50;
		gbc.gridx = 0;
		gbc.gridy = 0;
		filterPanel.add(fHeading, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 50;
		gbc.gridx = 0;
		gbc.gridy = 2;
		filterPanel.add(fLabel1, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		filterPanel.add(fCombo1, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 50;
		gbc.gridx = 0;
		gbc.gridy = 3;
		filterPanel.add(fLabel2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		filterPanel.add(fCombo2, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 50;
		gbc.gridx = 0;
		gbc.gridy = 4;
		filterPanel.add(fLabel3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		filterPanel.add(fCombo3, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 50;
		gbc.gridx = 0;
		gbc.gridy = 5;
		filterPanel.add(fLabel4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		filterPanel.add(fCombo4, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 50;
		gbc.gridx = 0;
		gbc.gridy = 6;
		filterPanel.add(fLabel5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		filterPanel.add(fCombo5, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 50;
		gbc.gridx = 0;
		gbc.gridy = 7;
		filterPanel.add(fLabel6, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		filterPanel.add(fCombo6, gbc);

		filterPanel1.add(filter);
		filterPanel1.add(reset);

		gbc.gridx = 1;
		gbc.gridy = 8;
		filterPanel.add(filterPanel1, gbc);

		//next 7 lines include changes made for heading/picture
		imageLabel1 = new JLabel("");
		imageLabel1.setIcon(new ImageIcon("/Users/khutaijashariff/Documents/workspace/16SpringProject/Final_Project/src/project/vehicle/pic/Screen2_header.jpg"));
		imagePanel = new JPanel();
		imagePanel.add(imageLabel1);
		this.getContentPane().add(filterPanel, BorderLayout.WEST);
		this.getContentPane().add(tablePanel, BorderLayout.CENTER);
		this.getContentPane().add(imagePanel, BorderLayout.NORTH);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	}

	private void makeVisible(String dealerName) {

		this.setTitle("Welcome " + dealerName);
		this.setSize(1500, 1500);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setDefaultLookAndFeelDecorated(true);
		// this.setFonts();

	}

	private void addListener() {

		delete.addActionListener(this);
		add.addActionListener(this);
		reset.addActionListener(this);
		modify.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == delete) {
			

			JDialog.setDefaultLookAndFeelDecorated(true);
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?",
					"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (confirm == 0) {

				ArrayList<Car> tempDelete = new ArrayList<Car>();
				tempDelete = cfm.readCars(
						"/Users/khutaijashariff/Documents/workspace/16SpringProject/Final_Project/src/project/vehicle/data/"
								+ dealerName);
				CarDataManager cdm = new CarDataManager(tempDelete);
				cdm.deleteCarByVIN("2655761333");
				cfm.writeCars(tempDelete,
						"/Users/khutaijashariff/Documents/workspace/16SpringProject/Final_Project/src/project/vehicle/data/"
								+ dealerName);
				new Screen_CarList(tempDelete, dealerName);

			}
		}
		if (ae.getSource() == modify) {
			
			int test = table.getSelectedRow();
			System.out.println(test);
		}
		
		if (ae.getSource() == add) {

			Screen_AddRecord addScreen = new Screen_AddRecord(dealerName, data);
		}
		if (ae.getSource() == reset) {

			fCombo1.setSelectedIndex(0);
			fCombo2.setSelectedIndex(0);
			fCombo3.setSelectedIndex(0);
			fCombo4.setSelectedIndex(0);
			fCombo5.setSelectedIndex(0);
			fCombo6.setSelectedIndex(0);

		}

	}

	// CarDataManager -> addCar, deleteCar, modifyCar
	// CarFileManager -> readCars, writeCars
	// CarFilterManager -> listOfYears, listOfMakes, listOfModels,
	// listOfPriceRanges, counts (nice to have)
	// CarSearchManager -> listCars, searchCars, sortCars

	public static void main(String[] args) {

		// Screen_CarList screen = new Screen_CarList();

	}

}
