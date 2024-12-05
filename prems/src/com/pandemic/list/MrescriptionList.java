package com.pandemic.list;

import com.pandemic.a.MrescriptionDao;
import com.pandemic.b.Mrescription;
import com.pandemic.list.tablemodel.MrescriptionTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MrescriptionList is a JFrame-based GUI application for managing hospital information.
 * It allows users to view, modify, delete, and select hospital details.
 */
public class MrescriptionList extends JFrame {

    // Constructor to initialize the components
    public MrescriptionList() throws Exception {
        initComponents();
    }

    // Method to initialize and configure UI components
    private void initComponents() throws Exception {
        // Set layout for the main content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize components
        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable();
        JLabel labelId = new JLabel("Hospital ID:");
        JLabel labelName = new JLabel("Name:");
        JLabel labelAddress = new JLabel("Address:");
        JLabel labelDate = new JLabel("Established Date:");
        JLabel labelDescription = new JLabel("Description:");
        JTextField textFieldId = new JTextField(15);
        JTextField textFieldName = new JTextField(15);
        JTextField textFieldAddress = new JTextField(15);
        JTextField textFieldDate = new JTextField(15);
        JTextField textFieldDescription = new JTextField(15);
        JButton buttonModify = new JButton("Modify");
        JButton buttonDelete = new JButton("Delete");
        JButton buttonCancel = new JButton("Cancel");

        // Configure the table
        table.setModel(new MrescriptionTableModel());
        scrollPane.setViewportView(table);

        // Add components to the layout
        // Table
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPane.add(scrollPane, gbc);

        // Labels and TextFields
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 10, 5, 5);

        addLabelAndField(contentPane, gbc, labelId, textFieldId, 0, 1);
        addLabelAndField(contentPane, gbc, labelName, textFieldName, 0, 2);
        addLabelAndField(contentPane, gbc, labelAddress, textFieldAddress, 0, 3);
        addLabelAndField(contentPane, gbc, labelDate, textFieldDate, 2, 1);
        addLabelAndField(contentPane, gbc, labelDescription, textFieldDescription, 2, 2);

        // Buttons
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        contentPane.add(buttonModify, gbc);

        gbc.gridx = 1;
        contentPane.add(buttonDelete, gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(10, 5, 10, 10);
        contentPane.add(buttonCancel, gbc);

        // Add action listeners
        buttonModify.addActionListener(e -> modifyHospital(table, textFieldId, textFieldName, textFieldAddress, textFieldDate, textFieldDescription));
        buttonDelete.addActionListener(e -> deleteHospital(table, textFieldId));
        buttonCancel.addActionListener(e -> dispose());

        // Table row selection listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                textFieldId.setText((String) table.getValueAt(row, 0));
                textFieldName.setText((String) table.getValueAt(row, 1));
                textFieldAddress.setText((String) table.getValueAt(row, 2));
                textFieldDate.setText((String) table.getValueAt(row, 3));
                textFieldDescription.setText((String) table.getValueAt(row, 4));
            }
        });

        // Finalize frame settings
        pack();
        setLocationRelativeTo(null);
        setTitle("Hospital Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to add labels and text fields
    private void addLabelAndField(Container contentPane, GridBagConstraints gbc, JLabel label, JTextField field, int col, int row) {
        gbc.gridx = col;
        gbc.gridy = row;
        contentPane.add(label, gbc);

        gbc.gridx = col + 1;
        contentPane.add(field, gbc);
    }

    // Method to modify hospital details
    private void modifyHospital(JTable table, JTextField... fields) {
        try {
            String id = fields[0].getText();
            String name = fields[1].getText();
            String address = fields[2].getText();
            String date = fields[3].getText();
            String description = fields[4].getText();

            Mrescription hospital = new Mrescription(id, name, address, date, description);
            MrescriptionDao hospitalDao = new MrescriptionDao();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a hospital ID to modify.");
                return;
            }

            if (hospitalDao.modifyMrescpition(hospital)) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table.setModel(new MrescriptionTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to delete hospital details
    private void deleteHospital(JTable table, JTextField fieldId) {
        String id = fieldId.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a hospital ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this hospital?");
        if (confirm == JOptionPane.YES_OPTION) {
            MrescriptionDao hospitalDao = new MrescriptionDao();
            try {
                Mrescription hospital = new Mrescription();
                hospital.setM_id(id);
                JOptionPane.showMessageDialog(null, hospitalDao.deleteMrescription(hospital));
                table.setModel(new MrescriptionTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) throws Exception {
        new MrescriptionList();
    }
}
