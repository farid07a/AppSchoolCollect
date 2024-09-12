/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.school.impl;

import domaine.Etudiant;
import domaine.Presence;
import domaine.Seance;
import domaine.Seance_Matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.DAO.AbstractDAO;

/**
 *
 * @author client
 */
public class PresenceDAOImpl extends AbstractDAO<Presence> {

    public PresenceDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "presence";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO presence (id_etudiant,id_matiere,id_seance,date_presence) VALUES (?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE presence SET id_etudiant=?,id_matiere=?,id_seance=?,date_presence=?, id=?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Presence presence) throws SQLException {
        statement.setInt(1, presence.getEtudiant().getId());
        statement.setInt(2, presence.getMatiere().getId());
        statement.setInt(3, presence.getSeance().getId());
        statement.setDate(4, java.sql.Date.valueOf(presence.getDatePresence()));

    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Presence presence) throws SQLException {

        statement.setInt(1, presence.getEtudiant().getId());
        statement.setInt(2, presence.getMatiere().getId());
        statement.setInt(3, presence.getSeance().getId());
        statement.setDate(4, java.sql.Date.valueOf(presence.getDatePresence()));
    }

    @Override
    protected Presence mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Presence presence = new Presence();
        presence.setId(resultSet.getInt("id"));
        presence.setEtudiant(new EtudiantDAOImpl(connection).findById(resultSet.getInt("id_etudiant")));
        presence.setMatiere(new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere")));
        presence.setSeance(new SeanceDAOImpl(connection).findById(resultSet.getInt("id_seance")));
        presence.setDatePresence(resultSet.getDate("date_presence").toLocalDate());
        return presence;
    }

    public Presence getPresenceOFetudiantInSeance(Etudiant etudiant, Seance seance) {
        Presence presence = new Presence();
        try {
            String query = "SELECT * FROM " + getTableName()
                    + "  WHERE id_etudiant=? AND id_seance=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, etudiant.getId());
            statement.setInt(2, seance.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                presence = mapResultSetToEntity(resultSet);
            } else {
                presence = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return presence;
    }

    public List<Presence> getPresenceEtudiantToDay(LocalDate date) {
        List<Presence> presences = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + getTableName()
                    + "  WHERE date_presence =? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, java.sql.Date.valueOf(date));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Presence presence = mapResultSetToEntity(resultSet);
                presences.add(presence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return presences;
    }

    public List<Presence> getPresenceByEtudiant(Etudiant etudiant) {
        List<Presence> presences = new ArrayList<>();
        try {
            String Query = "SELECT * FROM  " + getTableName()
                    + "  WHERE id_etudiant =?  ";

            PreparedStatement statement = connection.prepareStatement(Query);
            statement.setInt(1, etudiant.getId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Presence presence = mapResultSetToEntity(resultSet);
                presences.add(presence);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PresenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return presences;
    }
}
