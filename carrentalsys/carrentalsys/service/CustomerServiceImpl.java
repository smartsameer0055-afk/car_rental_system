package com.carrental.service;

import java.sql.SQLException;
import java.util.List;

import com.carrental.exception.CustomerNotFoundException;
import com.carrental.model.Customer;
import com.carrental.repository.CustomerRepository;

public class CustomerServiceImpl
        implements CustomerService {

    private CustomerRepository repository;

    public CustomerServiceImpl(
            CustomerRepository repository) {

        this.repository = repository;
    }

    @Override
    public int addCustomer(Customer customer)
            throws SQLException {

        if (customer.getName() == null ||
            customer.getName().isBlank()) {

            throw new IllegalArgumentException(
                    "Customer name is required");
        }

        if (customer.getPhone() == null ||
            customer.getPhone().isBlank()) {

            throw new IllegalArgumentException(
                    "Phone number is required");
        }

        return repository.save(customer);
    }

    @Override
    public Customer getCustomer(int id)
            throws SQLException {

        Customer customer =
                repository.findById(id);

        if (customer == null) {

            throw new CustomerNotFoundException(
                    "Customer not found");
        }

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers()
            throws SQLException {

        return repository.findAll();
    }
}