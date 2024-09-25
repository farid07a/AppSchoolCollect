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
public class EnseignantMatiere {
    private int id;
    private Enseignant enseignant;
    private Matiere matiere;
    private LocalDate date_start;
    private int num_sceance_semaine;
    private int num_sceance_moins;
    private double prix;

    public EnseignantMatiere(int id, Enseignant enseignant, Matiere matiere, LocalDate date_start, int num_sceance_semaine, int num_sceance_moins, double prix) {
        this.id = id;
        this.enseignant = enseignant;
        this.matiere = matiere;
        this.date_start = date_start;
        this.num_sceance_semaine = num_sceance_semaine;
        this.num_sceance_moins = num_sceance_moins;
        this.prix = prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    public int getNum_sceance_semaine() {
        return num_sceance_semaine;
    }

    public void setNum_sceance_semaine(int num_sceance_semaine) {
        this.num_sceance_semaine = num_sceance_semaine;
    }

    public int getNum_sceance_moins() {
        return num_sceance_moins;
    }

    public void setNum_sceance_moins(int num_sceance_moins) {
        this.num_sceance_moins = num_sceance_moins;
    }

    
    public EnseignantMatiere() {
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
