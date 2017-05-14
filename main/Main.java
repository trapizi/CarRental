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
                
                System.out.println(System.getProperty("user.dir"));
                
		try {
			/* Note: You need to add your file containing your CREATE TABLE statement in the function below.
			 *       More information given in the function. 
			 */
			DBUtil.dbInitAllTables();
			
			/* add test functions for your tables here */
			Main.testStaffTable();
			Main.testCorporateMemberTable();

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
		
	public static void testCorporateMemberTable() {
		try {
			String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE_MEMBER");
		
			url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "MEMBER");

		    url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			/* does not work on CORPORATE_MEMBER table due to the naming conventions of the fields in there */
			//DBUtil.clearTable("CORPORATE_MEMBER");
			DBUtil.dropTable("CORPORATE_MEMBER");
			
			DBUtil.clearTable("MEMBER");
			DBUtil.dropTable("MEMBER");
			
			DBUtil.clearTable("CORPORATE");
			DBUtil.dropTable("CORPORATE");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}