package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Offer;
import util.*;

public class OfferDAO{
    
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
    
    public void init(){
            try {
                    String sqlStmt = SQLBuilder.createTableSQL("offer.txt");
                    DBUtil.dbInitTable(sqlStmt);
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
	
    public void insert(Seek seek) throws SQLException, ClassNotFoundExecption{
        
    }
	
//EDIT CAR OFFER (2)
//MEMBER UPDATE CAR OFFER INFORMATION FROM offerList   

    public void updateCarOffer(int offerID, int seats, double price, String carName, String carType, String brand, String model, String transmission, String fuelType) throws ClassNotFoundException, SQLException{
        //UPDATE query
        String updateStmt = 
                  " UPDATE database \n" 
                + " SET SEATS 			= '" + seats + "' \n "
                + " SET CAR_NAME 		= '" + carName + "' \n "
                + " SET CAR_TYPE 		= '" + carType + "' \n"
                + " SET BRAND 			= '" + brand + "' \n"
                + " SET MODEL 			= '" + model + "' \n"
                + " SET TRANSMISSION 	= '" + transmission + "' \n"
                + " SET FUEL_TYPE 		= '" + fuelType + "' \n"
                + " SET PRICE 			= '" + price + "' \n"
                + " WHERE CAR_ID 		= '" + offerID + "' \n";
        //Execute query
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Update failed" + e);
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
                        offer.setTransmission(rs.getString("TRANMISSION"));
                        offer.setFuelType(rs.getString("FUEL_TYPE"));
                        offer.setPrice(rs.getFloat("PRICE"));
                    
	    		list.add(offer);
    		} catch (SQLException e) {
            	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
    			throw e;
    		}
    	}
    	return list;
    }
    
    
	}