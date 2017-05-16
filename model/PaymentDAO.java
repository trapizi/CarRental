package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;
import util.InsertSQLBuilder;
import util.SQLBuilder;
import util.UpdateSQLBuilder;

public class PaymentDAO implements TableDAO<Payment> {
    
    //findAll() return list of all Payments in Payment table
    public ObservableList<Payment> findAll() throws SQLException, ClassNotFoundException {    			
        try {
            //SELECT query for Payment
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "PAYMENT", ""));
        
            ObservableList<Payment> list = this.getPaymentList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        }

        return null;
	}
    
    
    //findById()
    public Payment findById(int paymentID) throws SQLException, ClassNotFoundException {
	try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "PAYMENT", "PAYMENT_ID=" + paymentID));
            
            ObservableList<Payment> list = this.getPaymentList(rs);

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
    public void insert(Payment payment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new InsertSQLBuilder()
            .addTable("PAYMENT")
            .addFieldValue("AMOUNT", payment.getAmount())
            .addFieldValue("DATE", payment.getDate())
            .addFieldValue("PAYMENT_ACCOUNT", payment.getPaymentAccount())
            .addFieldValue("PAYMENT_TYPE", payment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", payment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", payment.getAccountOwnerName())
            .addFieldValue("PAYMENT_MEDIA", payment.getPaymentMedia())
		.toString();

    	try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}  
	}
    
    //update()
    public void update(Payment payment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new UpdateSQLBuilder()
            .addTable("PAYMENT")
            .addFieldValue("AMOUNT", payment.getAmount())
            .addFieldValue("DATE", payment.getDate())
            .addFieldValue("PAYMENT_ACCOUNT", payment.getPaymentAccount())
            .addFieldValue("PAYMENT_TYPE", payment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", payment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", payment.getAccountOwnerName())
            .addFieldValue("PAYMENT_MEDIA", payment.getPaymentMedia())
            .where("PAYMENT_ID=" + payment.getPaymentID())
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
            DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("PAYMENT", condition));
    	} catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            throw e;
    	}
    }
    
    
    private ObservableList<Payment> getPaymentList (ResultSet rs) throws SQLException {
        ObservableList<Payment> list = FXCollections.observableArrayList();
    	
        while (rs.next()) {
            try {
	    	Payment payment = new Payment();    		
	    	payment.setAmount(rs.getDouble("AMOUNT"));
	    	payment.setDate(rs.getString("DATE"));
	    	payment.setPaymentID(rs.getString("PAYMENT_ID"));
	    	payment.setPaymentAccount(rs.getString("PAYMENT_ACCOUNT"));
	    	payment.setPaymentType(rs.getString("PAYMENT_TYPE"));
	    	payment.setAccountExpiry(rs.getString("ACCOUNT_EXPIRY"));
	    	payment.setAccountOwnerName(rs.getString("ACCOUNT_OWNER_NAME"));
	    	payment.setPaymentMedia(rs.getString("PAYMENT_MEDIA"));    		
	    	
	    	list.add(payment);
            } catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
            }
    	}
    	return list;
    }
}