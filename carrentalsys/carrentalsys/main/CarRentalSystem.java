package com.carrental.main;

import javax.swing.SwingUtilities;

import com.carrental.controller.CarRentalController;
import com.carrental.repository.CarRepository;
import com.carrental.repository.CarRepositoryImpl;
import com.carrental.repository.CustomerRepository;
import com.carrental.repository.CustomerRepositoryImpl;
import com.carrental.repository.RentalRepository;
import com.carrental.repository.RentalRepositoryImpl;
import com.carrental.service.CarService;
import com.carrental.service.CarServiceImpl;
import com.carrental.service.CustomerService;
import com.carrental.service.CustomerServiceImpl;
import com.carrental.service.RentalService;
import com.carrental.service.RentalServiceImpl;
import com.carrental.ui.LoginFrame;

public class CarRentalSystem {

    public static void main(String[] args) {

       
        CarRepository carRepository =
                new CarRepositoryImpl();

        CustomerRepository customerRepository =
                new CustomerRepositoryImpl();

        RentalRepository rentalRepository =
                new RentalRepositoryImpl();


        CarService carService =
                new CarServiceImpl(carRepository);

        CustomerService customerService =
                new CustomerServiceImpl(customerRepository);

        RentalService rentalService =
                new RentalServiceImpl(
                        rentalRepository,
                        carRepository,
                        customerRepository
                );


        CarRentalController controller =
                new CarRentalController(
                        carService,
                        customerService,
                        rentalService
                );

        SwingUtilities.invokeLater(() -> {

            LoginFrame loginFrame =
                    new LoginFrame(controller);

            loginFrame.setVisible(true);

        });
    }
}