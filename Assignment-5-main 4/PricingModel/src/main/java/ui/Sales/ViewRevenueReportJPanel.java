/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Sales;

import java.awt.CardLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Business.Business;
import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.ProductManagement.Product;
import model.Supplier.Supplier;

/**
 *
 * @author martta
 */
public class ViewRevenueReportJPanel extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private Business business;
    private Map<String, Product> productNameToProductMap = new HashMap<>();

    /**
     * Creates new form ViewRevenueReportJPanel
     */
    public ViewRevenueReportJPanel(Business business,JPanel userProcessContainer) {
        initComponents();
        this.business = business;
        this.userProcessContainer = userProcessContainer;
        
        populateContinentCombo();
        populateGenderCombo();
        populateAgeCombo();
        
        populateOrderTable();
        
    }
    
    public void populateContinentCombo(){
        //cmbContinent.removeAllItems();
        
        cmbContinent.addItem("Asia");
        cmbContinent.addItem("Africa");
        cmbContinent.addItem("North America"); 
        cmbContinent.addItem("South America"); 
        cmbContinent.addItem("Europe"); 
        cmbContinent.addItem("Australia"); 
       
    }
    
    public void populateGenderCombo(){
    
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        cmbGender.addItem("Non-Binary");
    }
    
    public void populateAgeCombo(){
        
        cmbAge.addItem("Youth");
        cmbAge.addItem("Middle-Aged");
        cmbAge.addItem("Elderly");
    }
    

    public void populateOrderTable() {

        if (cmbContinent.getSelectedItem() == null || cmbGender.getSelectedItem() == null || cmbAge.getSelectedItem() == null) {
            return;
        }

        String selectedContinent = cmbContinent.getSelectedItem().toString();
        String selectedGender = cmbGender.getSelectedItem().toString();
        String selectedAgeGroup = cmbAge.getSelectedItem().toString();

        DefaultTableModel model = (DefaultTableModel) tblOrderItems.getModel();
        model.setRowCount(0);

        double totalSalesRevenue = 0;
        double totalProfit = 0;

        for (Market market : business.getMarketCatalog().getMarketList()) {
            if (market.getCharacteristics().containsAll(Arrays.asList(selectedContinent, selectedGender, selectedAgeGroup))) {
                for (MarketChannelAssignment mca : market.getChannels()) {
                    double advertisingCost = mca.getChannel().getAdvertisingCost();
                    double conversionRate = mca.getChannel().getConversionRate();

                    for (CustomerProfile cp : market.getCustomerProfileInMarket()) {
                        for (Order order : business.getMasterOrderList().getOrderList()) {
                            if (order.getCustomer().equals(cp)) {
                                for (OrderItem item : order.getOrderItemList()) {
                                    Product p = item.getSelectedProduct();
                                    double itemRevenue = item.getActualPrice() * item.getQuantity();
                                    double costMultiplier = 5 + (Math.random() * 2);
                                    double itemCost = advertisingCost * conversionRate * costMultiplier; 
                                    double itemProfit = itemRevenue - itemCost;
                                    totalSalesRevenue += itemRevenue;
                                    totalProfit += itemProfit;
                                    Object[] row = new Object[]{
                                        p.getName(), 
                                        item.getActualPrice(), 
                                        item.getQuantity(), 
                                        itemRevenue
                                    };
                                    model.addRow(row);
                                    productNameToProductMap.put(p.getName(), p);
                                }
                            }
                        }
                    }
                }
            }
        }

        txtSalesRevenue.setText(String.format("%.2f", totalSalesRevenue));
        txtMarginProfits.setText(String.format("%.2f", totalProfit));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRevenueReport = new javax.swing.JLabel();
        lblOrderItems = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderItems = new javax.swing.JTable();
        lblOrderItems1 = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        lblFreAboveTarget = new javax.swing.JLabel();
        txtFreAboveTarget = new javax.swing.JTextField();
        lblFreBelowTarget = new javax.swing.JLabel();
        txtFreBelowTarget = new javax.swing.JTextField();
        lblMarketSalesRevenue = new javax.swing.JLabel();
        txtSalesRevenue = new javax.swing.JTextField();
        lblMarginProfits = new javax.swing.JLabel();
        txtMarginProfits = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        lblContinent = new javax.swing.JLabel();
        cmbContinent = new javax.swing.JComboBox();
        lblAge = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox();
        cmbAge = new javax.swing.JComboBox();
        btnAnalyze = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 204));

        lblRevenueReport.setBackground(new java.awt.Color(102, 102, 102));
        lblRevenueReport.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lblRevenueReport.setForeground(new java.awt.Color(102, 102, 102));
        lblRevenueReport.setText("Revenue Report");

        lblOrderItems.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblOrderItems.setText("Order items");

        tblOrderItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product Name", "Actual Price", "Sales Quantity", "Total Sales"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblOrderItems);

        lblOrderItems1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblOrderItems1.setText("Business - Wide Product Intelligence");

        lblProductName.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblProductName.setText("Product Name");

        lblFreAboveTarget.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblFreAboveTarget.setText("Frequency Above Target");

        lblFreBelowTarget.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblFreBelowTarget.setText("Frequency Below Target");

        lblMarketSalesRevenue.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblMarketSalesRevenue.setText("Market Sales Revenue");

        lblMarginProfits.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblMarginProfits.setText("Market Margin Profits");

        btnBack.setBackground(new java.awt.Color(255, 255, 204));
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblContinent.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblContinent.setText("Continent:");

        cmbContinent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbContinentActionPerformed(evt);
            }
        });

        lblAge.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblAge.setText("Age Group");

        lblGender.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblGender.setText("Gender:");

        cmbGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGenderActionPerformed(evt);
            }
        });

        cmbAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgeActionPerformed(evt);
            }
        });

        btnAnalyze.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnAnalyze.setText("Select Product and Analyze");
        btnAnalyze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalyzeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(161, 161, 161))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblOrderItems1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblProductName)
                                .addGap(18, 18, 18)
                                .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFreBelowTarget)
                                    .addComponent(lblFreAboveTarget))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFreBelowTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFreAboveTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnAnalyze)
                            .addComponent(lblRevenueReport)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrderItems)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addComponent(cmbAge, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMarketSalesRevenue)
                                    .addComponent(lblMarginProfits))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSalesRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarginProfits, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGender)
                            .addComponent(lblContinent)
                            .addComponent(lblAge))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbContinent, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 57, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMarginProfits, txtSalesRevenue});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbAge, cmbContinent, cmbGender});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(btnBack)
                .addGap(18, 18, 18)
                .addComponent(lblRevenueReport)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContinent, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbContinent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarginProfits, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarginProfits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarketSalesRevenue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalesRevenue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(lblOrderItems)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAnalyze)
                .addGap(17, 17, 17)
                .addComponent(lblOrderItems1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFreAboveTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFreAboveTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFreBelowTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFreBelowTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
      userProcessContainer.remove(this);
      CardLayout layout = (CardLayout) userProcessContainer.getLayout();
      layout.previous(userProcessContainer);
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void cmbContinentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbContinentActionPerformed
        // TODO add your handling code here:
        populateOrderTable();
    }//GEN-LAST:event_cmbContinentActionPerformed

    private void cmbGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGenderActionPerformed
        // TODO add your handling code here:
         populateOrderTable();
    }//GEN-LAST:event_cmbGenderActionPerformed

    private void cmbAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgeActionPerformed
        // TODO add your handling code here:
         populateOrderTable();
    }//GEN-LAST:event_cmbAgeActionPerformed

    private void btnAnalyzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalyzeActionPerformed
        // TODO add your handling code here:
        int selectedRowIndex = tblOrderItems.getSelectedRow();
        if(selectedRowIndex<0){
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) tblOrderItems.getModel();
       // Product selectedProduct = (Product) model.getValueAt(selectedRowIndex, 0);
        
        String productName = model.getValueAt(selectedRowIndex, 0).toString();
        Product selectedProduct = productNameToProductMap.get(productName);
        
        List<OrderItem> orderItems = business.getMasterOrderList().getOrderItemsForProduct(selectedProduct);
        
        double frequencyAboveTarget = 0.0;
        double frequencyBelowTarget = 0.0;
    
        for(OrderItem item : orderItems) {

            if(item.getActualPrice() > selectedProduct.getTargetPrice()) {
                frequencyAboveTarget++;
            } else if(item.getActualPrice() < selectedProduct.getTargetPrice()) {
                frequencyBelowTarget++;
            }
        }
        txtProductName.setText(selectedProduct.getName());
        txtFreAboveTarget.setText(String.valueOf(frequencyAboveTarget/ 100.0));
        txtFreBelowTarget.setText(String.valueOf(frequencyBelowTarget/ 100.0));
         
    }//GEN-LAST:event_btnAnalyzeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalyze;
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox cmbAge;
    private javax.swing.JComboBox cmbContinent;
    private javax.swing.JComboBox cmbGender;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblContinent;
    private javax.swing.JLabel lblFreAboveTarget;
    private javax.swing.JLabel lblFreBelowTarget;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblMarginProfits;
    private javax.swing.JLabel lblMarketSalesRevenue;
    private javax.swing.JLabel lblOrderItems;
    private javax.swing.JLabel lblOrderItems1;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblRevenueReport;
    private javax.swing.JTable tblOrderItems;
    private javax.swing.JTextField txtFreAboveTarget;
    private javax.swing.JTextField txtFreBelowTarget;
    private javax.swing.JTextField txtMarginProfits;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtSalesRevenue;
    // End of variables declaration//GEN-END:variables
}
