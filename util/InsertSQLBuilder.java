package util;

public class InsertSQLBuilder extends Builder {
	public InsertSQLBuilder() {
		super();
	}
	
	public InsertSQLBuilder addTable(String tableName) {
		return (InsertSQLBuilder) (super.addTable(tableName));
	}
	
	/*
	 * Add a field and a value for that field to the insert statement
	 * 
	 * @param field - the field you want to insert in
	 * @param value - the value you want to insert into the field
	 */
	@Override
	public InsertSQLBuilder addFieldValue(String field, Object value) {
		return (InsertSQLBuilder) (super.addFieldValue(field, value));
	}
		
	/*
	 * Use this to get a string of the SQL insert statement
	 */
	@Override
	public String toString() {
		StringBuilder SQLStmt = new StringBuilder();
		StringBuilder fieldText = new StringBuilder();
		StringBuilder valueText = new StringBuilder();
				
		// Append fields into a query
		int lastIndex = fields.size() - 1;
		for (int i = 0; i < fields.size(); i++) {
			
			fieldText.append(fields.get(i));

			if (i != lastIndex) {
				fieldText.append(",");
			}
		}

		// Append values into a query
		lastIndex = values.size() - 1;
		for (int i = 0; i < values.size(); i++) {
			
			if (values.get(i) instanceof String) {
				valueText.append("'" + values.get(i) + "'");
			} else {
				valueText.append(values.get(i));
			}

			if (i != lastIndex) {
				valueText.append(",");
			}
		}
		
		SQLStmt.append("INSERT INTO " + this.tables.get(0) + " (" + fieldText + ") VALUES (" + valueText + ")");
		
		return SQLStmt.toString();
	}
}
