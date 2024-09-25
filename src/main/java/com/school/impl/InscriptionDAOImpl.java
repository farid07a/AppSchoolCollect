/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.Etudiant;
import domaine.Inscription;
import domaine.Matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.DAO.InscriptionDAO;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author farid
 */
public class InscriptionDAOImpl extends AbstractDAO<Inscription> implements InscriptionDAO{

    public InscriptionDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "inscription";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO inscription (id_etudiant, id_matiere, date_inscription) VALUES (?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE inscription SET id_etudiant = ?, id_matiere = ?, date_inscription = ? WHERE id = ?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Inscription inscription) throws SQLException {
      statement.setInt(1, inscription.getEtudiant().getId());
        statement.setInt(2, inscription.getMatiere().getId());
        statement.setDate(3, java.sql.Date.valueOf(inscription.getDateInscription()));
         
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Inscription inscription) throws SQLException {
        statement.setInt(1, inscription.getEtudiant().getId());
        statement.setInt(2, inscription.getMatiere().getId());
        statement.setDate(3, java.sql.Date.valueOf(inscription.getDateInscription()));
        if (inscription.getId() > 0) {
            statement.setInt(4, inscription.getId());
        }
        
    }

    @Override
    protected Inscription mapResultSetToEntity(ResultSet resultSet) throws SQLException {
            Inscription inscription =new Inscription();
            inscription.setId(resultSet.getInt("id"));
            Etudiant etudiant = new EtudiantDAOImpl(connection).findById(resultSet.getInt("id_etudiant"));
            inscription.setEtudiant(etudiant);
            Matiere matiere=new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere"));
            inscription.setMatiere(matiere);
            inscription.setDateInscription(resultSet.getDate("date_inscription").toLocalDate());
            return inscription;
        }

    @Override
    public List<Inscription> findByEtudiantId(int etudiantId){
      List<Inscription> inscriptions = new ArrayList<>();
        Inscription  inscription = new Inscription();
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE id_etudiant=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,etudiantId );
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                inscription = mapResultSetToEntity(resultSet);
                inscriptions.add(inscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscriptions;
        
    }
       public Inscription findByEtudiantAndMatiere(Etudiant etudiant , Matiere matiere){
        Inscription  inscription = null;
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE id_etudiant=?  and id_matiere=? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,etudiant.getId() );
             statement.setInt(2,matiere.getId() );
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                inscription = mapResultSetToEntity(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscription;      
    }
    
    

    @Override
    public List<Inscription> findByMatiereId(int matiereId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Etudiant> findEtudiantsByMatiereId(int matiereId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        try {
            Connection connection=ConnectionDB.getConnection();
            InscriptionDAOImpl inscription_dao=new InscriptionDAOImpl(connection);
            
            try {
                inscription_dao.findByMatiereId(1);
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            
            System.out.println("Succes passe ");
            
            
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(InscriptionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
    }

}
