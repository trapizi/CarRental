package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class UpdateSQLBuilderTest {

	@Test
	public void test() {
		UpdateSQLBuilder b = new UpdateSQLBuilder("staff")
				.addFieldValue("email", "kek@kek") 
				.addFieldValue("salary", 1000.00)
				.where("salary < 1000")
				.and("email='kek'");
		
		System.out.println(b.toString());
	}

}
