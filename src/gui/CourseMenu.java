package gui;

import controller.CourseController;
import controller.LoginController;
import model.Course;
import model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.sql.SQLException;
import java.util.List;

public class CourseMenu extends JPanel {
	private final JTable table;
	private Object[][] courseData;
	private final CourseController courseController = new CourseController();
	private final Person person = LoginController.getInstance().getPerson();

	/**
	 * Create the panel.
	 */
	public CourseMenu(MainMenu mainMenu) throws SQLException {
		setLayout(null);
		setSize(626, 515);

		JScrollPane scrollPaneCourses = new JScrollPane();
		scrollPaneCourses.setBounds(10, 11, 486, 492);
		add(scrollPaneCourses);

		List<Course> courses = courseController.getAllCourses();
		table = new JTable();
		refreshTable(courses);

		scrollPaneCourses.setViewportView(table);

		JButton btnEnrollCourse = new JButton("Tilmeld");
		btnEnrollCourse.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Kursus er ikke valgt");
				return;
			}
			Course course = (Course) courseData[selectedRow][0];
			if (course == null) {
				JOptionPane.showMessageDialog(null, "Vælg venligst et kursus");
				return;
			}
			if(!courseController.createCourseMember(course, LoginController.getInstance().getPerson())) {
				JOptionPane.showMessageDialog(null, "Kunne ikke tilmelde kursus");
				return;
			}
			try {
				refreshTable(courseController.getAllCourses());
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke opdatere kursus");
				return;
			}
			JOptionPane.showMessageDialog(null, "Kursus er tilmeldt");
		});
		btnEnrollCourse.setBounds(506, 11, 110, 48);
		add(btnEnrollCourse);

		JButton btnLeaveCourse = new JButton("Frameld");
		btnLeaveCourse.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg venligst et kursus");
				return;
			}
			Course course = (Course) courseData[selectedRow][0];

			boolean isNotEnrolled;
			try {
				isNotEnrolled = courseController.removeCourseMember(course, person);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke framelde kursus");
				return;
			}
			
			if(isNotEnrolled) {
				JOptionPane.showMessageDialog(null, "Kursus er frameldt");
				refreshTable(courses);
			} else {
				JOptionPane.showMessageDialog(null, "Kunne ikke framelde kursus");
			}
		});
		btnLeaveCourse.setBounds(506, 70, 110, 48);
		add(btnLeaveCourse);

		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(506, 324, 110, 179);
		add(panelAdmin);
		panelAdmin.setLayout(null);
		panelAdmin.setVisible(LoginController.getInstance().getPerson().getRole() > 2);

		JButton btnEditCourse = new JButton("Rediger");
		btnEditCourse.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Kursus er ikke valgt");
				return;
			}
			Course course = (Course) courseData[selectedRow][0];
			EditCourseMenu editCourseMenu = new EditCourseMenu(mainMenu, course);
			mainMenu.mainPanel.add(editCourseMenu, "edit course panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "edit course panel");
		});
		btnEditCourse.setBounds(0, 73, 110, 48);
		panelAdmin.add(btnEditCourse);

		JButton btnCreateCourse = new JButton("Opret");
		btnCreateCourse.addActionListener(e -> {
			CreateCourseMenu createCourseMenu = new CreateCourseMenu(mainMenu);
			mainMenu.mainPanel.add(createCourseMenu, "create course panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "create course panel");
		});
		btnCreateCourse.setBounds(0, 0, 110, 50);
		panelAdmin.add(btnCreateCourse);

		JButton btnDeleteCourse = new JButton("Slet");
		btnDeleteCourse.addActionListener(e -> {
			// TODO: implement delete course functionality
		});
		btnDeleteCourse.setBounds(0, 132, 110, 48);
		panelAdmin.add(btnDeleteCourse);
		
		JPanel panelSession = new JPanel();
		panelSession.setBounds(506, 129, 110, 48);
		add(panelSession);
		panelSession.setLayout(null);

		JButton btnViewSessions = new JButton("Se Sessioner");
		btnViewSessions.setBounds(0, 0, 110, 44);
		panelSession.add(btnViewSessions);
		btnViewSessions.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Kursus er ikke valgt");
				return;
			}
			Course course = (Course) courseData[selectedRow][0];
			if (!courseController.getAllCourseMembers((Course) table.getModel().getValueAt(table.getSelectedRow(), 0)).contains(person))
			{
				JOptionPane.showMessageDialog(null, "Du er ikke tilmeldt dette kursus");
				return;
			}

			SessionMenu sessionMenu = null;
			try {
				sessionMenu = new SessionMenu(mainMenu, course);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			if (sessionMenu != null) {
				mainMenu.mainPanel.add(sessionMenu, "session panel");
				mainMenu.cardLayout.show(mainMenu.mainPanel, "session panel");
			}
		});
		btnDeleteCourse.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Kursus er ikke valgt");
				return;
			}
			Course course = (Course) courseData[selectedRow][0];
			try {
				int result = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil slette dette kursus?", "Slet Kursus", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					courseController.removeCourse(course);
					JOptionPane.showMessageDialog(null, "Kursus slettet");
					refreshTable(courseController.getAllCourses());
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Kunne ikke slette kursus");
			}
		});
		
	}

	private void refreshTable(List<Course> courses) {
		Object[][] data = new Object[courses.size()][7];
		for (int i = 0; i < courses.size(); i++) {
			Course course = courses.get(i);
			data[i][0] = course;
			data[i][1] = course.getName();
			data[i][2] = course.getPrice();
			data[i][3] = courseController.getAllCourseMembers(course).size();
			data[i][4] = course.getStartDate();
			data[i][5] = course.getEndDate();
			data[i][6] = isEnrolled(course);
		}

		courseData = data;

		String[] columnNames = {
				"CourseObject", "Navn", "Pris", "Antal Tilmeldte", "Start dato", "slut dato", "Timeldt"
		};

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.setModel(model);

		// Get the column model
		TableColumnModel columnModel = table.getColumnModel();
		// Hide the first column
		TableColumn column = columnModel.getColumn(0);
		columnModel.removeColumn(column);
	}

	private String isEnrolled(Course course) {
		List<Person> personEnrolled = courseController.getAllCourseMembers(course);
		if(personEnrolled.contains(person)){
			return "Ja";
		} else {
			return "Nej";
		}
	}
}