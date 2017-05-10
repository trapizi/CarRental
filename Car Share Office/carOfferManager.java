/*
I will edit the code to have it compatible with database methods 
and more functions to come 10-05-17
*/

import DatabaseUtil.DBUtil;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//EDIT CAR OFFER (1)
//STAFF AND MEMBER UPDATE OFFER QUOTA AND PRICE    

public carOfferManager{
    public void update(int quota, int offerID, String price){
        //UPDATE query
        String updateStmt = 
                  " UPDATE database \n" 
                + " SET QUOTA = '" + quota + "' \n "
                + " SET PRICE = '" + price + "' \n "
                + " WHERE OFFER_ID = '" + offerID + "' \n";
        //Execute query
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Update failed" + e);
            throw e;
        }
    }
    
//EDIT CAR OFFER (2)
//MEMBER UPDATE CAR OFFER INFORMATION FROM offerList   

    public void updateCarOffer(int offerID, int seats, String carName, String carType, String brand, String transmission, String fuelType){
        //UPDATE query
        String updateStmt = 
                  " UPDATE database \n" 
                + " SET SEATS = '" + seats + "' \n "
                + " SET CAR_NAME = '" + carName + "' \n "
                + " SET CAR_TYPE = '" + carType + "' \n"
                + " SET BRAND = '" + brand+ "' \n"
                + " SET TRANSMISSION = '" + transmission + "' \n"
                + " SET FUEL_TYPE = '" + fuelType + "' \n"
                + " WHERE OFFER_ID = '" + offerID + "' \n";
        //Execute query
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Update failed" + e);
            throw e;
        }
    }
}    