package test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;

import javafx.collections.ObservableList;
import model.Corporate;
import model.CorporateDAO;
import model.CorporateMember;
import model.CorporateMemberDAO;
import model.Member;
import model.MemberDAO;
import model.Staff;
import model.StaffDAO;
import util.DBTablePrinter;
import util.DBUtil;

public class BingTest {
	
	public static void initMyTables() throws Exception {
		try {
			DBUtil.dbInitAllTables();
		} catch (Exception e) {
			throw e;
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
			
			Staff s1 = staffDAO.findById(1);
			s1.setFirstName("topkek");
			
			staffDAO.update(s1);

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
			MemberDAO mDAO = new MemberDAO();
			mDAO.insert(new Member());

			
			CorporateDAO cDAO = new CorporateDAO();
			cDAO.insert(new Corporate());
			
			CorporateMemberDAO cmDAO = new CorporateMemberDAO();
			
			Member m = mDAO.findById(1);
			m.setFirstName("test");
			m.setLastMatchDate(new Date(10,10,10));
			mDAO.update(m);
			
			Corporate c = cDAO.findById(1);
			cmDAO.insert(m, c);
			
			cDAO.insert(new Corporate());
			c = cDAO.findById(2);
			cmDAO.insert(m, c);
			
			String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE_MEMBER");
		
			url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "MEMBER");

		    url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			ObservableList<CorporateMember> list = cmDAO.findAll();
			for (CorporateMember cm: list) {
				System.out.println(cm.getFirstName());
			}
			
			/* does not work on CORPORATE_MEMBER table due to the naming conventions of the fields in there */
			/*
			//DBUtil.clearTable("CORPORATE_MEMBER");
			DBUtil.dropTable("CORPORATE_MEMBER");
			
			DBUtil.clearTable("MEMBER");
			DBUtil.dropTable("MEMBER");
			
			DBUtil.clearTable("CORPORATE");
			DBUtil.dropTable("CORPORATE");
			*/
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void clearTables() throws Exception {
		try { 
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
			
			DBUtil.dropTable("CORPORATE_MEMBER");
			
			DBUtil.clearTable("MEMBER");
			DBUtil.dropTable("MEMBER");
			
			DBUtil.clearTable("CORPORATE");
			DBUtil.dropTable("CORPORATE");
		} catch (Exception e) {
			throw e;
		}
	}
}
