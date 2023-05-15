package gui;

import controller.CourseController;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;

public class CreateCourseMenu extends JDialog {
    CourseController courseController = new CourseController();
    public void run() {
        try {
            CreateCourseMenu dialog = new CreateCourseMenu();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CreateCourseMenu(){
        setBounds(100, 100, 300, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 284, 461);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblCourseName = new JLabel("Navn:");
        lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseName.setBounds(10, 11, 250, 20);
        panel.add(lblCourseName);

        JTextField txtCourseName = new JTextField();
        txtCourseName.setBounds(10, 42, 250, 20);
        panel.add(txtCourseName);
        txtCourseName.setColumns(10);

        JLabel lblCourseDescription = new JLabel("Beskrivelse:");
        lblCourseDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseDescription.setBounds(10, 73, 250, 20);
        panel.add(lblCourseDescription);

        JTextArea txtCourseDescription = new JTextArea();
        txtCourseDescription.setBounds(10, 104, 250, 100);
        panel.add(txtCourseDescription);

        JLabel lblCoursePrice = new JLabel("Pris:");
        lblCoursePrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCoursePrice.setBounds(10, 215, 250, 20);
        panel.add(lblCoursePrice);

        JTextField txtCoursePrice = new JTextField();
        txtCoursePrice.setBounds(10, 246, 250, 20);
        panel.add(txtCoursePrice);
        txtCoursePrice.setColumns(10);

        JLabel lblCourseStartDate = new JLabel("Start Dato (DD/MM/YEAR)");
        lblCourseStartDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseStartDate.setBounds(10, 277, 250, 20);
        panel.add(lblCourseStartDate);

        JTextField txtCourseStartDate = new JTextField();
        txtCourseStartDate.setBounds(10, 308, 250, 20);
        panel.add(txtCourseStartDate);
        txtCourseStartDate.setColumns(10);

        JLabel lblCourseEndDate = new JLabel("Slut Dato (DD/MM/YEAR)");
        lblCourseEndDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCourseEndDate.setBounds(10, 339, 250, 20);
        panel.add(lblCourseEndDate);

        JTextField txtCourseEndDate = new JTextField();
        txtCourseEndDate.setBounds(10, 370, 250, 20);
        panel.add(txtCourseEndDate);
        txtCourseEndDate.setColumns(10);

        JButton btnBack = new JButton();
        btnBack.addActionListener(e -> dispose());
        btnBack.setText("Tilbage");
        btnBack.setBounds(10, 400, 120, 20);
        panel.add(btnBack);

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
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(f, "Fejl: Kursus kunne ikke oprettes");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(f, ex.getMessage());
            }
        });
        btnCreateCourse.setText("Opret Kursus");
        btnCreateCourse.setBounds(154, 400, 120, 20);
        panel.add(btnCreateCourse);
    }

    private static int[] StringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.parseInt(s[i]);
        }
        return result;
    }
}
