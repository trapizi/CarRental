package util;

import java.util.ArrayList;

public abstract class Builder {
	protected ArrayList<String> fields;
	protected ArrayList<Object> values;
	protected ArrayList<String> tables;
	protected ArrayList<String> conditions;
	
	public Builder() {
		this.tables = new ArrayList<String>();
		this.fields = new ArrayList<String>();
		this.values = new ArrayList<Object>();
		this.conditions = new ArrayList<String>();
	}
	
	/*
	 * Add a field and a value for that field to the SQL statement
	 * 
	 */
	public Builder addFieldValue(String field, Object value) {
		this.fields.add(field);
		this.values.add(value);
		return this;
	}

	/*
	 * Add a field to the SQL statement
	 * 
	 * @param field - the field you want to insert in
	 */
	public Builder addField(String field) {
		this.fields.add(field);
		return this;
	}
	
	/*
	 * Add a table to the SQL statement
	 * 
	 * @param tableName - the table you want to insert in
	 */
	public Builder addTable(String tableName) {
		this.tables.add(tableName);
		return this;
	}
	
	/*
	 * Add a condition to the SQL statement
	 */
	public Builder where(String condition) {
		this.conditions.add(" WHERE " + condition);
		return this;
	}
	
	/*
	 * Add more conditions to the SQL statement
	 */
	public Builder and(String condition) {
		this.conditions.add(" AND " + condition);
		return this;
	}
}
