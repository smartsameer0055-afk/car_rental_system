package com.carrental.model;

public class Car {

    private int carId;
    private String brand;
    private String model;
    private int manufactureYear;
    private double pricePerDay;
    private boolean available;

    public Car() {
    }

    public Car(String brand, String model,
               int number, double pricePerDay) {

        this.brand = brand;
        this.model = model;
        this.manufactureYear = number;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    public Car(int carId, String brand, String model,
               int manufactureYear, double pricePerDay,
               boolean available) {

        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}