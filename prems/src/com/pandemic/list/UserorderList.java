package com.pandemic.list;

import com.pandemic.a.UserorderDao;
import com.pandemic.b.Userorder;
import com.pandemic.list.tablemodel.UserorderTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * UserorderList is a GUI for managing user orders.
 * Users can view, modify, and delete orders.
 */
public class UserorderList extends JFrame {

    public UserorderList() throws Exception {
        initComponents();
    }

    private void initComponents() throws Exception {
        // Scroll pane and table
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        // Labels
        label1 = new JLabel("Order ID:");
        label2 = new JLabel("Order Date:");
        label3 = new JLabel("Order Description:");
        label4 = new JLabel("Product Name:");
        label5 = new JLabel("Quantity:");

        // Text fields
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();

        // Buttons
        button1 = new JButton("Modify");
        button2 = new JButton("Delete");
        button3 = new JButton("Cancel");

        // Container settings
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        // Table setup
        table1.setPreferredSize(new Dimension(555, 400));
        table1.setModel(new UserorderTableModel());
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 495, 130);

        // Label and field placements
        contentPane.add(label1);
        label1.setBounds(50, 155, 100, 25);
        contentPane.add(textField1);
        textField1.setBounds(150, 155, 150, 25);

        contentPane.add(label2);
        label2.setBounds(50, 195, 100, 25);
        contentPane.add(textField4);
        textField4.setBounds(150, 195, 150, 25);

        contentPane.add(label3);
        label3.setBounds(50, 235, 150, 25);
        contentPane.add(textField5);
        textField5.setBounds(250, 235, 150, 25);

        contentPane.add(label4);
        label4.setBounds(350, 155, 150, 25);
        contentPane.add(textField2);
        textField2.setBounds(450, 155, 150, 25);

        contentPane.add(label5);
        label5.setBounds(350, 195, 100, 25);
        contentPane.add(textField3);
        textField3.setBounds(450, 195, 150, 25);

        // Button placements
        button1.setBounds(40, 280, 100, 30);
        contentPane.add(button1);

        button2.setBounds(190, 280, 100, 30);
        contentPane.add(button2);

        button3.setBounds(345, 280, 100, 30);
        contentPane.add(button3);

        // Button actions
        button1.addActionListener(e -> modifyUserorder());
        button2.addActionListener(e -> deleteUserorder());
        button3.addActionListener(e -> dispose());

        // Table row selection listener
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

        // Finalize layout
        contentPane.setPreferredSize(new Dimension(600, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void modifyUserorder() {
        String id = textField1.getText();
        String productName = textField2.getText();
        String quantity = textField3.getText();
        String orderDate = textField4.getText();
        String description = textField5.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select an order ID to modify.");
            return;
        }

        Userorder userorder = new Userorder(id, productName, quantity, orderDate, description);
        UserorderDao userorderDao = new UserorderDao();

        try {
            if (userorderDao.modifyUserorder(userorder)) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table1.setModel(new UserorderTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void deleteUserorder() {
        String id = textField1.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select an order ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this order?");
        if (confirm == JOptionPane.YES_OPTION) {
            Userorder userorder = new Userorder();
            userorder.setM_id(id);
            UserorderDao userorderDao = new UserorderDao();

            try {
                String resultMessage = userorderDao.deleteUserorder(userorder);
                JOptionPane.showMessageDialog(null, resultMessage);
                table1.setModel(new UserorderTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new UserorderList();
    }

    // UI components
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1, label2, label3, label4, label5;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton button1, button2, button3;
}
