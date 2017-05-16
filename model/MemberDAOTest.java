package model;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.collections.ObservableList;
import util.DBUtil;

public class MemberDAOTest {

	@Test
	public void testInsert() {
		MemberDAO memberDAO = new MemberDAO();
		
		try {
			DBUtil.dbInitAllTables();	
			
			Member s1 = new Member();
			s1.setUserName("s1");
			memberDAO.insert(s1);
			
			Member s2 = memberDAO.findById(1);
			
			assertTrue(s2 != null);
			assertTrue(s2.getUserName().equals("s1"));
			
			DBUtil.clearTable("Member");
			DBUtil.dropTable("Member");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFindAll() {
		MemberDAO memberDAO = new MemberDAO();
				
		try {
			DBUtil.dbInitAllTables();

			Member s1 = new Member();
			s1.setUserName("testFindAll()");
			final int noRecords = 10;
			
			for (int i = 0; i < noRecords; i++) {
				memberDAO.insert(s1);
			}
							
			ObservableList<Member> list = memberDAO.findAll();
			
			assertEquals(list.size(), 10);
			
			DBUtil.clearTable("Member");
			DBUtil.dropTable("Member");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testDelete() {
		MemberDAO memberDAO = new MemberDAO();
				
		try {
			DBUtil.dbInitAllTables();

			Member s1 = new Member();
			final int noRecords = 10;
			
			for (int i = 0; i < noRecords; i++) {
				memberDAO.insert(s1);
			}
				
			ObservableList<Member> list = memberDAO.findAll();
			assertEquals(list.size(), 10);
			
			memberDAO.delete("Member_id=1 OR Member_id=2");
			
			list = memberDAO.findAll();
			assertEquals(list.size(), 8);
			
			DBUtil.clearTable("Member");
			DBUtil.dropTable("Member");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testUpdate() {
		MemberDAO memberDAO = new MemberDAO();
				
		try {
			DBUtil.dbInitAllTables();

			Member s1 = new Member();
			memberDAO.insert(s1);
			memberDAO.insert(s1);
			
			// edit the details of the 2nd record in the table
			Member s2Copy = memberDAO.findById(2);
			s2Copy.setEmail("kek@kek.com");
			
			memberDAO.update(s2Copy);
			
			// check that update went through
			assertTrue(memberDAO.findById(2).getEmail().equals("kek@kek.com"));
			
			DBUtil.clearTable("Member");
			DBUtil.dropTable("Member");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void clean() {
		try {
			DBUtil.dbInitAllTables();			
			DBUtil.clearTable("Member");
			DBUtil.dropTable("Member");
			DBUtil.dbShutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
