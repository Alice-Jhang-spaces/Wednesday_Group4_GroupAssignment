package com.pandemic.add;

import com.pandemic.a.DoctorDao;
import com.pandemic.b.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interface for adding doctor information
 */
public class DoctorAdd extends JFrame {

    // UI components
    private JLabel label1, label2, label3, label4, label5, label6, label8, label9, label10;
    private JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8;
    private JButton button1, button2;

    public DoctorAdd() {
        initComponents();
    }

    private void initComponents() {
        // Initialize labels and input fields
        label1 = new JLabel("Please enter doctor information:");
        label2 = new JLabel("Doctor ID:");
        label3 = new JLabel("Department ID:");
        label4 = new JLabel("Doctor Name:");
        label5 = new JLabel("Gender:");
        label6 = new JLabel("Age:");
        label8 = new JLabel("Graduation School:");
        label9 = new JLabel("Years of Experience:");
        label10 = new JLabel("Title:");

        textField1 = new JTextField(); // Doctor ID input field
        textField2 = new JTextField(); // Department ID input field
        textField3 = new JTextField(); // Doctor name input field
        textField4 = new JTextField(); // Doctor gender input field
        textField5 = new JTextField(); // Doctor age input field
        textField6 = new JTextField(); // Doctor title input field
        textField7 = new JTextField(); // Doctor graduation school input field
        textField8 = new JTextField(); // Doctor years of experience input field

        button1 = new JButton("Add"); // Add button
        button2 = new JButton("Cancel"); // Cancel button

        // Set up the container
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(500, 500));

        // Add and position components
        label1.setBounds(30, 20, 400, 30);
        contentPane.add(label1);

        label2.setBounds(30, 70, 120, 30);
        contentPane.add(label2);

        textField1.setBounds(180, 70, 250, 30);
        contentPane.add(textField1);

        label3.setBounds(30, 110, 120, 30);
        contentPane.add(label3);

        textField2.setBounds(180, 110, 250, 30);
        contentPane.add(textField2);

        label4.setBounds(30, 150, 120, 30);
        contentPane.add(label4);

        textField3.setBounds(180, 150, 250, 30);
        contentPane.add(textField3);

        label5.setBounds(30, 190, 120, 30);
        contentPane.add(label5);

        textField4.setBounds(180, 190, 250, 30);
        contentPane.add(textField4);

        label6.setBounds(30, 230, 120, 30);
        contentPane.add(label6);

        textField5.setBounds(180, 230, 250, 30);
        contentPane.add(textField5);

        label10.setBounds(30, 270, 120, 30);
        contentPane.add(label10);

        textField6.setBounds(180, 270, 250, 30);
        contentPane.add(textField6);

        label8.setBounds(30, 310, 150, 30);
        contentPane.add(label8);

        textField7.setBounds(180, 310, 250, 30);
        contentPane.add(textField7);

        label9.setBounds(30, 350, 150, 30);
        contentPane.add(label9);

        textField8.setBounds(180, 350, 250, 30);
        contentPane.add(textField8);

        button1.setBounds(100, 400, 100, 40);
        contentPane.add(button1);

        button2.setBounds(250, 400, 100, 40);
        contentPane.add(button2);

        // Add button action listener
        button1.addActionListener((ActionEvent e) -> {
            String doctorId = textField1.getText().trim();
            String departmentId = textField2.getText().trim();
            String doctorName = textField3.getText().trim();
            String gender = textField4.getText().trim();
            int age;
            try {
                age = Integer.parseInt(textField5.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid age");
                return;
            }
            String title = textField6.getText().trim();
            String graduationSchool = textField7.getText().trim();
            int yearsOfExperience;
            try {
                yearsOfExperience = Integer.parseInt(textField8.getText().trim());
                if (yearsOfExperience < 0) {
                    JOptionPane.showMessageDialog(null, "Years of Experience cannot be negative");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for Years of Experience");
                return;
            }

            if (doctorId.isEmpty() || departmentId.isEmpty() || doctorName.isEmpty() || gender.isEmpty() || title.isEmpty() || graduationSchool.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required");
                return;
            }

            Doctor doctor = new Doctor(doctorId, departmentId, doctorName, gender, age, title, graduationSchool, String.valueOf(yearsOfExperience));
            DoctorDao doctorDao = new DoctorDao();
            boolean res;

            try {
                res = doctorDao.addDoctor(doctor);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while adding doctor: " + ex.getMessage());
                return;
            }

            if (res) {
                JOptionPane.showMessageDialog(null, "Doctor added successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add doctor");
            }
        });


        // Cancel button action listener
        button2.addActionListener((ActionEvent e) -> {
            JFrame frame = (JFrame) SwingUtilities.getRoot(button2);
            frame.dispose();
        });

        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }

    public static void main(String[] args) {
        new DoctorAdd();
    }
}
