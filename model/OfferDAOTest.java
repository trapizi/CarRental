package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import model.Offer;
import model.Offer;
import util.DBUtil;
import util.SQLBuilder;

public class OfferDAOTest {

	public void init(){
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
			
			DBUtil.clearTable("Offer");
			DBUtil.dropTable("Offer");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
