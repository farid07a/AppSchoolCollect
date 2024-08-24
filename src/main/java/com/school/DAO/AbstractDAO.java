/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.DAO;

/**
 *
 * @author farid
 * @param <T>
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.java.com.school.model.config.ConnectionDB;
import main.java.com.school.model.config.DatabaseConnectionException;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    protected Connection connection;

    public AbstractDAO(Connection connection) {  
      this.connection = connection;      
    }

    protected abstract String getTableName();
    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    
    protected abstract void setInsertQueryParameters(PreparedStatement statement, T t) throws SQLException;
    
    protected abstract void setUpdateStatementParameters(PreparedStatement statement, T t) throws SQLException;
    
    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    @Override
    public int save(T t) {
        try {
            
            PreparedStatement statement = connection.prepareStatement(getInsertQuery());
            setInsertQueryParameters(statement, t);
            int x = statement.executeUpdate();
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
         

    @Override
    public int update(T t) {
        try {
            PreparedStatement statement = connection.prepareStatement(getUpdateQuery());
            setUpdateStatementParameters(statement, t);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            String query = "DELETE FROM " + getTableName() + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public T findById(int id) {
        T entity = null;
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public T findByName(String name, String colum) {
        T entity = null;
        try {
            String query = "SELECT * FROM " + getTableName() + " WHERE "+colum+" = ?" ;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name.trim());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
 
    
     public T findbyName_And_Id(String name, String colum,int id, String colum_id) {
      T entity = null;
         try {
            String query = "SELECT * FROM " + getTableName() + " WHERE "+colum+" = ? and "+colum+"= ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
      
     }
    
    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + getTableName();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public T getlast() {
        T entity = null;
        try {
            String query = "SELECT TOP 1 * From " + getTableName() + " ORDER BY id DESC";
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
    
  
}

