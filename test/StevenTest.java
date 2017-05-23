/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import model.OfferDAO;
import model.Offer;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import util.DBTablePrinter;
import util.DBUtil;;


public class StevenTest {
    public static void initMyTables() throws Exception {
		try {
			DBUtil.dbInitAllTables();
		} catch (Exception e) {
			throw e;
		}        
    }
    
    public static void testOfferTable() throws SQLException, ClassNotFoundException {
        OfferDAO offerDAO = new OfferDAO();
        
        Offer offer1 = new Offer();
        offer1.setBrand("Toyota");
        offer1.setModel("Corolla");
        offer1.setCarType("Sedan");
        offer1.setSeats(5);
        offer1.setTransmission("Auto");
        offer1.setFuelType("Diesel");
        offer1.setPostcode(2000);
        offer1.setPrice(99.99);
        
        Offer offer2 = new Offer();
        offer2.setBrand("Tesla");
        offer2.setModel("3");
        offer2.setCarType("SUV");
        offer2.setSeats(7);
        offer2.setTransmission("Auto");
        offer2.setFuelType("Electric");
        offer2.setPostcode(2002);
        offer2.setPrice(9.99);
        
        Offer offer3 = new Offer();
        offer3.setBrand("Ferrari");
        offer3.setModel("911");
        offer3.setCarType("Hatchback");
        offer3.setSeats(2);
        offer3.setTransmission("Manual");
        offer3.setFuelType("Water");
        offer3.setPostcode(2000);
        offer3.setPrice(19.99);
                
        
        try {
            offerDAO.insert(offer1);
            offerDAO.insert(offer2);
            offerDAO.insert(offer3);
            
            final String url = "jdbc:derby:DBforDEMO;create=true";
            DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "OFFER");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
