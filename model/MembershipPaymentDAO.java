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
            String sqlStmt = "SELECT MEMBERSHIP_PAYMENT.MEMBERSHIP_PAYMENT_ID, PAYMENT.*, MEMBER.*"
        	+ " FROM MEMBERSHIP_PAYMENT, PAYMENT, MEMBERSHIP"
        	+ " WHERE MEMBERSHIP_PAYMENT.MEMBER_ID = MEMBER.MEMBER_ID";
        	
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
    public MembershipPayment findById(int membershipPayment_id) throws SQLException, ClassNotFoundException {
	try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "MEMBERSHIP_PAYMENT", "MEMBERSHIP_PAYMENT_ID=" + membershipPayment_id));
            
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
    
    //insert()
    //insert membershipPayment_id into the database
    public void insert(MembershipPayment membershipPayment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new InsertSQLBuilder()
            .addTable("MEMBERSHIP_PAYMENT")
            .addFieldValue("PAYMENT_AMOUNT", membershipPayment.getPaymentAmount())
            .addFieldValue("PAYMENT_DATE", membershipPayment.getPaymentDate())
            .addFieldValue("PAYMENT_ACCOUNT", membershipPayment.getPaymentAccount())   
            .addFieldValue("PAYMENT_TYPE", membershipPayment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", membershipPayment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", membershipPayment.getAccountOwnerName())       
            .addFieldValue("REFUND_FLAG", membershipPayment.getRefundFlag())
            .toString();
        
        System.out.println(sqlStmt);
    	
        try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}  
    }
    
    
    //update()
    public void update(MembershipPayment membershipPayment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new UpdateSQLBuilder()
            .addTable("MEMBERSHIP_PAYMENT")
            .addFieldValue("PAYMENT_AMOUNT", membershipPayment.getPaymentAmount())
            .addFieldValue("PAYMENT_DATE", membershipPayment.getPaymentDate())
            .addFieldValue("PAYMENT_ACCOUNT", membershipPayment.getPaymentAccount())   
            .addFieldValue("PAYMENT_TYPE", membershipPayment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", membershipPayment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", membershipPayment.getAccountOwnerName())        
            .addFieldValue("REFUND_FLAG", membershipPayment.getRefundFlag())
            .where("MEMBERSHIP_PAYMENT_ID" + membershipPayment.getMembershipPayment_id())
                .toString();
		
	try {
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
	
    //Helper function (convert records from database into objects for java
    private ObservableList<MembershipPayment> getMembershipPaymentList (ResultSet rs) throws SQLException {
    	ObservableList<MembershipPayment> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
            try {
                MembershipPayment membershipPayment = new MembershipPayment();
                membershipPayment.setMembershipPayment_id(rs.getInt("MEMBERSHIP_PAYMENT_ID"));
	    	membershipPayment.setPaymentAmount(rs.getFloat("PAYMENT_AMOUNT"));
	    	membershipPayment.setPaymentDate(rs.getDate("PAYMENT_DATE"));
	    	membershipPayment.setPaymentAccount(rs.getString("PAYMENT_ACCOUNT"));
	    	membershipPayment.setPaymentType(rs.getString("PAYMENT_TYPE"));
	    	membershipPayment.setAccountExpiry(rs.getDate("ACCOUNT_EXPIRY"));
	    	membershipPayment.setAccountOwnerName(rs.getString("ACCOUNT_OWNER_NAME"));
                membershipPayment.setRefundFlag(rs.getBoolean("REFUND_FLAG"));

	    	list.add(membershipPayment);
            } catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
