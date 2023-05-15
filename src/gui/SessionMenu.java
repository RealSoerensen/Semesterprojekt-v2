package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;

public class SessionMenu extends JFrame {

	private JPanel contentPanel;

	/**
	 * Create the frame.
	 */
	public SessionMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 427);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 454, 366);
		contentPanel.add(scrollPane);
		
		JButton btnViewInfo = new JButton("Se Oplysninger");
		btnViewInfo.setBounds(474, 11, 146, 39);
		contentPanel.add(btnViewInfo);
		
		JButton btnCloseWindow = new JButton("Luk Vindue");
		btnCloseWindow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCloseWindow.setBounds(474, 311, 146, 66);
		contentPanel.add(btnCloseWindow);
		
		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(463, 137, 167, 163);
		contentPanel.add(panelAdmin);
		panelAdmin.setLayout(null);
		
		JButton btnCreateNewSession = new JButton("Opret Ny Session");
		btnCreateNewSession.setBounds(10, 11, 146, 39);
		panelAdmin.add(btnCreateNewSession);
		
		JButton btnEditSession = new JButton("Rediger Session");
		btnEditSession.setBounds(10, 61, 146, 39);
		panelAdmin.add(btnEditSession);
		
		JButton btnDeleteSession = new JButton("Slet Session");
		btnDeleteSession.setBounds(10, 111, 146, 39);
		panelAdmin.add(btnDeleteSession);
	}
}
