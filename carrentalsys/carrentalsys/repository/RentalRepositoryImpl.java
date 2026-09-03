package com.carrental.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.carrental.database.DBConnection;
import com.carrental.model.Rental;

public class RentalRepositoryImpl
        implements RentalRepository {

    @Override
    public int save(Rental rental)
            throws SQLException {

        String sql =
                "INSERT INTO rentals " +
                "(car_id, customer_id, rental_date, " +
                "total_amount, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, rental.getCarId());

            ps.setInt(2, rental.getCustomerId());

            ps.setDate(
                    3,
                    Date.valueOf(
                            rental.getRentalDate()));

            ps.setDouble(
                    4,
                    rental.getTotalAmount());

            ps.setString(
                    5,
                    rental.getStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                rental.setRentalId(
                        rs.getInt(1));

                return rental.getRentalId();
            }
        }

        return 0;
    }

    @Override
    public Rental findActiveById(
            int rentalId)
            throws SQLException {

        String sql =
                "SELECT * FROM rentals " +
                "WHERE rental_id=? AND status='RENTED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, rentalId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Date returnDate =
                        rs.getDate("return_date");

                return new Rental(
                        rs.getInt("rental_id"),
                        rs.getInt("car_id"),
                        rs.getInt("customer_id"),
                        rs.getDate("rental_date")
                                .toLocalDate(),
                        returnDate == null
                                ? null
                                : returnDate.toLocalDate(),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );
            }
        }

        return null;
    }

    @Override
    public List<Rental> findAll()
            throws SQLException {

        List<Rental> rentals =
                new ArrayList<>();

        String sql =
                "SELECT * FROM rentals " +
                "ORDER BY rental_id DESC";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                Date returnDate =
                        rs.getDate("return_date");

                rentals.add(
                        new Rental(
                                rs.getInt("rental_id"),
                                rs.getInt("car_id"),
                                rs.getInt("customer_id"),
                                rs.getDate("rental_date")
                                        .toLocalDate(),
                                returnDate == null
                                        ? null
                                        : returnDate.toLocalDate(),
                                rs.getDouble("total_amount"),
                                rs.getString("status")
                        )
                );
            }
        }

        return rentals;
    }

    @Override
    public boolean completeRental(
            int rentalId,
            LocalDate returnDate,
            double totalAmount)
            throws SQLException {

        String sql =
                "UPDATE rentals SET " +
                "return_date=?, " +
                "total_amount=?, " +
                "status='RETURNED' " +
                "WHERE rental_id=? " +
                "AND status='RENTED'";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setDate(
                    1,
                    Date.valueOf(returnDate));

            ps.setDouble(
                    2,
                    totalAmount);

            ps.setInt(3, rentalId);

            return ps.executeUpdate() > 0;
        }
    }
}