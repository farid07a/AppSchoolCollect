/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import DialogFram.ValidationMessageDialog;
import Service.MatiereService;
import Service.PayementService;
import Service.PresenceService;
import Service.SeanceService;
import domaine.Etudiant;
import domaine.Inscription;
import domaine.Matiere;
import domaine.Payement;
import domaine.Presence;
import domaine.Seance;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import main.java.com.school.impl.EtudiantDAOImpl;
import main.java.com.school.impl.InscriptionDAOImpl;
import main.java.com.school.impl.PayementDAOImpl;
import main.java.com.school.impl.PresenceDAOImpl;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;
import material.design.ScrollBar;
import ui.table.TableCustom;

/**
 *
 * @author farid
 */
public class Panel_PresenceUI1 extends javax.swing.JPanel {

    /**
     * Creates new form Panel_PresenceUI
     */
    Connection connection;
    MatiereService matiere_service = new MatiereService();
    SeanceService seance_service = new SeanceService();
    EtudiantDAOImpl etudiantDAOImpl;
    InscriptionDAOImpl inscriptionDAOImpl;
    List<Seance> list_seance_about_all_matieres = new ArrayList<>();
    ValidationMessageDialog message_validation;
    SeanceDAOImpl seanceDAOImpl;
    PresenceDAOImpl presenceDAOImpl;
    PresenceService PresenceService = new PresenceService();
    PayementDAOImpl payementDAOImpl;

