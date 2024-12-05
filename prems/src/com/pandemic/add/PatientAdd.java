package com.pandemic.add;

import com.pandemic.a.PatientDao;
import com.pandemic.b.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interface for adding patient information.
 * Extends the JFrame class.
 */
public class PatientAdd extends JFrame {

    // Component declarations
    private JLabel label1; // Title label
    private JLabel label2; // Label for patient ID
    private JLabel label3; // Label for patient name
    private JLabel label4; // Label for patient gender
    private JLabel label5; // Label for patient age
    private JTextField P_id; // Text field for patient ID
    private JTextField p_name; // Text field for patient name
    private JTextField p_sex; // Text field for patient gender
    private JTextField p_age; // Text field for patient age
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    /**
     * Constructor to initialize the interface.
     */
    public PatientAdd() {
        initComponents();
    }

    /**
     * Initializes the components of the interface.
     */
    private void initComponents() {
        // Initializing labels
        label1 = new JLabel("Please enter patient information:");
        label2 = new JLabel("Patient ID:");
        label3 = new JLabel("Patient Name:");
        label4 = new JLabel("Gender:");
        label5 = new JLabel("Age:");

        // Initializing text fields
        P_id = new JTextField();
        p_name = new JTextField();
        p_sex = new JTextField();
        p_age = new JTextField();

        // Initializing buttons
        button1 = new JButton("Add");
        button2 = new JButton("Cancel");

        // Setting layout and window properties
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(400, 400));

        // Adding labels and positioning
        label1.setBounds(50, 20, 300, 30);
        contentPane.add(label1);

        label2.setBounds(50, 70, 150, 30);
        contentPane.add(label2);

        label3.setBounds(50, 110, 150, 30);
        contentPane.add(label3);

        label4.setBounds(50, 150, 150, 30);
        contentPane.add(label4);

        label5.setBounds(50, 190, 150, 30);
        contentPane.add(label5);

        // Adding text fields and positioning
        P_id.setBounds(150, 70, 200, 30);
        contentPane.add(P_id);

        p_name.setBounds(150, 110, 200, 30);
        contentPane.add(p_name);

        p_sex.setBounds(150, 150, 200, 30);
        contentPane.add(p_sex);

        p_age.setBounds(150, 190, 200, 30);
        contentPane.add(p_age);

        // Adding buttons and positioning
        button1.setBounds(80, 250, 100, 40);
        contentPane.add(button1);

        button2.setBounds(220, 250, 100, 40);
        contentPane.add(button2);

        // Add button action listener
        button1.addActionListener((ActionEvent e) -> {
            // Fetching user input from the form
            String p_idTemp = P_id.getText();
            String p_nameTemp = p_name.getText();
            String p_sexTemp = p_sex.getText();
            String p_ageTemp = p_age.getText();

            // Validating Patient ID
            if (p_idTemp.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Patient ID is required!");
                return;
            }

            // Creating a Patient object with the entered details
            Patient patient = new Patient(p_idTemp, p_nameTemp, p_sexTemp, p_ageTemp);
            PatientDao patientDao = new PatientDao();

            try {
                // Adding the patient record to the database
                boolean res = patientDao.addPatient(patient);
                if (res) {
                    JOptionPane.showMessageDialog(null, "Patient information added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add patient information.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Cancel button action listener
        button2.addActionListener((ActionEvent e) -> {
            JFrame frame = (JFrame) SwingUtilities.getRoot(button2);
            frame.dispose(); // Closes the current window
        });

        // Setting layout properties and making the interface visible
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        P_id.setText("");
        p_name.setText("");
        p_sex.setText("");
        p_age.setText("");
    }

    /**
     * Main method to launch the interface.
     */
    public static void main(String[] args) {
        new PatientAdd();
    }
}
