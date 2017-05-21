/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import util.DBTablePrinter;
import util.DBUtil;
import util.SQLBuilder;

/**
 *
 * @author elizabeth
 */
public class ConsultationDAOTest {
	public static void initMyTables() throws Exception {
		try {
			DBUtil.dbInitAllTables();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void testConsultationTable() {
		//Initialise your DAO objects to test your tables here 
		ConsultationDAO consultationDAO = new ConsultationDAO();

		Consultation consult1 = new Consultation();
		consult1.setConsultationPrice((float) 100.00);
		consult1.setConsultationTime(("12:00:00"));
                consult1.setConsultationTime(("11.10.2017"));

		Consultation consult2 = new Consultation();
		consult2.setConsultationPrice((float) 100.00);
                consult2.setConsultationTime(("15:00:00"));
                consult2.setConsultationDate(("12.10.2017"));

		try {
			consultationDAO.insert(consult1);
			consultationDAO.insert(consult2);

			final String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");


			//	
			Consultation c = consultationDAO.findById(1);
			
			c.setConsultationPrice((float) 100.00);
			c.setConsultationTime(("12:00:00"));
			consultationDAO.update(c);	

			//agmtDAO.delete("AGREEMENT_ID = 1");

			System.out.println(c.toString());

			Consultation cCopy = consultationDAO.findById(1);
			System.out.println("cCopy price: " + cCopy.getConsultationPrice());

			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");

			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
			DBUtil.clearTable("CONSULTATION");
			DBUtil.dropTable("CONSULTATION");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        }
}