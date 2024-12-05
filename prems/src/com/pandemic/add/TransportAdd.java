package com.pandemic.add;

import com.pandemic.a.TransportDao;
import com.pandemic.b.Transport;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for adding transportation information.
 * Extends the JFrame class.
 */
public class TransportAdd extends JFrame {

    // Component declarations
    private JLabel label1; // Title label
    private JLabel label2; // Transport ID label
    private JLabel label3; // Cargo label
    private JLabel label4; // Status label
    private JLabel label5; // Transport time label
    private JLabel label6; // Transport description label
    private JTextField textField1; // Transport ID input field
    private JTextField textField2; // Cargo input field
    private JTextField textField3; // Status input field
    private JTextField textField4; // Transport time input field
    private JTextField textField5; // Transport description input field
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    /**
     * Constructor to initialize the interface.
     */
    public TransportAdd() {
        initComponents();
    }

    /**
     * Initializes the components of the interface.
     */
    private void initComponents() {
        // Initialize labels
        label1 = new JLabel("Please enter transportation information:");
        label2 = new JLabel("Transport ID:");
        label3 = new JLabel("Cargo:");
        label4 = new JLabel("Status:");
        label5 = new JLabel("Transport Time:");
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
        button1.setBounds(80, 270, 100, 40);
        contentPane.add(button1);

        // Action listener for the Add button
        button1.addActionListener(e -> {
            String transportId = textField1.getText();
            String cargo = textField2.getText();
            String status = textField3.getText();
            String transportTime = textField4.getText();
            String description = textField5.getText();

            // Validate that the transport ID is not empty
            if (transportId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Transport ID is required!");
                return;
            }

            // Create a new Transport object
            Transport transport = new Transport(transportId, cargo, status, transportTime, description);

            // Add the Transport object to the database
            TransportDao transportDao = new TransportDao();
            try {
                boolean result = transportDao.addTransport(transport);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Transport information added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add transport information.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Add cancel button and set its position
        button2.setBounds(220, 270, 100, 40);
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
        new TransportAdd();
    }
}
