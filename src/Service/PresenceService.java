/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import domaine.Etudiant;
import domaine.Inscription;
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

//    public Presence checkPreseceOfEtudiantToday(Etudiant etudiant, Seance seance) throws DatabaseConnectionException {
//        Presence presence = new PresenceDAOImpl(ConnectionDB.getConnection()).getPresenceOFetudiantInSeance(etudiant, seance);
//        
//         return presence;
//         
//    }
    
     public  List<Presence> getAllPrsenceOfEtdiantWithSeanceOfTOday(List<Seance> seances) throws DatabaseConnectionException {
        List<Inscription> inscriptions = new InscriptionService().getAllInscriptionOFToday(seances);
        List<Presence> presences = new ArrayList<>();
        for (Inscription inscription : inscriptions) {
            Seance seance_presence = new Seance();
            for (Seance seance : seances) {
                if (seance.getMatiere().getId() == inscription.getMatiere().getId()) {
                    seance_presence = seance;
                }
            }

           Presence presence = new Presence(0, inscription.getEtudiant(), inscription.getMatiere(), seance_presence, LocalDate.now(),false);
           presences.add(presence);       
        }
         System.out.println("   presences : "+presences);
        Presence lastPresence= new PresenceDAOImpl(ConnectionDB.getConnection()).getlast();
        if(lastPresence == null  || !lastPresence.getDatePresence().isEqual(LocalDate.now()) ){
            for(Presence presenceObj :presences){
            new PresenceDAOImpl(ConnectionDB.getConnection()).save(presenceObj);
            }
        }
        return presences;
    }


}
