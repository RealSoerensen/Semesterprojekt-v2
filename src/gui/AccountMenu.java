package gui;

import controller.LoginController;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class AccountMenu extends JPanel {

	LoginController loginController = LoginController.getInstance();

	/**
	 * Create the panel.
	 */
	public AccountMenu() {
		setLayout(null);
		setSize(626, 515);

		JLabel lblYourInformation = new JLabel("Dine Oplysninger");
		lblYourInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourInformation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblYourInformation.setBounds(10, 11, 430, 39);
		add(lblYourInformation);
		
		JLabel lblNameText = new JLabel("Navn:");
		lblNameText.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
		lblEmailText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmailText.setBounds(10, 136, 50, 20);
		add(lblEmailText);
		
		JLabel lblPhoneText = new JLabel("Telefon:");
		lblPhoneText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneText.setBounds(10, 198, 252, 20);
		add(lblPhoneText);
		
		JLabel lblAddressText = new JLabel("Adresse");
		lblAddressText.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddressText.setBounds(284, 78, 101, 20);
		add(lblAddressText);
		
		JLabel lblPostalCode = new JLabel("0000");
		lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostalCode.setBounds(285, 113, 145, 20);
		add(lblPostalCode);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCity.setBounds(284, 144, 146, 20);
		add(lblCity);
		
		JLabel lblRoadname = new JLabel("Road");
		lblRoadname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRoadname.setBounds(284, 175, 146, 20);
		add(lblRoadname);
		
		JLabel lblHouseNumber = new JLabel("Number");
		lblHouseNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHouseNumber.setBounds(284, 206, 146, 20);
		add(lblHouseNumber);
		
		JLabel lblName = new JLabel("Anders Andersen");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(10, 105, 237, 20);
		add(lblName);
		
		JLabel lblEmail = new JLabel("AndersAndersen@gmail.com");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 167, 254, 20);
		add(lblEmail);
		
		JLabel lblPhone = new JLabel("11111111");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(10, 225, 252, 20);
		add(lblPhone);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(-68, 256, 600, 2);
		add(separator_2);
		
		JButton btnEditInfo = new JButton("Rediger Oplysninger");
		btnEditInfo.addActionListener(e -> {
			// TODO: implement edit account
		});
		btnEditInfo.setBounds(224, 266, 129, 23);
		add(btnEditInfo);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			// TODO: Implement back button
		});
		btnBack.setBounds(86, 266, 129, 23);
		add(btnBack);

	}
}
