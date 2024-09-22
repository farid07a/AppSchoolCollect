/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package guis;

import Service.PayementService;
import domaine.CategoreNiveau;
import domaine.Etudiant;
import domaine.Inscription;
import domaine.Matiere;
import domaine.NiveauEtude;
import domaine.Payement;
import domaine.Seance_Matiere;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import main.java.com.school.impl.CategoreNiveauDAOImpl;
import main.java.com.school.impl.EnseignantDAOImpl;
import main.java.com.school.impl.EtudiantDAOImpl;
import main.java.com.school.impl.InscriptionDAOImpl;
import main.java.com.school.impl.MatiereDAOImpl;
import main.java.com.school.impl.NiveauEtudeDAOImpl;
import main.java.com.school.impl.PayementDAOImpl;
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
public class PayementCredit extends javax.swing.JDialog {
    
    home home;
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
    List<Seance_Matiere> seance_Matieres_payee = new ArrayList<>();
    List<Payement> payements = new ArrayList<>();    
    Etudiant etudiant;
    
    public PayementCredit(java.awt.Frame parent, boolean modal, Etudiant etudiant) {
        super(parent, modal);
        this.home = (home) parent;
        initComponents();
        setLocationRelativeTo( this.home);
        this.etudiant = etudiant;
        
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
        inscriptionDAOImpl = new InscriptionDAOImpl(connection);
        seanceMatiereDAOImpl = new SeanceMatiereDAOImpl(connection);
        payementDAOImpl = new PayementDAOImpl(connection);
        sceanceDAOImpl = new SeanceDAOImpl(connection);        
        
        setDesignTable(jTable5, jScrollPane16);
      //  SearchTable(jTable5, jTextField1);
        if (etudiant != null) {
            setInfoEtudiantInPan(etudiant);
            setInfoCridetInTab(etudiant);
        }
    }
    
    public void setInfoCridetInTab(Etudiant etudiant) {
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
        List<Payement> payements = payementDAOImpl.getCreditOfEtudiant(etudiant, "ديون");
        
        for (Payement payement : payements) {
            int id_payement = payement.getId();
            int id_etudiant = payement.getEtudiant().getId();
            int id_matiere = payement.getMatiere().getId();
            int id_seance = payement.getSeance().getId();
            
            String name_prenom = payement.getEtudiant().getNom() + " " + payement.getEtudiant().getPrenom();
            String matiere = payement.getMatiere().getMatiereEtdAr();
            int nb_seance = payement.getNb_seance();
            
            double Prix = payement.getPrix();
            double prxi_seance = payement.getMatiere().getPrix() / payement.getMatiere().getNum_sceance_moins();
            double prix_payee = payement.getPrixPaye();
            double prix_total = payement.getPrixTotal();
            String Type_Payement = payement.getTypePayement();
            
            LocalDate date = payement.getDate();
            model.addRow(new Object[]{date, prix_total, prix_payee, Type_Payement,
                 nb_seance, prxi_seance, payement.getMatiere().getPrix(), matiere, name_prenom, id_seance, id_matiere, id_etudiant, id_payement}
            );
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lab_id_categ = new javax.swing.JLabel();
        lab_id_niv = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lab_prenom = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lab_nom = new javax.swing.JLabel();
        lab_catego = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lab_niveau = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tableScrollButton1 = new ui.table.TableScrollButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lab_prix = new javax.swing.JLabel();
        lab_prix_seance = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        montant_vers = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_nb_seance = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        buttonRounder19 = new material.design.buttonRounder();
        buttonRounder17 = new material.design.buttonRounder();
        jCheckBoxCustomfr1 = new material.design.JCheckBoxCustomfr();
        buttonRounder5 = new material.design.buttonRounder();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 420, 31));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("N°");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 20, 25));

        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 390, 29));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("BarCode");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, 25));

        lab_id_categ.setText("id level");
        jPanel1.add(lab_id_categ, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 59, -1));

        lab_id_niv.setText("id_class");
        jPanel1.add(lab_id_niv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 21));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        lab_prenom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_prenom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_prenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 226, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("اللقب");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 32, -1));

        lab_nom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_nom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 120, 204, 30));

        lab_catego.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_catego.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_catego.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_catego, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 204, 29));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("المستوى");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 50, -1));

        lab_niveau.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_niveau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_niveau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lab_niveau, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 228, 25));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("دفع مستحقات الديون");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 1090, 30));

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

        jPanel1.add(tableScrollButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 1000, 200));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(147, 147, 147));
        jLabel4.setText("المادة");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 410, 39, 27));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 440, 189, 36));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(147, 147, 147));
        jLabel7.setText("المبلغ الشهري");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 410, 74, 27));

        lab_prix.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lab_prix.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_prix.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel1.add(lab_prix, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 440, 179, 37));

        lab_prix_seance.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lab_prix_seance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_prix_seance.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel1.add(lab_prix_seance, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, 172, 38));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(147, 147, 147));
        jLabel11.setText("ثمن الحصة الواحدة");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, 112, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(147, 147, 147));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("المبلغ الواجب دفعه حسب عدد الحصص");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 504, 190, 20));

        montant_vers.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        montant_vers.setForeground(new java.awt.Color(51, 204, 0));
        montant_vers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        montant_vers.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel1.add(montant_vers, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 530, 179, 46));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(147, 147, 147));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("عدد الحصصة الغير مدفوعة");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 500, 160, 30));

        txt_nb_seance.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_nb_seance.setForeground(new java.awt.Color(0, 51, 255));
        txt_nb_seance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nb_seance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nb_seanceActionPerformed(evt);
            }
        });
        txt_nb_seance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nb_seanceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nb_seanceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nb_seanceKeyTyped(evt);
            }
        });
        jPanel1.add(txt_nb_seance, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 530, 186, 46));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("الاسم");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 100, 32, -1));

        buttonRounder19.setBackground(new java.awt.Color(51, 153, 0));
        buttonRounder19.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder19.setText("حفظ");
        buttonRounder19.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonRounder19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder19ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonRounder19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 590, 93, -1));

        buttonRounder17.setBackground(new java.awt.Color(255, 0, 51));
        buttonRounder17.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder17.setText("إلغاء");
        buttonRounder17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonRounder17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder17ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonRounder17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 590, 94, -1));

        jCheckBoxCustomfr1.setBackground(new java.awt.Color(0, 255, 0));
        jCheckBoxCustomfr1.setText("تأكيد الدفع");
        jCheckBoxCustomfr1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jCheckBoxCustomfr1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jCheckBoxCustomfr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 530, 126, 37));

        buttonRounder5.setBackground(new java.awt.Color(102, 153, 255));
        buttonRounder5.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifier (4).png"))); // NOI18N
        buttonRounder5.setText("معاينة");
        buttonRounder5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder5ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonRounder5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 120, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("القسم");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 32, -1));

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
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String codbare = jTextField2.getText();
        Etudiant etudiant = etudiantDAOImpl.getEtudiantByCodbar(codbare);
        setInfoEtudiantInPan(etudiant);
        setInfoCridetInTab(etudiant);
