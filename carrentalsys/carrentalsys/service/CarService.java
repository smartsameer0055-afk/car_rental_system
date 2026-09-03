package com.carrental.service;

import java.sql.SQLException;
import java.util.List;

import com.carrental.model.Car;

public interface CarService {

    int addCar(Car car)
            throws SQLException;

    Car getCar(int id)
            throws SQLException;

    List<Car> getAllCars()
            throws SQLException;

    List<Car> getAvailableCars()
            throws SQLException;
}