/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.Enseignant;
import domaine.Groupe;
import domaine.GroupeMatiere;
import domaine.Inscription;
import domaine.Matiere;
import domaine.MatiereEnseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.DAO.AbstractDAO;

/**
 *
 * @author farid
 */
public class GroupeMatiereDAOImpl extends AbstractDAO<GroupeMatiere> {

    public GroupeMatiereDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "groupe_matiere";
    }

    @Override
    protected String getInsertQuery() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return "INSERT INTO " + getTableName() + " (id_groupe,id_matiere) VALUES (?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return "UPDATE " + getTableName() + " SET id_groupe=?,id_matiere=? WHERE id=?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, GroupeMatiere t) throws SQLException {
        statement.setInt(1, t.getGroupe().getId());
        statement.setInt(2, t.getMatiere().getId());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, GroupeMatiere t) throws SQLException {
        statement.setInt(1, t.getGroupe().getId());
        statement.setInt(2, t.getMatiere().getId());
        statement.setInt(3, t.getId());
    }

    @Override
    protected GroupeMatiere mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Groupe groupe = new GroupeImpl(connection).findById(resultSet.getInt("id_groupe"));
        Matiere matiere = new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere"));

        return new GroupeMatiere(id, groupe, matiere);
    }

    

}
