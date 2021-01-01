package org.kacper.repo;

import org.kacper.Customer;
import org.kacper.repo.db_factory.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataMapper {
    private static CustomerDataMapper instance;
    public final Connection connection;

    private CustomerDataMapper() {
        connection = DBConnectionFactory.getDBConnection();
    }

    public static CustomerDataMapper getInstance() {
        if(instance == null) {
            instance = new CustomerDataMapper();
        }

        return instance;
    }

    public List<Customer> getAllCustomers() {
        String sql = "select * from customers;";

        List<Customer> customers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            while(rs.next()) {
                customers.add(RepoRowMapper.mapRowToCustomer(rs));
            }

            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return customers;
    }
    
    public Customer getCustomerById(int id) {
        String sql = "select * from customers where id=" + id + ";";
        Customer customer = null;

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            if(rs.next()) {
                customer = RepoRowMapper.mapRowToCustomer(rs);
            }

            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return customer;
    }

    public int getCustomerRentalCount(int id) {
        String sql = "select count(*) from rentals where customer_id=" + id + ";";
        int result = 0;

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            if(rs.next()) {
                result = rs.getInt(1);
            }

            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public void addCustomer(String name, String surname, String pesel, String password,
                            String phone, String email, Integer discount) {
        String sql =
                "insert into customers (name, surname, pesel, password, phone, email, permanent_discount) values" +
                        "(?,?,?,?,?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, pesel);
            statement.setString(4, Integer.toString(password.hashCode()));
            statement.setString(5, phone);
            statement.setString(6, email);
            statement.setObject(7, discount, Types.INTEGER);
            statement.executeUpdate();
            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
