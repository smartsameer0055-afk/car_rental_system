package com.carrental.service;

import java.sql.SQLException;
import java.util.List;

import com.carrental.model.Rental;

public interface RentalService {

    int rentCar(
            int carId,
            int customerId,
            int days)
            throws SQLException;

    double returnCar(
            int rentalId)
            throws SQLException;

    List<Rental> getAllRentals()
            throws SQLException;
}