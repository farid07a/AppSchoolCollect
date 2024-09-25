/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package guis;

import domaine.Etudiant;
import domaine.Payement;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import main.java.com.school.impl.EtudiantDAOImpl;
import main.java.com.school.impl.PayementDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;
import material.design.ScrollBar;
import ui.table.TableCustom;

/**
 *
 * @author client
 */
public class Pan_payement extends javax.swing.JPanel {

    Connection connection;
    PayementDAOImpl payementDAOImpl;
    EtudiantDAOImpl etudiantDAOImpl;

    public Pan_payement() {
        initComponents();
//        jTable5.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//               Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//               
//                if (column == 4) {
//                int val= (int)value;
//                String typePayement = jTable5.getValueAt(row, 3).toString(); // Get value from the fourth column
//              //  if (value instanceof Number && ((Number) value).intValue() == 0 ) {
//               if (val > 0) {
//                    cell.setBackground(Color.WHITE);
//                    cell.setBackground(new Color(255,163,163));
//                }
//                   // return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody           
//            }
//                        return cell;
//
//         } 
//    });
//        jTable5.repaint();
//      jTable5.revalidate();
        
        try {
            connection = ConnectionDB.getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        payementDAOImpl = new PayementDAOImpl(connection);
        etudiantDAOImpl = new EtudiantDAOImpl(connection);
        setDesignTable(jTable5, jScrollPane16);
        SearchTable(jTable5, searchText6);

        buttonGroup1.add(rad_payee);
        buttonGroup1.add(radio_credit);
        buttonGroup1.add(rdio_all);
        prepareUI();
             jTable5.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
             public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                      DefaultTableCellRenderer MyCellrendar = new DefaultTableCellRenderer();

            // Get values from column 3 and column 4
            int col4Value =(int) table.getValueAt(row, 4); // Column index 2
            Object col3Value = table.getValueAt(row, 3); // Column index 3

            // Check conditions: column 3 == 0 and column 4 == 'ديون'
           // "1".equals(col4Value.toString()) 
            if (col4Value==1 && "ديون".equals(col3Value)) {
                 MyCellrendar.setForeground(Color.RED);
                 table.getColumnModel().getColumn(4).setCellRenderer(MyCellrendar);
            } else {
                if (col4Value>0 && !"ديون".equals(col3Value)) {
                 MyCellrendar.setForeground(Color.GREEN);
                 table.getColumnModel().getColumn(4).setCellRenderer(MyCellrendar);
                }else{
                MyCellrendar.setForeground(Color.BLUE); // Default color
                table.getColumnModel().getColumn(4).setCellRenderer(MyCellrendar);}
            }
              setHorizontalAlignment(SwingConstants.CENTER);

            return cell;
        }
    
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//               Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//               DefaultTableCellRenderer MyCellrendar = new DefaultTableCellRenderer();
//  
//                if (column == 4) {
//                int val= (int)value;
//                String typePayement = jTable5.getValueAt(row, 3).toString(); // Get value from the fourth column
//                //  if (value instanceof Number && ((Number) value).intValue() == 0 ) {
//               if (val > 0 && typePayement.equals("ديون")) {
//               
//                 MyCellrendar.setForeground(Color.RED);
//                             table.getColumnModel().getColumn(4).setCellRenderer(MyCellrendar);
//
//                    //cell.setForeground( Color.RED);
//                }
//               if(val > 0 && !typePayement.equals("ديون"))
//                       MyCellrendar.setForeground(Color.GREEN);
//                           table.getColumnModel().getColumn(4).setCellRenderer(MyCellrendar);
//
//             //  cell.setForeground( Color.GREEN);
//               if(val ==0 && !typePayement.equals("ديون"))
//                   cell.setForeground( Color.BLUE);
//                           table.getColumnModel().getColumn(4).setCellRenderer(MyCellrendar);
//
//                   // return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody           
//            }
//                
//                        
//            
//            return cell;
//
//         } 
    });
