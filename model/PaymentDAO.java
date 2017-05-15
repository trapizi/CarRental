package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;
import util.SQLBuilder;

public class PaymentDAO implements TableDAO<Payment> {
    
    //return list of all Payments in Payment table
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