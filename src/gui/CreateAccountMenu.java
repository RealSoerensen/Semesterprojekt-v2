package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PersonController;
import model.Address;
import model.Person;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;

public class CreateAccountMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTextField textFieldFirstName;
	private final JTextField textFieldLastName;
	private final JTextField textFieldEmail;
	private final JTextField textFieldPhone;
	private final JPasswordField passwordFieldPassword;
	private final JTextField textFieldSSN;
	private final JTextField textFieldPostalCode;
	private final JTextField textFieldCity;
	private final JTextField textFieldRoadName;
	private final JTextField textFieldRoadNumber;
	private final JPasswordField passwordFieldConfirmPassword;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			CreateAccountMenu dialog = new CreateAccountMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateAccountMenu() {
		setBounds(100, 100, 400, 580);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(178, 49, 194, 20);
		contentPanel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(178, 80, 194, 20);
		contentPanel.add(textFieldLastName);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(178, 111, 194, 20);
		contentPanel.add(textFieldEmail);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(178, 142, 194, 20);
		contentPanel.add(textFieldPhone);
		
		JLabel lblFirstName = new JLabel("Fornavn");
		lblFirstName.setBounds(10, 52, 105, 14);
		contentPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Efternavn");
		lblLastName.setBounds(10, 83, 105, 14);
		contentPanel.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 114, 105, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Telefon");
		lblPhone.setBounds(10, 145, 105, 14);
		contentPanel.add(lblPhone);
		
		JLabel lblPassword = new JLabel("Adgangskode");
		lblPassword.setBounds(10, 176, 105, 14);
		contentPanel.add(lblPassword);

		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(178, 176, 194, 20);
		contentPanel.add(passwordFieldPassword);

		JLabel lblConfirmPassword = new JLabel("Bekræft Adgangskode");
		lblConfirmPassword.setBounds(10, 207, 150, 14);
		contentPanel.add(lblConfirmPassword);
		
		passwordFieldConfirmPassword = new JPasswordField();
		passwordFieldConfirmPassword.setBounds(178, 207, 194, 20);
		contentPanel.add(passwordFieldConfirmPassword);
		
		textFieldSSN = new JTextField();
		textFieldSSN.setColumns(10);
		textFieldSSN.setBounds(178, 235, 194, 20);
		contentPanel.add(textFieldSSN);
		
		JLabel lblSSN = new JLabel("CPR-Nummer");
		lblSSN.setBounds(10, 235, 150, 14);
		contentPanel.add(lblSSN);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 301, 350, 2);
		contentPanel.add(separator);
		
		JLabel lblPostalCode = new JLabel("Postnummer");
		lblPostalCode.setBounds(10, 354, 105, 14);
		contentPanel.add(lblPostalCode);
		
		JLabel lblCity = new JLabel("By");
		lblCity.setBounds(10, 385, 105, 14);
		contentPanel.add(lblCity);
		
		JLabel lblRoadName = new JLabel("Vejnavn");
		lblRoadName.setBounds(10, 417, 105, 14);
		contentPanel.add(lblRoadName);
		
		JLabel lblRoadNumber = new JLabel("Vejnummer");
		lblRoadNumber.setBounds(10, 448, 105, 14);
		contentPanel.add(lblRoadNumber);
		
		JLabel lblCreateUserText = new JLabel("Opret Bruger");
		lblCreateUserText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCreateUserText.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateUserText.setBounds(10, 11, 362, 27);
		contentPanel.add(lblCreateUserText);
		
		JLabel lblAddressText = new JLabel("Adresse");
		lblAddressText.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddressText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddressText.setBounds(10, 320, 362, 20);
		contentPanel.add(lblAddressText);
		
		textFieldPostalCode = new JTextField();
		textFieldPostalCode.setColumns(10);
		textFieldPostalCode.setBounds(178, 351, 194, 20);
		contentPanel.add(textFieldPostalCode);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(178, 382, 194, 20);
		contentPanel.add(textFieldCity);
		
		textFieldRoadName = new JTextField();
		textFieldRoadName.setColumns(10);
		textFieldRoadName.setBounds(178, 413, 194, 20);
		contentPanel.add(textFieldRoadName);
		
		textFieldRoadNumber = new JTextField();
		textFieldRoadNumber.setColumns(10);
		textFieldRoadNumber.setBounds(178, 448, 194, 20);
		contentPanel.add(textFieldRoadNumber);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e -> createAccount());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> {
					new LoginMenu().run();
					dispose();
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void createAccount() {
		String errorMessage = "Følgende fejl findes:";
		boolean viableAccount = true;
		PersonController personController = new PersonController();
		
		String firstName = textFieldFirstName.getText();
		String lastName = textFieldLastName.getText();
		String email = textFieldEmail.getText();
		String phoneNumber = textFieldPhone.getText();
		String password = String.valueOf(passwordFieldPassword.getPassword());
		String confirmPassword = String.valueOf(passwordFieldConfirmPassword.getPassword());
		String ssn = textFieldSSN.getText();
		String zipcode = textFieldPostalCode.getText();
		String city = textFieldCity.getText();
		String houseNumber = textFieldRoadNumber.getText();
		String street = textFieldRoadName.getText();
		
		if(!password.equals(confirmPassword)) {
			errorMessage += "\nAdgangskoderne er ikke ens";
			viableAccount = false;
		}
		if(ssn.isEmpty() || ssn.contains(" ")) {
			errorMessage += "\nDit CPR-Nummer er ugyldidgt";
		} 
		else if(!personController.isSsnUnique(Long.parseLong(ssn))) {
			errorMessage += "\nDu har allerede en bruger";
			viableAccount = false;
		}
		if(firstName.isEmpty() || firstName.length() > 20) {
			errorMessage += "\nDit navn er ugyldidgt";
			viableAccount = false;
		}
		if(lastName.isEmpty() || lastName.length() > 32) {
			errorMessage += "\nDit efternavn er ugyldigt";
			viableAccount = false;
		}
		if(email.length() > 50 || !email.contains("@")) {
			errorMessage += "\nDin email er ugyldig";
			viableAccount = false;
		}
		if(phoneNumber.isEmpty() || phoneNumber.length() > 10) {
			errorMessage += "\nDit telefonnummer er ugyldigt";
			viableAccount = false;
		}
		else if(isStringNotNumber(phoneNumber)) {
			errorMessage += "\nDit telefonnummer er ugyldigt";
			viableAccount = false;
		}
		if(password.length() < 8 || password.length() > 16) {
			errorMessage += "\nDit kodeord er ugyldigt. Længden skal være på mindst 8 og højst 16";
			viableAccount = false;
		}
		if(zipcode.isEmpty() || zipcode.length() > 16) {
			errorMessage += "\nUgyldig Post nummer";
			viableAccount = false;
		} else if(isStringNotNumber(zipcode)) {
			errorMessage += "\nUgyldig Post nummer";
			viableAccount = false;
		}
		if(city.isEmpty() || city.length() > 24) {
			errorMessage += "\nUgyldig by navn";
			viableAccount = false;
		}
		if(street.isEmpty() || street.length() > 32) {
			errorMessage += "\nUgyldig vej navn";
			viableAccount = false;
		}
		if(houseNumber.isEmpty() || houseNumber.length() > 8) {
			errorMessage += "\nUgyldig vej nummer";
			viableAccount = false;
		}
		if(viableAccount) {
			Address address = new Address(zipcode, city, street, houseNumber);
			Person person = new Person(firstName, lastName, address, email, phoneNumber, 1, password, Long.parseLong(ssn));
			try {
				if (personController.createPersonWithAddress(person, address)) {
					JOptionPane.showMessageDialog(contentPanel, "Bruger oprettet");
				} else {
					JOptionPane.showMessageDialog(contentPanel, "Bruger ikke oprettet");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			new LoginMenu().run();
			dispose();
		} else {
			JOptionPane.showMessageDialog(contentPanel, errorMessage);
		}
	}
	
	private boolean isStringNotNumber(String string) {
		boolean isStringNumber = true;
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			isStringNumber = false;
		}
		return !isStringNumber;
	}
}
