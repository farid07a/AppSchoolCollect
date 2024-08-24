/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.school.DAO;

import java.sql.SQLException;
import java.util.List;


public interface GenericDAO<T> {
    int save(T t);
    int update(T t);
    int delete(int id);
    T findById(int id);
    T findByName(String name,String colum);
    T findbyName_And_Id(String name, String colum,int id, String colum_id);
    List<T> findAll();
    T getlast();
 
}
