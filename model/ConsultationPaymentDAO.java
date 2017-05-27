package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;
import util.InsertSQLBuilder;
import util.SQLBuilder;
import util.UpdateSQLBuilder;

public class ConsultationPaymentDAO implements TableDAO<ConsultationPayment> {
    
    //findAll()
    public ObservableList<ConsultationPayment> findAll() throws SQLException, ClassNotFoundException {
        try {
            String sqlStmt = "SELECT CONSULTATION_PAYMENT.CONSULTATION_PAYMENT_ID, PAYMENT.*, CONSULTATION.*"
                + " FROM CONSULTATION_PAYMENT, PAYMENT, CONSULTATION"
        	+ " WHERE CONSULTATION_PAYMENT.CONSULTATION_ID = CONSULTATION.CONSULTATION_ID";
        	
            ResultSet rs = DBUtil.dbExecuteQuery(sqlStmt);
            
            ObservableList<ConsultationPayment> list = this.getConsultationPaymentList(rs);
            
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            e.printStackTrace();
        }
    	return null;
    }


    //findById()
    public ConsultationPayment findById(int consultationPayment_id) throws SQLException, ClassNotFoundException {
	try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "CONSULTATION_PAYMENT", "CONSULTATION_PAYMENT_ID=" + consultationPayment_id));
            
            ObservableList<ConsultationPayment> list = this.getConsultationPaymentList(rs);
            
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
    //inserts an consultatonPayment into the database
    public void insert(ConsultationPayment consultationPayment) throws SQLException, ClassNotFoundException {
	String sqlStmt = new InsertSQLBuilder()
            .addTable("CONSULTATION_PAYMENT")
            .addFieldValue("PAYMENT_AMOUNT", consultationPayment.getPaymentAmount())
            .addFieldValue("PAYMENT_DATE", consultationPayment.getPaymentDate())
            .addFieldValue("PAYMENT_ACCOUNT", consultationPayment.getPaymentAccount())   
            .addFieldValue("PAYMENT_TYPE", consultationPayment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", consultationPayment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", consultationPayment.getAccountOwnerName())          
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
    public void update(ConsultationPayment consultationPayment) throws SQLException, ClassNotFoundException {
        String sqlStmt = new UpdateSQLBuilder()
            .addTable("CONSULTATION_PAYMENT")      
            .addFieldValue("PAYMENT_AMOUNT", consultationPayment.getPaymentAmount())
            .addFieldValue("PAYMENT_DATE", consultationPayment.getPaymentDate())
            .addFieldValue("PAYMENT_ACCOUNT", consultationPayment.getPaymentAccount())   
            .addFieldValue("PAYMENT_TYPE", consultationPayment.getPaymentType())
            .addFieldValue("ACCOUNT_EXPIRY", consultationPayment.getAccountExpiry())
            .addFieldValue("ACCOUNT_OWNER_NAME", consultationPayment.getAccountOwnerName())           
            .where("CONSULTATION_PAYMENT_ID" + consultationPayment.getConsultationPayment_id())
                .toString();
		
	try {
            DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            throw e;
    	}
    }
 

    //delete() 
    //delete a consultationPayment from database given a condition
    public void delete(String condition) throws SQLException, ClassNotFoundException {
	try {
            DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("CONSULTATION_PAYMENT", condition));
    	} catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            throw e;
    	}
    } 
	
    //Helper function (convert records from database into objects for java)
    private ObservableList<ConsultationPayment> getConsultationPaymentList (ResultSet rs) throws SQLException {
    	ObservableList<ConsultationPayment> list = FXCollections.observableArrayList();
    	
    	while (rs.next()) {
            try {
                ConsultationPayment consultationPayment = new ConsultationPayment();
                consultationPayment.setConsultationPayment_id(rs.getInt("CONSULTATION_PAYMENT_ID"));
	    	consultationPayment.setPaymentAmount(rs.getFloat("PAYMENT_AMOUNT"));
	    	consultationPayment.setPaymentDate(rs.getDate("PAYMENT_DATE"));
	    	consultationPayment.setPaymentAccount(rs.getString("PAYMENT_ACCOUNT"));
	    	consultationPayment.setPaymentType(rs.getString("PAYMENT_TYPE"));
	    	consultationPayment.setAccountExpiry(rs.getDate("ACCOUNT_EXPIRY"));
	    	consultationPayment.setAccountOwnerName(rs.getString("ACCOUNT_OWNER_NAME"));

                list.add(consultationPayment);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}

