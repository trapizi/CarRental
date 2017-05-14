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
	
	/*
	 * Constructs an SQL statement using the contents of a given file
	 * 
	 * Follow the format of staff.txt to get an idea of what to put in these files
	 * 
	 * Note: Place your file in the same folder as where staff.txt is placed otherwise netbeans/eclipse
	 *       complains about not finding your file
	 *       
	 * Note: IF YOU CAN'T FIND THE CORRECT FILE PUT THIS LINE IN THE MAIN FUNCTION TO SEE WHERE YOU SHOULD PUT IT
	 * 		 
	 * 		 System.out.println(System.getProperty("user.dir"));
	 * 
	 *       The line above will print out the folder where you should put the file onto the console.
	 *       
	 * Usage: String createStmt = createTable("staff.txt");
	 */
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
	
	/*
	 * Constructs a SQL SELECT statement given a field, tableName and an optional condition.
	 * 	 
	 * Usage (no WHERE clause): String selectStmt = selectTable("staff_id", staff, "")
	 * Usage (WHERE clause): String selectStmt = selectTable("staff_id", staff, "id=1")
	 */
	public static String selectTable(String fields, String tableName, String condition) {
		if (condition.equals("")) {
			return "SELECT " + fields + " FROM " + tableName;
		} 
		return "SELECT " + fields + " FROM " + tableName + " WHERE " + condition;
	}
	
	/*
	 * Constructs a simple SQL DROP statement given a tableName
	 */
	public static String dropTable(String tableName) {		
		return "DROP TABLE " + tableName;
	}
	
	/*
	 * Constructs a simple SQL DELETE statement given a tableName
	 */
	public static String deleteFrom(String tableName) {
		return "DELETE FROM " + tableName;
	}
	
	/*
	 * Constructs a SQL DELETE statement with a condition
	 * 
	 * Usage: String deleteStmt = deleteFromCondition("staff", "id=1");
	 */
	public static String deleteFromCondition(String tableName, String condition) {
		return deleteFrom(tableName) + " WHERE " + condition;
	}
	
	/*
	 * Constructs a SQL ALTER TABLE statement with a tableName
	 * 
	 */
	public static String alterTable(String tableName) {
		return "ALTER TABLE " + tableName;
	}
		
	/*
	 * Constructs an SQL statement to reset the numbering of your primary key
	 * 
	 * Use this after you have deleted all rows from your table to restart your primary key numbering.
	 * 
	 * Note: Assumes your primary key name has the format 'tableName'_ID, e.g. staff table's PK is STAFF_ID
	 */
	public static String restartNumbering(String tableName) {
		return alterTable(tableName) + " ALTER " + tableName + "_ID RESTART WITH 0";
	}
}