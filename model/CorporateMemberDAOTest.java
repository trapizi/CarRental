package model;

import static org.junit.Assert.*;

import java.sql.DriverManager;

import org.junit.Test;

import javafx.collections.ObservableList;
import util.DBTablePrinter;
import util.DBUtil;
import util.SQLBuilder;

public class CorporateMemberDAOTest {

	public void init() {
		try {
			String sqlStmt = SQLBuilder.createTableSQL("corporate.txt");    		    		
			DBUtil.dbInitTable(sqlStmt);
			
			sqlStmt = SQLBuilder.createTableSQL("member.txt");    		    		
			DBUtil.dbInitTable(sqlStmt);
			
			sqlStmt = SQLBuilder.createTableSQL("corporateMember.txt");    		    		
			DBUtil.dbInitTable(sqlStmt);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public Corporate createCorporateRecord() {
		Corporate s1 = new Corporate();

		s1.setCompanyName("company1");
		s1.setCompanyAddr("123 fake street");
		s1.setCompanyPhoneNumber(123456789);
		s1.setCompanyPCode(2222);
		s1.setCustomerType(1);
		
		return s1;
	}
	
	public CorporateMember createCorporateMemberRecord() {
		CorporateMember s1 = new CorporateMember();

		s1.setFirstName("corporateMember1");
		s1.setUserName("corporateMember1");
		s1.setCreditCard("kek");
		
		return s1;
	}
	
	public Member createMemberRecord() {
		Member s1 = new Member();

		s1.setFirstName("member1");
		s1.setUserName("member1");
		s1.setCreditCard("kek");
		
		return s1;
	}
	
	@Test
	public void testInsert() {
		CorporateMemberDAO corporateMemberDAO = new CorporateMemberDAO();
		CorporateDAO corporateDAO = new CorporateDAO();
		MemberDAO memberDAO = new MemberDAO();
		
		try {
			init();
			
			Corporate c1 = createCorporateRecord();
			corporateDAO.insert(c1);
			
			Member m1 = createMemberRecord();
			memberDAO.insert(m1);
			
			c1 = corporateDAO.findById(1);
			m1 = memberDAO.findById(1);
			
		    String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "MEMBER");
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE_MEMBER");
			
			corporateMemberDAO.insert(m1, c1);
					
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE_MEMBER");
			
			DBUtil.dropTable("CORPORATE_MEMBER");
			DBUtil.dropTable("MEMBER");
			DBUtil.dropTable("CORPORATE");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	/*
	@Test
	public void testFindAll() {
		CorporateDAO corporateMemberDAO = new CorporateDAO();
				
		try {
			init();
			
			Corporate s1 = createRecord();
			final int noRecords = 10;
			
			for (int i = 0; i < noRecords; i++) {
				corporateMemberDAO.insert(s1);
			}
							
			ObservableList<Corporate> list = corporateMemberDAO.findAll();
			
			assertEquals(list.size(), 10);
			
			DBUtil.dropTable("Corporate");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	@Test
	public void testDelete() {
		CorporateDAO corporateMemberDAO = new CorporateDAO();
				
		try {
			init();

			Corporate s1 = createRecord();
			
			final int noRecords = 10;
			for (int i = 0; i < noRecords; i++) {
				corporateMemberDAO.insert(s1);
			}
				
			ObservableList<Corporate> list = corporateMemberDAO.findAll();
			assertEquals(list.size(), 10);
			
			corporateMemberDAO.delete("Corporate_id=1 OR Corporate_id=2");
			
			list = corporateMemberDAO.findAll();
			assertEquals(list.size(), 8);
			
			DBUtil.dropTable("Corporate");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testUpdate() {
		CorporateDAO corporateMemberDAO = new CorporateDAO();
				
		try {
			init();

			Corporate s1 = createRecord();
			corporateMemberDAO.insert(s1);
			corporateMemberDAO.insert(s1);
			
			// edit the details of the 2nd record in the table
			Corporate s2Copy = corporateMemberDAO.findById(2);
			s2Copy.setCompanyPCode(1111);
			s2Copy.setCompanyName("company2Copy");
			
			 String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			corporateMemberDAO.update(s2Copy);
			
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			// check that update went through
			assertTrue(corporateMemberDAO.findById(2).getCompanyPCode() == 1111);
			
			DBUtil.clearTable("Corporate");
			DBUtil.dropTable("Corporate");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	*/
	
	@Test
	public void clean() {
		try {
			init();
			
			DBUtil.dropTable("CORPORATE_MEMBER");
			DBUtil.dropTable("MEMBER");
			DBUtil.dropTable("CORPORATE");
			
			DBUtil.dbShutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
