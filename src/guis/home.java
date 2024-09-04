package guis;

import DialogFram.ValidationMessageDialog;
import Service.MatiereService;
import Service.PresenceService;
import Service.SeanceService;
import com.sun.javafx.application.PlatformImpl;
import domaine.CategoreNiveau;
import domaine.Enseignant;
import domaine.Etudiant;
import domaine.Inscription;
import domaine.Matiere;
import domaine.NiveauEtude;
import domaine.Payement;
import domaine.Presence;
import domaine.Seance;
import domaine.Seance_Matiere;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
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
import main.java.com.school.impl.PresenceDAOImpl;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.impl.SeanceMatiereDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;
import material.design.PanelAction;
import material.design.ScrollBar;
import material.design.TableActionCellEditor;
import material.design.TableActionCellRender;
import material.design.TableActionEvent;
import ui.card.Model_card;
import ui.menufr.EventMenuSelected;
import ui.table.TableCustom;

public class home extends javax.swing.JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    Connection connection;
    NiveauEtudeDAOImpl niveauEtudeDAOImpl;
    CategoreNiveauDAOImpl categoreNiveauDAOImpl;
    EnseignantDAOImpl enseignantDAOImpl;
    EtudiantDAOImpl etudiantDAOImpl;
    MatiereDAOImpl matiereDAOImpl;
    SeanceDAOImpl sceanceDAOImpl;
    SeanceMatiereDAOImpl seanceMatiereDAOImpl;
    ValidationMessageDialog message_validation;
    InscriptionDAOImpl inscriptionDAOImpl;
    PayementDAOImpl payementDAOImpl;
    PresenceDAOImpl presenceDAOImpl;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    File image_file_mod = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    FormPayement formPayement;
    MatiereService matiere_service = new MatiereService();
    SeanceService seance_service = new SeanceService();
    List<Seance> list_seance_about_all_matieres = new ArrayList<>();

    public home() {
        initComponents();
        try {
            connection = new ConnectionDB().getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        niveauEtudeDAOImpl = new NiveauEtudeDAOImpl(connection);
        enseignantDAOImpl = new EnseignantDAOImpl(connection);
        etudiantDAOImpl = new EtudiantDAOImpl(connection);
        matiereDAOImpl = new MatiereDAOImpl(connection);
        sceanceDAOImpl = new SeanceDAOImpl(connection);
        seanceMatiereDAOImpl = new SeanceMatiereDAOImpl(connection);
        inscriptionDAOImpl = new InscriptionDAOImpl(connection);
        payementDAOImpl = new PayementDAOImpl(connection);
        presenceDAOImpl = new PresenceDAOImpl(connection);
        message_validation = new ValidationMessageDialog(this);
        setExtendedState(MAXIMIZED_BOTH);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        setDesignTable(jTable1, jScrollPane1);
        setDesignTable(tab_etudiant_view, jScrollPane2);
        setDesignTable(jTable3, jScrollPane3);
        setDesignTable(tab_seance, jScrollPane6);
        // setDesignTable(jTable1, jScrollPane1);
        setDesignTable(tab_class, jScrollPane5);
        setDesignTable(tab_level, jScrollPane4);
        setDesignTable(tab_formation, jScrollPane7);
        setDesignTable(tab_lang, jScrollPane8);
        setDesignTable(tab_prof, jScrollPane9);
        setDesignTable(tab_matier, jScrollPane10);
        setDesignTable(table_seance_to_day, jScrollPane11);
        setDesignTable(tab_presence, jScrollPane12);
        SearchTable(tab_seance, searchText5);
        preapareDashboard();
        customMenu1.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                switch (index) {
                    case 0:
                        setForm(pan_center, Dashboard);
                        preapareDashboard();

                        break;
                    case 1:
                        setForm(pan_center, pan_saise);

                        break;
                    case 2:
                        setForm(pan_center, pan_view);
                        setInfoEtudiantInTab();

                        break;
//                    case 3:
//                        setForm(pan_room);                     
//                        break;
//
//                    case 4:
//                        setForm(pan_booking);
//                                                break;
//                    case 5:
//                        setForm(pan_booking_entreprise);                    
//                        break;

                    case 3:

                        setForm(pan_center, pan_ensign);
                        setInfoEnsignInTab();
                        break;

                    case 4:
                        //pan_presence.setVisible(false);
                        //setForm(pan_center, pan_presence);
                        Panel_PresenceUI pan_prsence=new Panel_PresenceUI();
                        setForm(pan_center,pan_prsence);
                        try {
                            prepareUIPresence();
                        } catch (SQLException ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //getMatiereOfToDay2();
                        /// getMatiereOfToDay();
                        break;

                    case 7:
                        setForm(pan_center, pan_config);
                        setInfoCategorNiveauInTab();
                        break;
                    default:

                }
            }
        });

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
        categoreNiveauDAOImpl = new CategoreNiveauDAOImpl(connection);
        PrepareUI();
    }

    public void prepareUIPresence() throws SQLException {

        try {
            checkSeancesAndSaveNewSeance();
            setSeanceInTable();
            // this.list_seance_about_all_matieres.clear();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(Panel_PresenceUI.class.getName()).log(Level.SEVERE, null, ex);
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
                JOptionPane.showMessageDialog(null, "null seance and matiere id ==" + matiere.getId());
                JOptionPane.showMessageDialog(null, "existe vacance  : " + seance_service.ExistVacances(matiere));
                if (!seance_service.ExistVacances(matiere)) {  //ExistVacances = false
                    seance_service.saveAllNextSeances(list_previeux);// save next seances about Matiere
                    list_seance_about_all_matieres.add(seance_service.GetSeanceByTody(matiere));

                } else {    // disable all components and show add seance
                    JOptionPane.showMessageDialog(null, "existe vacance . . . " + list_previeux.size());
                    seance_service.saveAllNextSeancesSiExistVacance(list_previeux);
                    System.out.println("Seance is Null AFter inser database change to :" + seance_service.GetSeanceByTody(matiere));
                    list_seance_about_all_matieres.add(seance_service.GetSeanceByTody(matiere));
                }
            }
        }

    }

    public void setSeanceInTable() {
        DefaultTableModel df = (DefaultTableModel) table_seance_to_day.getModel();
        df.setRowCount(0);
        System.out.println("List Befor Insert Data in Table " + list_seance_about_all_matieres);
        for (Seance seance_obj : list_seance_about_all_matieres) {
            System.out.println(seance_obj);

            Object[] args = {seance_obj.getNumSeance(), seance_obj.getMatiere().getMatiereEtdAr(), seance_obj.getMatiere().getNiveau().getNiveauInitialAr(),
                seance_obj.getMatiere().getCategoreNiveau().getCategore_niveau_ar(), seance_obj.getMatiere().getId(), seance_obj.getId()};
            df.addRow(args);
        }

    }

    public void PrepareUI() {
        // setInfoCategoreNiveauInCombox(com_catego_niveau, com_niveau);

        setInfoCategoreNiveauInCombox(com_catego_niveau, com_niveau);
        setInfoSeanceMatierInTable();
        creation_dashbord();
    }

    public void setInfoCategoreNiveauInCombox(JComboBox comb_catego, JComboBox comb_niveau) {
        List<CategoreNiveau> categoreNiveaus = categoreNiveauDAOImpl.findAll();
        if (categoreNiveaus != null) {
            for (CategoreNiveau categoreNiveau_obj : categoreNiveaus) {
                comb_catego.addItem(categoreNiveau_obj.getCategore_niveau_ar());
            }
            setInfoNiveauEtudeInCom(comb_catego, comb_niveau);
        }
    }

    public void setInfoNiveauEtudeInCom(JComboBox comb_catego, JComboBox comb_niveau) {
        if (comb_catego.getSelectedIndex() != -1) {
            CategoreNiveau category = categoreNiveauDAOImpl.findByName(comb_catego.getSelectedItem().toString(), "categore_niveau_ar");
            List<NiveauEtude> niveauEtudes = niveauEtudeDAOImpl.findAll();
            comb_niveau.removeAllItems();
            for (NiveauEtude obj : niveauEtudes) {
                if (obj.getCategore_niveau_id() == category.getId()) {
                    comb_niveau.addItem(obj.getNiveauInitialAr());
                }
            }
        }
        // setInfoMatiereInCom();
    }

