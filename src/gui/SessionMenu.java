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
		setBounds(100, 100, 646, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 454, 325);
		contentPane.add(scrollPane);
		
		JButton btnMarkAbsent = new JButton("Meld afbud");
		btnMarkAbsent.setBounds(474, 11, 146, 39);
		contentPane.add(btnMarkAbsent);
		
		JButton btnCloseWindow = new JButton("Luk Vindue");
		btnCloseWindow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCloseWindow.setBounds(474, 270, 146, 66);
		contentPane.add(btnCloseWindow);
	}
}
