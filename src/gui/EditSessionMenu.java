package gui;

import model.Address;
import model.Person;
import model.Session;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import controller.CourseController;
import controller.DateController;
import controller.PersonController;
import model.Subject;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditSessionMenu extends JPanel {
	private JTextField textFieldDate;
	private JTextField textFieldTime;
	private JTextField textFieldCity;
	private JTextField textFieldZIP;
	private JTextField textFieldStreet;
	private JTextField textFieldStreetNum;
	private PersonController personController;
	private CourseController courseController;
	
    public EditSessionMenu(MainMenu mainMenu, Session session) {
    	courseController = new CourseController();
		personController = new PersonController();
    	setSize(626, 515);
    	setLayout(null);

    	JLabel lblNewLabel = new JLabel("Fag:");
    	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblNewLabel.setBounds(191, 121, 103, 26);
    	add(lblNewLabel);

    	JLabel lblDato = new JLabel("Dato:");
    	lblDato.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblDato.setBounds(191, 47, 47, 26);
    	add(lblDato);
    	
    	JLabel lblTime = new JLabel("Tid:");
    	lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblTime.setBounds(191, 84, 47, 26);
    	add(lblTime);

    	JLabel lblInstruktr = new JLabel("Instruktør:");
    	lblInstruktr.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblInstruktr.setBounds(191, 158, 103, 26);
    	add(lblInstruktr);

    	JLabel lblAdresse = new JLabel("Adresse:");
    	lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblAdresse.setBounds(191, 195, 103, 26);
    	add(lblAdresse);

		List<Subject> subjects = new ArrayList<>();
		try{
			subjects = courseController.getAllSubjects();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Kunne ikke hente liste med fag");
		}

		JComboBox<Subject> comboBoxSubject = new JComboBox<>();
		for (Subject subject : subjects) {
			comboBoxSubject.addItem(subject);
		}

    	comboBoxSubject.setBounds(292, 121, 153, 26);
    	add(comboBoxSubject);

    	textFieldDate = new JTextField(session.getDate().toString());
    	textFieldDate.setBounds(292, 47, 153, 26);
    	add(textFieldDate);
    	textFieldDate.setColumns(10);
    	
    	textFieldTime = new JTextField(session.getTime().toString());
    	textFieldTime.setBounds(292, 84, 153, 26);
    	add(textFieldTime);
    	textFieldTime.setColumns(10);

		List<Person> instructors = new ArrayList<>();
		try {
			instructors = personController.getAllInstructors();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kunne ikke hente instruktørene");
		}

		JComboBox<Person> comboBoxInstructor = new JComboBox<>();
		for (Person instructor : instructors) {
			comboBoxInstructor.addItem(instructor);
		}
    	comboBoxInstructor.setBounds(292, 158, 153, 26);
    	add(comboBoxInstructor);

    	textFieldCity = new JTextField(session.getAddress().getCity());
    	textFieldCity.setBounds(292, 232, 153, 29);
    	add(textFieldCity);
    	textFieldCity.setColumns(10);

    	JLabel lblBy = new JLabel("By:");
    	lblBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblBy.setBounds(191, 235, 29, 26);
    	add(lblBy);

    	JLabel lblPostNummer = new JLabel("Postnummer:");
    	lblPostNummer.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblPostNummer.setBounds(191, 272, 103, 26);
    	add(lblPostNummer);

    	textFieldZIP = new JTextField(session.getAddress().getZipCode());
    	textFieldZIP.setBounds(292, 270, 153, 28);
    	add(textFieldZIP);
    	textFieldZIP.setColumns(10);

    	JLabel lblGade = new JLabel("Vej:");
    	lblGade.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblGade.setBounds(191, 309, 103, 26);
    	add(lblGade);

    	textFieldStreet = new JTextField(session.getAddress().getStreet());
    	textFieldStreet.setColumns(10);
    	textFieldStreet.setBounds(292, 309, 153, 28);
    	add(textFieldStreet);

    	JLabel lblV = new JLabel("Vejnummer:");
    	lblV.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblV.setBounds(191, 346, 103, 26);
    	add(lblV);

    	textFieldStreetNum = new JTextField(session.getAddress().getHouseNumber());
    	textFieldStreetNum.setColumns(10);
    	textFieldStreetNum.setBounds(292, 346, 153, 28);
    	add(textFieldStreetNum);

    	JButton btnSave = new JButton("Gem ændringer");
    	btnSave.addActionListener(e -> {
			Subject subject = (Subject) comboBoxSubject.getSelectedItem();
			Person instructor = (Person) comboBoxInstructor.getSelectedItem();
			String strDate = textFieldDate.getText();
			LocalDate date = session.getDate();
			try {
				int[] intDate = courseController.StringArrToIntArr(strDate.split("-"));
				date = DateController.getInstance().getLocalDate(intDate);
			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
				return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
				return;
			}
			
			String strTime = textFieldTime.getText();
			LocalTime time = session.getTime();
			
			try {
				int[] intTime = courseController.StringArrToIntArr(strTime.split(":"));
				time = LocalTime.of(intTime[0], intTime[1]);
			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Tid er skrevet forkert ind");
				time = null;
				return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
				return;
			}
			
			if(date == null || time == null) {
				date = session.getDate();
				time = session.getTime();
			}
			else {
				String city = textFieldCity.getText();
				String zip = textFieldZIP.getText();
				String street = textFieldStreet.getText();
				String streetNum = textFieldStreetNum.getText();
				Address address = new Address(city, zip, street, streetNum);
				session.setSubject(subject);
				session.setInstructor(instructor);
				session.setDate(date);
				session.setTime(time);
				session.setAddress(address);
				try {
					courseController.updateSession(session);
					JOptionPane.showMessageDialog(null, "Ændringerne er gemt");
					mainMenu.mainPanel.add(new SessionMenu(mainMenu, session.getCourse()), "session panel");
					mainMenu.cardLayout.show(mainMenu.mainPanel, "session panel");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Kunne ikke gemme ændringerne");
				}
			}
		});
    	btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
    	btnSave.setBounds(324, 409, 121, 36);
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
    	btnBack.setBounds(191, 409, 121, 36);
    	add(btnBack);

    }
}
