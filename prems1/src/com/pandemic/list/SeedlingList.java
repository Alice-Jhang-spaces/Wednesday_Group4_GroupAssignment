package com.pandemic.list;

import com.pandemic.a.SeedlingDao;
import com.pandemic.b.Seedling;
import com.pandemic.list.tablemodel.SeedlingTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * SeedlingList is a JFrame-based GUI for managing vaccines.
 * Users can view, modify, and delete vaccine details.
 */
public class SeedlingList extends JFrame {

    // Constructor to initialize UI components
    public SeedlingList() throws Exception {
        initComponents();
    }

    // Method to initialize UI components
    private void initComponents() throws Exception {
        // UI Components
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel("Vaccine ID:");
        label2 = new JLabel("Production Date:");
        label3 = new JLabel("Description:");
        label4 = new JLabel("Name:");
        label5 = new JLabel("Origin:");
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        button1 = new JButton("Modify");
        button2 = new JButton("Delete");
        button3 = new JButton("Cancel");

        // Layout settings
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        // Configure the table
        table1.setPreferredSize(new Dimension(555, 400));
        table1.setModel(new SeedlingTableModel());
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 495, 130);

        // Configure labels and text fields
        contentPane.add(label1);
        label1.setBounds(50, 155, 100, 25);
        contentPane.add(textField1);
        textField1.setBounds(150, 155, 150, 25);

        contentPane.add(label2);
        label2.setBounds(50, 195, 100, 25);
        contentPane.add(textField4);
        textField4.setBounds(150, 195, 150, 25);

        contentPane.add(label3);
        label3.setBounds(50, 235, 100, 25);
        contentPane.add(textField5);
        textField5.setBounds(150, 235, 150, 25);

        contentPane.add(label4);
        label4.setBounds(255, 155, 100, 25);
        contentPane.add(textField2);
        textField2.setBounds(355, 155, 150, 25);

        contentPane.add(label5);
        label5.setBounds(255, 195, 100, 25);
        contentPane.add(textField3);
        textField3.setBounds(355, 195, 150, 25);

        // Configure buttons
        button1.setBounds(50, 280, 100, 30);
        contentPane.add(button1);
        button2.setBounds(200, 280, 100, 30);
        contentPane.add(button2);
        button3.setBounds(350, 280, 100, 30);
        contentPane.add(button3);

        // Button actions
        button1.addActionListener(e -> modifySeedling());
        button2.addActionListener(e -> deleteSeedling());
        button3.addActionListener(e -> dispose());

        // Add mouse listener for row selection in the table
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

        // Final settings
        contentPane.setPreferredSize(new Dimension(600, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Modify vaccine details
    private void modifySeedling() {
        String id = textField1.getText();
        String name = textField2.getText();
        String origin = textField3.getText();
        String productionDate = textField4.getText();
        String description = textField5.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a vaccine ID to modify.");
            return;
        }

        Seedling seedling = new Seedling(id, name, origin, productionDate, description);
        SeedlingDao seedlingDao = new SeedlingDao();

        try {
            boolean result = seedlingDao.modifySeedling(seedling);
            if (result) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table1.setModel(new SeedlingTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Delete vaccine details
    private void deleteSeedling() {
        String id = textField1.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a vaccine ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this vaccine?");
        if (confirm == JOptionPane.YES_OPTION) {
            Seedling seedling = new Seedling();
            seedling.setM_id(id);
            SeedlingDao seedlingDao = new SeedlingDao();

            try {
                String resultMessage = seedlingDao.deleteSeedling(seedling);
                JOptionPane.showMessageDialog(null, resultMessage);
                table1.setModel(new SeedlingTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new SeedlingList();
    }

    // UI Components
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1, label2, label3, label4, label5;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton button1, button2, button3;
}
