package project.vehicle.management.dto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class Screen_Main extends JFrame{
	private JPanel contentPane;
	private JTable table;
	private JLabel plsSelectDealer, label1;
	private JComboBox<String> dealerOptions;
	private JButton manageInv;
	
	private String selectedName;

	public String getSelectedName() {
		return selectedName;
	}
	
	public Screen_Main(){
		setTitle("Inventory Mangement Expert");
		createComponents();
		addComponents();
		addListeners();
		makeItVisible();

	}
	
	private void createComponents(){
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		label1 = new JLabel("");
		label1.setIcon(new ImageIcon("/Users/workspace/Final_Project/src/project/vehicle/pic/Screen1_Title.jpg"));
		
		table = new JTable();
		
		plsSelectDealer = new JLabel("Please Select Dealer : ");
		plsSelectDealer.setFont(new Font("Arial", Font.BOLD, 14));
		
		//fill in the comboBox with DealerName
				try {
					dealerOptions = new JComboBox<String>();
					DealerManager dm = new DealerManager();
					List<String> contents = new ArrayList<String>();
					contents = dm.getDealersName("/Users/workspace/Final_Project/src/project/vehicle/data/dealers");
					for(String str : contents){
						dealerOptions.addItem(str);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				manageInv = new JButton("");
				manageInv.setBackground(UIManager.getColor("Button.highlight"));
				manageInv.setIcon(new ImageIcon("/Users/workspace/Final_Project/src/project/vehicle/pic/Screen1_Button.jpg"));
				
				
	}
	
	private void addComponents(){
		//set group layout
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(120, Short.MAX_VALUE)
							.addComponent(plsSelectDealer)
							.addGap(98)
							.addComponent(dealerOptions, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(161))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(160)
									.addComponent(manageInv, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
								.addComponent(label1))
							.addContainerGap(32, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addComponent(label1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(dealerOptions, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(plsSelectDealer, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGap(66)
							.addComponent(manageInv, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(74, Short.MAX_VALUE))
				);
				contentPane.setLayout(gl_contentPane);			
	}
	
	private void addListeners(){
		 //function for button click
		manageInv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//get the selected dealerName
				int i = dealerOptions.getSelectedIndex();
				selectedName = dealerOptions.getItemAt(i);
				
				//open the Screen #2, exit Screen #1
				new Screen_CarList();
			}
		});
		
	}
	
	private void makeItVisible(){
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
	}
	
	public static void main(String[] args) {
		new Screen_Main();
	}

}
