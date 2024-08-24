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
public class MatiereEnseignant {
    private int id;
    private Enseignant enseignant;
    private Matiere matiere;
    private LocalDate date_start;

    public MatiereEnseignant(int id, Enseignant enseignant, Matiere matiere, LocalDate date_start) {
        this.id = id;
        this.enseignant = enseignant;
        this.matiere = matiere;
        this.date_start = date_start;
    }

    public MatiereEnseignant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public LocalDate getDate_start() {
        return date_start;
    }

    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    @Override
    public String toString() {
        return "MatiereEnseignant{" + "id=" + id + ", enseignant=" + enseignant + ", matiere=" + matiere + ", date_start=" + date_start + '}';
    }
    
    
    
    
}
