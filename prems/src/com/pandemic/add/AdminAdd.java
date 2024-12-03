package com.pandemic.add;

import com.pandemic.a.AdminDao;
import com.pandemic.b.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interface class for adding departments by an administrator.
 * Author: admin
 */
public class AdminAdd extends JFrame {

    private JLabel label1, label2, label3;
    private JTextField textField1, textField2;
    private JButton button1, button2;

    public AdminAdd() {
        initComponents();
    }

    private void initComponents() {
        // Initialize components
        label1 = new JLabel("Please enter department information:");
        label2 = new JLabel("Department ID:");
        label3 = new JLabel("Department Name:");

        textField1 = new JTextField(); // Department ID input field
        textField2 = new JTextField(); // Department name input field

        button1 = new JButton("Add"); // Add button
        button2 = new JButton("Cancel"); // Cancel button

        // Set up the container
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(500, 400));

        // Add and position components
        label1.setBounds(30, 20, 300, 30);
        contentPane.add(label1);

        label2.setBounds(30, 80, 120, 30);
        contentPane.add(label2);

        textField1.setBounds(160, 80, 250, 30);
        contentPane.add(textField1);

        label3.setBounds(30, 140, 150, 30);
        contentPane.add(label3);

        textField2.setBounds(160, 140, 250, 30);
        contentPane.add(textField2);

        button1.setBounds(100, 220, 100, 40);
        contentPane.add(button1);

        button2.setBounds(250, 220, 100, 40);
        contentPane.add(button2);

        // Add button action listener
        button1.addActionListener((ActionEvent e) -> {
            String a_idTemp = textField1.getText().trim(); // Get the department ID entered by the user
            String a_nameTemp = textField2.getText().trim(); // Get the department name entered by the user

            if (a_idTemp.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Department ID is required");
                return;
            }

            Admin admin = new Admin(a_idTemp, a_nameTemp); // Create a new department object
            AdminDao adminDao = new AdminDao(); // Create a new database access object for departments
            boolean res;

            try {
                res = adminDao.addAdmin(admin); // Add the new department object to the database
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while adding department: " + ex.getMessage());
                return;
            }

            if (res) {
                JOptionPane.showMessageDialog(null, "Addition successful");
                textField1.setText(""); // Clear input fields
                textField2.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Addition failed");
            }
        });

        // Cancel button action listener
        button2.addActionListener((ActionEvent e) -> {
            JFrame frame = (JFrame) SwingUtilities.getRoot(button2);
            frame.dispose(); // Close the current interface
        });

        // Adjust layout and finalize frame
        {
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }

        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true); // Set the interface to be visible
    }

    public static void main(String[] args) {
        new AdminAdd(); // Create a new instance of the interface for adding departments
    }
}
