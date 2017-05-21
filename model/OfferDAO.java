package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import util.DBUtil;
import util.SQLBuilder;
import util.InsertSQLBuilder;
import util.UpdateSQLBuilder;



public class OfferDAO implements TableDAO<Offer>{
    
    public ObservableList<Offer> findAll() throws SQLException, ClassNotFoundException {
        try {
                ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "OFFER", ""));
        
                ObservableList<Offer> list = this.getOfferList(rs);
                
                return list;
    } catch (SQLException | ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() +" failed.");
            e.printStackTrace();
    	}
        return null;
    }
    
    public Offer findById(int offerID) throws SQLException, ClassNotFoundException {
    	try {
    		ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "OFFER", "OFFER_ID=" + offerID  ));
    		
    		ObservableList<Offer> list = this.getOfferList(rs);
    	
    		if (list.size() >0){
    			return list.get(0);
    		}
    	} catch (SQLException | ClassNotFoundException e) {
    		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public Offer findByPostcode(long postcode) throws SQLException, ClassNotFoundException {
    	try{
    		ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "OFFER", "POSTCODE BETWEEN "+ (postcode-1) +"AND"+ (postcode+1)));
    	} catch (SQLException | ClassNotFoundException e) {
    		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public void insert(Offer offer) throws SQLException, ClassNotFoundException {
    	String sqlStmt = new InsertSQLBuilder()
    			.addTable("OFFER")
    			.addFieldValue("SEATS", offer.getSeats())
    			.addFieldValue("CAR_TYPE", offer.getCarType())
    			.addFieldValue("BRAND", offer.getBrand())
    			.addFieldValue("MODEL", offer.getModel())
    			.addFieldValue("TRANSMISSION", offer.getTransmission())
    			.addFieldValue("FUEL_TYPE", offer.getFuelType())
    			.addFieldValue("PRICE", offer.getPrice())
    			.addFieldValue("POSTCODE", offer.getPostcode())
    			.toString();
    	
    	
    	try {
    		DBUtil.dbExecuteUpdate(sqlStmt);
    	} catch (SQLException | ClassNotFoundException e) {
    		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;    		
    	}
    }
    
    public void update(Offer offer) throws SQLException, ClassNotFoundException {
    	String sqlStmt = new UpdateSQLBuilder()
    			.addTable("OFFER")
    			.addFieldValue("SEATS", offer.getSeats())
    			.addFieldValue("CAR_TYPE", offer.getCarType())
    			.addFieldValue("BRAND", offer.getBrand())
    			.addFieldValue("MODEL", offer.getModel())
    			.addFieldValue("TRANSMISSION", offer.getTransmission())
    			.addFieldValue("FUEL_TYPE", offer.getFuelType())
    			.addFieldValue("POSTCODE", offer.getPostcode())    			
    			.addFieldValue("PRICE", offer.getPrice())
    			.where("OFFER_ID=" + offer.getOfferID())
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
    		DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("OFFER", condition));
    	} catch (SQLException | ClassNotFoundException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    		throw e;
    	}    	
    }
 
    
    private ObservableList<Offer> getOfferList (ResultSet rs) throws SQLException {
	ObservableList<Offer> list = FXCollections.observableArrayList();
	
	while (rs.next()) {
		try {
    		Offer offer = new Offer();    		
    		offer.setOfferID(rs.getInt("OFFER_ID"));
                    offer.setSeats(rs.getInt("SEATS"));
                    offer.setCarType(rs.getString("CAR_TYPE"));
                    offer.setBrand(rs.getString("BRAND"));
                    offer.setModel(rs.getString("MODEL"));
                    offer.setTransmission(rs.getString("TRANSMISSION"));
                    offer.setFuelType(rs.getString("FUEL_TYPE"));
                    offer.setPostcode(rs.getLong("POSTCODE"));
                    offer.setPrice(rs.getFloat("PRICE"));
                list.add(offer);
			} catch (SQLException e) {
        	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
			throw e;
				}
			} return list;
    	}
    
    
}