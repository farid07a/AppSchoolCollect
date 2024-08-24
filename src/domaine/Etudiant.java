/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author farid
 */
public class Etudiant {
    private int id;
    private String matricule;
    private String codeBare;
    private String prenom;
    private String nom;
    private LocalDate dateBirth;
    private String adress;
    private String tel;
    private String email;
    private String renseignementPe;
    private CategoreNiveau ctegore_niveau;
    private NiveauEtude niveau;
    private byte[] image;

    public Etudiant() {
        
    }
    
    public Etudiant(int id, String matricule, String codeBare, String prenom, String nom, LocalDate dateBirth,String adress, String tel,String email, String renseignementPe,CategoreNiveau ctegore_niveau, NiveauEtude niveau, byte[] image) {
        this.id = id;
        this.matricule = matricule;
        this.codeBare = codeBare;
        this.prenom = prenom;
        this.nom = nom;
        this.dateBirth = dateBirth;
        this.adress = adress;
        this.tel = tel;
        this.email=email;
        this.renseignementPe = renseignementPe;
        this.ctegore_niveau= ctegore_niveau;
        this.niveau = niveau;
        this.image = image;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCodeBare() {
        return codeBare;
    }

    public void setCodeBare(String codeBare) {
        this.codeBare = codeBare;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRenseignementPe() {
        return renseignementPe;
    }

    public void setRenseignementPe(String renseignementPe) {
        this.renseignementPe = renseignementPe;
    }

    public NiveauEtude getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauEtude niveau) {
        this.niveau = niveau;
    }
    
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getAdress() {
        return adress;
    }

    public String getEmail() {
        return email;
    }

    public CategoreNiveau getCtegore_niveau() {
        return ctegore_niveau;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCtegore_niveau(CategoreNiveau ctegore_niveau) {
        this.ctegore_niveau = ctegore_niveau;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "id=" + id + ", matricule=" + matricule + ", codeBare=" + codeBare + ", prenom=" + prenom + ", nom=" + nom + ", dateBirth=" + dateBirth + ", tel=" + tel + ", renseignementPe=" + renseignementPe + ", niveau=" + niveau + ", image=" + image + '}';
    }   
    
}
