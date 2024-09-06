/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.time.LocalDate;
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
    private Enseignant enseignant;

    public Seance(int id, int numSeance, LocalTime timeSeance, LocalTime finTime, String day_sceance, LocalDate date_sceance, boolean terminate, Matiere matiere, Enseignant enseignant) {
        this.id = id;
        this.numSeance = numSeance;
        this.timeSeance = timeSeance;
        this.finTime = finTime;
        this.day_sceance = day_sceance;
        this.date_sceance = date_sceance;
        this.terminate = terminate;
        this.matiere = matiere;
        this.enseignant = enseignant;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
    
    
    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
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

    @Override
    public String toString() {
        return "Seance{" + "id=" + id + ", numSeance=" + numSeance + ", timeSeance=" + timeSeance + ", finTime=" + finTime + ", day_sceance=" + day_sceance + ", date_sceance=" + date_sceance + ", terminate=" + terminate + ", matiere=" + matiere + '}';
    }
       
}
