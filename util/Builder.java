package util;

import java.util.ArrayList;

public abstract class Builder {
	protected ArrayList<String> fields;
	protected ArrayList<Object> values;
	protected String tableName;
	
	public Builder(String tableName) {
		this.tableName = tableName;
		this.fields = new ArrayList<String>();
		this.values = new ArrayList<Object>();
	}
	
	/*
	 * Add a field and a value for that field to the insert statement
	 * 
	 * @param field - the field you want to insert in
	 * @param value - the value you want to insert into the field
	 */
	public Builder addFieldValue(String field, Object value) {
		this.fields.add(field);
		this.values.add(value);
		return this;
	}	
}
