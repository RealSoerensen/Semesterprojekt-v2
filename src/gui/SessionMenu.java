package gui;

import controller.LoginController;
import controller.PersonController;
import model.Course;
import model.Person;
import model.Session;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.CourseController;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SessionMenu extends JPanel {

	private final JTable table;
	private final CourseController courseController = new CourseController();
	private final Person person;
	/**
	 * Create the frame.
	 */
	public SessionMenu(MainMenu mainMenu, Course course) throws SQLException {
		person = mainMenu.user;
		setLayout(null);
		setSize(626, 515);

		JScrollPane scrollPaneCourses = new JScrollPane();
		scrollPaneCourses.setBounds(10, 11, 473, 492);
		add(scrollPaneCourses);

		List<Session> sessions = courseController.getAllSessionsFromCourse(course);
		Object[][] data = new Object[sessions.size()][7];

		for (int i = 0; i < sessions.size(); i++) {
			Session session = sessions.get(i);
			data[i][0] = session;
			data[i][1] = session.getSubject().getName();
			data[i][2] = session.getDate();
			data[i][3] = session.getInstructor().getFirstName() + " " + session.getInstructor().getLastName();
			data[i][4] = courseController.getAllSessionMembers(session).size();
			data[i][5] = session.getAddress().getStreet() + " " + session.getAddress().getHouseNumber() + ", " + session.getAddress().getZipCode() + " " + session.getAddress().getCity();
			data[i][6] = isEnrolled(course, session);
		}

		String[] columnNames = {
				"SessionObject", "Fag", "Dato", "Instruktør", "Tilmeldte", "Adresse", "Tilmeldt"
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

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			CourseMenu courseMenu = null;
			try {
				courseMenu = new CourseMenu(mainMenu);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Fejl: Kan ikke åbne Kursus-menu");
			}

			if(courseMenu != null) {
				mainMenu.mainPanel.add(courseMenu, "course panel");
				mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel");
			}

		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(493, 458, 123, 46);
		add(btnBack);

		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(483, 284, 143, 163);
		add(panelAdmin);
		panelAdmin.setLayout(null);

		panelAdmin.setVisible(person.getRole() > 2);
		JButton btnCreateNewSession = new JButton("Opret Ny Session");
		btnCreateNewSession.addActionListener(e -> {
			CreateSessionMenu createSessionMenu = new CreateSessionMenu(mainMenu, course);
			mainMenu.mainPanel.add(createSessionMenu, "create session panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "create session panel");
		});
		btnCreateNewSession.setBounds(10, 11, 124, 39);
		panelAdmin.add(btnCreateNewSession);

		JButton btnEditSession = new JButton("Rediger Session");
		btnEditSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at tilmelde dig");
				return;
			}
			Session session = (Session) table.getModel().getValueAt(table.getSelectedRow(), 0);
			EditSessionMenu editSessionMenu = new EditSessionMenu(mainMenu, session);
			mainMenu.mainPanel.add(editSessionMenu, "edit session panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "edit session panel");
		});
		btnEditSession.setBounds(10, 61, 124, 39);
		panelAdmin.add(btnEditSession);

		JButton btnDeleteSession = new JButton("Slet Session");
		btnDeleteSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at slette");
			} else {
				Session session = (Session) table.getModel().getValueAt(table.getSelectedRow(), 0);
				try {
					courseController.removeSession(session);
					JOptionPane.showMessageDialog(null, "Session slettet");
					SessionMenu sessionMenu = new SessionMenu(mainMenu, course);
					mainMenu.mainPanel.add(sessionMenu, "session panel");
					mainMenu.cardLayout.show(mainMenu.mainPanel, "session panel");

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteSession.setBounds(10, 111, 124, 39);
		panelAdmin.add(btnDeleteSession);

		JButton btnJoinSession = new JButton("Tilmeld session");
		btnJoinSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at tilmelde dig");
				return;
			}
			Session session = (Session) table.getModel().getValueAt(table.getSelectedRow(), 0);
			if (!courseController.createSessionMember(session, person)) {
				JOptionPane.showMessageDialog(null, "Kunne ikke tilmelde session");
				return;
			}
			JOptionPane.showMessageDialog(null, "Tilmeldt session");
			// Delayed creation of SessionMenu
			SessionMenu sessionMenu = null;
			try {
				sessionMenu = new SessionMenu(mainMenu, course);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Fejl: Kan ikke opdatere session-menu");
			}

			if (sessionMenu != null) {
				mainMenu.mainPanel.add(sessionMenu, "session panel");
				mainMenu.cardLayout.show(mainMenu.mainPanel, "session panel");
			}
		});
		btnJoinSession.setBounds(493, 11, 123, 39);
		add(btnJoinSession);

		JButton btnLeaveSession = new JButton("Meld afbud");
		btnLeaveSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at melde afbud til");
			} else {
				Session session = (Session) table.getModel().getValueAt(table.getSelectedRow(), 0);
				if(courseController.removeSessionMember(session, person)){
					JOptionPane.showMessageDialog(null, "Afmeldt session");
					// Delayed creation of SessionMenu
					SessionMenu sessionMenu = null;
					try {
						sessionMenu = new SessionMenu(mainMenu, course);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Fejl: Kan ikke opdatere session-menu");
					}

					if (sessionMenu != null) {
						mainMenu.mainPanel.add(sessionMenu, "session panel");
						mainMenu.cardLayout.show(mainMenu.mainPanel, "session panel");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Du er ikke tilmeldt denne session");
				}
			}
		});
		btnLeaveSession.setBounds(493, 61, 123, 39);
		add(btnLeaveSession);
	}

	private String isEnrolled(Course course, Session session) throws SQLException {
		List<Session> enrolledSessions = courseController.getEnrolledSessions(person, course);
		if(enrolledSessions.contains(session)) {
			return "Ja";
		} else {
			return "Nej";
		}
	}
}
