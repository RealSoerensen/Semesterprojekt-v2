package gui;

import controller.CourseController;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;

public class CreateCourseMenu extends JPanel {
    final CourseController courseController = new CourseController();

    public CreateCourseMenu(MainMenu mainMenu) {
        setLayout(null);
        setSize(626, 515);

        JLabel lblCourseName = new JLabel("Navn:");
        lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseName.setBounds(10, 11, 205, 20);
        add(lblCourseName);

        JTextField txtCourseName = new JTextField();
        txtCourseName.setBounds(10, 42, 205, 20);
        add(txtCourseName);
        txtCourseName.setColumns(10);

        JLabel lblCourseDescription = new JLabel("Beskrivelse:");
        lblCourseDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseDescription.setBounds(10, 73, 205, 20);
        add(lblCourseDescription);

        JTextArea txtCourseDescription = new JTextArea();
        txtCourseDescription.setBounds(10, 104, 205, 82);
        add(txtCourseDescription);

        JLabel lblCoursePrice = new JLabel("Pris:");
        lblCoursePrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCoursePrice.setBounds(225, 11, 217, 20);
        add(lblCoursePrice);

        JTextField txtCoursePrice = new JTextField();
        txtCoursePrice.setBounds(225, 42, 217, 20);
        add(txtCoursePrice);
        txtCoursePrice.setColumns(10);

        JLabel lblCourseStartDate = new JLabel("Start Dato (DD/MM/YEAR)");
        lblCourseStartDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseStartDate.setBounds(221, 73, 221, 20);
        add(lblCourseStartDate);

        JTextField txtCourseStartDate = new JTextField();
        txtCourseStartDate.setBounds(221, 104, 221, 20);
        add(txtCourseStartDate);
        txtCourseStartDate.setColumns(10);

        JLabel lblCourseEndDate = new JLabel("Slut Dato (DD/MM/YEAR)");
        lblCourseEndDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseEndDate.setBounds(221, 135, 221, 20);
        add(lblCourseEndDate);

        JTextField txtCourseEndDate = new JTextField();
        txtCourseEndDate.setBounds(223, 166, 219, 20);
        add(txtCourseEndDate);
        txtCourseEndDate.setColumns(10);

        JButton btnBack = new JButton();
        btnBack.addActionListener(e -> mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel"));
        btnBack.setText("Tilbage");
        btnBack.setBounds(95, 255, 120, 34);
        add(btnBack);

        JButton btnCreateCourse = new JButton();
        btnCreateCourse.addActionListener(e -> {
            JFrame f = new JFrame();
            String name = txtCourseName.getText();
            String description = txtCourseDescription.getText();
            String stringPrice = txtCoursePrice.getText();
            String stringStartDate = txtCourseStartDate.getText();
            String stringEndDate = txtCourseEndDate.getText();

            if(name.equals("") || stringPrice.equals("") || stringStartDate.equals("") || stringEndDate.equals("")) {
                JOptionPane.showMessageDialog(f, "Fejl: Navn, Pris, Starts dato og Sluts dao kan ikke være tomt");
                return;
            }

            double price;
            try {
                price = Double.parseDouble(stringPrice);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(f, e1.getMessage());
                return;
            }

            Date startDate;
            try {
                int[] date = StringArrToIntArr(stringStartDate.split("/"));
                startDate = new Date(date[2], date[1], date[0]);
            } catch (IndexOutOfBoundsException _ignore) {
                JOptionPane.showMessageDialog(f, "Fejl: Dato er skrevet forkert ind");
                return;
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(f, e2);
                return;
            }

            Date endDate;
            try {
                int[] date = StringArrToIntArr(stringStartDate.split("/"));
                endDate = new Date(date[2], date[1], date[0]);
            } catch (IndexOutOfBoundsException _ignore) {
                JOptionPane.showMessageDialog(f, "Fejl: Dato er skrevet forkert ind");
                return;
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(f, e2);
                return;
            }

            Course newCourse = new Course(name, price, description, startDate, endDate);
            try {
                if(courseController.createCourse(newCourse)){
                    JOptionPane.showMessageDialog(f, "Kursus oprettet");
                    mainMenu.cardLayout.show(mainMenu.mainPanel, "course panel");
                } else {
                    JOptionPane.showMessageDialog(f, "Fejl: Kursus kunne ikke oprettes");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(f, ex.getMessage());
            }
        });
        btnCreateCourse.setText("Opret Kursus");
        btnCreateCourse.setBounds(225, 255, 120, 34);
        add(btnCreateCourse);
    }

    private static int[] StringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            int newInt = Integer.parseInt(s[i]);
            if(i == 0){
                newInt -= 1900;
            } else if(i == 1){
                newInt--;
            }
            result[i] = newInt;
        }
        return result;
    }
}
