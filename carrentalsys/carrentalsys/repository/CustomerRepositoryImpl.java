package com.carrental.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carrental.database.DBConnection;
import com.carrental.model.Customer;

public class CustomerRepositoryImpl
        implements CustomerRepository {

    @Override
    public int save(Customer customer) throws SQLException {

        String sql =
                "INSERT INTO customers(name, phone, email) " +
                "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                customer.setCustomerId(rs.getInt(1));

                return customer.getCustomerId();
            }
        }

        return 0;
    }

    @Override
    public Customer findById(int id) throws SQLException {

        String sql =
                "SELECT * FROM customers WHERE customer_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
            }
        }

        return null;
    }

    @Override
    public List<Customer> findAll() throws SQLException {

        List<Customer> customers = new ArrayList<>();

        String sql =
                "SELECT * FROM customers ORDER BY customer_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                customers.add(
                        new Customer(
                                rs.getInt("customer_id"),
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("email")
                        )
                );
            }
        }

        return customers;
    }
}