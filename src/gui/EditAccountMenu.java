package gui;

import controller.PersonController;
import dal.address.AddressContainer;
import dal.person.PersonContainer;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditAccountMenu extends JFrame {

	private static EditAccountMenu frame;
	private JPanel contentPanel;
	private static PersonController personController;

	public static void run(Person person) {
		try {
			personController = new PersonController(PersonContainer.getInstance(), AddressContainer.getInstance());
			frame = new EditAccountMenu(person);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setTitle("Aalborg Gigtforening");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the panel.
	 */
	public EditAccountMenu(Person person) {
		setName("Bruh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 548);
		contentPanel = new JPanel();

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblYourInformation = new JLabel("Dine Oplysninger");
		lblYourInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourInformation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblYourInformation.setBounds(10, 11, 600, 39);
		getContentPane().add(lblYourInformation);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 61, 600, 2);
		getContentPane().add(separator);
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(310, 82, 2, 347);
		getContentPane().add(separator_1);
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 427, 600, 2);
		getContentPane().add(separator_2);


		
		JLabel lblFirstNameText = new JLabel("Fornavn:");
		lblFirstNameText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstNameText.setBounds(10, 82, 78, 20);
		getContentPane().add(lblFirstNameText);

		JTextField txtFirstName = new JTextField(person.getFirstName());
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFirstName.setBounds(86, 82, 215, 20);
		getContentPane().add(txtFirstName);

		JLabel lblLastName = new JLabel("Efternavn:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(10, 113, 78, 20);
		getContentPane().add(lblLastName);

		JTextField txtLastName = new JTextField(person.getLastName());
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLastName.setBounds(86, 113, 215, 20);
		getContentPane().add(txtLastName);

		JButton btnEditName = new JButton("Rediger Navn");
		btnEditName.addActionListener(e -> {
			person.setFirstName(txtFirstName.getText());
			person.setLastName(txtLastName.getText());
		});
		btnEditName.setBounds(86, 144, 215, 23);
		getContentPane().add(btnEditName);
		
		JLabel lblEmailText = new JLabel("Email:");
		lblEmailText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmailText.setBounds(10, 215, 50, 20);
		getContentPane().add(lblEmailText);

		JTextField txtEmail = new JTextField(person.getEmail());
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBounds(86, 215, 215, 20);
		getContentPane().add(txtEmail);

		JButton btnEditEmail = new JButton("Rediger Email");
		btnEditEmail.addActionListener(e -> person.setEmail(txtEmail.getText()));
		btnEditEmail.setBounds(86, 246, 214, 23);
		getContentPane().add(btnEditEmail);
		
		JLabel lblPhoneText = new JLabel("Telefon:");
		lblPhoneText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneText.setBounds(10, 333, 66, 20);
		getContentPane().add(lblPhoneText);

		JTextField txtPhone = new JTextField(person.getPhoneNumber());
		txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPhone.setBounds(86, 333, 215, 20);
		getContentPane().add(txtPhone);

		JButton btnEditPhone = new JButton("Rediger Telefon");
		btnEditPhone.addActionListener(e -> person.setPhoneNumber(txtPhone.getText()));
		btnEditPhone.setBounds(86, 364, 214, 23);
		getContentPane().add(btnEditPhone);

		JLabel lblAddressText = new JLabel("Adresse");
		lblAddressText.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddressText.setBounds(323, 82, 285, 39);
		getContentPane().add(lblAddressText);

		JTextField txtPostalCode = new JTextField(person.getAddress().getZipCode());
		txtPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPostalCode.setBounds(323, 171, 285, 20);
		getContentPane().add(txtPostalCode);


		JTextField txtCity = new JTextField(person.getAddress().getCity());
		txtCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCity.setBounds(323, 202, 285, 20);
		getContentPane().add(txtCity);

		JTextField txtRoadname = new JTextField(person.getAddress().getStreet());
		txtRoadname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRoadname.setBounds(323, 233, 285, 20);
		getContentPane().add(txtRoadname);

		JTextField txtHouseNumber = new JTextField(person.getAddress().getHouseNumber());
		txtHouseNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHouseNumber.setBounds(323, 264, 285, 20);
		getContentPane().add(txtHouseNumber);

		JButton btnEditAddress = new JButton("Rediger Adresse");
		btnEditAddress.addActionListener(e -> {
			person.getAddress().setZipCode(txtPostalCode.getText());
			person.getAddress().setCity(txtCity.getText());
			person.getAddress().setStreet(txtRoadname.getText());
			person.getAddress().setHouseNumber(txtHouseNumber.getText());
		});
		btnEditAddress.setBounds(323, 295, 285, 23);
		getContentPane().add(btnEditAddress);
		
		JButton btnDoneEditInfo = new JButton("Færdiggør Redigering");
		btnDoneEditInfo.addActionListener(e -> {
			try {
				personController.updatePerson(person);
				personController.updateAddress(person.getAddress());
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}

			frame.dispose();
		});
		btnDoneEditInfo.setBounds(10, 440, 600, 39);
		getContentPane().add(btnDoneEditInfo);
	}
}
