/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import domaine.Matiere;
import domaine.Seance;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author farid
 */
public class testClass {
    MatiereService matiere_service=new MatiereService();
    SeanceService seance_service=new SeanceService();
    List <Seance> list_seance_about_all_matieres=new ArrayList<>();

    
    
    
    public void checkSeancesAndSaveNewSeance() throws DatabaseConnectionException, SQLException{
        java.util.List<Matiere> list_matiere=matiere_service.getListMatierByDay();// get all Matiere for today 
        
        System.out.println("Lenght List Matiere: "+list_matiere.size()+ " List Matiere Today :"+list_matiere);
        
        List list_previeux=null;
        // for test si seance exit update true= en cours sinon creation Number total of seance in month  
        Seance scence_obj;
        for (Matiere matiere : list_matiere) {
            System.out.println("Matire:"+matiere);
            
            // get seance of matiere today 
            scence_obj=seance_service.GetSeanceByTody(matiere);
            
            System.out.println("Seance By Matiere "+matiere.getMatiereEtdAr()+" -> Seance :"+scence_obj);
            
            if(scence_obj!=null){
                // 
                list_seance_about_all_matieres.add(scence_obj);
                System.out.println("Object != null success add ");
                
            }else{
                
                list_previeux=seance_service.getListAllSeancePrevieuMonth(matiere); // list [sceance(1....30) ]
                seance_service.saveAllNextSeances(list_previeux);// save next seances about Matiere
                System.out.println("Seance is Null AFter inser database change to :"+seance_service.GetSeanceByTody(matiere));
                list_seance_about_all_matieres.add(seance_service.GetSeanceByTody(matiere));
                
            }
        }
        
    }
    
    public static void main(String[] args) {
        testClass obj=new testClass();
        try {
            obj.checkSeancesAndSaveNewSeance();
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(testClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(testClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
