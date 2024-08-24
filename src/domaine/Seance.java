/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author farid
 */
public class Seance {
    private int id ;
    private int numSeance ;
    private LocalTime timeSeance ;
    private LocalTime finTime;
    private String day_sceance ;
    private LocalDate date_sceance ;
    private boolean terminate;
    private  Matiere matiere;
    
    

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
    

    public Seance(int id, int numSeance, LocalTime timeSeance, LocalTime finTime,String day_sceance, LocalDate date_sceance,boolean termine, Matiere matiere ) {
        this.id = id;
        
        this.numSeance = numSeance;
        this.timeSeance = timeSeance;
        this.finTime = finTime;
        this.day_sceance = day_sceance;
        this.date_sceance = date_sceance;
        this.terminate=termine;
        this.matiere=matiere;
    }

    public Seance() {
    }

    public int getId() {
        return id;
    }

    public int getNumSeance() {
        return numSeance;
    }

    public LocalTime getTimeSeance() {
        return timeSeance;
    }

    public String getDay_sceance() {
        return day_sceance;
    }

    public LocalDate getDate_sceance() {
        return date_sceance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumSeance(int numSeance) {
        this.numSeance = numSeance;
    }

    public void setTimeSeance(LocalTime timeSeance) {
        this.timeSeance = timeSeance;
    }

   
    public void setDay_sceance(String day_sceance) {
        this.day_sceance = day_sceance;
    }

    public void setDate_sceance(LocalDate date_sceance) {
        this.date_sceance = date_sceance;
    }

    public LocalTime getFinTime() {
        return finTime;
    }

    public void setFinTime(LocalTime finTime) {
        this.finTime = finTime;
    }

    public void setMatiere(Matiere id_matiere) {
        this.matiere = id_matiere;
    }

    public Matiere getMatiere() {
        return matiere;
    }
    
    
}
