package util;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ONUR BASKIRT on 22.02.2016.
 */
public class DBUtil {
    //Connection
    private static Connection conn = null;
    
    //private static final String connectionURL = "jdbc:derby://localhost:1527/DBforDEMO;create=true";
    private static final String connectionURL = "jdbc:derby:DBforDEMO;create=true";

    //Connect to DB
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        //Setting Oracle JDBC Driver
        try {
            conn = DriverManager.getConnection(connectionURL, "demo", "demo");
        } catch (SQLException e) {
            System.out.println("Connection to database failed");
            e.printStackTrace();
            throw e;
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
            	conn.close();
            }
        } catch (Exception e){
           throw e;
        }
    }
    
    public static void dbShutdown() throws SQLException {
    	try {
        	System.gc();
        	DriverManager.getConnection(connectionURL + ";shutdown=true", "demo", "demo");
        
        // shutting down correctly always throws this exception, ignore exception
    	} catch (SQLNonTransientConnectionException e) {
        	System.out.println("Shutting down...");
        } catch (SQLException e) {
        	System.out.println("Shut down failed");
        	e.printStackTrace();
        	throw e;
        }
    }
    
    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.createStatement();

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
    
    /*
     * Initialises a single table in the database with a SQL create statement
     */
    public static void dbInitTable(String sqlStmt) throws SQLException, ClassNotFoundException {
    	ResultSet rs;
    	
    	if (sqlStmt == null) {
    		System.out.println("DBUtil.dbInitTable: sqlStmt is null!");
    	}
    	
    	try {
    		dbConnect();
    	
    		String[] split = sqlStmt.split("[^\\w]+");
    		
    		// extract tableName from create table statement
    		String tableName = (split.length > 2) ? split[2].toUpperCase() : null;
    		
    		rs = conn.getMetaData().getTables(null, null, tableName, null);
    		
    		System.out.println("Creating table <<" + tableName + ">>");
    		
    		if (!rs.next() && tableName != null) {
    			// disconnect as we are connecting in try statement
        		dbDisconnect();
    			try {
    	        	System.out.println("Creating " + tableName + " table...");    		
    	            DBUtil.dbExecuteUpdate(sqlStmt);
    	        } catch (SQLException e) {
    	            System.out.print("Error occurred during CREATE Operation: " + e);
    	            throw e;
    	        }	
    		} else {
    			dbDisconnect();
    		}
    		
    	} catch (SQLException e) {
    		System.out.print("Error occured during getTables()");
    		throw e;
    	} 
    }
    
    /*
     * Initiliases all tables in the database
     */
    public static void dbInitAllTables() throws SQLException, ClassNotFoundException {
    	
    	ArrayList<String> fileNames = new ArrayList<String>();
    	
    	/* add your files that contain your CREATE TABLE statements here */
    	fileNames.add("staff.txt");
    	fileNames.add("member.txt");
    	fileNames.add("corporate.txt");
    	fileNames.add("corporateMember.txt");

    	/* */
    	
    	/* construct tables using the files given in ArrayList<String> fileNames */
    	for (String file: fileNames) {
    		String sqlStmt;
	    	try {
	    		sqlStmt = SQLBuilder.createTableSQL(file);    		    		
	    		dbInitTable(sqlStmt);	
	    	} catch (SQLException e) {
	    		System.out.println("Could not initilise " + file + " table...");
	    		e.printStackTrace();
	    		throw e;
	    	}
    	}
    }
    
    /*
     * Removes all rows inside a given table
     * Restarts numbering of primary keys at 0
     * 
     * Note: Use this if you want to clear all records inside of a table
     */
    public static void clearTable(String tableName) throws SQLException, ClassNotFoundException {
    	try {    		
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFrom(tableName));
    		DBUtil.dbExecuteUpdate(SQLBuilder.restartNumbering(tableName));
    	} catch (Exception e) {
    		System.out.println("Could not clear table: " + tableName);
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    /*
     * Drops a table from the database
     */
    public static void dropTable(String tableName) throws SQLException, ClassNotFoundException {
    	try {
    		DBUtil.dbExecuteUpdate(SQLBuilder.dropTable(tableName));
    	} catch (Exception e) {
    		System.out.println("Could not drop table: " + tableName);
    		e.printStackTrace();
    		throw e;
    	}
    }
}