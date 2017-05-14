import util.DBUtil;
import util.SQLBuilder;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Member;

public class OfferDAO{

//EDIT CAR OFFER (2)
//MEMBER UPDATE CAR OFFER INFORMATION FROM offerList   

    public void updateCarOffer(int carID, int seats, double price, String carName, String carType, String brand, String model, String transmission, String fuelType) throws ClassNotFoundException, SQLException{
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
                + " WHERE CAR_ID 		= '" + carID + "' \n";
        //Execute query
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Update failed" + e);
            throw e;
        	}
    	}
	}