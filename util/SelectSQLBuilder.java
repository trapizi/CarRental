package util;

import java.util.ArrayList;

/*
 * Builds select SQL statements
 * 
 * Doesn't support JOINS -- need to write them yourself
 */
public class SelectSQLBuilder extends Builder {		
	public SelectSQLBuilder() {
		super();
	}
	
	@Override
	public SelectSQLBuilder addField(String field) {
		return (SelectSQLBuilder) (super.addField(field));
	}
	
	@Override
	public SelectSQLBuilder addTable(String tableName) {
		return (SelectSQLBuilder) (super.addTable(tableName));
	}
	
	@Override
	public SelectSQLBuilder where(String condition) {		
		return (SelectSQLBuilder) (super.where(condition));
	}

	@Override
	public SelectSQLBuilder and(String condition) {
		return (SelectSQLBuilder) (super.and(condition));
	}
	
	@Override
	public String toString() {
		StringBuilder SQLStmt = new StringBuilder();
		StringBuilder columnText = new StringBuilder();
		StringBuilder tableText = new StringBuilder();
		StringBuilder conditionText = new StringBuilder();

		for (int i = 0; i < fields.size(); i++) {
			columnText.append(fields.get(i));
			
			if (i != fields.size() - 1) {
				columnText.append(",");
			}
		}
		
		for (int i = 0; i < tables.size(); i++) {
			tableText.append(tables.get(i));
			
			if (i != tables.size() - 1) {
				tableText.append(",");
			}
		}
		
		for (int i = 0; i < conditions.size(); i++) {
			conditionText.append(conditions.get(i));
		}
		
		SQLStmt.append("SELECT " + columnText + " FROM " + tableText + conditionText);
		
		return SQLStmt.toString();
	}
}
