package com.pandemic.add;

import com.pandemic.a.MrescriptionDao;
import com.pandemic.b.Mrescription;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interface for adding hospital information.
 * Extends the JFrame class to provide a user interface for adding hospital details.
 */
public class MrescriptionAdd extends JFrame {

    // Component declarations
    private JLabel label1; // Title label
    private JLabel label2; // Label for hospital ID
    private JLabel label3; // Label for hospital name
    private JLabel label4; // Label for hospital address
    private JLabel label5; // Label for establishment date
    private JLabel label6; // Label for detailed description
    private JTextField textField1; // Text field for hospital ID
    private JTextField textField2; // Text field for hospital name
    private JTextField textField3; // Text field for hospital address
    private JTextField textField4; // Text field for establishment date
    private JTextField textField5; // Text field for detailed description
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    // Constructor to initialize the interface
    public MrescriptionAdd() {
        initComponents();
    }

    /**
     * Initializes the components of the MrescriptionAdd interface.
     */
    private void initComponents() {
        // Initializing labels with descriptive text
        label1 = new JLabel("Please enter hospital information:");
        label2 = new JLabel("Hospital ID:");
        label3 = new JLabel("Name:");
        label4 = new JLabel("Address:");
        label5 = new JLabel("Establishment Date:");
        label6 = new JLabel("Detailed Description:");

        // Initializing text fields
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();

        // Initializing buttons
        button1 = new JButton("Add");
        button2 = new JButton("Cancel");

        // Setting layout and window properties
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(450, 400));

        // Adding labels and positioning
        label1.setBounds(30, 20, 400, 30);
        contentPane.add(label1);

        label2.setBounds(30, 70, 150, 30);
        contentPane.add(label2);

        label3.setBounds(30, 110, 150, 30);
        contentPane.add(label3);

        label4.setBounds(30, 150, 150, 30);
        contentPane.add(label4);

        label5.setBounds(30, 190, 150, 30);
        contentPane.add(label5);

        label6.setBounds(30, 230, 200, 30);
        contentPane.add(label6);

        // Adding text fields and positioning
        textField1.setBounds(150, 70, 250, 30);
        contentPane.add(textField1);

        textField2.setBounds(150, 110, 250, 30);
        contentPane.add(textField2);

        textField3.setBounds(150, 150, 250, 30);
        contentPane.add(textField3);

        textField4.setBounds(150, 190, 250, 30);
        contentPane.add(textField4);

        textField5.setBounds(150, 230, 250, 30);
        contentPane.add(textField5);

        // Adding buttons and positioning
        button1.setBounds(100, 280, 100, 40);
        contentPane.add(button1);

        button2.setBounds(250, 280, 100, 40);
        contentPane.add(button2);

        // Add button action listener
        button1.addActionListener((ActionEvent e) -> {
            // Fetching user input from the form
            String m_idTemp = textField1.getText();
            String r_idTemp = textField2.getText();
            String m_numberTemp = textField3.getText();
            String m_dataTemp = textField4.getText();
            String m_resultTemp = textField5.getText();

            // Validating Hospital ID
            if (m_idTemp.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Hospital ID is required!");
                return;
            }

            // Creating a Mrescription object with the entered details
            Mrescription mrescription = new Mrescription(m_idTemp, r_idTemp, m_numberTemp, m_dataTemp, m_resultTemp);
            MrescriptionDao mrescriptionDao = new MrescriptionDao();

            try {
                // Adding the hospital record to the database
                boolean res = mrescriptionDao.addMrescription(mrescription);
                if (res) {
                    JOptionPane.showMessageDialog(null, "Hospital information added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add hospital information.");
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
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    // Main method to launch the interface
    public static void main(String[] args) {
        new MrescriptionAdd();
    }
}
