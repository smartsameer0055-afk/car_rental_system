package com.carrental.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.carrental.exception.CarNotFoundException;
import com.carrental.exception.CustomerNotFoundException;
import com.carrental.exception.RentalNotFoundException;
import com.carrental.model.Car;
import com.carrental.model.Rental;
import com.carrental.repository.CarRepository;
import com.carrental.repository.CustomerRepository;
import com.carrental.repository.RentalRepository;

public class RentalServiceImpl
        implements RentalService {

    private RentalRepository rentalRepository;

    private CarRepository carRepository;

    private CustomerRepository customerRepository;

    public RentalServiceImpl(
            RentalRepository rentalRepository,
            CarRepository carRepository,
            CustomerRepository customerRepository) {

        this.rentalRepository =
                rentalRepository;

        this.carRepository =
                carRepository;

        this.customerRepository =
                customerRepository;
    }

    @Override
    public int rentCar(
            int carId,
            int customerId,
            int days)
            throws SQLException {

        if (days <= 0) {

            throw new IllegalArgumentException(
                    "Days must be greater than zero");
        }

        Car car =
                carRepository.findById(carId);

        if (car == null) {

            throw new CarNotFoundException(
                    "Car not found");
        }

        if (!car.isAvailable()) {

            throw new IllegalStateException(
                    "Car is already rented");
        }

        if (customerRepository
                .findById(customerId) == null) {

            throw new CustomerNotFoundException(
                    "Customer not found");
        }

        double amount =
                car.getPricePerDay() * days;

        Rental rental =
                new Rental(
                        carId,
                        customerId,
                        LocalDate.now(),
                        amount,
                        "RENTED"
                );

        int rentalId =
                rentalRepository.save(rental);

        carRepository.updateAvailability(
                carId,
                false
        );

        return rentalId;
    }

    @Override
    public double returnCar(
            int rentalId)
            throws SQLException {

        Rental rental =
                rentalRepository
                        .findActiveById(rentalId);

        if (rental == null) {

            throw new RentalNotFoundException(
                    "Active rental not found");
        }

        Car car =
                carRepository
                        .findById(rental.getCarId());

        if (car == null) {

            throw new CarNotFoundException(
                    "Car not found");
        }

        LocalDate returnDate =
                LocalDate.now();

        long days =
                ChronoUnit.DAYS.between(
                        rental.getRentalDate(),
                        returnDate
                );

        if (days < 1) {
            days = 1;
        }

        double amount =
                car.getPricePerDay() * days;

        rentalRepository.completeRental(
                rentalId,
                returnDate,
                amount
        );

        carRepository.updateAvailability(
                rental.getCarId(),
                true
        );

        return amount;
    }

    @Override
    public List<Rental> getAllRentals()
            throws SQLException {

        return rentalRepository.findAll();
    }
}