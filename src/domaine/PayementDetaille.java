/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaine;

import java.time.LocalDate;


public class PayementDetaille {
    
    private int id;
    private Payement payement;
    private Seance seance;
    private LocalDate date_payement;
    private double montant;

    public PayementDetaille() {
    }

    public PayementDetaille(int id, Payement payement, Seance seance, LocalDate date_payement, double montant) {
        this.id = id;
        this.payement = payement;
        this.seance = seance;
        this.date_payement = date_payement;
        this.montant = montant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayement(Payement payement) {
        this.payement = payement;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public void setDate_payement(LocalDate date_payement) {
        this.date_payement = date_payement;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public Payement getPayement() {
        return payement;
    }

    public Seance getSeance() {
        return seance;
    }

    public LocalDate getDate_payement() {
        return date_payement;
    }

    public double getMontant() {
        return montant;
    }

   
  

  
    
    
}
