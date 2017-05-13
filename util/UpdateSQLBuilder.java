package util;

/* Look in UpdateSQLBuilderTest.java to see how it's used */
public class UpdateSQLBuilder extends Builder {	
	private StringBuilder condition;
	
	public UpdateSQLBuilder(String tableName) {
		super(tableName);
		this.condition = new StringBuilder();
	}
		
	@Override
	public UpdateSQLBuilder addFieldValue(String field, Object value) {
		this.fields.add(field);
		this.values.add(value);
		return this;
	}	
	
	public UpdateSQLBuilder where(String condition) {
		this.condition.append(" WHERE " + condition);
		
		return this;
	}
	
	public UpdateSQLBuilder and(String condition) {
		this.condition.append(" AND " + condition);
		
		return this;
	}
	
	/*
	 * Use this to get a string of the SQL insert statement
	 */
	@Override
	public String toString() {
		StringBuilder SQLStmt = new StringBuilder();
		StringBuilder updateText = new StringBuilder();
				
		// Append fields and values into a query
		int lastIndex = fields.size() - 1;
		for (int i = 0; i < fields.size(); i++) {
			
			updateText.append(fields.get(i) + "=");

			if (values.get(i) instanceof String) {
				updateText.append("'" + values.get(i) + "'");
			} else {
				updateText.append(values.get(i));
			}
			
			if (i != lastIndex) {
				updateText.append(",");
			}
		}
		
		SQLStmt.append("UPDATE " + this.tableName + " SET " + updateText + " " + this.condition);
		
		return SQLStmt.toString();
	}
}
