package gui;

import controller.LoginController;
import controller.PersonController;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditAccountMenu extends JPanel {

	private static PersonController personController;

	/**
	 * Create the panel.
	 */
	public EditAccountMenu(MainMenu mainMenu, Person person) {
		personController = new PersonController();
		
		setName("Bruh");
		setSize(626, 515);
		setLayout(null);

		JLabel lblFirstNameText = new JLabel("Fornavn:");
		lblFirstNameText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstNameText.setBounds(10, 56, 58, 19);
		add(lblFirstNameText);

		JTextField txtFirstName = new JTextField(person.getFirstName());
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFirstName.setBounds(93, 53, 184, 25);
		add(txtFirstName);

		JLabel lblLastName = new JLabel("Efternavn:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(10, 92, 68, 19);
		add(lblLastName);

		JTextField txtLastName = new JTextField(person.getLastName());
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLastName.setBounds(93, 89, 184, 25);
		add(txtLastName);
		
		JLabel lblEmailText = new JLabel("Email:");
		lblEmailText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmailText.setBounds(10, 128, 38, 19);
		add(lblEmailText);

		JTextField txtEmail = new JTextField(person.getEmail());
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBounds(93, 125, 184, 25);
		add(txtEmail);
		
		JLabel lblPhoneText = new JLabel("Telefon:");
		lblPhoneText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneText.setBounds(10, 164, 54, 19);
		add(lblPhoneText);

		JTextField txtPhone = new JTextField(person.getPhoneNumber());
		txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPhone.setBounds(93, 161, 184, 25);
		add(txtPhone);

		JLabel lblAddressText = new JLabel("Adresse");
		lblAddressText.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddressText.setBounds(333, 11, 86, 31);
		add(lblAddressText);

		JTextField txtPostalCode = new JTextField(person.getAddress().getZipCode());
		txtPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPostalCode.setBounds(432, 89, 184, 25);
		add(txtPostalCode);


		JTextField txtCity = new JTextField(person.getAddress().getCity());
		txtCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCity.setBounds(432, 53, 184, 25);
		add(txtCity);

		JTextField txtRoadname = new JTextField(person.getAddress().getStreet());
		txtRoadname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRoadname.setBounds(432, 125, 184, 25);
		add(txtRoadname);

		JTextField txtHouseNumber = new JTextField(person.getAddress().getHouseNumber());
		txtHouseNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHouseNumber.setBounds(432, 161, 184, 25);
		add(txtHouseNumber);
		
		JButton btnDoneEditInfo = new JButton("Gem ændringer");
		btnDoneEditInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDoneEditInfo.addActionListener(e -> {
			try {
				person.setFirstName(txtFirstName.getText());
				person.setLastName(txtLastName.getText());
				person.setEmail(txtEmail.getText());
				person.setPhoneNumber(txtPhone.getText());
				person.getAddress().setCity(txtCity.getText());
				person.getAddress().setHouseNumber(txtHouseNumber.getText());
				person.getAddress().setStreet(txtRoadname.getText());
				person.getAddress().setZipCode(txtPostalCode.getText());
				
				personController.updatePerson(person);
				personController.updateAddress(person.getAddress());

				AccountMenu accountMenu = new AccountMenu(mainMenu);
				mainMenu.mainPanel.add(accountMenu, "account menu panel");
				mainMenu.cardLayout.show(mainMenu.mainPanel, "account menu panel");
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}

			//TODO: implement going back to account menu
		});
		btnDoneEditInfo.setBounds(473, 452, 143, 52);
		add(btnDoneEditInfo);
		
		JLabel lblNewLabel = new JLabel("By: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(333, 58, 46, 14);
		add(lblNewLabel);
		
		JLabel lblPersonligeDetaljer = new JLabel("Personlige Detaljer");
		lblPersonligeDetaljer.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPersonligeDetaljer.setBounds(10, 11, 267, 31);
		add(lblPersonligeDetaljer);
		
		JLabel lblPostnummer = new JLabel("Postnummer:");
		lblPostnummer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostnummer.setBounds(333, 94, 97, 14);
		add(lblPostnummer);
		
		JLabel lblVejnavn = new JLabel("Vejnavn:");
		lblVejnavn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVejnavn.setBounds(333, 130, 97, 14);
		add(lblVejnavn);
		
		JLabel lblHusNummer = new JLabel("Hus nummer:");
		lblHusNummer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHusNummer.setBounds(333, 168, 97, 14);
		add(lblHusNummer);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			mainMenu.switchPanelToAccountMenu();
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(320, 452, 143, 52);
		add(btnBack);
	}
}
