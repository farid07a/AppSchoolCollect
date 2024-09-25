/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.Enseignant;
import domaine.Matiere;
import domaine.EnseignantMatiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.DAO.EnseignantMatiereDAO;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

/**
 *
 * @author farid
 */
public class EnseignantMatiereDAOImpl extends AbstractDAO<EnseignantMatiere> implements EnseignantMatiereDAO {

    public EnseignantMatiereDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "matiere_by_enseignant";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (id_enseignant,id_matiere,date_start,num_sceance_semaine,num_sceance_moins,prix) VALUES(?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET id_enseignant=?, id_matiere=?,date_start=?,num_sceance_semaine=?,num_sceance_moins=?,prix=? WHERE id=? ";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, EnseignantMatiere t) throws SQLException {
        statement.setInt(1, t.getEnseignant().getId());
        statement.setInt(2, t.getMatiere().getId());
        statement.setDate(3, java.sql.Date.valueOf(t.getDate_start()));
        statement.setInt(4, t.getNum_sceance_semaine());
        statement.setInt(5, t.getNum_sceance_moins());
        statement.setDouble(6, t.getPrix());

    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, EnseignantMatiere t) throws SQLException {
        statement.setInt(1, t.getEnseignant().getId());
        statement.setInt(2, t.getMatiere().getId());
        statement.setDate(3, java.sql.Date.valueOf(t.getDate_start()));
        statement.setInt(4, t.getNum_sceance_semaine());
        statement.setInt(5, t.getNum_sceance_moins());
        statement.setDouble(6, t.getPrix());
        statement.setInt(7, t.getId());
    }

    @Override
    protected EnseignantMatiere mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Matiere matiere = new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere"));
        Enseignant enseignat = new EnseignantDAOImpl(connection).findById(resultSet.getInt("id_enseignant"));
        LocalDate date_start = (resultSet.getDate("date_start") == null) ? LocalDate.now() : resultSet.getDate("date_start").toLocalDate();
        int num_sceance_semaine = resultSet.getInt("num_sceance_semaine");
        int num_sceance_moins = resultSet.getInt("num_sceance_moins");
        double prix=resultSet.getDouble("prix");
        return new EnseignantMatiere(id, enseignat, matiere, date_start, num_sceance_semaine, num_sceance_moins,prix);
    }
// t
    @Override
    public List<Enseignant> findEnseignantByMatiereId(Matiere Matiere) throws SQLException {
        String Query = "SELECT * from " + getTableName() + " WHERE id_matiere=?";
        List<Enseignant> list_enseignant = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(Query);
       statement.setInt(1, Matiere.getId());

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            list_enseignant.add(mapResultSetToEntity(resultSet).getEnseignant());
        }

        return list_enseignant;
    }

    @Override
    public EnseignantMatiere findByNamesEnseignantMatiere(Enseignant enseignant, Matiere matiere) throws SQLException{
        String Query = "SELECT * from " + getTableName() + " WHERE id_enseignant = ? and id_matiere=?";
         
        EnseignantMatiere enseignant_matiere=null;

        PreparedStatement statement = connection.prepareStatement(Query);
        statement.setInt(1, enseignant.getId());
        statement.setInt(2, matiere.getId());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            enseignant_matiere=mapResultSetToEntity(resultSet);
        }

        return enseignant_matiere;
    }

    
    
    @Override
    public List<Matiere> findMatiereByEnseignantId(Enseignant Enseignant) throws SQLException {
        String Query = "SELECT * from " + getTableName() + " WHERE id_enseignant = ?";
        List<Matiere> list_matiere = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(Query);
        statement.setInt(1, Enseignant.getId());
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            list_matiere.add(mapResultSetToEntity(resultSet).getMatiere());
        }

        return list_matiere;
    }

    public List<EnseignantMatiere> findEnseignantMatiereByDate(String DaySeance) throws SQLException {
        //String Query="SELECT * from "+getTableName()+" WHERE id_matiere = ?";
        String Query = "select DISTINCT matiere_by_enseignant.id,"
                + "matiere_by_enseignant.id_matiere,"
                + "matiere_by_enseignant.id_enseignant,"
                + "date_start,"
                + "matiere_by_enseignant.num_sceance_semaine,"
                + "matiere_by_enseignant.num_sceance_moins,"
                + "matiere_by_enseignant.prix\n"
                + "FROM sceance,enseignant,matiere_by_enseignant,matiere\n"
                + "WHERE sceance.id_enseignant=enseignant.id \n"
                + "AND enseignant.id = matiere_by_enseignant.id_enseignant\n"
                + "AND matiere_by_enseignant.id_matiere=matiere.id "
                + " AND sceance.day_sceance=N'"+DaySeance+"' ";
                
        List<EnseignantMatiere> listEnseignantMatiere = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(Query);
        //statement.setDate(1, java.sql.Date.valueOf(date));
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            listEnseignantMatiere.add(mapResultSetToEntity(resultSet));
        }

        return listEnseignantMatiere;
    }

    public static void main(String[] args) {
        try {
            System.out.println(new EnseignantMatiereDAOImpl(ConnectionDB.getConnection()).findEnseignantMatiereByDate("الاثنين"));
        } catch (DatabaseConnectionException | SQLException ex) {
            Logger.getLogger(EnseignantMatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
