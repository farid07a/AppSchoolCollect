/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

/**
 *
 * @author farid
 */
import domaine.CategoreNiveau;
import main.java.com.school.DAO.EtudiantDAO;
import domaine.Etudiant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.school.DAO.AbstractDAO;

public class EtudiantDAOImpl extends AbstractDAO<Etudiant> implements EtudiantDAO { //  EtudiantDAO use for additional Method 
                                                                                    // Now Not uses
    
    public EtudiantDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "etudiant";
    }

    @Override
    protected String getInsertQuery() {
     //   return "INSERT INTO etudiant (matricule, code_bare, prenom, nom, date_birth, adress,tel,email, renseignement_pere, id_categore_niveau,id_nivea) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
            return "INSERT INTO etudiant (matricule, code_bare, prenom, nom, date_birth,adress,tel,email, renseignement_pere, id_categore_niveau,id_niveau,image) VALUES (?,?, ?,?, ?, ?,  ?, ?, ?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE etudiant SET matricule = ?, code_bare = ?, prenom = ? ,nom =? ,date_birth =? , adress =?,"
                + "tel =?,email =?,renseignement_pere =?, id_categore_niveau =? , id_niveau =? , image =?  WHERE  id = ?";
    }
    
    

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Etudiant etudiant) throws SQLException {
        statement.setString(1, etudiant.getMatricule());
        statement.setString(2, etudiant.getCodeBare());
        statement.setString(3, etudiant.getPrenom());
        statement.setString(4, etudiant.getNom());
       statement.setDate(5,  java.sql.Date.valueOf(etudiant.getDateBirth()));//java.sql.Date.valueOf(item.getBirth_date())
        statement.setString(6, etudiant.getAdress());
        statement.setString(7, etudiant.getTel());//adress
        statement.setString(8, etudiant.getEmail());
        statement.setString(9, etudiant.getRenseignementPe());
        statement.setInt(10, etudiant.getCtegore_niveau().getId());
        statement.setInt(11, etudiant.getNiveau().getId());
        statement.setBytes(12,etudiant.getImage());
    }
    
    
    @Override
    protected Etudiant mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(resultSet.getInt("id"));
        etudiant.setMatricule(resultSet.getString("matricule"));
        etudiant.setCodeBare(resultSet.getString("code_bare"));
        etudiant.setPrenom(resultSet.getString("prenom"));
        etudiant.setNom(resultSet.getString("nom"));
        etudiant.setDateBirth(resultSet.getDate("date_birth").toLocalDate());
        etudiant.setAdress(resultSet.getString("adress"));
        etudiant.setTel(resultSet.getString("tel"));
        etudiant.setEmail(resultSet.getString("email"));
        etudiant.setRenseignementPe(resultSet.getString("renseignement_pere"));
        etudiant.setCtegore_niveau(new CategoreNiveauDAOImpl(connection).findById(resultSet.getInt("id_categore_niveau")));
        etudiant.setNiveau(new NiveauEtudeDAOImpl(connection).findById(resultSet.getInt("id_niveau")));
        etudiant.setImage(resultSet.getBytes("image"));
        return etudiant;
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Etudiant etudiant) throws SQLException {
        statement.setString(1, etudiant.getMatricule());
        statement.setString(2, etudiant.getCodeBare());
        statement.setString(3, etudiant.getPrenom());
        statement.setString(4, etudiant.getNom());
        statement.setDate(5,  java.sql.Date.valueOf(etudiant.getDateBirth()));//java.sql.Date.valueOf(item.getBirth_date())
         statement.setString(6, etudiant.getAdress());
        statement.setString(7, etudiant.getTel());
        statement.setString(8, etudiant.getEmail());
        statement.setString(9, etudiant.getRenseignementPe());
        statement.setInt(10, etudiant.getCtegore_niveau().getId());
        statement.setInt(11, etudiant.getNiveau().getId());
        statement.setBytes(12, etudiant.getImage());
        statement.setInt(13, etudiant.getId());
    }
    
    public Etudiant getEtudiantByCodbar(String code_bare){
         Etudiant etudiant =null;
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE code_bare=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, code_bare);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                etudiant = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
        
        
    }
            
}