//     public  void setInfoCategoreNiveauInCombox(){
//         List<CategoreNiveau > categoreNiveaus = categoreNiveauDAOImpl.findAll();
//          for ( CategoreNiveau categoreNiveau_obj : categoreNiveaus) {
//           com_catego_niveau.addItem(categoreNiveau_obj.getCategore_niveau_ar());
//          }
//    }
    public void setInfoEnsignInTab() {
        List<Enseignant> enseignants = enseignantDAOImpl.findAll();
        DefaultTableModel model = (DefaultTableModel) tab_prof.getModel();
        model.setRowCount(0);
        for (Enseignant enseignant : enseignants) {
            model.addRow(new Object[]{"", "", "", enseignant.getEmail(), enseignant.getPhoneNum(), enseignant.getSpecialite(), enseignant.getPrenomFr(),
                enseignant.getNomFr(), enseignant.getPrenomAr(), enseignant.getNomAr(), enseignant.getId()});
        }
    }

    public void setInfoEtudiantInTab() {
        lab_img_etu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png")));
        List<Etudiant> etudiants = etudiantDAOImpl.findAll();
        DefaultTableModel model = (DefaultTableModel) tab_etudiant_view.getModel();
        model.setRowCount(0);
        for (Etudiant etudiant : etudiants) {
            model.addRow(new Object[]{etudiant.getRenseignementPe(), etudiant.getEmail(), etudiant.getTel(), etudiant.getNiveau().getNiveauInitialAr(), etudiant.getCtegore_niveau().getCategore_niveau_ar(), etudiant.getAdress(), "", etudiant.getDateBirth(), "prenofr", "nomfr", etudiant.getPrenom(),
                etudiant.getNom(), etudiant.getCodeBare(), etudiant.getMatricule(), etudiant.getId()});
//            if (etudiant.getImage() != null) {
//                byte[] imag = etudiant.getImage();
//                
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
//                lab_img_etu.setIcon(imageIcon);
//            } else {
//                lab_img_etu.setIcon(null);
//            }
        }

    }

    public void setInfoCategorNiveauInTab() {
        List<CategoreNiveau> categoreNiveaus = categoreNiveauDAOImpl.findAll();
        DefaultTableModel model = (DefaultTableModel) tab_level.getModel();
        model.setRowCount(0);
        for (CategoreNiveau categoreNiveau : categoreNiveaus) {
            model.addRow(new Object[]{categoreNiveau.getCategore_niveau_fr(), categoreNiveau.getCategore_niveau_ar(), categoreNiveau.getId()});
        }
    }

    public void setInfoNiveauInTab(int id_category) {
        List<NiveauEtude> niveauEtudes = niveauEtudeDAOImpl.findAll();
        DefaultTableModel model = (DefaultTableModel) tab_class.getModel();
        model.setRowCount(0);
        for (NiveauEtude niveauEtude : niveauEtudes) {
            if (niveauEtude.getCategore_niveau_id() == id_category) {
                model.addRow(new Object[]{niveauEtude.getNiveauInitialAr(), niveauEtude.getNiveauInitialFr(), niveauEtude.getId()});
            }
        }
    }

    public void setInfoMatiereInTab(int id_category, int id_niveau) {
        List<Matiere> matieres = matiereDAOImpl.findAll();
        DefaultTableModel model = (DefaultTableModel) tab_matier.getModel();
        model.setRowCount(0);
        for (Matiere matiere : matieres) {
            if (matiere.getCategoreNiveau().getId() == id_category && matiere.getNiveau().getId() == id_niveau) {
                String nom_prof = "/";
                if (matiere.getEnseignant() != null) {
                    nom_prof = matiere.getEnseignant().getNomAr() + "-" + matiere.getEnseignant().getPrenomAr();
                }
                model.addRow(new Object[]{nom_prof, matiere.getMatiereEtdFr(), matiere.getMatiereEtdAr(), matiere.getId()});
            }
        }

    }

    public void setInfoSeanceMatierInTable() {
        List<Seance_Matiere> seance_Matieres = seanceMatiereDAOImpl.findAll();
        DefaultTableModel model = (DefaultTableModel) tab_seance.getModel();
        model.setRowCount(0);
        List<Seance> seances = sceanceDAOImpl.findAll();
        for (Seance seance : seances) {
            int exist_row = existSeanceMatier(seance.getMatiere().getId());
            Matiere matier = matiereDAOImpl.findById(seance.getMatiere().getId());
            if (exist_row != -1) {
                int nbr_seance = (int) tab_seance.getValueAt(exist_row, 0) + 1;
                tab_seance.setValueAt(nbr_seance, exist_row, 0);
            } else {
                String matiere_ar = matier.getMatiereEtdAr();
                String catego_niveau = categoreNiveauDAOImpl.findById(matier.getCategoreNiveau().getId()).getCategore_niveau_ar();
                String niveau = niveauEtudeDAOImpl.findById(matier.getNiveau().getId()).getNiveauInitialAr();
                String enseignant_name = "/";
///                Enseignant ensignat = enseignantDAOImpl.findById(matier.getEnseignant().getId());
                //  if(ensignat !=null)
                //  enseignant_name = ensignat.getNomAr() + "  " + ensignat.getPrenomAr();

                model.addRow(new Object[]{1, "/", "/", catego_niveau, niveau, matiere_ar, matier.getId()});
            }
        }
//      for (Seance_Matiere seance_Matiere : seance_Matieres) {
//            Matiere matier = seance_Matiere.getMatiere();
//            int exist_row = existSeanceMatier(matier.getId());
//            if (exist_row != -1) {
//                int nbr_seance = (int) tab_seance.getValueAt(exist_row, 0) + 1;
//                tab_seance.setValueAt(nbr_seance, exist_row, 0);
//            } else {
//                int id_seance_matiere = seance_Matiere.getId();
//
//                int id_matier = matier.getId();
//                String matiere_ar = matier.getMatiereEtdAr();
//                String catego_niveau = categoreNiveauDAOImpl.findById(matier.getCategoreNiveau().getId()).getCategore_niveau_ar();
//                String niveau = niveauEtudeDAOImpl.findById(matier.getNiveau().getId()).getNiveauInitialAr();
//                Enseignant ensignat = enseignantDAOImpl.findById(matier.getEnseignant().getId());
//                String enseignant_name = ensignat.getNomAr() + "  " + ensignat.getPrenomAr();
//
//                model.addRow(new Object[]{1, enseignant_name, "/", catego_niveau, niveau, matiere_ar, id_matier, id_seance_matiere});
//
//            }
//        }
    }

    public int existSeanceMatier(int id_matier) {
        DefaultTableModel model = (DefaultTableModel) tab_seance.getModel();
        int row = -1;
        for (int i = 0; i < tab_seance.getRowCount(); i++) {
            int col = model.getColumnCount();
            int id_matiere = (int) tab_seance.getValueAt(i, 6);
            if (id_matier == id_matiere) {
                row = i;
            } else {
                row = -1;
            }
        }
        return row;
    }

    public void getSeanceOfMatier() {
        List<Seance> seances = sceanceDAOImpl.findAll();
        int id_matiere = (int) tab_seance.getValueAt(tab_seance.getSelectedRow(), 6);
        for (Seance seance : seances) {
            if (id_matiere == seance.getMatiere().getId()) {
                PanDayHourMatier p = new PanDayHourMatier();
                p.setday(seance.getDay_sceance());
                p.setstartTime(sdf.format(seance.getTimeSeance()));
                p.setfintTime(sdf.format(seance.getFinTime()));
                jPanel14.add(p);
                jPanel14.revalidate();
                jPanel14.repaint();
            }
        }
        jLabel48.setText(tab_seance.getValueAt(tab_seance.getSelectedRow(), 3).toString());
        jLabel46.setText(tab_seance.getValueAt(tab_seance.getSelectedRow(), 4).toString());
        jLabel45.setText(tab_seance.getValueAt(tab_seance.getSelectedRow(), 5).toString());
    }

    public void getMatiereOfToDay2() {

        DefaultTableModel model = (DefaultTableModel) table_seance_to_day.getModel();
        model.setRowCount(0);
        //seance satu
        List<Seance> seances = sceanceDAOImpl.findAll();
    }

    public void getMatiereOfToDay() {
        JOptionPane.showMessageDialog(null, "getMatiereOfToDay");
        String day = getday();
        DefaultTableModel model = (DefaultTableModel) table_seance_to_day.getModel();
        model.setRowCount(0);
        //seance satu
        List<Seance> seances = sceanceDAOImpl.findAll();
        List<Matiere> matieres = new ArrayList<>();
        List<Seance> seances_to_day = new ArrayList<>();
        for (Seance seance : seances) {
            if (seance.getDay_sceance().equals(day)) {
                seances_to_day.add(seance);
                JOptionPane.showMessageDialog(null, "add seance");
            }
        }
        LocalDate dat_seance_ma = LocalDate.now();
        for (Seance seance : seances_to_day) {
            Matiere matiere = matiereDAOImpl.findById(seance.getMatiere().getId());
            Seance_Matiere seance_Matiere = seanceMatiereDAOImpl.getlasSeanceOfToday(day, matiere.getId());

            JOptionPane.showMessageDialog(null, "" + seance_Matiere.getMatiere().getMatiereEtdAr() + "    " + seance_Matiere.isTermine() + "   " + seance_Matiere.getSeance().getDay_sceance());

            if (seance_Matiere.isTermine() && LocalDate.now().isAfter(seance_Matiere.getDate())) { // terminat ==true        
                JOptionPane.showMessageDialog(null, " termint all true  " + seance_Matiere.getId() + "   " + seance_Matiere.isTermine());
                List< Seance> new_seances = sceanceDAOImpl.getSeanceOfMatiere(matiere.getId());
                for (Seance seance1 : new_seances) {

                    if (seance1.getNumSeance() == 1) {
                        dat_seance_ma = LocalDate.now();
                    } else {
                        dat_seance_ma = dat_seance_ma.with(TemporalAdjusters.next(getDayOfWeekFromArabic(seance1.getDay_sceance())));
                        JOptionPane.showMessageDialog(null, getDayOfWeekFromArabic(seance1.getDay_sceance()));
                    }
                    Seance_Matiere seance_Matiere1 = new Seance_Matiere(0, seance1, matiere, false, dat_seance_ma);
                    seanceMatiereDAOImpl.save(seance_Matiere1);// add new seance 
                }
                seance_Matiere = seanceMatiereDAOImpl.getlasSeanceOfToday(day, matiere.getId());
                seance_Matiere.setTermine(true);
                seanceMatiereDAOImpl.update(seance_Matiere);
                model.addRow(new Object[]{seance_Matiere.getSeance().getNumSeance(), seance_Matiere.getMatiere().getMatiereEtdAr(), seance_Matiere.getMatiere().getNiveau().getNiveauInitialAr(), seance_Matiere.getMatiere().getCategoreNiveau().getCategore_niveau_ar(), seance_Matiere.getMatiere().getId(), seance_Matiere.getId()});
            } else {
                if (seance_Matiere.getDate().isEqual(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "else ");
                    seance_Matiere.setTermine(true);
                    seanceMatiereDAOImpl.update(seance_Matiere);
                    model.addRow(new Object[]{seance_Matiere.getSeance().getNumSeance(), seance_Matiere.getMatiere().getMatiereEtdAr(), seance_Matiere.getMatiere().getNiveau().getNiveauInitialAr(), seance_Matiere.getMatiere().getCategoreNiveau().getCategore_niveau_ar(), seance_Matiere.getMatiere().getId(), seance_Matiere.getId()});
                }
            }
        }
//      }  
//        //  List<Matiere> matieres_of_to_day = new ArrayList<>();
//        List< Seance_Matiere> seance_Matieres = seanceMatiereDAOImpl.findAll();
//
//        //    List< Seance_Matiere> seance_Matieres_Today = new ArrayList<>();
//        //  getSeanceMatierNoterminate();     
//        for (Seance_Matiere seance_Matiere : seance_Matieres) {
//            if (seance_Matiere.getSeance().getDay_sceance().equals("السبت")) {
//                    if(seance_Matiere.isTermine()){}// if last seance_matier satu ==true new add seance  ; true 
//                //  matieres_of_to_day.add(seance_Matiere.getMatiere());  // 
//                // seance_Matieres_Today.add(seance_Matiere);
//                int numSeance = seance_Matiere.getSeance().getNumSeance();
//                ///seanceMatiereDAOImpl.getSeanceMatierNoterminate(false, seance_Matiere.getMatiere);
//                model.addRow(new Object[]{numSeance, seance_Matiere.getMatiere().getMatiereEtdAr(), seance_Matiere.getMatiere().getNiveau().getNiveauInitialAr(), seance_Matiere.getMatiere().getCategoreNiveau().getCategore_niveau_ar(), seance_Matiere.getMatiere().getId(), seance_Matiere.getId()});
//            }
//        }
    }

    public List<Seance> getSeanceOfTodayWithInscriptionEtud(List<Seance> Seances, Etudiant etudiant) {
        List<Inscription> inscriptions = inscriptionDAOImpl.findByEtudiantId(etudiant.getId());
        List<Seance> Seance_of_today = new ArrayList<>();
        JOptionPane.showMessageDialog(null, "inscriptions : " + inscriptions.size());
        for (Inscription inscription : inscriptions) { // list inscription of etudaint
            for (Seance seance : Seances) { // list seance o to day
                if (inscription.getMatiere().getId() == seance.getMatiere().getId()) { // etidaint <- inscription + matier (time 1)
                    JOptionPane.showMessageDialog(null, "exist mod : " + seance.getMatiere().getId());
                    Seance_of_today.add(seance); // liste seance etudieé to day
                }
            }
        }
        return Seance_of_today;
    }

    public String getday() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");
        String dayName = today.format(dayFormatter);
        switch (dayName) {
            case "dimanche":
                return "الأحد";
            case "lundi":
                return "الاثنين";
            case "mardi":
                return "الثلاثاء";
            case "mercredi":
                return "الأربعاء";
            case "jeudi":
                return "الخميس";
            case "vendredi":
                return "الجمعة";
            case "samedi":
                return "السبت";
            default:
                throw new AssertionError();
        }

    }
    private static final Map<String, DayOfWeek> arabicDayToDayOfWeekMap = new HashMap<>();

    static {
        arabicDayToDayOfWeekMap.put("الأحد", DayOfWeek.SUNDAY);
        arabicDayToDayOfWeekMap.put("الاثنين", DayOfWeek.MONDAY);
        arabicDayToDayOfWeekMap.put("الثلاثاء", DayOfWeek.TUESDAY);
        arabicDayToDayOfWeekMap.put("الأربعاء", DayOfWeek.WEDNESDAY);
        arabicDayToDayOfWeekMap.put("الخميس", DayOfWeek.THURSDAY);
        arabicDayToDayOfWeekMap.put("الجمعة", DayOfWeek.FRIDAY);
        arabicDayToDayOfWeekMap.put("السبت", DayOfWeek.SATURDAY);
    }

    public static DayOfWeek getDayOfWeekFromArabic(String arabicDayName) {
        return arabicDayToDayOfWeekMap.get(arabicDayName);
    }

    public void setInfoInscriptionInTable(int id_etudiant) {
        List<Inscription> inscriptions = inscriptionDAOImpl.findByEtudiantId(id_etudiant);
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        for (Inscription inscription : inscriptions) {
            String nom_enseignant;
            if (inscription.getMatiere().getEnseignant() == null) {
                nom_enseignant = "/";
            } else {
                nom_enseignant = inscription.getMatiere().getEnseignant().getNomAr() + " " + inscription.getMatiere().getEnseignant().getPrenomAr();
            }

            model.addRow(new Object[]{inscription.getDateInscription(),
                nom_enseignant, "", inscription.getMatiere().getMatiereEtdAr(), inscription.getId()});
        }
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
    }

    public void setInfoInTablePresence() {
        
        try {
          List<Presence>  presences = new PresenceService().getPresenceOfToDay();
            DefaultTableModel model = (DefaultTableModel) tab_presence.getModel();
            for (Presence presence : presences) {
                model.addRow(new Object[]{presence.getDatePresence(), presence.getMatiere().getMatiereEtdAr(),
                    presence.getMatiere().getNiveau().getNiveauInitialAr(),
                    presence.getMatiere().getCategoreNiveau().getCategore_niveau_ar(),
                    presence.getEtudiant().getNom() + " " + presence.getEtudiant().getPrenom(),
                    presence.getEtudiant().getId(), presence.getSeance().getId(), presence.getId()});
            }
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public  void preapareDashboard(){
     List<Etudiant> etudiants= etudiantDAOImpl.findAll();
     List<Enseignant> enseignants=enseignantDAOImpl.findAll();
     
     CardEtudtaint.setData(new Model_card(new javax.swing.ImageIcon(getClass().getResource("/icon/graduation (2).png")),"الـطـلـبـة :"  ,   etudiants.size()+" "   , " "));
     CardEnsigniant.setData(new Model_card(new javax.swing.ImageIcon(getClass().getResource("/icon/businessmen.png")),"الأسـاتذة  :",enseignants.size()+"" , ""));
     CardEmployer.setData(new Model_card(new javax.swing.ImageIcon(getClass().getResource("/icon/employee (1).png")),"الـعـمـال :",00+"" , ""));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser = new datechooser.DateChooser();
        pan_buttom = new javax.swing.JPanel();
        pan_menu = new javax.swing.JPanel();
        customMenu1 = new ui.menufr.customMenu();
        pan_center = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        pan_dashbord = new javax.swing.JPanel();
        scrol_dashbord = new javax.swing.JScrollPane();
        pan_dashbord1 = new javax.swing.JPanel();
        CardEnsigniant = new ui.card.Card();
        CardEtudtaint = new ui.card.Card();
        CardEmployer = new ui.card.Card();
        costumChart1 = new chart.costumChart();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        link_facebook1 = new material.design.buttonRounder();
        link_facebook = new material.design.buttonRounder();
        link_Instgram = new material.design.buttonRounder();
        link_webSite = new material.design.buttonRounder();
        link_location = new material.design.buttonRounder();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        pan_saise = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        textField2 = new material.design.TextField();
        textField1 = new material.design.TextField();
        textField4 = new material.design.TextField();
        textField3 = new material.design.TextField();
        txt_dat_birth = new material.design.TextField();
        textField6 = new material.design.TextField();
        textField8 = new material.design.TextField();
        textField9 = new material.design.TextField();
        textField10 = new material.design.TextField();
        com_catego_niveau = new material.design.Combobox();
        com_niveau = new material.design.Combobox();
        jPanel3 = new javax.swing.JPanel();
        buttonRounder1 = new material.design.buttonRounder();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        combobox1 = new material.design.Combobox();
        pan_catego = new javax.swing.JPanel();
        pan_lang = new javax.swing.JPanel();
        combobox2 = new material.design.Combobox();
        Groupe4 = new material.design.Combobox();
        Groupe5 = new material.design.Combobox();
        pan_formation = new javax.swing.JPanel();
        الدورات = new material.design.Combobox();
        Groupe3 = new material.design.Combobox();
        pan_cour = new javax.swing.JPanel();
        combobox3 = new material.design.Combobox();
        combobox5 = new material.design.Combobox();
        jLabel7 = new javax.swing.JLabel();
        tableScrollButton1 = new ui.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pan_view = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tableScrollButton2 = new ui.table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_etudiant_view = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        tableScrollButton3 = new ui.table.TableScrollButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        searchText2 = new material.design.SearchText();
        buttonRounder8 = new material.design.buttonRounder();
        jLabel11 = new javax.swing.JLabel();
        buttonRounder18 = new material.design.buttonRounder();
        buttonRounder20 = new material.design.buttonRounder();
        buttonRounder21 = new material.design.buttonRounder();
        lab_img_etu = new javax.swing.JLabel();
        buttonRounder32 = new material.design.buttonRounder();
        buttonRounder37 = new material.design.buttonRounder();
        buttonRounder38 = new material.design.buttonRounder();
        pan_config = new javax.swing.JPanel();
        pan_conf_inter = new javax.swing.JPanel();
        pan_level = new javax.swing.JPanel();
        tableScrollButton4 = new ui.table.TableScrollButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab_level = new javax.swing.JTable();
        tableScrollButton5 = new ui.table.TableScrollButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tab_class = new javax.swing.JTable();
        searchText4 = new material.design.SearchText();
        buttonRounder22 = new material.design.buttonRounder();
        buttonRounder23 = new material.design.buttonRounder();
        buttonRounder24 = new material.design.buttonRounder();
        jLabel32 = new javax.swing.JLabel();
        buttonRounder25 = new material.design.buttonRounder();
        buttonRounder26 = new material.design.buttonRounder();
        buttonRounder27 = new material.design.buttonRounder();
        tableScrollButton10 = new ui.table.TableScrollButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tab_matier = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        buttonRounder34 = new material.design.buttonRounder();
        buttonRounder35 = new material.design.buttonRounder();
        buttonRounder36 = new material.design.buttonRounder();
        pan_cour_formati_lang_ = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        pan_cours_conf = new javax.swing.JPanel();
        tableScrollButton6 = new ui.table.TableScrollButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tab_seance = new javax.swing.JTable();
        searchText5 = new material.design.SearchText();
        jLabel37 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        pan_formation_conf = new javax.swing.JPanel();
        tableScrollButton7 = new ui.table.TableScrollButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tab_formation = new javax.swing.JTable();
        pan_lang_conf = new javax.swing.JPanel();
        tableScrollButton8 = new ui.table.TableScrollButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tab_lang = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        buttonRounder28 = new material.design.buttonRounder();
        buttonRounder16 = new material.design.buttonRounder();
        buttonRounder11 = new material.design.buttonRounder();
        buttonRounder12 = new material.design.buttonRounder();
        btn_serv_conf = new material.design.buttonRounder();
        btn_level_conf = new material.design.buttonRounder();
        pan_presence = new javax.swing.JPanel();
        txt_cod_barr_presence = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        tableScrollButton11 = new ui.table.TableScrollButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        table_seance_to_day = new javax.swing.JTable();
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
        jButton8 = new javax.swing.JButton();
        tableScrollButton12 = new ui.table.TableScrollButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tab_presence = new javax.swing.JTable();
        searchText6 = new material.design.SearchText();
        buttonRounder33 = new material.design.buttonRounder();
        buttonRounder2 = new material.design.buttonRounder();
        buttonRounder3 = new material.design.buttonRounder();
        buttonRounder4 = new material.design.buttonRounder();
        pan_ensign = new javax.swing.JPanel();
        pan_prof = new javax.swing.JPanel();
        tableScrollButton9 = new ui.table.TableScrollButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tab_prof = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        first_name_client_txt7 = new javax.swing.JTextField();
        first_name_client_txt8 = new javax.swing.JTextField();
        first_name_client_txt9 = new javax.swing.JTextField();
        first_name_client_txt10 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        first_name_client_txt11 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        first_name_client_txt12 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        first_name_client_txt13 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        combobox12 = new material.design.Combobox();
        jSeparator2 = new javax.swing.JSeparator();
        first_name_client_txt14 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        first_name_client_txt15 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        first_name_client_txt16 = new javax.swing.JTextField();
        first_name_client_txt17 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        النوع = new material.design.Combobox();
        first_name_client_txt18 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        buttonRounder17 = new material.design.buttonRounder();
        buttonRounder19 = new material.design.buttonRounder();
        jLabel30 = new javax.swing.JLabel();
        searchText3 = new material.design.SearchText();
        buttonRounder13 = new material.design.buttonRounder();
        buttonRounder14 = new material.design.buttonRounder();
        buttonRounder15 = new material.design.buttonRounder();
        btn_prof_conf = new material.design.buttonRounder();

        dateChooser.setTextRefernce(txt_dat_birth);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pan_buttom.setPreferredSize(new java.awt.Dimension(733, 20));

        javax.swing.GroupLayout pan_buttomLayout = new javax.swing.GroupLayout(pan_buttom);
        pan_buttom.setLayout(pan_buttomLayout);
        pan_buttomLayout.setHorizontalGroup(
            pan_buttomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        pan_buttomLayout.setVerticalGroup(
            pan_buttomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(pan_buttom, java.awt.BorderLayout.PAGE_END);

        pan_menu.setPreferredSize(new java.awt.Dimension(180, 569));
        pan_menu.setLayout(new java.awt.CardLayout());

        customMenu1.setMinimumSize(new java.awt.Dimension(150, 680));
        customMenu1.setPreferredSize(new java.awt.Dimension(150, 680));
        pan_menu.add(customMenu1, "card2");

        getContentPane().add(pan_menu, java.awt.BorderLayout.LINE_END);

        pan_center.setBackground(new java.awt.Color(255, 255, 255));
        pan_center.setMinimumSize(new java.awt.Dimension(1200, 334));
        pan_center.setLayout(new java.awt.CardLayout());

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));
        Dashboard.setLayout(new java.awt.CardLayout());

        pan_dashbord.setBackground(new java.awt.Color(255, 255, 255));
        pan_dashbord.setLayout(new java.awt.CardLayout());

        scrol_dashbord.setBackground(new java.awt.Color(255, 255, 255));
        scrol_dashbord.setBorder(null);
        scrol_dashbord.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrol_dashbord.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrol_dashbord.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pan_dashbord1.setBackground(new java.awt.Color(255, 255, 255));
        pan_dashbord1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CardEnsigniant.setColor1(new java.awt.Color(255, 204, 204));
        CardEnsigniant.setColor2(new java.awt.Color(153, 0, 153));
        pan_dashbord1.add(CardEnsigniant, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 260, 140));

        CardEtudtaint.setColor1(new java.awt.Color(204, 204, 255));
        CardEtudtaint.setColor2(new java.awt.Color(51, 102, 255));
        pan_dashbord1.add(CardEtudtaint, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 270, 140));

        CardEmployer.setColor1(new java.awt.Color(204, 204, 255));
        CardEmployer.setColor2(new java.awt.Color(102, 0, 153));
        pan_dashbord1.add(CardEmployer, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 250, 140));
        pan_dashbord1.add(costumChart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 840, 355));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Nihal Scholl", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Rage Italic", 3, 30), new java.awt.Color(153, 153, 153))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        pan_dashbord1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 650, -1, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Nihal Scholl", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Rage Italic", 3, 30), new java.awt.Color(153, 153, 153))); // NOI18N

        link_facebook1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        link_facebook1.setText("Tel :");
        link_facebook1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_facebook1ActionPerformed(evt);
            }
        });

        link_facebook.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        link_facebook.setForeground(new java.awt.Color(0, 51, 204));
        link_facebook.setText("Facebook.com");
        link_facebook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_facebookActionPerformed(evt);
            }
        });

        link_Instgram.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        link_Instgram.setForeground(new java.awt.Color(204, 0, 51));
        link_Instgram.setText("Instagram");
        link_Instgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_InstgramActionPerformed(evt);
            }
        });

        link_webSite.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        link_webSite.setText("Web Site");
        link_webSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_webSiteActionPerformed(evt);
            }
        });

        link_location.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        link_location.setText("Maps");
        link_location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_locationActionPerformed(evt);
            }
        });

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/facebook.png"))); // NOI18N

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/carte.png"))); // NOI18N

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lien-web.png"))); // NOI18N

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/instagram.png"))); // NOI18N

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/telephone.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(link_location, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(link_facebook1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(link_facebook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(link_Instgram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(link_webSite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(link_facebook1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(link_facebook, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(5, 5, 5)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(link_Instgram, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(link_webSite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(link_location, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pan_dashbord1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 240));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        pan_dashbord1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        scrol_dashbord.setViewportView(pan_dashbord1);

        pan_dashbord.add(scrol_dashbord, "card2");

        Dashboard.add(pan_dashbord, "card5");

        pan_center.add(Dashboard, "card5");

        pan_saise.setBackground(new java.awt.Color(255, 255, 255));
        pan_saise.setPreferredSize(new java.awt.Dimension(64, 46));
        pan_saise.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "المعلومات الشخصية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField2.setToolTipText("");
        textField2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        textField2.setLabelText("الاســم");
        jPanel1.add(textField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 28, 146, 40));

        textField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField1.setToolTipText("");
        textField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        textField1.setLabelText("الــلـقـب");
        jPanel1.add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 28, 158, 40));

        textField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField4.setToolTipText("");
        textField4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        textField4.setLabelText("Prénom");
        textField4.setLangue(1);
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });
        jPanel1.add(textField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 150, -1));

        textField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField3.setToolTipText("");
        textField3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        textField3.setLabelText("Nom");
        textField3.setLangue(1);
        jPanel1.add(textField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 165, -1));

        txt_dat_birth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dat_birth.setToolTipText("");
        txt_dat_birth.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txt_dat_birth.setLabelText("تاريخ الميلاد");
        txt_dat_birth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dat_birthActionPerformed(evt);
            }
        });
        jPanel1.add(txt_dat_birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 146, -1));

        textField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField6.setToolTipText("");
        textField6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        textField6.setLabelText("مكان الميلاد");
        jPanel1.add(textField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 158, -1));

        textField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField8.setToolTipText("");
        textField8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        textField8.setLabelText("الـعنوان");
        textField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField8ActionPerformed(evt);
            }
        });
        jPanel1.add(textField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 327, -1));

        textField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField9.setToolTipText("");
        textField9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        textField9.setLabelText("اسم الولي ");
        textField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField9ActionPerformed(evt);
            }
        });
        jPanel1.add(textField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 146, -1));

        textField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField10.setToolTipText("");
        textField10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        textField10.setLabelText("رقم هاتف الولي ");
        textField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField10ActionPerformed(evt);
            }
        });
        jPanel1.add(textField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 157, -1));

        com_catego_niveau.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        com_catego_niveau.setLabeText("المستوى الدراسي");
        com_catego_niveau.setPreferredSize(new java.awt.Dimension(64, 46));
        com_catego_niveau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                com_catego_niveauItemStateChanged(evt);
            }
        });
        com_catego_niveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_catego_niveauActionPerformed(evt);
            }
        });
        jPanel1.add(com_catego_niveau, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 146, -1));

        com_niveau.setLabeText("القـسـم");
        jPanel1.add(com_niveau, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 140, 180, 50));

        pan_saise.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 680, 250));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "المعلومات الشخصية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        buttonRounder1.setBackground(new java.awt.Color(224, 175, 175));
        buttonRounder1.setText("تحميل الصورة");
        buttonRounder1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonRounder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder1ActionPerformed(evt);
            }
        });

        jLabel4.setText("الصورة من النوع : PNG , JPEG , BMP");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png"))); // NOI18N
        jLabel6.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRounder1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(buttonRounder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pan_saise.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 330, 150));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "المعلومات الشخصية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Tel :");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 27, 40, 20));

        jLabel2.setText("Gmail :");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, 25));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 220, 31));

        jTextField6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 220, 31));

        pan_saise.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 330, 110));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "المعلومات الشخصية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("تاريخ التسجيل");

        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel5.setText("CodeBare");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pan_saise.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 680, 60));
        jPanel5.getAccessibleContext().setAccessibleName("");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "المعلومات الشخصية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 51, 204));

        combobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cours", "Langues", "Formation", "" }));
        combobox1.setSelectedIndex(-1);
        combobox1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        combobox1.setLabeText("الــنــوع");
        combobox1.setLightWeightPopupEnabled(false);
        combobox1.setPreferredSize(new java.awt.Dimension(64, 46));
        combobox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox1ActionPerformed(evt);
            }
        });

        pan_catego.setBackground(new java.awt.Color(255, 255, 255));
        pan_catego.setLayout(new java.awt.CardLayout());

        pan_lang.setBackground(new java.awt.Color(255, 255, 255));
        pan_lang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combobox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Français", "Anglais" }));
        combobox2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        combobox2.setLabeText("لــغــات");
        combobox2.setPreferredSize(new java.awt.Dimension(64, 46));
        combobox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox2ActionPerformed(evt);
            }
        });
        pan_lang.add(combobox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 17, 198, 50));

        Groupe4.setLabeText("الفوج");
        pan_lang.add(Groupe4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 21, 198, 50));

        Groupe5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "السبت", "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة" }));
        Groupe5.setSelectedIndex(-1);
        Groupe5.setLabeText("المستوى");
        pan_lang.add(Groupe5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 198, 50));

        pan_catego.add(pan_lang, "card3");

        pan_formation.setBackground(new java.awt.Color(255, 255, 255));
        pan_formation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        الدورات.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        الدورات.setLabeText("الدورات التكوينية ");
        الدورات.setPreferredSize(new java.awt.Dimension(64, 46));
        pan_formation.add(الدورات, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 6, 349, -1));

        Groupe3.setLabeText("الــفــوج");
        pan_formation.add(Groupe3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 105, 40));

        pan_catego.add(pan_formation, "card4");

        pan_cour.setBackground(new java.awt.Color(255, 255, 255));
        pan_cour.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combobox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ابتدائي", "متوسط", "ثانوي", "جامعي", "/", " ", " ", " " }));
        combobox3.setSelectedIndex(-1);
        combobox3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        combobox3.setLabeText("المستوى الدراسي ");
        combobox3.setPreferredSize(new java.awt.Dimension(64, 46));
        pan_cour.add(combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 146, -1));

        combobox5.setLabeText("المــواد ");
        pan_cour.add(combobox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 250, 50));
        pan_cour.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 161, 53, 35));

        pan_catego.add(pan_cour, "card2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan_catego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_catego, javax.swing.GroupLayout.PREFERRED_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        pan_saise.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 680, 290));

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

        pan_saise.add(tableScrollButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 460, 280));

        pan_center.add(pan_saise, "card2");

        pan_view.setBackground(new java.awt.Color(255, 255, 255));
        pan_view.setForeground(new java.awt.Color(255, 255, 255));
        pan_view.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "Cours", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel7.setLayout(new java.awt.CardLayout());

        tab_etudiant_view.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tab_etudiant_view.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RenseignementPe", "Gmail", "Tel", "القسم", "المستوى الدراسي", "العنوان", "مكان الميلاد", "تاريخ الميلاد", "prenom", "Nom", "اللقب", "الاسم", "CodeBare", "Matricule", "N°"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, true, true, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_etudiant_view.setSelectionBackground(new java.awt.Color(235, 235, 235));
        tab_etudiant_view.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_etudiant_viewMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_etudiant_view);

        tableScrollButton2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel7.add(tableScrollButton2, "card2");

        pan_view.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 1070, 242));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "Cours", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel8.setLayout(new java.awt.CardLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "تاريخ التسجيل", "الأستاذ", "الفوج", "المادة", "Id_Inscription"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setSelectionBackground(new java.awt.Color(235, 235, 235));
        jScrollPane3.setViewportView(jTable3);

        tableScrollButton3.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel8.add(tableScrollButton3, "card2");

        pan_view.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 1070, 167));

        searchText2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        searchText2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pan_view.add(searchText2, new org.netbeans.lib.awtextra.AbsoluteConstraints(891, 72, 258, 27));

        buttonRounder8.setBackground(new java.awt.Color(51, 204, 0));
        buttonRounder8.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/8.png"))); // NOI18N
        buttonRounder8.setText("طالب جـديـد");
        buttonRounder8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder8ActionPerformed(evt);
            }
        });
        pan_view.add(buttonRounder8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 117, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-imprimante-64.png"))); // NOI18N
        pan_view.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 50, 30));

        buttonRounder18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus - Copie - Copie.png"))); // NOI18N
        buttonRounder18.setToolTipText("Nouvelle Type");
        buttonRounder18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder18ActionPerformed(evt);
            }
        });
        pan_view.add(buttonRounder18, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 30, 40));

        buttonRounder20.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/C_Edit.png"))); // NOI18N
        buttonRounder20.setToolTipText("Annnuler la command");
        buttonRounder20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder20ActionPerformed(evt);
            }
        });
        pan_view.add(buttonRounder20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 40, 40));

        buttonRounder21.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supprimer (1).png"))); // NOI18N
        buttonRounder21.setToolTipText("Annnuler la command");
        buttonRounder21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder21ActionPerformed(evt);
            }
        });
        pan_view.add(buttonRounder21, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 30, 40));

        lab_img_etu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan_view.add(lab_img_etu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 90));

        buttonRounder32.setBackground(new java.awt.Color(102, 102, 255));
        buttonRounder32.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder32.setText("تـعـديــل");
        pan_view.add(buttonRounder32, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 100, 30));

        buttonRounder37.setBackground(new java.awt.Color(41, 195, 195));
        buttonRounder37.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder37.setText("التسجيل في مادة");
        buttonRounder37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder37ActionPerformed(evt);
            }
        });
        pan_view.add(buttonRounder37, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 120, 30));

        buttonRounder38.setBackground(new java.awt.Color(255, 90, 90));
        buttonRounder38.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder38.setText("حذف");
        pan_view.add(buttonRounder38, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 100, 30));

        pan_center.add(pan_view, "card3");

        pan_config.setBackground(new java.awt.Color(255, 255, 255));

        pan_conf_inter.setLayout(new java.awt.CardLayout());

        pan_level.setBackground(new java.awt.Color(255, 255, 255));
        pan_level.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "المستويات الدراسية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 13), new java.awt.Color(204, 204, 204))); // NOI18N

        tableScrollButton4.setLayout(new java.awt.CardLayout());

        tab_level.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "المستوى", "المستوى الدراسي ", "N°"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_level.setSelectionBackground(new java.awt.Color(235, 235, 235));
        tab_level.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_levelMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tab_level);

        tableScrollButton4.add(jScrollPane4, "card2");

        tableScrollButton5.setLayout(new java.awt.CardLayout());

        tab_class.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classse", "القسم", "رقم"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_class.setSelectionBackground(new java.awt.Color(235, 235, 235));
        tab_class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_classMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tab_class);

        tableScrollButton5.add(jScrollPane5, "card2");

        searchText4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        searchText4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        buttonRounder22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus - Copie - Copie.png"))); // NOI18N
        buttonRounder22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder22ActionPerformed(evt);
            }
        });

        buttonRounder23.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/C_Edit.png"))); // NOI18N
        buttonRounder23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder23ActionPerformed(evt);
            }
        });

        buttonRounder24.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supprimer (1).png"))); // NOI18N
        buttonRounder24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder24ActionPerformed(evt);
            }
        });

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-imprimante-64.png"))); // NOI18N

        buttonRounder25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus - Copie - Copie.png"))); // NOI18N
        buttonRounder25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder25ActionPerformed(evt);
            }
        });

        buttonRounder26.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/C_Edit.png"))); // NOI18N
        buttonRounder26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder26ActionPerformed(evt);
            }
        });

        buttonRounder27.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supprimer (1).png"))); // NOI18N
        buttonRounder27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder27ActionPerformed(evt);
            }
        });

        tableScrollButton10.setLayout(new java.awt.CardLayout());

        tab_matier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الأستاذ", "Matiere", "المادة ", "رقم"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_matier.setSelectionBackground(new java.awt.Color(235, 235, 235));
        jScrollPane10.setViewportView(tab_matier);

        tableScrollButton10.add(jScrollPane10, "card2");

        jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel35.setText("jLabel35");
        jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        buttonRounder34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus - Copie - Copie.png"))); // NOI18N
        buttonRounder34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder34ActionPerformed(evt);
            }
        });

        buttonRounder35.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/C_Edit.png"))); // NOI18N
        buttonRounder35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder35ActionPerformed(evt);
            }
        });

        buttonRounder36.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supprimer (1).png"))); // NOI18N
        buttonRounder36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder36ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_levelLayout = new javax.swing.GroupLayout(pan_level);
        pan_level.setLayout(pan_levelLayout);
        pan_levelLayout.setHorizontalGroup(
            pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_levelLayout.createSequentialGroup()
                .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan_levelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pan_levelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonRounder36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(buttonRounder35, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(buttonRounder34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(buttonRounder27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(buttonRounder26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(buttonRounder25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_levelLayout.createSequentialGroup()
                        .addComponent(buttonRounder24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(buttonRounder23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(buttonRounder22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchText4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addComponent(tableScrollButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_levelLayout.createSequentialGroup()
                    .addContainerGap(289, Short.MAX_VALUE)
                    .addComponent(tableScrollButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(435, 435, 435)))
            .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_levelLayout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(tableScrollButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(877, Short.MAX_VALUE)))
        );
        pan_levelLayout.setVerticalGroup(
            pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_levelLayout.createSequentialGroup()
                .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_levelLayout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(332, 332, 332))
                    .addGroup(pan_levelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonRounder27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pan_levelLayout.createSequentialGroup()
                                .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonRounder24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonRounder23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonRounder22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchText4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(tableScrollButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRounder26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRounder25, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRounder36, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRounder35, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRounder34, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)))
                .addComponent(jLabel32)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_levelLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(tableScrollButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(261, Short.MAX_VALUE)))
            .addGroup(pan_levelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_levelLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(tableScrollButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(251, Short.MAX_VALUE)))
        );

        pan_conf_inter.add(pan_level, "card2");

        pan_cour_formati_lang_.setBackground(new java.awt.Color(255, 255, 255));
        pan_cour_formati_lang_.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "الخدمات التعليمية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(204, 204, 204))); // NOI18N

        jButton1.setBackground(new java.awt.Color(186, 186, 186));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("دروس خصوصية Cours");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(186, 186, 186));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("لغات Langue");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(186, 186, 186));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("دورات Formation ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(186, 186, 186));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("أخرى ");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.CardLayout());

        pan_cours_conf.setBackground(new java.awt.Color(255, 255, 255));

        tab_seance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "عدد الحصص ", "الأستاذ", "التوقيت", "المستوى", "القسم", "المادة ", "N°", "id_seance", "id_seance_matiere"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_seance.setSelectionBackground(new java.awt.Color(235, 235, 235));
        tab_seance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_seanceMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tab_seance);

        tableScrollButton6.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        searchText5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        searchText5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("المادة ");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 102, 102));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("القسم ");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("المستوى");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(204, 0, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(204, 0, 51));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(204, 0, 51));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout pan_cours_confLayout = new javax.swing.GroupLayout(pan_cours_conf);
        pan_cours_conf.setLayout(pan_cours_confLayout);
        pan_cours_confLayout.setHorizontalGroup(
            pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_cours_confLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_cours_confLayout.createSequentialGroup()
                        .addGroup(pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchText5, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_cours_confLayout.createSequentialGroup()
                        .addGroup(pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pan_cours_confLayout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel44))
                            .addGroup(pan_cours_confLayout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35))))
            .addGroup(pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_cours_confLayout.createSequentialGroup()
                    .addGap(469, 469, 469)
                    .addComponent(tableScrollButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pan_cours_confLayout.setVerticalGroup(
            pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_cours_confLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(searchText5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341)
                .addGroup(pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(pan_cours_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_cours_confLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(tableScrollButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(222, Short.MAX_VALUE)))
        );

        jPanel9.add(pan_cours_conf, "card2");

        pan_formation_conf.setBackground(new java.awt.Color(255, 255, 255));

        tab_formation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الاستاذ", "Formation  الدورات ", "N°"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_formation.setSelectionBackground(new java.awt.Color(235, 235, 235));
        jScrollPane7.setViewportView(tab_formation);

        tableScrollButton7.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pan_formation_confLayout = new javax.swing.GroupLayout(pan_formation_conf);
        pan_formation_conf.setLayout(pan_formation_confLayout);
        pan_formation_confLayout.setHorizontalGroup(
            pan_formation_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1161, Short.MAX_VALUE)
            .addGroup(pan_formation_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_formation_confLayout.createSequentialGroup()
                    .addGap(469, 469, 469)
                    .addComponent(tableScrollButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(64, Short.MAX_VALUE)))
        );
        pan_formation_confLayout.setVerticalGroup(
            pan_formation_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
            .addGroup(pan_formation_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_formation_confLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(tableScrollButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(222, Short.MAX_VALUE)))
        );

        jPanel9.add(pan_formation_conf, "card3");

        pan_lang_conf.setBackground(new java.awt.Color(255, 255, 255));

        tab_lang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الاستاذ", "اللغة", "N°"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_lang.setSelectionBackground(new java.awt.Color(235, 235, 235));
        jScrollPane8.setViewportView(tab_lang);

        tableScrollButton8.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pan_lang_confLayout = new javax.swing.GroupLayout(pan_lang_conf);
        pan_lang_conf.setLayout(pan_lang_confLayout);
        pan_lang_confLayout.setHorizontalGroup(
            pan_lang_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1161, Short.MAX_VALUE)
            .addGroup(pan_lang_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_lang_confLayout.createSequentialGroup()
                    .addGap(469, 469, 469)
                    .addComponent(tableScrollButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(64, Short.MAX_VALUE)))
        );
        pan_lang_confLayout.setVerticalGroup(
            pan_lang_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
            .addGroup(pan_lang_confLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_lang_confLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(tableScrollButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(222, Short.MAX_VALUE)))
        );

        jPanel9.add(pan_lang_conf, "card4");

        javax.swing.GroupLayout pan_cour_formati_lang_Layout = new javax.swing.GroupLayout(pan_cour_formati_lang_);
        pan_cour_formati_lang_.setLayout(pan_cour_formati_lang_Layout);
        pan_cour_formati_lang_Layout.setHorizontalGroup(
            pan_cour_formati_lang_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_cour_formati_lang_Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pan_cour_formati_lang_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pan_cour_formati_lang_Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton1)))
                .addGap(31, 31, 31))
        );
        pan_cour_formati_lang_Layout.setVerticalGroup(
            pan_cour_formati_lang_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_cour_formati_lang_Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pan_cour_formati_lang_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pan_conf_inter.add(pan_cour_formati_lang_, "card3");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        buttonRounder28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus - Copie - Copie.png"))); // NOI18N
        buttonRounder28.setText("اضافة حصة ");
        buttonRounder28.setToolTipText("Nouvelle Type");
        buttonRounder28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder28ActionPerformed(evt);
            }
        });

        buttonRounder16.setBackground(new java.awt.Color(179, 236, 123));
        buttonRounder16.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/8.png"))); // NOI18N
        buttonRounder16.setText("جديد");

        buttonRounder11.setBackground(new java.awt.Color(255, 153, 153));
        buttonRounder11.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder11.setText("حذف");

        buttonRounder12.setBackground(new java.awt.Color(174, 174, 255));
        buttonRounder12.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder12.setText("معاينة");

        btn_serv_conf.setBackground(new java.awt.Color(174, 174, 255));
        btn_serv_conf.setForeground(new java.awt.Color(255, 255, 255));
        btn_serv_conf.setText("الخدمات الدراسية");
        btn_serv_conf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_serv_confActionPerformed(evt);
            }
        });

        btn_level_conf.setBackground(new java.awt.Color(179, 236, 123));
        btn_level_conf.setForeground(new java.awt.Color(255, 255, 255));
        btn_level_conf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/8.png"))); // NOI18N
        btn_level_conf.setText("المستويات الدراسية");
        btn_level_conf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_level_confActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRounder28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRounder16, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRounder11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRounder12, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btn_serv_conf, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_level_conf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRounder28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRounder16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRounder11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRounder12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_serv_conf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_level_conf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pan_configLayout = new javax.swing.GroupLayout(pan_config);
        pan_config.setLayout(pan_configLayout);
        pan_configLayout.setHorizontalGroup(
            pan_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_configLayout.createSequentialGroup()
                .addGroup(pan_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pan_configLayout.createSequentialGroup()
                        .addComponent(pan_conf_inter, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pan_configLayout.setVerticalGroup(
            pan_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_configLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_conf_inter, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pan_center.add(pan_config, "card4");

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
        pan_presence.add(txt_cod_barr_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 360, 31));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pan_presence.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 590, 125, -1));

        jButton7.setText("payement par moin");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        pan_presence.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, -1, 30));

        table_seance_to_day.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "رقم الحصة", "المادة ", "القسم", "المستوى", "N", "id_saence_Matier"
            }
        ));
        jScrollPane11.setViewportView(table_seance_to_day);

        tableScrollButton11.add(jScrollPane11, java.awt.BorderLayout.CENTER);

        pan_presence.add(tableScrollButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 15, 450, 290));

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("اللقب");
        pan_presence.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, -1, -1));

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("الاسم");
        pan_presence.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 120, -1, 25));

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(102, 102, 102));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("المستوى ");
        pan_presence.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 160, -1, -1));

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("القسم ");
        pan_presence.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, -1, -1));

        lab_category_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_category_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_category_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_category_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_category_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 160, 136, 24));

        lab_niveau_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_niveau_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_niveau_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_niveau_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_niveau_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 136, 24));

        lab_nam_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_nam_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_nam_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_nam_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_nam_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 120, 136, 25));

        lab_prenom_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_prenom_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_prenom_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_prenom_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_prenom_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 120, 136, 26));

        lab_imag_etud_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_imag_etud_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_imag_etud_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 109, 104));

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 102));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("المادة");
        pan_presence.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 200, 40, -1));

        lab_matiere_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_matiere_presence.setForeground(new java.awt.Color(0, 0, 204));
        lab_matiere_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_matiere_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_matiere_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 200, 136, 24));

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(102, 102, 102));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText("رقم الحصة");
        pan_presence.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, -1, 24));

        lab_num_seance_presence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_num_seance_presence.setForeground(new java.awt.Color(204, 0, 0));
        lab_num_seance_presence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_num_seance_presence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pan_presence.add(lab_num_seance_presence, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 200, 70, 24));
        pan_presence.add(lab_icon_chech_cross, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 72, 64));

        jButton8.setText("payemenrt par seance ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        pan_presence.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 600, 140, -1));

        tab_presence.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "التوقيت", "المادة", "القسم", "المستوى", "الاسم و اللقب", "Id_etud", "Id_seance", "id_presence"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tab_presence);

        tableScrollButton12.add(jScrollPane12, java.awt.BorderLayout.CENTER);

        pan_presence.add(tableScrollButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 1010, 250));

        searchText6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        searchText6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pan_presence.add(searchText6, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 290, 258, 27));

        buttonRounder33.setBackground(new java.awt.Color(153, 0, 153));
        buttonRounder33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder33ActionPerformed(evt);
            }
        });
        pan_presence.add(buttonRounder33, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 290, 30, 30));

        buttonRounder2.setBackground(new java.awt.Color(0, 153, 153));
        buttonRounder2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder2.setText("checkCard");
        buttonRounder2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonRounder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder2ActionPerformed(evt);
            }
        });
        pan_presence.add(buttonRounder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 120, 30));

        buttonRounder3.setBackground(new java.awt.Color(255, 158, 13));
        buttonRounder3.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder3.setText("الدفع");
        buttonRounder3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pan_presence.add(buttonRounder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 120, 30));

        buttonRounder4.setBackground(new java.awt.Color(153, 153, 255));
        buttonRounder4.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder4.setText("معاينة");
        buttonRounder4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pan_presence.add(buttonRounder4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 110, 30));

        pan_center.add(pan_presence, "card6");

        pan_ensign.setBackground(new java.awt.Color(255, 255, 255));

        pan_prof.setBackground(new java.awt.Color(255, 255, 255));
        pan_prof.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "الأسـاتـذة", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        tab_prof.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "تاريخ الانضمام", "رقم التعريف", "الشهادة", "Gmail", "Tel", "التخصص", "Prenom", "Nom", "لقب الأستاذ", "اسم الأستاذ", "N°"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_prof.setSelectionBackground(new java.awt.Color(235, 235, 235));
        jScrollPane9.setViewportView(tab_prof);

        tableScrollButton9.add(jScrollPane9, java.awt.BorderLayout.CENTER);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("اسم الأستاذ :");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, 74, 25));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("لقب الأستاذ :");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 64, 25));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("تاريخ الميلاد :");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 74, 26));

        first_name_client_txt7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt7.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt7.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt7KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 150, -1));

        first_name_client_txt8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt8.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt8.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt8KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt8, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 142, -1));

        first_name_client_txt9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt9.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt9.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first_name_client_txt9ActionPerformed(evt);
            }
        });
        first_name_client_txt9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt9KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 136, -1));

        first_name_client_txt10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt10.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt10.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first_name_client_txt10ActionPerformed(evt);
            }
        });
        first_name_client_txt10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt10KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt10, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 142, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("مكان الميلاد :");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, -1, 26));

        first_name_client_txt11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt11.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt11.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first_name_client_txt11ActionPerformed(evt);
            }
        });
        first_name_client_txt11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt11KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt11, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 360, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("رقم التعريف");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 60, 26));

        first_name_client_txt12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt12.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt12.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first_name_client_txt12ActionPerformed(evt);
            }
        });
        first_name_client_txt12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt12KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 220, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Gmail");
        jPanel10.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 40, 10));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Tel");
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 20, 30));

        first_name_client_txt13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt13.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt13.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first_name_client_txt13ActionPerformed(evt);
            }
        });
        first_name_client_txt13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt13KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 220, -1));

        jLabel24.setBackground(new java.awt.Color(246, 246, 246));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png"))); // NOI18N
        jLabel24.setOpaque(true);
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 100, 80));

        combobox12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        combobox12.setLabeText("الشــهـادة");
        combobox12.setPreferredSize(new java.awt.Dimension(64, 46));
        jPanel10.add(combobox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 280, 20));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel10.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 0, 240));

        first_name_client_txt14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt14.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt14.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt14KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt14, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 136, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("العنوان :");
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 80, 60, 26));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel10.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 10, 230));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel10.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 10, 240));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("الاخـتصـاص :");
        jPanel10.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 64, 30));

        first_name_client_txt15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt15.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt15.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt15KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt15, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 280, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("لقب الأستاذ :");
        jPanel10.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 64, 25));

        first_name_client_txt16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt16.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt16.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt16KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt16, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 136, -1));

        first_name_client_txt17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt17.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt17.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt17KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 280, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("المادة :");
        jPanel10.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 64, 30));

        النوع.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        النوع.setLabeText("النــوع");
        النوع.setPreferredSize(new java.awt.Dimension(64, 46));
        jPanel10.add(النوع, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 150, 40));

        first_name_client_txt18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        first_name_client_txt18.setForeground(new java.awt.Color(204, 204, 204));
        first_name_client_txt18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        first_name_client_txt18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        first_name_client_txt18.setPreferredSize(new java.awt.Dimension(100, 30));
        first_name_client_txt18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                first_name_client_txt18KeyPressed(evt);
            }
        });
        jPanel10.add(first_name_client_txt18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 280, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("تاريخ الانضمام :");
        jPanel10.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, -1, 30));

        buttonRounder17.setBackground(new java.awt.Color(255, 153, 153));
        buttonRounder17.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder17.setText("إلغاء");
        jPanel10.add(buttonRounder17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 100, 30));

        buttonRounder19.setBackground(new java.awt.Color(179, 236, 123));
        buttonRounder19.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/8.png"))); // NOI18N
        buttonRounder19.setText("حفظ");
        jPanel10.add(buttonRounder19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 90, 30));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-imprimante-64.png"))); // NOI18N
        jLabel30.setToolTipText("طباعة معلومات الأستاذ");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 54, 55));

        searchText3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        searchText3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        buttonRounder13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus - Copie - Copie.png"))); // NOI18N
        buttonRounder13.setToolTipText("Nouvelle Type");
        buttonRounder13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder13ActionPerformed(evt);
            }
        });

        buttonRounder14.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/C_Edit.png"))); // NOI18N
        buttonRounder14.setToolTipText("Annnuler la command");
        buttonRounder14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder14ActionPerformed(evt);
            }
        });

        buttonRounder15.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supprimer (1).png"))); // NOI18N
        buttonRounder15.setToolTipText("Annnuler la command");
        buttonRounder15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_profLayout = new javax.swing.GroupLayout(pan_prof);
        pan_prof.setLayout(pan_profLayout);
        pan_profLayout.setHorizontalGroup(
            pan_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_profLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_profLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(buttonRounder15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(buttonRounder14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRounder13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchText3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(pan_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_profLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(tableScrollButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );
        pan_profLayout.setVerticalGroup(
            pan_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_profLayout.createSequentialGroup()
                .addGroup(pan_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pan_profLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(searchText3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_profLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(buttonRounder13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_profLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pan_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonRounder15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRounder14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pan_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_profLayout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(tableScrollButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(303, Short.MAX_VALUE)))
        );

        btn_prof_conf.setBackground(new java.awt.Color(255, 153, 153));
        btn_prof_conf.setForeground(new java.awt.Color(255, 255, 255));
        btn_prof_conf.setText("أستاذ جديد");
        btn_prof_conf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prof_confActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_ensignLayout = new javax.swing.GroupLayout(pan_ensign);
        pan_ensign.setLayout(pan_ensignLayout);
        pan_ensignLayout.setHorizontalGroup(
            pan_ensignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_ensignLayout.createSequentialGroup()
                .addContainerGap(823, Short.MAX_VALUE)
                .addComponent(btn_prof_conf, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257))
            .addGroup(pan_ensignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_ensignLayout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(pan_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        pan_ensignLayout.setVerticalGroup(
            pan_ensignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_ensignLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_prof_conf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(665, Short.MAX_VALUE))
            .addGroup(pan_ensignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pan_ensignLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pan_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pan_center.add(pan_ensign, "card7");

        getContentPane().add(pan_center, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combobox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combobox2ActionPerformed

    private void combobox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox1ActionPerformed
        if (combobox1.getSelectedItem().toString().equals("Cours")) {
            setForm(pan_catego, pan_cour);
        } else {
            if (combobox1.getSelectedItem().toString().equals("Formation")) {
                setForm(pan_catego, pan_formation);
            } else {
                setForm(pan_catego, pan_lang);
            }
        }
    }//GEN-LAST:event_combobox1ActionPerformed

    private void buttonRounder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder1ActionPerformed
        PlatformImpl.startup(() -> {
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(file.getPath()));
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
    }//GEN-LAST:event_buttonRounder1ActionPerformed

    private void textField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField10ActionPerformed

    private void textField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField9ActionPerformed

    }//GEN-LAST:event_textField9ActionPerformed

    private void textField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField8ActionPerformed

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setForm(jPanel9, pan_lang_conf);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setForm(jPanel9, pan_cours_conf);
        setInfoSeanceMatierInTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        pan_formation_conf.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_level_confActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_level_confActionPerformed
        setForm(pan_conf_inter, pan_level);
        setInfoCategorNiveauInTab();

    }//GEN-LAST:event_btn_level_confActionPerformed

    private void btn_serv_confActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_serv_confActionPerformed
        setForm(pan_conf_inter, pan_cour_formati_lang_);
    }//GEN-LAST:event_btn_serv_confActionPerformed

    private void first_name_client_txt7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt7KeyPressed

    private void first_name_client_txt8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt8KeyPressed

    private void first_name_client_txt9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first_name_client_txt9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt9ActionPerformed

    private void first_name_client_txt9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt9KeyPressed

    private void first_name_client_txt10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first_name_client_txt10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt10ActionPerformed

    private void first_name_client_txt10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt10KeyPressed

    private void first_name_client_txt11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first_name_client_txt11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt11ActionPerformed

    private void first_name_client_txt11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt11KeyPressed

    private void first_name_client_txt12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first_name_client_txt12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt12ActionPerformed

    private void first_name_client_txt12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt12KeyPressed

    private void first_name_client_txt13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first_name_client_txt13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt13ActionPerformed

    private void first_name_client_txt13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt13KeyPressed

    private void first_name_client_txt14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt14KeyPressed

    private void first_name_client_txt15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt15KeyPressed

    private void first_name_client_txt16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt16KeyPressed

    private void first_name_client_txt17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt17KeyPressed

    private void first_name_client_txt18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_name_client_txt18KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_first_name_client_txt18KeyPressed

    private void buttonRounder13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder13ActionPerformed
        new AddEnseignantForm(this, true).setVisible(true);
    }//GEN-LAST:event_buttonRounder13ActionPerformed

    private void buttonRounder14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder14ActionPerformed

    }//GEN-LAST:event_buttonRounder14ActionPerformed

    private void buttonRounder15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder15ActionPerformed

    }//GEN-LAST:event_buttonRounder15ActionPerformed

    private void btn_prof_confActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prof_confActionPerformed
        new AddEnseignantForm(this, true).setVisible(true);

    }//GEN-LAST:event_btn_prof_confActionPerformed

    private void txt_dat_birthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dat_birthActionPerformed
        dateChooser.showPopup();
    }//GEN-LAST:event_txt_dat_birthActionPerformed

    private void com_catego_niveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_catego_niveauActionPerformed

    }//GEN-LAST:event_com_catego_niveauActionPerformed

    private void com_catego_niveauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_catego_niveauItemStateChanged

    }//GEN-LAST:event_com_catego_niveauItemStateChanged

    private void link_facebookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_facebookActionPerformed
        // openWebsite(contact.getFacebook());
    }//GEN-LAST:event_link_facebookActionPerformed

    private void link_InstgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_InstgramActionPerformed
        // openWebsite(contact.getInstagram());
    }//GEN-LAST:event_link_InstgramActionPerformed

    private void link_locationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_locationActionPerformed
    }//GEN-LAST:event_link_locationActionPerformed

    private void link_webSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_webSiteActionPerformed
        // openWebsite(contact.getWebSite());
    }//GEN-LAST:event_link_webSiteActionPerformed

    private void link_facebook1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_facebook1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_link_facebook1ActionPerformed

    private void buttonRounder18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder18ActionPerformed
        new AddEtudiantForm(this, true).setVisible(true);
    }//GEN-LAST:event_buttonRounder18ActionPerformed

    private void buttonRounder20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder20ActionPerformed

        if (tab_etudiant_view.getSelectedRow() != -1) {
            int id = (int) tab_etudiant_view.getValueAt(tab_etudiant_view.getSelectedRow(), 14);
            Etudiant etudiant = etudiantDAOImpl.findById(id);
            new ModifyEtudiant(this, true, etudiant).setVisible(true);
//            lab_id.setText(etudiant.getId() + "");
//            txt_Nom_mod.setText(etudiant.getNom());
//            txt_prenom_mod.setText(etudiant.getPrenom());
//            txt_birth_date_mod.setText(etudiant.getDateBirth().format(formatter));
//            txt_adress_mod.setText(etudiant.getAdress());
//            com_catego_modi_etu.setSelectedItem(etudiant.getCtegore_niveau().getCategore_niveau_fr());
//            com_niv_mod_etud.setSelectedItem(etudiant.getNiveau().getNiveauInitialAr());
//            txt_tel_mod.setText(etudiant.getTel());
//            txt_gmail_mod.setText(etudiant.getEmail());
//            if (etudiant.getImage() != null) {
//                byte[] imag = etudiant.getImage();
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
//                jLabel16.setIcon(imageIcon);
//            } else {
//                jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png")));
//            }
//            com_catego_modi_etu.setSelectedItem(etudiant.getCtegore_niveau().getCategore_niveau_ar());
//            com_niv_mod_etud.setSelectedItem(etudiant.getNiveau().getNiveauInitialAr());
        }
    }//GEN-LAST:event_buttonRounder20ActionPerformed

    private void buttonRounder21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRounder21ActionPerformed

    private void buttonRounder8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder8ActionPerformed
        new AddEtudiantForm(this, true);
    }//GEN-LAST:event_buttonRounder8ActionPerformed

    private void buttonRounder22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder22ActionPerformed
        new AddCategoreNiveauForm(this).setVisible(true);
    }//GEN-LAST:event_buttonRounder22ActionPerformed

    private void buttonRounder23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder23ActionPerformed
        if (tab_level.getSelectedRow() != -1) {
            int row = tab_level.getSelectedRow();
            String categore_niveau_ar = tab_level.getValueAt(row, 1).toString();
            String categore_niveau_fr = tab_level.getValueAt(row, 0).toString();
            int id = (int) tab_level.getValueAt(row, 2);
            CategoreNiveau categoreNiveau = new CategoreNiveau(id, categore_niveau_ar, categore_niveau_fr, "");
            new ModifyCategoryNiveau(this, true, categoreNiveau).setVisible(true);
        }
    }//GEN-LAST:event_buttonRounder23ActionPerformed

    private void buttonRounder24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRounder24ActionPerformed

    private void buttonRounder25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder25ActionPerformed
        AddNiveauForm addNiveauForm = new AddNiveauForm(this);
        addNiveauForm.setVisible(true);
    }//GEN-LAST:event_buttonRounder25ActionPerformed

    private void buttonRounder26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder26ActionPerformed
        if (tab_class.getSelectedRow() != -1) {
            int row = tab_class.getSelectedRow();
            String niveau_ar = tab_class.getValueAt(row, 1).toString();
            String niveau_fr = tab_class.getValueAt(row, 0).toString();
            int id = (int) tab_class.getValueAt(row, 2);
            int id_catego = Integer.parseInt(jLabel34.getText());
            NiveauEtude niveauEtude = new NiveauEtude(id, niveau_ar, niveau_fr, "", id_catego);
            CategoreNiveau categoreNiveau = categoreNiveauDAOImpl.findById(id_catego);
            new ModifyNiveauEtude(this, true, niveauEtude, categoreNiveau).setVisible(true);
        }
    }//GEN-LAST:event_buttonRounder26ActionPerformed

    private void buttonRounder27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRounder27ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void buttonRounder28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder28ActionPerformed
        new AddSeanceForm(this, true).setVisible(true);
    }//GEN-LAST:event_buttonRounder28ActionPerformed

    private void tab_levelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_levelMouseClicked
        if (tab_level.getSelectedRow() != -1) {
            setInfoNiveauInTab((int) tab_level.getValueAt(tab_level.getSelectedRow(), 2));
            if (tab_class.getRowCount() != 0) {
                jLabel33.setText((String) tab_level.getValueAt(tab_level.getSelectedRow(), 1));
                jLabel34.setText((int) tab_level.getValueAt(tab_level.getSelectedRow(), 2) + "");
            } else {
                jLabel33.setText("");
                jLabel34.setText("");
            }
        } else {
            DefaultTableModel model = (DefaultTableModel) tab_class.getModel();
            model.setRowCount(0);
        }
    }//GEN-LAST:event_tab_levelMouseClicked

    private void tab_classMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_classMouseClicked
        if (tab_class.getSelectedRow() != -1) {
            int id_ctegory = Integer.parseInt(jLabel34.getText());
            int id_niveau = (int) tab_class.getValueAt(tab_class.getSelectedRow(), 2);

            setInfoMatiereInTab(id_ctegory, id_niveau);
        } else {
            DefaultTableModel model = (DefaultTableModel) tab_matier.getModel();
            model.setRowCount(0);
        }
    }//GEN-LAST:event_tab_classMouseClicked

    private void tab_etudiant_viewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_etudiant_viewMouseClicked
        if (tab_etudiant_view.getSelectedRow() != -1) {
            int id = (int) tab_etudiant_view.getValueAt(tab_etudiant_view.getSelectedRow(), 14);
            Etudiant etudiant = etudiantDAOImpl.findById(id);
            if (etudiant.getImage() != null) {
                byte[] imag = etudiant.getImage();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
                lab_img_etu.setIcon(imageIcon);
            } else {
                lab_img_etu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png")));
            }

            setInfoInscriptionInTable(etudiant.getId());

        }
    }//GEN-LAST:event_tab_etudiant_viewMouseClicked

    private void tab_seanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_seanceMouseClicked
        jPanel14.removeAll();
        jPanel14.revalidate();
        jPanel14.repaint();
        if (tab_seance.getSelectedRow() != -1) {
            getSeanceOfMatier();
        }

        //  System.out.println(" Component : "+jPanel14.getComponents().length);
    }//GEN-LAST:event_tab_seanceMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

