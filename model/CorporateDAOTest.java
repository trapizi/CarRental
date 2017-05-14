package model;

import static org.junit.Assert.*;

import java.sql.DriverManager;

import org.junit.Test;

import javafx.collections.ObservableList;
import util.DBTablePrinter;
import util.DBUtil;
import util.SQLBuilder;

public class CorporateDAOTest {

	public void init() {
		try {
			String sqlStmt = SQLBuilder.createTableSQL("corporate.txt");    		    		
			DBUtil.dbInitTable(sqlStmt);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public Corporate createRecord() {
		Corporate s1 = new Corporate();

		s1.setCompanyName("company1");
		s1.setCompanyAddr("123 fake street");
		s1.setCompanyPhoneNumber(123456789);
		s1.setCompanyPCode(2222);
		s1.setCustomerType(1);
		
		return s1;
	}
	
	@Test
	public void testInsert() {
		CorporateDAO corporateDAO = new CorporateDAO();
		
		try {
			init();
				
			Corporate s1 = createRecord();
			corporateDAO.insert(s1);
			
			Corporate s2 = corporateDAO.findById(1);
			
			assertTrue(s2 != null);
			assertTrue(s2.getCompanyName().equals("company1"));
			
		    String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			DBUtil.dropTable("Corporate");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFindAll() {
		CorporateDAO corporateDAO = new CorporateDAO();
				
		try {
			init();
			
			Corporate s1 = createRecord();
			final int noRecords = 10;
			
			for (int i = 0; i < noRecords; i++) {
				corporateDAO.insert(s1);
			}
							
			ObservableList<Corporate> list = corporateDAO.findAll();
			
			assertEquals(list.size(), 10);
			
			DBUtil.dropTable("Corporate");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	@Test
	public void testDelete() {
		CorporateDAO corporateDAO = new CorporateDAO();
				
		try {
			init();

			Corporate s1 = createRecord();
			
			final int noRecords = 10;
			for (int i = 0; i < noRecords; i++) {
				corporateDAO.insert(s1);
			}
				
			ObservableList<Corporate> list = corporateDAO.findAll();
			assertEquals(list.size(), 10);
			
			corporateDAO.delete("Corporate_id=1 OR Corporate_id=2");
			
			list = corporateDAO.findAll();
			assertEquals(list.size(), 8);
			
			DBUtil.dropTable("Corporate");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testUpdate() {
		CorporateDAO corporateDAO = new CorporateDAO();
				
		try {
			init();

			Corporate s1 = createRecord();
			corporateDAO.insert(s1);
			corporateDAO.insert(s1);
			
			// edit the details of the 2nd record in the table
			Corporate s2Copy = corporateDAO.findById(2);
			s2Copy.setCompanyPCode(1111);
			s2Copy.setCompanyName("company2Copy");
			
			 String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			corporateDAO.update(s2Copy);
			
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			// check that update went through
			assertTrue(corporateDAO.findById(2).getCompanyPCode() == 1111);
			
			DBUtil.clearTable("Corporate");
			DBUtil.dropTable("Corporate");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void clean() {
		try {
			init();
			
			DBUtil.clearTable("Corporate");
			DBUtil.dropTable("Corporate");
			DBUtil.dbShutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
