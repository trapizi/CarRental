package util;

public class UpdateSQLBuilderTest {

	public void test() {
		UpdateSQLBuilder b = new UpdateSQLBuilder()
				.addTable("staff")
				.addFieldValue("email", "kek@kek") 
				.addFieldValue("salary", 1000.00)
				.where("salary < 1000")
				.and("email='kek'");
		
		System.out.println(b.toString());
	}

}
