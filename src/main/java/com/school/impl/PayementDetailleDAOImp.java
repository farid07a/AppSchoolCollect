/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.school.impl;

import domaine.Payement;
import domaine.PayementDetaille;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.school.DAO.AbstractDAO;

/**
 *
 * @author client
 */
public class PayementDetailleDAOImp extends  AbstractDAO<PayementDetaille >{

    public PayementDetailleDAOImp(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "payement_detaille";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO payement_detaille (id_payement, id_seance, date_payement, montant)"
                + " VALUES (?,?, ?,?)";
    }

    @Override
    protected String getUpdateQuery() {
    return "UPDATE payement_detaille SET  id_payement =? , id_seance=? ,date_payement=?, montant=?"
                + " WHERE  id = ?";

    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, PayementDetaille payementDetaille) throws SQLException {
        statement.setInt(1, payementDetaille.getPayement().getId());
        statement.setInt(2, payementDetaille.getSeance().getId());
        statement.setDate(3, java.sql.Date.valueOf(payementDetaille.getDate_payement()));
        statement.setDouble(4,payementDetaille.getMontant());     
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement statement, PayementDetaille payementDetaille) throws SQLException {
         statement.setInt(1, payementDetaille.getPayement().getId());
        statement.setInt(2, payementDetaille.getSeance().getId());
        statement.setDate(3, java.sql.Date.valueOf(payementDetaille.getDate_payement()));
        statement.setDouble(4,payementDetaille.getMontant());
        statement.setInt(5, payementDetaille.getId());

    }

    @Override
    protected PayementDetaille mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        PayementDetaille payementDetaille = new PayementDetaille();
        payementDetaille.setId(resultSet.getInt("id"));
        payementDetaille.setPayement(new PayementDAOImpl(connection).findById(resultSet.getInt("id_payement")));
        payementDetaille.setSeance(new SeanceDAOImpl(connection).findById(resultSet.getInt("id_seance")));
        payementDetaille.setDate_payement(resultSet.getDate("date_payement").toLocalDate());
        payementDetaille.setMontant(resultSet.getDouble("montant"));


        return payementDetaille;
    }
    
    public List<PayementDetaille> getPayementDetailleCredit(int id_payement) {
        List<PayementDetaille> payementDetailles= new ArrayList<>();
        try {
            String query = " SELECT * From " + getTableName() + " where  id_payement =? ";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id_payement);    
          //  statement.setString(2, type_payemnt);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PayementDetaille  entity = mapResultSetToEntity(resultSet);
                payementDetailles.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payementDetailles;
    }
    
}
