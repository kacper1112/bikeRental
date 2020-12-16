package org.kacper.repo;

import org.kacper.UserType;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class RepoUpdateOperation {
    private static RepoUpdateOperation instance;
    private final Connection connection;
    
    private RepoUpdateOperation() {
        connection = DBConnectionFactory.getDBConnection(DBType.POSTGRES);
    }
    
    public static RepoUpdateOperation getInstance() {
        if(instance == null) {
            instance = new RepoUpdateOperation();
        }
        
        return instance;
    }
    
    public void addEmployee(String name, String surname, String pesel, String password) {
        String sql = 
                "insert into employees (name, surname, pesel, password) values" +
                        "(?,?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, pesel);
            statement.setString(4, Integer.toString(password.hashCode()));
            statement.executeUpdate();
            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addAccessoryType(String type) {
        String sql = "insert into accessory_types (type) values (?);";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            statement.executeUpdate();
            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAccessory(String name, Double price, String desc, String type) {
        String sql = 
                "insert into accessories (name, price_per_hour, description, type)  values " +
                        "(?,?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, desc);
            statement.setString(4, type);
            statement.executeUpdate();
            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addBike(String name, Double price, String make, String frameSize,
                        String wheelSize, String suspension, String frameNumber) {
        String sql = "insert into bikes (name, price_per_hour, make, frame_size, wheel_size," +
                "suspension, frame_number) values (?,?,?,?,?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, make);
            statement.setString(4, frameSize);
            statement.setString(5, wheelSize);
            statement.setString(6, suspension);
            statement.setString(7, frameNumber);
            statement.executeUpdate();
            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
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
    
    private void addRentalBike(int rentalId, int bikeId) throws SQLException {
        
        String sql = 
                "insert into rental_bikes (rental_id, bike_id) values (?,?);";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, rentalId);
        statement.setInt(2, bikeId);
        statement.executeUpdate();
        statement.close();
    }

    private void addRentalAccessory(int rentalId, int accessoryId) throws SQLException {

        String sql =
                "insert into rental_accessories (rental_id, accessory_id) values (?,?);";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, rentalId);
        statement.setInt(2, accessoryId);
        statement.executeUpdate();
        statement.close();
    }

    public void addRental(LocalDateTime from, LocalDateTime to, int customerId, List<Integer> rentalBikes,
                          List<Integer> rentalAccessories) {
        String rentalSql =
                "insert into rentals (timeFrom, timeTo, customer_id) values (?,?,?);";// returning rental_id;";

        try {
            PreparedStatement rentalStatement = connection.prepareStatement(rentalSql, Statement.RETURN_GENERATED_KEYS);
            rentalStatement.setObject(1, from);
            rentalStatement.setObject(2, to);
            rentalStatement.setInt(3, customerId);
            rentalStatement.executeUpdate();
            var rs = rentalStatement.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            rentalStatement.close();

            for (Integer item : rentalBikes) {
                addRentalBike(key, item);
            }

            for (Integer item : rentalAccessories)
                addRentalAccessory(key, item);
            
            rentalStatement.close();

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    public UserType validateUser(String pesel, String password) {
        String userSql = "select * from customers where pesel like '" + pesel + "' and " +
                "password like '" + password.hashCode() + "';"; 
        
        String employeeSql = "select * from employees where pesel like '" + pesel + "' and " +
                "password like '" + password.hashCode() + "';";
        
        UserType result = UserType.NOUSER;
        
        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(userSql);
            
            if(rs.next()) {
                result = UserType.CUSTOMER;
            }
            statement.close();
            
            statement = connection.createStatement();
            rs = statement.executeQuery(employeeSql);
            
            if(rs.next()) {
                result = UserType.EMPLOYEE;
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}
