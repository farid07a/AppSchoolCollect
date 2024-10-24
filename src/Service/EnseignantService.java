/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import domaine.Enseignant;
import domaine.Matiere;
import domaine.Seance;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
 * @author farid
 */
public class EnseignantService {
    
    public List getListEnseignantEtudieeByDay() {
        List<Seance> Listsceance = null;
        Map<Integer, Enseignant> map_enseignant = new HashMap<>();
        try {
            Listsceance = new SeanceDAOImpl(ConnectionDB.getConnection()).findAll();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("ar"));
            LocalDate today = LocalDate.now();
            // Format the date to get the day name in Arabic
            String dayNameInArabic = today.format(formatter);
            System.out.println("Now " + dayNameInArabic);

            for (Seance seance : Listsceance) {

                if (seance.getDay_sceance().equals(dayNameInArabic)) {
                    //int id_matier = seance.getMatiere().getId();
                    int id_enseignat=seance.getEnseignant().getId();
                    if (!map_enseignant.containsKey(id_enseignat)) {
                        map_enseignant.put(id_enseignat, seance.getEnseignant());
                    } else {
                        System.out.println("This Enseignat Deja Exist:" + seance.getEnseignant().getNomAr());
                    }
                }

            }

        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
        }

        // si Map null return empty list
        List<Enseignant> listEnseignant = (map_enseignant.isEmpty()) ? Collections.emptyList() : new ArrayList<>(map_enseignant.values());
        return listEnseignant;
    }
    
}
