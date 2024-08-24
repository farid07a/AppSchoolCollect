/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.model.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import util.PropertiesReader;

/**
 *
 * @author farid
 */
public class ConnectionDB {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=nihal_school";
    private static final String DATABASE_USER = "sa";
    private static final String DATABASE_PASSWORD = "farid";

    public static Connection getConnection() throws DatabaseConnectionException {
        try {
            Class.forName(DATABASE_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Success Connecting");
            return connection;
        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionException("Database Driver not found: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error Connecting to Database: " + e.getMessage(), e);
        }
    
    }
    
    public static void main(String[] args) {
        try {
            ConnectionDB obj=new ConnectionDB();
            Connection cnx=ConnectionDB.getConnection();

                
                System.out.println(cnx.getCatalog());
            
        } catch (DatabaseConnectionException | SQLException ex) {
             java.util.logging.Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
}
