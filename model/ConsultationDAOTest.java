/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.DriverManager;
import util.DBTablePrinter;
import util.DBUtil;
import util.SQLBuilder;

/**
 *
 * @author elizabeth
 */
public class ConsultationDAOTest {
    public void init() {
        try {
                String sqlStmt = SQLBuilder.createTableSQL("consultation.txt");
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Consultation createRecord() {
        Consultation s1 = new Consultation();
        
        s1.setConsultationPrice((float) 100.00);
        s1.setConsultationTime(("12:00:00"));
        s1.setConsultationDate(("10.02.2017"));
        s1.setCorporateID(200);
        
        return s1;
    }
    
    @Test
    
    public void testInsert() {
        ConsultationDAO consultationDAO = new ConsultationDAO
                
      try {
                init();
                Consultation s1 = createRecord();
                consultationDAO.insert(s1);
                
                Consultation s2 = consultationDAO.findbyID(200);
                	

			
		    String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");
			
			DBUtil.dropTable("Consultation");
			DBUtil.dbShutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
      }
    
        
}
}
