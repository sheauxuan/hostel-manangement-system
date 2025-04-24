/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_grp_assignment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
/**
/**
 *
 * @author HP
 */
public class manage_residents extends javax.swing.JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private String managerName;
    private String tpNum;
    private String Password;
    private String email;
    private String userPassword;
    /**
     * Creates new form manage_residents
     */
    public manage_residents(){
        initComponents();
    }
    public manage_residents(String managerName, String tpNum, String Password, String email) {
        this.managerName = managerName;
        this.tpNum = tpNum;
        this.Password = Password;
        this.email = email;

        initComponents();
        setupTableAndSearch();
        loadDataFromFile();
        setupDoubleClickListener();
        setVisible(true); 
    }
    
    private void setupTableAndSearch() {
        tableModel = (DefaultTableModel) residentinfotable.getModel();
    
        rowSorter = new TableRowSorter<>(tableModel);
        residentinfotable.setRowSorter(rowSorter);
        searchfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchfield.getText().trim();
                if (searchText.isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("^" + searchText, 1)); 
                }
            }
        });
    }

    private void loadDataFromFile() {
        File file = new File("residentInfo.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "File not found. Please ensure 'txtUserInfo.txt' is in the correct location.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Vector<String> row = new Vector<>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("residentName:")) {
                    row = new Vector<>();
                    row.add(line.split(":", 2)[1]); 
                } else if (line.startsWith("tpNum:")) {
                    row.add(line.split(":", 2)[1]); 
                } else if (line.startsWith("password:")){
                    userPassword = line.split(":", 2)[1];
                } else if (line.startsWith("contact:")) {
                    row.add(line.split(":", 2)[1]); 
                } else if (line.startsWith("eContact:")) {
                    row.add(line.split(":", 2)[1]); 
                } else if (line.startsWith("email:")) {
                    row.add(line.split(":", 2)[1]); 
                } else if (line.startsWith("birth:")) {
                    row.add(line.split(":", 2)[1]); 
                } else if (line.startsWith("address:")) {
                    row.add(line.split(":", 2)[1]); 
                } else if (line.startsWith("country:")) {
                    row.add(line.split(":", 2)[1]);
                } else if (line.startsWith("roomType:")) {
                    row.add(line.split(":", 2)[1]); 
                } else if (line.startsWith("roomAssigned")) {
                    row.add(line.split(":", 2)[1]); 
                    tableModel.addRow(row); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setupDoubleClickListener() {
        residentinfotable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && residentinfotable.getSelectedRow() != -1) {
                    int selectedRow = residentinfotable.getSelectedRow();
                    int modelRow = residentinfotable.convertRowIndexToModel(selectedRow);

                    String residentName = tableModel.getValueAt(modelRow, 0).toString();
                    String tpNumber = tableModel.getValueAt(modelRow, 1).toString();
                    String contact = tableModel.getValueAt(modelRow, 2).toString();
                    String emergencyContact = tableModel.getValueAt(modelRow, 3).toString();
                    String residentemail = tableModel.getValueAt(modelRow, 4).toString();
                    String birth = tableModel.getValueAt(modelRow, 5).toString();
                    String address = tableModel.getValueAt(modelRow, 6).toString();
                    String country = tableModel.getValueAt(modelRow, 7).toString();
                    String roomType = tableModel.getValueAt(modelRow, 8).toString();
                    String roomAssigned = tableModel.getValueAt(modelRow, 9).toString();

                    managerEditResident mer = new managerEditResident(
                            residentName, tpNumber, userPassword,contact, emergencyContact, residentemail,
                            birth, address, country, roomType, roomAssigned, managerName, tpNum, Password, email
                    );
                    mer.setVisible(true);

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
        jScrollPane1 = new javax.swing.JScrollPane();
        residentinfotable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        searchfield = new javax.swing.JTextField();
        deleteuserbutton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(80, 118, 135));

        residentinfotable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "TPNum", "Contact", "Emergency Contact", "Email", "Birth", "Address", "Country", "RoomType", "RoomNo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(residentinfotable);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("Search :");

        deleteuserbutton.setText("DELETE USER");
        deleteuserbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteuserbuttonActionPerformed(evt);
            }
        });

        jButton1.setText("ADD USER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(56, 75, 112));

        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(deleteuserbutton)
                        .addGap(102, 102, 102)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteuserbutton)
                    .addComponent(jButton1))
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteuserbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteuserbuttonActionPerformed
        int selectedRow = residentinfotable.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a resident to delete.");
        return;
    }

    String tpNumberToDelete = tableModel.getValueAt(selectedRow, 1).toString();

    int confirmation = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete the RESIDENT with TP Number " + tpNumberToDelete + "?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmation == JOptionPane.NO_OPTION) {
        return;
    }

    File file = new File("residentInfo.txt");
    List<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        String previousLine = null; 
        boolean isDeleting = false;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("tpNum:") && line.contains(tpNumberToDelete)) {
                isDeleting = true; 

                if (previousLine != null && previousLine.startsWith("residentName:")) {
                    previousLine = null; 
                }
            }

            if (isDeleting) {
                if (line.startsWith("---------------------------------------------------------------")) {
                    isDeleting = false; 
                    continue; 
                }
                continue; 
            }

            if (previousLine != null) {
                lines.add(previousLine); 
            }
            previousLine = line; 
        }

       
        if (previousLine != null && !isDeleting) {
            lines.add(previousLine); 
        }

      
        try (FileWriter writer = new FileWriter(file)) {
            for (String l : lines) {
                writer.write(l + System.lineSeparator());
            }
        }

     
        JOptionPane.showMessageDialog(null, "Resident data deleted successfully.");

        tableModel.removeRow(selectedRow);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error occurred while deleting the resident data.");
        e.printStackTrace();
    }
    

    

    }//GEN-LAST:event_deleteuserbuttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        registration_main_manager rm = new registration_main_manager(managerName, tpNum, Password, email);
        rm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Main_pageManager mm = new Main_pageManager(managerName, tpNum, Password, email);
        mm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    // Set the Nimbus look and feel
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(managerfixrate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    String managerName = "LimJay";
    String tpNum = "MNG999";
    String Password = "manager123456";
    String email = "aaa@GMAIL.COM";

  
    java.awt.EventQueue.invokeLater(() -> {
        new manage_residents(managerName, tpNum, Password, email).setVisible(true);
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteuserbutton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable residentinfotable;
    private javax.swing.JTextField searchfield;
    // End of variables declaration//GEN-END:variables
}
