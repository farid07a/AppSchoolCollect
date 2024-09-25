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
public class Matiere {
    private int id;
    private String matiereEtdAr;
    private String matiereEtdFr;
    private double prix;
    private NiveauEtude niveau;
    private CategoreNiveau categoreNiveau;
    private Enseignant enseignant;
    private int num_sceance_semaine; 
    private int num_sceance_moins;

    public Matiere(int id, String matiereEtdAr, String matiereEtdFr, double prix, NiveauEtude niveau, CategoreNiveau categoreNiveau, Enseignant enseignant, int num_sceance_semaine, int num_sceance_moins) {
        this.id = id;
        this.matiereEtdAr = matiereEtdAr;
        this.matiereEtdFr = matiereEtdFr;
        this.prix = prix;
        this.niveau = niveau;
        this.categoreNiveau = categoreNiveau;
        this.enseignant = enseignant;
        this.num_sceance_semaine = num_sceance_semaine;
        this.num_sceance_moins = num_sceance_moins;
    }

    public void setNum_sceance_moins(int num_sceance_moins) {
        this.num_sceance_moins = num_sceance_moins;
    }

    public void setNum_sceance_semaine(int num_sceance_semaine) {
        this.num_sceance_semaine = num_sceance_semaine;
    }

    public int getNum_sceance_moins() {
        return num_sceance_moins;
    }

    public int getNum_sceance_semaine() {
        return num_sceance_semaine;
    }

    

    public Matiere() {
   
    }

    

    @Override
    public String toString() {
        return "Matiere{" + "id=" + id + ", matiereEtdAr=" + matiereEtdAr + ", matiereEtdFr=" + matiereEtdFr + ", prix=" + prix + ", niveau=" + niveau + ", enseignant=" + enseignant + '}';
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiereEtdAr() {
        return matiereEtdAr;
    }

    public void setMatiereEtdAr(String matiereEtdAr) {
        this.matiereEtdAr = matiereEtdAr;
    }

    public String getMatiereEtdFr() {
        return matiereEtdFr;
    }

    public void setMatiereEtdFr(String matiereEtdFr) {
        this.matiereEtdFr = matiereEtdFr;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public NiveauEtude getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauEtude niveau) {
        this.niveau = niveau;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public CategoreNiveau getCategoreNiveau() {
        return categoreNiveau;
    }

    public void setCategoreNiveau(CategoreNiveau categoreNiveau) {
        this.categoreNiveau = categoreNiveau;
    }
    
    
    
    
}
