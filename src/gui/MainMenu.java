package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.sql.SQLException;

public class MainMenu extends JFrame {

	private JPanel contentPanel;
	private JPanel panelFill;
	private MainMenu frame;
	private JPanel mainPanel;
	private CardLayout cardLayout = new CardLayout();
	private AccountMenu accountMenu = new AccountMenu();
	private AccountManagerMenu accountManagerMenu = new AccountManagerMenu();
	private CourseMenu courseMenu = new CourseMenu();

	public static final String ACCOUNT_PANEL = "account panel";
	public static final String ACCOUNT_MANAGER_PANEL = "account manager panel";
	public static final String COURSE_PANEL = "course panel";

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			MainMenu frame = new MainMenu();
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setTitle("Aalborg Gigtforening");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() throws SQLException {
		setName("Bruh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 670);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(192, 192, 192));
		titlePanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		titlePanel.setBounds(10, 23, 793, 72);
		contentPanel.add(titlePanel);
		titlePanel.setLayout(null);

		JLabel lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 773, 50);
		titlePanel.add(lblTitle);

		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		menuPanel.setBackground(new Color(192, 192, 192));
		menuPanel.setBounds(10, 106, 164, 514);
		contentPanel.add(menuPanel);
		menuPanel.setLayout(null);

		JButton btnCourses = new JButton("Kurser");
		btnCourses.addActionListener(e -> lblTitle.setText("Kurser"));
		btnCourses.addActionListener(e -> switchPanelToCourseMenu());
		btnCourses.setBounds(10, 96, 144, 37);
		menuPanel.add(btnCourses);

		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblMenu.setBounds(10, 36, 144, 49);
		menuPanel.add(lblMenu);

		JPanel panelContact = new JPanel();
		panelContact.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelContact.setBackground(new Color(220, 20, 60));
		panelContact.setBounds(10, 412, 144, 91);
		menuPanel.add(panelContact);
		panelContact.setLayout(new BorderLayout(0, 0));

		JLabel lblContactText = new JLabel("Kontakt");
		lblContactText.setForeground(Color.WHITE);
		lblContactText.setHorizontalAlignment(SwingConstants.CENTER);
		panelContact.add(lblContactText, BorderLayout.NORTH);

		JPanel panelContactInfo = new JPanel();
		panelContactInfo.setBackground(new Color(220, 20, 60));
		panelContact.add(panelContactInfo, BorderLayout.CENTER);
		panelContactInfo.setLayout(new BorderLayout(0, 0));

		JLabel lblContactEmail = new JLabel("gokaalborgmb@outlook.com");
		lblContactEmail.setForeground(Color.WHITE);
		lblContactEmail.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblContactEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panelContactInfo.add(lblContactEmail, BorderLayout.NORTH);

		JLabel lblContactPhone = new JLabel("98 17 84 36");
		lblContactPhone.setForeground(Color.WHITE);
		lblContactPhone.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblContactPhone.setHorizontalAlignment(SwingConstants.CENTER);
		panelContactInfo.add(lblContactPhone, BorderLayout.CENTER);

		JLabel lblContactAddress = new JLabel("Lerumbakken 11");
		lblContactAddress.setForeground(Color.WHITE);
		lblContactAddress.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblContactAddress.setHorizontalAlignment(SwingConstants.CENTER);
		panelContactInfo.add(lblContactAddress, BorderLayout.SOUTH);

		JLabel lblLoggedInAsText = new JLabel("Logget ind som:");
		lblLoggedInAsText.setBounds(10, 11, 144, 14);
		menuPanel.add(lblLoggedInAsText);

		JLabel lblLoggedInAs = new JLabel("");
		lblLoggedInAs.setBounds(10, 36, 144, 37);
		menuPanel.add(lblLoggedInAs);

		JButton btnLogOut = new JButton("Log ud");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginMenu().run();
				dispose();
			}
		});
		btnLogOut.setBounds(10, 370, 144, 23);
		menuPanel.add(btnLogOut);

		JPanel panelMainWindowAdmin = new JPanel();
		panelMainWindowAdmin.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelMainWindowAdmin.setBackground(new Color(192, 192, 192));
		panelMainWindowAdmin.setBounds(0, 205, 164, 154);
		menuPanel.add(panelMainWindowAdmin);
		panelMainWindowAdmin.setLayout(null);

		JButton btnAccounts = new JButton("Alle Konti");
		btnAccounts.setBounds(10, 39, 144, 37);
		panelMainWindowAdmin.add(btnAccounts);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setBounds(10, 11, 144, 17);
		panelMainWindowAdmin.add(lblAdmin);

		JButton btnMyAccount = new JButton("Min Konto");
		btnMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("Min Konto");
				switchPanelToAccountMenu();
			}
		});
		btnMyAccount.setBounds(10, 144, 144, 37);
		menuPanel.add(btnMyAccount);
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("Alle Konti");
				switchPanelToAccountManagerMenu();
			}
		});

		JPanel panelFill = new JPanel();
		panelFill.setBounds(184, 106, 619, 514);
		contentPanel.add(panelFill);
		mainPanel = panelFill;
		mainPanel.setLayout(cardLayout);
		mainPanel.add(new JPanel());
		mainPanel.add(courseMenu, COURSE_PANEL);
		mainPanel.add(accountMenu, ACCOUNT_PANEL);
		mainPanel.add(accountManagerMenu, ACCOUNT_MANAGER_PANEL);
	}

	public void switchPanelToCourseMenu() {
		cardLayout.show(mainPanel, COURSE_PANEL);
	}

	public void switchPanelToAccountMenu() {
		cardLayout.show(mainPanel, ACCOUNT_PANEL);

	}

	public void switchPanelToAccountManagerMenu() {
		cardLayout.show(mainPanel, ACCOUNT_MANAGER_PANEL);
	}
}
