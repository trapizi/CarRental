package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;
import util.InsertSQLBuilder;
import util.SQLBuilder;
import util.UpdateSQLBuilder;

public class AgreementPaymentDAO implements TableDAO<AgreementPayment> {
    
    //findAll()
    public ObservableList<AgreementPayment> findAll() throws SQLException, ClassNotFoundException {
        try {
            String sqlStmt = "SELECT PAYMENT.PAYMENT_ID, AGREEMENT.*"
        				+ " FROM AGREEMENT_PAYMENT, AGREEMENT, PAYMENT"
        				+ " WHERE AGREEMENT_PAYMENT.AGREEMENT_ID = AGREEMENT.AGREEMENT_ID"
        				+ " AND AGREEMENT_PAYMENT.PAYMENT_ID = PAYMENT.PAYMENT_ID";
        	
            ResultSet rs = DBUtil.dbExecuteQuery(sqlStmt);
            
            ObservableList<AgreementPayment> list = this.getAgreementPaymentList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }
    	return null;
    }
    
    //findById()
    public AgreementPayment findById(int agreementID) throws SQLException, ClassNotFoundException {
	try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "AGREEMENT_PAYMENT", "AGREEMENT_ID=" + agreementID));
            
            ObservableList<AgreementPayment> list = this.getAgreementPaymentList(rs);
            
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
    
    
    //insert agreement_id and payment_id into agreemenPayment table
    public void insert(AgreementPayment agreementPayment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new InsertSQLBuilder()
            .addTable("AGREEMENT_PAYMENT")
            .addFieldValue("AGREEMENT_ID", agreementPayment.getAgreementPaymentID())
            .addFieldValue("PAYMENT_ID", agreementPayment.getPaymentID())
		.toString();		
    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}  
    }
    
    
    //update()
    //PaymentDAO update paymentfields by calling paymentDAO.update()
    public void update(AgreementPayment agreementPayment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new UpdateSQLBuilder()
            .addTable("AGREEMENT_PAYMENT")
            .addFieldValue("AGREEMENT_ID", agreementPayment.getAgreementPaymentID())
            .where("AGREEMENT_ID=" + agreementPayment.getAgreementPaymentID())
            .and("PAYMENT_ID=" + agreementPayment.getPaymentID())
		.toString();
		
	try {
            // update payment-related information for the agreementPayment
            PaymentDAO paymentDAO = new PaymentDAO();
            paymentDAO.update(agreementPayment);
			
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
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("CORPORATE_MEMBER", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}
    }
	
    
    private ObservableList<AgreementPayment> getAgreementPaymentList (ResultSet rs) throws SQLException {
    	ObservableList<AgreementPayment> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
            try {
                AgreementPayment agreementPayment = new AgreementPayment();
                //agreementPayment.setAgreementPaymentID(rs.getString("AGREEMENT_PAYMENT_ID"));
                agreementPayment.setPaymentID(rs.getString("PAYMENT_ID"));
	    	agreementPayment.setAmount(rs.getDouble("AMOUNT"));
	    	agreementPayment.setDate(rs.getString("DATE"));
	    	agreementPayment.setPaymentAccount(rs.getString("PAYMENT_ACCOUNT"));
	    	agreementPayment.setPaymentType(rs.getString("PAYMENT_TYPE"));
	    	agreementPayment.setAccountExpiry(rs.getString("ACCOUNT_EXPIRY"));
	    	agreementPayment.setAccountOwnerName(rs.getString("ACCOUNT_OWNER_NAME"));
	    	agreementPayment.setPaymentMedia(rs.getString("PAYMENT_MEDIA"));  
	    	
	    		list.add(agreementPayment);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
