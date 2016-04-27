package project.vehicle.management.dto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
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
	JLabel fHeading, fLabel1, fLabel2, fLabel3, fLabel4, fLabel5, fLabel6, imageLabel1, imageLabel2;
	Screen_Main getDealer = new Screen_Main();

	JComboBox<String> fCombo1;
	JComboBox<String> fCombo2;
	JComboBox<String> fCombo3;
	JComboBox<String> fCombo4;
	JComboBox<String> fCombo5;
	JComboBox<String> fCombo6;
	JTable table;
	ArrayList<Car> data; // list of data receiving from the file
	Object[][] dataFinal; // data to fill in the table
	String dealerName;
	int clickedRow;
	Car clickedCar; // Car of being clicked
	JRadioButton buttonClicked;// button which has been clicked

	Font font = new Font("Arial", Font.BOLD, 24);
	CarFileManager cfm = new CarFileManager();

	// ----------------------------------------------------
	// ----------Changes made by Jia(Section 1/4)----------
	CarFilterManager carFilterManager;
	// ----------Changes made by Jia(Section 1/4) End----------
	// --------------------------------------------------------

	// Font f = new Font(Font.PLAIN, 16);

	/*
	 * constructor for receive value from Screen #1
	 */
	Screen_CarList(String dealerName) {
		this.dealerName = dealerName;
		createAddComponents(dealerName, buttonClicked);
		addListener();
		makeVisible(dealerName);
	}

	/*
	 * constructor to receive the value of radioButton
	 */
	Screen_CarList(JRadioButton jb) {
		this.buttonClicked = jb;
		createAddComponents(dealerName, buttonClicked);
		addListener();
		makeVisible(dealerName);
	}

	/*
	 * constructor for receive value from Screen #3
	 */
	Screen_CarList(ArrayList<Car> data, String dealerName) {
		this.dealerName = dealerName;
		this.data = data;
		createAddComponents(dealerName, buttonClicked);
		addListener();
		makeVisible(dealerName);
	}

	private void createAddComponents(String dealerName, JRadioButton jb) {
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

		/*
		 * read dealerName from Screen #1 and form the carList
		 */
		ArrayList<Car> temp = new ArrayList<Car>();
		temp = cfm.readCars("/Users/workspace/Final_Project/src/project/vehicle/data/" + dealerName);
		CarSearchManager csm = new CarSearchManager(temp);
		data = csm.listCarsByDealer(dealerName);

		/*
		 * form the data to fill in the JTable
		 */
		List<String> columns = Arrays.asList("ACTION", "VIN", "CONDITION", "YEAR", "MAKE", "MODEL", "TRIM", "TYPE",
				"PRICE");

		// final data to fill in the table
		dataFinal = new Object[data.size()][columns.size()];
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < columns.size(); j++) {
				switch (j) {
				case 0:
					dataFinal[i][0] = new JRadioButton();
					break;
				case 1:
					dataFinal[i][1] = data.get(i).getVIN();
					break;
				case 2:
					dataFinal[i][2] = data.get(i).getCondition();
					break;
				case 3:
					dataFinal[i][3] = data.get(i).getYear();
					break;
				case 4:
					dataFinal[i][4] = data.get(i).getMake();
					break;
				case 5:
					dataFinal[i][5] = data.get(i).getModel();
					break;
				case 6:
					dataFinal[i][6] = data.get(i).getTrim();
					break;
				case 7:
					dataFinal[i][7] = data.get(i).getType();
					break;
				case 8:
					dataFinal[i][8] = data.get(i).getPrice();
					break;
				}
			}
		}

		/*
		 * JTable design
		 */
		table = new JTable(new MyModel(columns, dataFinal));
		tablePanel.add(new JScrollPane(table));
		table.setAutoCreateRowSorter(true);
		table.setPreferredScrollableViewportSize(new Dimension(1150, 490));
		table.setShowGrid(true);
		table.setGridColor(Color.lightGray);
		table.setEditingColumn(0);

		/*
		 * fill the first column with radioButton
		 */
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setCellRenderer(new RadioButtonRenderer());
		column.setCellEditor(new RadioButtonEditor(new JCheckBox()));
		table.setOpaque(true);

		/*
		 * filter design
		 */
		fHeading = new JLabel(("<html><font color='062F8E'>Filter Cars</font></html>"));
		filterPanel1.add(fHeading);

		// ----------------------------------------------------
		// ----------Changes made by Jia(Section 2/4)----------
		// This part is to get and display the contents of each filter combo box
		// when screen2 initialized
		carFilterManager = new CarFilterManager(dealerName);

		fLabel1 = new JLabel("<html><font color='062F8E'>Condition:</font></html>");

		List<String> conditions = new ArrayList<String>();
		conditions = carFilterManager.getConditionChoice();
		fCombo1 = new JComboBox<String>();
		for (String str : conditions) {
			fCombo1.addItem(str);
		}

		fLabel2 = new JLabel("<html><font color='062F8E'>Year:</font></html>");

		List<String> years = new ArrayList<String>();
		years = carFilterManager.getYearChoice();
		fCombo2 = new JComboBox<String>();
		for (String str : years) {
			fCombo2.addItem(str);
		}

		fLabel3 = new JLabel("<html><font color='062F8E'>Make:</font></html>");

		List<String> makes = new ArrayList<String>();
		makes = carFilterManager.getMakeChoice();
		fCombo3 = new JComboBox<String>();
		for (String str : makes) {
			fCombo3.addItem(str);
		}

		fLabel4 = new JLabel("<html><font color='062F8E'>Model:</font></html>");
		List<String> models = new ArrayList<String>();
		models = carFilterManager.getModelChoice();
		fCombo4 = new JComboBox<String>();
		for (String str : models) {
			fCombo4.addItem(str);
		}

		fLabel5 = new JLabel("<html><font color='062F8E'>Type:</font></html>");
		List<String> types = new ArrayList<String>();
		types = carFilterManager.getTypeChoice();
		fCombo5 = new JComboBox<String>();
		for (String str : types) {
			fCombo5.addItem(str);
		}

		fLabel6 = new JLabel("<html><font color='062F8E'>Price:</font></html>");
		List<String> price = new ArrayList<String>();
		price = carFilterManager.getPriceChoice();
		fCombo6 = new JComboBox<String>();
		for (String str : price) {
			fCombo6.addItem(str);
		}
		// ----------Changes made by Jia(Section 2/4) End----------
		// --------------------------------------------------------

		
		
		/*
		 * set gridbagLayout
		 */
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

		// next 7 lines include changes made for heading/picture
		imageLabel1 = new JLabel("");
		imageLabel1.setIcon(new ImageIcon("/Users/workspace/Final_Project/src/project/vehicle/pic/Screen2_header.jpg"));
		imagePanel = new JPanel();
		imagePanel.add(imageLabel1);
		this.getContentPane().add(filterPanel, BorderLayout.WEST);
		this.getContentPane().add(tablePanel, BorderLayout.CENTER);
		this.getContentPane().add(imagePanel, BorderLayout.NORTH);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	}

	@SuppressWarnings("static-access")
	private void makeVisible(String dealerName) {

		this.setTitle("Welcome " + dealerName);
		this.setSize(1500, 1500);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setDefaultLookAndFeelDecorated(true);

	}

	private void addListener() {

		delete.addActionListener(this);
		add.addActionListener(this);
		reset.addActionListener(this);
		modify.addActionListener(this);

		// ----------------------------------------------------
		// ----------Changes made by Jia(Section 3/4)----------
		// Add listeners
		ComboBoxListener cbl = new ComboBoxListener();
		filter.addActionListener(this);
		fCombo1.addActionListener(cbl);
		fCombo2.addActionListener(cbl);
		fCombo3.addActionListener(cbl);
		fCombo4.addActionListener(cbl);
		fCombo5.addActionListener(cbl);
		fCombo6.addActionListener(cbl);
		// ----------Changes made by Jia(Section 3/4) End----------
		// --------------------------------------------------------

	}

	private void updateData() {
		List<String> columns = Arrays.asList("ACTION", "VIN", "CONDITION", "YEAR", "MAKE", "MODEL", "TRIM", "TYPE",
				"PRICE");
		data = (ArrayList) carFilterManager.getFilteredCarList();
		refreshData();
	}

	private void resetData() {

		ArrayList<Car> temp = new ArrayList<Car>();
		temp = cfm.readCars("/Users/workspace/Final_Project/src/project/vehicle/data/" + dealerName);
		CarSearchManager csm = new CarSearchManager(temp);
		data = csm.listCarsByDealer(dealerName);
		refreshData();

	}

	private void refreshData() {
		List<String> columns = Arrays.asList("ACTION", "VIN", "CONDITION", "YEAR", "MAKE", "MODEL", "TRIM", "TYPE",
				"PRICE");
		dataFinal = new Object[data.size()][columns.size()];
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < columns.size(); j++) {
				switch (j) {
				case 0:
					dataFinal[i][0] = new JRadioButton();
					break;
				case 1:
					dataFinal[i][1] = data.get(i).getVIN();
					break;
				case 2:
					dataFinal[i][2] = data.get(i).getCondition();
					break;
				case 3:
					dataFinal[i][3] = data.get(i).getYear();
					break;
				case 4:
					dataFinal[i][4] = data.get(i).getMake();
					break;
				case 5:
					dataFinal[i][5] = data.get(i).getModel();
					break;
				case 6:
					dataFinal[i][6] = data.get(i).getTrim();
					break;
				case 7:
					dataFinal[i][7] = data.get(i).getType();
					break;
				case 8:
					dataFinal[i][8] = data.get(i).getPrice();
					break;
				}
			}
			table.setModel(new MyModel(columns, dataFinal));
			table.updateUI();
		}
	}

	public void actionPerformed(ActionEvent ae) {
		/*
		 * "delete" button function
		 */
		  if (ae.getSource() == delete) {

              int deleteIndex = table.getSelectedRow();
              System.out.println(deleteIndex);
              JDialog.setDefaultLookAndFeelDecorated(true);
              int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?",
                              "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
              if (confirm == 0) {
                      String vin = (String) table.getValueAt(deleteIndex, 1);
                      System.out.println(vin);
                      ArrayList<Car> tempDelete = new ArrayList<Car>();
                      tempDelete = cfm.readCars(
                                      "/Users/workspace/Final_Project/src/project/vehicle/data/"
                                                      + dealerName);
                      CarDataManager cdm = new CarDataManager(tempDelete);

                      cdm.deleteCarByVIN(vin);
                      cfm.writeCars(tempDelete,"/Users/workspace/Final_Project/src/project/vehicle/data/"+ dealerName);
                      new Screen_CarList(tempDelete, dealerName);

              }
		  }
		/*
		 * "add" button function
		 */
		if (ae.getSource() == add) {

			Screen_AddRecord addScreen = new Screen_AddRecord(dealerName, data);
		}
		/*
		 * "modify" button function
		 */
		if (ae.getSource() == modify) {

			int test = table.getSelectedRow();
			Car c = new Car((String) dataFinal[test][1], dealerName, (String) dataFinal[test][2],
					(Integer) dataFinal[test][3], (String) dataFinal[test][4], (String) dataFinal[test][5],
					(String) dataFinal[test][6], (String) dataFinal[test][7], (Double) dataFinal[test][8]);
			Screen_ModifyRecord modifyScreen = new Screen_ModifyRecord(c, dealerName, data);
		}

		/*
		 * "reset" button function
		 */
		if (ae.getSource() == reset) {

			fCombo1.setSelectedIndex(0);
			fCombo2.setSelectedIndex(0);
			fCombo3.setSelectedIndex(0);
			fCombo4.setSelectedIndex(0);
			fCombo5.setSelectedIndex(0);
			fCombo6.setSelectedIndex(0);
			resetData();

		}
		// ----------------------------------------------------
		// ----------Changes made by Jia(Section 4/4)----------
		// for the "filter" button I just return an filtered Arraylist<Car>, but
		// didn't
		// get it displayed in the screen
		if (ae.getSource() == filter) {
			List<Car> filteredCarList = carFilterManager.getFilteredCarList();
		}

		if (!fCombo1.getSelectedItem().equals("Select")) {

			// first get the index of the choice in combo box
			int i = fCombo1.getSelectedIndex(); // get the choice (a String)
												// through the
			String conditionSelect = fCombo1.getItemAt(i); // filter the carList
															// by the choice
			carFilterManager.conditionFilter(conditionSelect);
		}
		if (!fCombo2.getSelectedItem().equals("Select")) {
			int i = fCombo2.getSelectedIndex();
			String yearSelect = fCombo2.getItemAt(i);
			carFilterManager.yearFilter(yearSelect);
		}
		if (!fCombo3.getSelectedItem().equals("Select")) {
			int i = fCombo3.getSelectedIndex();
			String makeSelect = fCombo3.getItemAt(i);
			carFilterManager.makeFilter(makeSelect);
		}
		if (!fCombo4.getSelectedItem().equals("Select")) {
			int i = fCombo4.getSelectedIndex();
			String modelSelect = fCombo4.getItemAt(i);
			carFilterManager.modelFilter(modelSelect);
		}
		if (!fCombo5.getSelectedItem().equals("Select")) {
			int i = fCombo5.getSelectedIndex();
			String typeSelect = fCombo5.getItemAt(i);
			carFilterManager.typeFilter(typeSelect);
		}
		if (!fCombo6.getSelectedItem().equals("Select")) {
			int i = fCombo6.getSelectedIndex();
			String priceSelect = fCombo6.getItemAt(i);
			carFilterManager.priceFilter(priceSelect);

		}
		updateData();
		// ----------Changes made by Jia(Section 4/4) End----------
		// --------------------------------------------------------
	}

	class ComboBoxListener implements ActionListener {
		int currentCombo = 0;
		String[] currCombos = new String[5];

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == fCombo1 && (currentCombo == 0 || currentCombo == 1)) {
				currentCombo = 1;
				// first get the index of the choice in combo box
				int i = fCombo1.getSelectedIndex();
				// get the choice (a String) through the index
				String conditionSelect = fCombo1.getItemAt(i);
				// filter the carList by the choice
				carFilterManager.conditionFilter(conditionSelect);
				saveCurrent();
				// to update the other combo box, first clear them all
				fCombo2.removeAllItems();
				fCombo3.removeAllItems();
				fCombo4.removeAllItems();
				fCombo5.removeAllItems();
				fCombo6.removeAllItems();
				// then add the new choiceList to each filter
				// the new choiceList comes from the filtered carList
				for (String str : carFilterManager.getYearChoice()) {
					fCombo2.addItem(str);
				}
				for (String str : carFilterManager.getMakeChoice())
					fCombo3.addItem(str);
				for (String str : carFilterManager.getModelChoice())
					fCombo4.addItem(str);
				for (String str : carFilterManager.getTypeChoice())
					fCombo5.addItem(str);
				for (String str : carFilterManager.getPriceChoice())
					fCombo6.addItem(str);
				recoverCurrent(1);
				currentCombo = 0;
			}

			if (e.getSource() == fCombo2 && (currentCombo == 0 || currentCombo == 2)) {
				currentCombo = 2;
				int i = fCombo2.getSelectedIndex();
				String yearSelect = fCombo2.getItemAt(i);
				carFilterManager.yearFilter(yearSelect);
				saveCurrent();
				fCombo1.removeAllItems();
				fCombo3.removeAllItems();
				fCombo4.removeAllItems();
				fCombo5.removeAllItems();
				fCombo6.removeAllItems();

				for (String str : carFilterManager.getConditionChoice())
					fCombo1.addItem(str);
				for (String str : carFilterManager.getMakeChoice())
					fCombo3.addItem(str);
				for (String str : carFilterManager.getModelChoice())
					fCombo4.addItem(str);
				for (String str : carFilterManager.getTypeChoice())
					fCombo5.addItem(str);
				for (String str : carFilterManager.getPriceChoice())
					fCombo6.addItem(str);
				recoverCurrent(2);
				currentCombo = 0;
			}
			if (e.getSource() == fCombo3 && (currentCombo == 0 || currentCombo == 3)) {
				currentCombo = 3;
				int i = fCombo3.getSelectedIndex();
				String makeSelect = fCombo3.getItemAt(i);
				carFilterManager.makeFilter(makeSelect);
				saveCurrent();
				fCombo1.removeAllItems();
				fCombo2.removeAllItems();
				fCombo4.removeAllItems();
				fCombo5.removeAllItems();
				fCombo6.removeAllItems();

				for (String str : carFilterManager.getConditionChoice())
					fCombo1.addItem(str);
				for (String str : carFilterManager.getYearChoice())
					fCombo2.addItem(str);
				for (String str : carFilterManager.getModelChoice())
					fCombo4.addItem(str);
				for (String str : carFilterManager.getTypeChoice())
					fCombo5.addItem(str);
				for (String str : carFilterManager.getPriceChoice())
					fCombo6.addItem(str);
				recoverCurrent(3);
				currentCombo = 0;
			}
			if (e.getSource() == fCombo4 && (currentCombo == 0 || currentCombo == 4)) {
				currentCombo = 4;
				int i = fCombo4.getSelectedIndex();
				String modelSelect = fCombo4.getItemAt(i);
				carFilterManager.modelFilter(modelSelect);
				saveCurrent();
				fCombo1.removeAllItems();
				fCombo2.removeAllItems();
				fCombo3.removeAllItems();
				fCombo5.removeAllItems();
				fCombo6.removeAllItems();

				for (String str : carFilterManager.getConditionChoice())
					fCombo1.addItem(str);
				for (String str : carFilterManager.getYearChoice())
					fCombo2.addItem(str);
				for (String str : carFilterManager.getMakeChoice())
					fCombo3.addItem(str);
				for (String str : carFilterManager.getTypeChoice())
					fCombo5.addItem(str);
				for (String str : carFilterManager.getPriceChoice())
					fCombo6.addItem(str);
				recoverCurrent(4);
				currentCombo = 0;
			}
			if (e.getSource() == fCombo5 && (currentCombo == 0 || currentCombo == 5)) {
				currentCombo = 5;
				int i = fCombo5.getSelectedIndex();
				String typeSelect = fCombo5.getItemAt(i);
				carFilterManager.typeFilter(typeSelect);
				saveCurrent();
				fCombo1.removeAllItems();
				fCombo2.removeAllItems();
				fCombo3.removeAllItems();
				fCombo4.removeAllItems();
				fCombo6.removeAllItems();

				for (String str : carFilterManager.getConditionChoice())
					fCombo1.addItem(str);
				for (String str : carFilterManager.getYearChoice())
					fCombo2.addItem(str);
				for (String str : carFilterManager.getMakeChoice())
					fCombo3.addItem(str);
				for (String str : carFilterManager.getModelChoice())
					fCombo4.addItem(str);
				for (String str : carFilterManager.getPriceChoice())
					fCombo6.addItem(str);
				recoverCurrent(5);
				currentCombo = 0;
			}
			if (e.getSource() == fCombo6 && (currentCombo == 0 || currentCombo == 6)) {
				currentCombo = 6;
				int i = fCombo6.getSelectedIndex();
				String priceSelect = fCombo6.getItemAt(i);
				carFilterManager.priceFilter(priceSelect);
				saveCurrent();
				fCombo1.removeAllItems();
				fCombo2.removeAllItems();
				fCombo3.removeAllItems();
				fCombo4.removeAllItems();
				fCombo5.removeAllItems();

				for (String str : carFilterManager.getConditionChoice())
					fCombo1.addItem(str);
				for (String str : carFilterManager.getYearChoice())
					fCombo2.addItem(str);
				for (String str : carFilterManager.getMakeChoice())
					fCombo3.addItem(str);
				for (String str : carFilterManager.getModelChoice())
					fCombo4.addItem(str);
				for (String str : carFilterManager.getTypeChoice())
					fCombo5.addItem(str);
				recoverCurrent(6);
				currentCombo = 0;
			}

		}

		private void saveCurrent() {
			currCombos[0] = (String) fCombo1.getSelectedItem();
			currCombos[1] = (String) fCombo2.getSelectedItem();
			currCombos[2] = (String) fCombo3.getSelectedItem();
			currCombos[3] = (String) fCombo4.getSelectedItem();
			currCombos[4] = (String) fCombo5.getSelectedItem();
		}

		private void recoverCurrent(int indexCombo) {
			if (indexCombo != 1) {
				fCombo1.setSelectedItem(currCombos[0]);
			}
			if (indexCombo != 2) {
				fCombo2.setSelectedItem(currCombos[1]);
			}
			if (indexCombo != 3) {
				fCombo3.setSelectedItem(currCombos[2]);
			}
			if (indexCombo != 4) {
				fCombo4.setSelectedItem(currCombos[3]);
			}
			if (indexCombo != 5) {
				fCombo5.setSelectedItem(currCombos[4]);
			}
		}

	}

	// CarDataManager -> addCar, deleteCar, modifyCar
	// CarFileManager -> readCars, writeCars
	// CarFilterManager -> listOfYears, listOfMakes, listOfModels,
	// listOfPriceRanges, counts (nice to have)
	// CarSearchManager -> listCars, searchCars, sortCars
	public static void main(String[] args) {

	}
}
