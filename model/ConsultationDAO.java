/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;
import util.SQLBuilder;
import model.Consultation;
import util.InsertSQLBuilder;
import util.UpdateSQLBuilder;
/**
 *
 * @author elizabeth
 */
public class ConsultationDAO implements TableDAO<Consultation> {

    public ObservableList<Consultation> findAll() throws SQLException, ClassNotFoundException {
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "CONSULTATION", "")) ;
            
            ObservableList<Consultation> list = this.getConsultationList(rs);
            return list;
            } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
        	e.printStackTrace();
        } 
        return null;
    }


    public Consultation findById(int consultationNum) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "CONSULTATION", "CONSULTATION_NUM=" + consultationNum));
        
            ObservableList<Consultation> list = this.getConsultationList(rs);
            
            if (list.size() > 0) {
                return list.get(0);
            }
            
           
        } catch (SQLException | ClassNotFoundException e)  {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
            e.printStackTrace();
        } 
               
        return null;
    }


    public void insert(Consultation consultation) throws SQLException, ClassNotFoundException {
                    String sqlStmt = new InsertSQLBuilder()
                        .addTable("CONSULTATION")
 //                       .addFieldValue("CONSULTATION_NUM", consultation.getConsultationNum())
                        .addFieldValue("CONSULTATION_PRICE", consultation.getConsultationPrice())
                        .addFieldValue("CONSULTATION_TIME", consultation.getConsultationTime())
                        .addFieldValue("CONSULTATION_DATE", consultation.getConsultationDate())
                        .addFieldValue("CORPORATE_ID", consultation.getCorporateID())

                        .toString();
        System.out.println(sqlStmt);
        try {
        	DBUtil.dbExecuteUpdate(sqlStmt);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}  
    }
    
                        
    public void update(Consultation consultation) throws SQLException, ClassNotFoundException {
       String sqlStmt = new UpdateSQLBuilder()
                        .addTable("CONSULTATION")
 //                       .addFieldValue("CONSULTATION_NUM", consultation.getConsultationNum())
                        .addFieldValue("CONSULTATION_PRICE", consultation.getConsultationPrice())
                        .addFieldValue("CONSULTATION_TIME", consultation.getConsultationTime())
                        .addFieldValue("CONSULTATION_DATE", consultation.getConsultationDate())
                        .addFieldValue("CORPORATE_ID", consultation.getCorporateID())
               
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
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("CONSULTATION", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}
	}
    private ObservableList<Consultation> getConsultationList(ResultSet rs) throws SQLException {
    ObservableList<Consultation> list = FXCollections.observableArrayList();
    while (rs.next()) {
    		try {   
                    Consultation consultation = new Consultation();
  //                  consultation.setConsultationNum(rs.getInt("CONSULTATION_NUM"));
                    consultation.setConsultationPrice(rs.getFloat("CONSULTATION_PRICE"));
                    consultation.setConsultationTime(rs.getTime("CONSULTATION_TIME"));
                    consultation.setConsultationDate(rs.getDate("CONSULTATION_DATE"));
                    consultation.setCorporateID(rs.getInt("CORPORATE_ID"));
			
	    		list.add(consultation);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
}
