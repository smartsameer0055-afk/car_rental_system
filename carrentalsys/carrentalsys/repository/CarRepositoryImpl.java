package com.carrental.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carrental.database.DBConnection;
import com.carrental.model.Car;

public class CarRepositoryImpl implements CarRepository {

    @Override
    public int save(Car car) throws SQLException {

        String sql =
                "INSERT INTO cars " +
                "(brand, model, manufacture_year, price_per_day, available) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getManufactureYear());
            ps.setDouble(4, car.getPricePerDay());
            ps.setBoolean(5, true);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                car.setCarId(rs.getInt(1));
                return car.getCarId();
            }
        }

        return 0;
    }

    @Override
    public Car findById(int id) throws SQLException {

        String sql =
                "SELECT * FROM cars WHERE car_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Car(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("manufacture_year"),
                        rs.getDouble("price_per_day"),
                        rs.getBoolean("available")
                );
            }
        }

        return null;
    }

    @Override
    public List<Car> findAll() throws SQLException {

        List<Car> cars = new ArrayList<>();

        String sql =
                "SELECT * FROM cars ORDER BY car_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                cars.add(
                        new Car(
                                rs.getInt("car_id"),
                                rs.getString("brand"),
                                rs.getString("model"),
                                rs.getInt("manufacture_year"),
                                rs.getDouble("price_per_day"),
                                rs.getBoolean("available")
                        )
                );
            }
        }

        return cars;
    }

    @Override
    public List<Car> findAvailable() throws SQLException {

        List<Car> cars = new ArrayList<>();

        String sql =
                "SELECT * FROM cars WHERE available=true";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                cars.add(
                        new Car(
                                rs.getInt("car_id"),
                                rs.getString("brand"),
                                rs.getString("model"),
                                rs.getInt("manufacture_year"),
                                rs.getDouble("price_per_day"),
                                true
                        )
                );
            }
        }

        return cars;
    }

    @Override
    public boolean updateAvailability(
            int carId,
            boolean available) throws SQLException {

        String sql =
                "UPDATE cars SET available=? WHERE car_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, available);
            ps.setInt(2, carId);

            return ps.executeUpdate() > 0;
        }
    }
}