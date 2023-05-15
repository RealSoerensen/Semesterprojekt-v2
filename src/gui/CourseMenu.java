package gui;

import controller.CourseController;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseMenu extends JPanel {
	private JTable table;
	CourseController courseController = new CourseController();

	/**
	 * Create the panel.
	 */
	public CourseMenu() throws SQLException {
		setLayout(null);

		JScrollPane scrollPaneCourses = new JScrollPane();
		scrollPaneCourses.setBounds(10, 11, 410, 492);
		add(scrollPaneCourses);

		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Navn");
		model.addColumn("Beskrivelse");
		model.addColumn("Pris");
		model.addColumn("Tilmeldte");

		List<Course> courses = getAllCourses();

		for (Course course : courses) {
			Object[] row = { course.getName(), course.getDescription(), course.getPrice(),
					courseController.getNumberOfMembersEnrolled(course) };
			model.addRow(row);
		}
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					System.out.println(table.getValueAt(row, 0));
				}
			}
		});
		scrollPaneCourses.setViewportView(table);

		JButton btnLeaveCourse = new JButton("Frameld Kursus");
		btnLeaveCourse.setBounds(430, 197, 179, 33);
		add(btnLeaveCourse);

		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(419, 336, 200, 167);
		add(panelAdmin);
		panelAdmin.setLayout(null);

		JButton btnEditCourse = new JButton("Rediger Kursus");
		btnEditCourse.setBounds(10, 52, 180, 33);
		panelAdmin.add(btnEditCourse);

		JButton btnCreateCourse = new JButton("Opret Kursus");
		btnCreateCourse.addActionListener(e -> {
			openCreateCoursePopup();
		});
		btnCreateCourse.setBounds(10, 11, 180, 33);
		panelAdmin.add(btnCreateCourse);

		JButton btnDeleteCourse = new JButton("Slet Kursus");
		btnDeleteCourse.setBounds(10, 96, 180, 33);
		panelAdmin.add(btnDeleteCourse);

		JButton btnViewSessions = new JButton("Se Sessioner");
		btnViewSessions.setBounds(430, 153, 179, 33);
		add(btnViewSessions);

		JButton btnEnrollCourse = new JButton("Tilmeld Kursus");
		btnEnrollCourse.setBounds(430, 109, 179, 33);
		add(btnEnrollCourse);
	}

	private void openCreateCoursePopup() {
		CreateCourseMenu popup = new CreateCourseMenu();
		popup.run();
	}

	private List<Course> getAllCourses() throws SQLException {
		return courseController.getAllCourses();
	}
}
