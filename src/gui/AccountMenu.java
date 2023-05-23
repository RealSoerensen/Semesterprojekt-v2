package gui;

import controller.LoginController;
import model.Person;

import javax.swing.*;

import java.awt.Font;

public class AccountMenu extends JPanel {

	final Person user = LoginController.getInstance().getPerson();
	private final MainMenu mainMenu;

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
		
		JLabel lblEmailText = new JLabel("Email:");
		lblEmailText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmailText.setBounds(10, 136, 50, 20);
		add(lblEmailText);
		
		JLabel lblPhoneText = new JLabel("Telefon:");
		lblPhoneText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhoneText.setBounds(10, 198, 252, 20);
		add(lblPhoneText);
		
		JLabel lblAddressText = new JLabel("Adresse");
		lblAddressText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddressText.setBounds(10, 256, 101, 20);
		add(lblAddressText);
		
		JLabel lblPostalCode = new JLabel(user.getAddress().getZipCode() + " " + user.getAddress().getCity() + " " + user.getAddress().getStreet() + ", " + user.getAddress().getHouseNumber());
		lblPostalCode.setVerticalAlignment(SwingConstants.TOP);
		lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostalCode.setBounds(10, 287, 606, 52);
		add(lblPostalCode);
		
		JLabel lblName = new JLabel(user.getFirstName() + " " + user.getLastName());
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(10, 105, 606, 20);
		add(lblName);
		
		JLabel lblEmail = new JLabel(user.getEmail());
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 167, 606, 20);
		add(lblEmail);
		
		JLabel lblPhone = new JLabel(user.getPhoneNumber());
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(10, 225, 606, 20);
		add(lblPhone);
		
		JButton btnEditInfo = new JButton("Rediger Konto");
		btnEditInfo.addActionListener(e -> {
			EditAccountMenu editAccountMenu;
			editAccountMenu = new EditAccountMenu(mainMenu, user, true);
			mainMenu.mainPanel.add(editAccountMenu, "edit account panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "edit account panel");
		});
		btnEditInfo.setBounds(293, 364, 129, 23);
		add(btnEditInfo);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel"));
		btnBack.setBounds(154, 365, 129, 23);
		add(btnBack);
	}
}
