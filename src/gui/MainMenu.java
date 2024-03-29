package gui;

import controller.LoginController;
import model.Person;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class MainMenu extends JFrame {
	public final JPanel mainPanel;
	public final Person user = LoginController.getInstance().getPerson();
	public final CardLayout cardLayout = new CardLayout();
	public static final String ACCOUNT_PANEL = "account panel";
	public static final String ACCOUNT_MANAGER_PANEL = "account manager panel";
	public static final String COURSE_PANEL = "course panel";
	private final JLabel lblTitle;
	public final JPanel menuPanel;
	public final JPanel panelMainWindowAdmin;
	private final JButton btnMyAccount;
	private final JButton btnAccounts;
	private final JButton btnCourses;

	/**
	 * Launch the application.
	 */
	public void run() {
		MainMenu frame;
		try {
			frame = new MainMenu();
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
	public MainMenu() {
		setName("Bruh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 749);
		JPanel contentPanel = new JPanel();
		contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(192, 192, 192));
		titlePanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		titlePanel.setBounds(10, 11, 793, 84);
		contentPanel.add(titlePanel);
		titlePanel.setLayout(null);

		lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 773, 50);
		titlePanel.add(lblTitle);

		menuPanel = new JPanel();
		menuPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		menuPanel.setBackground(new Color(192, 192, 192));
		menuPanel.setBounds(10, 106, 164, 514);
		contentPanel.add(menuPanel);
		menuPanel.setLayout(null);

		btnCourses = new JButton("Kurser");
		btnCourses.addActionListener(e -> switchPanelToCourseMenu(contentPanel));
		btnCourses.setBounds(10, 86, 144, 37);
		menuPanel.add(btnCourses);

		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMenu.setBounds(10, 52, 144, 23);
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

		String email = "gokaalborgmb@outlook.com";
		JLabel lblContactEmail = new JLabel(email);
		lblContactEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().mail(new URI("mailto:" + email + "?subject=Hello"));
				} catch (URISyntaxException | IOException ex) {
					JOptionPane.showMessageDialog(null, "Kunne ikke åbne mail programmet");
				}
			}
		});
		lblContactEmail.setForeground(Color.WHITE);
		lblContactEmail.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblContactEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panelContactInfo.add(lblContactEmail, BorderLayout.NORTH);

		JLabel lblContactPhone = new JLabel("+45 98 17 84 36");
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

		JLabel lblLoggedInAs = new JLabel(LoginController.getInstance().getPerson().getFirstName());
		lblLoggedInAs.setBounds(10, 36, 144, 14);
		menuPanel.add(lblLoggedInAs);

		JButton btnLogOut = new JButton("Log ud");
		btnLogOut.addActionListener(e -> {
			try {
				new LoginMenu().run();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			dispose();
		});
		btnLogOut.setBounds(10, 370, 144, 23);
		menuPanel.add(btnLogOut);

		panelMainWindowAdmin = new JPanel();
		panelMainWindowAdmin.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelMainWindowAdmin.setBackground(new Color(192, 192, 192));
		panelMainWindowAdmin.setBounds(0, 182, 164, 177);
		menuPanel.add(panelMainWindowAdmin);
		panelMainWindowAdmin.setLayout(null);
		panelMainWindowAdmin.setVisible(user.getRole() > 2);

		btnAccounts = new JButton("Alle Konti");
		btnAccounts.addActionListener(e -> switchPanelToAccountManagerMenu(contentPanel));
		btnAccounts.setBounds(10, 39, 144, 37);
		panelMainWindowAdmin.add(btnAccounts);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setBounds(10, 11, 144, 17);
		panelMainWindowAdmin.add(lblAdmin);
		

		btnMyAccount = new JButton("Min Konto");
		btnMyAccount.addActionListener(e -> switchPanelToAccountMenu());
		btnMyAccount.setBounds(10, 134, 144, 37);
		menuPanel.add(btnMyAccount);

		JPanel panelFill = new JPanel();
		panelFill.setBounds(184, 106, 619, 514);
		contentPanel.add(panelFill);

		mainPanel = panelFill;
		mainPanel.setLayout(cardLayout);
		mainPanel.add(new JPanel());

		switchPanelToCourseMenu(contentPanel);
		
		JPanel tipPanel = new JPanel();
		tipPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		tipPanel.setBackground(new Color(192, 192, 192));
		tipPanel.setBounds(10, 631, 793, 68);
		contentPanel.add(tipPanel);
		tipPanel.setLayout(null);
		
		JLabel lblTip = new JLabel("Tip");
		lblTip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTip.setBounds(10, 11, 773, 46);
		tipPanel.add(lblTip);
		
		ArthritisTipWorker worker = new ArthritisTipWorker(lblTip);
        worker.execute();
	}

	public void switchPanelToCourseMenu(JPanel contentPanel) {
		enableButtons();
		btnCourses.setEnabled(false);
		lblTitle.setText("Kurser");
		contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		CourseMenu courseMenu;
		try {
			courseMenu = new CourseMenu(this);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Der skete en fejl ved hentning af kurser.");
			return;
		}

		mainPanel.add(courseMenu, COURSE_PANEL);
		cardLayout.show(mainPanel, COURSE_PANEL);
		contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public void switchPanelToAccountMenu() {
		enableButtons();
		btnMyAccount.setEnabled(false);
		lblTitle.setText("Min Konto");
		AccountMenu accountMenu = new AccountMenu(this);
		mainPanel.add(accountMenu, ACCOUNT_PANEL);
		cardLayout.show(mainPanel, ACCOUNT_PANEL);
	}

	public void switchPanelToAccountManagerMenu(JPanel contentPanel) {
		enableButtons();
		btnAccounts.setEnabled(false);
		contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		lblTitle.setText("Alle Konti");
		AccountManagerMenu accountManagerMenu;
		try {
			accountManagerMenu = new AccountManagerMenu(this);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Der skete en fejl ved hentning af kontoer.");
			return;
		}
		mainPanel.add(accountManagerMenu, ACCOUNT_MANAGER_PANEL);
		cardLayout.show(mainPanel, ACCOUNT_MANAGER_PANEL);
		contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}
	
	public void setLblTitle(String newTitle) {
		lblTitle.setText(newTitle);
	}
	
	private void enableButtons() {
		btnMyAccount.setEnabled(true);
		btnAccounts.setEnabled(true);
		btnCourses.setEnabled(true);
	}
}
