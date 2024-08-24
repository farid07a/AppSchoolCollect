/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.Enseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.school.DAO.AbstractDAO;
import main.java.com.school.DAO.EnseignantDAO;
import main.java.com.school.DAO.GenericDAO;

/**
 *
 * @author farid
 */
public class EnseignantDAOImpl extends AbstractDAO<Enseignant> implements EnseignantDAO {

    private static final String INSERT_ENSEIGNANT = "INSERT INTO enseignant (col1,...) VALUES (?,?,?,?,?)";

    public EnseignantDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "enseignant";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO enseignant (nom_ar,nom_fr,prenom_ar,prenom_fr,specialite,phone_number,email) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE enseignant SET nom_ar=?,nom_fr=?,prenom_ar=?,prenom_fr=?,specialite=?,phone_number=?,email=? WHERE id=?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Enseignant enseignant) throws SQLException {
        statement.setString(1, enseignant.getNomAr());
        statement.setString(2, enseignant.getNomFr());
        statement.setString(3, enseignant.getPrenomAr());

        statement.setString(4, enseignant.getPrenomFr());
        statement.setString(5, enseignant.getSpecialite());
        statement.setString(6, enseignant.getPhoneNum());
        statement.setString(7, enseignant.getEmail());
        
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Enseignant enseignant) throws SQLException {
        statement.setString(1, enseignant.getNomAr());
        statement.setString(2, enseignant.getNomFr());
        statement.setString(3, enseignant.getPrenomAr());

        statement.setString(4, enseignant.getPrenomFr());
        statement.setString(5, enseignant.getSpecialite());
        statement.setString(6, enseignant.getPhoneNum());
        statement.setString(7, enseignant.getEmail());
        statement.setInt(8, enseignant.getId());
    }

    @Override
    protected Enseignant mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Enseignant enseignant = new  Enseignant();
        int id=resultSet.getInt("id");
        enseignant.setId(id);
        String nomAr=resultSet.getString("nom_ar");
        enseignant.setNomAr(nomAr);
        String nomFr= resultSet.getString("nom_fr");
        enseignant.setNomFr(nomFr);
        String prenomAr =  resultSet.getString("prenom_ar");
        enseignant.setPrenomAr(prenomAr);
        String prenomFr = resultSet.getString("prenom_fr");
        enseignant.setPrenomFr(prenomFr);
        String specialite = resultSet.getString("specialite");
        enseignant.setSpecialite(specialite);
        String phoneNum =  resultSet.getString("phone_number");
        enseignant.setPhoneNum(phoneNum);
        String Email = resultSet.getString("email");
        enseignant.setEmail(Email);
        return enseignant;
    
        //return new Enseignant(id, nomAr, nomFr, prenomAr, prenomFr, specialite, phoneNum,Email);
    }

    @Override
    public Enseignant findByFullName(String Nom, String prenom) throws SQLException {
        
        String Query="SELECT * FROM "+getTableName()+" WHERE nom_ar=N'"+Nom+"' AND prenom_ar=N'"+prenom+"' ";
        
        PreparedStatement statement = connection.prepareStatement(Query);
//        statement.setString(1, Nom);
//        statement.setString(2, prenom);
        
        ResultSet resultSet=statement.executeQuery();
        
        while (resultSet.next()) {            
            
        return mapResultSetToEntity(resultSet);
        }
        return null;
        
    }

}
