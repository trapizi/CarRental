package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.*;

public class AgreementDAO implements TableDAO<Agreement> {

    public ObservableList<Agreement> findAll() throws SQLException {    			
        try {
        	/* Query database*/
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "AGREEMENT", ""));
            
            ObservableList<Agreement> list = this.getAgreementList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	
    	return null;
    }
      
    /*
     * Inserts an agreement into the database
     */
    public void insert(Agreement agmt) throws SQLException, ClassNotFoundException {
    	String sqlStmt = new InsertSQLBuilder("AGREEMENT")
    			//.addFieldValue("SEEKER", agmt.getSeeker())
    			//.addFieldValue("OFFERER", agmt.getOfferer())
    			.addFieldValue("STATUS", agmt.getStatus())
    			.addFieldValue("PAYAMT", agmt.getPayAmt())
    			.addFieldValue("AGREEDATE", agmt.getAgreeDate())
    			.addFieldValue("UNIQUENO", agmt.getUniqueNo())
    			.addFieldValue("CREATEDAY", agmt.getCreateDay())
    			.addFieldValue("INITIATEBY", agmt.getInitiateBy())
    			//.addFieldValue("OFFER", agmt)
    			.addFieldValue("OFFERRECEIVABLE", agmt.getOfferReceivable())
    			.addFieldValue("TOPIN", agmt.getToPin())
    			.addFieldValue("FROMPIN", agmt.getFromPin())
    			.addFieldValue("PUPFROM", agmt.getpUpFrom())
    			.addFieldValue("PUPTO", agmt.getpUpTo())
    			.addFieldValue("DAY", agmt.getDay())
    			.toString();
    			
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}   
    }
    
    /*
     * Updates the details of an agreement in the database
     * 
     * Note: Will be able to update anything but username and staff_id
     */
    public void update(Agreement agmt) throws SQLException, ClassNotFoundException {
    	String sqlStmt = new UpdateSQLBuilder("AGREEMENT")
    			//.addFieldValue("SEEKER", agmt.getSeeker())
    			//.addFieldValue("OFFERER", agmt.getOfferer())
    			.addFieldValue("STATUS", agmt.getStatus())
    			.addFieldValue("PAYAMT", agmt.getPayAmt())
    			.addFieldValue("AGREEDATE", agmt.getAgreeDate())
    			.addFieldValue("UNIQUENO", agmt.getUniqueNo())
    			.addFieldValue("CREATEDAY", agmt.getCreateDay())
    			.addFieldValue("INITIATEBY", agmt.getInitiateBy())
    			//.addFieldValue("OFFER", agmt)
    			.addFieldValue("OFFERRECEIVABLE", agmt.getOfferReceivable())
    			.addFieldValue("TOPIN", agmt.getToPin())
    			.addFieldValue("FROMPIN", agmt.getFromPin())
    			.addFieldValue("PUPFROM", agmt.getpUpFrom())
    			.addFieldValue("PUPTO", agmt.getpUpTo())
    			.addFieldValue("DAY", agmt.getDay())
    			.toString();
    	
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}   
    }
    
    /*
     * Deletes a item from the database given a condition
     * 
     * Note: Ensure that your condition is formatted correctly for SQL
     */
    public void delete(String condition) throws SQLException, ClassNotFoundException {
    	try {
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("AGREEMENT", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}    	
    }
    
    /*
     * Helper function
     * Converts records from database into objects for java to play with
     */
    private ObservableList<Agreement> getAgreementList (ResultSet rs) throws SQLException {
    	ObservableList<Agreement> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
    		try {
	    		Agreement agmt = new Agreement();    		
	    		
	    		//agmt.setSeeker(rs.getInt("SEEKER"));
	    		//agmt.setOfferer(rs.getInt("OFFERER"));
	    		agmt.setStatus(rs.getString("STATUS"));
	    		agmt.setPayAmt(rs.getString("PAYAMT"));
	    		agmt.setAgreeDate(rs.getString("AGREEDATE"));
	    		agmt.setUniqueNo(rs.getString("UNIQUENO"));
	    		agmt.setCreateDay(rs.getString("CREATEDAY"));
	    		agmt.setInitiateBy(rs.getString("INITIATEBY"));
	    		//agmt.setOffer??
	    		agmt.setOfferReceivable(rs.getDouble("OFFERRECEIVABLE"));
	    		agmt.setToPin(rs.getLong("TOPIN"));
	    		agmt.setFromPin(rs.getLong("FROMPIN"));
	    		agmt.setpUpFrom(rs.getLong("PUPFROM"));
	    		agmt.setpUpTo(rs.getLong("PUPTO"));
	    		agmt.setDay(rs.getString("DAY"));
	    	
	    		list.add(agmt);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
	
	public Agreement findById(int agmt_id) throws SQLException {
    	try {
        	/* Query database for agreement */
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "AGREEMENT", "AGREEMENT_ID=" + agmt_id));
            
            ObservableList<Agreement> list = this.getAgreementList(rs);
            
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
	

}