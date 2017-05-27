package model;

import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException; 
import javafx.collections.ObservableList;
import util.DBTablePrinter;
import util.DBUtil;
import util.SQLBuilder; 

/**
 *
 * @author selena
 */

public class ConsultationPaymentDAOTest {
    
    public void initialiseTables(){
        try{
            String sqlStmt = SQLBuilder.createTableSQL("agreementPayment.txt"); 
            DBUtil.dbInitTable(sqlStmt);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //create
    public AgreementPayment create(){
        AgreementPayment ap1 = new AgreementPayment();
        
        ap1.setPaymentAmount((float)200.00);
        ap1.setPaymentDate(10.10.2017);
        ap1.setPaymentAccount("1111323212321232");
        ap1.setPaymentType("Visa");
        ap1.setAccountExpiry("22.12.2017");
        ap1.setAccountOwnerName("Selena Tse");
        
        return ap1;
    }
    
    //test insert()
    @Test
    public void testInsert(){
        AgreementPaymentDAO agreementPaymentDAO = new AgreementPaymentDAO();
        
        try{
            initialiseTables();
            
            AgreementPayment ap1 = create();
            agreementPaymentDAO.insert(ap1);
            
            AgreementPayment ap2 = agreementPaymentDAO.findById(1);
            
            assertTrue(ap2 != null);
            assertTrue(ap2.getPaymentAmount().equals("paymentAmount1"));
			
            String url = "jdbc:derby:DBforDEMO;create=true";
            DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT_PAYMENT");
			
            DBUtil.dropTable("Agreement_Payment");
            DBUtil.dbShutdown();

	} catch (Exception e) {
            e.printStackTrace();
	}	
    }
    
    
    //test findAll()
    @Test
    public void testFindAll(){
        AgreementPaymentDAO agreementPaymentDAO = new AgreementPaymentDAO();
        
        try{
            initialiseTables();
            
            AgreementPayment ap1 = create();
            final int noRecords = 10;
            
            for (int i=0; i< noRecords; i++){
                agreementPaymentDAO.insert(ap1);
            }
            
            ObservableList<AgreementPayment> list = agreementPaymentDAO.findAll();
			
            assertEquals(list.size(), 10);  
			
            DBUtil.dropTable("AgreementPayment");
            DBUtil.dbShutdown();

	} catch (Exception e) {
            e.printStackTrace();
	}	
    }
    
    
	@Test
	public void testDelete() {
	
        model.AgreementPaymentDAO agreementPaymentDAO = new model.AgreementPaymentDAO();
				
	try {
            initialiseTables();

            model.AgreementPayment ap1 = create();
			
            final int noRecords = 10;
            for (int i = 0; i < noRecords; i++) {
                AgreementPaymentDAO.insert(ap1);
            }
				
            ObservableList<AgreementPayment> list = agreementPaymentDAO.findAll();
            
            assertEquals(list.size(), 10);
			
            agreementPaymentDAO.delete("AgreementPayment_id=1 OR AgreementPayment_id=2");
			
            list = agreementPaymentDAO.findAll();
            assertEquals(list.size(), 8);
			
            DBUtil.dropTable("AgreementPayment");
            DBUtil.dbShutdown();

        } catch (Exception e) {
            e.printStackTrace();
	}	
    }

	@Test
	public void testUpdate() {
            model.AgreementPaymentDAO agreementPaymentDAO = new AgreementPaymentDAO();
				
            try {
		initialiseTables();

		AgreementPayment ap1 = create();
		agreementPaymentDAO.insert(ap1);
		agreementPaymentDAO.insert(ap1);
			
		// edit the details of the 2nd record in the table
		AgreementPayment ap1Copy = agreementPaymentDAO.findById(2);
		ap1Copy.setPaymentAmount(12.00);
		ap1Copy.setPaymentDate("10.10.2017");
			
                String url = "jdbc:derby:DBforDEMO;create=true";
		DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT_PAYMENT");
			
		agreementPaymentDAO.update(ap1Copy);
			
		DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT_PAYMENT");
			
		// check that update went through
		assertTrue(agreementPaymentDAO.findById(2).getPaymentAmount() == 1111);
			
		DBUtil.clearTable("AgreementPayment");
		DBUtil.dropTable("AgreementPayment");
		DBUtil.dbShutdown();

	} catch (Exception e) {
            e.printStackTrace();
	}	
    }
	

    @Test
    public void clean() {
	try {
            initialiseTables();
			
            DBUtil.clearTable("AgreementPayment");
            DBUtil.dropTable("AgreementPayment");
            DBUtil.dbShutdown();
	} catch (Exception e) {
            e.printStackTrace();
	}	
    }
}
