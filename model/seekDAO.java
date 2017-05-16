/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Seek;
import util.*;

/**
 *
 * @author z5076470
 */
public class seekDAO {
    
    
    
    private ObservableList<Seek> getSeekList (ResultSet rs) throws SQLException {
    ObservableList<Seek> list = FXCollections.observableArrayList();

    while (rs.next()) {
            try {
                    Seek seek = new Seek();    		
                    seek.setSeekID(rs.getInt("SEEK_ID"));
                    seek.setUsername(rs.getString("USERNAME"));
                    seek.setBookDay(rs.getDate("BOOK_DAY"));
                    seek.setBookTime(rs.getDate("BOOK_TIME"));
                    
                    list.add(seek);
            } catch (SQLException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
                    throw e;
            }
        }
    return list;
    }    
}
