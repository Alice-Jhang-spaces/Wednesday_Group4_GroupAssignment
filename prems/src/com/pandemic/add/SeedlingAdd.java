package com.pandemic.add;

import com.pandemic.a.SeedlingDao;
import com.pandemic.b.Seedling;

import javax.swing.*;
import java.awt.*;

/**
 * Interface class for adding vaccine information.
 * Extends the JFrame class.
 */
public class SeedlingAdd extends JFrame {

    // Component declarations
    private JLabel label1; // Title label
    private JLabel label2; // Vaccine ID label
    private JLabel label3; // Vaccine name label
    private JLabel label4; // Place of production label
    private JLabel label5; // Manufacturing date label
    private JLabel label6; // Vaccine description label
    private JTextField textField1; // Vaccine ID input field
    private JTextField textField2; // Vaccine name input field
    private JTextField textField3; // Place of production input field
    private JTextField textField4; // Manufacturing date input field
    private JTextField textField5; // Vaccine description input field
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    /**
     * Constructor to initialize the interface.
     */
    public SeedlingAdd() {
        initComponents();
    }

    /**
     * Initializes the components of the interface.
     */
    private void initComponents() {
        // Initialize labels
        label1 = new JLabel("Please enter vaccine information:");
        label2 = new JLabel("Vaccine ID:");
        label3 = new JLabel("Name:");
        label4 = new JLabel("Place of Production:");
        label5 = new JLabel("Manufacturing Date:");
        label6 = new JLabel("Description:");

        // Initialize text fields
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();

        // Initialize buttons
        button1 = new JButton("Add");
        button2 = new JButton("Cancel");

        // Set layout and properties for the window
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(450, 350));

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

        textField3.setBounds(180, 140, 200, 30);
        contentPane.add(textField3);

        textField4.setBounds(180, 180, 200, 30);
        contentPane.add(textField4);

        textField5.setBounds(180, 220, 200, 30);
        contentPane.add(textField5);

        // Add button and set its position
        button1.setBounds(80, 280, 100, 40);
        contentPane.add(button1);

        // Action listener for the Add button
        button1.addActionListener(e -> {
            String vaccineId = textField1.getText();
            String name = textField2.getText();
            String placeOfProduction = textField3.getText();
            String manufacturingDate = textField4.getText();
            String description = textField5.getText();

            // Validate that the vaccine ID is not empty
            if (vaccineId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vaccine ID is required!");
                return;
            }

            // Create a new Seedling object
            Seedling seedling = new Seedling(vaccineId, name, placeOfProduction, manufacturingDate, description);

            // Add the Seedling object to the database
            SeedlingDao seedlingDao = new SeedlingDao();
            try {
                boolean result = seedlingDao.addSeedling(seedling);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Vaccine information added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add vaccine information.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Add cancel button and set its position
        button2.setBounds(220, 280, 100, 40);
        contentPane.add(button2);

        // Action listener for the Cancel button
        button2.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getRoot(button2);
            frame.dispose();
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
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    /**
     * Main method to launch the interface.
     */
    public static void main(String[] args) {
        new SeedlingAdd();
    }
}
