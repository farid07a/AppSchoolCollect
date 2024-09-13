/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.DAO;

import domaine.Enseignant;
import domaine.EnseignantMatiere;
import domaine.Matiere;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author farid
 */
public interface EnseignantMatiereDAO {
    List<Enseignant> findEnseignantByMatiereId(Matiere MatiereId)throws SQLException;
    List<Matiere> findMatiereByEnseignantId(Enseignant EnseignantId)throws SQLException;
    EnseignantMatiere findByNamesEnseignantMatiere(Enseignant enseignant,Matiere matiere)throws SQLException;
    
    
}
