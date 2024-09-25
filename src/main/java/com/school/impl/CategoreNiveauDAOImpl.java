/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.school.impl;

import domaine.CategoreNiveau;
import domaine.NiveauEtude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author client
 */
public class CategoreNiveauDAOImpl extends AbstractDAO<CategoreNiveau>{

    public CategoreNiveauDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "categore_niveau";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO categore_niveau (categore_niveau_ar, categore_niveau_fr, description) VALUES (?, ?, ?)";
    }
    
    @Override
    protected String getUpdateQuery() {
        return "UPDATE categore_niveau SET categore_niveau_ar = ?, categore_niveau_fr = ?, description = ? WHERE id = ?";
    }

   
    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, CategoreNiveau entity) throws SQLException {
        statement.setString(1, entity.getCategore_niveau_ar());
        statement.setString(2, entity.getCategore_niveau_fr());
        statement.setString(3, entity.getDescription());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, CategoreNiveau entity) throws SQLException {
        statement.setString(1, entity.getCategore_niveau_ar());
        statement.setString(2, entity.getCategore_niveau_fr());
        statement.setString(3, entity.getDescription());
        statement.setInt(4, entity.getId());
    }

    @Override
    protected CategoreNiveau mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        CategoreNiveau categoreNiveau = new CategoreNiveau();
        categoreNiveau.setId(resultSet.getInt("id"));
        categoreNiveau.setCategore_niveau_ar(resultSet.getString("categore_niveau_ar"));
        categoreNiveau.setCategore_niveau_fr(resultSet.getString("categore_niveau_fr"));
        categoreNiveau.setDescription(resultSet.getString("description"));
        return categoreNiveau;
    }

     public CategoreNiveau getCategory_by_name( String Catego){        
        CategoreNiveau categoreNiveau = new CategoreNiveau();
         String  SELECT_Category=" SELECT  * FROM   "
                  + getTableName()+"  WHERE  categore_niveau.categore_niveau_ar = N'"+Catego+"' ";

       PreparedStatement statement;
            
        try {
            statement = connection.prepareStatement(SELECT_Category);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                categoreNiveau =mapResultSetToEntity(resultSet);
            }else{
            categoreNiveau=null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NiveauEtudeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return categoreNiveau ;
   
    }
        
    public static void main(String[] args) {
        try {
            System.out.println(  new CategoreNiveauDAOImpl(new ConnectionDB().getConnection()).findById(6));
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(CategoreNiveauDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
