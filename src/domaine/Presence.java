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
    private Seance_Matiere Seance_matier;
    private LocalDate datePresence;

    public Presence() {
    }

    public Presence(int id, Etudiant Etudiant, Matiere Matiere, Seance_Matiere Seance_matier, LocalDate datePresence) {
        this.id = id;
        this.Etudiant = Etudiant;
        this.Matiere = Matiere;
        this.Seance_matier = Seance_matier;
        this.datePresence = datePresence;
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

    public Seance_Matiere getSeance_matier() {
        return Seance_matier;
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

    public void setSeance_matier(Seance_Matiere Seance_matier) {
        this.Seance_matier = Seance_matier;
    }

    public void setDatePresence(LocalDate datePresence) {
        this.datePresence = datePresence;
    }

    
    
    
    
}
