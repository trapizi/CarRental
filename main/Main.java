package main;

import java.sql.SQLException;

import model.*;
import util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		// initialise tables for db
		try {
			DBUtil.dbInitAllTables();
			
			StaffDerbyDAO staffDAO = new StaffDerbyDAO();
			
			staffDAO.insertStaffName("Top", "Kek");
			staffDAO.insertStaffName("Ayy", "Lmao");
			
			ObservableList<Staff> list = staffDAO.findAll();
			
			System.out.println(list.size());
			
			for (Staff s: list) {
				System.out.println(s.getFirstName() + " " + s.getLastName() + " " + s.getEmail());
			}
			
			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
			DBUtil.dbShutdown();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}
}