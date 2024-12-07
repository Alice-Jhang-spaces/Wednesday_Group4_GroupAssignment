package com.pandemic.c;

import com.pandemic.a.BaseDao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



/**
 * Displays a list of work requests and allows users to view and update specific details.
 *
 * @author alice
 */
public class WorkRequestList extends JFrame {

    private JTable workRequestTable;
    private JTextField workRequestIDField, requestTypeField, requesterRoleField, requesterOrgField, requesterEntField,
            receiverRoleField, receiverOrgField, receiverEntField, descriptionField;
    private JComboBox<String> statusDropdown;
    private DefaultTableModel tableModel;

    public WorkRequestList() {
        setTitle("Work Request List");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create table model with column headers
        tableModel = new DefaultTableModel(new Object[]{
                "WorkRequestID", "RequestType", "RequesterRole", "RequesterOrganization",
                "RequesterEnterprise", "ReceiverRole", "ReceiverOrganization",
                "ReceiverEnterprise", "Status", "Description"
        }, 0);

        // Initialize table and add to scroll pane
        workRequestTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(workRequestTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and populate data from the database
        populateTable();

        // Form Panel for Details
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(10, 2, 5, 5));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add labels and text fields to detail panel
        detailPanel.add(new JLabel("Work Request ID:"));
        workRequestIDField = new JTextField();
        workRequestIDField.setEditable(false);
        detailPanel.add(workRequestIDField);

        detailPanel.add(new JLabel("Request Type:"));
        requestTypeField = new JTextField();
        requestTypeField.setEditable(false);
        detailPanel.add(requestTypeField);

        detailPanel.add(new JLabel("Requester Role:"));
        requesterRoleField = new JTextField();
        requesterRoleField.setEditable(false);
        detailPanel.add(requesterRoleField);

        detailPanel.add(new JLabel("Requester Organization:"));
        requesterOrgField = new JTextField();
        requesterOrgField.setEditable(false);
        detailPanel.add(requesterOrgField);

        detailPanel.add(new JLabel("Requester Enterprise:"));
        requesterEntField = new JTextField();
        requesterEntField.setEditable(false);
        detailPanel.add(requesterEntField);

        detailPanel.add(new JLabel("Receiver Role:"));
        receiverRoleField = new JTextField();
        receiverRoleField.setEditable(false);
        detailPanel.add(receiverRoleField);

        detailPanel.add(new JLabel("Receiver Organization:"));
        receiverOrgField = new JTextField();
        receiverOrgField.setEditable(false);
        detailPanel.add(receiverOrgField);

        detailPanel.add(new JLabel("Receiver Enterprise:"));
        receiverEntField = new JTextField();
        receiverEntField.setEditable(false);
        detailPanel.add(receiverEntField);

        detailPanel.add(new JLabel("Status:"));
        statusDropdown = new JComboBox<>(new String[]{"Pending", "In Progress", "Completed"});
        detailPanel.add(statusDropdown);

        detailPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        detailPanel.add(descriptionField);

        // Add detail panel to the frame
        add(detailPanel, BorderLayout.SOUTH);

        // Add listener to update details on table row selection
        workRequestTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = workRequestTable.getSelectedRow();
                if (selectedRow >= 0) {
                    populateDetails(selectedRow);
                }
            }
        });

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton updateButton = new JButton("Update");
        JButton refreshButton = new JButton("Refresh");

        updateButton.addActionListener(e -> updateWorkRequest());
        refreshButton.addActionListener(e -> populateTable());

        buttonPanel.add(updateButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    /**
     * Populates the table with work requests from the database.
     */
    private void populateTable() {
        String sql = "SELECT * FROM WorkRequests";
        tableModel.setRowCount(0); // Clear the table
        try (Connection connection = new BaseDao().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                tableModel.addRow(new Object[]{
                        resultSet.getInt("WorkRequestID"),
                        resultSet.getString("RequestType"),
                        resultSet.getString("RequesterRole"),
                        resultSet.getString("RequesterOrganization"),
                        resultSet.getString("RequesterEnterprise"),
                        resultSet.getString("ReceiverRole"),
                        resultSet.getString("ReceiverOrganization"),
                        resultSet.getString("ReceiverEnterprise"),
                        resultSet.getString("Status"),
                        resultSet.getString("Description")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching work requests: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populates the detail form with the selected row's data.
     *
     * @param selectedRow The selected row index
     */
    private void populateDetails(int selectedRow) {
        workRequestIDField.setText(tableModel.getValueAt(selectedRow, 0).toString());
        requestTypeField.setText(tableModel.getValueAt(selectedRow, 1).toString());
        requesterRoleField.setText(tableModel.getValueAt(selectedRow, 2).toString());
        requesterOrgField.setText(tableModel.getValueAt(selectedRow, 3).toString());
        requesterEntField.setText(tableModel.getValueAt(selectedRow, 4).toString());
        receiverRoleField.setText(tableModel.getValueAt(selectedRow, 5).toString());
        receiverOrgField.setText(tableModel.getValueAt(selectedRow, 6).toString());
        receiverEntField.setText(tableModel.getValueAt(selectedRow, 7).toString());
        statusDropdown.setSelectedItem(tableModel.getValueAt(selectedRow, 8).toString());
        descriptionField.setText(tableModel.getValueAt(selectedRow, 9).toString());
    }

    /**
     * Updates the selected work request's status and description in the database.
     */
    private void updateWorkRequest() {
        int selectedRow = workRequestTable.getSelectedRow();
        if (selectedRow >= 0) {
            int workRequestID = Integer.parseInt(workRequestIDField.getText());
            String newStatus = statusDropdown.getSelectedItem().toString();
            String newDescription = descriptionField.getText();

            String sql = "UPDATE WorkRequests SET Status = ?, Description = ? WHERE WorkRequestID = ?";
            try (Connection connection = new BaseDao().getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, newStatus);
                pstmt.setString(2, newDescription);
                pstmt.setInt(3, workRequestID);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated == 1) {
                    JOptionPane.showMessageDialog(this, "Work request updated successfully.");
                    tableModel.setValueAt(newStatus, selectedRow, 8);
                    tableModel.setValueAt(newDescription, selectedRow, 9);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating work request: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a work request to update.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new WorkRequestList();
    }
}
