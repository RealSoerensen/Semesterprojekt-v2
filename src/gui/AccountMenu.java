package gui;

import controller.LoginController;
import model.Person;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class AccountMenu extends JPanel {

	Person user = LoginController.getInstance().getPerson();
	private MainMenu mainMenu;

	/**
	 * Create the panel.
	 */
	public AccountMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		initialize();
	}
	
	public void initialize() {
		setLayout(null);
		setSize(626, 515);

		JLabel lblYourInformation = new JLabel("Dine Oplysninger");
		lblYourInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourInformation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblYourInformation.setBounds(10, 11, 430, 39);
		add(lblYourInformation);
		
		JLabel lblNameText = new JLabel("Navn:");
		lblNameText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNameText.setBounds(10, 74, 50, 20);
		add(lblNameText);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 61, 600, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(272, 61, 2, 196);
		add(separator_1);
		
		JLabel lblEmailText = new JLabel("Email:");
		lblEmailText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmailText.setBounds(10, 136, 50, 20);
		add(lblEmailText);
		
		JLabel lblPhoneText = new JLabel("Telefon:");
		lblPhoneText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhoneText.setBounds(10, 198, 252, 20);
		add(lblPhoneText);
		
		JLabel lblAddressText = new JLabel("Adresse");
		lblAddressText.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblAddressText.setBounds(284, 78, 101, 20);
		add(lblAddressText);
		
		JLabel lblPostalCode = new JLabel(user.getAddress().getZipCode() + " " + user.getAddress().getCity() + ", " + user.getAddress().getStreet() + " " + user.getAddress().getHouseNumber());
		lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostalCode.setBounds(285, 113, 145, 20);
		add(lblPostalCode);
		
		JLabel lblName = new JLabel(user.getFirstName() + " " + user.getLastName());
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(10, 105, 237, 20);
		add(lblName);
		
		JLabel lblEmail = new JLabel(user.getEmail());
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 167, 254, 20);
		add(lblEmail);
		
		JLabel lblPhone = new JLabel(user.getPhoneNumber());
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(10, 225, 252, 20);
		add(lblPhone);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(-68, 256, 600, 2);
		add(separator_2);
		
		JButton btnEditInfo = new JButton("Rediger Konto");
		btnEditInfo.addActionListener(e -> mainMenu.cardLayout.show(mainMenu.mainPanel, "edit account panel"));
		btnEditInfo.setBounds(224, 266, 180, 23);
		add(btnEditInfo);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel"));
		btnBack.setBounds(86, 266, 129, 23);
		add(btnBack);
	}
}
