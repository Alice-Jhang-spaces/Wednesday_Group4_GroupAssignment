package com.pandemic.list;

import com.pandemic.a.DoctorDao;
import com.pandemic.b.Doctor;
import com.pandemic.list.tablemodel.DoctorTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * DoctorList is a JFrame-based GUI application for managing doctor information.
 * It allows users to view, modify, delete, and select doctor details.
 */
public class DoctorList extends JFrame {

    // Constructor to initialize components
    public DoctorList() throws Exception {
        initComponents();
    }

    // Method to initialize and configure UI components
    private void initComponents() throws Exception {
        // Set the layout for the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize components
        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable();
        JLabel labelId = new JLabel("Doctor ID:");
        JLabel labelDeptId = new JLabel("Department ID:");
        JLabel labelName = new JLabel("Doctor Name:");
        JLabel labelTitle = new JLabel("Title:");
        JLabel labelGender = new JLabel("Gender:");
        JLabel labelAge = new JLabel("Age:");
        JLabel labelExperience = new JLabel("Experience (Years):");
        JLabel labelSchool = new JLabel("Graduation School:");
        JTextField textFieldId = new JTextField(15);
        JTextField textFieldDeptId = new JTextField(15);
        JTextField textFieldName = new JTextField(15);
        JTextField textFieldTitle = new JTextField(15);
        JTextField textFieldGender = new JTextField(15);
        JTextField textFieldAge = new JTextField(15);
        JTextField textFieldExperience = new JTextField(15);
        JTextField textFieldSchool = new JTextField(15);
        JButton buttonModify = new JButton("Modify");
        JButton buttonDelete = new JButton("Delete");
        JButton buttonCancel = new JButton("Cancel");

        // Configure the table
        table.setModel(new DoctorTableModel());
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

        // Add labels and fields row-wise
        addLabelAndField(contentPane, gbc, labelId, textFieldId, 0, 1);
        addLabelAndField(contentPane, gbc, labelDeptId, textFieldDeptId, 0, 2);
        addLabelAndField(contentPane, gbc, labelName, textFieldName, 0, 3);
        addLabelAndField(contentPane, gbc, labelTitle, textFieldTitle, 0, 4);

        addLabelAndField(contentPane, gbc, labelGender, textFieldGender, 2, 1);
        addLabelAndField(contentPane, gbc, labelAge, textFieldAge, 2, 2);
        addLabelAndField(contentPane, gbc, labelExperience, textFieldExperience, 2, 3);
        addLabelAndField(contentPane, gbc, labelSchool, textFieldSchool, 2, 4);

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

        // Add action listeners for buttons
        buttonModify.addActionListener(e -> modifyDoctor(table, textFieldId, textFieldDeptId, textFieldName, textFieldGender, textFieldAge, textFieldTitle, textFieldExperience, textFieldSchool));
        buttonDelete.addActionListener(e -> deleteDoctor(table, textFieldId));
        buttonCancel.addActionListener(e -> dispose());

        // Add table row selection listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                textFieldId.setText((String) table.getValueAt(row, 0));
                textFieldDeptId.setText((String) table.getValueAt(row, 1));
                textFieldName.setText((String) table.getValueAt(row, 2));
                textFieldGender.setText((String) table.getValueAt(row, 3));
                textFieldAge.setText((String) table.getValueAt(row, 4));
                textFieldTitle.setText((String) table.getValueAt(row, 5));
                textFieldExperience.setText((String) table.getValueAt(row, 6));
                textFieldSchool.setText((String) table.getValueAt(row, 7));
            }
        });

        // Finalize frame settings
        pack();
        setLocationRelativeTo(null);
        setTitle("Doctor Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to add a label and text field in the layout
    private void addLabelAndField(Container contentPane, GridBagConstraints gbc, JLabel label, JTextField field, int col, int row) {
        gbc.gridx = col;
        gbc.gridy = row;
        contentPane.add(label, gbc);

        gbc.gridx = col + 1;
        contentPane.add(field, gbc);
    }

    // Method to handle the modification of doctor data
    private void modifyDoctor(JTable table, JTextField... fields) {
        try {
            String id = fields[0].getText();
            String deptId = fields[1].getText();
            String name = fields[2].getText();
            String gender = fields[3].getText();
            int age = Integer.parseInt(fields[4].getText());
            String title = fields[5].getText();
            String experience = fields[6].getText();
            String school = fields[7].getText();

            Doctor doctor = new Doctor(id, deptId, name, gender, age, title, experience, school);
            DoctorDao doctorDao = new DoctorDao();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select the doctor ID to modify.");
                return;
            }

            if (doctorDao.modifyDoctor(doctor)) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table.setModel(new DoctorTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to handle the deletion of doctor data
    private void deleteDoctor(JTable table, JTextField fieldId) {
        String id = fieldId.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a doctor ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this doctor?");
        if (confirm == JOptionPane.YES_OPTION) {
            DoctorDao doctorDao = new DoctorDao();
            try {
                Doctor doctor = new Doctor();
                doctor.setD_id(id);
                JOptionPane.showMessageDialog(null, doctorDao.deleteDoctor(doctor));
                table.setModel(new DoctorTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) throws Exception {
        new DoctorList();
    }
}
