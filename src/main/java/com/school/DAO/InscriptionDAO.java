/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.DAO;

import domaine.Etudiant;
import domaine.Inscription;
import java.util.List;

/**
 *
 * @author farid
 */
public interface InscriptionDAO {
    List<Inscription> findByEtudiantId(int etudiantId);
    List<Inscription> findByMatiereId(int matiereId);
    List<Etudiant> findEtudiantsByMatiereId(int matiereId); // New method to get Etudiants by Matiere ID
    
}
