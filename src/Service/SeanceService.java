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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.impl.MatiereDAOImpl;
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
    
    public Seance GetSeanceByTody(Matiere matiere)throws DatabaseConnectionException{
        java.util.List<Seance> listSeance = new SeanceDAOImpl(ConnectionDB.getConnection()).findAll();
        LocalDate tody=LocalDate.now();
        for (Seance seance : listSeance) {
            
            if (seance.getMatiere().getId()== matiere.getId() && seance.getDate_sceance().isEqual(tody)){
                return seance;
            }
        }
        return null;
    }
    
    public java.util.List<Seance> getListAllSeancePrevieuMonth(Matiere matiere)throws DatabaseConnectionException{
        List<Seance>  Reverse_list_previeux_seances=  new SeanceDAOImpl(ConnectionDB.getConnection()).getListPrevieuxSceanceWithMonthByMatiere(matiere);
        
        List list_previeux_seances=new ArrayList();
        for(int i=Reverse_list_previeux_seances.size()-1;i>=0;i--){
            list_previeux_seances.add(Reverse_list_previeux_seances.get(i));
        }
        
        return list_previeux_seances;
        
    }
    
    public java.util.List<Seance> getListAllSeancePrevieuSemaine(Matiere matiere)throws DatabaseConnectionException{
        List<Seance>  Reverse_list_previeux_seances=  new SeanceDAOImpl(ConnectionDB.getConnection()).getListPrevieuxSceanceWithSemaineByMatiere(matiere);
        
        List list_previeux_seances=new ArrayList();
        for(int i=Reverse_list_previeux_seances.size()-1;i>=0;i--){
            list_previeux_seances.add(Reverse_list_previeux_seances.get(i));
        }
        
        return list_previeux_seances;
        
    }
    
    public void saveAllNextSeances(List<Seance> previeux_seances)throws DatabaseConnectionException, SQLException{
        System.out.println("********************************************");
        Connection cnx=ConnectionDB.getConnection();
        seance_dao=new SeanceDAOImpl(cnx);
        for (Seance previeux_seance : previeux_seances) {
           
            System.out.print("Before: "+previeux_seance.getDay_sceance()+" "+previeux_seance.getDate_sceance());
            previeux_seance.setDate_sceance(previeux_seance.getDate_sceance().plusDays(7));
            // seance{ id , time, fiTime, date }
            System.out.println("-- next: "+previeux_seance.getDay_sceance()+" "+previeux_seance.getDate_sceance());
            int x=seance_dao.save(previeux_seance);
            
            if (x>0) {
                System.out.println("Success Save Seance");
            }else {
                System.out.println("Cannot save Seance");
            }
            //cnx.close();
            
            
        }
        
    }
    
    
    
    public boolean ExistVacances(Matiere matiere){
         
        
        return True;
     }
    
    
    public static void main(String[] args) {
        LocalDate f=LocalDate.now();
        
        LocalDate f2= f.plusDays(7*4);
        
        System.out.println("F1"+f);
        System.out.println("F2"+f2);
        
        SeanceService obj=new SeanceService();
        Connection conx;
        try {
            conx = ConnectionDB.getConnection();
            Matiere matiere=new MatiereDAOImpl(conx).findById(2);
            
            Seance sc=obj.GetSeanceByTody(matiere);
            
            System.out.println("sc today "+sc);
            
            System.out.println(matiere);
            List lisSeances=obj.getListAllSeancePrevieuMonth(matiere);
            System.out.println(lisSeances);
            //obj.saveAllNextSeances(lisSeances);
            
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //obj.getListAllSeancePrevieuMonth();
        //obj.getListAllSeancePrevieuMonth();
        
        //obj.getListAllSeancePrevieuMonth();
        
        
        
        //obj.getListAllSeancePrevieuMonth();
        
        
        
    }
    
}
