package com.pandemic.list;

import com.pandemic.a.RegistrationDao;
import com.pandemic.b.Registration;
import com.pandemic.list.tablemodel.RegistrationTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * RegistrationList is a JFrame-based GUI application for managing registrations.
 * It provides functionalities for viewing, modifying, and deleting registrations.
 */
public class RegistrationList extends JFrame {

    // Constructor to initialize the UI components
    public RegistrationList() throws Exception {
        initComponents();
    }

    // Method to initialize and set up the UI components
    private void initComponents() throws Exception {
        // UI Components
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel("Registration ID:");
        label2 = new JLabel("Patient ID:");
        label3 = new JLabel("Medicine ID:");
        label4 = new JLabel("Distribution Date:");
        label5 = new JLabel("Description:");
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        button1 = new JButton("Modify");
        button2 = new JButton("Delete");
        button3 = new JButton("Cancel");

        // Set layout for the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        // Configure the table
        table1.setPreferredSize(new Dimension(555, 400));
        table1.setModel(new RegistrationTableModel());
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 525, 150);

        // Configure labels and text fields
        contentPane.add(label1);
        label1.setBounds(30, 175, 100, 25);
        contentPane.add(textField1);
        textField1.setBounds(130, 175, 155, 25);

        contentPane.add(label2);
        label2.setBounds(30, 215, 100, 25);
        contentPane.add(textField2);
        textField2.setBounds(130, 215, 155, 25);

        contentPane.add(label3);
        label3.setBounds(30, 255, 100, 25);
        contentPane.add(textField3);
        textField3.setBounds(130, 255, 155, 25);

        contentPane.add(label4);
        label4.setBounds(310, 175, 120, 25);
        contentPane.add(textField4);
        textField4.setBounds(420, 175, 155, 25);

        contentPane.add(label5);
        label5.setBounds(310, 215, 100, 25);
        contentPane.add(textField5);
        textField5.setBounds(420, 215, 155, 25);

        // Configure buttons
        button1.setBounds(55, 300, 100, 30);
        contentPane.add(button1);
        button2.setBounds(225, 300, 100, 30);
        contentPane.add(button2);
        button3.setBounds(395, 300, 100, 30);
        contentPane.add(button3);

        // Button actions
        button1.addActionListener(e -> modifyRegistration());
        button2.addActionListener(e -> deleteRegistration());
        button3.addActionListener(e -> dispose());

        // Add mouse listener to the table for row selection
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.getSelectedRow();
                textField1.setText((String) table1.getValueAt(row, 0));
                textField2.setText((String) table1.getValueAt(row, 1));
                textField3.setText((String) table1.getValueAt(row, 2));
                textField4.setText((String) table1.getValueAt(row, 3));
                textField5.setText((String) table1.getValueAt(row, 4));
            }
        });

        // Set preferred size for the content pane
        contentPane.setPreferredSize(new Dimension(600, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to handle the Modify button action
    private void modifyRegistration() {
        String id = textField1.getText();
        String patientId = textField2.getText();
        String medicineId = textField3.getText();
        String date = textField4.getText();
        String description = textField5.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a registration to modify.");
            return;
        }

        Registration registration = new Registration(id, patientId, medicineId, date, description);
        RegistrationDao registrationDao = new RegistrationDao();

        try {
            boolean result = registrationDao.modifyRegistration(registration);
            if (result) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table1.setModel(new RegistrationTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to handle the Delete button action
    private void deleteRegistration() {
        String id = textField1.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a registration to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this registration?");
        if (confirm == JOptionPane.YES_OPTION) {
            Registration registration = new Registration();
            registration.setR_id(id);
            RegistrationDao registrationDao = new RegistrationDao();

            try {
                String resultMessage = registrationDao.deleteRegistration(registration);
                JOptionPane.showMessageDialog(null, resultMessage);
                table1.setModel(new RegistrationTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new RegistrationList();
    }

    // UI Components
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1, label2, label3, label4, label5;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton button1, button2, button3;
}
