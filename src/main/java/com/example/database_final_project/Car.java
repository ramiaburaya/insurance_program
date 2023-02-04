package com.example.database_final_project;

public class Car {

    private String carId;
    private String clientID;
    private String model;
    private String color;
    private double  engineSize;
    private String licenseEnd;
    private String modelDate;
    private double price;
    public Car() {
    }

    public Car(String carId, String clientID, String model, String color, String licenseEnd, String modelDate, double price) {
        this.carId = carId;
        this.clientID = clientID;
        this.model = model;
        this.color = color;
        this.licenseEnd = licenseEnd;
        this.modelDate = modelDate;
        this.price = price;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getClientID() {
        return clientID;
    }
    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicenseEnd() {
        return licenseEnd;
    }

    public void setLicenseEnd(String licenseEnd) {
        this.licenseEnd = licenseEnd;
    }

    public String getModelDate() {
        return modelDate;
    }

    public void setModelDate(String modelDate) {
        this.modelDate = modelDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
