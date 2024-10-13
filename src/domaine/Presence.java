/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.time.LocalDate;

/**
 *
 * @author farid
 */
public class Presence {
    private int id;
    private Etudiant Etudiant;
    private Matiere Matiere;
    private  Seance Seance;
    private LocalDate datePresence;
    private boolean  etat;

    public Presence() {
    }

    public Presence(int id, Etudiant Etudiant, Matiere Matiere, Seance Seance, LocalDate datePresence, boolean etat) {
        this.id = id;
        this.Etudiant = Etudiant;
        this.Matiere = Matiere;
        this.Seance = Seance;
        this.datePresence = datePresence;
        this.etat = etat;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }


    public int getId() {
        return id;
    }

    public Etudiant getEtudiant() {
        return Etudiant;
    }

    public Matiere getMatiere() {
        return Matiere;
    }

    public Seance getSeance() {
        return Seance;
    }

    public LocalDate getDatePresence() {
        return datePresence;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtudiant(Etudiant Etudiant) {
        this.Etudiant = Etudiant;
    }

    public void setMatiere(Matiere Matiere) {
        this.Matiere = Matiere;
    }

    public void setSeance(Seance Seance) {
        this.Seance = Seance;
    }

    public void setDatePresence(LocalDate datePresence) {
        this.datePresence = datePresence;
    }

    
    
    
    
}
