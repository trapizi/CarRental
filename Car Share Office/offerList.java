public class offerList {
    private int carID, seats;
    private String carType, brand, transmission, fuelType;

    public offerList(int carID, int seats, String carType, String brand, String transmission, String fuelType) {
        this.carID = carID;
        this.seats = seats;
        this.carType = carType;
        this.brand = brand;
        this.transmission = transmission;
        this.fuelType = fuelType;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
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
    
}