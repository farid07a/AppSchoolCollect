/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import domaine.Etudiant;
import domaine.Inscription;
import domaine.Matiere;
import domaine.Payement;
import domaine.PayementDetaille;
import domaine.Seance;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import main.java.com.school.DAO.PayementDetailleDAO;
import main.java.com.school.impl.PayementDAOImpl;
import main.java.com.school.impl.PayementDetailleDAOImp;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author client
 */
public class PayementService {

    public void getPayementOnCours(List<Inscription> inscriptions) {
    }

    public boolean CheckIfSeanceTODayPayeeAndSave(Payement Last_payement) {
        if (Last_payement != null && Last_payement.getNb_seance() > 0) {
            return true;
        } else {
           // JOptionPane.showMessageDialog(null, " check message ");
            return false;
        }

    }

    public void SaveDettesInPayement(Payement Last_payement, Etudiant etudiant, Matiere matiere, Seance seance) throws DatabaseConnectionException {
        
        if (Last_payement != null && Last_payement.getTypePayement().equals("ديون")
                && Last_payement.getNb_seance() != 0 && Last_payement.getNb_seance()+1<=matiere.getNum_sceance_moins()) {
            int nb_seance_Dettes = Last_payement.getNb_seance() +1; // save ++
            Last_payement.setNb_seance(nb_seance_Dettes);
            // Payement First_payement = new Payement(0, etudiant, matiere, null, "ديون", matiere.getPrix(), matiere.getPrix(), matiere.getPrix(), LocalDate.now(), -1);
            if (new PayementDAOImpl(ConnectionDB.getConnection()).update(Last_payement) > 0) {
                PayementDetaille payementDetaille = new PayementDetaille(0, Last_payement, seance, LocalDate.now(), 0);
                new PayementDetailleDAOImp(ConnectionDB.getConnection()).save(payementDetaille);
                JOptionPane.showMessageDialog(null, "save dettes ++");
            }
        } else {

            Payement First_payement = new Payement(0, etudiant, matiere, seance, "ديون", matiere.getPrix(),0.0, 0.0, LocalDate.now(), 1);
            if (new PayementDAOImpl(ConnectionDB.getConnection()).save(First_payement) > 0) {
                Payement last_payement_save= new PayementDAOImpl(ConnectionDB.getConnection()).getlast();
                PayementDetaille payementDetaille = new PayementDetaille(0, last_payement_save, seance, LocalDate.now(), 0);
                new PayementDetailleDAOImp(ConnectionDB.getConnection()).save(payementDetaille);
                JOptionPane.showMessageDialog(null, "save dettes first +1 ");
            }// save -1
        }

    }
    
    public void updatePayementDetailleWhenPayéé(int id_payement,int nbr_seance_payee,double prix_seance) throws DatabaseConnectionException{
        List<PayementDetaille> payementDetailles= new PayementDetailleDAOImp(ConnectionDB.getConnection()).getPayementDetailleCredit(id_payement);
        int i=0;
        while(i<nbr_seance_payee){
            for (PayementDetaille payementDetaille : payementDetailles) {
                if(payementDetaille.getMontant()==0){
                        payementDetaille.setMontant(prix_seance);
                        payementDetaille.setDate_payement(LocalDate.now());
                        i++;
                        new PayementDetailleDAOImp(ConnectionDB.getConnection()).update(payementDetaille);
                        System.out.println(""+payementDetaille.toString());
                        break;
                }}
            }
 
    }


    public void existCredit(Etudiant etudiant, Matiere matiere) throws DatabaseConnectionException {
        List<Payement> payements_Dettes = new PayementDAOImpl(ConnectionDB.getConnection()).getPayementOfEtudiantByMatier(etudiant, matiere, "ديون");

    }

}
