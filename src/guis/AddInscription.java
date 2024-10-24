/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package guis;

import DialogFram.ValidationMessageDialog;
import domaine.CategoreNiveau;
import domaine.Enseignant;
import domaine.Etudiant;
import domaine.Groupe;
import domaine.Inscription;
import domaine.Matiere;
import domaine.EnseignantMatiere;
import domaine.NiveauEtude;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.java.com.school.impl.CategoreNiveauDAOImpl;
import main.java.com.school.impl.EnseignantDAOImpl;
import main.java.com.school.impl.EtudiantDAOImpl;
import main.java.com.school.impl.GroupeImpl;
import main.java.com.school.impl.GroupeMatiereDAOImpl;
import main.java.com.school.impl.InscriptionDAOImpl;
import main.java.com.school.impl.MatiereDAOImpl;
import main.java.com.school.impl.EnseignantMatiereDAOImpl;
import main.java.com.school.impl.NiveauEtudeDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author client
 */
public class AddInscription extends javax.swing.JDialog {

    home home;
    Connection connection;
    CategoreNiveauDAOImpl categoreNiveauDAOImpl;
    NiveauEtudeDAOImpl niveauEtudeDAOImpl;
    Etudiant etudiant;
    EtudiantDAOImpl etudiantDAOImpl;
    MatiereDAOImpl matiereDAOImpl;
    EnseignantMatiereDAOImpl matiereEnseignantDAOImpl;
    EnseignantDAOImpl enseignantDAOImpl;
    InscriptionDAOImpl inscriptionDAOImpl;
    GroupeImpl groupeImpl;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    ValidationMessageDialog message_validation;

