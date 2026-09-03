package com.carrental.model;

import java.time.LocalDate;

public class Rental {

    private int rentalId;
    private int carId;
    private int customerId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private double totalAmount;
    private String status;

    public Rental() {
    }

    public Rental(int carId, int customerId,
                   LocalDate rentalDate,
                   double totalAmount,
                   String status) {

        this.carId = carId;
        this.customerId = customerId;
        this.rentalDate = rentalDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Rental(int rentalId, int carId,
                  int customerId,
                  LocalDate rentalDate,
                  LocalDate returnDate,
                  double totalAmount,
                  String status) {

        this.rentalId = rentalId;
        this.carId = carId;
        this.customerId = customerId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}