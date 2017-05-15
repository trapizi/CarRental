package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;
import util.InsertSQLBuilder;
import util.SQLBuilder;
import util.UpdateSQLBuilder;

public class CorporateMemberDAO implements TableDAO<CorporateMember> {
	public ObservableList<CorporateMember> findAll() throws SQLException, ClassNotFoundException {
        try {
        	String sqlStmt = "SELECT CORPORATE.*, MEMBER.*"
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
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "CORPORATE_MEMBER", "MEMBER_ID=" + memberID));
            
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
	
	/*
	 * Inserts a member_id, corporate_id pair into the corporateMember table	 
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
	
	/*
	 * Overloaded insert() that takes in member and corporation
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
				.addFieldValue("CORPORATE_ID", corporateMember.getCorporation().getCorporateID())
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
	
	public ObservableList<CorporateMember> getCorporateMemberList (ResultSet rs) throws SQLException {
    	ObservableList<CorporateMember> list = FXCollections.observableArrayList();

		/* Grab information about corporations from the resultSet */
		CorporateDAO corporateDAO = new CorporateDAO();
		ObservableList<Corporate> corporateList = FXCollections.observableArrayList();
		corporateList = corporateDAO.getCorporateList(rs);
    	
		/* Reset resultSet cursor to construct CorporateMembers */
		rs.beforeFirst();
		
		int corporateListIndex = 0;
		
    	while (rs.next()) {
    		try {    			
    			CorporateMember corporateMember = new CorporateMember();  
    			corporateMember.setCorporation(corporateList.get(corporateListIndex++));
	    		corporateMember.setMemberID(rs.getInt("MEMBER_ID"));
	    		corporateMember.setUserName(rs.getString("USERNAME"));
	    		corporateMember.setPassword(rs.getString("PASSWORD"));
	    		corporateMember.setFirstName(rs.getString("FIRST_NAME"));
	    		corporateMember.setLastName(rs.getString("LAST_NAME"));
	    		corporateMember.setEmail(rs.getString("EMAIL"));
	    		corporateMember.setPhoneNo(rs.getLong("PHONE"));
	    		corporateMember.setHomeAddress(rs.getString("HOME_ADDRESS"));    		
	    		corporateMember.setLastMatchDate(rs.getDate("LAST_MATCH_DATE"));
	    		corporateMember.setAccountExpiry(rs.getDate("ACCOUNT_EXPIRY"));
	    		corporateMember.setCommissionRate(rs.getFloat("COMMISSION_RATE"));
	    		corporateMember.setCreditCard(rs.getString("CREDIT_CARD"));
	    		corporateMember.setPaymentMedia(rs.getString("PAYMENT_MEDIA"));
	    	
	    		list.add(corporateMember);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
