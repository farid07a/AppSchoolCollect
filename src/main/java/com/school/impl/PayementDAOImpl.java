/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.school.impl;

import domaine.Etudiant;
import domaine.Matiere;
import domaine.Payement;
import domaine.Seance;
import domaine.Seance_Matiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.DAO.AbstractDAO;

/**
 *
 * @author client
 */
public class PayementDAOImpl extends AbstractDAO<Payement> {

    public PayementDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "payement";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO payement (id_etudiant, id_matiere, id_seance, type_payement, prix,"
                + "prix_paye,prix_total,date ,nb_seance) VALUES (?,?, ?,?,?, ?, ?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE payement SET  id_etudiant =? , id_matiere=? ,id_seance=?, type_payement=?, prix=?,"
                + "prix_paye=?,prix_total=?,date=? ,nb_seance=?  WHERE  id = ?";

    }
    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Payement payement) throws SQLException {
        statement.setInt(1, payement.getEtudiant().getId());
        statement.setInt(2, payement.getMatiere().getId());
        statement.setInt(3, payement.getSeance().getId());
        statement.setString(4, payement.getTypePayement());
        statement.setDouble(5, payement.getPrix());
        statement.setDouble(6, payement.getPrixPaye());
        statement.setDouble(7, payement.getPrixTotal());
        statement.setDate(8, java.sql.Date.valueOf(payement.getDate()));
        statement.setInt(9, payement.getNb_seance());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Payement payement) throws SQLException {
        statement.setInt(1, payement.getEtudiant().getId());
        statement.setInt(2, payement.getMatiere().getId());
        statement.setInt(3, payement.getSeance().getId());
        statement.setString(4, payement.getTypePayement());
        statement.setDouble(5, payement.getPrix());//java.sql.Date.valueOf(etudiant.getDateBirth()));//java.sql.Date.valueOf(item.getBirth_date())
        statement.setDouble(6, payement.getPrixPaye());
        statement.setDouble(7, payement.getPrixTotal());
        statement.setDate(8, java.sql.Date.valueOf(payement.getDate()));
        statement.setInt(9, payement.getNb_seance());
        statement.setInt(10, payement.getId());
    }

    @Override
    protected Payement mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Payement payement = new Payement();
        payement.setId(resultSet.getInt("id"));
        payement.setEtudiant(new EtudiantDAOImpl(connection).findById(resultSet.getInt("id_etudiant")));
        payement.setMatiere(new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere")));
        payement.setSeance(new SeanceDAOImpl(connection).findById(resultSet.getInt("id_seance")));
        payement.setTypePayement(resultSet.getString("type_payement"));
        payement.setPrix(resultSet.getDouble("prix"));
        payement.setPrixPaye(resultSet.getDouble("prix_paye"));
        payement.setPrixTotal(resultSet.getDouble("prix_total"));
        payement.setDate(resultSet.getDate("date").toLocalDate());
        payement.setNb_seance(resultSet.getInt("nb_seance"));
        return payement;
    }

    public List<Payement> getPayementEtudiantOfMatier(Etudiant etudiant, Matiere matiere) {

        List<Payement> payements = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE id_etudiant=? and id_matiere=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, etudiant.getId());
            statement.setInt(2, matiere.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payement payement = mapResultSetToEntity(resultSet);
                payements.add(payement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payements;
    }
    

    public Payement getlasPayementEtudiantOfMatier(int id_etudiant, int id_matier) {
        Payement entity =null;
        try {
            //String query = " SELECT TOP 1 * From " + getTableName() + " where  id_etudiant =? and  id_matiere =?  ORDER BY id DESC ";
             String query = " SELECT  *From " + getTableName() + " where  id_etudiant =? and  id_matiere =? AND nb_seance > 0  "
                     + "AND type_payement=N'شهري' ";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id_etudiant);
            statement.setInt(2, id_matier);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
     public Payement getlastCreditEtudiantOfMatier(int id_etudiant, int id_matier) {
        Payement entity =null;
        try {
            //String query = " SELECT TOP 1 * From " + getTableName() + " where  id_etudiant =? and  id_matiere =?  ORDER BY id DESC ";
             String query = " SELECT TOP 1 * From " + getTableName() + " where  id_etudiant =? and  id_matiere =?   "
                     + "AND type_payement=N'ديون'  ORDER BY id DESC ";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id_etudiant);
            statement.setInt(2, id_matier);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }


     public List< Payement> getCreditEtudiantByMatiers_no_payéé(Etudiant etudiant, Matiere matier) {
       List< Payement> payements =new ArrayList<>();
        try {
            //String query = " SELECT TOP 1 * From " + getTableName() + " where  id_etudiant =? and  id_matiere =?  ORDER BY id DESC ";
             String query = " SELECT   * From " + getTableName() + " where  id_etudiant =? and  id_matiere =?  and  nb_seance > 0   ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, etudiant.getId());
            statement.setInt(2, matier.getId());
            ResultSet resultSet = statement.executeQuery();
           while(resultSet.next()) {              
              Payement entity = mapResultSetToEntity(resultSet);
              payements.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payements;
    }
     public Payement getlastCreditEtudiantOfMatierss(Etudiant etudiant, Matiere matier) {
        Payement entity =null;
        try {
            //String query = " SELECT TOP 1 * From " + getTableName() + " where  id_etudiant =? and  id_matiere =?  ORDER BY id DESC ";
             String query = " SELECT  TOP 1 * From " + getTableName() + " where  id_etudiant =? and  id_matiere =?  ORDER BY id DESC   ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, etudiant.getId());
            statement.setInt(2, matier.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
     
    public List<Payement> getPayementWithType(int id_etudiant, int id_matier, String type_payemnt) {
        List<Payement> payements = new ArrayList<>();
        try {
            String query = " SELECT * From " + getTableName() + " where  id_etudiant =? and  id_matiere =?  and type_payement =?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id_etudiant);
            statement.setInt(2, id_matier);
            statement.setString(3, type_payemnt);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payement entity = mapResultSetToEntity(resultSet);
                payements.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payements;
    }
     public List<Payement> getPayementOfEtudiantByMatier(Etudiant etudiant, Matiere matiere, String type_payemnt) {
        List<Payement> payements = new ArrayList<>();
        try {
            String query = " SELECT * From " + getTableName() + " where  id_etudiant =? AND  id_matiere =?  AND type_payement =N'?'";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, etudiant.getId());
            statement.setInt(2, matiere.getId());
            statement.setString(3, type_payemnt);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payement entity = mapResultSetToEntity(resultSet);
                payements.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payements;
    }
    
       public List<Payement> getCreditOfEtudiant(Etudiant etudiant, String type_payemnt) {
        List<Payement> payements = new ArrayList<>();
        try {
            String query = " SELECT * From " + getTableName() + " where  id_etudiant =? AND   type_payement =N'"+type_payemnt+"'";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, etudiant.getId());    
          //  statement.setString(2, type_payemnt);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payement entity = mapResultSetToEntity(resultSet);
                payements.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payements;
    }
    public List<Payement> getAll_Payement_Credit_Etudiant(Etudiant etudiant) {
        List<Payement> payements = new ArrayList<>();
        try {
            String query = " SELECT * From " + getTableName() + " where  id_etudiant =?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, etudiant.getId());    
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payement entity = mapResultSetToEntity(resultSet);
                payements.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payements;
    }
       
     public  int SavePayementParMoin( Payement payement) throws SQLException {
        String  Query=" INSERT INTO payement (id_etudiant, id_matiere,  type_payement, prix,"
                + "prix_paye,prix_total,date ,nb_seance) VALUES (?,?, ?,?, ?, ?,?,?)";
        
        PreparedStatement statement = connection.prepareStatement(Query);
        
        statement.setInt(1, payement.getEtudiant().getId());
        statement.setInt(2, payement.getMatiere().getId());
        statement.setString(3, payement.getTypePayement());
        statement.setDouble(4, payement.getPrix());
        statement.setDouble(5, payement.getPrixPaye());
        statement.setDouble(6, payement.getPrixTotal());
        statement.setDate(7, java.sql.Date.valueOf(payement.getDate()));
        statement.setInt(8, payement.getNb_seance());
        
       int x = statement.executeUpdate();
       return x;
    }
      
    
}
