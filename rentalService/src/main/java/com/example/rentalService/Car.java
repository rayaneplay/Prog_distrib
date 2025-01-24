package com.example.rentalService;

public class Car {
    private String make;
    private String model;
    private int year;
    private String plateNumber;
    private boolean rented;
    
        // Constructor
        public Car(String make, String model, int year, String plateNumber, boolean rented) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.plateNumber = plateNumber;
            this.rented = rented;
    }

    // Getters and Setters
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}

