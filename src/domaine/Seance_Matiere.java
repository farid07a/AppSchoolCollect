/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaine;

import java.time.LocalDate;


public class Seance_Matiere {

    int id;
    Seance seance;
    Matiere matiere;
    boolean termine;
    LocalDate date;
    
    

    public Seance_Matiere(int id ,Seance seance, Matiere matiere,boolean termine,LocalDate date) {
        this.id=id;
        this.seance = seance;
        this.matiere = matiere;
        this.termine=termine;
       this.date=date;
    }

    public Seance_Matiere() {
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Seance getSeance() {
        return seance;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public boolean isTermine() {
        return termine;
    }

    public void setTermine(boolean termine) {
        this.termine = termine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
    
    
}
