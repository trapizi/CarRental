package project.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.util.*;

public class StaffDerbyDAO implements StaffDAO {
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
    
    public ObservableList<Staff> findById(String staff_id) throws SQLException {
    	try {
        	/* Query database for staff */
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "STAFF", "STAFF_ID=" + staff_id));
            
            ObservableList<Staff> list = this.getStaffList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	
    	return null;
    }
    
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
    
    public void insertStaff(Staff staff) throws SQLException {
    
    }
    
    public void updateStaff(Staff staff) throws SQLException {
    	
    }
    
    public void deleteStaff(String condition) throws SQLException, ClassNotFoundException {
    	try {
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("STAFF", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}    	
    }
    
    public void insertStaffName(String firstName, String lastName) throws SQLException, ClassNotFoundException {
        String sqlStmt =
	    	"INSERT INTO STAFF\n" +
	        "(FIRST_NAME, LAST_NAME)\n" +
	        "VALUES\n" +
	        "('"+firstName+"', '"+lastName+"')\n";
        
        try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}   
    }
    
    /*
    private Staff getStaff(ResultSet rs) throws SQLException {
    	Staff staff = null;
    	
    	if (rs.next()) {
    		try {
	    		staff = new Staff();
	    		staff.setStaff_id(rs.getInt("STAFF_ID"));
	    		staff.setUserName(rs.getString("USERNAME"));
	    		staff.setPassword(rs.getString("PASSWORD"));
	    		staff.setFirstName(rs.getString("FIRST_NAME"));
	    		staff.setLastName(rs.getString("LAST_NAME"));
	    		staff.setEmail(rs.getString("EMAIL"));
	    		staff.setPhoneNo(rs.getString("PHONE"));
	    		staff.setHomeAddress(rs.getString("HOME_ADDRESS"));    		
	    		staff.setSalary(rs.getDouble("SALARY"));
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	
    	return staff;
    }
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