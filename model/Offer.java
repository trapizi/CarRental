package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

//carOffer could be a subclass of offerList?
public class Offer {//extends offerList{
	private IntegerProperty offerID, seats;
    private StringProperty carType, brand, model, transmission, fuelType;
    private FloatProperty price;
    private LongProperty postcode;

    public Offer() {
        this.offerID = new SimpleIntegerProperty();
        this.seats = new SimpleIntegerProperty();
        this.carType = new SimpleStringProperty();
        this.brand = new SimpleStringProperty();
        this.model = new SimpleStringProperty();
        this.transmission = new SimpleStringProperty();
        this.fuelType = new SimpleStringProperty();
        this.price = new SimpleFloatProperty();
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
    
    public String getLocation(){
    	return location.get();
    }
    
    public void setLocation(String location){
    	this.location.set(location);
    }
    
    public StringProperty locationProperty(){
    	return location;
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
    
    public void setPrice(float price){
    	this.price.set(price);
    }
    
    public FloatProperty priceProperty(){
        return price;
    }
    
}