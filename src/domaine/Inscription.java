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
public class Inscription {
    private int id;
    private Etudiant etudiant;
    private Matiere matiere;
    private LocalDate dateInscription;

    public Inscription() {
    }

    public Inscription(int id, Etudiant etudiant, Matiere matiere, LocalDate dateInscription) {
        this.id = id;
        this.etudiant = etudiant;
        this.matiere = matiere;
        this.dateInscription = dateInscription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", etudiant=" + etudiant + ", matiere=" + matiere + ", dateInscription=" + dateInscription + '}';
    }

    public static LocalDate now() {
        return LocalDate.now();
    }
    
    
    
    
}
