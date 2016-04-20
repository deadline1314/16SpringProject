package project.vehicle.management.dto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

/**
 * 
 * Screen 2 //dialog -> delete warning
 * 
 * @Author: Sana
 * 
 */

@SuppressWarnings("serial")
public class Screen_CarList extends JFrame implements ActionListener {

	JButton add;
	JButton delete;
	JButton modify;
	JButton filter;
	JButton clear;

	Screen_CarList() {
		createAddComponents();
		addListener();
		makeVisible();
	}

	private void createAddComponents() {
		add = new JButton("Add");
		delete = new JButton("Delete");
		modify = new JButton("Modify");
		filter = new JButton("Filter");
		clear = new JButton("Reset");

		JPanel filterPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(add);
		buttonPanel.add(delete);
		buttonPanel.add(modify);

		JLabel fHeading = new JLabel("Filter Cars");
		JPanel fpanel0 = new JPanel();
		fpanel0.add(fHeading);

		JPanel fpanel1 = new JPanel(new FlowLayout());
		JLabel flabel1 = new JLabel("Condition:");
		String[] conditions = new String[] { "Select", "New", "Used", "Certified" };
		JComboBox<String> fCombo1 = new JComboBox<String>(conditions);
		fpanel1.add(flabel1);
		fpanel1.add(fCombo1);

		JPanel fpanel2 = new JPanel(new FlowLayout());
		JLabel flabel2 = new JLabel("Year:");
		String[] years = new String[] { "Select" };
		JComboBox<String> fCombo2 = new JComboBox<String>(years);

		fpanel2.add(flabel2);
		fpanel2.add(fCombo2);

		JPanel fpanel3 = new JPanel(new FlowLayout());
		JLabel flabel3 = new JLabel("Make:");
		String[] make = new String[] { "Select" };
		JComboBox<String> fCombo3 = new JComboBox<String>(make);

		fpanel3.add(flabel3);
		fpanel3.add(fCombo3);

		JPanel fpanel4 = new JPanel(new FlowLayout());
		JLabel flabel4 = new JLabel("Model:");
		String[] model = new String[] { "Select" };
		JComboBox<String> fCombo4 = new JComboBox<String>(model);

		fpanel4.add(flabel4);
		fpanel4.add(fCombo4);

		JPanel fpanel5 = new JPanel(new FlowLayout());
		JLabel flabel5 = new JLabel("Type:");
		String[] type = new String[] { "Select" };
		JComboBox<String> fCombo5 = new JComboBox<String>(type);

		fpanel5.add(flabel5);
		fpanel5.add(fCombo5);

		JPanel fpanel6 = new JPanel(new FlowLayout());
		JLabel flabel6 = new JLabel("Price:");
		String[] price = new String[] { "Select", "10k - 15k", "15k - 30k", "30k - 45k" };
		JComboBox<String> fCombo6 = new JComboBox<String>(price);

		fpanel6.add(flabel6);
		fpanel6.add(fCombo6);

		JPanel fpanel7 = new JPanel(new FlowLayout());
		fpanel7.add(filter);
		fpanel7.add(clear);

		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));

		filterPanel.add(fpanel0);
		filterPanel.add(fpanel1);
		filterPanel.add(fpanel2);
		filterPanel.add(fpanel3);
		filterPanel.add(fpanel4);
		filterPanel.add(fpanel5);
		filterPanel.add(fpanel6);
		filterPanel.add(fpanel7);

		Table table = new Table();
		// table.setOpaque(true);
		tablePanel.add(table);

		this.getContentPane().add(filterPanel, BorderLayout.WEST);
		this.getContentPane().add(tablePanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	}

	private void makeVisible() {
		this.setTitle("Welcome DealerName"); //screen #1 has function getSelectedName()
		this.setSize(1500, 1500);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void addListener() {

		delete.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == delete) {
			JDialog.setDefaultLookAndFeelDecorated(true);
			JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		}

	}
	
	public void show(){
		new Screen_CarList();
	}

	// CarDataManager -> addCar, deleteCar, modifyCar
	// CarFileManager -> readCars, writeCars
	// CarFilterManager -> listOfYears, listOfMakes, listOfModels,
	// listOfPriceRanges, counts (nice to have)
	// CarSearchManager -> listCars, searchCars, sortCars

	public static void main(String[] args) {

		Screen_CarList screen = new Screen_CarList();

	}

}