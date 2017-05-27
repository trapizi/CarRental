package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.InvalidInputException;
import util.InputValidator;

import java.sql.SQLException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

//carOffer could be a subclass of offerList?
public class Offer {//extends offerList{
	private IntegerProperty offerID, seats;
    private StringProperty carType, brand, model, transmission, fuelType;
    private DoubleProperty price;
    private LongProperty postcode;

    public Offer() {
        this.offerID = new SimpleIntegerProperty();
        this.seats = new SimpleIntegerProperty();
        this.carType = new SimpleStringProperty();
        this.brand = new SimpleStringProperty();
        this.model = new SimpleStringProperty();
        this.transmission = new SimpleStringProperty();
        this.fuelType = new SimpleStringProperty();
        this.postcode = new SimpleLongProperty();
        this.price = new SimpleDoubleProperty();
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
    		String fuelTypeTextField, String postcodeTextField, String priceTextField)
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
    	
    	} catch (Exception e) {
    		throw e;
    	}
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
    
    public double getPrice(){
    	return price.get();
    }
    
    public void setPrice(double price){
    	this.price.set(price);
    }
    
    public DoubleProperty priceProperty(){
        return price;
    }
    
}