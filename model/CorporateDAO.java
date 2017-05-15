package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;
import util.InsertSQLBuilder;
import util.SQLBuilder;
import util.UpdateSQLBuilder;

public class CorporateDAO implements TableDAO<Corporate> {
	public ObservableList<Corporate> findAll() throws SQLException, ClassNotFoundException {
		try {
        	/* Query database for member */
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "CORPORATE", ""));
            
            ObservableList<Corporate> list = this.getCorporateList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	
    	return null;
	}
	
	public Corporate findById(int corporateID) throws SQLException, ClassNotFoundException {
		try {
        	/* Query database for staff */
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "CORPORATE", "CORPORATE_ID=" + corporateID));
            
            ObservableList<Corporate> list = this.getCorporateList(rs);
            
            /* only try to return if list is not empty to prevent out of bounds exception */
            if (list.size() > 0) {
            	return list.get(0);
            }
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
		
		return null;
	}
	public void insert(Corporate corporate) throws SQLException, ClassNotFoundException {
		String sqlStmt = new InsertSQLBuilder()
				.addTable("CORPORATE")
				.addFieldValue("COMPANY_NAME", corporate.getCompanyName())
				.addFieldValue("COMPANY_ADDRESS", corporate.getCompanyAddr())
				.addFieldValue("COMPANY_PHONE_NO", corporate.getCompanyPhoneNumber())
				.addFieldValue("COMPANY_POSTCODE", corporate.getCompanyPCode())
				.addFieldValue("CUSTOMER_TYPE", corporate.getCustomerType())
				.toString();

    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}  
	}
	
	public void update(Corporate corporate) throws SQLException, ClassNotFoundException {
		String sqlStmt = new UpdateSQLBuilder()
				.addTable("CORPORATE")
				.addFieldValue("COMPANY_NAME", corporate.getCompanyName())
				.addFieldValue("COMPANY_ADDRESS", corporate.getCompanyAddr())
				.addFieldValue("COMPANY_PHONE_NO", corporate.getCompanyPhoneNumber())
				.addFieldValue("COMPANY_POSTCODE", corporate.getCompanyPCode())
				.addFieldValue("CUSTOMER_TYPE", corporate.getCustomerType())
				.where("CORPORATE_ID=" + corporate.getCorporateID())
				.toString();

    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}
	}
	
	public void delete(String condition) throws SQLException, ClassNotFoundException {
		try {
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("CORPORATE", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}
	}
	
	public ObservableList<Corporate> getCorporateList (ResultSet rs) throws SQLException {
    	ObservableList<Corporate> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
    		try {   			
    			Corporate corporate = new Corporate();
    			corporate.setCorporateID(rs.getInt("CORPORATE_ID"));
    			corporate.setCompanyName(rs.getString("COMPANY_NAME"));
    			corporate.setCompanyAddr(rs.getString("COMPANY_ADDRESS"));
    			corporate.setCompanyPhoneNumber(rs.getLong("COMPANY_PHONE_NO"));
    			corporate.setCompanyPCode(rs.getLong("COMPANY_POSTCODE"));
    			corporate.setCustomerType(rs.getInt("CUSTOMER_TYPE"));
    						
	    		list.add(corporate);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
