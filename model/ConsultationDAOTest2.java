package model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Time;

import org.junit.Test;

import util.DBTablePrinter;
import util.DBUtil;
import util.SQLBuilder;

public class ConsultationDAOTest2 {
    public void init() {
        try {
                DBUtil.dbInitAllTables();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public Consultation createRecord() {
		Consultation s1 = new Consultation();

		s1.setConsultationPrice((float) 100.00);
		s1.setConsultationTime(new Time(1,1,1));
		s1.setConsultationDate(new Date(1,1,1));
		s1.setCorporateID(1);

		return s1;
	}
    
	@Test
	public void test() {
		init();
		
		ConsultationDAO consultationDAO = new ConsultationDAO();
		
		try {
			init();
			CorporateDAO cDAO = new CorporateDAO();
			cDAO.insert(new Corporate());
			
			Consultation s1 = createRecord();
			consultationDAO.insert(s1);

			String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");
			
			Consultation s2 = consultationDAO.findById(100);

			assertTrue(s2 != null);
			
			s2.setConsultationDate(new Date(1980,10,10));
			s2.setConsultationTime(new Time(10,10,100));
			consultationDAO.update(s2);
			
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");
			//DBUtil.dropTable("Consultation");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.dropTable("CONSULTATION");
				DBUtil.dbShutdown();
			} catch (Exception e) {
				System.out.println("Problem shutting down");
			}
		}
	}

}
