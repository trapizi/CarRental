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
    
    //findAll() 
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
    public Payment findById(int payment_id) throws SQLException, ClassNotFoundException {
	try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "PAYMENT", "PAYMENT_ID=" + payment_id));
            
            ObservableList<Payment> list = this.getPaymentList(rs);

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
    
    
    //insert()
    public void insert(Payment payment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new InsertSQLBuilder()
            .addTable("PAYMENT")
            .addFieldValue("PAYMENT_ID", payment.getPayment_id())
            .addFieldValue("PAYMENT_AMOUNT", payment.getPaymentAmount())
            .addFieldValue("PAYMENT_DATE", payment.getPaymentDate())
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
            .addFieldValue("PAYMENT_AMOUNT", payment.getPaymentAmount())
            .addFieldValue("PAYMENT_DATE", payment.getPaymentDate())
            .addFieldValue("PAYMENT_ACCOUNT", payment.getPaymentAccount())
            .addFieldValue("PAYMENT_TYPE", payment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", payment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", payment.getAccountOwnerName())
            .addFieldValue("PAYMENT_MEDIA", payment.getPaymentMedia())
            .where("PAYMENT_ID=" + payment.getPayment_id())
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
	    	payment.setPayment_id(rs.getInt("PAYMENT_ID"));
                payment.setPaymentAmount(rs.getDouble("PAYMENT_AMOUNT"));
	    	payment.setPaymentDate(rs.getDate("PAYMENT_DATE"));
	    	payment.setPaymentAccount(rs.getString("PAYMENT_ACCOUNT"));
	    	payment.setPaymentType(rs.getString("PAYMENT_TYPE"));
	    	payment.setAccountExpiry(rs.getDate("ACCOUNT_EXPIRY"));
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