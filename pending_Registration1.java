/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_grp_assignment;


import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class pending_Registration1 extends javax.swing.JFrame {

    private String managerName;
    private String tpNum;
    private String Password;
    private String email;
    
    private LinkedHashMap<String, String> availableRooms = new LinkedHashMap<>();
    
    
    
    public pending_Registration1(String managerName, String tpNum, String Password, String email) {
        initComponents();
        populateTable();
        this.managerName = managerName;
        this.tpNum = tpNum;
        this.Password = Password;
        this.email = email;
    }
    
    private void populateTable(){
        
        try {
        // Read availableRooms.txt to get room availability
        FileReader readRooms = new FileReader("availableRooms.txt");
        Scanner roomReader = new Scanner(readRooms);



        while (roomReader.hasNextLine()) {
            String line = roomReader.nextLine().trim();
            String[] roomInfo = line.split(":", 2);
            if (roomInfo.length == 2) {
                availableRooms.put(roomInfo[0].trim().toLowerCase(), roomInfo[1].trim()); // Store room type and room numbers
            }
        }

            
        readRooms.close();
        // Read user information
        BufferedReader reader = new BufferedReader(new FileReader("txtUserInfo.txt"));
        String line;
        String residentName = "";
        String tpNum = "";
        String roomType = "";
        String roomAssigned = "";
        String status = "";


        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing table rows
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        model2.setRowCount(0);

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.isEmpty()||line.equals("---------------------------------------------------------------")) {
                if(!residentName.isEmpty()&& !tpNum.isEmpty()&& !roomType.isEmpty()){
                    status = roomAssigned.isEmpty() ? "Not assigned" : "Assigned";
                    model.addRow(new Object[]{
                        residentName, tpNum, roomType.toLowerCase(),
                        roomAssigned.isEmpty()? "Not assigned" : roomAssigned,
                        status
                    });
                }
                
                residentName="";
                tpNum="";
                roomType="";
                roomAssigned="";
                continue;
                
            }   
            
            if(line.contains(":")){
                String[] partsOfLine = line.split(":", 2);
                if(partsOfLine.length == 2){
                    String key = partsOfLine[0].trim();
                    String value = partsOfLine[1].trim();
                    switch(key){
                        case "residentName":
                            residentName = value;
                            break;
                            
                        case "tpNum":
                            tpNum = value;
                            break;
                            
                        case "roomType":
                            roomType = value;
                            break;
                            
                        case "roomAssigned":
                            roomAssigned = value;
                            break;
                    }
                }
            }
           
            
        }

        if(!residentName.isEmpty() && !tpNum.isEmpty() && !roomType.isEmpty()){
            status = roomAssigned.isEmpty() ? "Not assigned" : "Assigned";
            model.addRow(new Object[]{
                residentName, 
                tpNum, 
                roomType.toLowerCase(), 
                roomAssigned.isEmpty() ? "Not assigned" : roomAssigned, 
                status
            });
        }
        
        for (Map.Entry<String, String> entry : availableRooms.entrySet()) {
            model2.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while reading available room or student information.");
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        pr_back = new javax.swing.JButton();
        pr_confirm = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pr_date = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pr_month = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pr_year = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(80, 118, 135));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "TP Number", "Room Type", "Room Assigned", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Room Types", "Available Rooms"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel3.setBackground(new java.awt.Color(56, 75, 112));
        jPanel3.setPreferredSize(new java.awt.Dimension(92, 52));

        pr_back.setText("Back");
        pr_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pr_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pr_back)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pr_back)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pr_confirm.setText("Confirm");
        pr_confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pr_confirmActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registration Date:");

        pr_date.setText("DD");
        pr_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pr_dateActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("/");

        pr_month.setText("MM");
        pr_month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pr_monthActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("/");

        pr_year.setText("YYYY");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pr_date, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pr_month, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pr_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pr_confirm))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(pr_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(pr_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(pr_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pr_confirm)))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void pr_confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pr_confirmActionPerformed
        confirmRoomAssignment();
    
    }//GEN-LAST:event_pr_confirmActionPerformed
    
    private void confirmRoomAssignment() {
        try {

            BufferedReader userReader = new BufferedReader(new FileReader("txtUserInfo.txt"));
            List<Map<String, String>> userData = new ArrayList<>();
            String line;
            Map<String, String> currentUser = null;

            // Reading user data
            while ((line = userReader.readLine()) != null) {
                if (line.isEmpty() || line.startsWith("---------------------------------------------------------------")) {
                    if (currentUser != null && !currentUser.isEmpty()) { 
                        userData.add(currentUser);
                    }
                    currentUser = new HashMap<>(); 
                    continue; 
                }

                // Parse key-value pairs
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    if (currentUser == null) {
                        currentUser = new HashMap<>(); 
                    }
                    currentUser.put(parts[0].trim(), parts[1].trim());
                }
            }
            if (currentUser != null && !currentUser.isEmpty()) {
                userData.add(currentUser);
            }

            DefaultTableModel userTable = (DefaultTableModel) jTable1.getModel();
            DefaultTableModel roomTable = (DefaultTableModel) jTable2.getModel();

            Set<String> assignedRooms = new HashSet<>(); 
            for (int i = 0; i < userTable.getRowCount(); i++) {
                String residentNameToModify = userTable.getValueAt(i, 0).toString().trim();
                String roomTypeToUpdate = userTable.getValueAt(i, 2).toString().toLowerCase().trim();
                String assignedRoomToUpdate = userTable.getValueAt(i, 3).toString().trim();
                String roomStatus = userTable.getValueAt(i, 4).toString().trim();
                
                if (roomStatus.equalsIgnoreCase("Assigned") && !assignedRoomToUpdate.equalsIgnoreCase("Not assigned")) {
                    continue;
                }
                
                if (assignedRoomToUpdate != null && !assignedRoomToUpdate.trim().isEmpty() && !assignedRoomToUpdate.equalsIgnoreCase("Not assigned")) {
                    // Check if the room is already assigned
                    if (assignedRooms.contains(assignedRoomToUpdate)) {
                        JOptionPane.showMessageDialog(null, "Room " + assignedRoomToUpdate + " is already assigned!");
                        continue;
                    }

                    
                    if (availableRooms.containsKey(roomTypeToUpdate)) {
                        List<String> roomList = new ArrayList<>(Arrays.asList(availableRooms.get(roomTypeToUpdate).split(",")));
                        if (!roomList.contains(assignedRoomToUpdate)) {
                            JOptionPane.showMessageDialog(null, "Room " + assignedRoomToUpdate + " is not available!");
                            continue;
                        }

                        assignedRooms.add(assignedRoomToUpdate);
                        userTable.setValueAt("Assigned", i, 4);
                        roomList.remove(assignedRoomToUpdate); 
                        availableRooms.put(roomTypeToUpdate, String.join(",", roomList)); 

                        for (Map<String, String> user : userData) {
                            if (residentNameToModify.equals(user.get("residentName"))) {
                                user.put("roomAssigned", assignedRoomToUpdate);
                                user.put("status", "Assigned");
                                break;
                            }
                        }
                        System.out.println("Updated availableRooms: " + availableRooms);
                        saveInfoToPaymentFile(residentNameToModify, userData);
                    } else {
                        JOptionPane.showMessageDialog(null, "Room type " + roomTypeToUpdate + " not found!");
                        continue;
                    }
                }
            }

          
            BufferedWriter userWriter = new BufferedWriter(new FileWriter("txtUserInfo.txt"));
            for (Map<String, String> user : userData) {
                userWriter.write("residentName:" + user.get("residentName"));
                userWriter.newLine();
                userWriter.write("tpNum:" + user.get("tpNum"));
                userWriter.newLine();
                userWriter.write("password:" + user.get("password"));
                userWriter.newLine();
                userWriter.write("rePassword:" + user.get("rePassword"));
                userWriter.newLine();
                userWriter.write("contact:" + user.get("contact"));
                userWriter.newLine();
                userWriter.write("eContact:" + user.get("eContact"));
                userWriter.newLine();
                userWriter.write("email:" + user.get("email"));
                userWriter.newLine();
                userWriter.write("birth:" + user.get("birth"));
                userWriter.newLine();
                userWriter.write("address:" + user.get("address"));
                userWriter.newLine();
                userWriter.write("country:" + user.get("country"));
                userWriter.newLine();
                userWriter.write("roomType:" + user.get("roomType"));
                userWriter.newLine();
                userWriter.write("roomAssigned:" + user.get("roomAssigned"));
                userWriter.newLine();
                userWriter.write("referenceNum:" + user.get("referenceNum"));
                userWriter.newLine();
                userWriter.write("---------------------------------------------------------------");
                userWriter.newLine();
            }
            userWriter.close();
            
            LinkedHashMap<String, List<String>> convertedAvailableRooms = new LinkedHashMap<>();

            for (Map.Entry<String, String> entry : availableRooms.entrySet()) {
                convertedAvailableRooms.put(entry.getKey(), Arrays.asList(entry.getValue()));
            }

            updateRoomTable(convertedAvailableRooms);
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("availableRooms.txt"));
            for (Map.Entry<String, String> entry : availableRooms.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine(); 
            }
            bw.close();
            
            updateUserTableStatus(userTable);
            JOptionPane.showMessageDialog(null, "Room assignment updated successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while updating room assignment.");
        }
    }
    
    private void updateUserTableStatus(DefaultTableModel userTable) {
        for (int i = 0; i < userTable.getRowCount(); i++) {
            String assignedRoom = userTable.getValueAt(i, 3).toString().trim();

            if (assignedRoom != null && !assignedRoom.isEmpty() && !assignedRoom.equalsIgnoreCase("Not assigned")) {
                userTable.setValueAt("Assigned", i, 4);
            }
        }
    }
    
    private void updateRoomTable(LinkedHashMap<String, List<String>> availableRooms) {
        DefaultTableModel roomTable = (DefaultTableModel) jTable2.getModel();
        roomTable.setRowCount(0);

        for (String roomType : availableRooms.keySet()) {
            roomTable.addRow(new Object[]{roomType, String.join(",", availableRooms.get(roomType))});
        }
    }
    
    public void saveInfoToPaymentFile(String residentNameToModify, List<Map<String, String>> userData) throws IOException{
        HashSet<String> existingTPNumbers = new HashSet<>();
        BufferedReader paymentReader = new BufferedReader(new FileReader("userPayment.txt"));
        String paymentLine;
        BufferedWriter paymentWriter = new BufferedWriter(new FileWriter("userPayment.txt", true));

        while ((paymentLine = paymentReader.readLine()) != null) {
            if (paymentLine.startsWith("TP Num:")) {
                String tpNum = paymentLine.substring("TP Num:".length()).trim();
                existingTPNumbers.add(tpNum);
            }
        }
        paymentReader.close();
        
        Map<String, Integer> roomRates = new HashMap<>();
        try (BufferedReader rateReader = new BufferedReader(new FileReader("roomrateInfo.txt"))) {
            String rateLine;
            String currentRoomType = null;
            while ((rateLine = rateReader.readLine()) != null) {
                if (rateLine.startsWith("RoomType:")) {
                    currentRoomType = rateLine.substring("RoomType:".length()).trim().toLowerCase();
                } else if (rateLine.startsWith("Rate:") && currentRoomType != null) {
                    int rate = Integer.parseInt(rateLine.substring("Rate:".length()).trim());
                    roomRates.put(currentRoomType, rate);
                    currentRoomType = null;
                }
            }
        }
        
        for (Map<String, String> userBlockInfo : userData) {
            if (userBlockInfo.containsKey("residentName") &&
                userBlockInfo.get("residentName").equalsIgnoreCase(residentNameToModify)) {

                String residentName = userBlockInfo.getOrDefault("residentName", "");
                String tpNum = userBlockInfo.getOrDefault("tpNum", "");
                String roomType = userBlockInfo.getOrDefault("roomType", "").toLowerCase();
                String roomAssigned = userBlockInfo.getOrDefault("roomAssigned", "");

                if (!roomAssigned.isEmpty() && !existingTPNumbers.contains(tpNum)) {
                    int registrationMonth = Integer.parseInt(pr_month.getText());
                    int currentYear = Integer.parseInt(pr_year.getText());
                    String registrationDate = pr_date.getText() + "-" + pr_month.getText() + "-" + pr_year.getText();
                    String amount = roomRates.containsKey(roomType) ? String.valueOf(roomRates.get(roomType)) : "0";

                    StringBuilder paymentDataFormat = new StringBuilder();
                    paymentDataFormat.append("residentName: ").append(residentName).append("\n")
                            .append("TP Num: ").append(tpNum).append("\n")
                            .append("Room Type: ").append(roomType).append("\n")
                            .append("Room No: ").append(roomAssigned).append("\n")
                            .append("Registration Date: ").append(registrationDate).append("\n")
                            .append("Payments:\n");
                    
                    for (int month = 12; month <= 15; month++) {
                        int actualMonth = (month > 12) ? month - 12 : month;
                        String monthName = LocalDate.of(currentYear, actualMonth, 1).getMonth().toString().toLowerCase();
                        String formattedMonth = monthName.substring(0, 1).toUpperCase() + monthName.substring(1).toLowerCase();

                        int adjustYear = currentYear;
                        if (month == 12) {
                            // If the registration month is Jan, Feb, or March, set December's year to the previous year
                            adjustYear = (registrationMonth <= 3) ? currentYear - 1 : currentYear;
                        } else if (month > 12) {
                            // For months after December, if registration month is later than this month, use next year
                            adjustYear = (registrationMonth > 12) ? currentYear + 1 : currentYear;
                        }

                        boolean isNotApplicable = (month == 12 && registrationMonth <= 3) || (month < registrationMonth);
                        
                        if (isNotApplicable) {
                            paymentDataFormat.append(formattedMonth).append(" ").append(adjustYear)
                                    .append(": ").append(amount).append(" [NOT_APPLICABLE]\n");
                        } else if (month == registrationMonth) {
                            paymentDataFormat.append(formattedMonth).append(" ").append(adjustYear)
                                    .append(": ").append(amount).append(" [RECEIPT-] [None] [Date]\n");
                        } else {
                            paymentDataFormat.append(formattedMonth).append(" ").append(adjustYear)
                                    .append(": ").append(amount).append(" [RECEIPT-] [None] [Date]\n");
                        }
                    }
                    paymentDataFormat.append("\n-------------------------------------------------------\n");

                    paymentWriter.write(paymentDataFormat.toString());
                    existingTPNumbers.add(tpNum);
                }
                break;
            }
        }

        paymentWriter.close(); 
    }
    
    private void pr_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pr_backActionPerformed
        registration_main_manager rm = new registration_main_manager(managerName, tpNum, Password, email);
        rm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pr_backActionPerformed

    private void pr_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pr_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pr_dateActionPerformed

    private void pr_monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pr_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pr_monthActionPerformed

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
            java.util.logging.Logger.getLogger(pending_Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pending_Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pending_Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pending_Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pending_Registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton pr_back;
    private javax.swing.JButton pr_confirm;
    private javax.swing.JTextField pr_date;
    private javax.swing.JTextField pr_month;
    private javax.swing.JTextField pr_year;
    // End of variables declaration//GEN-END:variables
}