//       System.out.println(""+etudiant);
//        CategoreNiveau categoreNiveau = etudiant.getCtegore_niveau();
//        
//        NiveauEtude niveauEtude = etudiant.getNiveau();
//        lab_nom.setText(etudiant.getNom());
//        lab_prenom.setText(etudiant.getPrenom());
//        lab_catego.setText(categoreNiveau.getCategore_niveau_ar());
//        lab_id_categ.setText(categoreNiveau.getId() + "");
//        lab_niveau.setText(niveauEtude.getNiveauInitialAr());
//        lab_id_niv.setText(niveauEtude.getId() + "");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
      if(jTable5.getSelectedRow()!=-1){
      int row = jTable5.getSelectedRow();
      String matiere = jTable5.getValueAt(row, 7).toString();
      double prix_matiere = (double)jTable5.getValueAt(row, 6);
       double prix_seance = (double)jTable5.getValueAt(row, 5);
       int nb_seance = (int)jTable5.getValueAt(row, 4);
       jLabel6.setText(matiere);
       lab_prix.setText(prix_matiere+"");
       lab_prix_seance.setText(prix_seance+"");
       txt_nb_seance.setText(nb_seance+"");       
       montant_vers.setText(nb_seance*prix_seance+"");      
      }
      
    }//GEN-LAST:event_jTable5MouseClicked

    private void txt_nb_seanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nb_seanceKeyTyped
        int nb_seance_tab =(int) jTable5.getValueAt(jTable5.getSelectedRow(), 4);      
        char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep(); // Play beep sound
                    evt.consume(); // Prevent the character from being typed
                }       
    }//GEN-LAST:event_txt_nb_seanceKeyTyped

    private void txt_nb_seanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nb_seanceKeyPressed
   if(!txt_nb_seance.getText().isEmpty()){
            int nb_seance = Integer.parseInt(txt_nb_seance.getText());
            double prix_seance = Double.parseDouble( lab_prix_seance.getText());
            double mantant = nb_seance * prix_seance;  
            montant_vers.setText(mantant +"");
        }else{
             montant_vers.setText(0+"");
 }    
    }//GEN-LAST:event_txt_nb_seanceKeyPressed

    private void txt_nb_seanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nb_seanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nb_seanceActionPerformed

    private void txt_nb_seanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nb_seanceKeyReleased
 
