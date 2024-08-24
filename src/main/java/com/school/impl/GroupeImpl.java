/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.Etudiant;
import domaine.Groupe;
import domaine.Inscription;
import domaine.MatiereEnseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.DAO.GroupeDAO;

/**
 *
 * @author farid
 */
public class GroupeImpl extends AbstractDAO<Groupe> implements GroupeDAO {

    public GroupeImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "groupe";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO groupe (id, , name_groupe) VALUES (?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE groupe SET name_groupe=? WHERE id=? ";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Groupe t) throws SQLException {
        statement.setString(1, t.getName_group());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Groupe t) throws SQLException {
        statement.setString(1, t.getName_group());
        statement.setInt(2, t.getId());
    }

    @Override
    protected Groupe mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Groupe group;
        int id = resultSet.getInt("id");
        String name_groupe = resultSet.getString("name_groupe");
        group = new Groupe(id, name_groupe);
        return group;
    }

    
    public List<Groupe> findGroupsByMatiereAndEnseignat(MatiereEnseignant matiere_enseignat,boolean all ) {
        List<Groupe> list_groups = new ArrayList<>();

        try {
            
            //String GroupsByMatiere = "SELECT * FROM " + getTableName() + " WHERE id_etudiant=?";
            String AllGroupsByMatiere = " SELECT groupe.id,groupe.name_groupe FROM groupe,groupe_matiere,matiere,matiere_by_enseignant,enseignant\n"
                    + " WHERE groupe.id=groupe_matiere.id_groupe AND\n"
                    + " groupe_matiere.id_matiere = matiere.id AND\n"
                    + " matiere.id=matiere_by_enseignant.id_matiere AND\n"
                    + " matiere_by_enseignant.id_enseignant=enseignant.id AND\n"
                    + " matiere.id=? AND enseignant.id=? ";

            String QueryOnlyByEnseignant = "SELECT DISTINCT groupe.id,groupe.name_groupe FROM groupe,groupe_matiere,matiere,matiere_by_enseignant,enseignant\n"
                    + " WHERE groupe.id=groupe_matiere.id_groupe AND\n"
                    + " groupe_matiere.id_matiere = matiere.id AND\n"
                    + " groupe_matiere.id_enseignant=enseignant.id AND \n"
                    + "	matiere.id=? AND enseignant.id=?";
           
            String Query = (all)?AllGroupsByMatiere:QueryOnlyByEnseignant;

            PreparedStatement statement = connection.prepareStatement(Query);
            statement.setInt(1, matiere_enseignat.getMatiere().getId());
            statement.setInt(2, matiere_enseignat.getEnseignant().getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Groupe groupe = mapResultSetToEntity(resultSet);
                list_groups.add(groupe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_groups;

    }

}
