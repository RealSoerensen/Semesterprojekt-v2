package gui;

import controller.CourseController;
import controller.PersonController;
import model.*;

import javax.swing.*;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static controller.DateController.getLocalDate;

public class CreateSessionMenu extends JPanel {
	private final CourseController courseController = new CourseController();
	private final JTextField textFieldDate;
	private final JTextField textFieldCity;
	private final JTextField textFieldZip;
	private final JTextField textFieldStreet;
	private final JTextField textFieldStreetNum;
	private final JTextField textFieldStartTime;

	public CreateSessionMenu(MainMenu mainMenu, Course course) throws SQLException {
		setSize(626, 515);
		setLayout(null);


		JLabel lblNewLabel = new JLabel("Dato:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(158, 45, 137, 19);
		add(lblNewLabel);

		JLabel lblTime = new JLabel("Start tid:");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTime.setBounds(158, 78, 137, 19);
		add(lblTime);

		textFieldDate = new JTextField();
		textFieldDate.setBounds(322, 45, 156, 21);
		add(textFieldDate);
		textFieldDate.setColumns(10);
		textFieldDate.setText(LocalDate.now().toString());

		textFieldStartTime = new JTextField();
		textFieldStartTime.setBounds(322, 78, 156, 21);
		add(textFieldStartTime);
		textFieldStartTime.setColumns(10);
		textFieldStartTime.setText(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute());

		JLabel lblInstructr = new JLabel("Instruktør:");
		lblInstructr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInstructr.setBounds(158, 140, 137, 19);
		add(lblInstructr);

		List<Person> instructors = new ArrayList<>();
		try {
			PersonController personController = new PersonController();
			instructors = personController.getAllInstructors();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kunne ikke hente instruktørene");
		}

		JComboBox<Person> comboBoxInstructors = new JComboBox<>();
		comboBoxInstructors.addItem(null);
		for (Person instructor : instructors) {
			comboBoxInstructors.addItem(instructor);
		}
		comboBoxInstructors.setBounds(322, 140, 156, 22);
		add(comboBoxInstructors);

		List<Subject> subjects = new ArrayList<>();
		try {
			subjects = courseController.getAllSubjects();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kunne ikke hente fagene");
		}

		JComboBox<Subject> comboBoxSubjects = new JComboBox<>();
		for (Subject subject : subjects) {
			System.out.println(subject.getSubjectID());
			comboBoxSubjects.addItem(subject);
		}
		comboBoxSubjects.addItem(null);
		comboBoxSubjects.setBounds(322, 170, 156, 22);
		add(comboBoxSubjects);

		JLabel lblFag = new JLabel("Fag:");
		lblFag.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFag.setBounds(158, 170, 137, 19);
		add(lblFag);

		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdresse.setBounds(158, 203, 137, 19);
		add(lblAdresse);

		textFieldCity = new JTextField(null);
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(322, 238, 156, 21);
		add(textFieldCity);

		JLabel lblBy = new JLabel("By:");
		lblBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBy.setBounds(158, 233, 29, 26);
		add(lblBy);

		JLabel lblPostNummer = new JLabel("Postnummer:");
		lblPostNummer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostNummer.setBounds(158, 270, 103, 26);
		add(lblPostNummer);

		JLabel lblGade = new JLabel("Vej:");
		lblGade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGade.setBounds(158, 307, 103, 26);
		add(lblGade);

		JLabel lblV = new JLabel("Vejnummer:");
		lblV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblV.setBounds(158, 344, 103, 26);
		add(lblV);

		textFieldZip = new JTextField(null);
		textFieldZip.setColumns(10);
		textFieldZip.setBounds(322, 275, 156, 21);
		add(textFieldZip);

		textFieldStreet = new JTextField(null);
		textFieldStreet.setColumns(10);
		textFieldStreet.setBounds(322, 312, 156, 21);
		add(textFieldStreet);

		textFieldStreetNum = new JTextField(null);
		textFieldStreetNum.setColumns(10);
		textFieldStreetNum.setBounds(322, 349, 156, 21);
		add(textFieldStreetNum);

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			SessionMenu sessionMenu;
			try {
				sessionMenu = new SessionMenu(mainMenu, course);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Der skete en fejl i forbindelse med databasen. Prøv igen senere.");
				return;
			}
			mainMenu.mainPanel.add(sessionMenu, "SessionMenu");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "SessionMenu");
		});
		btnBack.setBounds(206, 391, 106, 45);
		add(btnBack);

		JButton btnOpretSession = new JButton("Opret session");
		btnOpretSession.addActionListener(e -> {
			String strDate = textFieldDate.getText();
			LocalDate date;
			try {
				int[] intDate = courseController.StringArrToIntArr(strDate.split("-"));
				date = getLocalDate(intDate);

			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
				return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
				return;
			}

			String strTime = textFieldStartTime.getText();
			LocalTime startTime;

			try {
				int[] intTime = courseController.StringArrToIntArr(strTime.split(":"));
				startTime = LocalTime.of(intTime[0], intTime[1]);
			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Tid er skrevet forkert ind");
				return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
				return;
			}

			strTime = textFieldStartTime.getText();
			LocalTime endTime;

			try {
				int[] intTime = courseController.StringArrToIntArr(strTime.split(":"));
				endTime = LocalTime.of(intTime[0], intTime[1]);
			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Tid er skrevet forkert ind");
				return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
				return;
			}

			if (date == null || startTime == null || endTime == null || date.isBefore(LocalDate.now()) || startTime.isAfter(endTime) || startTime.isBefore(LocalTime.now())) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato eller tid er skrevet forkert ind");
				return;
			}

			String city = textFieldCity.getText();
			String zip = textFieldZip.getText();
			String street = textFieldStreet.getText();
			String streetNum = textFieldStreetNum.getText();
			Address address = new Address(city, zip, street, streetNum);
			Session newSession = new Session(date, (Person) comboBoxInstructors.getSelectedItem(), course, address,
					(Subject) comboBoxSubjects.getSelectedItem(), startTime, endTime);

			Session createdSession;
			try {
				createdSession = createSession(newSession);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null,
						"Der skete en fejl i forbindelse med databasen. Prøv igen senere.");
				return;
			}

			if (createdSession != null) {
				JOptionPane.showMessageDialog(null, "Session oprettet");
			} else {
				JOptionPane.showMessageDialog(null, "Kunne ikke oprette session");
				return;
			}

			SessionMenu sessionMenu;
			try {
				sessionMenu = new SessionMenu(mainMenu, course);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null,
						"Der skete en fejl i forbindelse med databasen. Prøv igen senere.");
				return;
			}
			mainMenu.mainPanel.add(sessionMenu, "SessionMenu");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "SessionMenu");
		});
		btnOpretSession.setBounds(322, 391, 106, 45);
		add(btnOpretSession);

		JTextField textFieldEndTime = new JTextField();
		textFieldEndTime.setColumns(10);
		textFieldEndTime.setBounds(322, 108, 156, 21);
		add(textFieldEndTime);
		textFieldEndTime.setText(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute());

		JLabel lblSlutTidtimemin = new JLabel("Slut tid:");
		lblSlutTidtimemin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSlutTidtimemin.setBounds(158, 108, 137, 19);
		add(lblSlutTidtimemin);

	}

	private Session createSession(Session newSession) throws SQLException {
		return courseController.createSession(newSession);
	}
}
