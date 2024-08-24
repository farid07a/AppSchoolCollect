/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.school.impl;

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
import main.java.com.school.DAO.SeanceMatiereDAO;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

public class SeanceMatiereDAOImpl extends AbstractDAO<Seance_Matiere> implements SeanceMatiereDAO{

    public SeanceMatiereDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
            return "sceance_by_matiere";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO sceance_by_matiere (num_sceance,name_sceance,id_matiere,id_sceance,termine,date,time,day_sceance) VALUES (?,?,?,?,?,?,?,?)";

    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE sceance_by_matiere SET num_sceance=?,name_sceance=?,id_matiere=?,id_sceance=?,termine=?,date=?,time=?,day_sceance=? WHERE id=?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Seance_Matiere entity) throws SQLException {
        statement.setInt(1, entity.getSeance().getNumSeance());
        statement.setString(2, entity.getMatiere().getMatiereEtdAr());
        statement.setInt(3, entity.getMatiere().getId());
        statement.setInt(4, entity.getSeance().getId());
        statement.setBoolean(5, entity.isTermine());
        statement.setDate(6, java.sql.Date.valueOf(entity.getDate()));
        statement.setTime(7, entity.getSeance().getTimeSeance());
        statement.setString(8, entity.getSeance().getDay_sceance());

    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Seance_Matiere entity) throws SQLException {
        statement.setInt(1, entity.getSeance().getNumSeance());
        statement.setString(2, entity.getMatiere().getMatiereEtdAr());
        statement.setInt(3, entity.getMatiere().getId());
        statement.setInt(4, entity.getSeance().getId());
        statement.setBoolean(5, entity.isTermine());
        statement.setDate(6, java.sql.Date.valueOf(entity.getDate()));
        statement.setTime(7, entity.getSeance().getTimeSeance());
        statement.setString(8, entity.getSeance().getDay_sceance());
        statement.setInt(9, entity.getId());

    }

    @Override
    protected Seance_Matiere mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Seance_Matiere seance_Matiere = new Seance_Matiere();
       seance_Matiere.setId(resultSet.getInt("id"));
        seance_Matiere.setMatiere(new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere")));
        seance_Matiere.setSeance(new SceanceDAOImpl(connection).findById(resultSet.getInt("id_sceance")));
        seance_Matiere.setDate(resultSet.getDate("date").toLocalDate());
        seance_Matiere.setTermine(resultSet.getBoolean("termine"));
        return seance_Matiere;
    }
    public List<Seance_Matiere> getSeanceMatiereByIdMatier(int id_matier){
        List<Seance_Matiere > seance_Matieres = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE "
                    + "id_matiere=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id_matier);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
               Seance_Matiere seance_Matiere  = mapResultSetToEntity(resultSet);
               seance_Matieres.add(seance_Matiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seance_Matieres;
    
    }
    public Seance_Matiere getSeanceMatierNoterminate(boolean termine , int id_matiere){
    Seance_Matiere seance_Matiere= new Seance_Matiere();
        try {
            String query = "SELECT TOP 1 * FROM " + getTableName() +
                            "WHERE termine =?  and id_matiere=? ORDER BY id DESC ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, termine);
            statement.setInt(2, id_matiere);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
             seance_Matiere  = mapResultSetToEntity(resultSet);
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
             return seance_Matiere;
            }
       
    public Seance_Matiere getlasSeanceOfToday(String day,int id_matier) {
        Seance_Matiere entity = new Seance_Matiere();
        try {
            String query = " SELECT TOP 1 * From "+getTableName()+"  where day_sceance =N'"+day+"' and id_matiere ="+id_matier+"  ORDER BY id DESC ";
            
           PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
    
    public Seance_Matiere getlasSeanceOfMatier(int id_matier) {
        Seance_Matiere entity = new Seance_Matiere();
        try {
            String query = " SELECT TOP 1 * From "+getTableName()+" where id_matiere =?  ORDER BY id DESC ";
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1, id_matier);
           ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
    
    public List< Seance_Matiere >  getlastSeances_AfterLastPayement(int id_seance) {
        Seance_Matiere entity = new Seance_Matiere();
       List< Seance_Matiere > seance_Matieres = new ArrayList<>();
        try {
            String query = " SELECT *"+getTableName()+" FROM item WHERE id > ? ";
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1, id_seance);
           ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
                seance_Matieres.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seance_Matieres;
    }
     public List< Seance_Matiere >  getSeances_Noterminate(int id_matiere , LocalDate date ) {      
        Seance_Matiere entity = new Seance_Matiere();
       List< Seance_Matiere > seance_Matieres = new ArrayList<>();
        try {
            String query = " SELECT * FROM "+getTableName()+"   WHERE id_matiere =?  and  date >= ?  ";
            
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1, id_matiere);
            statement.setDate(2, java.sql.Date.valueOf(date) );
           ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
                seance_Matieres.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seance_Matieres;
    }
     
    
     public Seance_Matiere getlast_byID_Matiere( int id__matiere) {
        Seance_Matiere entity = null;
        try {
            String query = "SELECT TOP 1 * From " + getTableName() + " where id_matiere=? ORDER BY id DESC";
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1, id__matiere);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
     
      public Seance_Matiere getSeanceOfTodayBy_Date(LocalDate date,int id_matier) {
        Seance_Matiere entity = new Seance_Matiere();
        try {
            String query = " SELECT * From "+getTableName()+"  where date = '"+date+"' and id_matiere ="+id_matier+" "; //delet id matiere 
            
           PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }else{
            entity=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
      
      public Seance_Matiere Update_etat_seance(Seance_Matiere entity, boolean  etat) {
        try {
            String query =" UPDATE "+getTableName() +" SET termine = ?   WHERE id = ? ";
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setBoolean(1, etat);
           statement.setInt(2, entity.getId());
           statement.executeQuery();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    } 
      
      
      
      
 
    public static void main(String[] args) {
       Connection connection;
      
        try {
            connection = ConnectionDB.getConnection();
        //Seance_Matiere seance_Matiere =new SeanceMatiereDAOImpl(connection).getlasSeanceOfToday("الأحد", 1);
        List <Seance_Matiere> seance_Matieres   = new SeanceMatiereDAOImpl(connection).findAll();
        
        for(Seance_Matiere seance_Matiere : seance_Matieres){
         System.out.println(seance_Matiere.getMatiere() +"  "+seance_Matiere.getSeance().getDay_sceance() +"    "+seance_Matiere.isTermine());
        
        }
        //  System.out.println(""+seance_Matiere.isTermine());
            
        } catch (DatabaseConnectionException ex) {
            Logger.getLogger(SeanceMatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }   

}
