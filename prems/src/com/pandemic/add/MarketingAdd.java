package com.pandemic.add;

import com.pandemic.a.MarketingDao;
import com.pandemic.a.SeedlingDao;
import com.pandemic.b.Marketing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interface for adding marketing information.
 * Extends the JFrame class.
 */
public class MarketingAdd extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextField textField1; // Text field for marketing ID
    private JTextField textField2; // Text field for marketer name
    private JComboBox<String> comboBox3; // ComboBox for vaccine names
    private JTextField textField4; // Text field for marketing date
    private JTextField textField5; // Text field for marketing description
    private JButton button1; // Add button
    private JButton button2; // Cancel button

    public MarketingAdd() {
        initComponents();
    }

    /**
     * Initializes the components of the window.
     */
    private void initComponents() {
        label1 = new JLabel("Please enter marketing information:");
        label2 = new JLabel("Marketing ID:");
        label3 = new JLabel("Marketer:");
        label4 = new JLabel("Vaccine:");
        label5 = new JLabel("Marketing Date:");
        label6 = new JLabel("Marketing Description:");

        textField1 = new JTextField();
        textField2 = new JTextField();
        comboBox3 = new JComboBox<>();
        textField4 = new JTextField();
        textField5 = new JTextField();

        button1 = new JButton("Add");
        button2 = new JButton("Cancel");

        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setPreferredSize(new Dimension(400, 350));

        // Adding labels and positioning
        label1.setBounds(30, 20, 300, 30);
        contentPane.add(label1);

        label2.setBounds(30, 60, 120, 30);
        contentPane.add(label2);

        label3.setBounds(30, 100, 120, 30);
        contentPane.add(label3);

        label4.setBounds(30, 140, 120, 30);
        contentPane.add(label4);

        label5.setBounds(30, 180, 120, 30);
        contentPane.add(label5);

        label6.setBounds(30, 220, 200, 30);
        contentPane.add(label6);

        // Adding text fields and ComboBox
        textField1.setBounds(200, 60, 200, 30);
        contentPane.add(textField1);

        textField2.setBounds(200, 100, 200, 30);
        contentPane.add(textField2);

        // Populating the ComboBox with vaccine names
        SeedlingDao seedlingDao = new SeedlingDao();
        comboBox3.setModel(seedlingDao.getComboBoxModel());
        comboBox3.setBounds(200, 140, 200, 30);
        contentPane.add(comboBox3);

        textField4.setBounds(200, 180, 200, 30);
        contentPane.add(textField4);

        textField5.setBounds(200, 220, 200, 30);
        contentPane.add(textField5);

        // Adding buttons and positioning
        button1.setBounds(60, 270, 100, 40);
        contentPane.add(button1);

        button2.setBounds(200, 270, 100, 40);
        contentPane.add(button2);

        // Add button action listener
        button1.addActionListener((ActionEvent e) -> {
            String m_idTemp = textField1.getText();
            String r_idTemp = textField2.getText();
            String m_numberTemp = comboBox3.getSelectedItem().toString();
            String m_dataTemp = textField4.getText();
            String m_resultTemp = textField5.getText();

            if (m_idTemp.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Marketing ID is required!");
                return;
            }

            Marketing marketing = new Marketing(m_idTemp, r_idTemp, m_numberTemp, m_dataTemp, m_resultTemp);
            MarketingDao marketingDao = new MarketingDao();

            try {
                boolean res = marketingDao.addMarketing(marketing);
                if (res) {
                    JOptionPane.showMessageDialog(null, "Marketing information added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add marketing information.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Cancel button action listener
        button2.addActionListener((ActionEvent e) -> {
            JFrame frame = (JFrame) SwingUtilities.getRoot(button2);
            frame.dispose();
        });

        // Adjusting layout and visibility
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        comboBox3.setSelectedIndex(0);
        textField4.setText("");
        textField5.setText("");
    }

    public static void main(String[] args) {
        new MarketingAdd();
    }
}
