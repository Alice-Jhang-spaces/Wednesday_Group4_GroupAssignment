/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.Sales;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Business.Business;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Market;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.ProductManagement.Product;

/**
 *
 * @author martta
 */
public class ManagePriceJPanel extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private Business business;

    /**
     * Creates new form ManagePriceJPanel
     */
    public ManagePriceJPanel(Business business,JPanel userProcessContainer) {
        initComponents();
        this.business = business;
        this.userProcessContainer = userProcessContainer;
        
        populateContinentCombo();
        populateGenderCombo();
        populateAgeCombo();
//        populatePriceCombo();
        populatePriceCombo1();
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
        //cmbGender.removeAllItems(); 
    
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        cmbGender.addItem("Non-Binary");
    }
    
    public void populateAgeCombo(){
        //cmbGender.removeAllItems(); 
        
        cmbAge.addItem("Youth");
        cmbAge.addItem("Middle-Aged");
        cmbAge.addItem("Elderly");
    }
    
//    public void populatePriceCombo(){
//        //cmbGender.removeAllItems(); 
//        
//        cmbRaise.addItem("10");
//        cmbRaise.addItem("30");
//        cmbRaise.addItem("50");
//    }
//    
    public void populatePriceCombo1(){
        //cmbGender.removeAllItems(); 
        
        cmbRaise1.addItem("1");
        cmbRaise1.addItem("2");
        cmbRaise1.addItem("3");
    }

//    private void adjustPricesAndVolumes() {
//        String selectedContinent = cmbContinent.getSelectedItem().toString();
//        String selectedGender = cmbGender.getSelectedItem().toString();
//        String selectedAgeGroup = cmbAge.getSelectedItem().toString();
//        int priceAdjustmentPercentage = Integer.parseInt(cmbRaise.getSelectedItem().toString());
//
//        for (Market market : business.getMarketCatalog().getMarketList()) {
//            if (market.getCharacteristics().contains(selectedContinent) && market.getCharacteristics().contains(selectedGender)) {
//                for (CustomerProfile customer : market.getCustomerProfileInMarket()) {
//                    if (customer.getCustomerAge().equals(selectedAgeGroup)) {
//                        for (Order order : customer.getOrders()) {
//                            for (OrderItem item : order.getOrderItemList()) {
//                                adjustOrderItem(item, priceAdjustmentPercentage, selectedAgeGroup);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//    
//    private void adjustOrderItem(OrderItem item, int priceAdjustmentPercentage, String ageGroup) {
//
//        double priceAdjustmentFactor = 1 + (priceAdjustmentPercentage / 100.0);
//        double volumeAdjustmentFactor = 1 - (priceAdjustmentPercentage / 100.0);
//        
//        if ("Middle-Aged".equalsIgnoreCase(ageGroup.trim())) {
//            volumeAdjustmentFactor = 1 + (priceAdjustmentPercentage / 100.0);
//        }
//
//        double newPrice = item.getActualPrice() * priceAdjustmentFactor;
//        item.setActualPrice((int) newPrice);
//
//        int newQuantity = (int) (item.getQuantity() * volumeAdjustmentFactor);
//        item.setQuantity(newQuantity);
//    }
//    
private void adjustPricesAndVolumes() {
    String selectedContinent = cmbContinent.getSelectedItem().toString();
    String selectedGender = cmbGender.getSelectedItem().toString();
    String selectedAgeGroup = cmbAge.getSelectedItem().toString();
    int adjustmentLevel = Integer.parseInt(cmbRaise1.getSelectedItem().toString());

    // Adjustment levels based on cmbRaise selection
    int priceDecreasePercentage;
    int quantityIncreasePercentage;
    int priceIncreasePercentage;

    // Set adjustment percentages based on selection
    switch (adjustmentLevel) {
        case 1: // Low adjustments
            priceDecreasePercentage = 5;    // Decrease price by 5%
            quantityIncreasePercentage = 10; // Increase quantity by 10%
            priceIncreasePercentage = 5;    // Increase price by 5%
            break;
        case 2: // Moderate adjustments
            priceDecreasePercentage = 10;   // Decrease price by 10%
            quantityIncreasePercentage = 15; // Increase quantity by 15%
            priceIncreasePercentage = 10;   // Increase price by 10%
            break;
        case 3: // High adjustments
            priceDecreasePercentage = 15;   // Decrease price by 15%
            quantityIncreasePercentage = 20; // Increase quantity by 20%
            priceIncreasePercentage = 15;   // Increase price by 15%
            break;
        default:
            throw new IllegalArgumentException("Invalid adjustment level");
    }

    for (Market market : business.getMarketCatalog().getMarketList()) {
        if (market.getCharacteristics().contains(selectedContinent) && market.getCharacteristics().contains(selectedGender)) {
            for (CustomerProfile customer : market.getCustomerProfileInMarket()) {
                if (customer.getCustomerAge().equals(selectedAgeGroup)) {
                    for (Order order : customer.getOrders()) {
                        for (OrderItem item : order.getOrderItemList()) {
                            adjustOrderItem(item, priceDecreasePercentage, quantityIncreasePercentage, priceIncreasePercentage);
                        }
                    }
                }
            }
        }
    }
}

