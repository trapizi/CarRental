package util;

import java.sql.Time;
import java.util.Date;

/* Look in UpdateSQLBuilderTest.java to see how it's used */
public class UpdateSQLBuilder extends Builder {		
	public UpdateSQLBuilder() {
		super();
	}
		
	@Override
	public UpdateSQLBuilder addTable(String tableName) {
		return (UpdateSQLBuilder) (super.addTable(tableName));
	}
	
	@Override
	public UpdateSQLBuilder addFieldValue(String field, Object value) {
		return (UpdateSQLBuilder) (super.addFieldValue(field, value));
	}	
	
	@Override
	public UpdateSQLBuilder where(String condition) {
		return (UpdateSQLBuilder) (super.where(condition));		
	}
	
	@Override
	public UpdateSQLBuilder and(String condition) {		
		return (UpdateSQLBuilder) (super.and(condition));
	}
	
	/*
	 * Use this to get a string of the SQL insert statement
	 */
	@Override
	public String toString() {
		StringBuilder SQLStmt = new StringBuilder();
		StringBuilder updateText = new StringBuilder();
		StringBuilder conditionText = new StringBuilder();
				
		// Append fields and values into a query
		int lastIndex = fields.size() - 1;
		for (int i = 0; i < fields.size(); i++) {
			
			updateText.append(fields.get(i) + "=");

			if (values.get(i) instanceof String || values.get(i) instanceof Time || values.get(i) instanceof Date) {
				updateText.append("'" + values.get(i) + "'");
			} else {
				updateText.append(values.get(i));
			}
		
			if (i != lastIndex) {
				updateText.append(",");
			}
		}
		
		for (int i = 0; i < conditions.size(); i++) {
			conditionText.append(conditions.get(i));
		}
		
		SQLStmt.append("UPDATE " + this.tables.get(0) + " SET " + updateText + " " + conditionText);
		
		return SQLStmt.toString();
	}
}
