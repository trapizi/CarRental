package model;

import static org.junit.Assert.*;


import org.junit.Test;

import javafx.collections.ObservableList;
import model.Offer;
import util.DBUtil;

public class OfferDAOTest {

/*	public void init(){
		try{
			String sqlStmt = SQLBuilder.createTableSQL("offer.txt");
			DBUtil.dbInitTable(sqlStmt);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Offer createRecord() {
		Offer s1 = new Offer();
		
		s1.setSeats(5);
		s1.setCarType("SEDAN");
		s1.setBrand("FORD");
		s1.setModel("GT");
		s1.setTransmission("AUTO");
		s1.setFuelType("DIESEL");
		s1.setPrice(99);
		s1.setPostcode(2000);
		
		return s1;
	}
	*/

	//Test Insert and Find by ID
	@Test
	public void testInsert() {
		OfferDAO offerDAO = new OfferDAO();
		
		try {
			DBUtil.dbInitAllTables();	
			
			Offer s1 = new Offer();
			s1.setOfferID(1);
			offerDAO.insert(s1);
			
			Offer s2 = offerDAO.findById(1);
			
			assertNotNull(s2);
			assertEquals(s2.getOfferID(), 1);
			
			DBUtil.clearTable("OFFER");
			DBUtil.dropTable("OFFER");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
    public void testFindAll() {
        OfferDAO offerDAO = new OfferDAO();

        try {
            DBUtil.dbInitAllTables();

            Offer s1 = new Offer();
            offerDAO.insert(s1);

            ObservableList<Offer> list = offerDAO.findAll();
            assertNotNull(list);


            for (Offer o: list) {
                System.out.println(o.getOfferID());
            }


            DBUtil.clearTable("Offer");
            DBUtil.dropTable("Offer");
            DBUtil.dbShutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Test
	public void testDelete() {
		OfferDAO offerDAO = new OfferDAO();
		
		
		try {
			DBUtil.dbInitAllTables();
			
			Offer s1 = new Offer();
			final int noRecords = 10;
			
			for (int i = 0; i < noRecords; i++) {
				offerDAO.insert(s1);
			}
			
			ObservableList<Offer> list = offerDAO.findAll();
			assertEquals(list.size(), 10);
			
			DBUtil.clearTable("Offer");
            DBUtil.dropTable("Offer");
            DBUtil.dbShutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		OfferDAO offerDAO = new OfferDAO();
		
		 try {
	            DBUtil.dbInitAllTables();
	            
	            Offer s1 = new Offer();
	            offerDAO.insert(s1);
	            offerDAO.insert(s1);
	            
	            Offer s2Copy = offerDAO.findById(2);
	            s2Copy.setBrand("LAMBORGHINI");
	            
	            offerDAO.update(s2Copy);
	            
	            assertTrue(offerDAO.findById(2).getBrand().equals("LAMBORGHINI"));
	            
	            DBUtil.clearTable("Offer");
	            DBUtil.dropTable("Offer");
	            DBUtil.dbShutdown();
	            
	            
	} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
	
