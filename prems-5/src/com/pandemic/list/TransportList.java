package com.pandemic.list;

import com.pandemic.a.TransportDao;
import com.pandemic.b.Transport;
import com.pandemic.list.tablemodel.TransportTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TransportList is a JFrame-based GUI for managing transportation records.
 * Users can view, modify, and delete transportation records.
 */
public class TransportList extends JFrame {

    // Constructor to initialize the UI components
    public TransportList() throws Exception {
        initComponents();
    }

    // Method to initialize UI components
    private void initComponents() throws Exception {
        // UI Components
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel("Transport ID:");
        label2 = new JLabel("Transport Time:");
        label3 = new JLabel("Description:");
        label4 = new JLabel("Goods:");
        label5 = new JLabel("Status:");
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
        table1.setPreferredSize(new Dimension(700, 440));
        table1.setModel(new TransportTableModel());
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 495, 130);

        // Configure labels and text fields
        contentPane.add(label1);
        label1.setBounds(50, 155, 150, 25);
        contentPane.add(textField1);
        textField1.setBounds(150, 155, 150, 25);

        contentPane.add(label2);
        label2.setBounds(50, 195, 150, 25);
        contentPane.add(textField4);
        textField4.setBounds(150, 195, 150, 25);

        contentPane.add(label3);
        label3.setBounds(50, 235, 150, 25);
        contentPane.add(textField5);
        textField5.setBounds(150, 235, 150, 25);

        contentPane.add(label4);
        label4.setBounds(360, 155, 150, 25);
        contentPane.add(textField2);
        textField2.setBounds(410, 155, 100, 25);

        contentPane.add(label5);
        label5.setBounds(360, 195, 150, 25);
        contentPane.add(textField3);
        textField3.setBounds(410, 195, 100, 25);

        // Configure buttons
        button1.setBounds(50, 280, 100, 30);
        contentPane.add(button1);
        button2.setBounds(200, 280, 100, 30);
        contentPane.add(button2);
        button3.setBounds(360, 280, 100, 30);
        contentPane.add(button3);

        // Button actions
        button1.addActionListener(e -> modifyTransport());
        button2.addActionListener(e -> deleteTransport());
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

    // Method to modify transportation details
    private void modifyTransport() {
        String id = textField1.getText();
        String goods = textField2.getText();
        String status = textField3.getText();
        String transportTime = textField4.getText();
        String description = textField5.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a transport ID to modify.");
            return;
        }

        Transport transport = new Transport(id, goods, status, transportTime, description);
        TransportDao transportDao = new TransportDao();

        try {
            boolean result = transportDao.modifyTransport(transport);
            if (result) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table1.setModel(new TransportTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to delete transportation details
    private void deleteTransport() {
        String id = textField1.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a transport ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this transport?");
        if (confirm == JOptionPane.YES_OPTION) {
            Transport transport = new Transport();
            transport.setM_id(id);
            TransportDao transportDao = new TransportDao();

            try {
                String resultMessage = transportDao.deleteTransport(transport);
                JOptionPane.showMessageDialog(null, resultMessage);
                table1.setModel(new TransportTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new TransportList();
    }

    // UI Components
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1, label2, label3, label4, label5;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton button1, button2, button3;
}
