package com.pandemic.add;

import com.pandemic.a.DssDao;
import com.pandemic.a.RegistrationDao;
import com.pandemic.b.Registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interface class for adding distribution records.
 * Extends the JFrame class.
 */
public class RegistrationAdd extends JFrame {

    // Component declarations
    private JLabel label1; // Title label
    private JLabel label2; // Label for distribution ID
    private JLabel label3; // Label for patient ID
    private JLabel label4; // Label for medicine
    private JLabel label5; // Label for distribution date
    private JLabel label6; // Label for description
    private JTextField textField1; // Text field for distribution ID
    private JTextField textField2; // Text field for patient ID
    private JTextField textField4; // Text field for distribution date
    private JTextField textField5; // Text field for description
    private JComboBox<String> comboBox3; // ComboBox for medicine names
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    /**
     * Constructor to initialize the interface.
     */
    public RegistrationAdd() {
        initComponents();
    }

    /**
     * Initializes the components of the interface.
     */
    private void initComponents() {
        // Initialize labels
        label1 = new JLabel("Please enter distribution information:");
        label2 = new JLabel("Distribution ID:");
        label3 = new JLabel("Patient ID:");
        label4 = new JLabel("Medicine:");
        label5 = new JLabel("Distribution Date:");
        label6 = new JLabel("Description:");

        // Initialize text fields
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();

        // Initialize ComboBox for medicine names
        comboBox3 = new JComboBox<>();
        DssDao dssDao = new DssDao(); // Populate ComboBox with data
        comboBox3.setModel(dssDao.getComboBoxModel());

        // Initialize buttons
        button1 = new JButton("Add");
        button2 = new JButton("Cancel");

        // Set layout and properties for the window
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(450, 400));

        // Add labels and set their positions
        label1.setBounds(30, 20, 300, 30);
        contentPane.add(label1);

        label2.setBounds(30, 60, 150, 30);
        contentPane.add(label2);

        label3.setBounds(30, 100, 150, 30);
        contentPane.add(label3);

        label4.setBounds(30, 140, 150, 30);
        contentPane.add(label4);

        label5.setBounds(30, 180, 150, 30);
        contentPane.add(label5);

        label6.setBounds(30, 220, 150, 30);
        contentPane.add(label6);

        // Add text fields and set their positions
        textField1.setBounds(180, 60, 200, 30);
        contentPane.add(textField1);

        textField2.setBounds(180, 100, 200, 30);
        contentPane.add(textField2);

        comboBox3.setBounds(180, 140, 200, 30);
        contentPane.add(comboBox3);

        textField4.setBounds(180, 180, 200, 30);
        contentPane.add(textField4);

        textField5.setBounds(180, 220, 200, 30);
        contentPane.add(textField5);

        // Add button and set its position
        button1.setBounds(80, 280, 100, 40);
        contentPane.add(button1);

        // Action listener for the Add button
        button1.addActionListener((ActionEvent e) -> {
            String distributionId = textField1.getText();
            String patientId = textField2.getText();
            String medicine = comboBox3.getSelectedItem().toString();
            String distributionDate = textField4.getText();
            String description = textField5.getText();

            // Validate that the distribution ID is not empty
            if (distributionId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Distribution ID is required!");
                return;
            }

            // Create a new Registration object
            Registration registration = new Registration(distributionId, patientId, medicine, distributionDate, description);

            // Add the Registration object to the database
            RegistrationDao registrationDao = new RegistrationDao();
            try {
                boolean result = registrationDao.addRegistration(registration);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Distribution record added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add distribution record.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Add cancel button and set its position
        button2.setBounds(220, 280, 100, 40);
        contentPane.add(button2);

        // Action listener for the Cancel button
        button2.addActionListener((ActionEvent e) -> {
            JFrame frame = (JFrame) SwingUtilities.getRoot(button2);
            frame.dispose();
        });

        // Set layout properties and visibility
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        comboBox3.setSelectedIndex(0);
        textField4.setText("");
        textField5.setText("");
    }

    /**
     * Main method to launch the interface.
     */
    public static void main(String[] args) {
        new RegistrationAdd();
    }
}
