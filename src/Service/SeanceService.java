/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import domaine.Matiere;
import domaine.Seance;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author farid
 */
public class SeanceService {

    // test si exist some sence ne pass pas with this date
    SeanceDAOImpl seance_dao;

    public Seance GetSeanceByTody(Matiere matiere) throws DatabaseConnectionException {
        java.util.List<Seance> listSeance = new SeanceDAOImpl(ConnectionDB.getConnection()).findAll();
        LocalDate tody = LocalDate.now();
        for (Seance seance : listSeance) {

            if (seance.getMatiere().getId() == matiere.getId() && seance.getDate_sceance().isEqual(tody)) {
                return seance;
            }
        }
        return null;
    }

    public java.util.List<Seance> getListAllSeancePrevieuMonth(Matiere matiere) throws DatabaseConnectionException {
        List<Seance> Reverse_list_previeux_seances = new SeanceDAOImpl(ConnectionDB.getConnection()).getListPrevieuxSceanceWithMonthByMatiere(matiere);

        List list_previeux_seances = new ArrayList();
        for (int i = Reverse_list_previeux_seances.size() - 1; i >= 0; i--) {
            list_previeux_seances.add(Reverse_list_previeux_seances.get(i));
        }

        return list_previeux_seances;

    }

    public java.util.List<Seance> getListAllSeancePrevieuSemaineT(Matiere matiere) throws DatabaseConnectionException {
        List<Seance> Reverse_list_previeux_seances = new SeanceDAOImpl(ConnectionDB.getConnection()).getListPrevieuxSceanceWithSemaineByMatiere(matiere);
        List list_previeux_seances = new ArrayList();
        for (int i = Reverse_list_previeux_seances.size() - 1; i >= 0; i--) {
            list_previeux_seances.add(Reverse_list_previeux_seances.get(i));
        }

        return list_previeux_seances;

    }

    public void saveAllNextSeances(List<Seance> previeux_seances) throws DatabaseConnectionException, SQLException {
        System.out.println("********************************************");
        Connection cnx = ConnectionDB.getConnection();
        seance_dao = new SeanceDAOImpl(cnx);
        for (Seance previeux_seance : previeux_seances) {

            System.out.print("Before: " + previeux_seance.getDay_sceance() + " " + previeux_seance.getDate_sceance());
            previeux_seance.setDate_sceance(previeux_seance.getDate_sceance().plusDays(7));
            // seance{ id , time, fiTime, date }
            System.out.println("-- next: " + previeux_seance.getDay_sceance() + " " + previeux_seance.getDate_sceance());
            int x = seance_dao.save(previeux_seance);

            if (x > 0) {
                System.out.println("Success Save Seance");
            } else {
                System.out.println("Cannot save Seance");
            }
            //cnx.close();

        }

    }

    public boolean ExistVacances(Matiere matiere) throws DatabaseConnectionException {
        Connection cnx = ConnectionDB.getConnection();
        seance_dao = new SeanceDAOImpl(cnx);
        Seance seance = seance_dao.getLastSeanceOfMatiere(matiere);
        int nb_day = (int) ChronoUnit.DAYS.between(seance.getDate_sceance(), LocalDate.now());
        if (nb_day > 7) {
            return true;
        } else {
            return false;
        }
    }

    public void saveAllNextSeancesSiExistVacance(List<Seance> previeux_seances) throws DatabaseConnectionException, SQLException {
        System.out.println("********************************************");
        Connection cnx = ConnectionDB.getConnection();
        seance_dao = new SeanceDAOImpl(cnx);
        LocalDate date_now= LocalDate.now();
        LocalDate new_date_seace = null;
    boolean sameday = false;
        for (Seance previeux_seance : previeux_seances) {
           // int nbrDay_betweenToday_daySeance = (date_now.getDayOfWeek().getValue() -previeux_seance.getDate_sceance().getDayOfWeek().getValue()+7)%7;         
            if (previeux_seance.getDate_sceance().getDayOfWeek().getValue()==date_now.getDayOfWeek().getValue()) {
                sameday=true;
            } 
            if(sameday){
              int    nbr_days= (previeux_seance.getDate_sceance().getDayOfWeek().getValue()- date_now.getDayOfWeek().getValue() +7)%7 ;  
                 new_date_seace=   date_now.plusDays(nbr_days);
                 System.out.print("-- Date: " + previeux_seance.getDay_sceance() + " " + previeux_seance.getDate_sceance());
            } else {
               new_date_seace= getPreviousDayOfWeek(date_now, previeux_seance.getDate_sceance().getDayOfWeek());
       System.out.print("-- Date: " + previeux_seance.getDay_sceance() + " " + previeux_seance.getDate_sceance());

            }
//
            previeux_seance.setDate_sceance(new_date_seace);
            // seance{ id , time, fiTime, date }
            System.out.println("-- next: " + previeux_seance.getDay_sceance() + " " + previeux_seance.getDate_sceance());
            int x = seance_dao.save(previeux_seance);

            if (x > 0) {
                System.out.println("Success Save Seance");
            } else {
                System.out.println("Cannot save Seance");
            }
            //cnx.close();

        }

    }
    
