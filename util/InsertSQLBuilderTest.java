package util;


import java.sql.Time;
import java.sql.Date;

import org.junit.Test;

/**
 * Class for building simple Insert SQL statements
 * @author Bing Wen (z3463269)
 */
public class InsertSQLBuilderTest {

	@Test
	public void test() {
		/* Spell your table names and field names correctly or you'll get SQLexceptions */
		Builder i = new InsertSQLBuilder()
			.addTable("staff")
			.addFieldValue("staff_id", 1)
			.addFieldValue("salary", 10000)
			.addFieldValue("date", new Date(10,10,10))
			.addFieldValue("time", new Time(1,1,1))
			.addFieldValue("first_name", "kek");
		
		String myInsertStmt = i.toString();
		
		System.out.println(myInsertStmt);
	}

}
