/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Sales;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Business.Business;
import model.CustomerManagement.CustomerProfile;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.ProductManagement.Product;
import model.Supplier.Supplier;

/**
 *
 * @author martta
 */
public class ViewCustomerReportJPanel extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private Business business;

    /**
     * Creates new form ViewCustomerReportJPanel
     */
    public ViewCustomerReportJPanel(Business business,JPanel userProcessContainer) {
        initComponents();
        this.business = business;
        this.userProcessContainer = userProcessContainer;
        
        populateCombo();
        populateProductTable();
    }
    
    public void populateCombo(){
        cmbCustomer.removeAllItems();
        
        for (CustomerProfile cp : business.getCustomerDirectory().getCustomerList()){
            cmbCustomer.addItem(cp);
        }
    }
    
    public void populateProductTable(){
        CustomerProfile selectedcp = (CustomerProfile)cmbCustomer.getSelectedItem();
        
        if(selectedcp == null){
            return;
        }
        
        MasterOrderList masterOrderList = business.getMasterOrderList();
        DefaultTableModel model = (DefaultTableModel) tblOrderList.getModel(); 
        model.setRowCount(0); 
        
            for (Order order : masterOrderList.getOrderList()) {
                if (order.getCustomer().equals(selectedcp)) {
                    for (OrderItem item : order.getOrderItemList()) {
                        Product p = item.getSelectedProduct(); 
                        Object[] row = new Object[4];
                        row[0] = p.getName(); 
                        row[1] = item.getQuantity(); 
                        row[2] = item.getActualPrice();
                        row[3] = item.getQuantity() * item.getActualPrice();

                        model.addRow(row); 
                    }
                }
            }
    }

        
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCustomerName = new javax.swing.JLabel();
        lblCustomerReport = new javax.swing.JLabel();
        lblOrderList = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderList = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        cmbCustomer = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(153, 204, 255));

        lblCustomerName.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblCustomerName.setText("Customer Name:");

        lblCustomerReport.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lblCustomerReport.setForeground(new java.awt.Color(102, 102, 102));
        lblCustomerReport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustomerReport.setText("Customer Report");

        lblOrderList.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblOrderList.setText("Order List");

        tblOrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product Name", "Volume Purchased", "Actual Price", "Amount Paid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblOrderList);

        btnBack.setBackground(new java.awt.Color(255, 255, 204));
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        cmbCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(lblCustomerName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOrderList)
                        .addGap(356, 356, 356))
                    .addComponent(lblCustomerReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnBack)
                .addGap(26, 26, 26)
                .addComponent(lblCustomerReport)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomerName)
                    .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(lblOrderList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
      userProcessContainer.remove(this);
      CardLayout layout = (CardLayout) userProcessContainer.getLayout();
      layout.previous(userProcessContainer);
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void cmbCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCustomerActionPerformed
        // TODO add your handling code here:
        populateProductTable();
    }//GEN-LAST:event_cmbCustomerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox cmbCustomer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCustomerName;
    private javax.swing.JLabel lblCustomerReport;
    private javax.swing.JLabel lblOrderList;
    private javax.swing.JTable tblOrderList;
    // End of variables declaration//GEN-END:variables
}
