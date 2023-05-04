package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;

public class SessionMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SessionMenu frame = new SessionMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SessionMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 454, 366);
		contentPane.add(scrollPane);
		
		JButton btnViewInfo = new JButton("Se Oplysninger");
		btnViewInfo.setBounds(474, 11, 146, 39);
		contentPane.add(btnViewInfo);
		
		JButton btnCloseWindow = new JButton("Luk Vindue");
		btnCloseWindow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCloseWindow.setBounds(474, 311, 146, 66);
		contentPane.add(btnCloseWindow);
		
		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(463, 137, 167, 163);
		contentPane.add(panelAdmin);
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
