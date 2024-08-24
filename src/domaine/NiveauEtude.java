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
public class NiveauEtude {
    private int id;
    private String niveauInitialAr;
    private String niveauInitialFr;
    private String description;
    private int categore_niveau_id;

    public NiveauEtude() {
    }
    
    public NiveauEtude(int id, String niveauInitialAr, String niveauInitialFr, String description,int categore_niveau_id) {
        this.id = id;
        this.niveauInitialAr = niveauInitialAr;
        this.niveauInitialFr = niveauInitialFr;
        this.description = description;
        this.categore_niveau_id=categore_niveau_id;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNiveauInitialAr() {
        return niveauInitialAr;
    }

    public void setNiveauInitialAr(String niveauInitialAr) {
        this.niveauInitialAr = niveauInitialAr;
    }

    public String getNiveauInitialFr() {
        return niveauInitialFr;
    }


    public void setNiveauInitialFr(String niveauInitialFr) {
        this.niveauInitialFr = niveauInitialFr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategore_niveau_id() {
        return categore_niveau_id;
    }

    public void setCategore_niveau_id(int categore_niveau_id) {
        this.categore_niveau_id = categore_niveau_id;
    }
    
    
    
    
    
}
