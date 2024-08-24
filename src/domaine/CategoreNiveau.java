/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaine;

/**
 *
 * @author client
 */
public class CategoreNiveau {
    
 private int id;
    private String categore_niveau_ar;
    private String categore_niveau_fr;
    private String description;

    public CategoreNiveau() {
    }
    
    public CategoreNiveau(int id, String categore_niveau_ar, String categore_niveau_fr, String description) {
        this.id = id;
        this.categore_niveau_ar = categore_niveau_ar;
        this.categore_niveau_fr = categore_niveau_fr;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCategore_niveau_ar() {
        return categore_niveau_ar;
    }

    public String getCategore_niveau_fr() {
        return categore_niveau_fr;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategore_niveau_ar(String categore_niveau_ar) {
        this.categore_niveau_ar = categore_niveau_ar;
    }

    public void setCategore_niveau_fr(String categore_niveau_fr) {
        this.categore_niveau_fr = categore_niveau_fr;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    
    
    
}

