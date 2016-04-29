package project.vehicle.management.dto;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * 
 * @author Dora
 *
 */

public class Screen_ModifyRecord extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> conditionBox, yearBox;
	private JTextField vinField, makeField, modelField, trimField, typeField, priceField;
	private JButton modify, cancel;
	private JLabel dealerName, vin, condition, year, make, model, trim, type, price;
	private Car car;
	private ArrayList<Car> carList;
	public CarDataManager dataManager;
	public CarFileManager fileManager;
	private String dn;

//	public static void main(String args[]) {
//		 new Screen_ModifyRecord();
//	}

	public Screen_ModifyRecord(Car car, String dealerName, ArrayList<Car> data) {
		this.car = car;
		this.dn = dealerName;
		this.carList = data;
		dataManager = new CarDataManager(carList);
		fileManager = new CarFileManager();

		setTitle("Modify car");
		createComponents();
		addComponent();
		addListeners();
		makeItVisible();

	}


	private void createComponents() {

		dealerName = new JLabel("Dealer Name: " + car.getDealerName());// can't change name;														
		vin = new JLabel("VIN:");
		condition = new JLabel("Car Condition: ");
		year = new JLabel("Year: ");
		make = new JLabel("Make: ");
		model = new JLabel("Model: ");
		trim = new JLabel("Trim: ");
		type = new JLabel("Body style: ");
		price = new JLabel("Price: ");

		modify = new JButton("Modify");
		cancel = new JButton("Cancel");

		vinField = new JTextField(25);
		vinField.setText(car.getVIN());
		vinField.setEditable(false);

		makeField = new JTextField(25);
		makeField.setText(car.getMake());

		modelField = new JTextField(25);
		modelField.setText(car.getModel());

		trimField = new JTextField(25);
		trimField.setText(car.getModel());

		typeField = new JTextField(25);
		typeField.setText(car.getType());

		priceField = new JTextField(25);
		priceField.setText(String.valueOf(car.getPrice()));

		String[] yearList = new String[47];
		for (int i = 1970, j = 0; i <= 2016; i++, j++) {
			yearList[j] = i + "";
		}
		yearBox = new JComboBox<String>(yearList);
		yearBox.setSelectedItem(car.getYear());

		String[] Conditions = { "new", "used", "certified" };
		conditionBox = new JComboBox<String>(Conditions);
		conditionBox.setSelectedItem(car.getCondition());

	}

	private void addListeners() {
		ModifyListener m = new ModifyListener();
		modify.addActionListener(m);

		CancelListener c = new CancelListener();
		cancel.addActionListener(c);

	}

	class ModifyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String v = vinField.getText();
			String c = conditionBox.getSelectedItem().toString();
			String yString = yearBox.getSelectedItem().toString();
			String mk = makeField.getText();
			String md = modelField.getText();
			String tm = trimField.getText();
			String tp = typeField.getText();
			String pString = priceField.getText();

			if (v.isEmpty() || mk.isEmpty() || md.isEmpty() || tm.isEmpty() || tp.isEmpty() || pString.isEmpty()) {

				JOptionPane.showMessageDialog(new JLabel(), "You must fill all the information!", "Empty error",
						JOptionPane.ERROR_MESSAGE);

			} else {
				int y = Integer.parseInt(yString);
				double p = Double.parseDouble(pString);
				Car car = new Car(v, dn, c, y, mk, md, tm, tp, p);
				dataManager.modifyCar(car);
				String des = "src/project/vehicle/data/" + dn;
				System.out.println("wrting to file: " + des);
				fileManager.writeCars(carList, des);
				new Screen_CarList(carList, dn);
				dispose();
			}
		}
	}

	class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();

		}

	}

	private void addComponent() {

		setLayout(new GridBagLayout());
		// GridBagConstraints gbc = new GridBagConstraints();
		add(dealerName, new GridBagConstraints(0, 0, 4, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		add(vin, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(vinField, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(condition, new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(conditionBox, new GridBagConstraints(4, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		add(year, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(yearBox, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(make, new GridBagConstraints(3, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(makeField, new GridBagConstraints(4, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(model, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(modelField, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		add(trim, new GridBagConstraints(3, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(trimField, new GridBagConstraints(4, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(type, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(typeField, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(price, new GridBagConstraints(3, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(priceField, new GridBagConstraints(4, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		add(modify, new GridBagConstraints(2, 5, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(cancel, new GridBagConstraints(3, 5, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));

	}

	private void makeItVisible() {
		this.setSize(600, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null); // show in the center
		this.setResizable(false);
		this.setBackground(Color.white);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void windowClosing(final WindowEvent e) {
		this.dispose();
	}

}
