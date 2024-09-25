/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package guis;

import com.sun.javafx.application.PlatformImpl;
import domaine.CategoreNiveau;

import domaine.Enseignant;
import domaine.Etudiant;
import domaine.Groupe;
import domaine.GroupeMatiere;
import domaine.Inscription;
import domaine.Matiere;
import domaine.MatiereEnseignant;
import domaine.NiveauEtude;
import domaine.Payement;
import domaine.Seance_Matiere;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import main.java.com.school.impl.CategoreNiveauDAOImpl;
import main.java.com.school.impl.EnseignantDAOImpl;
import main.java.com.school.impl.EtudiantDAOImpl;
import main.java.com.school.impl.GroupeImpl;
import main.java.com.school.impl.GroupeMatiereDAOImpl;
import main.java.com.school.impl.InscriptionDAOImpl;
import main.java.com.school.impl.MatiereDAOImpl;
import main.java.com.school.impl.MatiereEnseignantDAOImpl;
import main.java.com.school.impl.NiveauEtudeDAOImpl;
import main.java.com.school.impl.PayementDAOImpl;
import main.java.com.school.impl.SceanceDAOImpl;
import main.java.com.school.impl.SeanceMatiereDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;
import material.design.Combobox;
import material.design.PanelAction;
import material.design.ScrollBar;
import material.design.TableActionCellEditor;
import material.design.TableActionCellRender;
import material.design.TableActionEvent;
import material.design.TextField;
import ui.table.TableCustom;

/**
 *
 * @author client
 */
public class AddEtudiantForm extends javax.swing.JDialog {

    home home;
    Connection connection;
    EtudiantDAOImpl etudiantDAOImpl;
    CategoreNiveauDAOImpl categoreNiveauDAOImpl;
    GroupeMatiereDAOImpl GroupeMatiere_imp_dao;
    MatiereEnseignantDAOImpl MatiereEnseignat_dao_impl;
    NiveauEtudeDAOImpl niveauEtudeDAOImpl;
    MatiereDAOImpl matiereDAOImpl;
    EnseignantDAOImpl enseignantDAOImpl;
    InscriptionDAOImpl inscriptionDAOImpl;
    GroupeImpl group_dao_imp;
    SeanceMatiereDAOImpl seanceMatiereDAOImpl;
    PayementDAOImpl payementDAOImpl;

