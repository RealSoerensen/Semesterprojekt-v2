package gui;

import controller.CourseController;
import controller.PersonController;
import model.*;

import javax.swing.*;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateSessionMenu extends JPanel {
	private CourseController courseController = new CourseController();
	private PersonController personController = new PersonController();
	private JTextField textFieldDate;
	private JTextField textFieldCity;
	private JTextField textFieldZip;
	private JTextField textFieldStreet;
	private JTextField textFieldStreetNum;
    public CreateSessionMenu(MainMenu mainMenu, Course course) {

    	setSize(626, 515);
    	setLayout(null);
    	
    	JLabel lblNewLabel = new JLabel("Dato (dd-mm-yyyy):");
    	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblNewLabel.setBounds(158, 78, 137, 19);
    	add(lblNewLabel);
    	
    	textFieldDate = new JTextField();
    	textFieldDate.setBounds(322, 79, 156, 21);
    	add(textFieldDate);
    	textFieldDate.setColumns(10);
    	
    	JLabel lblInstructr = new JLabel("Instruktør:");
    	lblInstructr.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblInstructr.setBounds(158, 111, 137, 19);
    	add(lblInstructr);


		List<Person> instructors = new ArrayList<>();
		try {
			instructors = personController.getAllInstructors();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kunne ikke hente instruktørene");
		}

		JComboBox<Person> comboBoxInstructors = new JComboBox<>();
		for (Person instructor : instructors) {
			comboBoxInstructors.addItem(instructor);
		}
    	comboBoxInstructors.setBounds(322, 111, 156, 22);
    	add(comboBoxInstructors);

		List<Subject> subjects = new ArrayList<>();
		try{
			subjects = courseController.getAllSubjects();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Kunne ikke hente fag");
		}

    	JComboBox<Subject> comboBoxSubjects = new JComboBox<>();
		for(Subject subject : subjects) {
			comboBoxSubjects.addItem(subject);
		}
    	comboBoxSubjects.setBounds(322, 141, 156, 22);
    	add(comboBoxSubjects);
    	
    	JLabel lblFag = new JLabel("Fag:");
    	lblFag.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblFag.setBounds(158, 141, 137, 19);
    	add(lblFag);
    	
    	JLabel lblAdresse = new JLabel("Adresse");
    	lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblAdresse.setBounds(158, 174, 137, 19);
    	add(lblAdresse);
    	
    	textFieldCity = new JTextField(null);
    	textFieldCity.setColumns(10);
    	textFieldCity.setBounds(322, 209, 156, 21);
    	add(textFieldCity);
    	
    	JLabel lblBy = new JLabel("By:");
    	lblBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblBy.setBounds(158, 204, 29, 26);
    	add(lblBy);
    	
    	JLabel lblPostNummer = new JLabel("Postnummer:");
    	lblPostNummer.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblPostNummer.setBounds(158, 241, 103, 26);
    	add(lblPostNummer);
    	
    	JLabel lblGade = new JLabel("Vej:");
    	lblGade.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblGade.setBounds(158, 278, 103, 26);
    	add(lblGade);
    	
    	JLabel lblV = new JLabel("Vejnummer:");
    	lblV.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblV.setBounds(158, 315, 103, 26);
    	add(lblV);
    	
    	textFieldZip = new JTextField(null);
    	textFieldZip.setColumns(10);
    	textFieldZip.setBounds(322, 246, 156, 21);
    	add(textFieldZip);
    	
    	textFieldStreet = new JTextField(null);
    	textFieldStreet.setColumns(10);
    	textFieldStreet.setBounds(322, 283, 156, 21);
    	add(textFieldStreet);
    	
    	textFieldStreetNum = new JTextField(null);
    	textFieldStreetNum.setColumns(10);
    	textFieldStreetNum.setBounds(322, 320, 156, 21);
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
			mainMenu.mainPanel.add(sessionMenu,"SessionMenu");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "SessionMenu");
		});
    	btnBack.setBounds(206, 362, 106, 45);
    	add(btnBack);
    	
    	JButton btnOpretSession = new JButton("Opret session");
    	btnOpretSession.addActionListener(e -> {
			String strDate = textFieldDate.getText();
			Date date;
			try {
				int[] intDate = courseController.StringArrToIntArr(strDate.split("-"));
				date = new Date(intDate[2], intDate[1], intDate[0]);
			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
				return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
				return;
			}
			String city = textFieldCity.getText();
			String zip = textFieldZip.getText();
			String street = textFieldStreet.getText();
			String streetNum = textFieldStreetNum.getText();
			Address address = new Address(city, zip, street, streetNum);
			Session newSession = new Session(date, (Person) comboBoxInstructors.getSelectedItem(), course, address, (Subject) comboBoxSubjects.getSelectedItem());
			try {
				if(courseController.createSession(newSession)) {
					JOptionPane.showMessageDialog(null, "Session oprettet");
				} else {
					JOptionPane.showMessageDialog(null, "Kunne ikke oprette session");
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Der skete en fejl i forbindelse med databasen. Prøv igen senere.");
				return;
			}

			SessionMenu sessionMenu;
			try {
				sessionMenu = new SessionMenu(mainMenu, course);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Der skete en fejl i forbindelse med databasen. Prøv igen senere.");
				return;
			}
			mainMenu.mainPanel.add(sessionMenu,"SessionMenu");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "SessionMenu");
		});
    	btnOpretSession.setBounds(322, 362, 106, 45);
    	add(btnOpretSession);

    }
}
