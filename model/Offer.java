package model;

/**
 * @author Xuan Huy Ngo (z5076470)
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import util.InvalidInputException;
import util.InputValidator;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

//carOffer could be a subclass of offerList?
public class Offer {//extends offerList{
	private IntegerProperty offerID, memberID, seats;
    private StringProperty carType, brand, model, transmission, fuelType;
    private FloatProperty price;
    private LongProperty postcode;
    private SimpleObjectProperty<Date> driveDay;

    public Offer() {
    	this.memberID = new SimpleIntegerProperty();
        this.offerID = new SimpleIntegerProperty();
        this.seats = new SimpleIntegerProperty();
        this.carType = new SimpleStringProperty();
        this.brand = new SimpleStringProperty();
        this.model = new SimpleStringProperty();
        this.transmission = new SimpleStringProperty();
        this.fuelType = new SimpleStringProperty();
        this.postcode = new SimpleLongProperty();
        this.price = new SimpleFloatProperty();
        this.driveDay = new SimpleObjectProperty<Date>();
    }
    
    public Offer(String brand, String model, String carType, int seats, String transmission, String fuelType,
    		Long postcode, Integer price) {
    	this.brand.set(brand);
    	this.model.set(model);
    	this.carType.set(carType);
    	this.seats.set(seats);
    	this.transmission.set(transmission);
    	this.postcode.set(postcode);
    	this.price.set(price);
    }

    @Override
    public String toString(){
    	return "OfferID: " + this.getOfferID();
    }
    
    public static void validateInput(String brandTextField, String modelTextField, String carTypeTextField, String seatsTextField, String transmissionTextField,
    		String fuelTypeTextField, String postcodeTextField, String priceTextField, String driveDayTextField)
    		throws InvalidInputException, SQLException, ClassNotFoundException {
    	try {
    		InputValidator.validateBrand(brandTextField);
    		InputValidator.validateModel(modelTextField);
    		InputValidator.validateCarType(carTypeTextField);
    		InputValidator.validateSeats(seatsTextField);
    		InputValidator.validateTransmission(transmissionTextField);
    		InputValidator.validateFuelType(fuelTypeTextField);
    		InputValidator.validatePostcode(postcodeTextField);
    		InputValidator.validatePrice(priceTextField);
    		InputValidator.validateDate(driveDayTextField);
    	} catch (Exception e) {
    		throw e;
    	}
    }
    
    public int getMemberID() {
    	return memberID.get();
    }
    
    public void setMemberID(int memberID) {
    	this.memberID.set(memberID);
    }
    
    public IntegerProperty memberIDProperty(){
    	return memberID;
    }
    
    public int getOfferID() {
        return offerID.get();
    }

    public void setOfferID(int offerID) {
        this.offerID.set(offerID);
    }
    
    public IntegerProperty offerIDProperty(){
        return offerID;
    }

    public int getSeats() {
        return seats.get();
    }

    public void setSeats(int seats) {
        this.seats.set(seats);
    }
    
    public IntegerProperty seatsProperty(){
        return seats;
    }

    public String getCarType() {
        return carType.get();
    }

    public void setCarType(String carType) {
        this.carType.set(carType);
    }

    public StringProperty carTypeProperty(){
        return carType;
    }
    
    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }
    
    public StringProperty brandProperty(){
        return brand;
    }
    
    public String getModel(){
    	return model.get();
    }
    
    public void setModel(String model){
    	this.model.set(model);
    }

    public StringProperty modelProperty(){
    return model;
    }
    
    public long getPostcode(){
    	return postcode.get();
    }
    
    public void setPostcode(long postcode){
    	this.postcode.set(postcode);
    }
    
    public LongProperty postcodeProperty(){
    	return postcode;
    }
    
    public String getTransmission() {
        return transmission.get();
    }

    public void setTransmission(String transmission) {
        this.transmission.set(transmission);
    }

    public StringProperty transmissionProperty(){
        return transmission;
    }
    
    public String getFuelType() {
        return fuelType.get();
    }

    public void setFuelType(String fuelType) {
        this.fuelType.set(fuelType);
    }  
    
    public StringProperty fuelTypeProperty(){
        return fuelType;
    }
    
    public float getPrice(){
    	return price.get();
    }
    
    public void setPrice(float price){
    	this.price.set(price);
    }
    
    public FloatProperty priceProperty(){
        return price;
    }
    
    public Date getDriveDay(){
    	return driveDay.get();
    }
    
    public void setDriveDay(Date driveDay) {
    	this.driveDay.set(driveDay);
    }
    
    public void setDriveDay(String date) {
    	try{
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		java.util.Date temp = format.parse(date);
    		this.setDriveDay(new Date(temp.getTime()));
    	} catch (ParseException e) {
    	}
    }
    
    public ObjectProperty<Date> driveDayProperty() {
    	return driveDay;
    }
}