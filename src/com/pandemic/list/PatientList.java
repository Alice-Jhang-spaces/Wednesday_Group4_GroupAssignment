package com.pandemic.list;



import com.pandemic.a.PatientDao;
import com.pandemic.b.Patient;
import com.pandemic.list.tablemodel.PatientTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * PatientList is a JFrame-based GUI application for managing patient information.
 * It allows users to view, modify, delete, and select patient details.
 */
public class PatientList extends JFrame {

    // Constructor to initialize components
    public PatientList() throws Exception {
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
        JLabel labelId = new JLabel("Patient ID:");
        JLabel labelName = new JLabel("Name:");
        JLabel labelSex = new JLabel("Gender:");
        JLabel labelAge = new JLabel("Age:");
        JTextField textFieldId = new JTextField(15);
        JTextField textFieldName = new JTextField(15);
        JTextField textFieldSex = new JTextField(15);
        JTextField textFieldAge = new JTextField(15);
        JButton buttonModify = new JButton("Modify");
        JButton buttonDelete = new JButton("Delete");
        JButton buttonCancel = new JButton("Cancel");

        // Configure the table
        table.setModel(new PatientTableModel());
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
        addLabelAndField(contentPane, gbc, labelName, textFieldName, 2, 1);
        addLabelAndField(contentPane, gbc, labelSex, textFieldSex, 0, 2);
        addLabelAndField(contentPane, gbc, labelAge, textFieldAge, 2, 2);

        // Buttons
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        contentPane.add(buttonModify, gbc);

        gbc.gridx = 1;
        contentPane.add(buttonDelete, gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(10, 5, 10, 10);
        contentPane.add(buttonCancel, gbc);

        // Add action listeners
        buttonModify.addActionListener(e -> modifyPatient(table, textFieldId, textFieldName, textFieldSex, textFieldAge));
        buttonDelete.addActionListener(e -> deletePatient(table, textFieldId));
        buttonCancel.addActionListener(e -> dispose());

        // Table row selection listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                textFieldId.setText((String) table.getValueAt(row, 0));
                textFieldName.setText((String) table.getValueAt(row, 1));
                textFieldSex.setText((String) table.getValueAt(row, 2));
                textFieldAge.setText((String) table.getValueAt(row, 3));
            }
        });

        // Finalize frame settings
        pack();
        setLocationRelativeTo(null);
        setTitle("Patient Management");
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

    // Method to modify patient details
    private void modifyPatient(JTable table, JTextField... fields) {
        try {
            String id = fields[0].getText();
            String name = fields[1].getText();
            String sex = fields[2].getText();
            String age = fields[3].getText();

            Patient patient = new Patient(id, name, sex, age);
            PatientDao patientDao = new PatientDao();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a patient ID to modify.");
                return;
            }

            if (patientDao.modifyPatient(patient)) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table.setModel(new PatientTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to delete patient details
    private void deletePatient(JTable table, JTextField fieldId) {
        String id = fieldId.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a patient ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this patient?");
        if (confirm == JOptionPane.YES_OPTION) {
            PatientDao patientDao = new PatientDao();
            try {
                Patient patient = new Patient();
                patient.setP_id(id);
                JOptionPane.showMessageDialog(null, patientDao.deletePatient(patient));
                table.setModel(new PatientTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) throws Exception {
        new PatientList();
    }
}
