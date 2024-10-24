/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package guis;

import Service.SeanceService;
import domaine.CategoreNiveau;
import domaine.Enseignant;
import domaine.Matiere;
import domaine.NiveauEtude;
import domaine.Seance;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import main.java.com.school.impl.CategoreNiveauDAOImpl;
import main.java.com.school.impl.EnseignantDAOImpl;
import main.java.com.school.impl.MatiereDAOImpl;
import main.java.com.school.impl.EnseignantMatiereDAOImpl;
import main.java.com.school.impl.NiveauEtudeDAOImpl;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;
import material.design.ScrollBar;
import material.design.pan_time;
import ui.table.TableCustom;

/**
 *
 * @author client
 */
public class UpdateSeanceForm extends javax.swing.JDialog {

    home home;
    Connection connection;
    CategoreNiveauDAOImpl categoreNiveauDAOImpl;
    NiveauEtudeDAOImpl niveauEtudeDAOImpl;
    MatiereDAOImpl matiereDAOImpl;
    EnseignantDAOImpl enseignantDAOImpl;
    EnseignantMatiereDAOImpl MatiereEnseignat_dao_impl;
    int nomber_seance = 0;

    public UpdateSeanceForm(java.awt.Frame parent, boolean modal,Seance seance) {
        super(parent, modal);
        home = (home) parent;
        initComponents();

        try {
            connection = new ConnectionDB().getConnection();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        categoreNiveauDAOImpl = new CategoreNiveauDAOImpl(connection);
        niveauEtudeDAOImpl = new NiveauEtudeDAOImpl(connection);
        matiereDAOImpl = new MatiereDAOImpl(connection);
        enseignantDAOImpl = new EnseignantDAOImpl(connection);
        MatiereEnseignat_dao_impl = new EnseignantMatiereDAOImpl(connection);
        setLocationRelativeTo(this);
        setDesignTable(TabSeance, jScrollPane1);
        PrepareUI();

        txt_dat_first_seance.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeDateOfDays();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //  changeDateOfDays();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeDateOfDays();
            }

        });

    }

    public void PrepareUI() {
        setInfoCategoreNiveauInCombox();   
    }
    
    public void FullSeanceTable(Matiere matiere){
        try {
            SeanceService seance_service=new SeanceService();
//            seance_service.getListAllSeancePrevieuSemaine(matiere);
//            Connection connection =ConnectionDB.getConnection();
//            SeanceDAOImpl seance_dao_imp=new SeanceDAOImpl(connection);
//            List <Seance> list_seance=seance_dao_imp.findAll();
            
            List <Seance> list_seance = seance_service.getListAllSeancePrevieuSemaine(matiere);
            DefaultTableModel df=(DefaultTableModel) TabSeance.getModel();
            df.setRowCount(0);
            for (Seance seance : list_seance) {
                
                if(matiere.getId()==seance.getMatiere().getId()){
                Object [] arg={seance.getId(),seance.getNumSeance(),seance.getTimeSeance(),seance.getFinTime(),seance.getDay_sceance()
                ,seance.getDate_sceance().toString(),seance.isTerminate(),"تحويل"};
                df.addRow(arg);
                }
            }
            
            //TabSeance.setModel(df);
            
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(UpdatSeanceInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void SetInfoSeeancesInFormUpdate() throws DatabaseConnectionException {
    Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(
            com_matiere.getSelectedItem().toString(),
            comb_niveau.getSelectedItem().toString(),
            com_catego_niveau.getSelectedItem().toString()
    );

    SeanceService seanceService = new SeanceService();
    
    List<Seance> listSeance = seanceService.getListAllSeancePrevieuSemaine(matiere);
    initializeAllSeances();
    for (Seance seance : listSeance) {
        System.out.println(seance);
        String dayName = seance.getDay_sceance();
        
        System.out.println("dayName:"+dayName);
        switch (dayName) {
            case "الأحد":
                setSeanceSund(seance);
                break;
            case "الاثنين":
                System.out.println("I am in Mondy");
                setSeanceMond(seance);
                check_mond.setSelected(true);
                
                System.out.println("Success checked");
                break;
            case "الثلاثاء":
                setSeancecheckTues(seance);
                break;
            case "الأربعاء":
                setSeancecheckWend(seance);
                break;
            case "الخميس":
                setSeancecheckThurs(seance);
                break;
            case "الجمعة":
                setSeancecheckFrid(seance);
                break;
            case "السبت":
                setSeancecheckSatur(seance);
                break;
            default:
                System.out.println("No Day");
                break;
        }
    }
}

private void initializeAllSeances() {
    setInitSeanceSund();
    setInitSeanceMond();
    setInitSeanceTues();
    setInitSeanceWend();
    setInitSeanceThurs();
    setInitSeanceFrid();
    setInitSeanceSatur();
}

    
//    public void SetInfoSeeancesInFormUpdate() throws DatabaseConnectionException{
//        Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(com_matiere.getSelectedItem().toString(),
//                    comb_niveau.getSelectedItem().toString(), com_catego_niveau.getSelectedItem().toString());
//        SeanceService seance_service=new SeanceService();
//        List <Seance> list_seance = seance_service.getListAllSeancePrevieuSemaine(matiere);
//        
//        for (Seance seance : list_seance) {
//            String NameDay=seance.getDay_sceance();
//            switch(NameDay){
//                case "الأحد":
//                    setSeanceSund(seance);
//                    //setInitSeanceSund();
//                    setInitSeanceMond();
//                    setInitSeanceTues();
//                    setInitSeanceWend();
//                    setInitSeanceThurs();
//                    setInitSeanceFrid();
//                    setInitSeanceSatur();
//                    
//                    break;
//                case "الاثنين":
//                    setSeanceMond(seance);
//                    setInitSeanceSund();
//                    //setInitSeanceMond();
//                    setInitSeanceTues();
//                    setInitSeanceWend();
//                    setInitSeanceThurs();
//                    setInitSeanceFrid();
//                    setInitSeanceSatur();
//                    break;
//                case "الثلاثاء":
//                    setSeancecheckTues(seance);
//                    setInitSeanceSund();
//                    setInitSeanceMond();
//                    //setInitSeanceTues();
//                    setInitSeanceWend();
//                    setInitSeanceThurs();
//                    setInitSeanceFrid();
//                    setInitSeanceSatur();
//                    
//                    break;
//                case "الأربعاء":
//                    setSeancecheckWend(seance);
//                    setInitSeanceSund();
//                    setInitSeanceMond();
//                    setInitSeanceTues();
//                    //setInitSeanceWend();
//                    setInitSeanceThurs();
//                    setInitSeanceFrid();
//                    setInitSeanceSatur();
//                    break;
//                case "الخميس":
//                    setSeancecheckThurs(seance);
//                    setInitSeanceSund();
//                    setInitSeanceMond();
//                    setInitSeanceTues();
//                    setInitSeanceWend();
//                    //setInitSeanceThurs();
//                    setInitSeanceFrid();
//                    setInitSeanceSatur();
//                    break;
//                case "الجمعة":
//                    setSeancecheckFrid(seance);
//                    setInitSeanceSund();
//                    setInitSeanceMond();
//                    setInitSeanceTues();
//                    setInitSeanceWend();
//                    setInitSeanceThurs();
//                    //setInitSeanceFrid();
//                    setInitSeanceSatur();
//                    break;
//                case "السبت":
//                    setSeancecheckSatur(seance);
//                    setInitSeanceSund();
//                    setInitSeanceMond();
//                    setInitSeanceTues();
//                    setInitSeanceWend();
//                    setInitSeanceThurs();
//                    setInitSeanceFrid();
//                    //setInitSeanceSatur();
//                    break;
//                
//                default:
//                    System.out.println("No Day");
//            }
//            
//        }
//    }
    
    
    
    
    public void setSeanceSund(Seance seance){
        check_sund.setSelected(true);
        pan_time_sund.setbignTime(seance.getTimeSeance().toString());
        pan_time_sund.setfinTime(seance.getFinTime().toString());
        lab_date_1.setText(seance.getDate_sceance().toString());
    }
    public void setSeanceMond(Seance seance){
        check_mond.setSelected(true);
        pan_time_mond.setbignTime(seance.getTimeSeance().toString());
        pan_time_mond.setfinTime(seance.getFinTime().toString());
        lab_date_2.setText(seance.getDate_sceance().toString());
    }
    
    public void setSeancecheckTues(Seance seance){
        check_tues.setSelected(true);
        pan_time_tuesd.setbignTime(seance.getTimeSeance().toString());
        pan_time_tuesd.setfinTime(seance.getFinTime().toString());
        lab_date_3.setText(seance.getDate_sceance().toString());
    }
    
    public void setSeancecheckWend(Seance seance){
        check_wend.setSelected(true);
        pan_time_wednesd.setbignTime(seance.getTimeSeance().toString());
        pan_time_wednesd.setfinTime(seance.getFinTime().toString());
        lab_date_4.setText(seance.getDate_sceance().toString());
    }
    
    public void setSeancecheckThurs(Seance seance){
        check_thurs.setSelected(true);
        pan_time_theursd.setbignTime(seance.getTimeSeance().toString());
        pan_time_theursd.setfinTime(seance.getFinTime().toString());
        lab_date_5.setText(seance.getDate_sceance().toString());
    }
    
    public void setSeancecheckFrid(Seance seance){
        check_frid.setSelected(true);
        pan_time_frid.setbignTime(seance.getTimeSeance().toString());
        pan_time_frid.setfinTime(seance.getFinTime().toString());
        lab_date_6.setText(seance.getDate_sceance().toString());
    }
    
    public void setSeancecheckSatur(Seance seance){
        check_satur.setSelected(true);
        pan_time_saturd.setbignTime(seance.getTimeSeance().toString());
        pan_time_saturd.setfinTime(seance.getFinTime().toString());
        lab_date_7.setText(seance.getDate_sceance().toString());
    }
    /**
     * @param seance***********************************************/
    
    public void setInitSeance(JCheckBox checkBox, pan_time timePanel, JLabel dateLabel) {
    checkBox.setSelected(false);
    timePanel.setbignTime("08:01");
    timePanel.setfinTime("09:01");
    dateLabel.setText("");
}

// Example usage:
public void setInitSeanceSund() {
    setInitSeance( check_sund, pan_time_sund, lab_date_1);
}

public void setInitSeanceMond() {
    setInitSeance(check_mond, pan_time_mond, lab_date_2);
}

public void setInitSeanceTues() {
    setInitSeance( check_tues, pan_time_tuesd, lab_date_3);
}

public void setInitSeanceWend() {
    setInitSeance(check_wend, pan_time_wednesd, lab_date_4);
}

public void setInitSeanceThurs() {
    setInitSeance(check_thurs, pan_time_theursd, lab_date_5);
}

public void setInitSeanceFrid() {
    setInitSeance( check_frid, pan_time_frid, lab_date_6);
}

public void setInitSeanceSatur() {
    setInitSeance( check_satur, pan_time_saturd, lab_date_7);
}

    
    /**************************************************/
    
    public void EmptyFieldsUpdateSeamine(){
        
    
    }
    
    
    
    
    public void setInfoCategoreNiveauInCombox() {
        List<CategoreNiveau> categoreNiveaus = categoreNiveauDAOImpl.findAll();
        for (CategoreNiveau categoreNiveau_obj : categoreNiveaus) {
            com_catego_niveau.addItem(categoreNiveau_obj.getCategore_niveau_ar());
        }
        setInfoNiveauEtudeInCom();
    }

    public void setInfoNiveauEtudeInCom() {
        CategoreNiveau category = categoreNiveauDAOImpl.findByName(com_catego_niveau.getSelectedItem().toString(), "categore_niveau_ar");
        List<NiveauEtude> niveauEtudes = niveauEtudeDAOImpl.findAll();
        comb_niveau.removeAllItems();
        for (NiveauEtude obj : niveauEtudes) {
            if (obj.getCategore_niveau_id() == category.getId()) {
                comb_niveau.addItem(obj.getNiveauInitialAr());
            }
        }
        //setInfoMatiereInCom();

    }

    public void setInfoMatiereInCom() {
        com_matiere.removeAllItems();
        CategoreNiveau category = categoreNiveauDAOImpl.findByName(com_catego_niveau.getSelectedItem().toString(), "categore_niveau_ar");
        NiveauEtude niveauEtude = new NiveauEtude();// = niveauEtudeDAOImpl.findByName(comb_niveau.getSelectedItem().toString(), "niveau_initial_ar");
        List<NiveauEtude> niveauEtudes = niveauEtudeDAOImpl.findAll();
        if (comb_niveau.getSelectedIndex() != -1) {
            for (NiveauEtude obj : niveauEtudes) {
                if (obj.getCategore_niveau_id() == category.getId()
                        && obj.getNiveauInitialAr().equals(comb_niveau.getSelectedItem().toString())) {
                    niveauEtude = obj;
                }
            }
            List<Matiere> matieres = matiereDAOImpl.findAll();
            for (Matiere matiere_obj : matieres) {
                if (matiere_obj.getNiveau().getId() == niveauEtude.getId()
                        && matiere_obj.getCategoreNiveau().getId() == category.getId()) {
                    // || matiere_obj.getCategoreNiveau().getId() == idCategoreNiveau) {           
                    com_matiere.addItem(matiere_obj.getMatiereEtdAr());
                }
            }

            //setInfoEnsignInCom();
        }
    }

    public void setInfoMatiereInCom(JComboBox com_catego_niv, JComboBox com_niv, JComboBox com_matier) {

        if (com_catego_niv.getSelectedIndex() != -1 && com_niv.getSelectedIndex() != -1) {

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

//    public void setInfoEnsignInCom() {
//
//        if (com_matiere.getSelectedIndex() != -1 && comb_niveau.getSelectedIndex() != -1 && com_catego_niveau.getSelectedIndex() != -1) {
//            Matiere matiere = new MatiereDAOImpl(connection).getMatiereNiveauOfCategory(com_matiere.getSelectedItem().toString(),
//                    comb_niveau.getSelectedItem().toString(), com_catego_niveau.getSelectedItem().toString());
//            com_ensieng.removeAllItems();
//            if (matiere.getEnseignant() != null && com_matiere.getSelectedIndex() != -1) {
//                com_ensieng.addItem(matiere.getEnseignant().getNomAr());
//            }
//        }
//    }
    public void changeDateOfDays() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(txt_dat_first_seance.getText(), formatter);
        LocalDate dat_next;
        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            dat_next = date;
            lab_date_1.setText(dat_next.format(formatter) + "");
            //lab_date_1.setText(dat_next + "");
        } else {

            dat_next = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
            //lab_date_1.setText(dat_next.format(formatter) + "");
            lab_date_1.setText(dat_next.format(formatter) + "");
        }

        lab_date_2.setText(dat_next.plusDays(1).format(formatter) + "");
        lab_date_3.setText(dat_next.plusDays(2).format(formatter) + "");
        lab_date_4.setText(dat_next.plusDays(3).format(formatter) + "");
        lab_date_5.setText(dat_next.plusDays(4).format(formatter) + "");
        lab_date_6.setText(dat_next.plusDays(5).format(formatter) + "");
        lab_date_7.setText(dat_next.plusDays(6).format(formatter) + "");

//        
//        if (date.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
//            //dat_next = date;
//            //lab_date_2.setText(dat_next.format(formatter) + "");
//            lab_date_2.setText(dat_next.plusDays(1).format(formatter) + "");
//        } else {
//            //dat_next = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
//            //lab_date_2.setText(dat_next.format(formatter) + "");
//            lab_date_2.setText(dat_next.plusDays(1) + "");
//        }
//
//        if (date.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
//            //dat_next = date;
//            //lab_date_3.setText(dat_next.format(formatter) + "");
//            lab_date_3.setText(dat_next.plusDays(2) + "");
//        } else {
//
//            //dat_next = date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
//            //lab_date_3.setText(dat_next.format(formatter) + "");
//            lab_date_3.setText(dat_next.plusDays(2) + "");
//        }
//
//        if (date.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
//            //dat_next = date;
//            //lab_date_4.setText(dat_next.format(formatter) + "");
//            lab_date_4.setText(dat_next.plusDays(3) + "");
//        } else {
//            //dat_next = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
//            //lab_date_4.setText(dat_next.format(formatter) + "");
//            lab_date_4.setText(dat_next.plusDays(3) + "");
//        }
//
//        if (date.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
//            //dat_next = date;
//            //lab_date_5.setText(dat_next.format(formatter) + "");
//            lab_date_5.setText(dat_next.plusDays(4) + "");
//        } else {
//            //dat_next = date.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
//            //lab_date_5.setText(dat_next.format(formatter) + "");
//            lab_date_5.setText(dat_next.plusDays(4) + "");
//        }
//
//        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
//            //dat_next = date;
//            //lab_date_6.setText(dat_next.format(formatter) + "");
//            lab_date_6.setText(dat_next.plusDays(5) + "");
//        } else {
//
//            //dat_next = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
//            //lab_date_6.setText(dat_next.format(formatter) + "");
//            lab_date_6.setText(dat_next.plusDays(5) + "");
//        }
//
//        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
//            //dat_next = date;
//            //lab_date_7.setText(dat_next.format(formatter) + "");
//            lab_date_7.setText(dat_next.plusDays(6) + "");
//        } else {
////            dat_next = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
////            lab_date_7.setText(dat_next.format(formatter) + "");
//            lab_date_7.setText(dat_next.plusDays(6) + "");
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    
    
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
        // tab.setShowVerticalLines(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Apply renderer to all columns
        for (int i = 0; i < tab.getColumnCount(); i++) {
            tab.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        JTableHeader header = tab.getTableHeader();
        header.setDefaultRenderer(headerRenderer);
        tab.setRowHeight(45);

    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker1 = new timePcker.TimePicker();
        dateChooser = new datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        com_matiere = new material.design.Combobox();
        buttonRounder17 = new material.design.buttonRounder();
        buttonRounder19 = new material.design.buttonRounder();
        com_catego_niveau = new material.design.Combobox();
        txt_dat_first_seance = new material.design.TextField();
        com_ensieng = new material.design.Combobox();
        jLabel9 = new javax.swing.JLabel();
        lab_nbr_seance = new javax.swing.JLabel();
        comb_niveau = new material.design.Combobox();
        jLabel1 = new javax.swing.JLabel();
        lab_error_matier = new javax.swing.JLabel();
        lab_error_check_day = new javax.swing.JLabel();
        lab_error_dat = new javax.swing.JLabel();
        NbrSeanceInSemaine_db = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pan_week_updt = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        check_sund = new material.design.JCheckBoxCustomfr();
        check_mond = new material.design.JCheckBoxCustomfr();
        check_tues = new material.design.JCheckBoxCustomfr();
        check_wend = new material.design.JCheckBoxCustomfr();
        check_thurs = new material.design.JCheckBoxCustomfr();
        check_satur = new material.design.JCheckBoxCustomfr();
        check_frid = new material.design.JCheckBoxCustomfr();
        pan_time_frid = new material.design.pan_time();
        pan_time_sund = new material.design.pan_time();
        pan_time_mond = new material.design.pan_time();
        pan_time_tuesd = new material.design.pan_time();
        pan_time_wednesd = new material.design.pan_time();
        pan_time_theursd = new material.design.pan_time();
        pan_time_saturd = new material.design.pan_time();
        lab_date_1 = new javax.swing.JLabel();
        lab_date_2 = new javax.swing.JLabel();
        lab_date_3 = new javax.swing.JLabel();
        lab_date_4 = new javax.swing.JLabel();
        lab_date_5 = new javax.swing.JLabel();
        lab_date_6 = new javax.swing.JLabel();
        lab_date_7 = new javax.swing.JLabel();
        pan_seance_updt = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabSeance = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        buttonRounder18 = new material.design.buttonRounder();
        buttonRounder20 = new material.design.buttonRounder();
        id_ens = new javax.swing.JLabel();
        id_matiere = new javax.swing.JLabel();

        dateChooser.setName(""); // NOI18N
        dateChooser.setTextRefernce(txt_dat_first_seance);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        com_matiere.setForeground(new java.awt.Color(51, 0, 153));
        com_matiere.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        com_matiere.setLabeText("الـمـادة");
        com_matiere.setPreferredSize(new java.awt.Dimension(64, 46));
        com_matiere.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                com_matiereItemStateChanged(evt);
            }
        });
        com_matiere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_matiereActionPerformed(evt);
            }
        });

        buttonRounder17.setBackground(new java.awt.Color(255, 0, 51));
        buttonRounder17.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder17.setText("إلغاء");
        buttonRounder17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonRounder17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder17ActionPerformed(evt);
            }
        });

        buttonRounder19.setBackground(new java.awt.Color(51, 153, 0));
        buttonRounder19.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder19.setText("حفظ");
        buttonRounder19.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonRounder19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder19ActionPerformed(evt);
            }
        });

        com_catego_niveau.setForeground(new java.awt.Color(51, 0, 153));
        com_catego_niveau.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
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

        txt_dat_first_seance.setForeground(new java.awt.Color(204, 0, 51));
        txt_dat_first_seance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dat_first_seance.setToolTipText("");
        txt_dat_first_seance.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_dat_first_seance.setLabelText("تاريخ أسبوع الدراسة");
        txt_dat_first_seance.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                txt_dat_first_seanceHierarchyChanged(evt);
            }
        });
        txt_dat_first_seance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dat_first_seanceActionPerformed(evt);
            }
        });
        txt_dat_first_seance.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_dat_first_seancePropertyChange(evt);
            }
        });

        com_ensieng.setForeground(new java.awt.Color(51, 0, 153));
        com_ensieng.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        com_ensieng.setLabeText("الأستاذ");
        com_ensieng.setPreferredSize(new java.awt.Dimension(64, 46));
        com_ensieng.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                com_ensiengItemStateChanged(evt);
            }
        });
        com_ensieng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_ensiengActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("عدد الحصص في الأسبوع :");

        lab_nbr_seance.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lab_nbr_seance.setForeground(new java.awt.Color(0, 51, 204));
        lab_nbr_seance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_nbr_seance.setText("0");

        comb_niveau.setForeground(new java.awt.Color(51, 0, 153));
        comb_niveau.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        comb_niveau.setLabeText("القسم");
        comb_niveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_niveauActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("إضافة حصص ");
        jLabel1.setOpaque(true);

        lab_error_matier.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lab_error_matier.setForeground(new java.awt.Color(255, 0, 0));
        lab_error_matier.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lab_error_check_day.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lab_error_check_day.setForeground(new java.awt.Color(255, 0, 0));
        lab_error_check_day.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lab_error_dat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lab_error_dat.setForeground(new java.awt.Color(255, 0, 0));
        lab_error_dat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        NbrSeanceInSemaine_db.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NbrSeanceInSemaine_db.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NbrSeanceInSemaine_db.setText("00");

        jPanel2.setLayout(new java.awt.CardLayout());

        pan_week_updt.setBackground(new java.awt.Color(255, 255, 255));
        pan_week_updt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(150, 150, 150)), "الحصص الدراسية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 16), new java.awt.Color(150, 150, 150))); // NOI18N
        pan_week_updt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(197, 197, 197));
        pan_week_updt.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 810, 11));

        jSeparator7.setBackground(new java.awt.Color(197, 197, 197));
        pan_week_updt.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 141, 810, 10));

        jSeparator8.setBackground(new java.awt.Color(197, 197, 197));
        pan_week_updt.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 810, 11));

        jSeparator9.setBackground(new java.awt.Color(197, 197, 197));
        pan_week_updt.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 261, 800, 10));

        jSeparator10.setBackground(new java.awt.Color(197, 197, 197));
        pan_week_updt.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 321, 810, 10));

        jSeparator11.setBackground(new java.awt.Color(197, 197, 197));
        pan_week_updt.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 800, 10));

        check_sund.setBackground(new java.awt.Color(51, 204, 0));
        check_sund.setText("الأحد");
        check_sund.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        check_sund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_sundActionPerformed(evt);
            }
        });
        pan_week_updt.add(check_sund, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 80, -1));

        check_mond.setBackground(new java.awt.Color(51, 204, 0));
        check_mond.setText("الاثنين");
        check_mond.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        check_mond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_mondActionPerformed(evt);
            }
        });
        pan_week_updt.add(check_mond, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 80, -1));

        check_tues.setBackground(new java.awt.Color(51, 204, 0));
        check_tues.setText("الثلاثاء");
        check_tues.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        check_tues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_tuesActionPerformed(evt);
            }
        });
        pan_week_updt.add(check_tues, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 170, 74, -1));

        check_wend.setBackground(new java.awt.Color(51, 204, 0));
        check_wend.setText("الأربعاء");
        check_wend.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        check_wend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_wendActionPerformed(evt);
            }
        });
        pan_week_updt.add(check_wend, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 230, 74, -1));

        check_thurs.setBackground(new java.awt.Color(51, 204, 0));
        check_thurs.setText("الخميس");
        check_thurs.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        check_thurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_thursActionPerformed(evt);
            }
        });
        pan_week_updt.add(check_thurs, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, 75, -1));

        check_satur.setBackground(new java.awt.Color(51, 204, 0));
        check_satur.setText("السبت");
        check_satur.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        check_satur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_saturActionPerformed(evt);
            }
        });
        pan_week_updt.add(check_satur, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 410, 70, -1));

        check_frid.setBackground(new java.awt.Color(51, 204, 0));
        check_frid.setText("الجمعة");
        check_frid.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        check_frid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_fridActionPerformed(evt);
            }
        });
        pan_week_updt.add(check_frid, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, 71, -1));
        pan_week_updt.add(pan_time_frid, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, -1, 40));
        pan_week_updt.add(pan_time_sund, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, -1));
        pan_week_updt.add(pan_time_mond, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, -1));
        pan_week_updt.add(pan_time_tuesd, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));
        pan_week_updt.add(pan_time_wednesd, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, -1, 40));
        pan_week_updt.add(pan_time_theursd, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, -1, 40));
        pan_week_updt.add(pan_time_saturd, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, -1, 40));

        lab_date_1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_date_1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        pan_week_updt.add(lab_date_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 140, 30));

        lab_date_2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_date_2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        pan_week_updt.add(lab_date_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 140, 30));

        lab_date_3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_date_3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        pan_week_updt.add(lab_date_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 140, 30));

        lab_date_4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_date_4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        pan_week_updt.add(lab_date_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 140, 30));

        lab_date_5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_date_5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        pan_week_updt.add(lab_date_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 140, 30));

        lab_date_6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_date_6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        pan_week_updt.add(lab_date_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 140, 30));

        lab_date_7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lab_date_7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        pan_week_updt.add(lab_date_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 140, 30));

        jPanel2.add(pan_week_updt, "card2");

        TabSeance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "numSeance", "timeSeance", "Fin Seance", "day_sceance", "date_sceance", "terminate", "Reporter"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabSeance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabSeanceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabSeance);

        jButton1.setText("Update ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Report");

        javax.swing.GroupLayout pan_seance_updtLayout = new javax.swing.GroupLayout(pan_seance_updt);
        pan_seance_updt.setLayout(pan_seance_updtLayout);
        pan_seance_updtLayout.setHorizontalGroup(
            pan_seance_updtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
            .addGroup(pan_seance_updtLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jButton1)
                .addGap(94, 94, 94)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pan_seance_updtLayout.setVerticalGroup(
            pan_seance_updtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_seance_updtLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(pan_seance_updtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(0, 123, Short.MAX_VALUE))
        );

        jPanel2.add(pan_seance_updt, "card3");

        buttonRounder18.setBackground(new java.awt.Color(255, 0, 51));
        buttonRounder18.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder18.setText("تعديل الاسبوع الحالي");
        buttonRounder18.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonRounder18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder18ActionPerformed(evt);
            }
        });

        buttonRounder20.setBackground(new java.awt.Color(255, 0, 51));
        buttonRounder20.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder20.setText("تعديل الاسبوع القادم");
        buttonRounder20.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonRounder20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounder20ActionPerformed(evt);
            }
        });

        id_ens.setText("IDEns");

        id_matiere.setText("ID Matiere");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(246, 246, 246)
                                .addComponent(lab_error_matier, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(NbrSeanceInSemaine_db, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(com_ensieng, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(com_matiere, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comb_niveau, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(id_ens)
                                        .addGap(43, 43, 43)
                                        .addComponent(id_matiere)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonRounder20, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(buttonRounder18, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(206, 206, 206)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lab_error_dat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(com_catego_niveau, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_dat_first_seance, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lab_nbr_seance, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonRounder17, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(buttonRounder19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(lab_error_check_day, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(41, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(com_catego_niveau, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(com_matiere, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(com_ensieng, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NbrSeanceInSemaine_db, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comb_niveau, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_dat_first_seance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lab_error_dat, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lab_error_matier, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonRounder18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRounder20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_matiere)
                            .addComponent(id_ens))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lab_nbr_seance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lab_error_check_day, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonRounder19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRounder17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void buttonRounder19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder19ActionPerformed

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (Integer.valueOf(NbrSeanceInSemaine_db.getText()) == 0
                || (!Objects.equals(Integer.valueOf(NbrSeanceInSemaine_db.getText()), Integer.valueOf(lab_nbr_seance.getText())))) {
            JOptionPane.showMessageDialog(null, "Error in Number Seance please check it");
            return;
        }

        if (com_matiere.getSelectedIndex() != -1 && !txt_dat_first_seance.getText().isEmpty() && !lab_nbr_seance.getText().equals("0")) {
            Matiere matiere = new MatiereDAOImpl(connection).getMatiereNiveauOfCategory(com_matiere.getSelectedItem().toString(),
                    comb_niveau.getSelectedItem().toString(), com_catego_niveau.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null, "" + matiere.getId());
            SeanceDAOImpl sceanceDAOImpl = new SeanceDAOImpl(connection);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.util.Date debu_seance;
            java.util.Date fin_seance;
            int num_seance = 1;
            List<Seance> listSceance = new ArrayList<>();

            try {
                String FullName = (String) com_ensieng.getSelectedItem();

                String Nom = FullName.split("-")[0];
                String Prenom = FullName.split("-")[1];

                System.out.println(Nom + "  -- " + Prenom);

                Enseignant enseignat = enseignantDAOImpl.findByFullName(Nom, Prenom);
                
                if (check_sund.isSelected()) {
                    LocalDate date = LocalDate.parse(lab_date_1.getText(), formatter);

                    debu_seance = sdf.parse(pan_time_sund.getbignTime());
                    fin_seance = sdf.parse(pan_time_sund.getfinTime());

                    LocalTime start_seance = LocalTime.parse(pan_time_sund.getbignTime().substring(0, 5));
                    LocalTime end_seance = LocalTime.parse(pan_time_sund.getfinTime().substring(0, 5));

                    Seance seance_1 = new Seance(0, num_seance, start_seance, end_seance,
                            check_sund.getText(), date, false, matiere,enseignat);
                    if (sceanceDAOImpl.save(seance_1) > 0) {
                        listSceance.add(seance_1);
                    }
//                    seance_1 = sceanceDAOImpl.getlast();//m this return Sceance with Id != 0 //0 not id seance_1(id:0,num==ok ,....)
//                  
//                    Seance_Matiere seance_Matiere = new Seance_Matiere(0, seance_1, matiere, false,date);
//                    new SeanceMatiereDAOImpl(connection).save(seance_Matiere);
                }
                // الاثين
                if (check_mond.isSelected()) {
                    LocalDate date = LocalDate.parse(lab_date_2.getText(), formatter);

                    num_seance = num_seance + 1;
                    debu_seance = sdf.parse(pan_time_mond.getbignTime());
                    fin_seance = sdf.parse(pan_time_mond.getfinTime());
                    LocalTime start_seance = LocalTime.parse(pan_time_sund.getbignTime().substring(0, 5));
                    LocalTime end_seance = LocalTime.parse(pan_time_sund.getfinTime().substring(0, 5));

                    Seance seance_2 = new Seance(0, num_seance, start_seance, end_seance,
                            check_mond.getText(), date, false, matiere,enseignat);

                    //sceanceDAOImpl.save(seance_2);
                    if (sceanceDAOImpl.save(seance_2) > 0) {
                        listSceance.add(seance_2);
                    }

                    //seance_2 = sceanceDAOImpl.getlast();//m this return Sceance with Id != 0 //0 not id seance_1(id:0,num==ok ,....)
                }
                if (check_tues.isSelected()) {
                    LocalDate date = LocalDate.parse(lab_date_3.getText(), formatter);

                    num_seance = num_seance + 1;
                    debu_seance = sdf.parse(pan_time_tuesd.getbignTime());
                    fin_seance = sdf.parse(pan_time_tuesd.getfinTime());

                    LocalTime start_seance = LocalTime.parse(pan_time_sund.getbignTime().substring(0, 5));
                    LocalTime end_seance = LocalTime.parse(pan_time_sund.getfinTime().substring(0, 5));

                    Seance seance_3 = new Seance(0, num_seance, start_seance, end_seance,
                            check_tues.getText(), date, false, matiere,enseignat);
                    sceanceDAOImpl.save(seance_3);

                    if (sceanceDAOImpl.save(seance_3) > 0) {
                        listSceance.add(seance_3);
                    }

                }
                if (check_wend.isSelected()) {
                    LocalDate date = LocalDate.parse(lab_date_4.getText(), formatter);

                    num_seance = num_seance + 1;
                    debu_seance = sdf.parse(pan_time_wednesd.getbignTime());
                    fin_seance = sdf.parse(pan_time_wednesd.getfinTime());
                    LocalTime start_seance = LocalTime.parse(pan_time_sund.getbignTime().substring(0, 5));
                    LocalTime end_seance = LocalTime.parse(pan_time_sund.getfinTime().substring(0, 5));

                    Seance seance_4 = new Seance(0, num_seance, start_seance, end_seance,
                            check_wend.getText(), date, false, matiere,enseignat);
                    //sceanceDAOImpl.save(seance_4);
                    if (sceanceDAOImpl.save(seance_4) > 0) {
                        listSceance.add(seance_4);
                    }
                }
                if (check_thurs.isSelected()) {
                    LocalDate date = LocalDate.parse(lab_date_5.getText(), formatter);

                    num_seance = num_seance + 1;
                    debu_seance = sdf.parse(pan_time_theursd.getbignTime());
                    fin_seance = sdf.parse(pan_time_theursd.getfinTime());
                    LocalTime start_seance = LocalTime.parse(pan_time_sund.getbignTime().substring(0, 5));
                    LocalTime end_seance = LocalTime.parse(pan_time_sund.getfinTime().substring(0, 5));

                    Seance seance_5 = new Seance(0, num_seance, start_seance, end_seance,
                            check_thurs.getText(), date, false, matiere,enseignat);
                    //sceanceDAOImpl.save(seance_5);
                    if (sceanceDAOImpl.save(seance_5) > 0) {
                        listSceance.add(seance_5);
                    }
                }

                if (check_frid.isSelected()) {
                    LocalDate date = LocalDate.parse(lab_date_6.getText(), formatter);
                    num_seance = num_seance + 1;
                    debu_seance = sdf.parse(pan_time_frid.getbignTime());
                    fin_seance = sdf.parse(pan_time_frid.getfinTime());
                    LocalTime start_seance = LocalTime.parse(pan_time_sund.getbignTime().substring(0, 5));
                    LocalTime end_seance = LocalTime.parse(pan_time_sund.getfinTime().substring(0, 5));
                    Seance seance_6 = new Seance(0, num_seance, start_seance, end_seance,
                            check_frid.getText(), date, false, matiere,enseignat);
                    if (sceanceDAOImpl.save(seance_6) > 0) {
                        listSceance.add(seance_6);
                    }

                }
                if (check_satur.isSelected()) {
                    LocalDate date = LocalDate.parse(lab_date_7.getText(), formatter);

                    num_seance = num_seance + 1;
                    debu_seance = sdf.parse(pan_time_saturd.getbignTime());
                    fin_seance = sdf.parse(pan_time_saturd.getfinTime());
                    LocalTime start_seance = LocalTime.parse(pan_time_sund.getbignTime().substring(0, 5));
                    LocalTime end_seance = LocalTime.parse(pan_time_sund.getfinTime().substring(0, 5));
                    Seance seance_6 = new Seance(0, num_seance, start_seance, end_seance,
                            check_satur.getText(), date, false, matiere,enseignat);
                    if (sceanceDAOImpl.save(seance_6) > 0) {
                        listSceance.add(seance_6);
                    }

                }

//                int numSceance = Integer.parseInt(lab_nbr_seance.getText());
//                 
//                for(int i=0; i<3;i++){
//                    
//                    for (Seance seance : listSceance) {
//                        seance.setNumSeance(++numSceance);
//                        seance.setDate_sceance(seance.getDate_sceance().plusWeeks(1));
//                        if (sceanceDAOImpl.save(seance)>0)
//                            System.out.println("Success Save");
//                    }
//                }
            } catch (ParseException ex) {
                Logger.getLogger(UpdateSeanceForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateSeanceForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (com_matiere.getSelectedIndex() == -1) {
                lab_error_matier.setText("إختر المادة ");
            }
            if (txt_dat_first_seance.getText().equals("")) {
                lab_error_matier.setText("حدد تاريخ بداية الدراسة");
            }
            if (lab_nbr_seance.getText().equals("0")) {
                lab_error_check_day.setText("اختر أيام الدراسة");
            }
        }
    }//GEN-LAST:event_buttonRounder19ActionPerformed

    private void com_matiereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_matiereActionPerformed
        //setInfoEnsignInCom();

        if (com_matiere.getSelectedIndex() != -1) {
            lab_error_matier.setText("");
        }

        if (com_matiere.getSelectedIndex() != -1 && comb_niveau.getSelectedIndex() != -1
                && com_catego_niveau.getSelectedIndex() != -1) {
            try {
                lab_error_matier.setText("");
                Matiere matiere = matiereDAOImpl.getMatiereNiveauOfCategory(com_matiere.getSelectedItem().toString(),
                        comb_niveau.getSelectedItem().toString(), com_catego_niveau.getSelectedItem().toString());
                
                setInfoEnseignantByMatiere(matiere);
                setNumbeSeanceInSemaineByMatiere(matiere);
                FullSeanceTable(matiere);
                //initializeAllSeances();
                SetInfoSeeancesInFormUpdate();
            } catch (DatabaseConnectionException ex) {
                Logger.getLogger(UpdateSeanceForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            com_ensieng.removeAllItems();
            NbrSeanceInSemaine_db.setText("00");
            //ComboGroup.removeAllItems();
        }

    }//GEN-LAST:event_com_matiereActionPerformed

    public void setNumbeSeanceInSemaineByMatiere(Matiere matiere) {
        NbrSeanceInSemaine_db.setText(String.valueOf(matiere.getNum_sceance_semaine()));
    }

    public void setInfoEnseignantByMatiere(Matiere matiere) {
        try {
            System.out.println(matiere);

            List<Enseignant> list_enseignat = MatiereEnseignat_dao_impl.findEnseignantByMatiereId(matiere);

            System.out.println("list enseignat:" + list_enseignat);

            com_ensieng.removeAllItems();

            System.out.println("");
            for (Enseignant enseignat : list_enseignat) {
                com_ensieng.addItem(enseignat.getNomAr() + "-" + enseignat.getPrenomAr());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AddEtudiantForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    private void com_matiereItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_matiereItemStateChanged

    }//GEN-LAST:event_com_matiereItemStateChanged

    private void com_catego_niveauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_catego_niveauItemStateChanged
        //setInfoNiveauEtudeInCom();
    }//GEN-LAST:event_com_catego_niveauItemStateChanged

    private void com_catego_niveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_catego_niveauActionPerformed
        setInfoNiveauEtudeInCom();
    }//GEN-LAST:event_com_catego_niveauActionPerformed

    private void com_ensiengItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_ensiengItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_com_ensiengItemStateChanged

    private void com_ensiengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_ensiengActionPerformed
         if (com_ensieng.getSelectedIndex()==-1) {
            System.out.println("No Prof Affected To This Module");
            NbrSeanceInSemaine_db.setText("00");
            id_ens.setText("");
            id_matiere.setText("");
            return;
        }
        
        try {
            String FullName = (String) com_ensieng.getSelectedItem();
            String Nom = FullName.split("-")[0];
            String Prenom = FullName.split("-")[1];
            System.out.println(Nom + "  -- " + Prenom);
            Enseignant enseignat = enseignantDAOImpl.findByFullName(Nom, Prenom);
            id_ens.setText(enseignat.getId() + "");
        } catch (SQLException ex) {
            Logger.getLogger(AddSeanceForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_com_ensiengActionPerformed

    private void comb_niveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_niveauActionPerformed
        setInfoMatiereInCom(com_catego_niveau, comb_niveau, com_matiere);
        //setInfoMatiereInCom(com_catego_niveau, comb_niveau, com_matiere);

    }//GEN-LAST:event_comb_niveauActionPerformed

    private void check_sundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_sundActionPerformed
        if (check_sund.isSelected()) {
            nomber_seance = nomber_seance + 1;
            lab_nbr_seance.setText(nomber_seance + "");
            lab_error_check_day.setText("");
        } else {
            nomber_seance = nomber_seance - 1;
            lab_nbr_seance.setText(nomber_seance + "");
        }
    }//GEN-LAST:event_check_sundActionPerformed

    private void check_mondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_mondActionPerformed
        if (check_mond.isSelected()) {
            nomber_seance = nomber_seance + 1;
            lab_nbr_seance.setText(nomber_seance + "");
            lab_error_check_day.setText("");
        } else {
            nomber_seance = nomber_seance - 1;
            lab_nbr_seance.setText(nomber_seance + "");
        }
    }//GEN-LAST:event_check_mondActionPerformed

    private void check_tuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_tuesActionPerformed
        if (check_tues.isSelected()) {
            nomber_seance = nomber_seance + 1;
            lab_nbr_seance.setText(nomber_seance + "");
            lab_error_check_day.setText("");
        } else {
            nomber_seance = nomber_seance - 1;
            lab_nbr_seance.setText(nomber_seance + "");
        }
    }//GEN-LAST:event_check_tuesActionPerformed

    private void check_wendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_wendActionPerformed
        if (check_wend.isSelected()) {
            nomber_seance = nomber_seance + 1;
            lab_nbr_seance.setText(nomber_seance + "");
            lab_error_check_day.setText("");
        } else {
            nomber_seance = nomber_seance - 1;
            lab_nbr_seance.setText(nomber_seance + "");
        }
    }//GEN-LAST:event_check_wendActionPerformed

    private void check_thursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_thursActionPerformed
        if (check_thurs.isSelected()) {
            nomber_seance = nomber_seance + 1;
            lab_nbr_seance.setText(nomber_seance + "");
            lab_error_check_day.setText("");
        } else {
            nomber_seance = nomber_seance - 1;
            lab_nbr_seance.setText(nomber_seance + "");
        }
    }//GEN-LAST:event_check_thursActionPerformed

    private void check_saturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_saturActionPerformed
        if (check_satur.isSelected()) {
            nomber_seance = nomber_seance + 1;
            lab_nbr_seance.setText(nomber_seance + "");
            lab_error_check_day.setText("");
        } else {
            nomber_seance = nomber_seance - 1;
            lab_nbr_seance.setText(nomber_seance + "");
        }
    }//GEN-LAST:event_check_saturActionPerformed

    private void check_fridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_fridActionPerformed
        if (check_frid.isSelected()) {
            nomber_seance = nomber_seance + 1;
            lab_nbr_seance.setText(nomber_seance + "");
            lab_error_check_day.setText("");
        } else {
            nomber_seance = nomber_seance - 1;
            lab_nbr_seance.setText(nomber_seance + "");
        }    }//GEN-LAST:event_check_fridActionPerformed

    private void txt_dat_first_seanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dat_first_seanceActionPerformed
        dateChooser.showPopup();
        if (!txt_dat_first_seance.getText().equals("")) {
            lab_error_check_day.setText("");
        }


    }//GEN-LAST:event_txt_dat_first_seanceActionPerformed

    private void buttonRounder17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder17ActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonRounder17ActionPerformed

    private void txt_dat_first_seanceHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txt_dat_first_seanceHierarchyChanged

    }//GEN-LAST:event_txt_dat_first_seanceHierarchyChanged

    private void txt_dat_first_seancePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_dat_first_seancePropertyChange

    }//GEN-LAST:event_txt_dat_first_seancePropertyChange

    private void TabSeanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabSeanceMouseClicked
         
    }//GEN-LAST:event_TabSeanceMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try {
            int selcRow=TabSeance.getSelectedRow();
            int id_Seance=(int) TabSeance.getValueAt(selcRow, 0);
            Seance seance=new SeanceDAOImpl(ConnectionDB.getConnection()).findById(id_Seance);
            UpdatSeanceInfo obj=new UpdatSeanceInfo(home, true, seance);
            obj.setVisible(true);
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(UpdateSeanceForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonRounder18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder18ActionPerformed
       pan_week_updt.setVisible(false);
       pan_seance_updt.setVisible(true);
    }//GEN-LAST:event_buttonRounder18ActionPerformed

    private void buttonRounder20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounder20ActionPerformed
         pan_week_updt.setVisible(true);
       pan_seance_updt.setVisible(false);
    }//GEN-LAST:event_buttonRounder20ActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateSeanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateSeanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateSeanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateSeanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateSeanceForm dialog = new UpdateSeanceForm(new home(), true,null);
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
    private javax.swing.JLabel NbrSeanceInSemaine_db;
    private javax.swing.JTable TabSeance;
    private material.design.buttonRounder buttonRounder17;
    private material.design.buttonRounder buttonRounder18;
    private material.design.buttonRounder buttonRounder19;
    private material.design.buttonRounder buttonRounder20;
    private material.design.JCheckBoxCustomfr check_frid;
    private material.design.JCheckBoxCustomfr check_mond;
    private material.design.JCheckBoxCustomfr check_satur;
    private material.design.JCheckBoxCustomfr check_sund;
    private material.design.JCheckBoxCustomfr check_thurs;
    private material.design.JCheckBoxCustomfr check_tues;
    private material.design.JCheckBoxCustomfr check_wend;
    private material.design.Combobox com_catego_niveau;
    private material.design.Combobox com_ensieng;
    private material.design.Combobox com_matiere;
    private material.design.Combobox comb_niveau;
    private datechooser.DateChooser dateChooser;
    private javax.swing.JLabel id_ens;
    private javax.swing.JLabel id_matiere;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lab_date_1;
    private javax.swing.JLabel lab_date_2;
    private javax.swing.JLabel lab_date_3;
    private javax.swing.JLabel lab_date_4;
    private javax.swing.JLabel lab_date_5;
    private javax.swing.JLabel lab_date_6;
    private javax.swing.JLabel lab_date_7;
    private javax.swing.JLabel lab_error_check_day;
    private javax.swing.JLabel lab_error_dat;
    private javax.swing.JLabel lab_error_matier;
    private javax.swing.JLabel lab_nbr_seance;
    private javax.swing.JPanel pan_seance_updt;
    private material.design.pan_time pan_time_frid;
    private material.design.pan_time pan_time_mond;
    private material.design.pan_time pan_time_saturd;
    private material.design.pan_time pan_time_sund;
    private material.design.pan_time pan_time_theursd;
    private material.design.pan_time pan_time_tuesd;
    private material.design.pan_time pan_time_wednesd;
    private javax.swing.JPanel pan_week_updt;
    private timePcker.TimePicker timePicker1;
    private material.design.TextField txt_dat_first_seance;
    // End of variables declaration//GEN-END:variables
}
