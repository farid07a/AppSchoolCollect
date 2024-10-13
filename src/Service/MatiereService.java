/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import domaine.Matiere;
import domaine.Seance;
import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.swing.JOptionPane;
import main.java.com.school.impl.SeanceDAOImpl;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author client
 */
public class MatiereService {

    int xMouse;
    int yMouse;
    int timeRun = 0;

    public List getListMatierByDay() {
        List<Seance> Listsceance = null;
        Map<Integer, Matiere> map_matiere = new HashMap<>();
        try {
            Listsceance = new SeanceDAOImpl(ConnectionDB.getConnection()).findAll();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("ar"));
            LocalDate today = LocalDate.now();
            // Format the date to get the day name in Arabic
            String dayNameInArabic = today.format(formatter);
            System.out.println("Now " + dayNameInArabic);

            for (Seance seance : Listsceance) {

                if (seance.getDay_sceance().equals(dayNameInArabic)) {
                    int id_matier = seance.getMatiere().getId();
                    if (!map_matiere.containsKey(id_matier)) {
                        map_matiere.put(id_matier, seance.getMatiere());
                    } else {
                        System.out.println("This Matiere Deja Exist:" + seance.getMatiere().getMatiereEtdAr());
                    }
                }

            }

        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
        }

        // si Map null return empty list
        List<Matiere> listMatiere = (map_matiere.isEmpty()) ? Collections.emptyList() : new ArrayList<>(map_matiere.values());
        return listMatiere;
    }

    public Matiere getMatier_Of_TimNow_Etud(List<Seance> seances) { // maitiere befor timeFin of matieres todey      
        boolean bool = true;
        Seance seance_etud = null;
        for (Seance seance : seances) {
            if (bool) {
                seance_etud = seances.get(0);
                bool = false;
            }
//            if(seance.getFinTime().isBefore(seance_etud.getFinTime())
//                    && seance.getFinTime().isAfter(LocalTime.now() ) ){
           if ((seance.getFinTime().isAfter(LocalTime.now())
                    && seance_etud.getFinTime().isBefore(LocalTime.now()))
                    || (seance.getFinTime().isAfter(LocalTime.now())
                    && seance_etud.getFinTime().equals(LocalTime.now()))) {
                seance_etud = seance;
            }
        }
        JOptionPane.showMessageDialog(null, "seance_etud :  "+seance_etud.getMatiere().getMatiereEtdAr()+"  :"
        +seance_etud.getFinTime());
        if(seance_etud.getFinTime().isBefore(LocalTime.now()))
            return null;
        return seance_etud.getMatiere();
    }

    public static void main(String[] args) {
        new MatiereService().getListMatierByDay();

        System.out.println(new MatiereService().getListMatierByDay());

        LocalTime time1 = LocalTime.of(23, 00, 00);  // 9:30 AM
        LocalTime time2 = LocalTime.of(22, 00, 00); // 2:45 PM
        Duration d1 = Duration.between(LocalTime.now(), time1);
        Duration d2 = Duration.between(LocalTime.now(), time2);
        System.out.println(Duration.between(LocalTime.now(), time1));
        System.out.println(Duration.between(LocalTime.now(), time2));

        int hours = (int) d1.toHours();
        int minutes = (int) (d1.toMinutes() % 60);
        int seconds = (int) (d1.getSeconds() % 60);

        LocalTime t1 = LocalTime.of(hours, minutes, seconds);

        hours = (int) d2.toHours();
        minutes = (int) (d2.toMinutes() % 60);
        seconds = (int) (d2.getSeconds() % 60);

        LocalTime t2 = LocalTime.of(hours, minutes, seconds);
        if (t1.isBefore(t2)) {
            System.out.println("first    1" + time1);
            System.out.println("first  2 " + time2);
        } else {
            if (t1.isAfter(t2)) {
                System.out.println("first  2 " + time2 + "   ");
                System.out.println("first   1  " + time1 + "  ");
            }
        }
    }
}
