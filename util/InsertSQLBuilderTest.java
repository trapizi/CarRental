package util;

public class InsertSQLBuilderTest {

	public void test() {
		/* Spell your table names and field names correctly or you'll get SQLexceptions */
		Builder i = new InsertSQLBuilder()
			.addTable("staff")
			.addFieldValue("staff_id", 1)
			.addFieldValue("salary", 10000)
			.addFieldValue("first_name", "kek");
		
		String myInsertStmt = i.toString();
		
		System.out.println(myInsertStmt);
	}

}
