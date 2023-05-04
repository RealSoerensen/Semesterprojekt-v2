package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class EditAccountMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public EditAccountMenu() {
		setLayout(null);
		
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
		
		JButton btnDoneEditInfo = new JButton("Færdiggør Redigering");
		btnDoneEditInfo.setBounds(225, 427, 176, 65);
		add(btnDoneEditInfo);
		
		JButton btnEditName = new JButton("Rediger Navn");
		btnEditName.setBounds(10, 113, 131, 39);
		add(btnEditName);
		
		JButton btnEditEmail = new JButton("Rediger Email");
		btnEditEmail.setBounds(10, 245, 131, 39);
		add(btnEditEmail);
		
		JButton btnEditPhone = new JButton("Rediger Telefon");
		btnEditPhone.setBounds(10, 364, 131, 39);
		add(btnEditPhone);
		
		JButton btnEditAddress = new JButton("Rediger Adresse");
		btnEditAddress.setBounds(318, 295, 131, 39);
		add(btnEditAddress);

	}
}