//        jTable5.repaint();
//      jTable5.revalidate();
    }

    public void prepareUI() {

        setInfoPayementInTab();
    }

    public void setInfoPayementInTab() {
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
        List<Payement> payements = payementDAOImpl.findAll();

        for (Payement payement : payements) {
            int id_payement = payement.getId();
            int id_etudiant = payement.getEtudiant().getId();
            int id_matiere = payement.getMatiere().getId();
            int id_seance;
            if(payement.getSeance()!=null){
             id_seance = payement.getSeance().getId();
            }else{
            id_seance=0;
            }
            String name_prenom = payement.getEtudiant().getNom() + " " + payement.getEtudiant().getPrenom();
            String matiere = payement.getMatiere().getMatiereEtdAr();
            int nb_seance = payement.getNb_seance();

            double Prix = payement.getMatiere().getPrix();

            double prxi_seance = Prix / payement.getMatiere().getNum_sceance_moins();
            double prix_payee = payement.getPrixPaye();
            double prix_total = payement.getPrixTotal();
            String Type_Payement = payement.getTypePayement();
            LocalDate date = payement.getDate();

            model.addRow(new Object[]{date, prix_total, prix_payee, Type_Payement,
                 nb_seance, prxi_seance, Prix, matiere, name_prenom, id_seance, id_matiere, id_etudiant, id_payement}
            );
        }
    }

    public void setInfoCridetInTab() {
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
        List<Payement> payements = payementDAOImpl.findAll();

        for (Payement payement : payements) {
            int id_payement = payement.getId();
            int id_etudiant = payement.getEtudiant().getId();
            int id_matiere = payement.getMatiere().getId();
            
            int id_seance = 0;
            if(payement.getSeance() != null )
            payement.getSeance().getId();
            String name_prenom = payement.getEtudiant().getNom() + " " + payement.getEtudiant().getPrenom();
            String matiere = payement.getMatiere().getMatiereEtdAr();
            int nb_seance = +payement.getNb_seance();

            double Prix = payement.getPrix();
            double prxi_seance = 0;
            double prix_payee = payement.getPrixPaye();
            double prix_total = payement.getPrixTotal();
            String Type_Payement = payement.getTypePayement();
            LocalDate date = payement.getDate();
            if (Type_Payement.equals("ديون")) {

                model.addRow(new Object[]{date, prix_total, prix_payee, Type_Payement,
                     nb_seance, Prix, matiere, name_prenom, id_seance, id_matiere, id_etudiant, id_payement}
                );

            }
        }
    }

    public void setInfoOnelyPayementInTab() {
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
        List<Payement> payements = payementDAOImpl.findAll();

        for (Payement payement : payements) {
            int id_payement = payement.getId();
            int id_etudiant = payement.getEtudiant().getId();
            int id_matiere = payement.getMatiere().getId();
            int id_seance =0; //error
            if(payement.getSeance()!=null){
               payement.getSeance().getId();
            }
            String name_prenom = payement.getEtudiant().getNom() + " " + payement.getEtudiant().getPrenom();
            String matiere = payement.getMatiere().getMatiereEtdAr();
            int nb_seance = +payement.getNb_seance();

            double Prix = payement.getPrix();
            double prxi_seance = 0;
            double prix_payee = payement.getPrixPaye();
            double prix_total = payement.getPrixTotal();
            String Type_Payement = payement.getTypePayement();
            LocalDate date = payement.getDate();
            if (Type_Payement.equals("شهري")) {

                model.addRow(new Object[]{date, prix_total, prix_payee, Type_Payement,
                     nb_seance, Prix, matiere, name_prenom, id_seance, id_matiere, id_etudiant, id_payement}
                );

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        searchText6 = new material.design.SearchText();
        tableScrollButton1 = new ui.table.TableScrollButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        rad_payee = new javax.swing.JRadioButton();
        radio_credit = new javax.swing.JRadioButton();
        rdio_all = new javax.swing.JRadioButton();
        buttonRounder1 = new material.design.buttonRounder();
        buttonRounder3 = new material.design.buttonRounder();
        buttonRounder2 = new material.design.buttonRounder();

        setBackground(new java.awt.Color(255, 255, 255));

        searchText6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        searchText6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchText6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText6ActionPerformed(evt);
            }
        });

        jTable5.setForeground(new java.awt.Color(255, 255, 255));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "تاريخ", "prix total", "المبلغ المدفوع", "نوع الدفع", "عدد الحصص", "ثمن الحصة", "ثمن المادة", "المادة", "الاسم و اللقب", "ID_Seance", "ID_Matiere", "ID_Etudiant", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(jTable5);

        tableScrollButton1.add(jScrollPane16, java.awt.BorderLayout.CENTER);

        rad_payee.setText("مدفوعات");
        rad_payee.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rad_payee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad_payeeActionPerformed(evt);
            }
        });

        radio_credit.setText("بديون");
        radio_credit.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        radio_credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_creditActionPerformed(evt);
            }
        });

        rdio_all.setText("الجميع");
        rdio_all.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rdio_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdio_allActionPerformed(evt);
            }
        });

        buttonRounder1.setBackground(new java.awt.Color(204, 0, 204));
        buttonRounder1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder1.setText("تسديد الديون");
        buttonRounder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder1ActionPerformed(evt);
            }
        });

        buttonRounder3.setBackground(new java.awt.Color(255, 158, 13));
        buttonRounder3.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder3.setText("الدفع");
        buttonRounder3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonRounder3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder3ActionPerformed(evt);
            }
        });

        buttonRounder2.setBackground(new java.awt.Color(0, 153, 153));
        buttonRounder2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder2.setText("مدفوعات الطالب");
        buttonRounder2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonRounder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 1139, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(radio_credit)
                        .addGap(18, 18, 18)
                        .addComponent(rad_payee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdio_all)
                        .addGap(117, 117, 117)
                        .addComponent(searchText6, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(buttonRounder2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonRounder1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRounder3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRounder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRounder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRounder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radio_credit)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchText6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rad_payee)
                        .addComponent(rdio_all)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchText6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchText6ActionPerformed

    private void radio_creditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_creditActionPerformed
        if (radio_credit.isSelected())
            setInfoCridetInTab();
    }//GEN-LAST:event_radio_creditActionPerformed

    private void rad_payeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad_payeeActionPerformed
        if (rad_payee.isSelected())
            setInfoOnelyPayementInTab();
    }//GEN-LAST:event_rad_payeeActionPerformed

    private void rdio_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdio_allActionPerformed
        if (rdio_all.isSelected())
            setInfoPayementInTab();
    }//GEN-LAST:event_rdio_allActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
       
    }//GEN-LAST:event_jTable5MouseClicked

    private void buttonRounder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder1ActionPerformed
 if (jTable5.getSelectedRow() != -1) {
            int row = jTable5.getSelectedRow();
           JOptionPane.showMessageDialog(null, jTable5.getValueAt(row, 11).toString() );
            int id_etudiant = (int) jTable5.getValueAt(row, 11);
            Etudiant etudiant = etudiantDAOImpl.findById(id_etudiant);
            System.out.println(""+etudiant);
            new PayementCredit((Frame) SwingUtilities.getWindowAncestor(this), true, etudiant).setVisible(true);
                    
        }    }//GEN-LAST:event_buttonRounder1ActionPerformed

    private void buttonRounder3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder3ActionPerformed
        new PayemmentParMoin((Frame) SwingUtilities.getWindowAncestor(this), true).setVisible(true);
    }//GEN-LAST:event_buttonRounder3ActionPerformed

    private void buttonRounder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder2ActionPerformed
      if (jTable5.getSelectedRow() != -1) {
            int row = jTable5.getSelectedRow();
        int id_etudiant = (int) jTable5.getValueAt(row, 11);
        Etudiant etudiant = etudiantDAOImpl.findById(id_etudiant);
        new  AllPayementOfEtudiantForm((Frame) SwingUtilities.getWindowAncestor(this), true, etudiant).setVisible(true);
      
      }
    }//GEN-LAST:event_buttonRounder2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private material.design.buttonRounder buttonRounder1;
    private material.design.buttonRounder buttonRounder2;
    private material.design.buttonRounder buttonRounder3;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JTable jTable5;
    private javax.swing.JRadioButton rad_payee;
    private javax.swing.JRadioButton radio_credit;
    private javax.swing.JRadioButton rdio_all;
    private material.design.SearchText searchText6;
    private ui.table.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables

    public void SearchTable(JTable table, JTextField textField) {
        TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sort);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = textField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);

                } else {
                    //(?i) recherche insensible à la casse
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = textField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public void setDesignTable(JTable tab, JScrollPane scrol) {
        TableCustom.apply(scrol, TableCustom.TableType.DEFAULT);
        //tab.getTableHeader().setFont(new Font("", Font.BOLD, 15));
       // tab.getTableHeader().setBackground(new Color(153,153,255));
        DefaultTableCellRenderer MyHeaderRender = new DefaultTableCellRenderer();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)tab.getDefaultRenderer(Object.class);
       renderer.setHorizontalAlignment( SwingConstants.CENTER );
        MyHeaderRender.setForeground(Color.WHITE);
        MyHeaderRender.setBackground(new Color(153,153,255));;
       DefaultTableCellRenderer rendererTableHeader= (DefaultTableCellRenderer) tab.getTableHeader().getDefaultRenderer();
       rendererTableHeader.setHorizontalAlignment( SwingConstants.CENTER );
       for(int i=0;i<tab.getColumnCount();i++){
        tab.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(MyHeaderRender);
       }
        tab.getTableHeader().setFont(new java.awt.Font("Times New Roman", 1, 18));
        //tab.setFont(new Font("", Font.BOLD, 14));
        tab.setFont(new java.awt.Font("Times New Roman", 0, 13));
        scrol.setVerticalScrollBar(new ScrollBar());
        scrol.getVerticalScrollBar().setBackground(Color.WHITE);
        scrol.getViewport().setBackground(Color.white);// make table without rouw white
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrol.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }

}
