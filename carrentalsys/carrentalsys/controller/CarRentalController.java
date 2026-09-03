package com.carrental.controller;

import java.sql.SQLException;
import java.util.List;

import com.carrental.model.Car;
import com.carrental.model.Customer;
import com.carrental.model.Rental;
import com.carrental.service.CarService;
import com.carrental.service.CustomerService;
import com.carrental.service.RentalService;

public class CarRentalController {

    private CarService carService;

    private CustomerService customerService;

    private RentalService rentalService;

    public CarRentalController(
            CarService carService,
            CustomerService customerService,
            RentalService rentalService) {

        this.carService = carService;
        this.customerService = customerService;
        this.rentalService = rentalService;
    }

    public int addCar(Car car)
            throws SQLException {

        return carService.addCar(car);
    }

    public List<Car> getCars()
            throws SQLException {

        return carService.getAllCars();
    }

    public int addCustomer(
            Customer customer)
            throws SQLException {

        return customerService
                .addCustomer(customer);
    }

    public List<Customer> getCustomers()
            throws SQLException {

        return customerService
                .getAllCustomers();
    }

    public int rentCar(
            int carId,
            int customerId,
            int days)
            throws SQLException {

        return rentalService
                .rentCar(
                        carId,
                        customerId,
                        days
                );
    }

    public double returnCar(
            int rentalId)
            throws SQLException {

        return rentalService
                .returnCar(rentalId);
    }

    public List<Rental> getRentals()
            throws SQLException {

        return rentalService
                .getAllRentals();
    }
}