//      char ch = evt.getKeyChar();
//        //if(prix.getText().c)
//        if ((!Character.isDigit(ch) && ch != '.')) {
//            getToolkit().beep();
//            evt.consume(); // Ignore the key event
//        }

    }//GEN-LAST:event_txt_nb_seanceKeyReleased

    private void buttonRounder19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder19ActionPerformed
            String cod_bar=jTextField2.getText();
            Etudiant etudiant = etudiantDAOImpl.getEtudiantByCodbar(cod_bar);
            Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(jLabel6.getText(), lab_niveau.getText(), lab_catego.getText());
            if(jCheckBoxCustomfr1.isSelected() && jTable5.getSelectedRow()!=-1){
                int row =jTable5.getSelectedRow();
                int id_payement_credit=(int)jTable5.getValueAt(row, 12);
                Payement payement_credit = payementDAOImpl.findById(id_payement_credit);
                int nb_seance_credit=(int)jTable5.getValueAt(row, 4);
                int nb_seance_payee = Integer.parseInt(   txt_nb_seance.getText());
                int nbr_seance_rest=nb_seance_credit-nb_seance_payee;
                double prix_seance=Double.parseDouble(lab_prix_seance.getText());
                double montant = Double.parseDouble(montant_vers.getText());
                
                payement_credit.setNb_seance(nbr_seance_rest);
                payement_credit.setPrixPaye(payement_credit.getPrixPaye()+montant);
                payement_credit.setPrixTotal(payement_credit.getPrixTotal()+payement_credit.getPrixPaye());
                
                if(payementDAOImpl.update(payement_credit)>0){                    
                  JOptionPane.showMessageDialog(null, "prix payee : "+montant);
                  
                    try {
                        new PayementService().updatePayementDetailleWhenPayéé(payement_credit.getId(), nb_seance_payee, prix_seance);
                    } catch (DatabaseConnectionException ex) {
                        Logger.getLogger(PayementCredit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                   setInfoCridetInTab(etudiant);
                }
                
            }else{
            
            }
    }//GEN-LAST:event_buttonRounder19ActionPerformed

    private void buttonRounder17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder17ActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonRounder17ActionPerformed

    private void buttonRounder5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder5ActionPerformed
        if (jTable5.getSelectedRow() != -1) {
            int id = (int) jTable5.getValueAt(jTable5.getSelectedRow(),12 );
            Payement payement = payementDAOImpl.findById(id);
            new CreditDetailleForm((Frame) SwingUtilities.getWindowAncestor(this.home), true,payement ).setVisible(true);
        }
    }//GEN-LAST:event_buttonRounder5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(PayementCredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayementCredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayementCredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayementCredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                PayementCredit dialog = new PayementCredit(new javax.swing.JFrame(), true);
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
    private material.design.buttonRounder buttonRounder17;
    private material.design.buttonRounder buttonRounder19;
    private material.design.buttonRounder buttonRounder5;
    private javax.swing.JButton jButton1;
    private material.design.JCheckBoxCustomfr jCheckBoxCustomfr1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lab_catego;
    private javax.swing.JLabel lab_id_categ;
    private javax.swing.JLabel lab_id_niv;
    private javax.swing.JLabel lab_niveau;
    private javax.swing.JLabel lab_nom;
    private javax.swing.JLabel lab_prenom;
    private javax.swing.JLabel lab_prix;
    private javax.swing.JLabel lab_prix_seance;
    private javax.swing.JLabel montant_vers;
    private ui.table.TableScrollButton tableScrollButton1;
    private javax.swing.JTextField txt_nb_seance;
    // End of variables declaration//GEN-END:variables
public void setInfoEtudiantInPan(Etudiant etudiant) {
        // JOptionPane.showMessageDialog(null, remplire);
        jTextField2.setText(etudiant.getCodeBare());
        CategoreNiveau categoreNiveau = etudiant.getCtegore_niveau();
        
        NiveauEtude niveauEtude = etudiant.getNiveau();
        lab_nom.setText(etudiant.getNom());
        lab_prenom.setText(etudiant.getPrenom());
        lab_catego.setText(categoreNiveau.getCategore_niveau_ar());
        lab_id_categ.setText(categoreNiveau.getId() + "");
        lab_niveau.setText(niveauEtude.getNiveauInitialAr());
        lab_id_niv.setText(niveauEtude.getId() + "");
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
}