//        //matier of to day 
//        lab_icon_chech_cross.setIcon(null);
//        boolean exist_inscription = false;
//        String codbar = txt_cod_barr_presence.getText();
//        Etudiant etudiant = etudiantDAOImpl.getEtudiantByCodbar(codbar);// matier of today        
//        List<Inscription> inscriptions = inscriptionDAOImpl.findByEtudiantId(etudiant.getId());
//        lab_nam_presence.setText(etudiant.getNom());
//        lab_prenom_presence.setText(etudiant.getPrenom());
//        lab_category_presence.setText(etudiant.getCtegore_niveau().getCategore_niveau_ar());
//        lab_niveau_presence.setText(etudiant.getNiveau().getNiveauInitialAr());
//        if (etudiant.getImage() != null) {
//            byte[] imag = etudiant.getImage();
//            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lab_img_etu.getWidth(), lab_img_etu.getHeight(), Image.SCALE_SMOOTH));
//            lab_imag_etud_presence.setIcon(imageIcon);
//        } else {
//            lab_imag_etud_presence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diplome (4).png")));
//        }
//
//        for (Inscription inscription : inscriptions) {
//            for (int i = 0; i < table_seance_to_day.getRowCount(); i++) { // trie tabl 1 matier with time          
//                if (inscription.getMatiere().getId() == (int) table_seance_to_day.getValueAt(i, 4)) { // etidaint <- inscription + matier (time 1)
//                    Seance_Matiere seance_Matiere_cour = seanceMatiereDAOImpl.findById((int) table_seance_to_day.getValueAt(i, 5));
//                    if (seance_Matiere_cour.getSeance().getFinTime().isAfter(LocalTime.now())) {
//                        JOptionPane.showMessageDialog(null, " inscription etudiant --->test Time");
//                        lab_matiere_presence.setText(inscription.getMatiere().getMatiereEtdAr());
//                        lab_num_seance_presence.setText(seance_Matiere_cour.getSeance().getNumSeance() + "");
//                        boolean pyee = testPyementOfEtudiantSeance(etudiant, inscription.getMatiere(), seance_Matiere_cour);
//
//                        if (pyee) {
//                            JOptionPane.showMessageDialog(null, " Payeeeeeeeeeeeeeeee\n" + "seanse :" + seance_Matiere_cour.getId() + seance_Matiere_cour.getMatiere().getMatiereEtdAr() + "payée");
//                            lab_icon_chech_cross.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check-mark.png")));
//                            //  Prese();
//                            if (presenceDAOImpl.getPresenceOetudiantInSeanceMatiere(etudiant, seance_Matiere_cour) != null) {
//
//                                JOptionPane.showMessageDialog(null, "l'etudiant :" + etudiant.getNom() + " " + etudiant.getPrenom() + " a une presence  ce jour");
//                            } else {
//                                Presence presence = new Presence(0, etudiant, inscription.getMatiere(), seance_Matiere_cour, LocalDate.now());
//                                presenceDAOImpl.save(presence);
//                            }
//                        } else {  // iscri mais no payee    
//                            formPayement = new FormPayement(this, true, etudiant, seance_Matiere_cour);
//                            lab_icon_chech_cross.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cross.png")));
//                            JOptionPane.showMessageDialog(null, "Nooooooooooooo  Payeeeeeeeeeeeeeeee\n" + "seanse :" + seance_Matiere_cour.getSeance().getNumSeance() + seance_Matiere_cour.getMatiere().getMatiereEtdAr() + " non payee ");
//                            Matiere matiere = seance_Matiere_cour.getMatiere();
//                            double prix = matiere.getPrix();
//
//                            Payement payement = new Payement(0, etudiant, matiere, seance_Matiere_cour.getSeance(), "NO payee", 0.0, 0.0, prix, LocalDate.now());
//                            payementDAOImpl.save(payement);
//
//                            Presence presence = new Presence(0, etudiant, inscription.getMatiere(), seance_Matiere_cour, LocalDate.now());
//                            presenceDAOImpl.save(presence);
//
//                        }
//                        exist_inscription = true;
//
//                    } else {
//                        JOptionPane.showMessageDialog(null, "لقد تم انتهاء الحصة على الساعة  :  " + seance_Matiere_cour.getSeance().getFinTime());
//                    }
//
//                }
//            }
//            if (!exist_inscription) {
//                JOptionPane.showMessageDialog(null, " pas inscption avec matiere of today ");
//            }
//        }
        //  Payement payement = payementDAOImpl.findById(1);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        PayemmentParMoin payemmentParMoin = new PayemmentParMoin(this, true);
        payemmentParMoin.setVisible(true);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_cod_barr_presenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cod_barr_presenceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cod_barr_presenceActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        formPayement.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void buttonRounder33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder33ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tab_presence.getModel();
        model.setRowCount(0);
        List<Presence> presences = presenceDAOImpl.getPresenceEtudiantToDay(LocalDate.now());
        for (Presence presence : presences) {
            model.addRow(new Object[]{presence.getDatePresence(), presence.getMatiere().getMatiereEtdAr(), presence.getEtudiant().getNiveau().getNiveauInitialAr(), presence.getEtudiant().getCtegore_niveau().getCategore_niveau_ar(),
                presence.getEtudiant().getNom() + "  " + presence.getEtudiant().getPrenom()});
        }
    }//GEN-LAST:event_buttonRounder33ActionPerformed

    private void buttonRounder34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder34ActionPerformed
        new AddMatiereForm(this, true).setVisible(true);
    }//GEN-LAST:event_buttonRounder34ActionPerformed

    private void buttonRounder35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder35ActionPerformed
        if (tab_class.getSelectedRow() != -1) {

            int row = tab_matier.getSelectedRow();
            int id = (int) tab_matier.getValueAt(row, 3);
            Matiere matiere = matiereDAOImpl.findById(id);
            new ModifyMatiere(this, true, matiere).setVisible(true);
        }    }//GEN-LAST:event_buttonRounder35ActionPerformed

    private void buttonRounder36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRounder36ActionPerformed

    private void buttonRounder37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder37ActionPerformed
        if (tab_etudiant_view.getSelectedRow() != -1) {
            int id = (int) tab_etudiant_view.getValueAt(tab_etudiant_view.getSelectedRow(), 14);
            Etudiant etudiant = etudiantDAOImpl.findById(id);
            new AddInscription(this, true, etudiant).setVisible(true);

        }
    }//GEN-LAST:event_buttonRounder37ActionPerformed

    private void buttonRounder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder2ActionPerformed
        if (!txt_cod_barr_presence.getText().isEmpty()) {
            Etudiant etudiant = etudiantDAOImpl.getEtudiantByCodbar(txt_cod_barr_presence.getText());// matier of today                            

            setInfoEtudiantInPanPresence(etudiant);
            // Liste seance to day of etud 
            List<Seance> Seance_of_today_for_etudtaint = getSeanceOfTodayWithInscriptionEtud(list_seance_about_all_matieres, etudiant);
            if (Seance_of_today_for_etudtaint.size() != 0) {
                // seance etudeé to day 1,2,3 
                Matiere matier = matiere_service.getMatier_Of_TimNow_Etud(Seance_of_today_for_etudtaint);//
                lab_matiere_presence.setText(matier.getMatiereEtdAr());
              setInfoInTablePresence();   
            } else {
                lab_matiere_presence.setText("/");
                message_validation.showMessage(null, "لا توجد حصة اليوم بالنسبة لهذا الطالب");
            }
        }
       
    }//GEN-LAST:event_buttonRounder2ActionPerformed

    public boolean testPyementOfEtudiantSeance(Etudiant etudiant, Matiere matiere, Seance_Matiere seance_Matiere) {
        List<Payement> payements = payementDAOImpl.findAll();
        boolean exist = false;
//        for (Payement payement : payementDAOImpl.findAll()) {
//            JOptionPane.showMessageDialog(null, "etud :" + etudiant.getId() + "mat :" + matiere.getId() + "seaMat : " + seance_Matiere.getId() + "\n" + "etud :" + payement.getEtudiant().getId() + "mat :" + payement.getMatiere().getId() + "seaMat : " + payement.getSeance_matiere().getId());
//            if (payement.getEtudiant().getId() == etudiant.getId()
//                    && payement.getMatiere().getId() == matiere.getId()
//                    && payement.getSeance_matiere().getId() == seance_Matiere.getId()) {
//                JOptionPane.showMessageDialog(null, "Pyéé  " + true);
//                exist = true;
//                break;
//            }
//        }
        return exist;
    }

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

    private void setForm(JComponent Center, JComponent com) {
        Center.removeAll();
        Center.add(com);
        Center.repaint();
        Center.revalidate();
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

    public void creation_dashbord() {

        scrol_dashbord.setVerticalScrollBar(new ScrollBar());
        scrol_dashbord.getVerticalScrollBar().setBackground(Color.WHITE);
        scrol_dashbord.getViewport().setBackground(Color.white);// make table without rouw white
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrol_dashbord.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                home home = new home();

                //  new splashscreen.SplashScreen(null, true).setVisible(true);
                home.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ui.card.Card CardEmployer;
    private ui.card.Card CardEnsigniant;
    private ui.card.Card CardEtudtaint;
    private javax.swing.JPanel Dashboard;
    private material.design.Combobox Groupe3;
    private material.design.Combobox Groupe4;
    private material.design.Combobox Groupe5;
    private material.design.buttonRounder btn_level_conf;
    private material.design.buttonRounder btn_prof_conf;
    private material.design.buttonRounder btn_serv_conf;
    private material.design.buttonRounder buttonRounder1;
    private material.design.buttonRounder buttonRounder11;
    private material.design.buttonRounder buttonRounder12;
    private material.design.buttonRounder buttonRounder13;
    private material.design.buttonRounder buttonRounder14;
    private material.design.buttonRounder buttonRounder15;
    private material.design.buttonRounder buttonRounder16;
    private material.design.buttonRounder buttonRounder17;
    private material.design.buttonRounder buttonRounder18;
    private material.design.buttonRounder buttonRounder19;
    private material.design.buttonRounder buttonRounder2;
    private material.design.buttonRounder buttonRounder20;
    private material.design.buttonRounder buttonRounder21;
    private material.design.buttonRounder buttonRounder22;
    private material.design.buttonRounder buttonRounder23;
    private material.design.buttonRounder buttonRounder24;
    private material.design.buttonRounder buttonRounder25;
    private material.design.buttonRounder buttonRounder26;
    private material.design.buttonRounder buttonRounder27;
    private material.design.buttonRounder buttonRounder28;
    private material.design.buttonRounder buttonRounder3;
    private material.design.buttonRounder buttonRounder32;
    private material.design.buttonRounder buttonRounder33;
    private material.design.buttonRounder buttonRounder34;
    private material.design.buttonRounder buttonRounder35;
    private material.design.buttonRounder buttonRounder36;
    private material.design.buttonRounder buttonRounder37;
    private material.design.buttonRounder buttonRounder38;
    private material.design.buttonRounder buttonRounder4;
    private material.design.buttonRounder buttonRounder8;
    private material.design.Combobox com_catego_niveau;
    private material.design.Combobox com_niveau;
    private material.design.Combobox combobox1;
    private material.design.Combobox combobox12;
    private material.design.Combobox combobox2;
    private material.design.Combobox combobox3;
    private material.design.Combobox combobox5;
    private chart.costumChart costumChart1;
    private ui.menufr.customMenu customMenu1;
    private datechooser.DateChooser dateChooser;
    private javax.swing.JTextField first_name_client_txt10;
    private javax.swing.JTextField first_name_client_txt11;
    private javax.swing.JTextField first_name_client_txt12;
    private javax.swing.JTextField first_name_client_txt13;
    private javax.swing.JTextField first_name_client_txt14;
    private javax.swing.JTextField first_name_client_txt15;
    private javax.swing.JTextField first_name_client_txt16;
    private javax.swing.JTextField first_name_client_txt17;
    private javax.swing.JTextField first_name_client_txt18;
    private javax.swing.JTextField first_name_client_txt7;
    private javax.swing.JTextField first_name_client_txt8;
    private javax.swing.JTextField first_name_client_txt9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lab_category_presence;
    private javax.swing.JLabel lab_icon_chech_cross;
    private javax.swing.JLabel lab_imag_etud_presence;
    private javax.swing.JLabel lab_img_etu;
    private javax.swing.JLabel lab_matiere_presence;
    private javax.swing.JLabel lab_nam_presence;
    private javax.swing.JLabel lab_niveau_presence;
    private javax.swing.JLabel lab_num_seance_presence;
    private javax.swing.JLabel lab_prenom_presence;
    private material.design.buttonRounder link_Instgram;
    private material.design.buttonRounder link_facebook;
    private material.design.buttonRounder link_facebook1;
    private material.design.buttonRounder link_location;
    private material.design.buttonRounder link_webSite;
    private javax.swing.JPanel pan_buttom;
    private javax.swing.JPanel pan_catego;
    private javax.swing.JPanel pan_center;
    private javax.swing.JPanel pan_conf_inter;
    private javax.swing.JPanel pan_config;
    private javax.swing.JPanel pan_cour;
    private javax.swing.JPanel pan_cour_formati_lang_;
    private javax.swing.JPanel pan_cours_conf;
    private javax.swing.JPanel pan_dashbord;
    private javax.swing.JPanel pan_dashbord1;
    private javax.swing.JPanel pan_ensign;
    private javax.swing.JPanel pan_formation;
    private javax.swing.JPanel pan_formation_conf;
    private javax.swing.JPanel pan_lang;
    private javax.swing.JPanel pan_lang_conf;
    private javax.swing.JPanel pan_level;
    private javax.swing.JPanel pan_menu;
    private javax.swing.JPanel pan_presence;
    private javax.swing.JPanel pan_prof;
    private javax.swing.JPanel pan_saise;
    private javax.swing.JPanel pan_view;
    private javax.swing.JScrollPane scrol_dashbord;
    private material.design.SearchText searchText2;
    private material.design.SearchText searchText3;
    private material.design.SearchText searchText4;
    private material.design.SearchText searchText5;
    private material.design.SearchText searchText6;
    private javax.swing.JTable tab_class;
    private javax.swing.JTable tab_etudiant_view;
    private javax.swing.JTable tab_formation;
    private javax.swing.JTable tab_lang;
    private javax.swing.JTable tab_level;
    private javax.swing.JTable tab_matier;
    private javax.swing.JTable tab_presence;
    private javax.swing.JTable tab_prof;
    private javax.swing.JTable tab_seance;
    private ui.table.TableScrollButton tableScrollButton1;
    private ui.table.TableScrollButton tableScrollButton10;
    private ui.table.TableScrollButton tableScrollButton11;
    private ui.table.TableScrollButton tableScrollButton12;
    private ui.table.TableScrollButton tableScrollButton2;
    private ui.table.TableScrollButton tableScrollButton3;
    private ui.table.TableScrollButton tableScrollButton4;
    private ui.table.TableScrollButton tableScrollButton5;
    private ui.table.TableScrollButton tableScrollButton6;
    private ui.table.TableScrollButton tableScrollButton7;
    private ui.table.TableScrollButton tableScrollButton8;
    private ui.table.TableScrollButton tableScrollButton9;
    private javax.swing.JTable table_seance_to_day;
    private material.design.TextField textField1;
    private material.design.TextField textField10;
    private material.design.TextField textField2;
    private material.design.TextField textField3;
    private material.design.TextField textField4;
    private material.design.TextField textField6;
    private material.design.TextField textField8;
    private material.design.TextField textField9;
    private javax.swing.JTextField txt_cod_barr_presence;
    private material.design.TextField txt_dat_birth;
    private material.design.Combobox الدورات;
    private material.design.Combobox النوع;
    // End of variables declaration//GEN-END:variables

}
