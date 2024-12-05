package com.pandemic.add;

import com.pandemic.a.SeedlingDao;
import com.pandemic.a.VaccinateDao;
import com.pandemic.b.Vaccinate;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for adding vaccination information.
 * Extends JFrame.
 */
public class VaccinateAdd extends JFrame {

    // Components for the interface
    private JLabel label1; // Title label
    private JLabel label2; // Vaccination ID label
    private JLabel label3; // Recipient name label
    private JLabel label4; // Vaccine label
    private JLabel label5; // Vaccination date label
    private JLabel label6; // Description label
    private JTextField textField1; // Vaccination ID input field
    private JTextField textField2; // Recipient name input field
    private JTextField textField4; // Vaccination date input field
    private JTextField textField5; // Description input field
    private JComboBox<String> comboBox3; // ComboBox for vaccine names
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    /**
     * Constructor to initialize the vaccination addition interface.
     */
    public VaccinateAdd() {
        initComponents();
    }

    /**
     * Initializes the components and layout of the interface.
     */
    private void initComponents() {
        // Initialize labels
        label1 = new JLabel("Please enter vaccination information:");
        label2 = new JLabel("Vaccination ID:");
        label3 = new JLabel("Recipient Name:");
        label4 = new JLabel("Vaccine:");
        label5 = new JLabel("Vaccination Date:");
        label6 = new JLabel("Description:");

        // Initialize text fields and ComboBox
        textField1 = new JTextField(); // Vaccination ID
        textField2 = new JTextField(); // Recipient name
        textField4 = new JTextField(); // Vaccination date
        textField5 = new JTextField(); // Description
        comboBox3 = new JComboBox<>();

        // Initialize buttons
        button1 = new JButton("Add");
        button2 = new JButton("Cancel");

        // Set layout and window properties
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(450, 350));

        // Add labels and set their positions
        label1.setBounds(30, 20, 300, 30);
        contentPane.add(label1);

        label2.setBounds(30, 60, 120, 30);
        contentPane.add(label2);

        label3.setBounds(30, 100, 120, 30);
        contentPane.add(label3);

        label4.setBounds(30, 140, 120, 30);
        contentPane.add(label4);

        label5.setBounds(30, 180, 120, 30);
        contentPane.add(label5);

        label6.setBounds(30, 220, 120, 30);
        contentPane.add(label6);

        // Add input fields and ComboBox, set their positions
        textField1.setBounds(150, 60, 220, 30);
        contentPane.add(textField1);

        textField2.setBounds(150, 100, 220, 30);
        contentPane.add(textField2);

        // Populate the ComboBox with vaccine names
        SeedlingDao seedlingDao = new SeedlingDao();
        comboBox3.setModel(seedlingDao.getComboBoxModel());
        comboBox3.setBounds(150, 140, 220, 30);
        contentPane.add(comboBox3);

        textField4.setBounds(150, 180, 220, 30);
        contentPane.add(textField4);

        textField5.setBounds(150, 220, 220, 30);
        contentPane.add(textField5);

        // Add button
        button1.setBounds(80, 270, 100, 40);
        contentPane.add(button1);

        // Action listener for the Add button
        button1.addActionListener(e -> {
            String vaccinationId = textField1.getText(); // Get vaccination ID
            String recipientName = textField2.getText(); // Get recipient name
            String vaccine = comboBox3.getSelectedItem().toString(); // Get selected vaccine name
            String vaccinationDate = textField4.getText(); // Get vaccination date
            String description = textField5.getText(); // Get description

            // Validate that the vaccination ID is not empty
            if (vaccinationId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vaccination ID is required!");
                return;
            }

            // Create a new Vaccinate object
            Vaccinate vaccinate = new Vaccinate(vaccinationId, recipientName, vaccine, vaccinationDate, description);

            // Add the vaccination information to the database
            VaccinateDao vaccinateDao = new VaccinateDao();
            try {
                boolean result = vaccinateDao.addVaccinate(vaccinate);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Vaccination added successfully!");
                    clearFields(); // Clear input fields after successful addition
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add vaccination.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Cancel button
        button2.setBounds(220, 270, 100, 40);
        contentPane.add(button2);

        // Action listener for the Cancel button
        button2.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getRoot(button2);
            frame.dispose(); // Close the current window
        });

        // Adjust layout properties and visibility
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
        new VaccinateAdd();
    }
}
