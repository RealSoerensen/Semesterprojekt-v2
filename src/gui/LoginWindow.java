package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PersonController;
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

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			LoginWindow frame = new LoginWindow();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(179, 33, 182, 25);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 85, 182, 25);
		contentPane.add(passwordField);
		
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
		contentPane.add(btnLogin);
		
		JButton btnCreateUser = new JButton("Opret Bruger");
		btnCreateUser.addActionListener(e -> createUser());
		btnCreateUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCreateUser.setBounds(217, 202, 101, 25);
		contentPane.add(btnCreateUser);
		
		JLabel lblUserID = new JLabel("BRUGERNAVN:");
		lblUserID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserID.setBounds(10, 31, 134, 25);
		contentPane.add(lblUserID);
		
		JLabel lblPassword = new JLabel("ADGANGSKODE:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 83, 134, 25);
		contentPane.add(lblPassword);
	}


	private void createUser() {
		// TODO Auto-generated method stub
		
	}

	private void logIn() throws SQLException {
	// TODO Auto-generated method stub
		PersonController personController = new PersonController(PersonContainer.getInstance());
		PersonContainer pc = PersonContainer.getInstance();
		List<Person> persons = pc.getAll();
		String usernameResult = textFieldUsername.getText();
		String passwordResult = Arrays.toString(passwordField.getPassword());
		
		for(Person p : persons) {
			if(usernameResult.equalsIgnoreCase(p.getUsername()) && passwordResult.equalsIgnoreCase(p.getPassword())) {
				System.out.println("Logged in as: " + p.getFirstName());
				
			}
		}
		
	}
}
