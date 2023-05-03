package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import controller.PersonController;
import dal.address.AddressContainer;
import dal.person.PersonContainer;
import model.Person;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LoginMenu extends JFrame {

	private JPanel contentPanel;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	private PersonController personController;
	private LoginController loginController;

	private LoginMenu frame;


//	personController = new PersonController(PersonContainer.getInstance(), AddressContainer.getInstance());
	/**
	 * Launch the application.
	 */
	public void run() {
		try {

			frame = new LoginMenu();

			frame.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public LoginMenu() {
		loginController = LoginController.getInstance();
		personController = new PersonController(PersonContainer.getInstance(), AddressContainer.getInstance());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 302);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(179, 33, 182, 25);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(179, 85, 182, 25);
		contentPanel.add(passwordField);

		JButton btnLogin = new JButton("Log ind");
		btnLogin.addActionListener(e -> {
			try {
				logIn();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(217, 138, 101, 53);
		contentPanel.add(btnLogin);

		JButton btnCreateUser = new JButton("Opret Bruger");
		btnCreateUser.addActionListener(e -> createUser());
		btnCreateUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCreateUser.setBounds(217, 202, 101, 25);
		contentPanel.add(btnCreateUser);

		JLabel lblUserID = new JLabel("BRUGERNAVN:");
		lblUserID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserID.setBounds(10, 31, 134, 25);
		contentPanel.add(lblUserID);

		JLabel lblPassword = new JLabel("ADGANGSKODE:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 83, 134, 25);
		contentPanel.add(lblPassword);
	}

	
	
	private void createUser() {
		// TODO Auto-generated method stub
		new CreateAccountMenu().run();
	}

	private void logIn() throws SQLException {
		// TODO Auto-generated method stub
		List<Person> persons = personController.getAllPersons();

		String usernameResult = textFieldUsername.getText();
		String passwordResult = new String(passwordField.getPassword());

		for (Person p : persons) {
			if (usernameResult.equalsIgnoreCase(p.getUsername()) && passwordResult.equalsIgnoreCase(p.getPassword())) {
				loginController.setLoggedInPerson(p);
				System.out.println("Logged in as: " + p.getFirstName());

				new MainMenu().run();
				this.setVisible(false);

			}
			
		}
	}
}
