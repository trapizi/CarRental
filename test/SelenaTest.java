package test;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Agreement;
import model.AgreementDAO;
import model.AgreementPayment;
import model.AgreementPaymentDAO;
import util.DBTablePrinter;
import util.DBUtil;

/**
 *
 * @author selena
 */

public class SelenaTest {
    
    public static void initMyTables() throws Exception {
	try {
            DBUtil.dbInitAllTables();
	} catch (Exception e) {
            throw e;
	}
    }
    
    public static void testAgreementPaymentTable() {
	//Initialiss DAO objects to test tables 
	AgreementPaymentDAO agreementPaymentDAO = new AgreementPaymentDAO();

	AgreementPayment ap1 = new AgreementPayment();
	ap1.setPaymentAmount(23.22);
	ap1.setAccountOwnerName("Selena Tse");

	AgreementPayment ap2 = new AgreementPayment();
	ap2.setPaymentAmount(33.23);
	ap2.setPaymentDate(new Date(1000,1,1));
	ap2.setPaymentAccount("Selena Tse");
	ap2.setPaymentType("Visa");
	ap2.setAccountExpiry(new Date(1000,1,1));
	ap2.setAccountOwnerName("Selena Tse");
	
	try {
            agreementPaymentDAO.insert(ap1);
            agreementPaymentDAO.insert(ap2);

            final String url = "jdbc:derby:DBforDEMO;create=true";
            DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT_PAYMENT");


            AgreementPayment ap3 = agreementPaymentDAO.findById(1);
            ap3.setPaymentAmount(55.43);
            ap3.setPaymentAccount("Selena Update");
            agreementPaymentDAO.update(ap3);	

            //System.out.println(a.toString());

            AgreementPayment ap4 = agreementPaymentDAO.findById(2);
            ap4.setPaymentAccount("Bob Selena");
            ap4.setPaymentType("Mastercard");
            agreementPaymentDAO.update(ap4);

            //AgreementPaymentDAO.delete("AGREEMENT_PAYMENT_ID=2");
			
            DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT_PAYMENT");

            // RESTART NUMBERING AFTER DELETING ROWS FROM TABLE

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
	}
}