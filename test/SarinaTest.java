package test;

import model.AgreementDAO;
import model.Corporate;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import model.Agreement;
import util.DBTablePrinter;
import util.DBUtil;;

public class SarinaTest {
	
	public static void initMyTables() throws Exception {
		try {
			DBUtil.dbInitAllTables();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void testAgreementTable() {
		//Initialise your DAO objects to test your tables here 
		AgreementDAO agmtDAO = new AgreementDAO();

		Agreement agmt1 = new Agreement();
		agmt1.setStatus("pending");
		agmt1.setPayAmt(50.0f);

		Agreement agmt2 = new Agreement();
		agmt2.setStatus("accepted");
		agmt2.setPayAmt(100.0f);
		agmt2.setAgreeDate(new Date(1000,1,1));
		agmt2.setCreateDay(new Date(1000,1,1));
		agmt2.setInitiateBy("seeker");
		agmt2.setToPostcode(2234L);
		agmt2.setFromPostcode(2220L);
	
		try {
			agmtDAO.insert(agmt1);
			agmtDAO.insert(agmt2);

			final String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT");


			Agreement a = agmtDAO.findById(1);
			a.setPayAmt(20.0f);
			a.setStatus("completed");
			agmtDAO.update(a);	

			//System.out.println(a.toString());

			Agreement aCopy2 = agmtDAO.findById(2);
			aCopy2.setAgreeDate(new Date(1000,1,1));
			aCopy2.setFromPostcode(12345L);
			agmtDAO.update(aCopy2);

			//agmtDAO.delete("AGREEMENT_ID=2");
			
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT");

			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
