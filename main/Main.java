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
			
			/* Initialise your DAO objects to test your tables here */
			StaffDerbyDAO staffDAO = new StaffDerbyDAO();
			
			staffDAO.insertStaffName("One", "Two");
			staffDAO.insertStaffName("Three", "Four");
			
			/*
			ObservableList<Staff> list = staffDAO.findAll();
			
			System.out.println(list.size());
			
			for (Staff s: list) {
				System.out.println(s.getFirstName() + " " + s.getLastName() + " " + s.getEmail());
			}
			*/
		    final String url = "jdbc:derby:DBforDEMO;create=true";

			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "STAFF");
			
			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
			
			/* DO NOT DELETE THIS LINE BELOW OR IT'LL FUCK UP YOUR PRIMARY KEY NUMBERING */
			DBUtil.dbShutdown();
			/* DO NOT DELETE THIS LINE ABOVE */
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}
}