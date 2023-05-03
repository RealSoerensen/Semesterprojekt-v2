package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CreateUserWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JTextField textFieldPostalCode;
	private JTextField textFieldCity;
	private JTextField textFieldRoadName;
	private JTextField textFieldRoadNumber;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			CreateUserWindow dialog = new CreateUserWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateUserWindow() {
		setBounds(100, 100, 398, 501);
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
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(178, 173, 194, 20);
		contentPanel.add(textFieldUsername);
		
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
		
		JLabel lblUsername = new JLabel("Brugernavn");
		lblUsername.setBounds(10, 176, 105, 14);
		contentPanel.add(lblUsername);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(178, 204, 194, 20);
		contentPanel.add(passwordFieldPassword);
		
		JLabel lblPassword = new JLabel("Adgangskode");
		lblPassword.setBounds(10, 207, 105, 14);
		contentPanel.add(lblPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 235, 350, 2);
		contentPanel.add(separator);
		
		JLabel lblPostalCode = new JLabel("Postnummer");
		lblPostalCode.setBounds(10, 292, 105, 14);
		contentPanel.add(lblPostalCode);
		
		JLabel lblCity = new JLabel("By");
		lblCity.setBounds(10, 323, 105, 14);
		contentPanel.add(lblCity);
		
		JLabel lblRoadName = new JLabel("Vejnavn");
		lblRoadName.setBounds(10, 354, 105, 14);
		contentPanel.add(lblRoadName);
		
		JLabel lblRoadNumber = new JLabel("Vejnummer");
		lblRoadNumber.setBounds(10, 385, 105, 14);
		contentPanel.add(lblRoadNumber);
		
		JLabel lblCreateUserText = new JLabel("Opret Bruger");
		lblCreateUserText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCreateUserText.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateUserText.setBounds(10, 11, 362, 27);
		contentPanel.add(lblCreateUserText);
		
		JLabel lblAddressText = new JLabel("Adresse");
		lblAddressText.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddressText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddressText.setBounds(10, 246, 362, 20);
		contentPanel.add(lblAddressText);
		
		textFieldPostalCode = new JTextField();
		textFieldPostalCode.setColumns(10);
		textFieldPostalCode.setBounds(178, 289, 194, 20);
		contentPanel.add(textFieldPostalCode);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(178, 320, 194, 20);
		contentPanel.add(textFieldCity);
		
		textFieldRoadName = new JTextField();
		textFieldRoadName.setColumns(10);
		textFieldRoadName.setBounds(178, 351, 194, 20);
		contentPanel.add(textFieldRoadName);
		
		textFieldRoadNumber = new JTextField();
		textFieldRoadNumber.setColumns(10);
		textFieldRoadNumber.setBounds(178, 382, 194, 20);
		contentPanel.add(textFieldRoadNumber);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
