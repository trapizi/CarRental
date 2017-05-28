package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;
import util.InsertSQLBuilder;
import util.SQLBuilder;
import util.SelectSQLBuilder;
import util.UpdateSQLBuilder;

public class CorporateMemberDAO implements TableDAO<CorporateMember> {
	public ObservableList<CorporateMember> findAll() throws SQLException, ClassNotFoundException {
        try {
        	/* TODO: change to display member details for a list of corporate members */
        	String sqlStmt = "SELECT CORPORATE.CORPORATE_ID, MEMBER.*"
        				+ " FROM CORPORATE_MEMBER, MEMBER, CORPORATE"
        				+ " WHERE CORPORATE_MEMBER.MEMBER_ID = MEMBER.MEMBER_ID"
        				+ " AND CORPORATE_MEMBER.CORPORATE_ID = CORPORATE.CORPORATE_ID";
        	
        	ResultSet rs = DBUtil.dbExecuteQuery(sqlStmt);
            
            ObservableList<CorporateMember> list = this.getCorporateMemberList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	return null;
	}
	
	public CorporateMember findById(int memberID) throws SQLException, ClassNotFoundException {
		try {
        	/* Query database for staff */
        	String sqlStmt = "SELECT CORPORATE.CORPORATE_ID, MEMBER.*"
    				+ " FROM CORPORATE_MEMBER, MEMBER, CORPORATE"
    				+ " WHERE CORPORATE_MEMBER.MEMBER_ID = MEMBER.MEMBER_ID"
    				+ " AND CORPORATE_MEMBER.CORPORATE_ID = CORPORATE.CORPORATE_ID"
    				+ " AND CORPORATE_MEMBER.MEMBER_ID = " + memberID;
        	
        	ResultSet rs = DBUtil.dbExecuteQuery(sqlStmt);
            
            ObservableList<CorporateMember> list = this.getCorporateMemberList(rs);
            
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
	
	/**
	 * Inserts a member_id, corporate_id pair into the corporateMember table
	 * @pre corporateMember has a valid memberID	 
	 */
	public void insert(CorporateMember corporateMember) throws SQLException, ClassNotFoundException {
		String sqlStmt = new InsertSQLBuilder()
				.addTable("CORPORATE_MEMBER")
				.addFieldValue("MEMBER_ID", corporateMember.getMemberID())
				.addFieldValue("CORPORATE_ID", corporateMember.getCorporation().getCorporateID())
				.toString();		
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}  
	}
	
	/**
	 * Overloaded insert() that takes in member and corporation
	 * @pre member requires a valid memberID
	 * @pre corporation requires a valid corporateID
	 */
	public void insert(Member member, Corporate corporation) throws SQLException, ClassNotFoundException {
		String sqlStmt = new InsertSQLBuilder()
				.addTable("CORPORATE_MEMBER")
				.addFieldValue("MEMBER_ID", member.getMemberID())
				.addFieldValue("CORPORATE_ID", corporation.getCorporateID())
				.toString();
		
		
		System.out.println("memberID = " + member.getMemberID() + " corporateID = " + corporation.getCorporateID());
		
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	} 
	}
	
	/*
	 * Let memberDAO update the member fields by calling memberDAO.update()
	 * Handle the changes in corporateID in this function
	 */
	public void update(CorporateMember corporateMember) throws SQLException, ClassNotFoundException {
		String sqlStmt = new UpdateSQLBuilder()
				.addTable("CORPORATE_MEMBER")
				//.addFieldValue("CORPORATE_ID", corporateMember.getCorporateID())
				.where("CORPORATE_ID=" + corporateMember.getCorporation().getCorporateID())
				.and("MEMBER_ID=" + corporateMember.getMemberID())
				.toString();
		
		try {
			// update member-related information for the corporateMember
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.update(corporateMember);
			
			// update corporate related information
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}
	}
	
	public void delete(String condition) throws SQLException, ClassNotFoundException {
		try {
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("CORPORATE_MEMBER", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}
	}
	
	private ObservableList<CorporateMember> getCorporateMemberList (ResultSet rs) throws SQLException {
    	ObservableList<CorporateMember> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
    		try {
	    		CorporateMember corporateMember = new CorporateMember();  
	    		corporateMember.setMemberID(rs.getInt("MEMBER_ID"));
	    		corporateMember.setUserName(rs.getString("USERNAME"));
	    		corporateMember.setPassword(rs.getString("PASSWORD"));
	    		corporateMember.setFirstName(rs.getString("FIRST_NAME"));
	    		corporateMember.setLastName(rs.getString("LAST_NAME"));
	    		corporateMember.setEmail(rs.getString("EMAIL"));
	    		corporateMember.setPhoneNo(rs.getInt("PHONE"));
	    		corporateMember.setHomeAddress(rs.getString("HOME_ADDRESS"));    		
	    		corporateMember.setAccountExpiry(rs.getDate("ACCOUNT_EXPIRY"));
	    	
	    		list.add(corporateMember);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
