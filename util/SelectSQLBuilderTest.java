package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class SelectSQLBuilderTest {

	@Test
	public void test() {
		SelectSQLBuilder sql = new SelectSQLBuilder()
				.addField("CORPORATE.CORPORATE_ID")
				.addField("MEMBER.*")
				.addTable("CORPORATE_MEMBER")
				.addTable("CORPORATE")
				.addTable("MEMBER")
				.where("CORPORATE_MEMBER.CORPORATE_ID = CORPORATE.CORPORATE_ID")
				.and("CORPORATE_MEMBER.MEMBER_ID = MEMBER.MEMBER_ID");
				//.toString();
		System.out.println(sql.toString());
		
	}

}
