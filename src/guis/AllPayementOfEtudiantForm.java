/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package guis;

import domaine.Etudiant;
import domaine.Matiere;
import domaine.Payement;
import domaine.PayementDetaille;
import domaine.Presence;
import domaine.Seance_Matiere;
import java.awt.Color;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.java.com.school.impl.CategoreNiveauDAOImpl;
import main.java.com.school.impl.EnseignantDAOImpl;
import main.java.com.school.impl.EtudiantDAOImpl;
import main.java.com.school.impl.InscriptionDAOImpl;
import main.java.com.school.impl.MatiereDAOImpl;
import main.java.com.school.impl.NiveauEtudeDAOImpl;
import main.java.com.school.impl.PayementDAOImpl;
import main.java.com.school.impl.PayementDetailleDAOImp;
import main.java.com.school.impl.PresenceDAOImpl;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.impl.SeanceMatiereDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;
import material.design.ScrollBar;
import ui.table.TableCustom;

/**
 *
 * @author client
 */
public class AllPayementOfEtudiantForm extends javax.swing.JDialog {

    Home home;
    Connection connection;
    CategoreNiveauDAOImpl categoreNiveauDAOImpl;
    NiveauEtudeDAOImpl niveauEtudeDAOImpl;
    MatiereDAOImpl matiereDAOImpl;
    EnseignantDAOImpl enseignantDAOImpl;
    InscriptionDAOImpl inscriptionDAOImpl;
    EtudiantDAOImpl etudiantDAOImpl;
    SeanceMatiereDAOImpl seanceMatiereDAOImpl;
    SeanceDAOImpl sceanceDAOImpl;
    PayementDAOImpl payementDAOImpl;
    PresenceDAOImpl presenceDAOImpl;
    PayementDetailleDAOImp PayementDetailleDAOImp;
    List<Seance_Matiere> seance_Matieres_payee = new ArrayList<>();
    List<Payement> payements = new ArrayList<>();
    Etudiant etudiant;

