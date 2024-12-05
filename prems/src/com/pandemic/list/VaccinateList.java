package com.pandemic.list;

import com.pandemic.a.VaccinateDao;
import com.pandemic.b.Vaccinate;
import com.pandemic.list.tablemodel.VaccinateTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * VaccinateList is a GUI to manage vaccination records.
 * Users can view, modify, and delete vaccination details.
 */
public class VaccinateList extends JFrame {

    public VaccinateList() throws Exception {
        initComponents();
    }

    private void initComponents() throws Exception {
        // Scroll pane and table
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        // Labels
        label1 = new JLabel("Vaccination ID:");
        label2 = new JLabel("Vaccination Date:");
        label3 = new JLabel("Description:");
        label4 = new JLabel("Vaccinated Person:");
        label5 = new JLabel("Vaccine:");

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

        // Container layout
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        // Table setup
        table1.setPreferredSize(new Dimension(580, 440));
        table1.setModel(new VaccinateTableModel());
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 495, 130);

        // Label and field placements
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
        textField2.setBounds(360, 155, 200, 25);

        contentPane.add(label5);
        label5.setBounds(360, 195, 150, 25);
        contentPane.add(textField3);
        textField3.setBounds(360, 195, 200, 25);

        // Button placements
        button1.setBounds(50, 280, 100, 30);
        contentPane.add(button1);
        button2.setBounds(200, 280, 100, 30);
        contentPane.add(button2);
        button3.setBounds(360, 280, 100, 30);
        contentPane.add(button3);

        // Button actions
        button1.addActionListener(e -> modifyVaccination());
        button2.addActionListener(e -> deleteVaccination());
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

        // Final layout adjustments
        contentPane.setPreferredSize(new Dimension(700, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void modifyVaccination() {
        String id = textField1.getText();
        String person = textField2.getText();
        String vaccine = textField3.getText();
        String date = textField4.getText();
        String description = textField5.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a vaccination ID to modify.");
            return;
        }

        Vaccinate vaccinate = new Vaccinate(id, person, vaccine, date, description);
        VaccinateDao vaccinateDao = new VaccinateDao();

        try {
            if (vaccinateDao.modifyVaccinate(vaccinate)) {
                JOptionPane.showMessageDialog(null, "Modification successful.");
                table1.setModel(new VaccinateTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void deleteVaccination() {
        String id = textField1.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a vaccination ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this vaccination?");
        if (confirm == JOptionPane.YES_OPTION) {
            Vaccinate vaccinate = new Vaccinate();
            vaccinate.setM_id(id);
            VaccinateDao vaccinateDao = new VaccinateDao();

            try {
                String resultMessage = vaccinateDao.deleteVaccinate(vaccinate);
                JOptionPane.showMessageDialog(null, resultMessage);
                table1.setModel(new VaccinateTableModel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new VaccinateList();
    }

    // UI components
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1, label2, label3, label4, label5;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton button1, button2, button3;
}
