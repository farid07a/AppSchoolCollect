/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.model.config;

/**
 *
 * @author farid
 */
public class DatabaseConnectionException extends Exception{

    public DatabaseConnectionException(String Message) {
        super(Message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    
    
}