    public AddInscription(java.awt.Frame parent, boolean modal, Etudiant etudiant) {
        super(parent, modal);
        initComponents();
        
        this.home = (home) parent;
        this.etudiant = etudiant;
        try {
            connection = new ConnectionDB().getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        categoreNiveauDAOImpl = new CategoreNiveauDAOImpl(connection);
        niveauEtudeDAOImpl = new NiveauEtudeDAOImpl(connection);
        etudiantDAOImpl = new EtudiantDAOImpl(connection);
        matiereEnseignantDAOImpl = new EnseignantMatiereDAOImpl(connection);
        inscriptionDAOImpl = new InscriptionDAOImpl(connection);
        matiereDAOImpl = new MatiereDAOImpl(connection);
        message_validation = new ValidationMessageDialog(this, home);
        enseignantDAOImpl = new EnseignantDAOImpl(connection);
        groupeImpl = new GroupeImpl(connection);

        lab_codbar.setText(etudiant.getCodeBare());
        lab_mtr.setText(etudiant.getMatricule());
        lab_Nom_mod.setText(etudiant.getNom());
        lab_prenom_mod.setText(etudiant.getPrenom());
        lab_catego_niv.setText(etudiant.getCtegore_niveau().getCategore_niveau_ar());
        lab_niv.setText(etudiant.getNiveau().getNiveauInitialAr());
        //JOptionPane.showMessageDialog(null, etudiant.getCtegore_niveau().getId() + "*****" + etudiant.getNiveau().getId());
        getMatieres_Niveau(etudiant.getCtegore_niveau(), etudiant.getNiveau());
    
    
    }

    public void getMatieres_Niveau(CategoreNiveau categoreNiveau, NiveauEtude niveauEtude) {
        List<Matiere> matieres = matiereDAOImpl.getMatieresNiveauOfCategory(categoreNiveau, niveauEtude);
        
        System.out.println("List Matiers :"+matieres);
        for (Matiere matiere : matieres) {
           JOptionPane.showMessageDialog(null, "add matiere niveau"+matiere.getId());
            matiere_comb.addItem(matiere.getMatiereEtdAr());
        }
    }

    public void setInfoEnseignantByMatiere() {
JOptionPane.showMessageDialog(null, matiere_comb.getSelectedItem().toString()+
                    lab_catego_niv.getText()+lab_niv.getText());
        try {
            Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(matiere_comb.getSelectedItem().toString(),
                   lab_niv.getText(), lab_catego_niv.getText());
            
JOptionPane.showMessageDialog(null, "matier ensig*********** " + matiere.getId());
   List<Enseignant> list_enseignat = matiereEnseignantDAOImpl.findEnseignantByMatiereId(matiere);
if(list_enseignat!=null){
JOptionPane.showMessageDialog(null, "nULL");


            System.out.println("list enseignat:" + list_enseignat);

            Enseignantcombo.removeAllItems();

            for (Enseignant enseignat : list_enseignat) {
                Enseignantcombo.addItem(enseignat.getNomAr() + "-" + enseignat.getPrenomAr());
            }
}
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AddEtudiantForm.class.getName()).log(Level.SEVERE, null, ex);
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
        lab_mtr = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lab_codbar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        matiere_comb = new material.design.Combobox();
        Enseignantcombo = new material.design.Combobox();
        lab_Nom_mod = new javax.swing.JLabel();
        lab_prenom_mod = new javax.swing.JLabel();
        lab_catego_niv = new javax.swing.JLabel();
        lab_niv = new javax.swing.JLabel();
        CheckAllGroups = new javax.swing.JCheckBox();
        buttonRounder31 = new material.design.buttonRounder();
        buttonRounder30 = new material.design.buttonRounder();
        ComboGroup = new material.design.Combobox();
        lab_error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lab_mtr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_mtr.setText("00");
        lab_mtr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_mtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 320, 30));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("N°");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 20, -1));

        lab_codbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_codbar.setText("00");
        lab_codbar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_codbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 270, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("CodBar");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 20));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("تعديل معلومات الطالب");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 330, 40));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("اللقب :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 50, 20));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("الاسم :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 70, 20));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("االمستوى : ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 70, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("القسم :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 90, 20));

        matiere_comb.setLabeText("المادة");
        matiere_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matiere_combActionPerformed(evt);
            }
        });
        jPanel1.add(matiere_comb, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 170, -1));

        Enseignantcombo.setLabeText("استاذ المادة");
        Enseignantcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnseignantcomboActionPerformed(evt);
            }
        });
        jPanel1.add(Enseignantcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 170, -1));

        lab_Nom_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_Nom_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 170, 30));

        lab_prenom_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_prenom_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 170, 30));

        lab_catego_niv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_catego_niv, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 170, 30));

        lab_niv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_niv, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 170, 30));

        CheckAllGroups.setText("الكل");
        CheckAllGroups.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckAllGroups.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(CheckAllGroups, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        buttonRounder31.setBackground(new java.awt.Color(153, 153, 153));
        buttonRounder31.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder31.setText("إلغاء");
        buttonRounder31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder31ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonRounder31, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, 100, 30));

        buttonRounder30.setBackground(new java.awt.Color(102, 102, 255));
        buttonRounder30.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder30.setText("حفظ");
        buttonRounder30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder30ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonRounder30, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 100, 30));

        ComboGroup.setLabeText("الفوج");
        ComboGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboGroupActionPerformed(evt);
            }
        });
        jPanel1.add(ComboGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 150, -1));

        lab_error.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lab_error.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(lab_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 310, 170, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void matiere_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matiere_combActionPerformed
        if (matiere_comb.getSelectedIndex() != -1 && !lab_catego_niv.getText().equals("")
                && !lab_catego_niv.getText().equals("")) {
            setInfoEnseignantByMatiere();

        } else {
            Enseignantcombo.removeAllItems();
            ComboGroup.removeAllItems();
        }

    }//GEN-LAST:event_matiere_combActionPerformed

    private void EnseignantcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnseignantcomboActionPerformed

        if (matiere_comb.getSelectedIndex() != -1 && Enseignantcombo.getSelectedIndex() != -1) {
            try {
                Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(matiere_comb.getSelectedItem().toString(),
                        lab_niv.getText(), lab_catego_niv.getText());
                String FullName = (String) Enseignantcombo.getSelectedItem();

                String Nom = FullName.split("-")[0];
                String Prenom = FullName.split("-")[1];
                Enseignant enseignat = enseignantDAOImpl.findByFullName(Nom, Prenom);

                if (enseignat != null) {
                    List<Groupe> list_group_etude = groupeImpl.findGroupsByMatiereAndEnseignat(new EnseignantMatiere(0, enseignat, matiere, LocalDate.now()), CheckAllGroups.isSelected());

                    ComboGroup.removeAllItems();
                    for (Groupe groupe : list_group_etude) {
                        ComboGroup.addItem(groupe.getName_group());
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(AddEtudiantForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_EnseignantcomboActionPerformed

    private void buttonRounder31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder31ActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonRounder31ActionPerformed

    private void buttonRounder30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder30ActionPerformed
        if (matiere_comb.getSelectedIndex() != -1) {
            Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(matiere_comb.getSelectedItem().toString(),
                    lab_niv.getText(), lab_catego_niv.getText());
            if (inscriptionDAOImpl.findByEtudiantAndMatiere(etudiant, matiere) == null) {
                Inscription inscription = new Inscription(0, etudiant, matiere, LocalDate.now());
                if (inscriptionDAOImpl.save(inscription) > 0) {
                    this.dispose();
                    message_validation.showMessage(" تأكيد", "تم التسجيل الطالب في مادة  :" + matiere.getMatiereEtdAr());
                }
            } else {
                lab_error.setText("الطالب مسجل في هذه المادة مسبقا");
            }

        }
    }//GEN-LAST:event_buttonRounder30ActionPerformed

    private void ComboGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboGroupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboGroupActionPerformed

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
            java.util.logging.Logger.getLogger(AddInscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddInscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddInscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddInscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AddInscription dialog = new AddInscription(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox CheckAllGroups;
    private material.design.Combobox ComboGroup;
    private material.design.Combobox Enseignantcombo;
    private material.design.buttonRounder buttonRounder30;
    private material.design.buttonRounder buttonRounder31;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lab_Nom_mod;
    private javax.swing.JLabel lab_catego_niv;
    private javax.swing.JLabel lab_codbar;
    private javax.swing.JLabel lab_error;
    private javax.swing.JLabel lab_mtr;
    private javax.swing.JLabel lab_niv;
    private javax.swing.JLabel lab_prenom_mod;
    private material.design.Combobox matiere_comb;
    // End of variables declaration//GEN-END:variables
}
