package com.pandemic.add;

import com.pandemic.a.PreventionDao;
import com.pandemic.b.Prevention;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interface for adding pandemic prevention information.
 * Extends the JFrame class.
 */
public class PreventionAdd extends JFrame {

    // Component declarations
    private JLabel label1; // Title label
    private JLabel label2; // Label for prevention ID
    private JLabel label3; // Label for title
    private JLabel label4; // Label for statistics value
    private JLabel label5; // Label for entry date
    private JLabel label6; // Label for detailed description
    private JTextField textField1; // Text field for prevention ID
    private JTextField textField2; // Text field for title
    private JTextField textField3; // Text field for statistics value
    private JTextField textField4; // Text field for entry date
    private JTextField textField5; // Text field for detailed description
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    /**
     * Constructor to initialize the interface.
     */
    public PreventionAdd() {
        initComponents();
    }

    /**
     * Initializes the components of the interface.
     */
    private void initComponents() {
        // Initialize labels
        label1 = new JLabel("Please enter pandemic prevention information:");
        label2 = new JLabel("Prevention ID:");
        label3 = new JLabel("Title:");
        label4 = new JLabel("Statistics Value:");
        label5 = new JLabel("Entry Date:");
        label6 = new JLabel("Detailed Description:");

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
        button1.setBounds(80, 270, 100, 40);
        contentPane.add(button1);

        // Action listener for the Add button
        button1.addActionListener((ActionEvent e) -> {
            // Fetch user input from text fields
            String preventionId = textField1.getText();
            String title = textField2.getText();
            String statsValue = textField3.getText();
            String entryDate = textField4.getText();
            String description = textField5.getText();

            // Validate that prevention ID is not empty
            if (preventionId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prevention ID is required!");
                return;
            }

            // Create a new Prevention object
            Prevention prevention = new Prevention(preventionId, title, statsValue, entryDate, description);

            // Add the Prevention object to the database
            PreventionDao preventionDao = new PreventionDao();
            try {
                boolean result = preventionDao.addPrevention(prevention);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Pandemic prevention information added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add pandemic prevention information.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Add cancel button and set its position
        button2.setBounds(250, 270, 100, 40);
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
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    /**
     * Main method to launch the interface.
     */
    public static void main(String[] args) {
        new PreventionAdd();
    }
}
