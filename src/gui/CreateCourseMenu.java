package gui;

import controller.CourseController;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;

import static controller.DateController.getLocalDate;

public class CreateCourseMenu extends JPanel {
    final CourseController courseController = new CourseController();

    public CreateCourseMenu(MainMenu mainMenu) throws SQLException {
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

        JLabel lblCourseStartDate = new JLabel("Start Dato");
        lblCourseStartDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseStartDate.setBounds(325, 182, 221, 20);
        add(lblCourseStartDate);

        JTextField txtCourseStartDate = new JTextField();
        txtCourseStartDate.setBounds(325, 215, 269, 20);
        add(txtCourseStartDate);
        txtCourseStartDate.setColumns(10);
        txtCourseStartDate.setText(LocalDate.now().toString());

        JLabel lblCourseEndDate = new JLabel("Slut Dato");
        lblCourseEndDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseEndDate.setBounds(325, 245, 221, 20);
        add(lblCourseEndDate);

        JTextField txtCourseEndDate = new JTextField();
        txtCourseEndDate.setBounds(325, 276, 269, 20);
        add(txtCourseEndDate);
        txtCourseEndDate.setColumns(10);
        txtCourseEndDate.setText(LocalDate.now().toString());

        JButton btnBack = new JButton();
        btnBack.addActionListener(e -> mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel"));
        btnBack.setText("Tilbage");
        btnBack.setBounds(175, 364, 120, 34);
        add(btnBack);

        JButton btnCreateCourse = new JButton();
        btnCreateCourse.addActionListener(e -> {
        	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            String name = txtCourseName.getText();
            String description = txtCourseDescription.getText();
            String stringPrice = txtCoursePrice.getText();
            String stringStartDate = txtCourseStartDate.getText();
            String stringEndDate = txtCourseEndDate.getText();

            Course course = createCourse(name, description, stringPrice, stringStartDate, stringEndDate);
            if (course != null) {
                JOptionPane.showMessageDialog(null, "Kursus oprettet");
                CourseMenu courseMenu;
                try {
                    courseMenu = new CourseMenu(mainMenu);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fejl: Kan ikke åbne kursus menu");
                    return;
                }
                mainMenu.mainPanel.add(courseMenu, "course panel");
                mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel");
            } else {
                JOptionPane.showMessageDialog(null, "Fejl: Kursus kunne ikke oprettes");
            }
        });
        btnCreateCourse.setText("Opret Kursus");
        btnCreateCourse.setBounds(325, 364, 120, 34);
        add(btnCreateCourse);
    }

    private Course createCourse(String name, String description, String stringPrice, String stringStartDate,
            String stringEndDate) {
        if (name.equals("") || stringPrice.equals("") || stringStartDate.equals("") || stringEndDate.equals("")) {
            JOptionPane.showMessageDialog(null, "Fejl: Navn, Pris, Starts dato og Sluts dao kan ikke være tomt");
            return null;
        }

        double price;
        try {
            price = Double.parseDouble(stringPrice);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            return null;
        }

        LocalDate startDate;
        try {
            int[] intDate = courseController.StringArrToIntArr(stringStartDate.split("-"));
            startDate = getLocalDate(intDate);
        } catch (IndexOutOfBoundsException _ignore) {
            JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
            return null;
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
            return null;
        }

        LocalDate endDate;
        try {
            int[] intDate = courseController.StringArrToIntArr(stringStartDate.split("-"));
            endDate = getLocalDate(intDate);
        } catch (IndexOutOfBoundsException _ignore) {
            JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
            return null;
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
            return null;
        }
        if (endDate == null || startDate == null || startDate.isAfter(endDate)) {
            JOptionPane.showMessageDialog(null, "Fejl: Dato er skrevet forkert ind");
            return null;
        }
        Course course = new Course(name, price, description, startDate, endDate);
        try {
            course = courseController.createCourse(course);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return course;
    }
}
