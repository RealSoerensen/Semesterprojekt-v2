package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountMenu extends JPanel {

//	private CardLayout cardLayout = new CardLayout();
//	private EditAccountMenu editAccountMenu = new EditAccountMenu();
//	public static final String EDIT_ACCOUNT_PANEL = "edit account panel";
//	public static final String ACCOUNT_PANEL = "account panel";
	
	/**
	 * Create the panel.
	 */
	public AccountMenu() {
		setLayout(null);
//		add(editAccountMenu, EDIT_ACCOUNT_PANEL);
//		cardLayout.show(this, ACCOUNT_PANEL);
		
		JLabel lblYourInformation = new JLabel("Dine Oplysninger");
		lblYourInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourInformation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblYourInformation.setBounds(10, 11, 600, 39);
		add(lblYourInformation);
		
		JLabel lblNameText = new JLabel("Navn:");
		lblNameText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameText.setBounds(10, 82, 50, 20);
		add(lblNameText);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 61, 600, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(311, 61, 2, 355);
		add(separator_1);
		
		JLabel lblEmailText = new JLabel("Email:");
		lblEmailText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmailText.setBounds(10, 215, 50, 20);
		add(lblEmailText);
		
		JLabel lblPhoneText = new JLabel("Telefon:");
		lblPhoneText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneText.setBounds(10, 333, 62, 20);
		add(lblPhoneText);
		
		JLabel lblAddressText = new JLabel("Adresse");
		lblAddressText.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddressText.setBounds(323, 82, 101, 20);
		add(lblAddressText);
		
		JLabel lblPostalCode = new JLabel("0000");
		lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostalCode.setBounds(323, 202, 50, 20);
		add(lblPostalCode);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCity.setBounds(383, 202, 227, 20);
		add(lblCity);
		
		JLabel lblRoadname = new JLabel("Road");
		lblRoadname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRoadname.setBounds(323, 233, 285, 20);
		add(lblRoadname);
		
		JLabel lblHouseNumber = new JLabel("Number");
		lblHouseNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHouseNumber.setBounds(323, 264, 287, 20);
		add(lblHouseNumber);
		
		JLabel lblName = new JLabel("Anders Andersen");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(70, 82, 231, 20);
		add(lblName);
		
		JLabel lblEmail = new JLabel("AndersAndersen@gmail.com");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(70, 215, 231, 20);
		add(lblEmail);
		
		JLabel lblPhone = new JLabel("11111111");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(82, 333, 177, 20);
		add(lblPhone);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 414, 600, 2);
		add(separator_2);
		
		JButton btnEditInfo = new JButton("Rediger Oplysninger");
		btnEditInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				switchPanelToEditAccountMenu();
			}
		});
		btnEditInfo.setBounds(225, 427, 176, 65);
		add(btnEditInfo);

	}
	
//	public void switchPanelToEditAccountMenu() {
//		
//		cardLayout.show(this, EDIT_ACCOUNT_PANEL);
//	}
}
