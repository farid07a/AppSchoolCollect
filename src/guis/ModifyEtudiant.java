/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package guis;

import DialogFram.ValidationMessageDialog;
import com.sun.javafx.application.PlatformImpl;
import domaine.CategoreNiveau;
import domaine.Etudiant;
import domaine.NiveauEtude;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import main.java.com.school.impl.CategoreNiveauDAOImpl;
import main.java.com.school.impl.EtudiantDAOImpl;
import main.java.com.school.impl.NiveauEtudeDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author client
 */
public class ModifyEtudiant extends javax.swing.JDialog {

    Home home;
    Connection connection;
    CategoreNiveauDAOImpl categoreNiveauDAOImpl;
    NiveauEtudeDAOImpl niveauEtudeDAOImpl;
    Etudiant etudiant;
    EtudiantDAOImpl etudiantDAOImpl;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    ValidationMessageDialog message_validation;

    public ModifyEtudiant(java.awt.Frame parent, boolean modal, Etudiant etudiant) {
        super(parent, modal);
        this.home = (Home) parent;
        this.etudiant = etudiant;
        try {
            connection = new ConnectionDB().getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        categoreNiveauDAOImpl = new CategoreNiveauDAOImpl(connection);
        niveauEtudeDAOImpl = new NiveauEtudeDAOImpl(connection);
        etudiantDAOImpl = new EtudiantDAOImpl(connection);
        message_validation = new ValidationMessageDialog(this, home);

        initComponents();
        setLocationRelativeTo(this);

        setInfoCategoreNiveauInCombox();
        setInfoNiveauEtudeInCom();

        lab_codbar.setText(etudiant.getCodeBare());
        lab_mtr.setText(etudiant.getMatricule());
        txt_Nom_mod.setText(etudiant.getNom());
        txt_prenom_mod.setText(etudiant.getPrenom());
        txt_birth_date_mod.setText(etudiant.getDateBirth().format(formatter));
        txt_renseignementPe_mod.setText(etudiant.getRenseignementPe());
        txt_adress_mod.setText(etudiant.getAdress());
        txt_tel_mod.setText(etudiant.getTel());
        txt_gmail_mod.setText(etudiant.getEmail());
        if (etudiant.getImage() != null) {
            byte[] imag = etudiant.getImage();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
            jLabel16.setIcon(imageIcon);
        } else {
            jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png")));
        }
        com_catego_modi_etu.setSelectedItem(etudiant.getCtegore_niveau().getCategore_niveau_ar());
        com_niv_mod_etud.setSelectedItem(etudiant.getNiveau().getNiveauInitialAr());

    }

    public void setInfoCategoreNiveauInCombox() {
        List<CategoreNiveau> categoreNiveaus = categoreNiveauDAOImpl.findAll();
        if (categoreNiveaus != null) {
            for (CategoreNiveau categoreNiveau_obj : categoreNiveaus) {
                com_catego_modi_etu.addItem(categoreNiveau_obj.getCategore_niveau_ar());
            }
            setInfoNiveauEtudeInCom();
        }
    }

    public void setInfoNiveauEtudeInCom() {
        if (com_catego_modi_etu.getSelectedIndex() != -1) {
            CategoreNiveau category = categoreNiveauDAOImpl.findByName(com_catego_modi_etu.getSelectedItem().toString(), "categore_niveau_ar");
            List<NiveauEtude> niveauEtudes = niveauEtudeDAOImpl.findAll();
            com_niv_mod_etud.removeAllItems();
            for (NiveauEtude obj : niveauEtudes) {
                if (obj.getCategore_niveau_id() == category.getId()) {
                    com_niv_mod_etud.addItem(obj.getNiveauInitialAr());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new datechooser.DateChooser();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_prenom_mod = new javax.swing.JTextField();
        txt_Nom_mod = new javax.swing.JTextField();
        txt_renseignementPe_mod = new javax.swing.JTextField();
        txt_birth_date_mod = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_adress_mod = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_tel_mod = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_gmail_mod = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        com_niv_mod_etud = new material.design.Combobox();
        com_catego_modi_etu = new material.design.Combobox();
        buttonRounder29 = new material.design.buttonRounder();
        buttonRounder30 = new material.design.buttonRounder();
        buttonRounder31 = new material.design.buttonRounder();
        buttonRounder1 = new material.design.buttonRounder();
        jLabel2 = new javax.swing.JLabel();
        lab_mtr = new javax.swing.JLabel();
        lab_codbar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        dateChooser1.setForeground(new java.awt.Color(204, 0, 51));
        dateChooser1.setTextRefernce(txt_birth_date_mod);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("الاسم :");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 70, 20));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("اللقب :");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 50, 20));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("تاريخ الميلاد :");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 70, -1));

        txt_prenom_mod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_prenom_mod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_prenom_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_prenom_mod.setPreferredSize(new java.awt.Dimension(100, 30));
        txt_prenom_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_prenom_modKeyPressed(evt);
            }
        });
        jPanel6.add(txt_prenom_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 150, -1));

        txt_Nom_mod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_Nom_mod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Nom_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_Nom_mod.setPreferredSize(new java.awt.Dimension(100, 30));
        txt_Nom_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Nom_modKeyPressed(evt);
            }
        });
        jPanel6.add(txt_Nom_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 150, -1));

        txt_renseignementPe_mod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_renseignementPe_mod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_renseignementPe_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_renseignementPe_mod.setPreferredSize(new java.awt.Dimension(100, 30));
        txt_renseignementPe_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_renseignementPe_modActionPerformed(evt);
            }
        });
        txt_renseignementPe_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_renseignementPe_modKeyPressed(evt);
            }
        });
        jPanel6.add(txt_renseignementPe_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 150, 30));

        txt_birth_date_mod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_birth_date_mod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_birth_date_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_birth_date_mod.setPreferredSize(new java.awt.Dimension(100, 30));
        txt_birth_date_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_birth_date_modActionPerformed(evt);
            }
        });
        txt_birth_date_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_birth_date_modKeyPressed(evt);
            }
        });
        jPanel6.add(txt_birth_date_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 150, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("اسم الأب :");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 70, 20));

        txt_adress_mod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_adress_mod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_adress_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_adress_mod.setPreferredSize(new java.awt.Dimension(100, 30));
        txt_adress_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_adress_modActionPerformed(evt);
            }
        });
        txt_adress_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_adress_modKeyPressed(evt);
            }
        });
        jPanel6.add(txt_adress_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 150, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("العنوان :");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 90, 20));

        txt_tel_mod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_tel_mod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tel_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_tel_mod.setPreferredSize(new java.awt.Dimension(100, 30));
        txt_tel_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tel_modActionPerformed(evt);
            }
        });
        txt_tel_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tel_modKeyPressed(evt);
            }
        });
        jPanel6.add(txt_tel_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 200, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Gmail");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 40, 10));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Tel");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 20, 30));

        txt_gmail_mod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_gmail_mod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_gmail_mod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_gmail_mod.setPreferredSize(new java.awt.Dimension(100, 30));
        txt_gmail_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_gmail_modActionPerformed(evt);
            }
        });
        txt_gmail_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_gmail_modKeyPressed(evt);
            }
        });
        jPanel6.add(txt_gmail_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 200, -1));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel16.setOpaque(true);
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 120, 110));

        com_niv_mod_etud.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        com_niv_mod_etud.setLabeText("القسم");
        com_niv_mod_etud.setPreferredSize(new java.awt.Dimension(64, 46));
        jPanel6.add(com_niv_mod_etud, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 170, 50));

        com_catego_modi_etu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        com_catego_modi_etu.setLabeText("المستوى الدراسي");
        com_catego_modi_etu.setPreferredSize(new java.awt.Dimension(64, 46));
        com_catego_modi_etu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                com_catego_modi_etuItemStateChanged(evt);
            }
        });
        com_catego_modi_etu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_catego_modi_etuActionPerformed(evt);
            }
        });
        jPanel6.add(com_catego_modi_etu, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 190, 50));

        buttonRounder29.setBackground(new java.awt.Color(224, 175, 175));
        buttonRounder29.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder29.setText("تحميل الصورة");
        buttonRounder29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonRounder29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder29ActionPerformed(evt);
            }
        });
        jPanel6.add(buttonRounder29, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 100, -1));

        buttonRounder30.setBackground(new java.awt.Color(102, 102, 255));
        buttonRounder30.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder30.setText("حفظ");
        buttonRounder30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder30ActionPerformed(evt);
            }
        });
        jPanel6.add(buttonRounder30, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 100, 30));

        buttonRounder31.setBackground(new java.awt.Color(153, 153, 153));
        buttonRounder31.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder31.setText("إلغاء");
        buttonRounder31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder31ActionPerformed(evt);
            }
        });
        jPanel6.add(buttonRounder31, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 100, 30));

        buttonRounder1.setBackground(new java.awt.Color(255, 0, 51));
        buttonRounder1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder1.setText("X");
        buttonRounder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder1ActionPerformed(evt);
            }
        });
        jPanel6.add(buttonRounder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 20, 20));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("تعديل معلومات الطالب");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 330, 40));

        lab_mtr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_mtr.setText("00");
        lab_mtr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(lab_mtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 320, 20));

        lab_codbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_codbar.setText("00");
        lab_codbar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(lab_codbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 270, 20));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("CodBar");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("N°");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 20, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_prenom_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_prenom_modKeyPressed

    }//GEN-LAST:event_txt_prenom_modKeyPressed

    private void txt_Nom_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Nom_modKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Nom_modKeyPressed

    private void txt_renseignementPe_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_renseignementPe_modActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_renseignementPe_modActionPerformed

    private void txt_renseignementPe_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_renseignementPe_modKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_renseignementPe_modKeyPressed

    private void txt_birth_date_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_birth_date_modActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_birth_date_modActionPerformed

    private void txt_birth_date_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_birth_date_modKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_birth_date_modKeyPressed

    private void txt_adress_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_adress_modActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adress_modActionPerformed

    private void txt_adress_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_adress_modKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adress_modKeyPressed

    private void txt_tel_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tel_modActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tel_modActionPerformed

    private void txt_tel_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tel_modKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tel_modKeyPressed

    private void txt_gmail_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_gmail_modActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gmail_modActionPerformed

    private void txt_gmail_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gmail_modKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gmail_modKeyPressed

    private void com_catego_modi_etuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_catego_modi_etuItemStateChanged
    }//GEN-LAST:event_com_catego_modi_etuItemStateChanged

    private void com_catego_modi_etuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_catego_modi_etuActionPerformed
        setInfoNiveauEtudeInCom();
    }//GEN-LAST:event_com_catego_modi_etuActionPerformed
    File imagefile = null;
    private void buttonRounder29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder29ActionPerformed
        PlatformImpl.startup(() -> {
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(null);
            //String path =file.getPath() ;
            if (file != null) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(file.getPath()));
                    imagefile = new File(file.getPath());
                    //  JOptionPane.showMessageDialog(null, image_file_mod.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Image dimg = img.getScaledInstance(jLabel16.getWidth() - 2, jLabel16.getHeight() - 2,
                        Image.SCALE_SMOOTH);
                ImageIcon imageIcon;
                imageIcon = new ImageIcon(dimg);

                //  jLabel2.setIcon(icon);
                jLabel16.setIcon(imageIcon);

                //lab_image.setText(name_food.getText());
                // lab_image.setHorizontalTextPosition(JLabel.CENTER);
            } else {

            }
        });
    }//GEN-LAST:event_buttonRounder29ActionPerformed

    private void buttonRounder30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder30ActionPerformed
        //  int id = Integer.parseInt(lab_id.getText());
        // String matricule = "";
        //String codeBare = "";
        String prenom = txt_Nom_mod.getText();
        String nom = txt_prenom_mod.getText();
        LocalDate date_birth = LocalDate.parse(txt_birth_date_mod.getText(), formatter);//txt_dat_birth
        String adress = txt_adress_mod.getText();
        String tel = txt_tel_mod.getText();
        String email = txt_gmail_mod.getText();
        String renseignementPe = txt_renseignementPe_mod.getText();
        CategoreNiveau categoreNiveau = categoreNiveauDAOImpl.getCategory_by_name(com_catego_modi_etu.getSelectedItem().toString());
        NiveauEtude niveauEtude = niveauEtudeDAOImpl.getNiveauOfCategory(com_niv_mod_etud.getSelectedItem().toString(), com_catego_modi_etu.getSelectedItem().toString());

        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setDateBirth(date_birth);
        etudiant.setAdress(adress);
        etudiant.setTel(tel);
        etudiant.setEmail(email);
        etudiant.setNiveau(niveauEtude);
        etudiant.setCtegore_niveau(categoreNiveau);
        etudiant.setRenseignementPe(renseignementPe);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/diplome (4).png"));
        byte[] image;
        if (jLabel16.getIcon() != imageIcon) {
            if (imagefile != null) {
                image = getimageTOByte();
                etudiant.setImage(image);
            }
        }

        if (etudiantDAOImpl.update(etudiant) > 0) {
            home.setInfoEtudiantInTab();
            this.dispose();
            message_validation.showMessage(" تأكيد العملية", "تم  تعديل معلومات الطالب بنجاج  .");

        }
    }//GEN-LAST:event_buttonRounder30ActionPerformed

    private void buttonRounder31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder31ActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonRounder31ActionPerformed

    private void buttonRounder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder1ActionPerformed
        if (etudiant.getImage() != null) {
            byte[] imag = etudiant.getImage();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
            jLabel16.setIcon(imageIcon);
        } else {
            jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png")));
        }
    }//GEN-LAST:event_buttonRounder1ActionPerformed

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
            java.util.logging.Logger.getLogger(ModifyEtudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyEtudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyEtudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyEtudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ModifyEtudiant dialog = new ModifyEtudiant(new javax.swing.JFrame(), true);
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
    private material.design.buttonRounder buttonRounder1;
    private material.design.buttonRounder buttonRounder29;
    private material.design.buttonRounder buttonRounder30;
    private material.design.buttonRounder buttonRounder31;
    private material.design.Combobox com_catego_modi_etu;
    private material.design.Combobox com_niv_mod_etud;
    private datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lab_codbar;
    private javax.swing.JLabel lab_mtr;
    private javax.swing.JTextField txt_Nom_mod;
    private javax.swing.JTextField txt_adress_mod;
    private javax.swing.JTextField txt_birth_date_mod;
    private javax.swing.JTextField txt_gmail_mod;
    private javax.swing.JTextField txt_prenom_mod;
    private javax.swing.JTextField txt_renseignementPe_mod;
    private javax.swing.JTextField txt_tel_mod;
    // End of variables declaration//GEN-END:variables

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    public byte[] getimageTOByte() {
        byte[] imag = null;
        try {

            if (imagefile != null) {
                FileInputStream imageInputStream;

                imageInputStream = new FileInputStream(imagefile);

                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readnum; (readnum = imageInputStream.read(buf)) != -1;) {
                    arrayOutputStream.write(buf, 0, readnum);
                }
                imag = arrayOutputStream.toByteArray();

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModifyEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModifyEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imag;
    }
}
