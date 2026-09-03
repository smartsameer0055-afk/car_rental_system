package com.carrental.service;

import java.sql.SQLException;
import java.util.List;

import com.carrental.exception.CarNotFoundException;
import com.carrental.model.Car;
import com.carrental.repository.CarRepository;
import com.carrental.repository.CustomerRepository;
import com.carrental.repository.RentalRepository;

public class CarServiceImpl
        implements CarService {

    private CarRepository repository;

    public CarServiceImpl(
            CarRepository repository) {

        this.repository = repository;
    }

    public CarServiceImpl(CarRepository carRepository, CustomerRepository customerRepository,
			RentalRepository rentalRepository) {
	
	}

	@Override
    public int addCar(Car car)
            throws SQLException {

        if (car.getBrand() == null ||
            car.getBrand().isBlank()) {

            throw new IllegalArgumentException(
                    "Brand is required");
        }

        if (car.getModel() == null ||
            car.getModel().isBlank()) {

            throw new IllegalArgumentException(
                    "Model is required");
        }

        if (car.getPricePerDay() <= 0) {

            throw new IllegalArgumentException(
                    "Price must be greater than zero");
        }

        return repository.save(car);
    }

    @Override
    public Car getCar(int id)
            throws SQLException {

        Car car = repository.findById(id);

        if (car == null) {

            throw new CarNotFoundException(
                    "Car not found");
        }

        return car;
    }

    @Override
    public List<Car> getAllCars()
            throws SQLException {

        return repository.findAll();
    }

    @Override
    public List<Car> getAvailableCars()
            throws SQLException {

        return repository.findAvailable();
    }
}