private void adjustOrderItem(OrderItem item, int priceDecreasePercentage, int quantityIncreasePercentage, int priceIncreasePercentage) {
    // Decrease price (below target)
    double newPrice = item.getActualPrice() * (1 - priceDecreasePercentage / 100.0);
    item.setActualPrice((int) newPrice);

    // Increase quantity (boost sales)
    int newQuantity = (int) (item.getQuantity() * (1 + quantityIncreasePercentage / 100.0));
    item.setQuantity(newQuantity);

    // Increase price (above target)
    newPrice = item.getActualPrice() * (1 + priceIncreasePercentage / 100.0);
    item.setActualPrice((int) newPrice);
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblManagePrice = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnViewAdjustedReport = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        lblContinent = new javax.swing.JLabel();
        cmbContinent = new javax.swing.JComboBox();
        lblGender = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox();
        lblAge = new javax.swing.JLabel();
        cmbAge = new javax.swing.JComboBox();
        lblRaise1 = new javax.swing.JLabel();
        cmbRaise1 = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(204, 204, 204));

        lblManagePrice.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lblManagePrice.setForeground(new java.awt.Color(255, 255, 255));
        lblManagePrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManagePrice.setText("Price Management System");

        btnSubmit.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnViewAdjustedReport.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnViewAdjustedReport.setText("View Adjusted Report");
        btnViewAdjustedReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAdjustedReportActionPerformed(evt);
            }
        });

        btnback.setText("<< Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        lblContinent.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblContinent.setText("Continent:");

        cmbContinent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbContinentActionPerformed(evt);
            }
        });

        lblGender.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblGender.setText("Gender:");

        cmbGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGenderActionPerformed(evt);
            }
        });

        lblAge.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblAge.setText("Age Group");

        cmbAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgeActionPerformed(evt);
            }
        });

        lblRaise1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblRaise1.setText("Rate mode:");

        cmbRaise1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRaise1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(btnback)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblContinent)
                        .addGap(84, 84, 84)
                        .addComponent(cmbContinent, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(491, 491, 491))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAge)
                                .addGap(80, 80, 80)
                                .addComponent(cmbAge, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRaise1)
                                .addGap(80, 80, 80)
                                .addComponent(cmbRaise1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblGender)
                                .addGap(100, 100, 100)
                                .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(273, 273, 273))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(btnViewAdjustedReport, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblManagePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btnback)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblManagePrice)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContinent, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbContinent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAge, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRaise1)
                    .addComponent(cmbRaise1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewAdjustedReport)
                    .addComponent(btnSubmit))
                .addContainerGap(303, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
      userProcessContainer.remove(this);
      CardLayout layout = (CardLayout) userProcessContainer.getLayout();
      layout.previous(userProcessContainer);
        
    }//GEN-LAST:event_btnbackActionPerformed

    private void cmbContinentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbContinentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbContinentActionPerformed

    private void cmbGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGenderActionPerformed

    private void cmbAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAgeActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        adjustPricesAndVolumes();
        
        JOptionPane.showMessageDialog(this, "Prices and volumes adjusted successfully.");

    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnViewAdjustedReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAdjustedReportActionPerformed
        // TODO add your handling code here:
        ViewRevenueReportJPanel viewRevenueReportJPanel = new ViewRevenueReportJPanel(business, userProcessContainer);
        userProcessContainer.add("ViewRevenueReportJPanel", viewRevenueReportJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnViewAdjustedReportActionPerformed

    private void cmbRaise1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRaise1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRaise1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnViewAdjustedReport;
    private javax.swing.JButton btnback;
    private javax.swing.JComboBox cmbAge;
    private javax.swing.JComboBox cmbContinent;
    private javax.swing.JComboBox cmbGender;
    private javax.swing.JComboBox cmbRaise1;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblContinent;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblManagePrice;
    private javax.swing.JLabel lblRaise1;
    // End of variables declaration//GEN-END:variables
}
