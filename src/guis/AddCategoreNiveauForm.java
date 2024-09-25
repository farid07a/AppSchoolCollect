/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package guis;

import DialogFram.ValidationMessageDialog;
import domaine.CategoreNiveau;
import domaine.NiveauEtude;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.event.PrintJobEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.java.com.school.impl.CategoreNiveauDAOImpl;
import main.java.com.school.impl.NiveauEtudeDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;
import material.design.ScrollBar;
import ui.table.TableCustom;

/**
 *
 * @author client
 */
public class AddCategoreNiveauForm extends javax.swing.JDialog {

    home home;
    Connection connection;
    CategoreNiveauDAOImpl categoreNiveauDAOImpl;
    NiveauEtudeDAOImpl niveauEtudeDAOImpl;
    ValidationMessageDialog message_validation;

    public AddCategoreNiveauForm(java.awt.Frame parent) {
        super(parent);
        this.home = (home) parent;

        initComponents();
        setLocationRelativeTo(this);
        setDesignTable(tab_niveau, jScrollPane2);

        try {
            connection = new ConnectionDB().getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        categoreNiveauDAOImpl = new CategoreNiveauDAOImpl(connection);
        niveauEtudeDAOImpl = new NiveauEtudeDAOImpl(connection);
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
        txt_categor_niv = new material.design.TextField();
        txt_category_niv_fr = new material.design.TextField();
        buttonRounder17 = new material.design.buttonRounder();
        buttonRounder19 = new material.design.buttonRounder();
        jPanel3 = new javax.swing.JPanel();
        txt_class_nam_fr = new material.design.TextField();
        txt_name_niv = new material.design.TextField();
        tableScrollButton1 = new ui.table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_niveau = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_add_niv = new material.design.buttonRounder();
        buttonRounder22 = new material.design.buttonRounder();
        textAreaScroll1 = new material.design.TextAreaScroll();
        txt_descrip_niv = new material.design.TextArea();
        lab_nbr_class = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        buttonRounder20 = new material.design.buttonRounder();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textAreaScroll2 = new material.design.TextAreaScroll();
        textDesc_categore = new material.design.TextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txt_categor_niv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_categor_niv.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_categor_niv.setLabelText("مستوى دراسي جديد");
        txt_categor_niv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_categor_nivKeyTyped(evt);
            }
        });

        txt_category_niv_fr.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_category_niv_fr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_category_niv_fr.setLabelText("Nouveau niveau etude");
        txt_category_niv_fr.setLangue(1);
        txt_category_niv_fr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_category_niv_frActionPerformed(evt);
            }
        });

        buttonRounder17.setBackground(new java.awt.Color(153, 153, 153));
        buttonRounder17.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder17.setText("إلغاء");

        buttonRounder19.setBackground(new java.awt.Color(102, 102, 255));
        buttonRounder19.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder19.setText("حفظ");
        buttonRounder19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder19ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_class_nam_fr.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_class_nam_fr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_class_nam_fr.setLabelText("Nouveau classe");
        txt_class_nam_fr.setLangue(1);
        txt_class_nam_fr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_class_nam_frActionPerformed(evt);
            }
        });
        jPanel3.add(txt_class_nam_fr, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 220, 50));

        txt_name_niv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_name_niv.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_name_niv.setLabelText("قسم جديد\n");
        jPanel3.add(txt_name_niv, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 220, 50));

        tab_niveau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الوصف", "classe", "القسم"
            }
        ));
        tab_niveau.setSelectionBackground(new java.awt.Color(235, 235, 235));
        jScrollPane2.setViewportView(tab_niveau);

        tableScrollButton1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.add(tableScrollButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 630, 140));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 210, 25));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 310, 23));

        btn_add_niv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus - Copie - Copie.png"))); // NOI18N
        btn_add_niv.setText("اضافة القسم ");
        btn_add_niv.setToolTipText("Nouvelle Type");
        btn_add_niv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_nivActionPerformed(evt);
            }
        });
        jPanel3.add(btn_add_niv, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 130, 40));

        buttonRounder22.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supprimer (1).png"))); // NOI18N
        buttonRounder22.setToolTipText("Annnuler la command");
        buttonRounder22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder22ActionPerformed(evt);
            }
        });
        jPanel3.add(buttonRounder22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 30, 30));

        textAreaScroll1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        textAreaScroll1.setLabelText("Description ");

        txt_descrip_niv.setColumns(20);
        txt_descrip_niv.setRows(3);
        txt_descrip_niv.setToolTipText("");
        textAreaScroll1.setViewportView(txt_descrip_niv);

        jPanel3.add(textAreaScroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 350, -1));

        lab_nbr_class.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_nbr_class.setForeground(new java.awt.Color(0, 0, 204));
        lab_nbr_class.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_nbr_class.setText("0");
        jPanel3.add(lab_nbr_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 60, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("عدد الأقسام :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 80, -1));

        buttonRounder20.setBackground(new java.awt.Color(0, 204, 204));
        buttonRounder20.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder20.setText("اضافة اقسام للمستوى الجديد ");
        buttonRounder20.setToolTipText("");
        buttonRounder20.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonRounder20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder20ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));

        textAreaScroll2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        textAreaScroll2.setLabelText("Description ");

        textDesc_categore.setColumns(20);
        textDesc_categore.setRows(3);
        textDesc_categore.setToolTipText("");
        textAreaScroll2.setViewportView(textDesc_categore);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("مستوى دراسي جديد");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txt_category_niv_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txt_categor_niv, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(buttonRounder20, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRounder17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(buttonRounder19, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(371, Short.MAX_VALUE)
                    .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_category_niv_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_categor_niv, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(82, 82, 82)
                .addComponent(buttonRounder20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRounder17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRounder19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(130, Short.MAX_VALUE)
                    .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(471, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRounder19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder19ActionPerformed
        if (txt_categor_niv.getText().equals("")) {
            jLabel7.setText("أدخل المستوى");
        } else {

            if (categoreNiveauDAOImpl.getCategory_by_name(txt_categor_niv.getText()) == null) {
                CategoreNiveau categoreNiveau = new CategoreNiveau(0, txt_categor_niv.getText(), txt_category_niv_fr.getText(), textDesc_categore.getText());
                if (categoreNiveauDAOImpl.save(categoreNiveau) > 0) {
                  
                    save_niveau_from_table();
                    this.dispose();
                    message_validation = new ValidationMessageDialog(this, home);
                    home.setInfoCategorNiveauInTab();
                    if(tab_niveau.getRowCount()!=0){
                        tab_niveau.removeAll();
                        lab_nbr_class.setText(0+"");
                        message_validation.showMessage(" تأكيد ", "تمت إضافةالمستوى و الأقسام  بنجاح  .");
                        
                    }else{
                        message_validation.showMessage(" تأكيد ", "تمت إضافةالمستوى  بنجاح  .");
                    }

                }
            }else {
                jLabel7.setText("المستوى الذي أدخلته موجود");
            }
        }


    }//GEN-LAST:event_buttonRounder19ActionPerformed

    private void txt_category_niv_frActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_category_niv_frActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_category_niv_frActionPerformed

    private void txt_class_nam_frActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_class_nam_frActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_class_nam_frActionPerformed

    private void buttonRounder20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder20ActionPerformed


    }//GEN-LAST:event_buttonRounder20ActionPerformed

    private void btn_add_nivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_nivActionPerformed

        if (!txt_name_niv.getText().isEmpty()) {
            if(!exist_niveau()){
            String niveau = txt_name_niv.getText();
            String niveau_fr = txt_class_nam_fr.getText();
            String descrip = txt_descrip_niv.getText();
            DefaultTableModel model = (DefaultTableModel) tab_niveau.getModel();
            model.addRow(new Object[]{descrip, niveau_fr, niveau});
            lab_nbr_class.setText(tab_niveau.getRowCount() + "");
            cleanItems();}

        } else {
            jLabel5.setText("ادخل اسم القسم");
        }

    }//GEN-LAST:event_btn_add_nivActionPerformed

    private void buttonRounder22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder22ActionPerformed
        if (tab_niveau.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) tab_niveau.getModel();
            model.removeRow(tab_niveau.getSelectedRow()); // Remove the row from the model
            lab_nbr_class.setText(tab_niveau.getRowCount() + "");
        }

    }//GEN-LAST:event_buttonRounder22ActionPerformed

    private void txt_categor_nivKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_categor_nivKeyTyped
        jLabel7.setText("");
    }//GEN-LAST:event_txt_categor_nivKeyTyped

    public void setDesignTable(JTable tab, JScrollPane scrol) {
        TableCustom.apply(scrol, TableCustom.TableType.DEFAULT);
        tab.getTableHeader().setFont(new Font("", Font.BOLD, 15));
        tab.setFont(new Font("", Font.BOLD, 14));
        scrol.setVerticalScrollBar(new ScrollBar());
        scrol.getVerticalScrollBar().setBackground(Color.WHITE);
        scrol.getViewport().setBackground(Color.white);// make table without rouw white
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrol.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

    }

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
            java.util.logging.Logger.getLogger(AddCategoreNiveauForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCategoreNiveauForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCategoreNiveauForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCategoreNiveauForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddCategoreNiveauForm dialog = new AddCategoreNiveauForm(new home());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private material.design.buttonRounder btn_add_niv;
    private material.design.buttonRounder buttonRounder17;
    private material.design.buttonRounder buttonRounder19;
    private material.design.buttonRounder buttonRounder20;
    private material.design.buttonRounder buttonRounder22;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lab_nbr_class;
    private javax.swing.JTable tab_niveau;
    private ui.table.TableScrollButton tableScrollButton1;
    private material.design.TextAreaScroll textAreaScroll1;
    private material.design.TextAreaScroll textAreaScroll2;
    private material.design.TextArea textDesc_categore;
    private material.design.TextField txt_categor_niv;
    private material.design.TextField txt_category_niv_fr;
    private material.design.TextField txt_class_nam_fr;
    private material.design.TextArea txt_descrip_niv;
    private material.design.TextField txt_name_niv;
    // End of variables declaration//GEN-END:variables
public void cleanItems() {
        txt_name_niv.setText("");
        txt_class_nam_fr.setText("");
        txt_descrip_niv.setText("");
    }

    public void save_niveau_from_table() {
        CategoreNiveau categoreNiveau = categoreNiveauDAOImpl.getlast();
        JOptionPane.showMessageDialog(null, " tab_niveau.getRowCount()"+tab_niveau.getRowCount());
        for (int row = 0; row < tab_niveau.getRowCount(); row++) {
            String niveauInitialAr = tab_niveau.getValueAt(row, 2).toString();
            String niveauInitialFr = tab_niveau.getValueAt(row, 1).toString();
            String description = tab_niveau.getValueAt(row, 0).toString();
            NiveauEtude niveauEtude = new NiveauEtude(0, niveauInitialAr, niveauInitialFr, description, categoreNiveau.getId());
            niveauEtudeDAOImpl.save(niveauEtude);
        }

    }
    public boolean exist_niveau() {
        for (int row = 0; row < tab_niveau.getRowCount(); row++) {
            if (txt_name_niv.getText().equals(tab_niveau.getValueAt(row, 2).toString())
                    && txt_categor_niv.getText().equals(tab_niveau.getValueAt(row, 3))) {
                jLabel5.setText("تم اضافة هذا القسم بنفس المستوى  في الجدول");
                return true;
            }
        }
        return false;
    }
}
