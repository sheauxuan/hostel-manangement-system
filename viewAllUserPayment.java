/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_grp_assignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class viewAllUserPayment extends javax.swing.JFrame {

    private String staffname;
    private String tpNum;
    private String Password;
    private String email;
    private String contact;
    
    private String name;
    private String tpnum;
    private String month;
    private String paymentStatus;
    private String paymentdate;
    private String paymentmethod;
    private String amount;
    private String receiptnum;
    private List<PaymentInfo> paymentInfoList = new ArrayList<>();
    private TableRowSorter<DefaultTableModel> rowSorter;
    
    
    public class PaymentInfo {
        String tpnum;
        String name;
        String month;
        String paymentStatus;
        String amount;
        String receiptnum;
        String paymentmethod;
        String paymentdate;

        // Constructor to set values
        public PaymentInfo(String tpnum, String name, String month, String paymentStatus, String amount, String receiptnum, String paymentmethod, String paymentdate) {
            this.tpnum = tpnum;
            this.name = name;
            this.month = month;
            this.paymentStatus = paymentStatus;
            this.amount = amount;
            this.receiptnum = receiptnum;
            this.paymentmethod = paymentmethod;
            this.paymentdate = paymentdate;
        }
    }
    
    public viewAllUserPayment(){
        initComponents();
        populateTable();
    }
    
    public viewAllUserPayment(String staffname, String staffTPNum, String Password, String email, String contact) {
        initComponents();
        
        this.staffname = staffname;
        this.tpNum = staffTPNum;
        this.Password = Password;
        this.email = email;
        this.contact = contact;
        populateTable();
        setupTableAndSearch();
    }
    
    private void populateTable(){
        try {
            FileReader fileReader = new FileReader("userPayment.txt");
            Scanner reader = new Scanner(fileReader);
            
            DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
            table.setRowCount(0);
            
            while(reader.hasNextLine()){
                String lines = reader.nextLine();
                
                if(lines.startsWith("residentName:")){
                    String[] parts = lines.split(":");
                    name = parts[1].trim();   
                }

                if(lines.startsWith("TP Num:")){
                    String[] parts2 = lines.split(":");
                    tpnum = parts2[1].trim();
                }
                
                if(lines.contains("Payments:")){
                    while(reader.hasNextLine()){
                        String paymentInfo = reader.nextLine();
                        String[] paymentParts = paymentInfo.split(":");
                        
                        if (paymentInfo.startsWith("-----------------------------------")) {
                            break;
                        }
                        
                        if (paymentInfo.contains("[NOT_APPLICABLE]")) {
                            continue;
                        }

                        if(paymentParts.length > 1){
                            String[] paymentReceiptPart = paymentParts[1].trim().split(" ");
                            String[] paymentMonthPart = paymentParts[0].split(" ");
                            month = paymentMonthPart[0].trim();

                            if(paymentReceiptPart.length > 1){
                                if (paymentReceiptPart[1].equals("[RECEIPT-]")){
                                    paymentStatus = "Unpaid";
                                } else if (paymentReceiptPart[1].startsWith("[RECEIPT-")) {
                                    paymentStatus = "Paid";
                                    String receiptNumPart = paymentReceiptPart[1].replace("[", "").replace("]", "").trim();
                                    if(receiptNumPart.contains("-")){
                                        String[] receiptnumparts = receiptNumPart.split("-");
                                        if(receiptnumparts.length > 1){
                                            receiptnum = receiptnumparts[1].trim();
                                        }
                                    }
                                }
                                
                                amount = paymentReceiptPart[0].trim();
                                
                                if (paymentReceiptPart.length > 2) {
                                    paymentmethod = paymentReceiptPart[2].trim();
                                    if (paymentReceiptPart.length > 3) {
                                        paymentdate = paymentReceiptPart[3].trim();  
                                    }
                                }

                                table.addRow(new Object[]{tpnum, name, month, paymentStatus});
                                paymentInfoList.add(new PaymentInfo(tpnum, name, month, paymentStatus, amount, receiptnum, paymentmethod, paymentdate));
                            }
                        }
                    }
                }    
                
            }
            reader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(viewAllUserPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to set up the TableRowSorter and the search functionality
    private void setupTableAndSearch() {
    DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
    
    // Create TableRowSorter for sorting and filtering
    rowSorter = new TableRowSorter<>(tableModel);
    jTable1.setRowSorter(rowSorter);
    
    // Add KeyListener for filtering the table based on search input (TP Number)
    searchfield.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
            String searchText = searchfield.getText().trim();
            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null); // If search field is empty, show all rows
            } else {
                // Filter the table by the TP Number (column 0 in the table)
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0)); // Case-insensitive filter
            }
        }
    });
}

    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        backbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        viewReceiptbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchfield = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(80, 118, 135));

        jPanel2.setBackground(new java.awt.Color(56, 75, 112));
        jPanel2.setPreferredSize(new java.awt.Dimension(92, 52));

        backbtn.setText("Back");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(backbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(backbtn)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TP Number", "Name", "Month", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        viewReceiptbtn.setText("View Receipt");
        viewReceiptbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReceiptbtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setText("Search :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewReceiptbtn)
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewReceiptbtn)
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void viewReceiptbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReceiptbtnActionPerformed
        int selectedUserRow = jTable1.getSelectedRow();
        
        if(selectedUserRow >= 0){
            
            PaymentInfo selectedPayment = paymentInfoList.get(selectedUserRow);

            if (selectedPayment.paymentStatus.equalsIgnoreCase("Not Done")) {
                JOptionPane.showMessageDialog(this, "No Receipt Available due to Not Done Payment.");
                return; 
            }
            
            System.out.println(receiptnum);
            
            payment_receipt2 pr2 = new payment_receipt2(staffname,tpNum,Password,email,contact,selectedPayment.name, selectedPayment.tpnum, selectedPayment.receiptnum, selectedPayment.month, selectedPayment.amount, selectedPayment.paymentmethod, selectedPayment.paymentdate);
            pr2.setVisible(true);
            this.dispose();
            
        }
        
    }//GEN-LAST:event_viewReceiptbtnActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        Main_pageStaff mp = new Main_pageStaff(staffname, tpNum, Password, email, contact);
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewAllUserPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewAllUserPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewAllUserPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewAllUserPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewAllUserPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField searchfield;
    private javax.swing.JButton viewReceiptbtn;
    // End of variables declaration//GEN-END:variables
}
