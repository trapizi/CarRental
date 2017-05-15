package main;

import java.sql.SQLException;

import model.*;
import util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		System.out.println("Put your CREATE TABLE .txt files in this folder --> " + System.getProperty("user.dir"));
		
		// initialise tables for db
		try {			 
			DBUtil.dbInitAllTables();
			
			Main.testAgreementTable();
			
			DBUtil.dbShutdown();	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testAgreementTable() {
		 //Initialise your DAO objects to test your tables here 
		AgreementDAO agmtDAO = new AgreementDAO();
		
		Agreement agmt1 = new Agreement();
		agmt1.setStatus("pending");
		agmt1.setPayAmt("50");
		
		Agreement agmt2 = new Agreement();
		agmt2.setStatus("accepted");
		agmt2.setPayAmt("100");
		
		try {
			agmtDAO.insert(agmt1);
			agmtDAO.insert(agmt2);
				
			Agreement a = agmtDAO.findById(1);
			System.out.println(a.toString());
						
		    final String url = "jdbc:derby:DBforDEMO;create=true";

			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT");
			
			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
			DBUtil.clearTable("AGREEMENT");
			DBUtil.dropTable("AGREEMENT");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
