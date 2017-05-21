/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Consultation;
import model.ConsultationDAO;
import model.Corporate;
import model.CorporateDAO;
import util.DBTablePrinter;
import util.DBUtil;

/**
 *
 * @author elizabeth
 */
public class LizTest {
    	public static void initMyTables() throws Exception {
		try {
			DBUtil.dbInitAllTables();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void testConsultationTable() throws SQLException {
		//Initialise your DAO objects to test your tables here 
                CorporateDAO cDAO = new CorporateDAO();
                try {
                    cDAO.insert(new Corporate());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LizTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                Corporate c;
                try {
                    c = cDAO.findById(1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LizTest.class.getName()).log(Level.SEVERE, null, ex);
                }
	
			
                try {
                    cDAO.insert(new Corporate());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LizTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    c = cDAO.findById(2);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LizTest.class.getName()).log(Level.SEVERE, null, ex);
                }
		
                        
		System.out.println("!!!!!!!!!!!!!!!!!!!!");
                ConsultationDAO consultationDAO = new ConsultationDAO();
                        System.out.println("@@@@@@@@@@@@@@@@");
		Consultation consult1 = new Consultation();
                consult1.setCorporateID(2);
		consult1.setConsultationPrice((float) 100.00);
                                        System.out.println("@@@@@@@@@@@@@@@@");
		consult1.setConsultationTime(new Time(10,10,10));
                consult1.setConsultationDate(new Date(10,10,10));
// consult1.setConsultationTime(("12:00:00"));
                // consult1.setConsultationDate(("11.10.2017"));
                        System.out.println("@@@@^^^^^^^^^");
		Consultation consult2 = new Consultation();
                consult2.setCorporateID(1);
		consult2.setConsultationPrice((float) 100.00);
                consult2.setConsultationTime(new Time(12,12,12));
                consult2.setConsultationDate(new Date(12,12,12));
                

		try {
                                            System.out.println("@@@@@@@@@@@@@@@@");
			consultationDAO.insert(consult1);
			consultationDAO.insert(consult2);
                              //print 
			final String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");

                                                                
			//	
			Consultation c1 = consultationDAO.findById(1);
			
			// c1.setConsultationPrice((float) 100.00);
			// c1.setConsultationTime(new Time(10,10,10));
                      
			// consultationDAO.update(c1);	



			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");

			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
//s			DBUtil.clearTable("CONSULTATION");
			DBUtil.dropTable("CONSULTATION");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        }
}

