package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(192, 192, 192));
		titlePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		titlePanel.setBounds(10, 23, 793, 72);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 773, 50);
		titlePanel.add(lblTitle);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		menuPanel.setBackground(new Color(192, 192, 192));
		menuPanel.setBounds(10, 106, 164, 514);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		JButton btnCourses = new JButton("Kurser");
		btnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("Kurser");
			}
		});
		btnCourses.setBounds(10, 144, 144, 37);
		menuPanel.add(btnCourses);
		
		JButton btnInstructors = new JButton("Intruktører");
		btnInstructors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("Intruktører");
			}
		});
		btnInstructors.setBounds(10, 192, 144, 37);
		menuPanel.add(btnInstructors);
		
		JButton btnMembers = new JButton("Kursister");
		btnMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("Kursister");
			}
		});
		btnMembers.setBounds(10, 240, 144, 37);
		menuPanel.add(btnMembers);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblMenu.setBounds(10, 84, 144, 49);
		menuPanel.add(lblMenu);
		
		JPanel panelContact = new JPanel();
		panelContact.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
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
		btnLogOut.setBounds(10, 370, 144, 23);
		menuPanel.add(btnLogOut);
		
		
		
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(184, 106, 619, 514);
		contentPane.add(contentPanel);
	}
}
