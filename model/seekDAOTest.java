package model;

import static org.junit.Assert.*;

import org.junit.Test;
import javafx.collections.ObservableList;
import model.Seek;
import util.DBUtil;

public class seekDAOTest {

/*	public void init(){
		try{
			String sqlStmt = SQLBuilder.createTableSQL("seek.txt");
			DBUtil.dbInitTable(sqlStmt);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	*/
	
	@Test
	public void testInsert() {
		seekDAO seekDAO = new seekDAO();
		
		try {
			DBUtil.dbInitAllTables();	
			
			Seek s1 = new Seek();
			s1.setSeekID(1);
			seekDAO.insert(s1);
			
			Seek s2 = seekDAO.findById(1);
			
			assertNotNull(s2);
			assertEquals(s2.getSeekID(), 1);
			
			DBUtil.clearTable("SEEK");
			DBUtil.dropTable("SEEK");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
