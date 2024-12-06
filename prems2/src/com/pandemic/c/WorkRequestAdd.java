/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pandemic.c;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alice
 */

public class WorkRequestAdd extends JFrame {

    private JTextField descriptionField;
    private JComboBox<String> requestTypeDropdown, requesterRoleDropdown, requesterOrgDropdown, requesterEntDropdown;
    private JComboBox<String> receiverRoleDropdown, receiverOrgDropdown, receiverEntDropdown, statusDropdown;

    public WorkRequestAdd() {
        setTitle("Add Work Request");
        setSize(600, 400);
        setLayout(new GridLayout(10, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize dropdowns and labels
        add(new JLabel("Request Type:"));
        requestTypeDropdown = new JComboBox<>(new String[]{
            "Lab Test Request", "Equipment Request", "Pandemic Data Update", "Transport Coordination"
        });
        add(requestTypeDropdown);

        add(new JLabel("Requester Role:"));
        requesterRoleDropdown = new JComboBox<>(new String[]{
            "Employee", "Manager", "Network Admin", "Organization Admin"
        });
        add(requesterRoleDropdown);

        add(new JLabel("Requester Organization:"));
        requesterOrgDropdown = new JComboBox<>(new String[]{
            "Pharmacy", "Healthcare Operations", "IT Department", "Logistics"
        });
        add(requesterOrgDropdown);

        add(new JLabel("Requester Enterprise:"));
        requesterEntDropdown = new JComboBox<>(new String[]{
            "Merck", "WHO", "Fedex"
        });
        add(requesterEntDropdown);

        add(new JLabel("Receiver Role:"));
        receiverRoleDropdown = new JComboBox<>(new String[]{
            "Organization Admin", "Manager", "Employee"
        });
        add(receiverRoleDropdown);

        add(new JLabel("Receiver Organization:"));
        receiverOrgDropdown = new JComboBox<>(new String[]{
            "Research & Development", "Logistics", "Transportation"
        });
        add(receiverOrgDropdown);

        add(new JLabel("Receiver Enterprise:"));
        receiverEntDropdown = new JComboBox<>(new String[]{
            "Merck", "Fedex", "WHO"
        });
        add(receiverEntDropdown);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        add(new JLabel("Status:"));
        statusDropdown = new JComboBox<>(new String[]{"Pending", "In Progress", "Completed"});
        add(statusDropdown);

        // Add Save button
        JButton saveButton = new JButton("Save Work Request");
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Create a new work request
                    WorkRequest workRequest = new WorkRequest(
                            0,
                            requestTypeDropdown.getSelectedItem().toString(),
                            requesterRoleDropdown.getSelectedItem().toString(),
                            requesterOrgDropdown.getSelectedItem().toString(),
                            requesterEntDropdown.getSelectedItem().toString(),
                            receiverRoleDropdown.getSelectedItem().toString(),
                            receiverOrgDropdown.getSelectedItem().toString(),
                            receiverEntDropdown.getSelectedItem().toString(),
                            statusDropdown.getSelectedItem().toString(),
                            descriptionField.getText()
                    );

                    // Save to the database
                    if (workRequest.saveToDatabase()) {
                        JOptionPane.showMessageDialog(null, "Work Request saved successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to save Work Request.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}
