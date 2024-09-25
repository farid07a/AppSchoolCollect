/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.school.impl;

import domaine.Etudiant;
import domaine.Matiere;
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
import javax.swing.JOptionPane;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

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
        return "INSERT INTO presence (id_etudiant,id_matiere,id_seance,date_presence,etat) VALUES (?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE presence SET id_etudiant=?,id_matiere=?,id_seance=?,date_presence=?, etat=? WHERE id=?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Presence presence) throws SQLException {
        statement.setInt(1, presence.getEtudiant().getId());
        statement.setInt(2, presence.getMatiere().getId());
        statement.setInt(3, presence.getSeance().getId());
        statement.setDate(4, java.sql.Date.valueOf(presence.getDatePresence()));
        statement.setBoolean(5, presence.isEtat());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Presence presence) throws SQLException {
        statement.setInt(1, presence.getEtudiant().getId());
        statement.setInt(2, presence.getMatiere().getId());
        statement.setInt(3, presence.getSeance().getId());
        statement.setDate(4, java.sql.Date.valueOf(presence.getDatePresence()));
        statement.setBoolean(5, presence.isEtat());
        statement.setInt(6, presence.getId());
    }

    @Override
    protected Presence mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Presence presence = new Presence();
        presence.setId(resultSet.getInt("id"));
        presence.setEtudiant(new EtudiantDAOImpl(connection).findById(resultSet.getInt("id_etudiant")));
        presence.setMatiere(new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere")));
        presence.setSeance(new SeanceDAOImpl(connection).findById(resultSet.getInt("id_seance")));
        presence.setDatePresence(resultSet.getDate("date_presence").toLocalDate());
        presence.setEtat(resultSet.getBoolean("etat"));
        return presence;
    }

    public Presence getPresenceOFetudiantInSeance(Etudiant etudiant, Seance seance, LocalDate date) {
        Presence presence = new Presence();
        try {
            String query = "SELECT * FROM " + getTableName()
                    + "  WHERE id_etudiant=? AND id_seance=? AND date_presence=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, etudiant.getId());
            statement.setInt(2, seance.getId());
            statement.setDate(3, java.sql.Date.valueOf(date));
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

    public List<Presence> getPresenceByTowDates(Etudiant etudiant, Matiere matiere, LocalDate date_start, LocalDate date_fin) {
        List<Presence> presences = new ArrayList<>();
        try {
            String Query = "SELECT * FROM  " + getTableName()
                    + "  WHERE id_etudiant =? AND id_matiere=? AND date_presence >= ? AND date_presence <= ?";

            PreparedStatement statement = connection.prepareStatement(Query);
            statement.setInt(1, etudiant.getId());
            statement.setInt(2, matiere.getId());
            statement.setDate(3, java.sql.Date.valueOf(date_start));
            statement.setDate(4, java.sql.Date.valueOf(date_fin));

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

    public static void main(String[] args) {
        Etudiant etudiant;
        try {
            etudiant = new EtudiantDAOImpl(ConnectionDB.getConnection()).findById(2);
            Seance seance = new SeanceDAOImpl(ConnectionDB.getConnection()).findById(30);
            Presence presence = new PresenceDAOImpl(ConnectionDB.getConnection()).getPresenceOFetudiantInSeance(etudiant, seance, LocalDate.now());
            
            JOptionPane.showMessageDialog(null, "etat : "+presence.isEtat());
            
            if(!presence.isEtat()){
            JOptionPane.showMessageDialog(null, "etat : "+presence.isEtat());
            presence.setEtat(true);
            }
            if( new PresenceDAOImpl(ConnectionDB.getConnection()).update(presence)>0){
               JOptionPane.showMessageDialog(null, "update ");
               
           }else{
               JOptionPane.showMessageDialog(null, "error ");
           
           }
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(PresenceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
