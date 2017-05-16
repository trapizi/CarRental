package test;

public class SarinaTest {
	public static void testAgreementTable() {
		//Initialise your DAO objects to test your tables here 
		AgreementDAO agmtDAO = new AgreementDAO();

		Agreement agmt1 = new Agreement();
		agmt1.setStatus("pending");
		agmt1.setPayAmt("50");

		Agreement agmt2 = new Agreement();
		agmt2.setStatus("accepted");
		agmt2.setPayAmt("100");

		try {
			agmtDAO.insert(agmt1);
			agmtDAO.insert(agmt2);

			final String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT");


			//	agmt1.setPayAmt("20");
			Agreement a = agmtDAO.findById(1);
			//a.setAgreement_id(a.getAgreement_id());
			a.setPayAmt("20");
			a.setStatus("test_status");
			agmtDAO.update(a);

			//agmtDAO.delete("AGREEMENT_ID = 1");

			System.out.println(a.toString());

			Agreement aCopy = agmtDAO.findById(1);
			System.out.println("aCopy payamt: " + aCopy.getPayAmt());

			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT");

			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
			DBUtil.clearTable("AGREEMENT");
			DBUtil.dropTable("AGREEMENT");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