    public Panel_PresenceUI1() {
        initComponents();

        try {
            connection = new ConnectionDB().getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        etudiantDAOImpl = new EtudiantDAOImpl(connection);
        inscriptionDAOImpl = new InscriptionDAOImpl(connection);
        seanceDAOImpl = new SeanceDAOImpl(connection);
        presenceDAOImpl = new PresenceDAOImpl(connection);
        payementDAOImpl = new PayementDAOImpl(connection);
        ValidationMessageDialog message_validation = new ValidationMessageDialog((JFrame) getParent());
        setDesignTable(tab_presence, jScrollPane12);
        SearchTable(tab_presence, searchText6);
        try {
            prepareUI();
        } catch (SQLException ex) {
            Logger.getLogger(Panel_PresenceUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        // DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        // labDate_seance.setText(LocalDate.now().format(Formatter)+""); 

    }

    public void prepareUI() throws SQLException {
        try {

            checkSeancesAndSaveNewSeance();
            setSeanceInTable();
            new PresenceService().getAllPrsenceOfEtdiantWithSeanceOfTOday(list_seance_about_all_matieres);
            SavePresenceInTable();

            // this.list_seance_about_all_matieres.clear();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(Panel_PresenceUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SavePresenceInTable() {
//        JOptionPane.showMessageDialog(null, "excute save in table ");
        DefaultTableModel model = (DefaultTableModel) tab_presence.getModel();
        model.setRowCount(0);
        System.out.println("list_seance_about_all_matieres = " + list_seance_about_all_matieres.size());

        List<Presence> presences = presenceDAOImpl.getPresenceEtudiantToDay(LocalDate.now());
        for (Presence presence : presences) {
            String str;
            if (presence.isEtat()) {
                str = "حاضر";
                model.addRow(new Object[]{LocalTime.now(), str, presence.getMatiere().getMatiereEtdAr(),
                    presence.getEtudiant().getNiveau().getNiveauInitialAr(),
                    presence.getEtudiant().getCtegore_niveau().getCategore_niveau_ar(),
                    presence.getEtudiant().getNom() + " " + presence.getEtudiant().getPrenom(),
                    presence.getEtudiant().getId(), presence.getSeance().getId(), presence.getId()});
            } else {
                //      JOptionPane.showMessageDialog(null, "list presence In table");
                str = "غائب";
                model.addRow(new Object[]{"", str, presence.getMatiere().getMatiereEtdAr(),
                    presence.getEtudiant().getNiveau().getNiveauInitialAr(),
                    presence.getEtudiant().getCtegore_niveau().getCategore_niveau_ar(),
                    presence.getEtudiant().getNom() + " " + presence.getEtudiant().getPrenom(),
                    presence.getEtudiant().getId(), presence.getSeance().getId(), presence.getId()});
            }

        }

    }

    public void checkSeancesAndSaveNewSeance() throws DatabaseConnectionException, SQLException {
        java.util.List<Matiere> list_matiere = matiere_service.getListMatierByDay();// get all Matiere for today 
        List list_previeux = null;
        // for test si seance exit update true= en cours sinon creation Number total of seance in month  
        Seance scence_obj = null;
        this.list_seance_about_all_matieres.clear();
        for (Matiere matiere : list_matiere) {
            // get seance of matiere today 
            scence_obj = seance_service.GetSeanceByTody(matiere);

            if (scence_obj != null) {
                // 
                list_seance_about_all_matieres.add(scence_obj);
                System.out.println("Object != null success add ");
            } else {
                list_previeux = seance_service.getListAllSeancePrevieuSemaine(matiere); // list [sceance(1....30) ]
                System.out.println("Seance is Null AFter inser database change to :" + seance_service.GetSeanceByTody(matiere));
                //- get last seance si diference <=7
                // JOptionPane.showMessageDialog(null, "null seance and matiere id ==" + matiere.getId());
                // JOptionPane.showMessageDialog(null, "existe vacance  : " + seance_service.ExistVacances(matiere));
                if (!seance_service.ExistVacances(matiere)) {  //ExistVacances = false
                    seance_service.saveAllNextSeances(list_previeux);// save next seances about Matiere
                    list_seance_about_all_matieres.add(seance_service.GetSeanceByTody(matiere));

                } else {    // disable all components and show add seance
                    //     JOptionPane.showMessageDialog(null, "existe vacance . . . " + list_previeux.size());
                    seance_service.saveAllNextSeancesSiExistVacance(list_previeux);
                    System.out.println("Seance is Null AFter inser database change to :" + seance_service.GetSeanceByTody(matiere));
                    list_seance_about_all_matieres.add(seance_service.GetSeanceByTody(matiere));
                }
            }
        }

    }

    public void setSeanceInTable() {
        DefaultTableModel df = (DefaultTableModel) pan1.getTtable_seance_to_day().getModel();
        df.setRowCount(0);
        System.out.println("List Befor Insert Data in Table " + list_seance_about_all_matieres);
        for (Seance seance_obj : list_seance_about_all_matieres) {
            System.out.println(seance_obj);

            Object[] args = {seance_obj.getNumSeance(), seance_obj.getMatiere().getMatiereEtdAr(), seance_obj.getMatiere().getNiveau().getNiveauInitialAr(),
                seance_obj.getMatiere().getCategoreNiveau().getCategore_niveau_ar(), seance_obj.getMatiere().getId(), seance_obj.getId()};
            df.addRow(args);
        }

    }

    public List<Seance> getSeanceOfTodayWithInscriptionEtud(List<Seance> Seances, Etudiant etudiant) {
        List<Inscription> inscriptions = inscriptionDAOImpl.findByEtudiantId(etudiant.getId());
        JOptionPane.showMessageDialog(null, "inscription etudiant : " + inscriptions.size());
        List<Seance> Seance_of_today = new ArrayList<>();
        for (Inscription inscription : inscriptions) { // list inscription of etudaint
            for (Seance seance : Seances) { // list seance o to day
                if (inscription.getMatiere().getId() == seance.getMatiere().getId()) { // etidaint <- inscription + matier (time 1)

                    Seance_of_today.add(seance); // liste seance etudieé to day
                }
            }
        }
        return Seance_of_today;
    }

    public void setInfoEtudiantInPanPresence(Etudiant etudiant) {
        if (etudiant != null) {
            cleanItemsPanPresence();
            lab_icon_chech_cross.setIcon(null);
            lab_nam_presence.setText(etudiant.getNom());
            lab_prenom_presence.setText(etudiant.getPrenom());
            lab_category_presence.setText(etudiant.getCtegore_niveau().getCategore_niveau_ar());
            lab_niveau_presence.setText(etudiant.getNiveau().getNiveauInitialAr());
            if (etudiant.getImage() != null) {
                byte[] imag = etudiant.getImage();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lab_imag_etud_presence.getWidth(), lab_imag_etud_presence.getHeight(), Image.SCALE_SMOOTH));
                lab_imag_etud_presence.setIcon(imageIcon);
            } else {
                lab_imag_etud_presence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png")));
            }
        } else {
            cleanItemsPanPresence();
        }
    }

    public void cleanItemsPanPresence() {
        lab_icon_chech_cross.setIcon(null);
        lab_nam_presence.setText("");
        lab_prenom_presence.setText("");
        lab_category_presence.setText("");
        lab_niveau_presence.setText("");
        lab_imag_etud_presence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png")));
        //
    }

    public void setInfoInTablePresence() {
        try {
            List<Presence> presences = new PresenceService().getPresenceOfToDay();
            DefaultTableModel model = (DefaultTableModel) tab_presence.getModel();
            model.setRowCount(0);
            for (Presence presence : presences) {
                model.addRow(new Object[]{presence.getDatePresence(), 
                    presence.getMatiere().getMatiereEtdAr(),
                    presence.getMatiere().getNiveau().getNiveauInitialAr(),
                    presence.getMatiere().getCategoreNiveau().getCategore_niveau_ar(),
                    presence.getEtudiant().getNom() + " " + presence.getEtudiant().getPrenom(),
                    presence.getEtudiant().getId(), presence.getSeance().getId(), presence.getId()});
            }
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);

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

        pan_presence = new javax.swing.JPanel();
        txt_cod_barr_presence = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lab_category_presence = new javax.swing.JLabel();
        lab_niveau_presence = new javax.swing.JLabel();
        lab_nam_presence = new javax.swing.JLabel();
        lab_prenom_presence = new javax.swing.JLabel();
        lab_imag_etud_presence = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lab_matiere_presence = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        lab_num_seance_presence = new javax.swing.JLabel();
        lab_icon_chech_cross = new javax.swing.JLabel();
        tableScrollButton12 = new ui.table.TableScrollButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tab_presence = new javax.swing.JTable();
        searchText6 = new material.design.SearchText();
        buttonRounder33 = new material.design.buttonRounder();
        buttonRounder2 = new material.design.buttonRounder();
        buttonRounder4 = new material.design.buttonRounder();
        buttonRounder3 = new material.design.buttonRounder();
        pan1 = new ui.card.pan();
        lab_msg_error = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        pan_presence.setBackground(new java.awt.Color(255, 255, 255));
        pan_presence.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_cod_barr_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_cod_barr_presence.setForeground(new java.awt.Color(102, 102, 102));
        txt_cod_barr_presence.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cod_barr_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_cod_barr_presence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cod_barr_presenceActionPerformed(evt);
            }
        });
        pan_presence.add(txt_cod_barr_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 320, 31));

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(204, 204, 204));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("اللقب");
        pan_presence.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 90, 40, -1));

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(204, 204, 204));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("الاسم");
        pan_presence.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(1064, 90, 40, 20));

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(204, 204, 204));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("المستوى ");
        pan_presence.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 140, -1, -1));

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(204, 204, 204));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("القسم ");
        pan_presence.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 140, -1, -1));

        lab_category_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_category_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_category_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_category_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_category_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 160, 150, 24));

        lab_niveau_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_niveau_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_niveau_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_niveau_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_niveau_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, 150, 24));

        lab_nam_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_nam_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_nam_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_nam_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_nam_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 110, 150, 25));

        lab_prenom_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_prenom_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_prenom_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_prenom_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_prenom_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 150, 26));

        lab_imag_etud_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_imag_etud_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_imag_etud_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 120, 110));

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(204, 204, 204));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("المادة");
        pan_presence.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 190, 40, -1));

        lab_matiere_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_matiere_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_matiere_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_matiere_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_matiere_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, 150, 24));

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(204, 204, 204));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText("رقم الحصة");
        pan_presence.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 190, -1, -1));

        lab_num_seance_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_num_seance_presence.setForeground(new java.awt.Color(204, 0, 0));
        lab_num_seance_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_num_seance_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_num_seance_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, 150, 24));
        pan_presence.add(lab_icon_chech_cross, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 72, 70));

        tab_presence.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "التوقيت", "الحضور", "المادة", "القسم", "المستوى", "الاسم و اللقب", "Id_etud", "Id_seance", "id_presence"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tab_presence);

        tableScrollButton12.add(jScrollPane12, java.awt.BorderLayout.CENTER);

        pan_presence.add(tableScrollButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 800, 350));

        searchText6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        searchText6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchText6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText6ActionPerformed(evt);
            }
        });
        pan_presence.add(searchText6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 320, 27));

        buttonRounder33.setBackground(new java.awt.Color(241, 241, 241));
        buttonRounder33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh-button.png"))); // NOI18N
        buttonRounder33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder33ActionPerformed(evt);
            }
        });
        pan_presence.add(buttonRounder33, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, 40, 40));

        buttonRounder2.setBackground(new java.awt.Color(0, 153, 153));
        buttonRounder2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder2.setText("checkCard");
        buttonRounder2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonRounder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder2ActionPerformed(evt);
            }
        });
        pan_presence.add(buttonRounder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 120, 30));

        buttonRounder4.setBackground(new java.awt.Color(153, 153, 255));
        buttonRounder4.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder4.setText("معاينة");
        buttonRounder4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonRounder4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder4ActionPerformed(evt);
            }
        });
        pan_presence.add(buttonRounder4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 110, 30));

        buttonRounder3.setBackground(new java.awt.Color(255, 158, 13));
        buttonRounder3.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder3.setText("الدفع");
        buttonRounder3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonRounder3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder3ActionPerformed(evt);
            }
        });
        pan_presence.add(buttonRounder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 120, 30));

        pan1.setColor1(new java.awt.Color(211, 211, 255));
        pan1.setColor2(new java.awt.Color(0, 0, 204));
        pan_presence.add(pan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 650));

        lab_msg_error.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_msg_error.setForeground(new java.awt.Color(255, 0, 51));
        lab_msg_error.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        pan_presence.add(lab_msg_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 390, 30));

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(153, 0, 153));
        jCheckBox1.setText("بـــديــــون");
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        pan_presence.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 90, 30));

        jLabel1.setText("jLabel1");
        pan_presence.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 250, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_presence, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pan_presence, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cod_barr_presenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cod_barr_presenceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cod_barr_presenceActionPerformed

    private void buttonRounder33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder33ActionPerformed
        setInfoInTablePresence();
    }//GEN-LAST:event_buttonRounder33ActionPerformed

    private void buttonRounder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder2ActionPerformed
        if (!txt_cod_barr_presence.getText().isEmpty()) {
            Etudiant etudiant = etudiantDAOImpl.getEtudiantByCodbar(txt_cod_barr_presence.getText());// matier of today
            setInfoEtudiantInPanPresence(etudiant);
            //  JOptionPane.showMessageDialog(null, "seance etudiant" + list_seance_about_all_matieres.size());
            List<Seance> Seance_of_today_for_etudtaint = getSeanceOfTodayWithInscriptionEtud(list_seance_about_all_matieres, etudiant);
            // JOptionPane.showMessageDialog(null, "seance etudiant" + Seance_of_today_for_etudtaint.size());
            if (!Seance_of_today_for_etudtaint.isEmpty()) {
                // seance etudeé to day 1,2,3
                Matiere matiere = matiere_service.getMatier_Of_TimNow_Etud(Seance_of_today_for_etudtaint);//
                if (matiere != null) {
                    lab_matiere_presence.setText(matiere.getMatiereEtdAr());
                    Seance seance = getSeanceFromTab(matiere);
                    //  JOptionPane.showMessageDialog(null, "matiere now " + matiere.getMatiereEtdAr());
                    Presence presence = presenceDAOImpl.getPresenceOFetudiantInSeance(etudiant, seance, LocalDate.now());
                    //presence.setEtat(true);
                    if(presence != null && presence.isEtat()==false){
                    //  JOptionPane.showMessageDialog(null, "Presence  : " + presence.getId());               
                    Payement Last_payement = payementDAOImpl.getlasPayementEtudiantOfMatier(etudiant.getId(), matiere.getId());
                    if (jCheckBox1.isSelected()) {  // payee
                        if (new PayementService().CheckIfSeanceTODayPayeeAndSave(Last_payement)) {// payee
                            int nbr_seance_payee = Last_payement.getNb_seance();
                            Last_payement.setSeance(seance);
                            Last_payement.setNb_seance(nbr_seance_payee - 1);
                            payementDAOImpl.update(Last_payement);
                            presence.setEtat(true);
                          
                           presenceDAOImpl.update(presence);                        
                            lab_msg_error.setText("الحصة مدفوعة");
                            SavePresenceInTable();
                        } else { // no payéé
                            try {
                                Payement Last_payement_credit = new PayementDAOImpl(ConnectionDB.getConnection()).getlastCreditEtudiantOfMatier(etudiant.getId(), matiere.getId());
                                new PayementService().SaveDettesInPayement(Last_payement_credit, etudiant, matiere, seance);
                                presence.setEtat(true);
                                presenceDAOImpl.update(presence);
                                lab_msg_error.setText("الحضور بديون");
                                SavePresenceInTable(); 
                            } catch (DatabaseConnectionException ex) {
                                Logger.getLogger(Panel_PresenceUI1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {  //  payee
                        if (new PayementService().CheckIfSeanceTODayPayeeAndSave(Last_payement)) {
                            int nbr_seance_payee = Last_payement.getNb_seance();
                            Last_payement.setSeance(seance);
                            Last_payement.setNb_seance(nbr_seance_payee - 1);
                            payementDAOImpl.update(Last_payement);
                            presence.setEtat(true);
                            presenceDAOImpl.update(presence);
                            lab_msg_error.setText("الحضور بديون");
                            SavePresenceInTable(); 
                            lab_msg_error.setText("الحصة مدفوعة");
                        } else {
                            lab_msg_error.setText("الشهر غير مدفوع");
                        }

                    }
                }else{// presence true
                if(presence ==null){ // etudiant n'exist pas dans table presence // new etud
                 Presence new_presence = new Presence(0, etudiant, matiere, seance, LocalDate.now(),false);
                 presenceDAOImpl.save(presence);
                 SavePresenceInTable();
                }
                }    

                } else {
                    lab_msg_error.setText("لا تتوفر لديك حصة بهذا التوقيت حاليا");
                }
            } else {
                lab_msg_error.setText("لا تتوفر لديك حصة بهذا اليوم ");

            }
        }

//        try {
//        if (!txt_cod_barr_presence.getText().isEmpty()) {
//            Etudiant etudiant = etudiantDAOImpl.getEtudiantByCodbar(txt_cod_barr_presence.getText());// matier of today
//            setInfoEtudiantInPanPresence(etudiant);
//            JOptionPane.showMessageDialog(null, "seance etudiant" + list_seance_about_all_matieres.size());
//            List<Seance> Seance_of_today_for_etudtaint = getSeanceOfTodayWithInscriptionEtud(list_seance_about_all_matieres, etudiant);
//            JOptionPane.showMessageDialog(null, "seance etudiant" + Seance_of_today_for_etudtaint.size());
//            if (!Seance_of_today_for_etudtaint.isEmpty()) {
//                // seance etudeé to day 1,2,3
//                Matiere matiere = matiere_service.getMatier_Of_TimNow_Etud(Seance_of_today_for_etudtaint);//
//                if(matiere !=null){
//                lab_matiere_presence.setText(matiere.getMatiereEtdAr());
//                Seance seance = getSeanceFromTab(matiere);
//                JOptionPane.showMessageDialog(null, "matiere now " + matiere.getMatiereEtdAr());
//                Presence presence = presenceDAOImpl.getPresenceOFetudiantInSeance(etudiant, seance,LocalDate.now());
//                JOptionPane.showMessageDialog(null, "Presence  : " + presence.getId());               
//                Payement Last_payement = payementDAOImpl.getlasPayementEtudiantOfMatier(etudiant.getId(), matiere.getId());              
//                if(!presence.isEtat() && Last_payement !=null && Last_payement.getNb_seance()>0){ // seance payee
//                presence.setEtat(true);
//                if ( presenceDAOImpl.update(presence) > 0) {
//                 lab_msg_error.setText("");
//                    JOptionPane.showMessageDialog(null, "save presence ");
//                    int nbr_seance_payee=Last_payement.getNb_seance();
//                    Last_payement.setSeance(seance);
//                    Last_payement.setNb_seance(nbr_seance_payee-1);
//                    payementDAOImpl.update(Last_payement);
//                    SavePresenceInTable();
//                }
//                }else{  // pas de Last_payement                                                 
//                if(Last_payement !=null && Last_payement.getNb_seance()==0){
//                if(jCheckBox1.isSelected())
//                  new PayementService().SaveDettesInPayement(Last_payement, etudiant, matiere,seance);
//                lab_msg_error.setText(" لم يتم تسديد الشهري الحالي");
//                 }
//                
//                 if(presence.isEtat())
//                   lab_msg_error.setText(" تم تسجيل الحضور");
//                 }               
//            } else {
//                lab_matiere_presence.setText("/");
//                //  message_validation.showMessage(null, "لا توجد حصة اليوم بالنسبة لهذا الطالب");
//            }
//         }else{
//            lab_matiere_presence.setText("****");
//            lab_msg_error.setText(" لا توجد حصصة بالنسبة لهاد الطالب");
//            }
//        } else {
//            cleanItemsPanPresence();
//        }
//         } catch (DatabaseConnectionException ex) {
//                        Logger.getLogger(Panel_PresenceUI1.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//        // setInfoInTablePresence();
    }//GEN-LAST:event_buttonRounder2ActionPerformed

    private void searchText6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchText6ActionPerformed

    private void buttonRounder3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder3ActionPerformed
        new PayemmentParMoin((Frame) SwingUtilities.getWindowAncestor(this), true).setVisible(true);
    }//GEN-LAST:event_buttonRounder3ActionPerformed

    private void buttonRounder4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder4ActionPerformed


    }//GEN-LAST:event_buttonRounder4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private material.design.buttonRounder buttonRounder2;
    private material.design.buttonRounder buttonRounder3;
    private material.design.buttonRounder buttonRounder33;
    private material.design.buttonRounder buttonRounder4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JLabel lab_category_presence;
    private javax.swing.JLabel lab_icon_chech_cross;
    private javax.swing.JLabel lab_imag_etud_presence;
    private javax.swing.JLabel lab_matiere_presence;
    private javax.swing.JLabel lab_msg_error;
    private javax.swing.JLabel lab_nam_presence;
    private javax.swing.JLabel lab_niveau_presence;
    private javax.swing.JLabel lab_num_seance_presence;
    private javax.swing.JLabel lab_prenom_presence;
    private ui.card.pan pan1;
    private javax.swing.JPanel pan_presence;
    private material.design.SearchText searchText6;
    private javax.swing.JTable tab_presence;
    private ui.table.TableScrollButton tableScrollButton12;
    private javax.swing.JTextField txt_cod_barr_presence;
    // End of variables declaration//GEN-END:variables

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

    public Seance getSeanceFromTab(Matiere matiere) {
        Seance seance = null;
        if (pan1.getTtable_seance_to_day().getRowCount() != 0) {
            for (int i = 0; i < pan1.getTtable_seance_to_day().getRowCount(); i++) {
                int id_matiere = (int) pan1.getTtable_seance_to_day().getValueAt(i, 4);
                if (id_matiere == matiere.getId()) {
                    int id_seance = (int) pan1.getTtable_seance_to_day().getValueAt(i, 5);
                    seance = seanceDAOImpl.findById(id_seance);

                }
            }
        }
        return seance;
    }

}
