package gui;

import controller.CourseController;
import controller.LoginController;
import model.Course;
import model.Person;
import model.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseMenu extends JPanel {
	private final JTable table;
	private Object[][] courseData;
	private final CourseController courseController = new CourseController();
	private final Person person = LoginController.getInstance().getPerson();
	private final JButton btnEnrollCourse;
	private final JButton btnLeaveCourse;
	private final JButton btnEditCourse;
	private final JButton btnDeleteCourse;
	private final JButton btnViewSessions;

	/**
	 * Create the panel.
	 */
	public CourseMenu(MainMenu mainMenu) throws SQLException {
		setLayout(null);
		setSize(626, 515);

		JScrollPane scrollPaneCourses = new JScrollPane();
		scrollPaneCourses.setBounds(10, 11, 486, 492);
		add(scrollPaneCourses);

		table = new JTable();
		try{
			refreshTable();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke opdatere kursus");
		}
		
		table.getSelectionModel().addListSelectionListener(event -> setButtonsEnabled(true));

		scrollPaneCourses.setViewportView(table);

		btnEnrollCourse = new JButton("Tilmeld");
		btnEnrollCourse.addActionListener(e -> {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Kursus er ikke valgt");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			Course course = (Course) courseData[selectedRow][0];
			if (course == null) {
				JOptionPane.showMessageDialog(null, "Vælg venligst et kursus");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			if(courseController.isMemberInCourse(course, person)) {
				JOptionPane.showMessageDialog(null, "Du er allerede meldt til kurset");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			if(person.getRole() == 1) {
				if(!courseController.createCourseMember(course, person)) {
					JOptionPane.showMessageDialog(null, "Kunne ikke tilmelde kursus");
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					return;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Man kan kun tilmelde et kursus som en kursist");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			List<Session> sessions = null;
			try {
				sessions = courseController.getAllSessionsFromCourse(course);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Der skete en fejl med at tilmelde sessioner under kurset");
			}
			
			if(sessions != null) {
				for(Session element: sessions) {
					courseController.createSessionMember(element, person);
				}
			}
			
			JOptionPane.showMessageDialog(null, "Kursus er tilmeldt");
			try {
				refreshTable();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke opdatere kursus");
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		});
		btnEnrollCourse.setBounds(506, 11, 110, 48);
		add(btnEnrollCourse);

		btnLeaveCourse = new JButton("Frameld");
		btnLeaveCourse.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg et kursus");
				return;
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Course course = (Course) courseData[selectedRow][0];
			
			if(!courseController.isMemberInCourse(course, person)) {
				JOptionPane.showMessageDialog(null, "Du er allerede meldt fra kurset");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}

			boolean isNotEnrolled;
			try {
				isNotEnrolled = courseController.removeCourseMember(course, person);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Kunne ikke framelde kursus");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			if(!isNotEnrolled) {
				JOptionPane.showMessageDialog(null, "Kunne ikke framelde kursus");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}

			JOptionPane.showMessageDialog(null, "Kursus er frameldt");
			try {
				refreshTable();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke opdatere kursus");
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		});
		btnLeaveCourse.setBounds(506, 70, 110, 48);
		add(btnLeaveCourse);

		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(506, 324, 110, 179);
		add(panelAdmin);
		panelAdmin.setLayout(null);
		panelAdmin.setVisible(LoginController.getInstance().getPerson().getRole() > 2);

		btnEditCourse = new JButton("Rediger");
		btnEditCourse.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Kursus er ikke valgt");
				return;
			}
			Course course = (Course) courseData[selectedRow][0];
			EditCourseMenu editCourseMenu;
			try {
				editCourseMenu = new EditCourseMenu(mainMenu, course);
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			mainMenu.mainPanel.add(editCourseMenu, "edit course panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "edit course panel");
		});
		btnEditCourse.setBounds(0, 73, 110, 48);
		panelAdmin.add(btnEditCourse);

		JButton btnCreateCourse = new JButton("Opret");
		btnCreateCourse.addActionListener(e -> {
			CreateCourseMenu createCourseMenu;
			try {
				createCourseMenu = new CreateCourseMenu(mainMenu);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke åbne menuen");
				return;
			}
			mainMenu.mainPanel.add(createCourseMenu, "create course panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "create course panel");
		});
		btnCreateCourse.setBounds(0, 12, 110, 50);
		panelAdmin.add(btnCreateCourse);

		btnDeleteCourse = new JButton("Slet");
		btnDeleteCourse.setBounds(0, 131, 110, 48);
		panelAdmin.add(btnDeleteCourse);
		
		JPanel panelSession = new JPanel();
		panelSession.setBounds(506, 129, 110, 48);
		add(panelSession);
		panelSession.setLayout(null);

		btnViewSessions = new JButton("Se Sessioner");
		btnViewSessions.setBounds(0, 0, 110, 44);
		panelSession.add(btnViewSessions);
		btnViewSessions.addActionListener(e -> {
			displaySessions(mainMenu);
		});
		btnDeleteCourse.addActionListener(e -> {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Kursus er ikke valgt");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			Course course = (Course) courseData[selectedRow][0];
			try {
				int result = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil slette dette kursus?", "Slet Kursus", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					courseController.removeCourse(course);
					try {
						refreshTable();
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke opdatere kursus");
					}
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Kunne ikke slette kursus");
			}
			setButtonsEnabled(false);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
	        	if (me.getClickCount() == 2) {
	        		displaySessions(mainMenu);
	        	}
	        }
	     });
		
		setButtonsEnabled(false);
	}
	
	private void displaySessions(MainMenu mainMenu) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Kursus er ikke valgt");
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		Course course = (Course) courseData[selectedRow][0];
		List<Person> courseMembers = courseController.getAllCourseMembers(course);
		boolean isEnrolled = false;
		for(Person p : courseMembers) {
			if(p.getSsn() == person.getSsn()) {
				isEnrolled = true;
				break;
			}
		}
		if(!isEnrolled && person.getRole() == 1) {
			JOptionPane.showMessageDialog(null, "Du er ikke tilmeldt dette kursus");
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
			mainMenu.setLblTitle(course.getName() + " Sessioner");
		}
	}

	private void refreshTable() throws SQLException {
		List<Course> courses = courseController.getAllCourses();
		Object[][] data = new Object[courses.size()][7];
		for (int i = 0; i < courses.size(); i++) {
			Course course = courses.get(i);
			List<Person> enrolledList = courseController.getAllCourseMembers(course);
			data[i][0] = course;
			data[i][1] = course.getName();
			data[i][2] = course.getPrice();
			data[i][3] = enrolledList.size();
			data[i][4] = course.getStartDate();
			data[i][5] = course.getEndDate();
			data[i][6] = isEnrolled(enrolledList);
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

	private String isEnrolled(List<Person> personEnrolled) {
		String result = "Nej";
		for(Person enrolled : personEnrolled) {
			if (person.getSsn() == enrolled.getSsn()) {
				result = "Ja";
				break;
			}
		}
		return result;
	}
	
	private void setButtonsEnabled(boolean enabled) {
		btnEnrollCourse.setEnabled(enabled);
		btnLeaveCourse.setEnabled(enabled);
		btnEditCourse.setEnabled(enabled);
		btnDeleteCourse.setEnabled(enabled);
		btnViewSessions.setEnabled(enabled);
	}
}
