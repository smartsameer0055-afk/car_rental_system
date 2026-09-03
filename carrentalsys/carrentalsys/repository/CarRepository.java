package com.carrental.repository;

import java.sql.SQLException;
import java.util.List;

import com.carrental.model.Car;

public interface CarRepository {

    int save(Car car) throws SQLException;

    Car findById(int id) throws SQLException;

    List<Car> findAll() throws SQLException;

    List<Car> findAvailable() throws SQLException;

    boolean updateAvailability(
            int carId,
            boolean available) throws SQLException;
}