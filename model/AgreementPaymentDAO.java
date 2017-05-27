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
            String sqlStmt = "SELECT AGREEMENT_PAYMENT.AGREEMENT_PAYMENT_ID, PAYMENT.*, AGREEMENT.SEEKER, AGREEMENT.OFFERER"
                + " FROM AGREEMENT_PAYMENT, AGREEMENT, PAYMENT"
        	+ " WHERE AGREEMENT_PAYMENT.AGREEMENT_ID = AGREEMENT.AGREEMENT_ID";
        	
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
    public AgreementPayment findById(int agreementPayment_id) throws SQLException, ClassNotFoundException {
	try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "AGREEMENT_PAYMENT", "AGREEMENT_PAYMENT_ID=" + agreementPayment_id));
            
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
    
    //insert() 
    //inserts an agreementPayment into the database
    public void insert(AgreementPayment agreementPayment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new InsertSQLBuilder()
            .addTable("AGREEMENT_PAYMENT")          
            .addFieldValue("PAYMENT_AMOUNT", agreementPayment.getPaymentAmount())
            .addFieldValue("PAYMENT_DATE", agreementPayment.getPaymentDate())
            .addFieldValue("PAYMENT_ACCOUNT", agreementPayment.getPaymentAccount())   
            .addFieldValue("PAYMENT_TYPE", agreementPayment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", agreementPayment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", agreementPayment.getAccountOwnerName())        
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
    //PaymentDAO update paymentfields by calling paymentDAO.update()
    public void update(AgreementPayment agreementPayment) throws SQLException, ClassNotFoundException {
        String sqlStmt = new UpdateSQLBuilder()
            .addTable("AGREEMENT_PAYMENT")      
            .addFieldValue("PAYMENT_AMOUNT", agreementPayment.getPaymentAmount())
            .addFieldValue("PAYMENT_DATE", agreementPayment.getPaymentDate())
            .addFieldValue("PAYMENT_ACCOUNT", agreementPayment.getPaymentAccount())   
            .addFieldValue("PAYMENT_TYPE", agreementPayment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", agreementPayment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", agreementPayment.getAccountOwnerName())         
            .where("AGREEMENT_PAYMENT_ID" + agreementPayment.getAgreementPayment_id())
                .toString();
		
	try {
            DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            throw e;
    	}
    }
	

    //delete() 
    //delete a agreementPayment from database given a condition
    public void delete(String condition) throws SQLException, ClassNotFoundException {
	try {
            DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("AGREEMENT_PAYMENT", condition));
    	} catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            throw e;
    	}
    }
	
    //Helper function (convert records from database into objects for java)
    private ObservableList<AgreementPayment> getAgreementPaymentList (ResultSet rs) throws SQLException {
    	ObservableList<AgreementPayment> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
            try {
                AgreementPayment agreementPayment = new AgreementPayment();
                agreementPayment.setAgreementPayment_id(rs.getInt("AGREEMENT_PAYMENT_ID"));
	    	agreementPayment.setPaymentAmount(rs.getFloat("PAYMENT_AMOUNT"));
	    	agreementPayment.setPaymentDate(rs.getDate("PAYMENT_DATE"));
	    	agreementPayment.setPaymentAccount(rs.getString("PAYMENT_ACCOUNT"));
	    	agreementPayment.setPaymentType(rs.getString("PAYMENT_TYPE"));
	    	agreementPayment.setAccountExpiry(rs.getDate("ACCOUNT_EXPIRY"));
	    	agreementPayment.setAccountOwnerName(rs.getString("ACCOUNT_OWNER_NAME"));

                list.add(agreementPayment);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
