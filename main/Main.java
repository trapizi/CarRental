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
			/* Note: You need to add your file containing your CREATE TABLE statement in the function below.
			 *       More information given in the function. 
			 */
			DBUtil.dbInitAllTables();
			
			/* add test functions for your tables here */
			Main.testStaffTable();
			
			/* DO NOT DELETE THIS LINE BELOW OR IT'LL FUCK UP YOUR PRIMARY KEY NUMBERING */
			DBUtil.dbShutdown();
			/* DO NOT DELETE THIS LINE ABOVE */
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void testStaffTable() {
		/* Initialise your DAO objects to test your tables here */
		StaffDAO staffDAO = new StaffDAO();
		
		Staff staff1 = new Staff();
		staff1.setUserName("alice");
		staff1.setPassword("xd");
		
		Staff staff2 = new Staff();
		staff2.setUserName("bob");
		staff2.setPassword("lul");
		
		try {
			staffDAO.insert(staff1);
			staffDAO.insert(staff2);
				
			Staff s = staffDAO.findById(1);
			System.out.println(s.toString());
			
			/* print the staff table out */			
		    final String url = "jdbc:derby:DBforDEMO;create=true";

			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "STAFF");
			
			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}