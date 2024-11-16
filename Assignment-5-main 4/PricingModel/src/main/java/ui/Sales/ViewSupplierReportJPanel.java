/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Sales;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Business.Business;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.ProductManagement.Product;
import model.Supplier.Supplier;

/**
 *
 * @author martta
 */
public class ViewSupplierReportJPanel extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private Business business;
    

    /**
     * Creates new form ViewSupplierReportJPanel
     */
    public ViewSupplierReportJPanel(Business business,JPanel userProcessContainer) {
        initComponents();
        this.business = business;
        this.userProcessContainer = userProcessContainer;
        
        populateCombo();
        populateProductTable();
        
    }
    
    public void populateCombo(){
        cmbSupplier.removeAllItems();
        
        for (Supplier s : business.getSupplierDirectory().getSupplierList()){
            cmbSupplier.addItem(s);
        }
    }
    
    private int calculateAverageActualPrice(Product product, MasterOrderList masterOrderList) {
        int totalPrice = 0;
        int totalQuantity = 0;
        for (Order order : masterOrderList.getOrderList()) {
            for (OrderItem item : order.getOrderItemList()) {
                if (item.getSelectedProduct().equals(product)) {
                    totalPrice += item.getActualPrice() * item.getQuantity();
                    totalQuantity += item.getQuantity();
                }
            }
        }
        return totalQuantity > 0 ? totalPrice / totalQuantity : 0;
    }
    
    public int calculateProductSalesVolume(Product product, MasterOrderList masterOrderList) {
        int salesVolume = 0;
        for (Order order : masterOrderList.getOrderList()) {
            for (OrderItem item : order.getOrderItemList()) {
                if (item.getSelectedProduct().equals(product)) {
                    salesVolume += item.getActualPrice() * item.getQuantity();
                }
            }
        }
        return salesVolume;
    }
    
    public int calculateOrderedQuantityForProduct(Product product, MasterOrderList masterOrderList) {
        int orderedQuantity = 0;
        for (Order order : masterOrderList.getOrderList()) {
            for (OrderItem item : order.getOrderItemList()) {
                if (item.getSelectedProduct().equals(product)) {
                    orderedQuantity += item.getQuantity();
                }
            }
        }
        return orderedQuantity;
    }
    
    public void populateProductTable() {
        Supplier selectedSupplier = (Supplier) cmbSupplier.getSelectedItem();
        
        if(selectedSupplier == null){
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) tblProductList.getModel();
        model.setRowCount(0);   
            for(Product p : selectedSupplier.getProductCatalog().getProductList()){
                    Object row[] = new Object[6];
                    row[0] = p.getName();
                    row[1] = p.getStockQuantity();// availability
                    row[2] = p.getTargetPrice();// Target Price
                    row[3] = calculateAverageActualPrice(p, business.getMasterOrderList());// Actual Price
                    row[4] = calculateOrderedQuantityForProduct(p,business.getMasterOrderList()); // ordered quantity
                    row[5] = calculateProductSalesVolume(p, business.getMasterOrderList());// Sales Volume
                    
                    model.addRow(row);                                
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

        lblSupplierReport = new javax.swing.JLabel();
        lblSupplierName = new javax.swing.JLabel();
        lblProductList = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductList = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        cmbSupplier = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(204, 204, 255));

        lblSupplierReport.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lblSupplierReport.setForeground(new java.awt.Color(51, 51, 51));
        lblSupplierReport.setText("Supplier Report");

        lblSupplierName.setBackground(new java.awt.Color(163, 199, 203));
        lblSupplierName.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblSupplierName.setText("Supplier Name:");

        lblProductList.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblProductList.setText("Product List");

        tblProductList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Stock Quantity", "Target Price", "Actual Price", "Sales Quantity", "Sales Volume"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductList);

        btnBack.setBackground(new java.awt.Color(255, 255, 204));
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProductList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                        .addComponent(lblSupplierName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSupplierReport)
                    .addComponent(btnBack))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblSupplierReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProductList, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSupplierName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
      userProcessContainer.remove(this);
      CardLayout layout = (CardLayout) userProcessContainer.getLayout();
      layout.previous(userProcessContainer);
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        // TODO add your handling code here:
        populateProductTable();
    }//GEN-LAST:event_cmbSupplierActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox cmbSupplier;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblProductList;
    private javax.swing.JLabel lblSupplierName;
    private javax.swing.JLabel lblSupplierReport;
    private javax.swing.JTable tblProductList;
    // End of variables declaration//GEN-END:variables
}