    public AllPayementOfEtudiantForm(java.awt.Frame parent, boolean modal, Etudiant etudiant) {
        super(parent, modal);
        this.home = (Home) parent;
        initComponents();
        setLocationRelativeTo(this.home);
        this.etudiant = etudiant;

        try {
            connection = new ConnectionDB().getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        etudiantDAOImpl = new EtudiantDAOImpl(connection);
        categoreNiveauDAOImpl = new CategoreNiveauDAOImpl(connection);
        niveauEtudeDAOImpl = new NiveauEtudeDAOImpl(connection);
        matiereDAOImpl = new MatiereDAOImpl(connection);
        enseignantDAOImpl = new EnseignantDAOImpl(connection);
        inscriptionDAOImpl = new InscriptionDAOImpl(connection);
        seanceMatiereDAOImpl = new SeanceMatiereDAOImpl(connection);
        payementDAOImpl = new PayementDAOImpl(connection);
        sceanceDAOImpl = new SeanceDAOImpl(connection);
        presenceDAOImpl = new PresenceDAOImpl(connection);
        PayementDetailleDAOImp = new PayementDetailleDAOImp(connection);
        ///  setDesignTable(jTable5, jScrollPane16);
        setDesignTable(tab_payement_etud, jScrollPane2);
        setDesignTable(table_detaill, jScrollPane1);

        lab_nam_prenom.setText(etudiant.getNom()+" " +etudiant.getPrenom());
        lab_catego_niv.setText(etudiant.getCtegore_niveau().getCategore_niveau_ar());
        lab_niv.setText(etudiant.getNiveau().getNiveauInitialAr());
        //  SearchTable(jTable5, jTextField1);
        setInfoInTable();

    }

    public void setInfoInTable() {
        DefaultTableModel model = (DefaultTableModel) tab_payement_etud.getModel();
        List<Payement> payements = payementDAOImpl.getAll_Payement_Credit_Etudiant(etudiant);
        double Total_payement = 0;
        double total_credit =0;
        
        for (Payement payement : payements) {
            if(payement.getTypePayement().equals("ديون") ){
            total_credit=total_credit +payement.getPrixTotal();
            }else{
            Total_payement =Total_payement +payement.getPrixTotal();
            }
            model.addRow(new Object[]{"",payement.getNb_seance(), payement.getDate(), payement.getTypePayement(), payement.getPrixTotal(),
                payement.getPrix(),
                payement.getMatiere().getMatiereEtdAr(),
                payement.getId()});
        }
        lab_total_credit.setText(total_credit+"");
        lab_total_payement.setText(Total_payement+"");
    }

    public void setInfoInPayementDetaille(Payement payement) {//payement
        DefaultTableModel model = (DefaultTableModel) table_detaill.getModel();
        model.setRowCount(0);
       if( payement.getSeance()!= null){
        LocalDate date_debut = payement.getDate();
        LocalDate date_fin_seance = payement.getSeance().getDate_sceance();
        List<Presence> presences = presenceDAOImpl.getPresenceByTowDates(etudiant, payement.getMatiere(), date_debut, date_fin_seance);
        for (Presence presence : presences) {
            model.addRow(new Object[]{
                presence.isEtat(),
                presence.getDatePresence(),
                presence.getMatiere().getMatiereEtdAr(),
                presence.getId()});
        }
       }
    }

    public void setInfoPaymentCreditDetailleInTable(Payement payement) {
        DefaultTableModel model = (DefaultTableModel) table_detaill.getModel();
        model.setRowCount(0);
        try {

            List<PayementDetaille> payementDetailles = new PayementDetailleDAOImp(ConnectionDB.getConnection()).getPayementDetailleCredit(payement.getId());
            if (!payementDetailles.isEmpty()) {
                LocalDate date_debut = payementDetailles.get(0).getSeance().getDate_sceance();
                int last_payem_detaill_index=payementDetailles.size()-1;
                LocalDate date_fin_seance = payementDetailles.get(last_payem_detaill_index).getSeance().getDate_sceance();
               
                System.out.println(""+date_debut +"----"+date_fin_seance);
                List<Presence> presences = presenceDAOImpl.getPresenceByTowDates(etudiant, payement.getMatiere(), date_debut, date_fin_seance);
                for (Presence presence : presences) {
                    model.addRow(new Object[]{"",
                        presence.isEtat(),
                        presence.getDatePresence(),
                        presence.getMatiere().getMatiereEtdAr(),
                        presence.getId()});
                }
            }
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(CreditDetailleForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setDesignTable(JTable tab, JScrollPane scrol) {
        TableCustom.apply(scrol, TableCustom.TableType.DEFAULT);
        //tab.getTableHeader().setFont(new Font("", Font.BOLD, 15));
        tab.getTableHeader().setFont(new java.awt.Font("Times New Roman", 1, 18));
        //tab.setFont(new Font("", Font.BOLD, 14));
        tab.setFont(new java.awt.Font("Times New Roman", 1, 16));
        scrol.setVerticalScrollBar(new ScrollBar());
        scrol.getVerticalScrollBar().setBackground(Color.WHITE);
        scrol.getViewport().setBackground(Color.white);// make table without rouw white
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrol.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
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
        lab_nb_seance = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lab_nb_seance_payee = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lab_nb_seance_no_payee = new javax.swing.JLabel();
        tableScrollButton1 = new ui.table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_payement_etud = new javax.swing.JTable();
        tableScrollButton2 = new ui.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_detaill = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        lab_niv = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lab_catego_niv = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lab_nam_prenom = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lab_total_payement = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lab_total_credit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lab_nb_seance.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_nb_seance.setForeground(new java.awt.Color(0, 51, 204));
        lab_nb_seance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_nb_seance.setText("0");
        jPanel1.add(lab_nb_seance, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 510, 140, 27));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("العدد الإجمالي للحصص");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 510, -1, 27));

        lab_nb_seance_payee.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_nb_seance_payee.setForeground(new java.awt.Color(0, 51, 204));
        lab_nb_seance_payee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_nb_seance_payee.setText("0");
        jPanel1.add(lab_nb_seance_payee, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 510, 130, 27));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("عدد الحصص المدفوع");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 510, -1, 27));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("عدد الحصص الغير مدفوعة ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 510, -1, 27));

        lab_nb_seance_no_payee.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_nb_seance_no_payee.setForeground(new java.awt.Color(0, 51, 204));
        lab_nb_seance_no_payee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_nb_seance_no_payee.setText("0");
        jPanel1.add(lab_nb_seance_no_payee, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 510, 80, 27));

        tab_payement_etud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ملاحضة", "عدد الحصص", "تاريخ الدفع", "نوع الدفع", "المبلغ المدفوع", "ثمن المادة", "المادة", "Id_payement"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tab_payement_etud);

        tableScrollButton1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(tableScrollButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 696, 299));

        table_detaill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الحضور", "التاريخ", "المادة", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_detaill);

        tableScrollButton2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(tableScrollButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 445, 420));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("القسم :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 53, -1));

        lab_niv.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        lab_niv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lab_niv.setText("jLabel8");
        jPanel1.add(lab_niv, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 191, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, -1, -1));

        lab_catego_niv.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        lab_catego_niv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lab_catego_niv.setText("jLabel8");
        jPanel1.add(lab_catego_niv, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 80, 190, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("المستوى :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 80, 78, -1));

        lab_nam_prenom.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        lab_nam_prenom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lab_nam_prenom.setText("jLabel8");
        jPanel1.add(lab_nam_prenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 170, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("الاسم و اللقب :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 50, -1, 22));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("الحصص الدراسية المدفوعة أو بديون");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 68, 206, 30));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("عمليات الدفع الخاصة بالطالب");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 0, 1150, 40));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("اجمالي الدفع");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 420, 80, 40));

        lab_total_payement.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lab_total_payement.setForeground(new java.awt.Color(102, 102, 255));
        lab_total_payement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_total_payement.setText("0.0");
        lab_total_payement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_total_payement, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 420, 220, 40));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("اجمالي الديون");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 460, 80, 40));

        lab_total_credit.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lab_total_credit.setForeground(new java.awt.Color(255, 0, 51));
        lab_total_credit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_total_credit.setText("0.0");
        lab_total_credit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_total_credit, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 460, 220, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tab_payement_etud.getSelectedRow() != -1) {
            int row = tab_payement_etud.getSelectedRow();
            int id_payement = (int) tab_payement_etud.getValueAt(row, 7);
            Payement payement = payementDAOImpl.findById(id_payement);
            if (payement.getTypePayement().equals("ديون")) {
                setInfoPaymentCreditDetailleInTable(payement);
            } else {
                setInfoInPayementDetaille(payement);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AllPayementOfEtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllPayementOfEtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllPayementOfEtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllPayementOfEtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AllPayementOfEtudiantForm dialog = new AllPayementOfEtudiantForm(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lab_catego_niv;
    private javax.swing.JLabel lab_nam_prenom;
    private javax.swing.JLabel lab_nb_seance;
    private javax.swing.JLabel lab_nb_seance_no_payee;
    private javax.swing.JLabel lab_nb_seance_payee;
    private javax.swing.JLabel lab_niv;
    private javax.swing.JLabel lab_total_credit;
    private javax.swing.JLabel lab_total_payement;
    private javax.swing.JTable tab_payement_etud;
    private ui.table.TableScrollButton tableScrollButton1;
    private ui.table.TableScrollButton tableScrollButton2;
    private javax.swing.JTable table_detaill;
    // End of variables declaration//GEN-END:variables
}
