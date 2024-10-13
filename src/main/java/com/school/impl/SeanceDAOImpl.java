/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.Enseignant;
import domaine.EnseignantMatiere;
import domaine.Matiere;
import domaine.NiveauEtude;
import domaine.Seance;
import domaine.Seance_Matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;



public class SeanceDAOImpl extends AbstractDAO<Seance> {

    public SeanceDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
                return "sceance";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO sceance (num_sceance, time_sceance, fin_time, "
                + "day_sceance,date_sceance,termine,id_matiere,id_enseignant) VALUES (?,?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE sceance SET num_sceance = ?, time_sceance = ?,fin_time = ?,"
                + " day_sceance = ?,date_sceance=?,termine=?,id_matiere=? , id_enseignant=? WHERE id = ?";
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
        statement.setInt(8, entity.getEnseignant().getId());
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
       statement.setInt(8, entity.getEnseignant().getId());
        statement.setInt(9, entity.getId());
    }

    @Override
    protected Seance mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Seance seance = new Seance();
        seance.setId(resultSet.getInt("id"));
        seance.setNumSeance(resultSet.getInt("num_sceance"));
        seance.setTimeSeance(resultSet.getTime("time_sceance").toLocalTime());
        seance.setFinTime(resultSet.getTime("fin_time").toLocalTime());
        seance.setDay_sceance(resultSet.getString("day_sceance"));       
        seance.setDate_sceance(resultSet.getDate("date_sceance").toLocalDate());
        seance.setTerminate(resultSet.getBoolean("termine"));
        MatiereDAOImpl matier_dao=new MatiereDAOImpl(connection);
        seance.setMatiere(matier_dao.findById(resultSet.getInt("id_matiere")));
        EnseignantDAOImpl enseignant_impl=new EnseignantDAOImpl(connection);
        seance.setEnseignant(enseignant_impl.findById(resultSet.getInt("id_enseignant")));
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

         public List<Seance> getSeancesOfToday(LocalDate date) {
        List<Seance> seances = new ArrayList<>();
        try {
            String query = " SELECT * From "+getTableName()+"  where date_sceance =? "; //delet id matiere 
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setDate(1,java.sql.Date.valueOf( date));
           ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                seances.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }
         
     public List<Seance> getListPrevieuxSceanceWithMonthByMatiere(Matiere matiere){
         
         List<Seance> seances = new ArrayList<>();
        try {
            String query = " SELECT TOP "+matiere.getNum_sceance_moins()+  " * FROM "+getTableName()+"  WHERE id_matiere =?  ORDER BY id DESC "; //delet id matiere 
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,matiere.getId()); 
           ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                seances.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
     
     }
     
     public List<Seance> getListPrevieuxSceanceWithSemaineByEnseignantMatiere(EnseignantMatiere enseignant_matiere){
         
         List<Seance> seances = new ArrayList<>();
        try {
            String query = " SELECT TOP "+enseignant_matiere.getNum_sceance_semaine()+  " * FROM "+getTableName()+"  WHERE id_matiere =? AND id_enseignant=? ORDER BY id DESC "; //delet id matiere 
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,enseignant_matiere.getMatiere().getId()); 
           statement.setInt(2,enseignant_matiere.getEnseignant().getId()); 
           ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                seances.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
     
     }
     
     
     public List<Seance> getListPrevieuxSceanceWithSemaineByEnseignant(Matiere matiere){
         
         List<Seance> seances = new ArrayList<>();
        try {
            String query = " SELECT TOP "+matiere.getNum_sceance_semaine()+  " * FROM "+getTableName()+"  WHERE id_matiere =?  ORDER BY id DESC "; //delet id matiere 
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,matiere.getId()); 
           ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                seances.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
     
     }
     public List<Seance> getListPrevieuxSceanceWithSemaineByMatiere(Matiere matiere){
         
         List<Seance> seances = new ArrayList<>();
        try {
            String query = " SELECT TOP "+matiere.getNum_sceance_semaine()+  " * FROM "+getTableName()+"  WHERE id_matiere =?  ORDER BY id DESC "; //delet id matiere 
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,matiere.getId()); 
           ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                seances.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
     
     }
     public Seance getLastSeanceOfMatiere(Matiere matier ){
     Seance seance = null ;
     
      try {
            String query ="SELECT TOP 1 * From " + getTableName() + " WHERE id_matiere =? ORDER BY id DESC ";
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,matier.getId()); 

           ResultSet resultSet = statement.executeQuery();    
            if (resultSet.next()) {
               seance= mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return seance;
     }
     
     public Seance getLastSeanceOfEnseignant_Matiere(EnseignantMatiere enseignant_matiere ){
     Seance seance = null ;
     
      try {
          System.out.println("I ame In last SeanceOfMatiere enseignant_matiere :"+enseignant_matiere);
            String query ="SELECT TOP 1 * FROM " + getTableName() + " WHERE id_matiere =? AND id_enseignant = ? ORDER BY id DESC ";
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,enseignant_matiere.getMatiere().getId()); 
           statement.setInt(2, enseignant_matiere.getEnseignant().getId());
           ResultSet resultSet = statement.executeQuery();    
            if (resultSet.next()) {
               seance= mapResultSetToEntity(resultSet);
                System.out.println("Seance After Mapping in getLastSeanceOfEnseignant_Matiere:"+seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return seance;
     }
     
      public Seance getSeancesOfTodayOfMatiere(LocalDate date, Matiere matiere) {
        Seance seance = null;
        try {
            String query = " SELECT * From "+getTableName()+"  where date_sceance =? and id_matiere=? "; //delet id matiere 
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setDate(1,java.sql.Date.valueOf( date));
           statement.setInt(2,matiere.getId());
           ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                seance= mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seance;
    }
         
         
         
         public static void main(String[] args) {
   
             
        
        try {
            Connection conx=ConnectionDB.getConnection();
            
            EnseignantMatiere mat=new EnseignantMatiereDAOImpl(conx).findById(1);
            System.out.println(mat);
            System.out.println("--------------");
            Seance  seance=  new SeanceDAOImpl(conx).getLastSeanceOfEnseignant_Matiere(mat);
            System.out.println(seance);
            
//            for (Seance seance : lst) {
//                System.out.println(seance);
//            }
            
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(SeanceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
         
    
    
}
