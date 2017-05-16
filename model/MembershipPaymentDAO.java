package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import util.DBUtil;
import util.InsertSQLBuilder;
import util.SQLBuilder;
import util.UpdateSQLBuilder;

public class MembershipPaymentDAO implements TableDAO<MembershipPayment> {
    
    //findAll()
    public ObservableList<MembershipPayment> findAll() throws SQLException, ClassNotFoundException {
        try {
            String sqlStmt = "SELECT PAYMENT.PAYMENT_ID, MEMBER.*"
        	+ " FROM MEMBERSHIP_PAYMENT, MEMBER, PAYMENT"
        	+ " WHERE MEMBERSHIP_PAYMENT.MEMBER_ID = MEMBER.MEMBER_ID"
        	+ " AND MEMBERSHIP_PAYMENT.PAYMENT_ID = PAYMENT.PAYMENT_ID";
        	
            ResultSet rs = DBUtil.dbExecuteQuery(sqlStmt);
            
            ObservableList<MembershipPayment> list = this.getMembershipPaymentList(rs);
            
        return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	return null;
    }
    
    //findById()
    public MembershipPayment findById(int membershipPaymentID) throws SQLException, ClassNotFoundException {
	try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "MEMBERSHIP_PAYMENT", "MEMBER_ID=" + membershipPaymentID));
            
            ObservableList<MembershipPayment> list = this.getMembershipPaymentList(rs);
            
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
    
    
    //insert membershipPayment_id and payment_id into membershipPaymentshipPayment table
    public void insert(MembershipPayment membershipPaymentshipPayment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new InsertSQLBuilder()
            .addTable("MEMBERSHIP_PAYMENT")
            .addFieldValue("MEMBER_ID", membershipPaymentshipPayment.getMembershipPaymentID())
            .addFieldValue("PAYMENT_ID", membershipPaymentshipPayment.getPaymentID())
		.toString();		
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}  
    }
    
    
    //update()
    //MemberDAO update membershipPayment fields by calling membershipPaymentDAO.update()
    public void update(MembershipPayment membershipPaymentshipPayment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new UpdateSQLBuilder()
            .addTable("MEMBERSHIP_PAYMENT")
            .addFieldValue("MEMBER_ID", membershipPaymentshipPayment.getMembershipPaymentID())
            .where("MEMBER_ID=" + membershipPaymentshipPayment.getMembershipPaymentID())
            .and("PAYMENT_ID=" + membershipPaymentshipPayment.getPaymentID())
		.toString();
		
	try {
            // update payment-related information for the membershipPaymentshipPayment
            PaymentDAO paymentDAO = new PaymentDAO();
            paymentDAO.update(membershipPaymentshipPayment);
			
            // update agreement-related information
            DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            throw e;
    	}
    }
	

    //delete()
    public void delete(String condition) throws SQLException, ClassNotFoundException {
	try {
            DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("MEMBERSHIP_PAYMENT", condition));
    	} catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            throw e;
    	}
    }
	
    
    
    private ObservableList<MembershipPayment> getMembershipPaymentList (ResultSet rs) throws SQLException {
    	ObservableList<MembershipPayment> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
            try {
                MembershipPayment membershipPayment = new MembershipPayment();
                membershipPayment.setMembershipPaymentID(rs.getString("MEMBERSHIP_PAYMENT_ID"));
                membershipPayment.setStatus(rs.getString("STATUS"));
                membershipPayment.setLastMatch(rs.getString("LAST_MATCH"));
                membershipPayment.setNextExpiry(rs.getString("NEXT_EXPIRY"));
                membershipPayment.setDurationToExpiry(rs.getInt("DURATION_TO_EXPIRY"));
                membershipPayment.setRefundFlag(rs.getInt("REFUND_FLAG"));
                //membershipPayment.setMemberID(rs.getInt("MEMBER_ID"));
	    	//membershipPayment.setPaymentID(rs.getString("PAYMENT_ID"));
                //membershipPayment.setUserName(rs.getString("USERNAME"));
	    	//membershipPayment.setPassword(rs.getString("PASSWORD"));
	    	//membershipPayment.setFirstName(rs.getString("FIRST_NAME"));
	    	//membershipPayment.setLastName(rs.getString("LAST_NAME"));
	    	//membershipPayment.setEmail(rs.getString("EMAIL"));
	    	//membershipPayment.setPhoneNo(rs.getLong("PHONE"));
	    	//membershipPayment.setHomeAddress(rs.getString("HOME_ADDRESS"));    		
	    	//membershipPayment.setLastMatchDate(rs.getDate("LAST_MATCH_DATE"));
	    	//membershipPayment.setAccountExpiry(rs.getDate("ACCOUNT_EXPIRY"));
	    	//membershipPayment.setCommissionRate(rs.getFloat("COMMISSION_RATE"));
	    	//membershipPayment.setCreditCard(rs.getString("CREDIT_CARD"));
	    	//membershipPayment.setPaymentMedia(rs.getString("PAYMENT_MEDIA"));
                //membershipPayment.setAmount(rs.getDouble("AMOUNT"));
	    	//membershipPayment.setDate(rs.getString("DATE"));    	
	    	//membershipPayment.setPaymentAccount(rs.getString("PAYMENT_ACCOUNT"));
	    	//membershipPayment.setPaymentType(rs.getString("PAYMENT_TYPE"));
	    	//membershipPayment.setAccountExpiry(rs.getString("ACCOUNT_EXPIRY"));
	    	//membershipPayment.setAccountOwnerName(rs.getString("ACCOUNT_OWNER_NAME"));
	    	//membershipPayment.setPaymentMedia(rs.getString("PAYMENT_MEDIA"));    		            
	    	
	    	list.add(membershipPayment);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
