/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.NiveauEtude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.model.config.ConnectionDB;

/**
 *
 * @author farid
 */
public class NiveauEtudeDAOImpl extends AbstractDAO<NiveauEtude> {

    
    public NiveauEtudeDAOImpl(Connection connection) {
        super(connection);
    }

    
    @Override
    protected String getTableName() {
        return "niveau_etude";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO niveau_etude (niveau_initial_ar, niveau_initial_fr, description,categore_niveau_id) VALUES (?, ?, ?,?)";
    }
    
    @Override
    protected String getUpdateQuery() {
        return "UPDATE niveau_etude SET niveau_initial_ar = ?, niveau_initial_fr = ?, description = ?,categore_niveau_id=? WHERE id = ?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, NiveauEtude entity) throws SQLException {
        statement.setString(1, entity.getNiveauInitialAr());
        statement.setString(2, entity.getNiveauInitialFr());
        statement.setString(3, entity.getDescription());
        statement.setInt(4, entity.getCategore_niveau_id());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, NiveauEtude entity) throws SQLException {
        statement.setString(1, entity.getNiveauInitialAr());
        statement.setString(2, entity.getNiveauInitialFr());
        statement.setString(3, entity.getDescription());
        statement.setInt(4, entity.getCategore_niveau_id());
        statement.setInt(5, entity.getId());
    }

    @Override
    protected NiveauEtude mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        NiveauEtude niveauEtude = new NiveauEtude();
        niveauEtude.setId(resultSet.getInt("id"));
        niveauEtude.setNiveauInitialAr(resultSet.getString("niveau_initial_ar"));
        niveauEtude.setNiveauInitialFr(resultSet.getString("niveau_initial_fr"));
        niveauEtude.setDescription(resultSet.getString("description"));
        niveauEtude.setCategore_niveau_id(resultSet.getInt("categore_niveau_id"));
        return niveauEtude;
    }
    
    public NiveauEtude getNiveauOfCategory(String niveau, String Catego){
        
        NiveauEtude niveauEtude = null;
         String  SELECT_Niveau_By_Category="SELECT *\n" +
                                "FROM niveau_etude,categore_niveau\n" + 
                                        "WHERE niveau_etude.niveau_initial_ar = N'"+niveau+"'\n" +
                                    "  AND categore_niveau.categore_niveau_ar = N'"+Catego+"'\n" +
                                    "  and niveau_etude.categore_niveau_id= categore_niveau.id";

       PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_Niveau_By_Category);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                niveauEtude.setId(resultSet.getInt(""));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(NiveauEtudeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return niveauEtude ;
    
    }
    
 
    
    

}
