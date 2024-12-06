package com.pandemic.list;

import com.pandemic.a.AdminDao;
import com.pandemic.b.Admin;
import com.pandemic.list.tablemodel.AdminTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * AdminList is a JFrame-based GUI application that manages department information.
 * It includes functionalities for viewing, modifying, deleting, and selecting departments.
 */
public class AdminList extends JFrame {

    // Constructor to initialize components
    public AdminList() throws Exception {
        initComponents();
    }

    // Method to initialize UI components
    private void initComponents() throws Exception {
        // Set layout for the main content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // UI components
        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable();
        JLabel labelID = new JLabel("Department ID:");
        JLabel labelName = new JLabel("Department Name:");
        JTextField textFieldID = new JTextField(15);
        JTextField textFieldName = new JTextField(15);
        JButton buttonModify = new JButton("Modify");
        JButton buttonDelete = new JButton("Delete");
        JButton buttonCancel = new JButton("Cancel");

        // Configure the table
        table.setModel(new AdminTableModel());
        scrollPane.setViewportView(table);

        // Add components to the layout
        // Table
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPane.add(scrollPane, gbc);

        // Department ID label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        contentPane.add(labelID, gbc);

        // Department ID text field
        gbc.gridx = 1;
        contentPane.add(textFieldID, gbc);

        // Department Name label
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(labelName, gbc);

        // Department Name text field
        gbc.gridx = 1;
        contentPane.add(textFieldName, gbc);

        // Modify button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 5);
        contentPane.add(buttonModify, gbc);

        // Delete button
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 5, 10, 5);
        contentPane.add(buttonDelete, gbc);

        // Cancel button
        gbc.gridx = 2;
        gbc.insets = new Insets(10, 5, 10, 10);
        contentPane.add(buttonCancel, gbc);

        // Add action listeners
        buttonModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textFieldID.getText();
                String name = textFieldName.getText();
                Admin admin = new Admin(id, name);
                AdminDao adminDao = new AdminDao();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select the department ID to modify.");
                } else {
                    boolean res = adminDao.modifyAdmin(admin);
                    if (res) {
                        JOptionPane.showMessageDialog(null, "Modification successful.");
                        try {
                            table.setModel(new AdminTableModel());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Modification failed.");
                    }
                }
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this department?");
                if (res == JOptionPane.YES_OPTION) {
                    if (textFieldID.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a department to delete.");
                    } else {
                        Admin admin = new Admin();
                        admin.setA_id(textFieldID.getText());
                        AdminDao adminDao = new AdminDao();
                        try {
                            String msg = adminDao.deleteAdmin(admin);
                            JOptionPane.showMessageDialog(null, msg);
                            table.setModel(new AdminTableModel());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                textFieldID.setText((String) table.getValueAt(row, 0));
                textFieldName.setText((String) table.getValueAt(row, 1));
            }
        });

        // Finalize frame settings
        pack();
        setLocationRelativeTo(null);
        setTitle("Department Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) throws Exception {
        new AdminList();
    }
}