    public AddEtudiantForm(java.awt.Frame parent, boolean model) {
        super(parent, model);
        this.home = (home) parent;
        initComponents();
        setLocationRelativeTo(this);
        try {
            connection = new ConnectionDB().getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        etudiantDAOImpl = new EtudiantDAOImpl(connection);
        categoreNiveauDAOImpl = new CategoreNiveauDAOImpl(connection);
        niveauEtudeDAOImpl = new NiveauEtudeDAOImpl(connection);
        matiereDAOImpl = new MatiereDAOImpl(connection);
        enseignantDAOImpl = new EnseignantDAOImpl(connection);
        seanceMatiereDAOImpl = new SeanceMatiereDAOImpl(connection);
        payementDAOImpl = new PayementDAOImpl(connection);
        inscriptionDAOImpl= new InscriptionDAOImpl(connection);
        MatiereEnseignat_dao_impl=new MatiereEnseignantDAOImpl(connection);
        GroupeMatiere_imp_dao=new GroupeMatiereDAOImpl(connection);
        group_dao_imp=new GroupeImpl(connection);
        
        setDesignTable(jTable1, jScrollPane1);
        int id_matricule=1;
        int code_bar=1;
        if (etudiantDAOImpl.getlast()!=null){
            
            id_matricule=Integer.parseInt(etudiantDAOImpl.getlast().getMatricule()) + 1;
        }
        
        String matricule = "00" + (id_matricule);

        //String codeBar = "00" + (Integer.parseInt(etudiantDAOImpl.getlast().getCodeBare()) + 1);
        String codeBar = "00" + (id_matricule);
        lab_codBar.setText(codeBar);
        lab_matricul.setText(matricule);
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                // System.out.println("Edit row : " + row);
//                int nb_items = Integer.parseInt(table.getValueAt(row, 1).toString()) + 1;
//                double prix = nb_items * (double) table.getValueAt(row, 2);
//                table.setValueAt(nb_items, row, 1);
//                table.setValueAt(prix, row, 3);
//                clculePrixTotal();
            }

            @Override
            public void onDelete(int row) {

//                System.out.println("delete ...... ");
//                if (table.isEditing()) {
//                    table.getCellEditor().stopCellEditing();
//                }
//                DefaultTableModel model = (DefaultTableModel) table.getModel();
//                model.removeRow(row);
//                clculePrixTotal();
            }

            @Override
            public void onMoins(int row) {
//                int nb_items = Integer.parseInt(table.getValueAt(row, 1).toString());
//                if (nb_items > 1) {
//                    nb_items = nb_items - 1;
//                    table.setValueAt(nb_items, row, 1);
//                    double prix = nb_items * (double) table.getValueAt(row, 2);
//                    table.setValueAt(nb_items, row, 1);
//                    table.setValueAt(prix, row, 3);
//                } else {
//                    if (table.isEditing()) {
//                        table.getCellEditor().stopCellEditing();
//                    }
//                    DefaultTableModel model = (DefaultTableModel) table.getModel();
//                    model.removeRow(row);
//
//                }
//                clculePrixTotal();
            }
        };
        jTable1.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender(new PanelAction()));
        jTable1.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event, jTable1, 1));

        PrepareUI();
    }

    public void PrepareUI() {
        setInfoCategoreNiveauInCombox(comb_catego_niv);
        setInfoNiveauEtudeInCom(comb_catego_niv, com_niv);
        
    }

    
    
    public void setInfoCategoreNiveauInCombox(JComboBox com_catego_niv) {
        List<CategoreNiveau> categoreNiveaus = categoreNiveauDAOImpl.findAll();
        for (CategoreNiveau categoreNiveau_obj : categoreNiveaus) {
            com_catego_niv.addItem(categoreNiveau_obj.getCategore_niveau_ar());
        }

    }

    public void setInfoNiveauEtudeInCom(JComboBox com_category_niv, JComboBox com_niv) {
        CategoreNiveau category = categoreNiveauDAOImpl.findByName(com_category_niv.getSelectedItem().toString(), "categore_niveau_ar");
        List<NiveauEtude> niveauEtudes = niveauEtudeDAOImpl.findAll();

        com_niv.removeAllItems();
        for (NiveauEtude obj : niveauEtudes) {
            if (obj.getCategore_niveau_id() == category.getId()) {
                com_niv.addItem(obj.getNiveauInitialAr());
            }
        }
        ///setInfoMatiereInCom
    }

    public void setInfoMatiereInCom(JComboBox com_catego_niv, JComboBox com_niv, JComboBox com_matier) {
        
        if (com_catego_niv.getSelectedIndex()!=-1 && com_niv.getSelectedIndex()!= -1){
        
        com_matier.removeAllItems();
        CategoreNiveau category = categoreNiveauDAOImpl.findByName(com_catego_niv.getSelectedItem().toString(), "categore_niveau_ar");
        NiveauEtude niveauEtude = new NiveauEtude();// = niveauEtudeDAOImpl.findByName(comb_niveau.getSelectedItem().toString(), "niveau_initial_ar");
        List<NiveauEtude> niveauEtudes = niveauEtudeDAOImpl.findAll();
        if (com_niv.getSelectedIndex() != -1) {
            for (NiveauEtude obj : niveauEtudes) {
                if (obj.getCategore_niveau_id() == category.getId()
                        && obj.getNiveauInitialAr().equals(com_niv.getSelectedItem().toString())) {
                    niveauEtude = obj;
                }
            }
            List<Matiere> matieres = matiereDAOImpl.findAll();
            for (Matiere matiere_obj : matieres) {
                if (matiere_obj.getNiveau().getId() == niveauEtude.getId()
                        && matiere_obj.getCategoreNiveau().getId() == category.getId()) {
                    // || matiere_obj.getCategoreNiveau().getId() == idCategoreNiveau) {           
                    com_matier.addItem(matiere_obj.getMatiereEtdAr());

                }
            }

            //  setInfoEnsignInCom();
        }
        }
    }

    public void setInfoEnsignInCom(JComboBox com_catego_niv, JComboBox com_niv, JComboBox com_matier) {

        if (com_matier.getSelectedIndex() != -1 && com_niv.getSelectedIndex() != -1 && com_catego_niv.getSelectedIndex() != -1) {
            Matiere matiere = new MatiereDAOImpl(connection).getMatiereNiveauOfCategory(com_matier.getSelectedItem().toString(),
                    com_niv.getSelectedItem().toString(), com_catego_niv.getSelectedItem().toString());
            //Enseignant enseignant = enseignantDAOImpl.findById(matiere.getEnseignant().getId());
//            if (enseignant != null) {
//                jLabel18.setText(enseignant.getNomAr() + " " + enseignant.getPrenomAr());
//            } else {
//                jLabel18.setText("/");
//            }
        } else if (com_matier.getSelectedIndex() == -1) {
            jLabel18.setText("");
        }

    }

    public void setInfoEnsignInCom() {

        if (matiere_comb.getSelectedIndex() != -1 && com_niv.getSelectedIndex() != -1 && Enseignantcombo.getSelectedIndex() != -1) {
            
            
            List <MatiereEnseignant> listMatiereByEnseignat=MatiereEnseignat_dao_impl.findAll();
            
            for (MatiereEnseignant matiereEnseignant : listMatiereByEnseignat) {
                if (matiereEnseignant.getMatiere().getMatiereEtdAr().equals(matiere_comb.getSelectedItem().toString())){
                    Enseignantcombo.addItem(matiereEnseignant.getEnseignant().getNomAr());
                }
            }
            
        }  
            
          

    }
    
    public void setInfoEnseignantByMatiere(){
       
        try {
            Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(matiere_comb.getSelectedItem().toString(),
                    ClassInNiveau.getSelectedItem().toString(), NiveauCatg_Etude.getSelectedItem().toString());
            
            
            
            System.out.println(matiere);
            
            List<Enseignant> list_enseignat= MatiereEnseignat_dao_impl.findEnseignantByMatiereId(matiere);
            
            System.out.println("list enseignat:"+list_enseignat);
            
            Enseignantcombo.removeAllItems();
            
            System.out.println("");
            for (Enseignant enseignat : list_enseignat) {
                Enseignantcombo.addItem(enseignat.getNomAr()+"-"+enseignat.getPrenomAr());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AddEtudiantForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    // Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(matiere_comb.getSelectedItem().toString(),
    //                ClassInNiveau.getSelectedItem().toString(), NiveauCatg_Etude.getSelectedItem().toString());
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser = new datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonRounder1 = new material.design.buttonRounder();
        buttonRounder17 = new material.design.buttonRounder();
        buttonRounder19 = new material.design.buttonRounder();
        jPanel5 = new javax.swing.JPanel();
        pan_info_etudiant_insc = new javax.swing.JPanel();
        Code = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lab_matricul = new javax.swing.JLabel();
        lab_codBar = new javax.swing.JLabel();
        panel_info_student = new javax.swing.JPanel();
        txt_name = new material.design.TextField();
        txt_prenom = new material.design.TextField();
        txt_prenom_fr = new material.design.TextField();
        txt_nom_fr = new material.design.TextField();
        txt_dat_birth = new material.design.TextField();
        txt_renseignementPe = new material.design.TextField();
        txt_adress = new material.design.TextField();
        comb_catego_niv = new material.design.Combobox();
        com_niv = new material.design.Combobox();
        jLabel6 = new javax.swing.JLabel();
        buttonRounder2 = new material.design.buttonRounder();
        jLabel7 = new javax.swing.JLabel();
        txt_tel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        buttonRounder3 = new material.design.buttonRounder();
        pan_etud_cour_insc = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        CatCours = new material.design.Combobox();
        pan_catego = new javax.swing.JPanel();
        pan_cour = new javax.swing.JPanel();
        combobox3 = new material.design.Combobox();
        NiveauCatg_Etude = new material.design.Combobox();
        matiere_comb = new material.design.Combobox();
        jLabel9 = new javax.swing.JLabel();
        buttonRounder9 = new material.design.buttonRounder();
        ClassInNiveau = new material.design.Combobox();
        jLabel4 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        NomEnseignant = new javax.swing.JLabel();
        PrenomEnseignant = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jCheckBoxCustom7 = new material.design.JCheckBoxCustomfr();
        jCheckBoxCustom8 = new material.design.JCheckBoxCustomfr();
        jCheckBoxCustom6 = new material.design.JCheckBoxCustomfr();
        ComboGroup = new material.design.Combobox();
        Enseignantcombo = new material.design.Combobox();
        CheckAllGroups = new javax.swing.JCheckBox();
        pan_lang = new javax.swing.JPanel();
        combobox2 = new material.design.Combobox();
        Groupe4 = new material.design.Combobox();
        Groupe5 = new material.design.Combobox();
        buttonRounder6 = new material.design.buttonRounder();
        jTextField7 = new javax.swing.JTextField();
        pan_day2 = new javax.swing.JPanel();
        buttonRounder7 = new material.design.buttonRounder();
        pan_formation = new javax.swing.JPanel();
        الدورات = new material.design.Combobox();
        Groupe2 = new material.design.Combobox();
        Groupe3 = new material.design.Combobox();
        jTextField6 = new javax.swing.JTextField();
        buttonRounder4 = new material.design.buttonRounder();
        pan_day1 = new javax.swing.JPanel();
        buttonRounder5 = new material.design.buttonRounder();
        btn_retour = new material.design.buttonRounder();
        tableScrollButton1 = new ui.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        dateChooser.setName(""); // NOI18N
        dateChooser.setTextRefernce(txt_dat_birth);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(101, 147, 252));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" طالب جديد");

        buttonRounder1.setBackground(new java.awt.Color(117, 158, 255));
        buttonRounder1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder1.setText("X");
        buttonRounder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRounder1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(buttonRounder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        buttonRounder17.setBackground(new java.awt.Color(255, 51, 51));
        buttonRounder17.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder17.setText("إلغاء");
        buttonRounder17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder17ActionPerformed(evt);
            }
        });

        buttonRounder19.setBackground(new java.awt.Color(51, 153, 0));
        buttonRounder19.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder19.setText("حفظ");
        buttonRounder19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder19ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.CardLayout());

        pan_info_etudiant_insc.setBackground(new java.awt.Color(255, 255, 255));

        Code.setBackground(new java.awt.Color(255, 255, 255));
        Code.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        Code.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("CodeBare");
        Code.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 31));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("N° ");
        Code.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 20, 30));

        lab_matricul.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Code.add(lab_matricul, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 190, 30));

        lab_codBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Code.add(lab_codBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 190, 30));

        panel_info_student.setBackground(new java.awt.Color(255, 255, 255));
        panel_info_student.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "المعلومات الشخصية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(150, 150, 150))); // NOI18N

        txt_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_name.setToolTipText("");
        txt_name.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txt_name.setLabelText("الاســم");

        txt_prenom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_prenom.setToolTipText("");
        txt_prenom.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txt_prenom.setLabelText("الــلـقـب");

        txt_prenom_fr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_prenom_fr.setToolTipText("");
        txt_prenom_fr.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txt_prenom_fr.setLabelText("Prénom");
        txt_prenom_fr.setLangue(1);
        txt_prenom_fr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_prenom_frActionPerformed(evt);
            }
        });

        txt_nom_fr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nom_fr.setToolTipText("");
        txt_nom_fr.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txt_nom_fr.setLabelText("Nom");
        txt_nom_fr.setLangue(1);

        txt_dat_birth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dat_birth.setToolTipText("");
        txt_dat_birth.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txt_dat_birth.setLabelText("تاريخ الميلاد");
        txt_dat_birth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dat_birthActionPerformed(evt);
            }
        });

        txt_renseignementPe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_renseignementPe.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txt_renseignementPe.setLabelText("اسم الأب");

        txt_adress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_adress.setToolTipText("");
        txt_adress.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txt_adress.setLabelText("الـعنوان");
        txt_adress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_adressActionPerformed(evt);
            }
        });

        comb_catego_niv.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        comb_catego_niv.setLabeText("المستوى الدراسي");
        comb_catego_niv.setPreferredSize(new java.awt.Dimension(64, 46));
        comb_catego_niv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comb_catego_nivItemStateChanged(evt);
            }
        });
        comb_catego_niv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_catego_nivActionPerformed(evt);
            }
        });

        com_niv.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        com_niv.setLabeText("القـسـم");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png"))); // NOI18N
        jLabel6.setOpaque(true);

        buttonRounder2.setBackground(new java.awt.Color(224, 175, 175));
        buttonRounder2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder2.setText("تحميل الصورة");
        buttonRounder2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonRounder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(150, 150, 150));
        jLabel7.setText("الـهاتف :");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(150, 150, 150));
        jLabel8.setText("البريد الإلكتروني ");

        javax.swing.GroupLayout panel_info_studentLayout = new javax.swing.GroupLayout(panel_info_student);
        panel_info_student.setLayout(panel_info_studentLayout);
        panel_info_studentLayout.setHorizontalGroup(
            panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_info_studentLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRounder2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_info_studentLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txt_adress, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dat_birth, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_info_studentLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_info_studentLayout.createSequentialGroup()
                                .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_info_studentLayout.createSequentialGroup()
                                .addComponent(txt_nom_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_prenom_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_info_studentLayout.createSequentialGroup()
                            .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel_info_studentLayout.createSequentialGroup()
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8))
                        .addGroup(panel_info_studentLayout.createSequentialGroup()
                            .addComponent(com_niv, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comb_catego_niv, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_renseignementPe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        panel_info_studentLayout.setVerticalGroup(
            panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_info_studentLayout.createSequentialGroup()
                .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_info_studentLayout.createSequentialGroup()
                        .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nom_fr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_prenom_fr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_adress, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(panel_info_studentLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(txt_dat_birth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(txt_renseignementPe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(com_niv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comb_catego_niv, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addGroup(panel_info_studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(panel_info_studentLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonRounder2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        buttonRounder3.setBackground(new java.awt.Color(0, 204, 204));
        buttonRounder3.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder3.setText("التسجيل في الدروس  >> ");
        buttonRounder3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonRounder3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_info_etudiant_inscLayout = new javax.swing.GroupLayout(pan_info_etudiant_insc);
        pan_info_etudiant_insc.setLayout(pan_info_etudiant_inscLayout);
        pan_info_etudiant_inscLayout.setHorizontalGroup(
            pan_info_etudiant_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_info_etudiant_inscLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRounder3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
            .addGroup(pan_info_etudiant_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_info_etudiant_inscLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pan_info_etudiant_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel_info_student, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Code, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        pan_info_etudiant_inscLayout.setVerticalGroup(
            pan_info_etudiant_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_info_etudiant_inscLayout.createSequentialGroup()
                .addContainerGap(504, Short.MAX_VALUE)
                .addComponent(buttonRounder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(pan_info_etudiant_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_info_etudiant_inscLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Code, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel_info_student, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE)))
        );

        jPanel5.add(pan_info_etudiant_insc, "card2");

        pan_etud_cour_insc.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "معلومات التخصص", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 13), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(0, 51, 204));

        CatCours.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "دروس خصوصية", "لغات", "دورات تكوينية", "" }));
        CatCours.setActionCommand("");
        CatCours.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        CatCours.setLabeText("Category");
        CatCours.setPreferredSize(new java.awt.Dimension(64, 46));
        CatCours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CatCoursActionPerformed(evt);
            }
        });

        pan_catego.setBackground(new java.awt.Color(255, 255, 255));
        pan_catego.setLayout(new java.awt.CardLayout());

        pan_cour.setBackground(new java.awt.Color(255, 255, 255));
        pan_cour.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combobox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ابتدائي", "متوسط", "ثانوي", "جامعي", "/", " ", " ", " " }));
        combobox3.setSelectedIndex(-1);
        combobox3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        combobox3.setLabeText("المستوى الدراسي ");
        combobox3.setPreferredSize(new java.awt.Dimension(64, 46));
        pan_cour.add(combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 146, -1));

        NiveauCatg_Etude.setLabeText("المستوى الدراسي");
        NiveauCatg_Etude.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NiveauCatg_EtudeItemStateChanged(evt);
            }
        });
        NiveauCatg_Etude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NiveauCatg_EtudeActionPerformed(evt);
            }
        });
        pan_cour.add(NiveauCatg_Etude, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 1, 190, 40));

        matiere_comb.setLabeText("المادة");
        matiere_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matiere_combActionPerformed(evt);
            }
        });
        pan_cour.add(matiere_comb, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 120, -1));
        pan_cour.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 161, 53, 35));

        buttonRounder9.setBackground(new java.awt.Color(151, 151, 255));
        buttonRounder9.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder9.setText("اضافة مادة أخرى");
        pan_cour.add(buttonRounder9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 150, 30));

        ClassInNiveau.setLabeText("القسم");
        ClassInNiveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClassInNiveauActionPerformed(evt);
            }
        });
        pan_cour.add(ClassInNiveau, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 1, 190, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("الفــوج");
        pan_cour.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 40, 30));

        jTextField8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("G 01");
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 150)));
        pan_cour.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 60, 30));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "الوقت و الأستاذ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 204, 204))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(150, 150, 150));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText(" التوقيت :");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(150, 150, 150));
        jLabel11.setText("من");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(150, 150, 150));
        jLabel13.setText("مدة الحصة :");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("00 : 00");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(150, 150, 150));
        jLabel12.setText("الى");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("0 ساعة");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("00 : 00");

        jLabel17.setText("أستاذ المادة");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        NomEnseignant.setText("Nom");

        PrenomEnseignant.setText("Prenm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel17))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(NomEnseignant, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PrenomEnseignant, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NomEnseignant, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(PrenomEnseignant, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
        );

        pan_cour.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 160));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("الدفــع :");
        pan_cour.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 40, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("2000");
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        pan_cour.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 110, 50));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(150, 150, 150));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("المبلغ الشهري");
        pan_cour.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 80, 40));

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        pan_cour.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 580, -1));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("00");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        pan_cour.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 120, 40));

        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pan_cour.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 120, 40));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        pan_cour.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 120, 40));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/payment (6).png"))); // NOI18N
        pan_cour.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, 30, 40));

        jCheckBoxCustom7.setBackground(new java.awt.Color(4, 181, 4));
        jCheckBoxCustom7.setText("تسديد المبلغ كاملا");
        jCheckBoxCustom7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCustom7ActionPerformed(evt);
            }
        });
        pan_cour.add(jCheckBoxCustom7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, 30));

        jCheckBoxCustom8.setText("المبلغ الذي تم تسديده أوليا");
        jCheckBoxCustom8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCustom8ActionPerformed(evt);
            }
        });
        pan_cour.add(jCheckBoxCustom8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 160, 30));

        jCheckBoxCustom6.setBackground(new java.awt.Color(255, 0, 51));
        jCheckBoxCustom6.setText("لم يتم دفع المبلغ");
        jCheckBoxCustom6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCustom6ActionPerformed(evt);
            }
        });
        pan_cour.add(jCheckBoxCustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, -1, 30));

        ComboGroup.setLabeText("Group N°");
        ComboGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboGroupActionPerformed(evt);
            }
        });
        pan_cour.add(ComboGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 70, -1));

        Enseignantcombo.setLabeText("استاذ المادة");
        Enseignantcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnseignantcomboActionPerformed(evt);
            }
        });
        pan_cour.add(Enseignantcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 130, -1));

        CheckAllGroups.setText("الكل");
        CheckAllGroups.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckAllGroups.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        pan_cour.add(CheckAllGroups, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        pan_catego.add(pan_cour, "card2");

        pan_lang.setBackground(new java.awt.Color(255, 255, 255));
        pan_lang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combobox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Français", "Anglais" }));
        combobox2.setSelectedIndex(-1);
        combobox2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        combobox2.setLabeText("Langue");
        combobox2.setPreferredSize(new java.awt.Dimension(64, 46));
        combobox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox2ActionPerformed(evt);
            }
        });
        pan_lang.add(combobox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 17, 198, -1));

        Groupe4.setLabeText("Groupe");
        pan_lang.add(Groupe4, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 130, 198, -1));

        Groupe5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "السبت", "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة" }));
        Groupe5.setSelectedIndex(-1);
        Groupe5.setLabeText("الايام");
        pan_lang.add(Groupe5, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 69, 198, 43));

        buttonRounder6.setBackground(new java.awt.Color(51, 204, 0));
        buttonRounder6.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder6.setText("+");
        buttonRounder6.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        buttonRounder6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder6ActionPerformed(evt);
            }
        });
        pan_lang.add(buttonRounder6, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 73, -1, -1));

        jTextField7.setText("12:0");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        pan_lang.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 139, 162, 32));

        pan_day2.setBackground(new java.awt.Color(255, 255, 255));
        pan_lang.add(pan_day2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 69, 424, 33));

        buttonRounder7.setBackground(new java.awt.Color(151, 151, 255));
        buttonRounder7.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder7.setText("اضافة مادة أخرى");
        buttonRounder7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder7ActionPerformed(evt);
            }
        });
        pan_lang.add(buttonRounder7, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 135, 158, 41));

        pan_catego.add(pan_lang, "card3");

        pan_formation.setBackground(new java.awt.Color(255, 255, 255));
        pan_formation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        الدورات.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        الدورات.setLabeText("الدورات التكوينية ");
        الدورات.setPreferredSize(new java.awt.Dimension(64, 46));
        pan_formation.add(الدورات, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 6, 349, -1));

        Groupe2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "السبت", "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة" }));
        Groupe2.setSelectedIndex(-1);
        Groupe2.setLabeText("الايام");
        pan_formation.add(Groupe2, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 58, -1, 34));

        Groupe3.setLabeText("Groupe");
        pan_formation.add(Groupe3, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 100, 105, 32));

        jTextField6.setText("12:0");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        pan_formation.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 105, 152, 32));

        buttonRounder4.setBackground(new java.awt.Color(51, 204, 0));
        buttonRounder4.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder4.setText("+");
        buttonRounder4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        buttonRounder4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder4ActionPerformed(evt);
            }
        });
        pan_formation.add(buttonRounder4, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 65, -1, -1));

        pan_day1.setBackground(new java.awt.Color(255, 255, 255));
        pan_formation.add(pan_day1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 86, 501, 33));

        buttonRounder5.setBackground(new java.awt.Color(151, 151, 255));
        buttonRounder5.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder5.setText("اضافة مادة أخرى");
        pan_formation.add(buttonRounder5, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 185, -1, -1));

        pan_catego.add(pan_formation, "card4");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CatCours, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addComponent(pan_catego, javax.swing.GroupLayout.PREFERRED_SIZE, 605, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CatCours, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_catego, javax.swing.GroupLayout.PREFERRED_SIZE, 319, Short.MAX_VALUE))
        );

        btn_retour.setBackground(new java.awt.Color(0, 204, 204));
        btn_retour.setForeground(new java.awt.Color(255, 255, 255));
        btn_retour.setText("<<    رجـــوع");
        btn_retour.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btn_retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_retourActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setSelectionBackground(new java.awt.Color(235, 235, 235));
        jScrollPane1.setViewportView(jTable1);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pan_etud_cour_inscLayout = new javax.swing.GroupLayout(pan_etud_cour_insc);
        pan_etud_cour_insc.setLayout(pan_etud_cour_inscLayout);
        pan_etud_cour_inscLayout.setHorizontalGroup(
            pan_etud_cour_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_etud_cour_inscLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_etud_cour_inscLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_retour, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pan_etud_cour_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_etud_cour_inscLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pan_etud_cour_inscLayout.setVerticalGroup(
            pan_etud_cour_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_etud_cour_inscLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(btn_retour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pan_etud_cour_inscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_etud_cour_inscLayout.createSequentialGroup()
                    .addContainerGap(407, Short.MAX_VALUE)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );

        jPanel5.add(pan_etud_cour_insc, "card3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(buttonRounder17, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(buttonRounder19, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRounder17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRounder19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRounder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRounder1ActionPerformed

    private void txt_prenom_frActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_prenom_frActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_prenom_frActionPerformed

    private void txt_dat_birthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dat_birthActionPerformed
        dateChooser.showPopup();
    }//GEN-LAST:event_txt_dat_birthActionPerformed

    private void txt_adressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_adressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adressActionPerformed

    private void comb_catego_nivItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comb_catego_nivItemStateChanged
        if (comb_catego_niv.getSelectedIndex() != -1) {
            com_niv.removeAllItems();
            //setInfoNiveauEtudeInCom(com_catego_niveau.getSelectedIndex()+1);
        }
    }//GEN-LAST:event_comb_catego_nivItemStateChanged

    private void comb_catego_nivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_catego_nivActionPerformed
        setInfoNiveauEtudeInCom(comb_catego_niv, com_niv);
    }//GEN-LAST:event_comb_catego_nivActionPerformed

    File imagefile = null;
    private void buttonRounder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder2ActionPerformed
        PlatformImpl.startup(() -> {
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(file.getPath()));
                    imagefile = new File(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Image dimg = img.getScaledInstance(jLabel6.getWidth() - 2, jLabel6.getHeight() - 2,
                        Image.SCALE_SMOOTH);
                ImageIcon imageIcon;
                imageIcon = new ImageIcon(dimg);

                //  jLabel2.setIcon(icon);
                jLabel6.setIcon(imageIcon);

                //lab_image.setText(name_food.getText());
                // lab_image.setHorizontalTextPosition(JLabel.CENTER);
            } else {

            }
        });
    }//GEN-LAST:event_buttonRounder2ActionPerformed

    private void buttonRounder19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder19ActionPerformed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String matricule = lab_matricul.getText();
        String codeBare = lab_codBar.getText();
        String prenom = txt_name.getText();
        String nom = txt_prenom.getText();
        LocalDate date_birth = LocalDate.parse(txt_dat_birth.getText(), formatter);//txt_dat_birth
        String adress = txt_adress.getText();
        String tel = txt_tel.getText();
        String email = txt_email.getText();
        String renseignementPe = txt_renseignementPe.getText();
        CategoreNiveau ctegore_niveau = categoreNiveauDAOImpl.findByName(comb_catego_niv.getSelectedItem().toString(), "categore_niveau_ar");
        List<NiveauEtude> niveauEtudes = niveauEtudeDAOImpl.findAll();
        NiveauEtude niveau = null;
        for (NiveauEtude nivea_etud : niveauEtudes) {
            if (nivea_etud.getCategore_niveau_id() == ctegore_niveau.getId() && 
                    nivea_etud.getNiveauInitialAr().equals(com_niv.getSelectedItem().toString())) {
                niveau = nivea_etud;
            }
        }

        try {
            if (imagefile != null) {
                FileInputStream imageInputStream = new FileInputStream(imagefile);
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readnum; (readnum = imageInputStream.read(buf)) != -1;) {
                    arrayOutputStream.write(buf, 0, readnum);
                }
                byte[] imag = arrayOutputStream.toByteArray();

                Etudiant etudiant = new Etudiant(0, matricule, codeBare, nom ,prenom, date_birth, adress, tel, email, renseignementPe, ctegore_niveau, niveau, imag);
                if (etudiantDAOImpl.save(etudiant)>0){
                    
                    Etudiant newEtudiant = etudiantDAOImpl.getlast();
                imagefile = null;
                // save inscription  //
                JOptionPane.showMessageDialog(null, "  befor iscripton ");
                Matiere matiere = matiereDAOImpl.findByName(matiere_comb.getSelectedItem().toString(), "matiere_etd_ar");
                
                Inscription inscription = new Inscription(0, newEtudiant, matiere, LocalDate.now());
                inscriptionDAOImpl.save(inscription);
                JOptionPane.showMessageDialog(null, "  after iscripton ");
               List<Seance_Matiere> seance_Matieres = seanceMatiereDAOImpl.getSeanceMatiereByIdMatier(matiere.getId());
              
               
                // payement //
                if (jCheckBoxCustom7.isSelected()) {
                    int nbr = seance_Matieres.size();
                    double prix = matiere.getPrix();
                    double prix_unitair_seance = prix / nbr;
                    for(Seance_Matiere seance_Matiere : seance_Matieres ){
                    JOptionPane.showMessageDialog(null, "  seance "+nbr);
                    Payement  payement = new Payement(0, newEtudiant, matiere,  seance_Matiere.getSeance(), "cash", prix, prix_unitair_seance, prix, LocalDate.now() );
                    payementDAOImpl.save(payement);
                  JOptionPane.showMessageDialog(null, "  after iscripton ");
                    }
                } else {                    
                    if(jCheckBoxCustom8.isSelected()){
                    int nbr = seance_Matieres.size();
                    double prix = matiere.getPrix();
                    double prix_unitair_seance = 0;
                    for(Seance_Matiere seance_Matiere : seance_Matieres ){         
                    Payement  payement = new Payement(0, newEtudiant, matiere, seance_Matiere.getSeance(), "NoPayee", prix, prix_unitair_seance, prix, LocalDate.now() );
                    payementDAOImpl.save(payement);
                    }
                   }

                }
                
                }
                
            } else {
                Etudiant etudiant = new Etudiant(0, matricule, codeBare, prenom, nom, date_birth, adress, tel, email, renseignementPe, ctegore_niveau, niveau, null);
                if (etudiantDAOImpl.save(etudiant)>0){
                    JOptionPane.showMessageDialog(null, "Success Save New Student");
                    InitialiseFields();
                    home.setInfoEtudiantInTab();
                    this.dispose();
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddEtudiantForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddEtudiantForm.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_buttonRounder19ActionPerformed

    private void buttonRounder3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder3ActionPerformed
        setForm(jPanel5, pan_etud_cour_insc);
        setInfoCategoreNiveauInCombox(NiveauCatg_Etude);
        setInfoNiveauEtudeInCom(NiveauCatg_Etude, ClassInNiveau);
        setInfoMatiereInCom(NiveauCatg_Etude, ClassInNiveau, matiere_comb);

    }//GEN-LAST:event_buttonRounder3ActionPerformed

    private void btn_retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retourActionPerformed
        setForm(jPanel5, pan_info_etudiant_insc);
    }//GEN-LAST:event_btn_retourActionPerformed

    private void buttonRounder4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRounder4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void buttonRounder7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder7ActionPerformed

    }//GEN-LAST:event_buttonRounder7ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void buttonRounder6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRounder6ActionPerformed

    private void combobox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combobox2ActionPerformed

    private void CatCoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CatCoursActionPerformed
        if (CatCours.getSelectedItem().toString().equals("دروس خصوصية")) {
            setForm(pan_catego, pan_cour);
        } else {
            if (CatCours.getSelectedItem().toString().equals("لغات")) {
                setForm(pan_catego, pan_lang);
            } else {
                setForm(pan_catego, pan_formation);
            }
        }
    }//GEN-LAST:event_CatCoursActionPerformed

    private void NiveauCatg_EtudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NiveauCatg_EtudeActionPerformed
            setInfoNiveauEtudeInCom(NiveauCatg_Etude, ClassInNiveau);
    }//GEN-LAST:event_NiveauCatg_EtudeActionPerformed

    private void buttonRounder17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder17ActionPerformed
        this.InitialiseFields();
        
    }//GEN-LAST:event_buttonRounder17ActionPerformed

    private void ClassInNiveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClassInNiveauActionPerformed
        setInfoMatiereInCom(NiveauCatg_Etude, ClassInNiveau, matiere_comb);
    }//GEN-LAST:event_ClassInNiveauActionPerformed

    private void matiere_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matiere_combActionPerformed
        if (matiere_comb.getSelectedIndex() != -1 && ClassInNiveau.getSelectedIndex() != -1
                && NiveauCatg_Etude.getSelectedIndex() != -1) {
            
            Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(matiere_comb.getSelectedItem().toString(),
                    ClassInNiveau.getSelectedItem().toString(), NiveauCatg_Etude.getSelectedItem().toString());

            jLabel20.setText(matiere.getPrix() + "");
            
            setInfoEnseignantByMatiere();
            
            setInfoCategoreNiveauInCombox(comb_catego_niv);
        } else {
            jLabel20.setText("0.00");
            Enseignantcombo.removeAllItems();
           ComboGroup.removeAllItems();
        }
        

        //setInfoEnsignInCom(NiveauCatg_Etude, ClassInNiveau, matiere_comb);
        
        
        
        //setInfoEnsignInCom();
        
        
        
    }//GEN-LAST:event_matiere_combActionPerformed

    private void NiveauCatg_EtudeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NiveauCatg_EtudeItemStateChanged
        
    }//GEN-LAST:event_NiveauCatg_EtudeItemStateChanged

    private void jCheckBoxCustom6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCustom6ActionPerformed
        jLabel22.setVisible(true);
        jLabel24.setVisible(false);
        jTextField1.setVisible(false);
    }//GEN-LAST:event_jCheckBoxCustom6ActionPerformed

    private void jCheckBoxCustom8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCustom8ActionPerformed
        jTextField1.setVisible(true);
        jLabel24.setVisible(false);
        jLabel22.setVisible(false);
        jTextField1.requestFocusInWindow();
    }//GEN-LAST:event_jCheckBoxCustom8ActionPerformed

    private void jCheckBoxCustom7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCustom7ActionPerformed
        jLabel24.setVisible(true);
        jLabel24.setText(jLabel20.getText());
        jLabel22.setVisible(false);
        jTextField1.setVisible(false);
    }//GEN-LAST:event_jCheckBoxCustom7ActionPerformed

    private void EnseignantcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnseignantcomboActionPerformed
       if (matiere_comb.getSelectedIndex() != -1 && ClassInNiveau.getSelectedIndex() != -1
                && NiveauCatg_Etude.getSelectedIndex() != -1 && Enseignantcombo.getSelectedIndex()!=-1) {
        try {
            Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(matiere_comb.getSelectedItem().toString(),
                    ClassInNiveau.getSelectedItem().toString(), NiveauCatg_Etude.getSelectedItem().toString());
            String FullName= (String) Enseignantcombo.getSelectedItem();
            
            String Nom=FullName.split("-")[0];
            String Prenom=FullName.split("-")[1];
            
            System.out.println(Nom +"  -- "+Prenom );
            
            Enseignant enseignat = enseignantDAOImpl.findByFullName(Nom, Prenom);
            
            System.out.println(enseignat);
            
            if (enseignat!=null){
            NomEnseignant.setText(enseignat.getNomAr());
            PrenomEnseignant.setText(enseignat.getPrenomAr());
            
            
            List<Groupe> list_group_etude=group_dao_imp.findGroupsByMatiereAndEnseignat(new MatiereEnseignant(0, enseignat, matiere, LocalDate.MAX),CheckAllGroups.isSelected());
                System.out.println("List Groups :"+list_group_etude);
            
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

//    public void setInfoGroupEtudeCombox() {
//        List<GroupeMatiere> listGroupesMatiers = GroupeMatiere_imp_dao.findAll();
//        List<MatiereEnseignant> listMatiereEnsegnant= MatiereEnseignat_dao_impl.findAll();
//        
//        
//        for (GroupeMatiere groupe_matiere : listGroupesMatiers) {
//            
//            if (groupe_matiere.getMatiere().getMatiereEtdAr().equals(matiere_comb.getSelectedItem()))
//            //ComboGroup.addItem(categoreNiveau_obj.getCategore_niveau_ar());
//        }
//
//    }
    
    
    private void ComboGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboGroupActionPerformed
        
    }//GEN-LAST:event_ComboGroupActionPerformed
    private void setForm(JComponent Center, JComponent com) {
        Center.removeAll();
        Center.add(com);
        Center.repaint();
        Center.revalidate();

    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JFIF", "*.jfif")
        );
    }

    public void InitialiseFields(){
        Component [] list =  panel_info_student.getComponents();
        
        for (Component component : list) {
            if (component instanceof TextField){
                ((JTextField) component).setText("");
            }
            if (component instanceof Combobox){
                ((JComboBox) component).setSelectedIndex(0);
            }
            
            
            
        }
        
    }
    
    public void setDesignTable(JTable tab, JScrollPane scrol) {
        TableCustom.apply(scrol, TableCustom.TableType.DEFAULT);
        tab.getTableHeader().setFont(new Font("", Font.BOLD, 15));
        tab.getTableHeader().setSize(20, HEIGHT);
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
            java.util.logging.Logger.getLogger(AddEtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddEtudiantForm dialog = new AddEtudiantForm(new home(), true);
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
    private material.design.Combobox CatCours;
    private javax.swing.JCheckBox CheckAllGroups;
    private material.design.Combobox ClassInNiveau;
    private javax.swing.JPanel Code;
    private material.design.Combobox ComboGroup;
    private material.design.Combobox Enseignantcombo;
    private material.design.Combobox Groupe2;
    private material.design.Combobox Groupe3;
    private material.design.Combobox Groupe4;
    private material.design.Combobox Groupe5;
    private material.design.Combobox NiveauCatg_Etude;
    private javax.swing.JLabel NomEnseignant;
    private javax.swing.JLabel PrenomEnseignant;
    private material.design.buttonRounder btn_retour;
    private material.design.buttonRounder buttonRounder1;
    private material.design.buttonRounder buttonRounder17;
    private material.design.buttonRounder buttonRounder19;
    private material.design.buttonRounder buttonRounder2;
    private material.design.buttonRounder buttonRounder3;
    private material.design.buttonRounder buttonRounder4;
    private material.design.buttonRounder buttonRounder5;
    private material.design.buttonRounder buttonRounder6;
    private material.design.buttonRounder buttonRounder7;
    private material.design.buttonRounder buttonRounder9;
    private material.design.Combobox com_niv;
    private material.design.Combobox comb_catego_niv;
    private material.design.Combobox combobox2;
    private material.design.Combobox combobox3;
    private datechooser.DateChooser dateChooser;
    private material.design.JCheckBoxCustomfr jCheckBoxCustom6;
    private material.design.JCheckBoxCustomfr jCheckBoxCustom7;
    private material.design.JCheckBoxCustomfr jCheckBoxCustom8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lab_codBar;
    private javax.swing.JLabel lab_matricul;
    private material.design.Combobox matiere_comb;
    private javax.swing.JPanel pan_catego;
    private javax.swing.JPanel pan_cour;
    private javax.swing.JPanel pan_day1;
    private javax.swing.JPanel pan_day2;
    private javax.swing.JPanel pan_etud_cour_insc;
    private javax.swing.JPanel pan_formation;
    private javax.swing.JPanel pan_info_etudiant_insc;
    private javax.swing.JPanel pan_lang;
    private javax.swing.JPanel panel_info_student;
    private ui.table.TableScrollButton tableScrollButton1;
    private material.design.TextField txt_adress;
    private material.design.TextField txt_dat_birth;
    private javax.swing.JTextField txt_email;
    private material.design.TextField txt_name;
    private material.design.TextField txt_nom_fr;
    private material.design.TextField txt_prenom;
    private material.design.TextField txt_prenom_fr;
    private material.design.TextField txt_renseignementPe;
    private javax.swing.JTextField txt_tel;
    private material.design.Combobox الدورات;
    // End of variables declaration//GEN-END:variables
}
