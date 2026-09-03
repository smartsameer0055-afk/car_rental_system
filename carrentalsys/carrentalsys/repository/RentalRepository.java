package com.carrental.repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.carrental.model.Rental;

public interface RentalRepository {

    int save(Rental rental) throws SQLException;

    Rental findActiveById(int rentalId)
            throws SQLException;

    List<Rental> findAll()
            throws SQLException;

    boolean completeRental(
            int rentalId,
            LocalDate returnDate,
            double totalAmount)
            throws SQLException;
}