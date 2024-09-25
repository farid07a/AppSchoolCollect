/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school;

import domaine.CategoreNiveau;
import domaine.Etudiant;
import domaine.Matiere;
import domaine.NiveauEtude;
import domaine.Seance;
import domaine.Seance_Matiere;
import static guis.Home.getDayOfWeekFromArabic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.java.com.school.impl.CategoreNiveauDAOImpl;
import main.java.com.school.impl.EtudiantDAOImpl;
import main.java.com.school.impl.MatiereDAOImpl;
import main.java.com.school.impl.NiveauEtudeDAOImpl;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.impl.SeanceMatiereDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author farid
 */
public class MAIN {
    
    public static void main(String[] args) {
        NiveauEtude niveau=new NiveauEtude();
        niveau.setId(1);
        niveau.setNiveauInitialAr("المستوي ثانية متوسط");
        niveau.setNiveauInitialFr("2 secondaire");
        niveau.setDescription("Niveau 2 S");
        Connection connection;
        try {
            connection = new ConnectionDB().getConnection();
            Seance seance = new SeanceDAOImpl(connection).findById(5);
        
      MatiereDAOImpl m = new MatiereDAOImpl(connection);
           // m.getMatieresNiveauOfCategory(new CategoreNiveauDAOImpl(connection).findById(6),new NiveauEtudeDAOImpl(connection).findById(5)).get(0).getMatiereEtdAr();
         System.out.println(
                 m.getMatiereNiveauOfCategory("لغة عربية", "سنة أولى ابتدائي", "الابتدائي").getId());
            
           // Seance_Matiere seance_Matiere =new SeanceMatiereDAOImpl(connection).getlasSeanceOfToday(day, 0)
           // LocalDate date_seance_ma= seance_Matiere.getDate().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));                 

     //       System.out.println(""+seance_Matiere.getDate());
//            if(seance_Matiere.getSeance().getFinTime().after(Time.valueOf(LocalTime.now()))){
//                JOptionPane.showMessageDialog(null, "after");
//            
//            }else{
//                            if(seance_Matiere.getSeance().getFinTime().before(Time.valueOf(LocalTime.now()))){
//                            JOptionPane.showMessageDialog(null, "befor");
//
//            }
            
            
            
            
            
            
//            Matiere matiere=  new   MatiereDAOImpl(connection).findById(5);
//                    System.out.println(matiere.getMatiereEtdAr());
            
          //  Matiere matiere=  new   MatiereDAOImpl(connection).getMatiereNiveauOfCategory("فرنسية", "سنة أولى", "متوسط");
           // System.out.println(matiere.getMatiereEtdAr() + matiere.getCategoreNiveau().getCategore_niveau_ar()+ matiere.getEnseignant().getNomAr());
            
            //NiveauEtudeDAOImpl niveau_dao=new NiveauEtudeDAOImpl(connection);
            //niveau_dao.save(niveau);
//            
//                 Etudiant etudiant = new Etudiant(0, "0012", "00125", "farid", "khebbache", LocalDate.now(), "0666544554", "pere farid", niveau, null);
//        EtudiantDAOImpl etudiant_dao=new EtudiantDAOImpl(connection);
//    //    etudiant_dao.save(etudiant);
//            CategoreNiveau categoreNiveau= new CategoreNiveau(  0, "إبتدائي", "Primaire", "primaire");
//            CategoreNiveauDAOImpl categoreNiveauDAOImpl= new CategoreNiveauDAOImpl(connection);
//         //   categoreNiveauDAOImpl.save(categoreNiveau);
//            
//            NiveauEtude niveauEtude = new NiveauEtude(1, "سنة أولى", "Première année", "Première année",1);
//            NiveauEtudeDAOImpl niveauEtudeDAOImpl= new NiveauEtudeDAOImpl(connection);
//          //  niveauEtudeDAOImpl.save(niveauEtude);
          //  System.out.println(etudiant_dao.findById(1).getNom());
        } catch (DatabaseConnectionException ex) {  
            Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    }
}
