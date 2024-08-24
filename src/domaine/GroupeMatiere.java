/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

/**
 *
 * @author farid
 */
public class GroupeMatiere {
    
    private int id;
    private Groupe groupe;
    private Matiere matiere;

    public GroupeMatiere(int id, Groupe groupe, Matiere matiere) {
        this.id = id;
        this.groupe = groupe;
        this.matiere = matiere;
    }

    public GroupeMatiere() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return "GroupeMatiere{" + "id=" + id + ", groupe=" + groupe + ", matiere=" + matiere + '}';
    }
    
    
    
}
