package gui;

import model.Course;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import controller.CourseController;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditCourseMenu extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldStartingDate;
	private JTextField textFieldEndingDate;
	private CourseController courseController = new CourseController();

    public EditCourseMenu(MainMenu mainMenu, Course course) {
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

			Date startDate;
			try {
				int[] intArrDate = courseController.StringArrToIntArr(strStartDate.split("-"));
				startDate = new Date(intArrDate[2], intArrDate[1], intArrDate[0]);
			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
				return;
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
				return;
			}

			Date endDate;
			try {
				int[] intArrDate = courseController.StringArrToIntArr(strEndDate.split("-"));
				endDate = new Date(intArrDate[2], intArrDate[1], intArrDate[0]);
			} catch (IndexOutOfBoundsException _ignore) {
				JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
				return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
				return;
			}

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
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	textFieldStartingDate = new JTextField(dateFormat.format(course.getStartDate()));
    	textFieldStartingDate.setColumns(10);
    	textFieldStartingDate.setBounds(271, 296, 210, 20);
    	add(textFieldStartingDate);
    	
    	textFieldEndingDate = new JTextField(dateFormat.format(course.getEndDate()));
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
