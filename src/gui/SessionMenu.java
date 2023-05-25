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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SessionMenu extends JPanel {

	private final JTable table;
	private Object[][] sessionData;
	private final CourseController courseController = new CourseController();
	private final Person person;
	private final Course course;

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
		try {
			refreshTable();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fejl: Kunne ikke opdatere sessioner");
		}
		scrollPaneCourses.setViewportView(table);

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
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

		JButton btnEditSession = new JButton("Rediger");
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

		JButton btnDeleteSession = new JButton("Slet");
		btnDeleteSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at slette");
				return;
			}
			Session session = (Session) sessionData[selectedRow][0];
			try {
				courseController.removeSession(session);
				JOptionPane.showMessageDialog(null, "Session slettet");
				refreshTable();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		});
		btnDeleteSession.setBounds(10, 161, 124, 39);
		panelAdmin.add(btnDeleteSession);

		JButton btnCreateSubject = new JButton("Opret Fag");
		btnCreateSubject.addActionListener(e -> createNewSubjectPopup());
		btnCreateSubject.setBounds(10, 61, 124, 39);
		panelAdmin.add(btnCreateSubject);

		JButton btnJoinSession = new JButton("Tilmeld session");
		btnJoinSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at tilmelde dig");
				return;
			}
			Session session = (Session) sessionData[selectedRow][0];
			if(person.getRole() == 1) {
				if (!courseController.createSessionMember(session, person)) {
					JOptionPane.showMessageDialog(null, "Kunne ikke tilmelde session");
					return;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Man kan kun tilmelde en session som en kursist");
				return;
			}
			JOptionPane.showMessageDialog(null, "Tilmeldt session");
			try {
				refreshTable();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Kunne ikke opdatere sessions-liste");
			}
		});
		btnJoinSession.setBounds(493, 61, 123, 39);
		add(btnJoinSession);

		JButton btnLeaveSession = new JButton("Meld afbud");
		btnLeaveSession.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Vælg en session at melde afbud til");
				return;
			}
			Session session = (Session) sessionData[selectedRow][0];
			if (!courseController.removeSessionMember(session, person)) {
				JOptionPane.showMessageDialog(null, "Kunne ikke melde afbud");
				return;
			}
			JOptionPane.showMessageDialog(null, "Afmeldt session");
			try {
				refreshTable();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Kunne ikke opdatere sessions-liste");
			}
		});
		btnLeaveSession.setBounds(493, 111, 123, 39);
		add(btnLeaveSession);
		
		JButton btnSessionInfo = new JButton("Session Info");
		btnSessionInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = (Session) sessionData[table.getSelectedRow()][0];

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
								"Adresse: " + address.getZipCode() + " " + address.getCity() + "\n" +
								address.getStreet() + " " + address.getHouseNumber()
				);


				optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

				JDialog dialog = optionPane.createDialog(null, "Session oplysninger");
				dialog.setVisible(true);
			}
		});
		btnSessionInfo.setBounds(493, 11, 123, 39);
		add(btnSessionInfo);
	}

	private void refreshTable() throws SQLException {
		List<Session> sessions = courseController.getAllSessionsFromCourse(course);
		Object[][] data = new Object[sessions.size()][7];

		for (int i = 0; i < sessions.size(); i++) {
			Session session = sessions.get(i);
			data[i][0] = session;
			data[i][1] = session.getSubject().getName();
			data[i][2] = session.getDate();
			data[i][3] = session.getInstructor().getFirstName() + " " + session.getInstructor().getLastName();
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

	private void createNewSubjectPopup(){
		JTextField textFieldName = new JTextField(10);
		JTextArea textFieldDescription = new JTextArea();
		textFieldDescription.setColumns(10);
		textFieldDescription.setRows(10);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBounds(10, 11, 414, 143);
		add(panel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);

		panel.add(new JLabel("Navn:"), gbc);

		gbc.gridx = 1;
		panel.add(textFieldName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Beskrivelse:"), gbc);

		gbc.gridx = 1;
		panel.add(textFieldDescription, gbc);
		int result = JOptionPane.showConfirmDialog(null, panel,
				"Nyt fag", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Subject subject = new Subject(textFieldName.getText(), textFieldDescription.getText());
			try {
				if(courseController.createSubject(subject) == null) {
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
		for(Session enrolledSession : enrolledSessions) {
			if(enrolledSession.getSessionID() == session.getSessionID()){
				result = "Ja";
				break;
			}
		}
		return result;
	}
}
