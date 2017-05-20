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


public class seekDAO {
    public ObservableList<Seek> findAll() throws SQLException, ClassNotFoundException {
        try {
                ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "SEEK", ""));
        
                ObservableList<Seek> list = this.getSeekList(rs);
                
                return list;
    } catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() +" failed.");
            e.printStackTrace();
    	}
        return null;
    }
    
    public Seek findById(int seekID) throws SQLException, ClassNotFoundException {
    	try {
    		ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "SEEK", "OFFER_ID=" + seekID  ));
    		
    		ObservableList<Seek> list = this.getSeekList(rs);
    	
    	} catch (SQLException | ClassNotFoundException e) {
    		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public void insert(Seek seek) throws SQLException, ClassNotFoundException {
    	String sqlStmt = new InsertSQLBuilder()
    			.addTable("SEEK")
    			.addFieldValue("USERNAME", seek.getUsername())
    			.addFieldValue("BOOK_DAY", seek.getBookDay())
    			.addFieldValue("BOOK_TIME", seek.getBookTime())
    			.where("SEEK_ID=" + seek.getSeekID())    			
    			.toString();
    	
    	try {
    		DBUtil.dbExecuteUpdate(sqlStmt);
    	} catch (SQLException | ClassNotFoundException e) {
    		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;    		
    	}
    }
    
    public void update(Seek seek) throws SQLException, ClassNotFoundException {
    	String sqlStmt = new UpdateSQLBuilder()
    			.addTable("SEEK")
    			.addFieldValue("USERNAME", seek.getUsername())
    			.addFieldValue("BOOK_DAY", seek.getBookDay())
    			.addFieldValue("BOOK_TIME", seek.getBookTime())
    			.where("SEEK_ID=" + seek.getSeekID())
    			.toString();
    	
    	try {
    		DBUtil.dbExecuteUpdate(sqlStmt);
    	} catch (SQLException | ClassNotFoundException e) {
    		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;    		
    	}    	
    }
    
    public void delete(String condition) throws SQLException, ClassNotFoundException {
		try {
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("SEEK", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}    	
    } 
    
    
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
