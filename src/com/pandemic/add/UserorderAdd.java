package com.pandemic.add;

import com.pandemic.a.SeedlingDao;
import com.pandemic.a.UserorderDao;
import com.pandemic.b.Userorder;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for adding user orders.
 * Extends JFrame.
 */
public class UserorderAdd extends JFrame {

    // Components for the interface
    private JLabel label1; // Title label
    private JLabel label2; // Order ID label
    private JLabel label3; // Product name label
    private JLabel label4; // Quantity label
    private JLabel label5; // Order date label
    private JLabel label6; // Order description label
    private JTextField textField1; // Order ID input field
    private JTextField textField3; // Quantity input field
    private JTextField textField4; // Order date input field
    private JTextField textField5; // Order description input field
    private JComboBox<String> comboBox3; // ComboBox for product names
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    /**
     * Constructor to initialize the user order addition interface.
     */
    public UserorderAdd() {
        initComponents();
    }

    /**
     * Initializes the components and layout of the interface.
     */
    private void initComponents() {
        // Initialize labels
        label1 = new JLabel("Please enter order information:");
        label2 = new JLabel("Order ID:");
        label3 = new JLabel("Product Name:");
        label4 = new JLabel("Quantity:");
        label5 = new JLabel("Order Date:");
        label6 = new JLabel("Description:");

        // Initialize text fields and ComboBox
        textField1 = new JTextField(); // Order ID
        textField3 = new JTextField(); // Quantity
        textField4 = new JTextField(); // Order date
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

        // Populate the ComboBox with product names
        SeedlingDao seedlingDao = new SeedlingDao();
        comboBox3.setModel(seedlingDao.getComboBoxModel());
        comboBox3.setBounds(150, 100, 220, 30);
        contentPane.add(comboBox3);

        textField3.setBounds(150, 140, 220, 30);
        contentPane.add(textField3);

        textField4.setBounds(150, 180, 220, 30);
        contentPane.add(textField4);

        textField5.setBounds(150, 220, 220, 30);
        contentPane.add(textField5);

        // Add button
        button1.setBounds(80, 270, 100, 40);
        contentPane.add(button1);

        // Action listener for the Add button
        button1.addActionListener(e -> {
            String orderId = textField1.getText(); // Get Order ID
            String productName = comboBox3.getSelectedItem().toString(); // Get selected product name
            String quantity = textField3.getText(); // Get quantity
            String orderDate = textField4.getText(); // Get order date
            String description = textField5.getText(); // Get description

            // Validate that the order ID is not empty
            if (orderId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Order ID is required!");
                return;
            }

            // Create a new Userorder object
            Userorder userorder = new Userorder(orderId, productName, quantity, orderDate, description);

            // Add the Userorder to the database
            UserorderDao userorderDao = new UserorderDao();
            try {
                boolean result = userorderDao.addUserorder(userorder);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Order added successfully!");
                    clearFields(); // Clear input fields after successful addition
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add order.");
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
        comboBox3.setSelectedIndex(0);
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    /**
     * Main method to launch the interface.
     */
    public static void main(String[] args) {
        new UserorderAdd();
    }
}
