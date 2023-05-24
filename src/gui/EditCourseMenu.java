package gui;

import model.Course;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.*;

import controller.CourseController;

import java.awt.Font;

import static controller.DateController.getLocalDate;

public class EditCourseMenu extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldStartingDate;
	private JTextField textFieldEndingDate;
	private final CourseController courseController = new CourseController();

    public EditCourseMenu(MainMenu mainMenu, Course course) throws SQLException {
    	setSize(626, 515);
    	setLayout(null);
    	
    	JButton btnBack = new JButton("Tilbage");
    	btnBack.addActionListener(e -> {
			CourseMenu courseMenu;
			try {
				courseMenu = new CourseMenu(mainMenu);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Der skete en fejl under indlæsningen af kurserne.");
				return;
			}

			mainMenu.mainPanel.add(courseMenu, "CourseMenu");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "CourseMenu");
		});
    	btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	btnBack.setBounds(150, 386, 175, 42);
    	add(btnBack);
    	
    	JButton btnAccept = new JButton("Accepter ændringer");
    	btnAccept.addActionListener(e -> {
			String name = textFieldName.getText();
			double price = Double.parseDouble(textFieldPrice.getText());
			String strStartDate = textFieldStartingDate.getText();
			String strEndDate = textFieldEndingDate.getText();

			LocalDate startDate;
            try {
            	int[] intDate = courseController.StringArrToIntArr(strStartDate.split("-"));
            	startDate = getLocalDate(intDate);

			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
				return;
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
				return;
			}

            LocalDate endDate;
            try {
            	int[] intDate = courseController.StringArrToIntArr(strEndDate.split("-"));
            	endDate = getLocalDate(intDate);
			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
				return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
				return;
			}
            
            if(endDate == null || startDate == null || startDate.isAfter(endDate)) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
			}
            else {
				course.setName(name);
				course.setPrice(price);
				course.setStartDate(startDate);
				course.setEndDate(endDate);
	
				try {
					courseController.updateCourse(course);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Der skete en fejl under opdateringen af kurset.");
					return;
				}
	
				CourseMenu courseMenu;
				try {
					courseMenu = new CourseMenu(mainMenu);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Der skete en fejl under indlæsningen af kurserne.");
					return;
				}
	
				mainMenu.mainPanel.add(courseMenu, "CourseMenu");
				mainMenu.cardLayout.show(mainMenu.mainPanel, "CourseMenu");
            }
		});
    	
    	btnAccept.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	btnAccept.setBounds(335, 386, 175, 42);
    	add(btnAccept);
    	
    	JLabel lblName = new JLabel("Navn:");
    	lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblName.setBounds(179, 46, 72, 14);
    	add(lblName);
    	
    	textFieldName = new JTextField(course.getName());
    	textFieldName.setBounds(271, 45, 210, 20);
    	add(textFieldName);
    	textFieldName.setColumns(10);
    	
    	JLabel lblPrice = new JLabel("Pris:");
    	lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblPrice.setBounds(179, 247, 72, 14);
    	add(lblPrice);
    	
    	JLabel lblEndingDate = new JLabel("Slut Dato:");
    	lblEndingDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblEndingDate.setBounds(179, 343, 72, 14);
    	add(lblEndingDate);
    	
    	JLabel lblStartingDate = new JLabel("Starts dato:");
    	lblStartingDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblStartingDate.setBounds(179, 297, 82, 14);
    	add(lblStartingDate);
    	
    	textFieldPrice = new JTextField(Double.toString(course.getPrice()));
    	textFieldPrice.setColumns(10);
    	textFieldPrice.setBounds(271, 246, 210, 20);
    	add(textFieldPrice);
    	
    	textFieldStartingDate = new JTextField(course.getStartDate().toString());
    	textFieldStartingDate.setColumns(10);
    	textFieldStartingDate.setBounds(271, 296, 210, 20);
    	add(textFieldStartingDate);
    	
    	textFieldEndingDate = new JTextField(course.getEndDate().toString());
    	textFieldEndingDate.setColumns(10);
    	textFieldEndingDate.setBounds(271, 342, 210, 20);
    	add(textFieldEndingDate);
    	
    	JLabel lblDesc = new JLabel("Beskrivelse:");
    	lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblDesc.setBounds(179, 94, 82, 14);
    	add(lblDesc);
    	
    	JTextArea textArea = new JTextArea(course.getDescription());
    	textArea.setBounds(271, 91, 210, 144);
    	add(textArea);
    }
}
