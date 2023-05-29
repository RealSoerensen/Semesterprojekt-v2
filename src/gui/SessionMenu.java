package gui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.CourseController;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SessionMenu extends JPanel {

	private final JTable table;
	private Object[][] sessionData;
	private final CourseController courseController = new CourseController();
	private final Person person;
	private final Course course;
	private final JButton btnDeleteSession;
	private final JButton btnJoinSession;
	private final JButton btnSessionInfo;
	private final JButton btnLeaveSession;
	private final JButton btnEditSession;

	/**
	 * Create the frame.
	 */
	public SessionMenu(MainMenu mainMenu, Course course) throws SQLException {
		person = mainMenu.user;
		this.course = course;
		setLayout(null);
		setSize(626, 515);

		JScrollPane scrollPaneCourses = new JScrollPane();
		scrollPaneCourses.setBounds(10, 11, 473, 492);
		add(scrollPaneCourses);

		table = new JTable();
		table.getSelectionModel().addListSelectionListener(event -> setButtonsEnabled(true));

		try {
			refreshTable();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke opdatere sessioner");
		}
		scrollPaneCourses.setViewportView(table);

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			CourseMenu courseMenu;
			try {
				courseMenu = new CourseMenu(mainMenu);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Fejl: Kan ikke åbne Kursus-menu");
				return;
			}
			mainMenu.setLblTitle("Kurser");
			mainMenu.mainPanel.add(courseMenu, "course panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel");
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(493, 458, 123, 46);
		add(btnBack);

		JPanel panelAdmin = new JPanel();
		panelAdmin.setBounds(483, 238, 143, 209);
		add(panelAdmin);
		panelAdmin.setLayout(null);

		panelAdmin.setVisible(person.getRole() > 2);
		JButton btnCreateNewSession = new JButton("Opret");
		btnCreateNewSession.addActionListener(e -> {
			CreateSessionMenu createSessionMenu;
			try {
				createSessionMenu = new CreateSessionMenu(mainMenu, course);
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			mainMenu.mainPanel.add(createSessionMenu, "create session panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "create session panel");
		});
		btnCreateNewSession.setBounds(10, 11, 124, 39);
		panelAdmin.add(btnCreateNewSession);

		btnEditSession = new JButton("Rediger");
		btnEditSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at tilmelde dig");
				return;
			}
			Session session = (Session) sessionData[selectedRow][0];
			EditSessionMenu editSessionMenu;
			try {
				editSessionMenu = new EditSessionMenu(mainMenu, session);
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			mainMenu.mainPanel.add(editSessionMenu, "edit session panel");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "edit session panel");
		});
		btnEditSession.setBounds(10, 111, 124, 39);
		panelAdmin.add(btnEditSession);

		btnDeleteSession = new JButton("Slet");
		btnDeleteSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at slette");
				return;
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Session session = (Session) sessionData[selectedRow][0];
			try {
				courseController.removeSession(session);
				refreshTable();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Session slettet");
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			setButtonsEnabled(false);
		});
		btnDeleteSession.setBounds(10, 161, 124, 39);
		panelAdmin.add(btnDeleteSession);

		JButton btnCreateSubject = new JButton("Opret Fag");
		btnCreateSubject.addActionListener(e -> createNewSubjectPopup());
		btnCreateSubject.setBounds(10, 61, 124, 39);
		panelAdmin.add(btnCreateSubject);

		btnJoinSession = new JButton("Tilmeld session");
		btnJoinSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at tilmelde dig");
				return;
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Session session = (Session) sessionData[selectedRow][0];
			if (person.getRole() == 1) {
				if(courseController.isMemberInSession(session, person)) {
					JOptionPane.showMessageDialog(null, "Du er allerede meldt til sessionen");
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				if (!courseController.createSessionMember(session, person)) {
					JOptionPane.showMessageDialog(null, "Kunne ikke tilmelde session");
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					return;
				}
			} 
			else if(person.getRole() == 2) {
				if(session.getInstructor() != null) {
					JOptionPane.showMessageDialog(null, "Der er allerede en instruktør på dette kursus");
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				else {
					session.setInstructor(person);
					try {
						courseController.updateSession(session);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Kunne ikke opdatere session");
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Man kan ikke melde sig til et kursus som en adminstrator");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			try {
				refreshTable();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Kunne ikke opdatere sessions-liste");
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			JOptionPane.showMessageDialog(null, "Tilmeldt session");
			setButtonsEnabled(false);
		});
		btnJoinSession.setBounds(493, 61, 123, 39);
		add(btnJoinSession);

		btnLeaveSession = new JButton("Meld afbud");
		btnLeaveSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session");
				return;
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Session session = (Session) sessionData[selectedRow][0];
			
			if(person.getRole() == 1) {
				if(!courseController.isMemberInSession(session, person)) {
					JOptionPane.showMessageDialog(null, "Du er allerede meldt fra sessionen");
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				if (!markAbsent(session)) {
					JOptionPane.showMessageDialog(null, "Kunne ikke melde afbud");
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					return;
				}
			}
			else if(person.getRole() == 2) {
				if(session.getInstructor() != null) {
					if(session.getInstructor().getSsn() != person.getSsn()) {
						JOptionPane.showMessageDialog(null, "Du kan ikke melde afbud fra en session du ikke er meldt til");
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						return;
					}
					else {
						session.setInstructor(null);
						try {
							courseController.updateSession(session);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Kunne ikke opdatere session");
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Du kan ikke melde afbud fra en session du ikke er meldt til");
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					return;
				}
			}
			else if(person.getRole() == 3) {
				JOptionPane.showMessageDialog(null, "Du kan ikke melde afbud fra en session som administrator");
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			try {
				refreshTable();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Kunne ikke opdatere sessions-liste");
			}
			JOptionPane.showMessageDialog(null, "Afmeldt session");
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			setButtonsEnabled(false);
		});
		btnLeaveSession.setBounds(493, 111, 123, 39);
		add(btnLeaveSession);

		btnSessionInfo = new JButton("Session Info");
		btnSessionInfo.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session");
				return;
			}
			Session session = (Session) sessionData[table.getSelectedRow()][0];
			displaySessionInfo(session);
		});
		btnSessionInfo.setBounds(493, 11, 123, 39);
		add(btnSessionInfo);

		mainMenu.setLblTitle(course.getName() + " Sessioner");
		setButtonsEnabled(false);
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	             if (me.getClickCount() == 2) {
	            	 Session session = (Session) sessionData[table.getSelectedRow()][0];
	            	 displaySessionInfo(session);
	             }
	         }
	     });
	}
	
	private void displaySessionInfo(Session session) {
		if (session == null) {
				JOptionPane.showMessageDialog(null, "Vælg en session at se informationer om");
				return;
			}
		Address address = session.getAddress();
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage("Session informationer:\n" +
		"Fag: " + session.getSubject().getName() + "\n" +
		"Beskrivelse: " + session.getSubject().getDescription() + "\n" +
		"Start dato: " + session.getDate() + "\n" +
		"Fra: " + session.getStartTime() + "\n" +
		"Til: " + session.getEndTime() + "\n" +
		"Instruktør: " + session.getInstructor().getFirstName() + " " + session.getInstructor().getLastName() + "\n" +
		getMembersOnSessionAsString(session) + "\n" +
		"Adresse: " + address.getZipCode() + " " + address.getCity() + "\n" +
		address.getStreet() + " " + address.getHouseNumber());
		
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Session oplysninger");
		dialog.setVisible(true);
	}
	
	private String getMembersOnSessionAsString(Session session) {
		StringBuilder membersOnSession = new StringBuilder("Kursister:");
		List<Person> members = courseController.getAllSessionMembers(session);

		for (Person member : members) {
			membersOnSession.append("\n").append(member.getFirstName()).append(" ").append(member.getLastName());
		}

		if(members.size() == 0) {
			membersOnSession = new StringBuilder("Ingen Kursister");
		}
		
		return membersOnSession.toString();
	}

	private void refreshTable() throws SQLException {
		List<Session> sessions = courseController.getAllSessionsFromCourse(course);
		Object[][] data = new Object[sessions.size()][7];
		for (int i = 0; i < sessions.size(); i++) {
			Session session = sessions.get(i);
			Person instructor = session.getInstructor();
			String instructorString;
			if(instructor == null) {
				instructorString = "Ingen instruktør";
			} else {
				instructorString = instructor.getFirstName() + " " + instructor.getLastName();
			}
			
			data[i][0] = session;
			data[i][1] = session.getSubject().getName();
			data[i][2] = session.getDate();
			data[i][3] = instructorString;
			data[i][4] = courseController.getAllSessionMembers(session).size();
			data[i][5] = session.getAddress().getStreet() + " " + session.getAddress().getHouseNumber() + ", "
					+ session.getAddress().getZipCode() + " " + session.getAddress().getCity();
			data[i][6] = isEnrolled(course, session);
		}

		sessionData = data;

		String[] columnNames = {
				"SessionObject", "Fag", "Dato", "Instruktør", "Tilmeldte", "Adresse", "Tilmeldt"
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

	private void createNewSubjectPopup() {
		// Create a JPanel to hold the components
		JPanel panel = new JPanel(new GridBagLayout());

		// Create GridBagConstraints for component positioning
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;

		// Create a JTextField for "Navn"
		JTextField nameField = new JTextField(20);
		panel.add(new JLabel("Navn:"), constraints);

		constraints.gridy = 1;
		panel.add(nameField, constraints);

		// Create a JTextArea for "Beskrivelse"
		JTextArea descriptionArea = new JTextArea(5, 20);
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(descriptionArea);
		constraints.gridy = 2;
		panel.add(new JLabel("Beskrivelse:"), constraints);

		constraints.gridy = 3;
		panel.add(scrollPane, constraints);

		int result = JOptionPane.showConfirmDialog(null, panel,
				"Nyt fag", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Subject subject = new Subject(nameField.getText(), descriptionArea.getText());
			try {
				if (courseController.createSubject(subject) == null) {
					JOptionPane.showMessageDialog(null, "Kunne ikke oprette fag");
					return;
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Kunne ikke tilgå database");
				return;
			}

			JOptionPane.showMessageDialog(null, "Fag oprettet");
		}
	}

	private String isEnrolled(Course course, Session session) throws SQLException {
		String result = "Nej";
		List<Session> enrolledSessions = courseController.getEnrolledSessions(person, course);
		for (Session enrolledSession : enrolledSessions) {
			if (enrolledSession.getSessionID() == session.getSessionID()) {
				result = "Ja";
				break;
			}
		}
		return result;
	}

	private boolean markAbsent(Session session) {
		return courseController.removeSessionMember(session, person);
	}

	private void setButtonsEnabled(boolean enabled) {
		btnDeleteSession.setEnabled(enabled);
		btnEditSession.setEnabled(enabled);
		btnJoinSession.setEnabled(enabled);
		btnLeaveSession.setEnabled(enabled);
		btnSessionInfo.setEnabled(enabled);
	}
}
