/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import domaine.Inscription;
import domaine.Seance;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.impl.InscriptionDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author client
 */
public class InscriptionService {
    
    public List<Inscription> getAllInscriptionOFToday(List<Seance> seances) throws DatabaseConnectionException{       
        List<Inscription> inscriptions = new InscriptionDAOImpl(ConnectionDB.getConnection()).findAll();
        List<Inscription> inscriptions_preview= new ArrayList<>();
        for (Seance seance : seances) {
        for (Inscription inscription : inscriptions) {
            if(seance.getMatiere().getId() == inscription.getMatiere().getId()){
              inscriptions_preview.add(inscription);
            }
        }
        }
        return inscriptions_preview;
    }

}