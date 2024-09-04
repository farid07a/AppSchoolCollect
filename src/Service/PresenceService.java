/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import domaine.Etudiant;
import domaine.Presence;
import domaine.Seance;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.impl.PresenceDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author client
 */
public class PresenceService {

    public List<Presence> getPresenceOfToDay() throws DatabaseConnectionException {
        List<Presence> presences = new PresenceDAOImpl(ConnectionDB.getConnection()).findAll();
        List<Presence> presenceOfTOday = new ArrayList<>();
        for (Presence presence : presences) {
            System.out.println(""+presence.getDatePresence().toString());
            System.out.println("No "+LocalDate.now().toString());
            if (presence.getDatePresence().isEqual(LocalDate.now())) {
                presenceOfTOday.add(presence);
                
            }
        }
        return presenceOfTOday;
    }

    public boolean checkPresebceOfEtudiantToday(Etudiant etudiant, Seance seance) throws DatabaseConnectionException {
        Presence presence = new PresenceDAOImpl(ConnectionDB.getConnection()).getPresenceOFetudiantInSeance(etudiant, seance);
         if(presence!=null){
         return true;
         }else{
         return false;
         }
    }

}
