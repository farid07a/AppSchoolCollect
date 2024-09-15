/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import domaine.Matiere;
import domaine.Seance;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author client
 */
public class MatiereService {
    
   public  List getListMatierByDay(){
       List<Seance> Listsceance = null;
       Map<Integer, Matiere> map_matiere = new HashMap<>();
       try {
            Listsceance=new SeanceDAOImpl(ConnectionDB.getConnection()).findAll();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("ar"));
            LocalDate today = LocalDate.now();
        // Format the date to get the day name in Arabic
        String dayNameInArabic = today.format(formatter);
           System.out.println("Now "+dayNameInArabic);
           
           for (Seance seance : Listsceance) {
               
               
               if(seance.getDay_sceance().equals(dayNameInArabic)){
                   int id_matier=seance.getMatiere().getId();
                   if(!map_matiere.containsKey(id_matier)){
                       map_matiere.put(id_matier, seance.getMatiere());
                   }else System.out.println("This Matiere Deja Exist:"+seance.getMatiere().getMatiereEtdAr());
               }
               
           }
           
       } catch (DatabaseConnectionException ex) {
           Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        
       // si Map null return empty list
       List<Matiere> listMatiere=(map_matiere.isEmpty())? Collections.emptyList(): new ArrayList<>(map_matiere.values());
   return listMatiere;
   }
    
   
   
    public static void main(String[] args) {
        new MatiereService().getListMatierByDay();
        
        System.out.println(new MatiereService().getListMatierByDay());
    }
}
