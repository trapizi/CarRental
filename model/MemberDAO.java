package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;
import util.InsertSQLBuilder;
import util.SQLBuilder;
import util.UpdateSQLBuilder;

public class MemberDAO implements TableDAO<Member> {
	public ObservableList<Member> findAll() throws SQLException, ClassNotFoundException {
        try {
        	/* Query database for member */
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "MEMBER", ""));
            
            ObservableList<Member> list = this.getMemberList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	
    	return null;
	}
	
	public Member findById(int member_id) throws SQLException, ClassNotFoundException {
		try {
        	/* Query database for staff */
        	ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "MEMBER", "MEMBER_ID=" + member_id));
            
            ObservableList<Member> list = this.getMemberList(rs);
            
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
	
	public void insert(Member member) throws SQLException, ClassNotFoundException {
		String sqlStmt = new InsertSQLBuilder("MEMBER")
    			.addFieldValue("USERNAME", member.getUserName())
    			.addFieldValue("PASSWORD", member.getPassword())
    			.addFieldValue("FIRST_NAME", member.getFirstName())
    			.addFieldValue("LAST_NAME", member.getLastName())
    			.addFieldValue("EMAIL", member.getEmail())
    			.addFieldValue("PHONE", member.getPhoneNo())
    			.addFieldValue("HOME_ADDRESS", member.getHomeAddress())
    			.addFieldValue("LAST_MATCH_DATE", member.getLastMatchDate())
    			.addFieldValue("ACCOUNT_EXPIRY", member.getAccountExpiry())
    			.addFieldValue("COMMISSION_RATE", member.getCommissionRate())
    			.addFieldValue("CREDIT_CARD", member.getCreditCard())
    			.addFieldValue("PAYMENT_MEDIA", member.getPaymentMedia())
    			.toString();
    			
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}  
	}
	
	public void update(Member member) throws SQLException, ClassNotFoundException {
		String sqlStmt = new UpdateSQLBuilder("MEMBER")
				.addFieldValue("USERNAME", member.getUserName())
				.addFieldValue("PASSWORD", member.getPassword())
    			.addFieldValue("FIRST_NAME", member.getFirstName())
    			.addFieldValue("LAST_NAME", member.getLastName())
    			.addFieldValue("EMAIL", member.getEmail())
    			.addFieldValue("PHONE", member.getPhoneNo())
    			.addFieldValue("HOME_ADDRESS", member.getHomeAddress())
    			.addFieldValue("LAST_MATCH_DATE", member.getLastMatchDate())
    			.addFieldValue("ACCOUNT_EXPIRY", member.getAccountExpiry())
    			.addFieldValue("COMMISSION_RATE", member.getCommissionRate())
    			.addFieldValue("CREDIT_CARD", member.getCreditCard())
    			.addFieldValue("PAYMENT_MEDIA", member.getPaymentMedia())
    			.where("MEMBER_ID=" + member.getMember_id())
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
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("MEMBER", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}
	}
	
    /*
     * Helper function
     * Converts member records from database into member objects for java to play with
     */
    private ObservableList<Member> getMemberList (ResultSet rs) throws SQLException {
    	ObservableList<Member> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
    		try {
	    		Member member = new Member();    		
	    		member.setMember_id(rs.getInt("MEMBER_ID"));
	    		member.setUserName(rs.getString("USERNAME"));
	    		member.setPassword(rs.getString("PASSWORD"));
	    		member.setFirstName(rs.getString("FIRST_NAME"));
	    		member.setLastName(rs.getString("LAST_NAME"));
	    		member.setEmail(rs.getString("EMAIL"));
	    		member.setPhoneNo(rs.getLong("PHONE"));
	    		member.setHomeAddress(rs.getString("HOME_ADDRESS"));    		
	    		member.setLastMatchDate(rs.getDate("LAST_MATCH_DATE"));
	    		member.setAccountExpiry(rs.getDate("ACCOUNT_EXPIRY"));
	    		member.setCommissionRate(rs.getFloat("COMMISSION_RATE"));
	    		member.setCreditCard(rs.getString("CREDIT_CARD"));
	    		member.setPaymentMedia(rs.getString("PAYMENT_MEDIA"));
	    	
	    		list.add(member);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
