package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.*;

/*
 * Ensure these four operations are implemented for the table
 * -Search
 * -Insert
 * -Update
 * -Delete
 */
public class StaffDAO {
	/*
	 * Returns a list of all staff in the staff table
	 */
    public ObservableList<Staff> findAll() throws SQLException {    			
        try {
        	/* Query database for staff */
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "STAFF", ""));
            
            ObservableList<Staff> list = this.getStaffList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	
    	return null;
    }
    
    /*
     * Pre-condition: staff_id is unique
     * Post-condition: st
     * @param staff_id the staff_id of the staff member you are trying to find
     */
    public Staff findById(String staff_id) throws SQLException {
    	try {
        	/* Query database for staff */
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "STAFF", "STAFF_ID=" + staff_id));
            
            ObservableList<Staff> list = this.getStaffList(rs);
            
            /* only try to return if list is not empty to prevent out of bounds exception */
            if (list.size() > 0) {
            	return list.get(0);
            }

            return null;
            
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	
    	return null;
    }
    
    /*
     * Finds a list of staff by their first and last names
     */
    public ObservableList<Staff> findByName(String first_name, String last_name) throws SQLException {
    	try {
        	/* Query database for staff */
        	ResultSet rs = DBUtil.dbExecuteQuery(
        			SQLBuilder.selectTable("*", "STAFF", "FIRST_NAME=" + first_name + " AND LAST_NAME=" + last_name));
            
            ObservableList<Staff> list = this.getStaffList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            //throw e;
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	
    	return null;
    }
      
    /*
     * Updates the details of a staff member in the database
     * 
     * Note: Will be able to update anything but username and staff_i
     */
    public void updateStaff(Staff staff) throws SQLException, ClassNotFoundException {
    	String sqlStmt = new UpdateSQLBuilder("STAFF")
    			.addFieldValue("PASSWORD", staff.getPassword())
    			.addFieldValue("FIRST_NAME", staff.getFirstName())
    			.addFieldValue("LAST_NAME", staff.getLastName())
    			.addFieldValue("EMAIL", staff.getEmail())
    			.addFieldValue("PHONE", staff.getPhoneNo())
    			.addFieldValue("HOME_ADDRESS", staff.getHomeAddress())
    			.addFieldValue("SALARY", staff.getSalary())
    			.where("STAFF_ID=" + staff.getStaff_id())
    			.toString();
    	
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}   
    }
    
    /*
     * Deletes a staff member from the database given a condition
     * 
     * Note: Ensure that your condition is formatted correctly for SQL
     */
    public void deleteStaff(String condition) throws SQLException, ClassNotFoundException {
    	try {
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("STAFF", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}    	
    }
    
    /*
     * Inserts a staff member into the database
     */
    public void insertStaff(Staff staff) throws SQLException, ClassNotFoundException {
    	String sqlStmt = new InsertSQLBuilder("STAFF")
    			.addFieldValue("USERNAME", staff.getUserName())
    			.addFieldValue("PASSWORD", staff.getPassword())
    			.addFieldValue("FIRST_NAME", staff.getFirstName())
    			.addFieldValue("LAST_NAME", staff.getLastName())
    			.addFieldValue("EMAIL", staff.getEmail())
    			.addFieldValue("PHONE", staff.getPhoneNo())
    			.addFieldValue("HOME_ADDRESS", staff.getHomeAddress())
    			.addFieldValue("SALARY", staff.getSalary())
    			.toString();
    			
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}   
    }

    /*
     * Helper function
     * Converts staff records from database into staff objects for java to play with
     */
    private ObservableList<Staff> getStaffList (ResultSet rs) throws SQLException {
    	ObservableList<Staff> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
    		try {
	    		Staff staff = new Staff();    		
	    		staff.setStaff_id(rs.getInt("STAFF_ID"));
	    		staff.setUserName(rs.getString("USERNAME"));
	    		staff.setPassword(rs.getString("PASSWORD"));
	    		staff.setFirstName(rs.getString("FIRST_NAME"));
	    		staff.setLastName(rs.getString("LAST_NAME"));
	    		staff.setEmail(rs.getString("EMAIL"));
	    		staff.setPhoneNo(rs.getString("PHONE"));
	    		staff.setHomeAddress(rs.getString("HOME_ADDRESS"));    		
	    		staff.setSalary(rs.getDouble("SALARY"));
	    		
	    		list.add(staff);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}