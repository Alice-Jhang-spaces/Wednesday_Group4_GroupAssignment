package com.pandemic.c;

import javax.swing.*;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Update Personal Details Window
 */
public class PatientDetails extends JFrame {

    private JTextArea addressField; // Larger area for address input
    private JTextArea familyMemberField; // Larger area for family member details
    private JButton saveButton; // Button to save details
    private JButton cancelButton; // Button to cancel action

    public PatientDetails() {
        // Set window properties
        setTitle("Update Personal Details");
        setSize(600, 400); // Increase window size for better layout
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create panel and layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Increase spacing between components

        // Add address label and field
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font for better readability
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        panel.add(addressLabel, gbc);

        addressField = new JTextArea(5, 30); // Multi-line text area for address
        addressField.setLineWrap(true); // Enable line wrapping
        addressField.setWrapStyleWord(true); // Wrap at word boundaries
        addressField.setFont(new Font("Arial", Font.PLAIN, 14)); // Consistent font style
        JScrollPane addressScrollPane = new JScrollPane(addressField); // Add scrollable area
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(addressScrollPane, gbc);

        // Add family member label and field
        JLabel familyMemberLabel = new JLabel("Family Member Details:");
        familyMemberLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(familyMemberLabel, gbc);

        familyMemberField = new JTextArea(5, 30); // Multi-line text area for family member details
        familyMemberField.setLineWrap(true);
        familyMemberField.setWrapStyleWord(true);
        familyMemberField.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane familyMemberScrollPane = new JScrollPane(familyMemberField);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(familyMemberScrollPane, gbc);

        // Add save button
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(saveButton, gbc);

        // Add cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cancelButton, gbc);

        // Add panel to frame
        add(panel);

        // Add action listeners for buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = addressField.getText().trim();
                String familyMemberDetails = familyMemberField.getText().trim();

                if (address.isEmpty() || familyMemberDetails.isEmpty()) {
                    JOptionPane.showMessageDialog(PatientDetails.this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Save logic here (e.g., update the database)
                JOptionPane.showMessageDialog(PatientDetails.this, "Details saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the window
            }
        });

        cancelButton.addActionListener(e -> dispose()); // Close the window on cancel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientDetails().setVisible(true));
    }
}