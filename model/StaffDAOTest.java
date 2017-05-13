package model;

import static org.junit.Assert.*;
import org.junit.Test;

import javafx.collections.ObservableList;
import util.DBUtil;

public class StaffDAOTest {

	@Test
	public void testInsert() {
		StaffDAO staffDAO = new StaffDAO();
		
		try {
			DBUtil.dbInitAllTables();	
			
			Staff s1 = new Staff();
			s1.setUserName("s1");
			staffDAO.insert(s1);
			
			Staff s2 = staffDAO.findById(1);
			
			assertTrue(s2 != null);
			assertTrue(s2.getUserName().equals("s1"));
			
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFindAll() {
		StaffDAO staffDAO = new StaffDAO();
				
		try {
			DBUtil.dbInitAllTables();

			Staff s1 = new Staff();
			s1.setUserName("testFindAll()");
			final int noRecords = 10;
			
			for (int i = 0; i < noRecords; i++) {
				staffDAO.insert(s1);
			}
							
			ObservableList<Staff> list = staffDAO.findAll();
			
			assertEquals(list.size(), 10);
			
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testDelete() {
		StaffDAO staffDAO = new StaffDAO();
				
		try {
			DBUtil.dbInitAllTables();

			Staff s1 = new Staff();
			final int noRecords = 10;
			
			for (int i = 0; i < noRecords; i++) {
				staffDAO.insert(s1);
			}
				
			ObservableList<Staff> list = staffDAO.findAll();
			assertEquals(list.size(), 10);
			
			staffDAO.delete("staff_id=1 OR staff_id=2");
			
			list = staffDAO.findAll();
			assertEquals(list.size(), 8);
			
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testUpdate() {
		StaffDAO staffDAO = new StaffDAO();
				
		try {
			DBUtil.dbInitAllTables();

			Staff s1 = new Staff();
			staffDAO.insert(s1);
			staffDAO.insert(s1);
			
			// edit the details of the 2nd record in the table
			Staff s2Copy = staffDAO.findById(2);
			s2Copy.setEmail("kek@kek.com");
			
			staffDAO.update(s2Copy);
			
			// check that update went through
			assertTrue(staffDAO.findById(2).getEmail().equals("kek@kek.com"));
			
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void clean() {
		try {
			DBUtil.dbInitAllTables();			
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
			DBUtil.dbShutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
