/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import domaine.Enseignant;
import domaine.EnseignantMatiere;
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
import main.java.com.school.impl.EnseignantMatiereDAOImpl;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author farid
 */
public class EnseignantMatiereService {
    
    public List getListEnseignantAndMatiereEtudieeByDay() {
        List<EnseignantMatiere> ListEnsgMatiere = Collections.emptyList();
        Map<Integer, Enseignant> map_enseignant = new HashMap<>();
        try {
            ListEnsgMatiere = new EnseignantMatiereDAOImpl(ConnectionDB.getConnection()).findAll();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("ar"));
            LocalDate today = LocalDate.now();
            // Format the date to get the day name in Arabic
            String dayNameInArabic = today.format(formatter);
            System.out.println("Now " + dayNameInArabic);



        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
        }

        // si Map null return empty list
//        List<Enseignant> listEnseignant = (map_enseignant.isEmpty()) ? Collections.emptyList() : new ArrayList<>(map_enseignant.values());
        return ListEnsgMatiere;
    }
}
