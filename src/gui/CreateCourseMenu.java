package gui;

import controller.CourseController;
import controller.DateController;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;

import static controller.DateController.getLocalDate;

public class CreateCourseMenu extends JPanel {
    final CourseController courseController = new CourseController();

    public CreateCourseMenu(MainMenu mainMenu) {
        setLayout(null);
        setSize(626, 515);

        JLabel lblCourseName = new JLabel("Navn:");
        lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseName.setBounds(26, 120, 205, 20);
        add(lblCourseName);

        JTextField txtCourseName = new JTextField();
        txtCourseName.setBounds(26, 151, 269, 20);
        add(txtCourseName);
        txtCourseName.setColumns(10);

        JLabel lblCourseDescription = new JLabel("Beskrivelse:");
        lblCourseDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseDescription.setBounds(26, 182, 205, 20);
        add(lblCourseDescription);

        JTextArea txtCourseDescription = new JTextArea();
        txtCourseDescription.setBounds(26, 213, 269, 126);
        add(txtCourseDescription);

        JLabel lblCoursePrice = new JLabel("Pris:");
        lblCoursePrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCoursePrice.setBounds(325, 120, 217, 20);
        add(lblCoursePrice);

        JTextField txtCoursePrice = new JTextField();
        txtCoursePrice.setBounds(325, 151, 269, 20);
        add(txtCoursePrice);
        txtCoursePrice.setColumns(10);

        JLabel lblCourseStartDate = new JLabel("Start Dato (DD/MM/YEAR)");
        lblCourseStartDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseStartDate.setBounds(325, 182, 221, 20);
        add(lblCourseStartDate);

        JTextField txtCourseStartDate = new JTextField();
        txtCourseStartDate.setBounds(325, 215, 269, 20);
        add(txtCourseStartDate);
        txtCourseStartDate.setColumns(10);

        JLabel lblCourseEndDate = new JLabel("Slut Dato (DD-MM-YYYY)");
        lblCourseEndDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseEndDate.setBounds(325, 245, 221, 20);
        add(lblCourseEndDate);

        JTextField txtCourseEndDate = new JTextField();
        txtCourseEndDate.setBounds(325, 276, 269, 20);
        add(txtCourseEndDate);
        txtCourseEndDate.setColumns(10);

        JButton btnBack = new JButton();
        btnBack.addActionListener(e -> mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel"));
        btnBack.setText("Tilbage");
        btnBack.setBounds(175, 364, 120, 34);
        add(btnBack);

        JButton btnCreateCourse = new JButton();
        btnCreateCourse.addActionListener(e -> {
            String name = txtCourseName.getText();
            String description = txtCourseDescription.getText();
            String stringPrice = txtCoursePrice.getText();
            String stringStartDate = txtCourseStartDate.getText();
            String stringEndDate = txtCourseEndDate.getText();

            if(name.equals("") || stringPrice.equals("") || stringStartDate.equals("") || stringEndDate.equals("")) {
                JOptionPane.showMessageDialog(null, "Fejl: Navn, Pris, Starts dato og Sluts dao kan ikke være tomt");
                return;
            }

            double price;
            try {
                price = Double.parseDouble(stringPrice);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                return;
            }

            LocalDate startDate;
            try {
            	int[] intDate = courseController.StringArrToIntArr(stringStartDate.split("-"));
            	startDate = getLocalDate(intDate);
			} catch (IndexOutOfBoundsException _ignore) {
                JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
                return;
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, e2);
                return;
            }

            LocalDate endDate;
            try {
            	int[] intDate = courseController.StringArrToIntArr(stringStartDate.split("-"));
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
	            Course newCourse = new Course(name, price, description, startDate, endDate);
	            try {
	                if(courseController.createCourse(newCourse)){
	                    JOptionPane.showMessageDialog(null, "Kursus oprettet");
	                    CourseMenu courseMenu = new CourseMenu(mainMenu);
	                    mainMenu.mainPanel.add(courseMenu, "course panel");
	                    mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Fejl: Kursus kunne ikke oprettes");
	                }
	            } catch (SQLException ex) {
	                JOptionPane.showMessageDialog(null, ex.getMessage());
	            }
            }
        });
        btnCreateCourse.setText("Opret Kursus");
        btnCreateCourse.setBounds(325, 364, 120, 34);
        add(btnCreateCourse);
    }
}
