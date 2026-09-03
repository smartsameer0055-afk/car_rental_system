package com.carrental.repository;

import java.sql.SQLException;
import java.util.List;

import com.carrental.model.Customer;

public interface CustomerRepository {

    int save(Customer customer) throws SQLException;

    Customer findById(int id) throws SQLException;

    List<Customer> findAll() throws SQLException;
}