package util;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Scanner;

/*
 * Constructs SQL statements for Apache Derby
 * Switch out this class for your own if you are using another database
 */
public class SQLBuilder {
	public static String createTableSQL(String filename) {
		File file = new File(filename);
		StringBuilder str = new StringBuilder();
		
		Scanner sc;
		
		try {
			sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String curLine = sc.nextLine();
				str.append(curLine + "\n");				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cannot create table... File not found: " + filename);
		}
		
		return str.toString();
	}
	
	public static String selectTable(String fields, String tableName, String condition) {
		if (condition.equals("")) {
			return "SELECT " + fields + " FROM " + tableName;
		} 
		return "SELECT " + fields + " FROM " + tableName + " WHERE " + condition;
	}
	
	public static String dropTable(String tableName) {		
		return "DROP TABLE " + tableName;
	}
	
	public static String deleteFrom(String tableName) {
		return "DELETE FROM " + tableName;
	}
	
	public static String deleteFromCondition(String tableName, String condition) {
		return deleteFrom(tableName) + " WHERE " + condition;
	}
	
	public static String alterTable(String tableName) {
		return "ALTER TABLE " + tableName;
	}
		
	/*
	 * Assumes your primary key name has the format 'tableName'_ID, e.g. staff table's PK is STAFF_ID
	 */
	public static String restartNumbering(String tableName) {
		return alterTable(tableName) + " ALTER " + tableName + "_ID RESTART WITH 0";
	}
}