package gui;

import model.Course;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.*;

public class SessionMenu extends JPanel {
	
	/**
	 * Create the frame.
	 */
	public SessionMenu(MainMenu mainMenu, Course course) {
		setLayout(null);
		setSize(626, 515);

		JScrollPane scrollPaneCourses = new JScrollPane();
		scrollPaneCourses.setBounds(10, 11, 410, 492);
		add(scrollPaneCourses);
		
		JButton btnViewInfo = new JButton("Se Oplysninger");
		btnViewInfo.addActionListener(e -> {
		});
		btnViewInfo.setBounds(430, 111, 186, 39);
		add(btnViewInfo);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(430, 458, 186, 46);
		add(btnBack);
		
		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(430, 284, 186, 163);
		add(panelAdmin);
		panelAdmin.setLayout(null);
		
		JButton btnCreateNewSession = new JButton("Opret Ny Session");
		btnCreateNewSession.addActionListener(e -> {
		});
		btnCreateNewSession.setBounds(10, 11, 166, 39);
		panelAdmin.add(btnCreateNewSession);
		
		JButton btnEditSession = new JButton("Rediger Session");
		btnEditSession.addActionListener(e -> {
		});
		btnEditSession.setBounds(10, 61, 166, 39);
		panelAdmin.add(btnEditSession);
		
		JButton btnDeleteSession = new JButton("Slet Session");
		btnDeleteSession.addActionListener(e -> {
		});
		btnDeleteSession.setBounds(10, 111, 166, 39);
		panelAdmin.add(btnDeleteSession);
		
		JButton btnJoinSession = new JButton("Tilmeld session");
		btnJoinSession.addActionListener(e -> {
		});
		btnJoinSession.setBounds(430, 11, 186, 39);
		add(btnJoinSession);
		
		JButton btnLeaveSession = new JButton("Meld afbud");
		btnLeaveSession.addActionListener(e -> {
		});
		btnLeaveSession.setBounds(430, 61, 186, 39);
		add(btnLeaveSession);
	}
}
