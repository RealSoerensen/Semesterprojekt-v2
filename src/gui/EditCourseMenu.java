package gui;

import model.Course;

import javax.swing.*;

import controller.CourseController;

public class EditCourseMenu extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldStartingDate;
	private JTextField textFieldEndingDate;
	private CourseController courseController = new CourseController();
	
    public EditCourseMenu(MainMenu mainMenu, Course course) {
    	
    	setSize(380, 350);
    	setLayout(null);
    	
    	JButton btnBack = new JButton("Tilbage");
    	btnBack.setBounds(195, 309, 175, 30);
    	add(btnBack);
    	
    	JButton btnAccept = new JButton("Accepter ændringer");
    	btnAccept.setBounds(10, 309, 175, 30);
    	add(btnAccept);
    	
    	JLabel lblName = new JLabel("Navn:");
    	lblName.setBounds(113, 48, 72, 14);
    	add(lblName);
    	
    	textFieldName = new JTextField(course.getName());
    	textFieldName.setBounds(204, 45, 166, 20);
    	add(textFieldName);
    	textFieldName.setColumns(10);
    	
    	JLabel lblPrice = new JLabel("Pris:");
    	lblPrice.setBounds(113, 96, 72, 14);
    	add(lblPrice);
    	
    	JLabel lblEndingDate = new JLabel("Slut Dato:");
    	lblEndingDate.setBounds(113, 192, 72, 14);
    	add(lblEndingDate);
    	
    	JLabel lblStartingDate = new JLabel("Starts dato:");
    	lblStartingDate.setBounds(113, 146, 72, 14);
    	add(lblStartingDate);
    	
    	textFieldPrice = new JTextField(Double.toString(course.getPrice()));
    	textFieldPrice.setColumns(10);
    	textFieldPrice.setBounds(204, 93, 166, 20);
    	add(textFieldPrice);
    	
    	textFieldStartingDate = new JTextField();
    	textFieldStartingDate.setColumns(10);
    	textFieldStartingDate.setBounds(204, 143, 166, 20);
    	add(textFieldStartingDate);
    	
    	textFieldEndingDate = new JTextField();
    	textFieldEndingDate.setColumns(10);
    	textFieldEndingDate.setBounds(204, 189, 166, 20);
    	add(textFieldEndingDate);
    	
    	
    }
}
