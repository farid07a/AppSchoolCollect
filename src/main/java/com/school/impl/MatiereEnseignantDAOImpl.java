/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.Enseignant;
import domaine.Matiere;
import domaine.MatiereEnseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.DAO.MatiereEnseignantDAO;

/**
 *
 * @author farid
 */
public class MatiereEnseignantDAOImpl extends AbstractDAO<MatiereEnseignant> implements MatiereEnseignantDAO{

    public MatiereEnseignantDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "matiere_by_enseignant";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO "+getTableName()+" (id_enseignant,id_matiere) VALUES(?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE "+getTableName()+" SET id_enseignant=?, id_matiere=? WHERE id=? ";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, MatiereEnseignant t) throws SQLException {
        statement.setInt(1, t.getEnseignant().getId());
        statement.setInt(2, t.getMatiere().getId());
        
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, MatiereEnseignant t) throws SQLException {
        statement.setInt(1, t.getEnseignant().getId());
        statement.setInt(2, t.getMatiere().getId());
        statement.setInt(3, t.getId());
    }

    @Override
    protected MatiereEnseignant mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        int id =resultSet.getInt("id");
        Matiere matiere=new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere"));
        Enseignant enseignat=new EnseignantDAOImpl(connection).findById(resultSet.getInt("id_enseignant"));        
        return new MatiereEnseignant(id, enseignat, matiere, LocalDate.now());
    }

    @Override
    public List<Enseignant> findEnseignantByMatiereId(Matiere Matiere) throws SQLException{
        String Query="SELECT * from "+getTableName()+" WHERE id_matiere=?";
        List <Enseignant> list_enseignant=new ArrayList<>();
        
        PreparedStatement statement=connection.prepareStatement(Query);
        statement.setInt(1, Matiere.getId());
        
        ResultSet resultSet=statement.executeQuery();
        
        while (resultSet.next()) {
            
                list_enseignant.add(mapResultSetToEntity(resultSet).getEnseignant());
            }
        
        return list_enseignant;
    }

    @Override
    public List<Matiere> findMatiereByEnseignantId(Enseignant Enseignant) throws SQLException{
        String Query="SELECT * from "+getTableName()+" WHERE id_matiere = ?";
        List <Matiere> list_matiere=new ArrayList<>();
        
        PreparedStatement statement=connection.prepareStatement(Query);
        statement.setInt(1, Enseignant.getId());
        ResultSet resultSet=statement.executeQuery();
        
        while (resultSet.next()) {
                list_matiere.add(mapResultSetToEntity(resultSet).getMatiere());
            }
        
        return list_matiere;
    }
    
}