    private static LocalDate getPreviousDayOfWeek(LocalDate date, DayOfWeek desiredDay) {
        int daysToSubtract = (date.getDayOfWeek().getValue() - desiredDay.getValue() + 7) % 7;
        if (daysToSubtract == 0) {
            daysToSubtract = 7;
        }
       
        return date.minusDays(daysToSubtract);
    }
    
     private static LocalDate getNextDayOfWeek(LocalDate date, DayOfWeek desiredDay) {
        int daysToAdd = (desiredDay.getValue() - date.getDayOfWeek().getValue() + 7) % 7;
        if (daysToAdd == 0) {
            daysToAdd = 7;
        }

        return date.plusDays(daysToAdd);
     }
     
   

    public static void main(String[] args) {
        
        LocalDate seance_dima=LocalDate.of(2024, 9, 1);
        LocalDate seance_mer=LocalDate.of(2024, 9,4 );
        LocalDate seance_ven=LocalDate.of(2024, 9,6 );
        LocalDate seance_sam=LocalDate.of(2024, 9,7 );
        List <LocalDate > date = new ArrayList<>();
       date.add(seance_dima);
       date.add(seance_mer);
       date.add(seance_ven);  
       date.add(seance_sam);
        LocalDate date_now = LocalDate.of(2024, 9, 15); //     
      boolean exist_pour_add=false;
        for(LocalDate seance : date){
        if(seance.getDayOfWeek().getValue()==date_now.getDayOfWeek().getValue())  {
            exist_pour_add=true;
        } 
        int daysToSubtract =0;//= (date_now.getDayOfWeek().getValue()-seance.getDayOfWeek().getValue() ) ;
        if(exist_pour_add){
         daysToSubtract= (seance.getDayOfWeek().getValue()- date_now.getDayOfWeek().getValue() +7)%7 ;  
         System.out.println("if"+daysToSubtract);
        System.out.println(""+date_now.plusDays(daysToSubtract));
        } else{
         //daysToSubtract= new SeanceService().getPreviousDayOfWeek(date_now, seance.getDayOfWeek());
// daysToSubtract= (date_now.getDayOfWeek().getValue()-seance.getDayOfWeek().getValue()) ;  
        System.out.println("else"+daysToSubtract);
        System.out.println(""+new SeanceService().getPreviousDayOfWeek(date_now, seance.getDayOfWeek()));
        }
       }
        
        
        
//        Seance s;
//        try {
//            s = new SeanceDAOImpl(ConnectionDB.getConnection()).findById(1);
//            int nbrDay_betweenToday_daySeance = (DayOfWeek.FRIDAY.getValue() - LocalDate.now().getDayOfWeek().getValue() + 7) % 7;
//
//            if (nbrDay_betweenToday_daySeance == 0) {
//                System.out.println("same daye " + LocalDate.now());
//            } else {
//                System.out.println("" + LocalDate.now().plus(nbrDay_betweenToday_daySeance, ChronoUnit.DAYS));
//            }
//
//        } catch (DatabaseConnectionException ex) {
//            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
//        }
////
//        LocalDate f = LocalDate.now();
//
//        LocalDate f2 = f.plusDays(7 * 4);
//
//        System.out.println("F1" + f);
//        System.out.println("F2" + f2);
//
//        SeanceService obj = new SeanceService();
//        Connection conx;
//        try {
//            conx = ConnectionDB.getConnection();
//            Matiere matiere = new MatiereDAOImpl(conx).findById(2);
//
//            Seance sc = obj.GetSeanceByTody(matiere);
//
//            System.out.println("sc today " + sc);
//
//            System.out.println(matiere);
//            List lisSeances = obj.getListAllSeancePrevieuMonth(matiere);
//            System.out.println(lisSeances);
//            //obj.saveAllNextSeances(lisSeances);
//
//        } catch (DatabaseConnectionException ex) {
//            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //obj.getListAllSeancePrevieuMonth();
        //obj.getListAllSeancePrevieuMonth();

        //obj.getListAllSeancePrevieuMonth();
        //obj.getListAllSeancePrevieuMonth();
    }

}
