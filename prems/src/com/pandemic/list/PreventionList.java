package com.pandemic.list;

import com.pandemic.a.PreventionDao;
import com.pandemic.b.Prevention;
import com.pandemic.list.tablemodel.PreventionTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * PreventionList is a JFrame-based GUI application for managing pandemic records.
 * It allows users to view, modify, delete, and select pandemic details.
 */
public class PreventionList extends JFrame {

    // Constructor to initialize the GUI components
    public PreventionList() throws Exception {
        initComponents();
    }

    // Method to initialize and configure UI components
    private void initComponents() throws Exception {
        // Set layout manager for the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        // Initialize components
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel("Pandemic ID:");
        label2 = new JLabel("Entry Date:");
        label3 = new JLabel("Description:");
        label4 = new JLabel("Title:");
        label5 = new JLabel("Statistics:");
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        button1 = new JButton("Modify");
        button2 = new JButton("Delete");
        button3 = new JButton("Cancel");

        // Configure the table
        table1.setPreferredSize(new Dimension(600, 200));
        table1.setModel(new PreventionTableModel());
        scrollPane1.setViewportView(table1);
        scrollPane1.setBounds(20, 20, 650, 200);
        contentPane.add(scrollPane1);

        // Configure labels and text fields
        int labelWidth = 100;
        int fieldWidth = 200;
        int height = 30;

        label1.setBounds(50, 240, labelWidth, height);
        textField1.setBounds(160, 240, fieldWidth, height);
        contentPane.add(label1);
        contentPane.add(textField1);

        label4.setBounds(400, 240, labelWidth, height);
        textField2.setBounds(500, 240, fieldWidth, height);
        contentPane.add(label4);
        contentPane.add(textField2);

        label2.setBounds(50, 290, labelWidth, height);
        textField4.setBounds(160, 290, fieldWidth, height);
        contentPane.add(label2);
        contentPane.add(textField4);

        label5.setBounds(400, 290, labelWidth, height);
        textField3.setBounds(500, 290, fieldWidth, height);
        contentPane.add(label5);
        contentPane.add(textField3);

        label3.setBounds(50, 340, labelWidth, height);
        textField5.setBounds(160, 340, fieldWidth + 340, height);
        contentPane.add(label3);
        contentPane.add(textField5);

        // Configure buttons
        button1.setBounds(160, 400, 100, height);
        button1.addActionListener(e -> modifyPrevention());
        contentPane.add(button1);

        button2.setBounds(280, 400, 100, height);
        button2.addActionListener(e -> deletePrevention());
        contentPane.add(button2);

        button3.setBounds(400, 400, 100, height);
        button3.addActionListener(e -> dispose());
        contentPane.add(button3);

        // Add mouse listener to the table for selecting rows
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.getSelectedRow(); // Get the selected row index
                textField1.setText((String) table1.getValueAt(row, 0)); // Set pandemic ID
                textField2.setText((String) table1.getValueAt(row, 1)); // Set title
                textField3.setText((String) table1.getValueAt(row, 2)); // Set statistics
                textField4.setText((String) table1.getValueAt(row, 3)); // Set entry date
                textField5.setText((String) table1.getValueAt(row, 4)); // Set description
            }
        });

        // Finalize layout settings
        setTitle("Pandemic Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    // Method to modify a pandemic record
    private void modifyPrevention() {
        String id = textField1.getText();
        String title = textField2.getText();
        String stats = textField3.getText();
        String date = textField4.getText();
        String description = textField5.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a pandemic ID to modify.");
            return;
        }

        Prevention prevention = new Prevention(id, title, stats, date, description);
        PreventionDao preventionDao = new PreventionDao();
        try {
            if (preventionDao.modifyMrescpition(prevention)) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table1.setModel(new PreventionTableModel()); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to delete a pandemic record
    private void deletePrevention() {
        String id = textField1.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a pandemic ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this pandemic?");
        if (confirm == JOptionPane.YES_OPTION) {
            Prevention prevention = new Prevention();
            prevention.setM_id(id);
            PreventionDao preventionDao = new PreventionDao();
            try {
                String result = preventionDao.deletePrevention(prevention);
                JOptionPane.showMessageDialog(null, result);
                table1.setModel(new PreventionTableModel()); // Refresh table
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) throws Exception {
        new PreventionList();
    }

    // Declare UI components
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1, label2, label3, label4, label5;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton button1, button2, button3;
}
