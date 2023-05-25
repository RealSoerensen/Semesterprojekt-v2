package gui;

import model.Address;
import model.Person;
import model.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import controller.CourseController;
import controller.PersonController;
import model.Subject;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import static controller.DateController.getLocalDate;

public class EditSessionMenu extends JPanel {
	private final JTextField textFieldDate;
	private final JTextField textFieldCity;
	private final JTextField textFieldZIP;
	private final JTextField textFieldStreet;
	private final JTextField textFieldStreetNum;
	private final JTextField textFieldStartTime;
	private final CourseController courseController;
	private JTextField textFieldEndTime;

	public EditSessionMenu(MainMenu mainMenu, Session session) throws SQLException {
		courseController = new CourseController();
		PersonController personController = new PersonController();
		setSize(626, 515);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Fag:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(181, 158, 91, 26);
		add(lblNewLabel);

		JLabel lblDato = new JLabel("Dato:");
		lblDato.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDato.setBounds(180, 47, 91, 26);
		add(lblDato);

		JLabel lblTime = new JLabel("Tid:");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTime.setBounds(191, 84, 47, 26);
		add(lblTime);
		JLabel lblStartTime = new JLabel("Start tid:");
		lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartTime.setBounds(180, 84, 91, 26);
		add(lblStartTime);

		JLabel lblInstruktr = new JLabel("Instruktør:");
		lblInstruktr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInstruktr.setBounds(181, 195, 91, 26);
		add(lblInstruktr);

		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdresse.setBounds(181, 232, 303, 26);
		add(lblAdresse);

		List<Subject> subjects = new ArrayList<>();
		try {
			subjects = courseController.getAllSubjects();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kunne ikke hente liste med fag");
		}

		JComboBox<Subject> comboBoxSubject = new JComboBox<>();
		for (Subject subject : subjects) {
			comboBoxSubject.addItem(subject);
		}

		comboBoxSubject.setBounds(282, 158, 202, 26);
		add(comboBoxSubject);

		textFieldDate = new JTextField(session.getDate().toString());
		textFieldDate.setBounds(281, 47, 202, 26);
		add(textFieldDate);
		textFieldDate.setColumns(10);

		textFieldStartTime = new JTextField(session.getStartTime().toString());
		textFieldStartTime.setBounds(281, 84, 202, 26);
		add(textFieldStartTime);
		textFieldStartTime.setColumns(10);

		List<Person> instructors;
		instructors = personController.getAllInstructors();

		JComboBox<Person> comboBoxInstructor = new JComboBox<>();
		for (Person instructor : instructors) {
			comboBoxInstructor.addItem(instructor);
		}
		comboBoxInstructor.setBounds(282, 195, 202, 26);
		add(comboBoxInstructor);

		textFieldCity = new JTextField(session.getAddress().getCity());
		textFieldCity.setBounds(282, 269, 202, 29);
		add(textFieldCity);
		textFieldCity.setColumns(10);

		JLabel lblBy = new JLabel("By:");
		lblBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBy.setBounds(181, 272, 91, 26);
		add(lblBy);

		JLabel lblPostNummer = new JLabel("Postnummer:");
		lblPostNummer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostNummer.setBounds(181, 309, 91, 26);
		add(lblPostNummer);

		textFieldZIP = new JTextField(session.getAddress().getZipCode());
		textFieldZIP.setBounds(282, 307, 202, 28);
		add(textFieldZIP);
		textFieldZIP.setColumns(10);

		JLabel lblGade = new JLabel("Vej:");
		lblGade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGade.setBounds(181, 346, 91, 26);
		add(lblGade);

		textFieldStreet = new JTextField(session.getAddress().getStreet());
		textFieldStreet.setColumns(10);
		textFieldStreet.setBounds(282, 346, 202, 28);
		add(textFieldStreet);

		JLabel lblV = new JLabel("Vejnummer:");
		lblV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblV.setBounds(181, 383, 91, 26);
		add(lblV);

		textFieldStreetNum = new JTextField(session.getAddress().getHouseNumber());
		textFieldStreetNum.setColumns(10);
		textFieldStreetNum.setBounds(282, 383, 202, 28);
		add(textFieldStreetNum);

		JButton btnSave = new JButton("Gem ændringer");
		btnSave.addActionListener(e -> {
			Subject subject = (Subject) comboBoxSubject.getSelectedItem();
			Person instructor = (Person) comboBoxInstructor.getSelectedItem();
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
			
			strTime = textFieldEndTime.getText();
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

			if ((date == null || startTime == null || endTime == null || date.isBefore(LocalDate.now()) || startTime.isAfter(endTime) || startTime.isBefore(LocalTime.now()))) {
				String city = textFieldCity.getText();
				String zip = textFieldZIP.getText();
				String street = textFieldStreet.getText();
				String streetNum = textFieldStreetNum.getText();
				Address address = new Address(zip, city, street, streetNum);
				session.setSubject(subject);
				session.setInstructor(instructor);
				session.setDate(date);
				session.setStartTime(startTime);
				session.setEndTime(endTime);

				session.setAddress(address);
				try {
					courseController.updateSession(session);
					JOptionPane.showMessageDialog(null, "Ændringerne er gemt");
					mainMenu.mainPanel.add(new SessionMenu(mainMenu, session.getCourse()), "session panel");
					mainMenu.cardLayout.show(mainMenu.mainPanel, "session panel");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Kunne ikke gemme ændringerne");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Fejl: Dato eller tid er ikke skrevet ind");
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSave.setBounds(336, 423, 170, 36);
		add(btnSave);

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			try {
				mainMenu.mainPanel.add(new SessionMenu(mainMenu, session.getCourse()), "session panel");
				mainMenu.cardLayout.show(mainMenu.mainPanel, "session panel");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(156, 423, 170, 36);
		add(btnBack);
		
		textFieldEndTime = new JTextField(session.getEndTime().toString());
		textFieldEndTime.setColumns(10);
		textFieldEndTime.setBounds(282, 121, 202, 26);
		add(textFieldEndTime);
		
		JLabel lblEndTime = new JLabel("Slut tid:");
		lblEndTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEndTime.setBounds(181, 121, 91, 26);
		add(lblEndTime);

	}
}
