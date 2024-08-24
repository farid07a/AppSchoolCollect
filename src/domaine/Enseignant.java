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
public class Enseignant {
    private int id;
    private String nomAr;
    private String nomFr;
    private String prenomAr;
    private String prenomFr;
    private String specialite;
    private String phoneNum;
    private String Email;
 
    public Enseignant(int id, String nomAr, String nomFr, String prenomAr, String prenomFr, String specialite, String phoneNum,String email) {
        this.id = id;
        this.nomAr = nomAr;
        this.nomFr = nomFr;
        this.prenomAr = prenomAr;
        this.prenomFr = prenomFr;
        this.specialite = specialite;
        this.phoneNum = phoneNum;
        this.Email=email;
    }

    public Enseignant() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getPrenomAr() {
        return prenomAr;
    }

    public void setPrenomAr(String prenomAr) {
        this.prenomAr = prenomAr;
    }

    public String getPrenomFr() {
        return prenomFr;
    }

    public void setPrenomFr(String prenomFr) {
        this.prenomFr = prenomFr;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    

    @Override
    public String toString() {
        return "Enseignant{" + "id=" + id + ", nomAr=" + nomAr + ", nomFr=" + nomFr + ", prenomAr=" + prenomAr + ", prenomFr=" + prenomFr + ", specialite=" + specialite + ", phoneNum=" + phoneNum + '}';
    }
    
    
    
}
