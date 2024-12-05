package com.pandemic.add;

import com.pandemic.a.DssDao;
import com.pandemic.b.Dss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interface for adding medicine information.
 * Extends the JFrame class.
 */
public class DssAdd extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JTextField d_id;
    private JTextField d_name;
    private JTextField d_type;
    private JTextField d_number;
    private JTextField d_id_id;
    private JTextField d_unit;
    private JTextField d_spec;
    private JTextField d_price;
    private JButton button1;
    private JButton button2;

    public DssAdd() {
        initComponents();
    }

    /**
     * Initializes the components of the window.
     */
    private void initComponents() {
        label1 = new JLabel("Please enter medicine information:");
        label2 = new JLabel("Medicine ID:");
        label3 = new JLabel("Medicine Name:");
        label4 = new JLabel("Medicine Company:");
        label5 = new JLabel("Quantity:");
        label6 = new JLabel("Shelf Code:");
        label7 = new JLabel("Unit:");
        label8 = new JLabel("Specification:");
        label9 = new JLabel("Price:");

        d_id = new JTextField();
        d_name = new JTextField();
        d_type = new JTextField();
        d_number = new JTextField();
        d_id_id = new JTextField();
        d_unit = new JTextField();
        d_spec = new JTextField();
        d_price = new JTextField();

        button1 = new JButton("Add");
        button2 = new JButton("Cancel");

        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(720, 700));

        // Adding labels and positioning
        label1.setBounds(30, 20, 300, 30);
        contentPane.add(label1);

        label2.setBounds(30, 70, 150, 30);
        contentPane.add(label2);

        label3.setBounds(30, 120, 150, 30);
        contentPane.add(label3);

        label4.setBounds(30, 170, 150, 30);
        contentPane.add(label4);

        label5.setBounds(30, 220, 150, 30);
        contentPane.add(label5);

        label6.setBounds(30, 270, 150, 30);
        contentPane.add(label6);

        label7.setBounds(30, 320, 150, 30);
        contentPane.add(label7);

        label8.setBounds(30, 370, 150, 30);
        contentPane.add(label8);

        label9.setBounds(30, 420, 150, 30);
        contentPane.add(label9);

        // Adding text fields and positioning
        d_id.setBounds(180, 70, 280, 30);
        contentPane.add(d_id);

        d_name.setBounds(180, 120, 280, 30);
        contentPane.add(d_name);

        d_type.setBounds(180, 170, 280, 30);
        contentPane.add(d_type);

        d_number.setBounds(180, 220, 280, 30);
        contentPane.add(d_number);

        d_id_id.setBounds(180, 270, 280, 30);
        contentPane.add(d_id_id);

        d_unit.setBounds(180, 320, 280, 30);
        contentPane.add(d_unit);

        d_spec.setBounds(180, 370, 280, 30);
        contentPane.add(d_spec);

        d_price.setBounds(180, 420, 280, 30);
        contentPane.add(d_price);

        // Adding buttons and positioning
        button1.setBounds(120, 480, 100, 40);
        contentPane.add(button1);

        button2.setBounds(300, 480, 100, 40);
        contentPane.add(button2);

        // Add button action listener
        button1.addActionListener((ActionEvent e) -> {
            String d_idTemp = d_id.getText();
            String d_nameTemp = d_name.getText();
            String d_typeTemp = d_type.getText();
            String d_numberTemp = d_number.getText();
            String d_id_idTemp = d_id_id.getText();
            String d_unitTemp = d_unit.getText();
            String d_specTemp = d_spec.getText();
            String d_priceTemp = d_price.getText();

            if (d_idTemp.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Medicine ID is required!");
                return;
            }

            try {
                int quantity = Integer.parseInt(d_numberTemp);

                Dss dss = new Dss(d_idTemp, d_nameTemp, d_typeTemp, quantity, d_id_idTemp, d_unitTemp, d_specTemp, d_priceTemp);
                DssDao dssDao = new DssDao();

                boolean res = dssDao.addDss(dss);

                if (res) {
                    JOptionPane.showMessageDialog(null, "Medicine added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add medicine.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantity must be a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Cancel button action listener
        button2.addActionListener((ActionEvent e) -> {
            JFrame frame = (JFrame) SwingUtilities.getRoot(button2);
            frame.dispose();
        });

        // Finalizing layout
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        d_id.setText("");
        d_name.setText("");
        d_type.setText("");
        d_number.setText("");
        d_id_id.setText("");
        d_unit.setText("");
        d_spec.setText("");
        d_price.setText("");
    }

    public static void main(String[] args) {
        new DssAdd();
    }
}
