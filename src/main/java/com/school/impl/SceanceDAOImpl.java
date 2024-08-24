/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.NiveauEtude;
import domaine.Seance;
import domaine.Seance_Matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.DAO.AbstractDAO;



public class SceanceDAOImpl extends AbstractDAO<Seance> {

    public SceanceDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
                return "sceance";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO sceance (num_sceance, time_sceance, fin_time, "
                + "day_sceance,date_sceance,termine,id_matiere) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE sceance SET num_sceance = ?, time_sceance = ?,fin_time = ?,"
                + " day_sceance = ?,date_sceance=?,termine=?,id_matiere=? WHERE id = ?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Seance entity ) throws SQLException {
        statement.setInt(1, entity.getNumSeance());
        statement.setObject(2,  entity.getTimeSeance());
        statement.setObject(3, entity.getFinTime());
        statement.setString(4, entity.getDay_sceance());
        statement.setDate(5,java.sql.Date.valueOf( entity.getDate_sceance()));
        statement.setBoolean(6, entity.isTerminate());
        statement.setInt(7, entity.getMatiere().getId());
    }
  
    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Seance entity) throws SQLException {
        statement.setInt(1, entity.getNumSeance());
        statement.setObject(2, entity.getTimeSeance());
        statement.setObject(3, entity.getFinTime());
        statement.setString(4, entity.getDay_sceance());
        statement.setDate(5, java.sql.Date.valueOf( entity.getDay_sceance()));
        statement.setBoolean(6, entity.isTerminate());
       statement.setInt(7, entity.getMatiere().getId());
        statement.setInt(8, entity.getId());
    }

    @Override
    protected Seance mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Seance seance = new Seance();
        seance.setId(resultSet.getInt("id"));
        seance.setNumSeance(resultSet.getInt("num_sceance"));
        seance.setTimeSeance(resultSet.getObject("time_sceance",LocalTime.class));
        seance.setFinTime(resultSet.getObject("fin_time",LocalTime.class));
        seance.setDay_sceance(resultSet.getString("day_sceance"));       
        seance.setDate_sceance(resultSet.getDate("date_sceance").toLocalDate());
        seance.setTerminate(resultSet.getBoolean("termine"));
        MatiereDAOImpl matier_dao=new MatiereDAOImpl(connection);
        seance.setMatiere(matier_dao.findById(resultSet.getInt("id_matiere")));
        return seance;
    }
    
    public List<Seance>  getSeanceOfMatiere(int id_matier) {
        List<Seance> entitys =  new ArrayList<>();
        try {
            String query = " SELECT  * From  "+getTableName()+" where id_matiere=? ";
            
           PreparedStatement statement = connection.prepareStatement(query);
         
           statement.setInt(1,id_matier );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
               Seance  entity = mapResultSetToEntity(resultSet);
               entitys.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entitys;
    }

    
}
