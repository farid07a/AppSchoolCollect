/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.DAO;

import domaine.Enseignant;
import java.sql.SQLException;

/**
 *
 * @author farid
 */
public interface EnseignantDAO {
    public Enseignant findByFullName(String Nom,String prenom) throws SQLException;
}
