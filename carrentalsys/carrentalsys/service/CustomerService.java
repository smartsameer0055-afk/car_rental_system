package com.carrental.service;

import java.sql.SQLException;
import java.util.List;

import com.carrental.model.Customer;

public interface CustomerService {

    int addCustomer(Customer customer)
            throws SQLException;

    Customer getCustomer(int id)
            throws SQLException;

    List<Customer> getAllCustomers()
            throws SQLException;
}