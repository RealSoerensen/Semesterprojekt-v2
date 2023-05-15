package gui;

import controller.CourseController;
import controller.LoginController;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.sql.SQLException;
import java.util.List;

public class CourseMenu extends JPanel {
	private final JTable table;
	final CourseController courseController = new CourseController();

	/**
	 * Create the panel.
	 */
	public CourseMenu(MainMenu mainMenu) throws SQLException {
		setLayout(null);
		setSize(626, 515);

		JScrollPane scrollPaneCourses = new JScrollPane();
		scrollPaneCourses.setBounds(10, 11, 410, 492);
		add(scrollPaneCourses);

		List<Course> courses = courseController.getAllCourses();
		Object[][] data = new Object[courses.size()][6];

		for (int i = 0; i < courses.size(); i++) {
			Course course = courses.get(i);
			data[i][0] = course;
			data[i][1] = course.getName();
			data[i][2] = course.getPrice();
			data[i][3] = courseController.getAllCourseMembers(course).size();
			data[i][4] = course.getStartDate();
			data[i][5] = course.getEndDate();

		}

		String[] columnNames = {
				"Kursus", "Navn", "Pris", "Tilmeldte", "Starts dato", "Slut dato"
		};

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		// Get the column model
		TableColumnModel columnModel = table.getColumnModel();
		// Hide the first column
		TableColumn column = columnModel.getColumn(0);
		columnModel.removeColumn(column);
		scrollPaneCourses.setViewportView(table);

		JButton btnViewSessions = new JButton("Se Sessioner");
		btnViewSessions.addActionListener(e -> {
			Course course = (Course) table.getModel().getValueAt(table.getSelectedRow(), 0);
			if (course == null) {
				JOptionPane.showMessageDialog(null, "Vælg venligst et kursus");
			} else {
				mainMenu.course = course;
				mainMenu.cardLayout.show(mainMenu.mainPanel, "session panel");
			}
		});
		btnViewSessions.setBounds(430, 11, 186, 48);
		add(btnViewSessions);

		JButton btnEnrollCourse = new JButton("Tilmeld");
		btnEnrollCourse.addActionListener(e -> {
		});
		btnEnrollCourse.setBounds(430, 70, 186, 48);
		add(btnEnrollCourse);

		JButton btnLeaveCourse = new JButton("Frameld");
		btnLeaveCourse.addActionListener(e -> {
		});
		btnLeaveCourse.setBounds(430, 129, 186, 48);
		add(btnLeaveCourse);

		System.out.println(LoginController.getInstance().getPerson().getRole());
		if(LoginController.getInstance().getPerson().getRole() > 2) {

			JPanel panelAdmin = new JPanel();
			panelAdmin.setBounds(430, 222, 94, 112);
			add(panelAdmin);
			panelAdmin.setLayout(null);

			JButton btnEditCourse = new JButton("Rediger");
			btnEditCourse.addActionListener(e -> {
				Course course = (Course) table.getModel().getValueAt(table.getSelectedRow(), 0);
				if(course == null) {
					JOptionPane.showMessageDialog(null, "Vælg venligst et kursus");
				} else {
					mainMenu.course = course;
					mainMenu.cardLayout.show(mainMenu.mainPanel, "edit course panel");
				}
			});
			btnEditCourse.setBounds(0, 45, 94, 23);
			panelAdmin.add(btnEditCourse);

			JButton btnCreateCourse = new JButton("Opret");
			btnCreateCourse.addActionListener(e -> mainMenu.cardLayout.show(mainMenu.mainPanel, "create course panel"));
			btnCreateCourse.setBounds(0, 11, 94, 23);
			panelAdmin.add(btnCreateCourse);

			JButton btnDeleteCourse = new JButton("Slet");
			btnDeleteCourse.addActionListener(e -> {
				Course course = table.getSelectedRow() == 0 ? null : courses.get(table.getSelectedRow());
				if(course == null) {
					JOptionPane.showMessageDialog(null, "Vælg venligst et kursus");
					return;
				}
				try {
					int result = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil slette dette kursus?", "Slet Kursus", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						courseController.removeCourse(course);
						mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel");
						JOptionPane.showMessageDialog(null, "Kursus slettet");
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Kunne ikke slette kursus");
				}
			});
			btnDeleteCourse.setBounds(0, 79, 94, 23);
			panelAdmin.add(btnDeleteCourse);
		}
	}
}
