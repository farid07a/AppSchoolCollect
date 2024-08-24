/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.impl;

import domaine.Matiere;
import domaine.NiveauEtude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.java.com.school.DAO.AbstractDAO;

/**
 *
 * @author farid
 */
public class MatiereDAOImpl extends AbstractDAO<Matiere> {

    public MatiereDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "matiere";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO matiere (matiere_etd_ar, matiere_etd_fr, prix, id_niveau, id_categore_niveau ,id_enseignant) VALUES (?, ?, ?,?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE matiere SET matiere_etd_ar=?, matiere_etd_fr=?, prix=?, id_niveau=?, id_categore_niveau=?,id_enseignant=? WHERE id=?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Matiere matiere) throws SQLException {
        statement.setString(1, matiere.getMatiereEtdAr());
        statement.setString(2, matiere.getMatiereEtdFr());
        statement.setDouble(3, matiere.getPrix());
        statement.setInt(4, matiere.getNiveau().getId());
        statement.setInt(5, matiere.getCategoreNiveau().getId());
        statement.setInt(6, matiere.getEnseignant().getId());

    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, Matiere matiere) throws SQLException {
        statement.setString(1, matiere.getMatiereEtdAr());
        statement.setString(2, matiere.getMatiereEtdFr());
        statement.setDouble(3, matiere.getPrix());
        statement.setInt(4, matiere.getNiveau().getId());
        statement.setInt(5, matiere.getCategoreNiveau().getId());
        statement.setInt(6, matiere.getEnseignant().getId());
        statement.setInt(7, matiere.getId());
    }

    @Override
    protected Matiere mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        NiveauEtudeDAOImpl niveau_dao = new NiveauEtudeDAOImpl(connection);
        CategoreNiveauDAOImpl categore_dao = new CategoreNiveauDAOImpl(connection);
        EnseignantDAOImpl enseignant_dao = new EnseignantDAOImpl(connection);

        Matiere matiere = new Matiere();
        matiere.setId(resultSet.getInt("id"));
        matiere.setMatiereEtdAr(resultSet.getString("matiere_etd_ar"));
        matiere.setMatiereEtdFr(resultSet.getString("matiere_etd_fr"));
        matiere.setPrix(resultSet.getDouble("prix"));
        matiere.setNiveau(niveau_dao.findById(resultSet.getInt("id_niveau")));
        matiere.setCategoreNiveau(categore_dao.findById(resultSet.getInt("id_categore_niveau")));
        matiere.setEnseignant(enseignant_dao.findById(resultSet.getInt("id_enseignant")));
        return matiere;
    }

    public Matiere findMatiereByNiveau_Catego(String name, int id_niveau, int id_Catego) {
        Matiere entity = null;
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE  matiere_etd_ar = ? and id_niveau = ? and  id_categore_niveau = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, name);
            statement.setInt(2, id_niveau);
            statement.setInt(3, id_Catego);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;

    }

    public Matiere getMatiereNiveauOfCategory(String Matiere_n,String niveau, String Catego) {

        Matiere matiere = null;
     
        String SELECT_Matier_Niveau_Category = "SELECT matiere.id As matiere_id\n"
                + "FROM niveau_etude,categore_niveau,matiere\n"
                + "WHERE niveau_etude.niveau_initial_ar = N'"+niveau+"'\n"
                + "  AND categore_niveau.categore_niveau_ar = N'"+Catego+"'\n"
                + "  and matiere.matiere_etd_ar=N'"+Matiere_n+"'\n"
                + "  and niveau_etude.categore_niveau_id= categore_niveau.id\n"
                + "  and matiere.id_categore_niveau = categore_niveau.id and "
                + "matiere.id_niveau=niveau_etude.id";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_Matier_Niveau_Category);
//            statement.setString(1, niveau.trim());
//            statement.setString(2, Catego.trim());
//            statement.setString(3, Matiere_n.trim());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_matiere =resultSet.getInt("matiere_id");
              matiere=findById(id_matiere);
                
                /* matiere.setId(resultSet.getInt("id"));
                matiere.setMatiereEtdAr(resultSet.getString("matiere_etd_ar"));
                matiere.setMatiereEtdFr(resultSet.getString("matiere_etd_fr"));               
                matiere.setPrix(resultSet.getDouble("prix"));                
                niveauEtude.setId(resultSet.getInt("id"));
                niveauEtude.setNiveauInitialAr("niveau_initial_ar");
                niveauEtude.setNiveauInitialFr("niveau_initial_fr");
                niveauEtude.setDescription("description");
                niveauEtude.setCategore_niveau_id(0);
                matiere.setNiveau(null);
                matiere.setCategoreNiveau(null);*/
            }

        } catch (SQLException ex) {
            Logger.getLogger(NiveauEtudeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return matiere;

    }
    
    
   

    /* SELECT *
FROM niveau_etude,categore_niveau,matiere
WHERE niveau_etude.niveau_initial_ar = N'سنة أولى'
  AND categore_niveau.categore_niveau_ar = N'متوسط'
  and matiere.matiere_etd_ar=N'فرنسية'
  and niveau_etude.categore_niveau_id= categore_niveau.id
  and matiere.id_categore_niveau = categore_niveau.id and matiere.id_niveau=niveau_etude.id */
}
