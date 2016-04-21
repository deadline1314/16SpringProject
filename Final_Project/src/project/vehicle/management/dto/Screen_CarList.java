package project.vehicle.management.dto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	JPanel filterPanel, tablePanel, buttonPanel, filterPanel1, filterPanel2;
	JLabel fHeading, fLabel1, fLabel2, fLabel3, fLabel4, fLabel5, fLabel6;
	Screen_Main getDealer = new Screen_Main();

	JComboBox<String> fCombo1;
	JComboBox<String> fCombo2;
	JComboBox<String> fCombo3;
	JComboBox<String> fCombo4;
	JComboBox<String> fCombo5;
	JComboBox<String> fCombo6;
	Font font = new Font("Arial", Font.BOLD, 24);

	// Font f = new Font(Font.PLAIN, 16);

	Screen_CarList(String dealerName) {
		createAddComponents();
		addListener();
		makeVisible(dealerName);
	}

	private void createAddComponents() {
		add = new JButton("<html><font color='062F8E'>Add</font></html>");
		delete = new JButton("<html><font color='062F8E'>Delete</font></html>");
		modify = new JButton("<html><font color='062F8E'>Modify</font></html>");
		filter = new JButton("<html><font color='062F8E'>Filter</font></html>");
		reset = new JButton("<html><font color='062F8E'>Reset</font></html>");

		filterPanel = new JPanel();
		filterPanel1 = new JPanel();
		filterPanel2 = new JPanel();
		tablePanel = new JPanel();
		buttonPanel = new JPanel();

		buttonPanel.add(add);
		buttonPanel.add(delete);
		buttonPanel.add(modify);
		Table table = new Table();
		// table.setOpaque(true);
		tablePanel.add(table);

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
		gbc.ipady = 60;
		gbc.gridx = 0;
		gbc.gridy = 0;
		filterPanel.add(fHeading, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 60;
		gbc.gridx = 0;
		gbc.gridy = 2;
		filterPanel.add(fLabel1, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		filterPanel.add(fCombo1, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 60;
		gbc.gridx = 0;
		gbc.gridy = 3;
		filterPanel.add(fLabel2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		filterPanel.add(fCombo2, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 60;
		gbc.gridx = 0;
		gbc.gridy = 4;
		filterPanel.add(fLabel3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		filterPanel.add(fCombo3, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 60;
		gbc.gridx = 0;
		gbc.gridy = 5;
		filterPanel.add(fLabel4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		filterPanel.add(fCombo4, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 60;
		gbc.gridx = 0;
		gbc.gridy = 6;
		filterPanel.add(fLabel5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		filterPanel.add(fCombo5, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 60;
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

		this.getContentPane().add(filterPanel, BorderLayout.WEST);
		this.getContentPane().add(tablePanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		//deleteDialog = new JDialog();

	}
	

	private void makeVisible(String dealerName) {
		
		this.setTitle("Welcome " +dealerName);
		//System.out.println(getDealer.getSelectedName());
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

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == delete) {
			
			JDialog.setDefaultLookAndFeelDecorated(true);
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); 
			if(confirm == 0){
				System.out.println("At least this works");
			/*	CarDataManager cm = new CarDataManager();
				cm.deleteCarByVIN(String VIN); */
			}
		}
		if (ae.getSource() == add) {
			Screen_AddRecord addScreen = new Screen_AddRecord();
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

		//Screen_CarList screen = new Screen_CarList();

	}

}

