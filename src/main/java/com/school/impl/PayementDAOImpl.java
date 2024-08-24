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
        return "INSERT INTO payement (id_etudiant, id_matiere,id_seance_matiere, id_seance, type_payement, prix,"
                + "prix_paye,prix_total,date ) VALUES (?,?, ?,?,?, ?, ?,  ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE payement SET  id_etudiant =? , id_matiere=?, id_seance_matiere=? ,id_seance=?, type_payement=?, prix=?,"
                + "prix_paye=?,prix_total=?,date=? WHERE  id = ?";

    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Payement payement) throws SQLException {
     statement.setInt(1, payement.getEtudiant().getId());
        statement.setInt(2, payement.getMatiere().getId());
        statement.setInt(3, payement.getSeance_matiere().getId());
        statement.setInt(4, payement.getSeance().getId());
        statement.setString(5, payement.getTypePayement());
       statement.setDouble(6,  payement.getPrix());
       statement.setDouble(7,payement.getPrixPaye() );
        statement.setDouble(8, payement.getPrixTotal());
        statement.setDate(9, java.sql.Date.valueOf(payement.getDate()));
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Payement payement) throws SQLException {
     statement.setInt(1, payement.getEtudiant().getId());
        statement.setInt(2, payement.getMatiere().getId());
        statement.setInt(3, payement.getSeance_matiere().getId());
        statement.setInt(4, payement.getSeance().getId());
        statement.setString(5, payement.getTypePayement());
        statement.setDouble(6,  payement.getPrix());//java.sql.Date.valueOf(etudiant.getDateBirth()));//java.sql.Date.valueOf(item.getBirth_date())
         statement.setDouble(7, payement.getPrixPaye());
        statement.setDouble(8, payement.getPrixTotal());
      statement.setDate(9, java.sql.Date.valueOf(payement.getDate()));
        statement.setInt(10, payement.getId());
    
    }

    @Override
    protected Payement mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Payement payement = new Payement();
        payement.setId(resultSet.getInt("id"));
        payement.setEtudiant(new EtudiantDAOImpl(connection).findById(resultSet.getInt("id_etudiant")));
        payement.setMatiere(new MatiereDAOImpl(connection).findById(resultSet.getInt("id_matiere")));
       payement.setSeance_matiere(new SeanceMatiereDAOImpl(connection).findById(resultSet.getInt("id_seance_matiere")));
        payement.setSeance(new SceanceDAOImpl(connection).findById(resultSet.getInt("id_seance")));
        payement.setTypePayement(resultSet.getString("type_payement"));
        payement.setPrix(resultSet.getDouble("prix"));
        payement.setPrixPaye(resultSet.getDouble("prix_paye"));
        payement.setPrixTotal(resultSet.getDouble("prix_total"));
        payement.setDate(resultSet.getDate("date").toLocalDate());
        
        return payement;

    }
    
    public List<Payement>  getPayementEtudiantOfMatier(Etudiant etudiant , Matiere  matiere  ){
    
        List <Payement> payements = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE id_etudiant=? and id_matiere=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,etudiant.getId() );
            statement.setInt(2,matiere.getId() );
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
               Payement payement = mapResultSetToEntity(resultSet);
                payements.add(payement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payements;
    }
    public Payement getlasPayementEtudiantOfMatier(int id_etudiant,int id_matier ) {
        Payement entity = new Payement();
        try {
            String query = " SELECT TOP 1 * From "+getTableName()+" where  id_etudiant =? and  id_matiere =?  ORDER BY id DESC ";
            
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
    public List <Payement> getPayement_NO_OfMatier(int id_etudiant,int id_matier ,String type_payemnt) {
      List <Payement> payements = new ArrayList<>();
        try {
            String query = " SELECT * From "+getTableName()+" where  id_etudiant =? and  id_matiere =?  and type_payement =?";
            
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
}
