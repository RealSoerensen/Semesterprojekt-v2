package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class CourseMenu extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public CourseMenu() {
		setLayout(null);
		
		JScrollPane scrollPaneCourses = new JScrollPane();
		scrollPaneCourses.setBounds(10, 11, 410, 492);
		add(scrollPaneCourses);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Test", "Test2", "Test3", "Test4"
			}
		));
		scrollPaneCourses.setViewportView(table);
		
		JButton btnLeaveCourse = new JButton("Frameld Kursus");
		btnLeaveCourse.setBounds(430, 197, 179, 33);
		add(btnLeaveCourse);
		
		JButton btnEnrollCourse = new JButton("Tilmeld Kursus");
		btnEnrollCourse.setToolTipText("");
		btnEnrollCourse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEnrollCourse.setForeground(UIManager.getColor("Button.background"));
		btnEnrollCourse.setBackground(UIManager.getColor("Button.background"));
		btnEnrollCourse.setBounds(430, 109, 179, 33);
		add(btnEnrollCourse);
		
		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(419, 336, 200, 167);
		add(panelAdmin);
		panelAdmin.setLayout(null);
		
		JButton btnEditCourse = new JButton("Rediger Kursus");
		btnEditCourse.setBounds(10, 52, 180, 33);
		panelAdmin.add(btnEditCourse);
		
		JButton btnCreateCourse = new JButton("Opret Kursus");
		btnCreateCourse.setBounds(10, 11, 180, 33);
		panelAdmin.add(btnCreateCourse);
		
		JButton btnDeleteCourse = new JButton("Slet Kursus");
		btnDeleteCourse.setBounds(10, 96, 180, 33);
		panelAdmin.add(btnDeleteCourse);
		
		JButton btnViewSessions = new JButton("Se Sessioner");
		btnViewSessions.setBounds(430, 153, 179, 33);
		add(btnViewSessions);

	}
}
