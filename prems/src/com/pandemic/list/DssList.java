package com.pandemic.list;

import com.pandemic.a.DssDao;
import com.pandemic.b.Dss;
import com.pandemic.list.tablemodel.DssTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * DssList is a JFrame-based GUI application for managing drug information.
 * It allows users to view, modify, delete, and select drug details.
 */
public class DssList extends JFrame {

    // Constructor to initialize the components
    public DssList() throws Exception {
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
        JLabel labelId = new JLabel("Drug ID:");
        JLabel labelName = new JLabel("Drug Name:");
        JLabel labelType = new JLabel("Drug Company:");
        JLabel labelQuantity = new JLabel("Quantity:");
        JLabel labelShelfId = new JLabel("Shelf ID:");
        JLabel labelUnit = new JLabel("Unit:");
        JLabel labelSpec = new JLabel("Specification:");
        JLabel labelPrice = new JLabel("Price:");
        JTextField textFieldId = new JTextField(15);
        JTextField textFieldName = new JTextField(15);
        JTextField textFieldType = new JTextField(15);
        JTextField textFieldQuantity = new JTextField(15);
        JTextField textFieldShelfId = new JTextField(15);
        JTextField textFieldUnit = new JTextField(15);
        JTextField textFieldSpec = new JTextField(15);
        JTextField textFieldPrice = new JTextField(15);
        JButton buttonModify = new JButton("Modify");
        JButton buttonDelete = new JButton("Delete");
        JButton buttonCancel = new JButton("Cancel");

        // Configure the table
        table.setModel(new DssTableModel());
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
        addLabelAndField(contentPane, gbc, labelType, textFieldType, 0, 3);
        addLabelAndField(contentPane, gbc, labelQuantity, textFieldQuantity, 0, 4);

        addLabelAndField(contentPane, gbc, labelShelfId, textFieldShelfId, 2, 1);
        addLabelAndField(contentPane, gbc, labelUnit, textFieldUnit, 2, 2);
        addLabelAndField(contentPane, gbc, labelSpec, textFieldSpec, 2, 3);
        addLabelAndField(contentPane, gbc, labelPrice, textFieldPrice, 2, 4);

        // Buttons
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        contentPane.add(buttonModify, gbc);

        gbc.gridx = 1;
        contentPane.add(buttonDelete, gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(10, 5, 10, 10);
        contentPane.add(buttonCancel, gbc);

        // Add action listeners
        buttonModify.addActionListener(e -> modifyDrug(table, textFieldId, textFieldName, textFieldType, textFieldQuantity, textFieldShelfId, textFieldUnit, textFieldSpec, textFieldPrice));
        buttonDelete.addActionListener(e -> deleteDrug(table, textFieldId));
        buttonCancel.addActionListener(e -> dispose());

        // Table row selection listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                textFieldId.setText((String) table.getValueAt(row, 0));
                textFieldName.setText((String) table.getValueAt(row, 1));
                textFieldType.setText((String) table.getValueAt(row, 2));
                textFieldQuantity.setText((String) table.getValueAt(row, 3));
                textFieldShelfId.setText((String) table.getValueAt(row, 4));
                textFieldUnit.setText((String) table.getValueAt(row, 5));
                textFieldSpec.setText((String) table.getValueAt(row, 6));
                textFieldPrice.setText((String) table.getValueAt(row, 7));
            }
        });

        // Finalize frame settings
        pack();
        setLocationRelativeTo(null);
        setTitle("Drug Management");
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

    // Method to modify drug details
    private void modifyDrug(JTable table, JTextField... fields) {
        try {
            String id = fields[0].getText();
            String name = fields[1].getText();
            String type = fields[2].getText();
            int quantity = Integer.parseInt(fields[3].getText());
            String shelfId = fields[4].getText();
            String unit = fields[5].getText();
            String spec = fields[6].getText();
            String price = fields[7].getText();

            Dss drug = new Dss(id, name, type, quantity, shelfId, unit, spec, price);
            DssDao dssDao = new DssDao();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a drug ID to modify.");
                return;
            }

            if (dssDao.modifyDss(drug)) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table.setModel(new DssTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to delete drug details
    private void deleteDrug(JTable table, JTextField fieldId) {
        String id = fieldId.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a drug ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this drug?");
        if (confirm == JOptionPane.YES_OPTION) {
            DssDao dssDao = new DssDao();
            try {
                Dss drug = new Dss();
                drug.setD_id(id);
                JOptionPane.showMessageDialog(null, dssDao.deleteDss(drug));
                table.setModel(new DssTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) throws Exception {
        new DssList();
    }
}
