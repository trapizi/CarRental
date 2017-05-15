package model;
//carOffer could be a subclass of offerList?
public class Offer {//extends offerList{
	private int offerID, seats;
    private String carType, brand, model, transmission, fuelType;
    private double price;

    public Offer(int carID, int seats, double price, String carType, String brand, String model, String transmission, String fuelType) {
        this.offerID = carID;
        this.seats = seats;
        this.carType = carType;
        this.brand = brand;
        this.model = model;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.price = price;
    }
    
    
    public int getCarID() {
        return offerID;
    }

    public void setCarID(int carID) {
        this.offerID = offerID;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getModel(){
    	return model;
    }
    
    public void setModel(String model){
    	this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }  
    
    public double getPrice(){
    	return price;
    }
    
    public void setPrice(double price){
    	this.price = price;
    }
    
}