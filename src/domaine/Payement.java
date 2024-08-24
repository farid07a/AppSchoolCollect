/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaine;

import java.time.LocalDate;
import java.util.logging.Logger;
import sun.util.resources.LocaleData;

/**
 *
 * @author client
 */
public class Payement {
    int id;
    Etudiant etudiant;
    Matiere matiere;
    //Seance_Matiere seance_matiere;
    Seance seance;
    String typePayement;
    Double prix;
    Double prixPaye;
    Double prixTotal;
    LocalDate date;

    public Payement() {
    }

    public Payement(int id, Etudiant etudiant, Matiere matiere, Seance seance, String typePayement, Double prix, Double prixPaye, Double prixTotal,LocalDate date) {
        this.id = id;
        this.etudiant = etudiant;
        this.matiere = matiere;
        //this. seance_matiere=seance_matiere;
        this.seance = seance;
      
        this.typePayement = typePayement;
        this.prix = prix;
        this.prixPaye = prixPaye;
        this.prixTotal = prixTotal;
        this.date=date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public void setTypePayement(String typePayement) {
        this.typePayement = typePayement;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setPrixPaye(Double prixPaye) {
        this.prixPaye = prixPaye;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getId() {
        return id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public Seance getSeance() {
        return seance;
    }

    public String getTypePayement() {
        return typePayement;
    }

    public Double getPrix() {
        return prix;
    }

    public Double getPrixPaye() {
        return prixPaye;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

//    public Seance_Matiere getSeance_matiere() {
//        return seance_matiere;
//    }
//
//    public void setSeance_matiere(Seance_Matiere seance_matiere) {
//        this.seance_matiere = seance_matiere;
//    }
    
    
    
     
}
