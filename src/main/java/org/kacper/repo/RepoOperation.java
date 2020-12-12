package org.kacper.repo;

import org.kacper.rental_items.Bike;
import org.kacper.rental_items.RentalItem;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class RepoOperation {
    private static RepoOperation instance;
    private final Connection connection;
    
    private RepoOperation() {
        connection = DBConnectionFactory.getDBConnection(DBType.POSTGRES);
    }
    
    public static RepoOperation getInstance() {
        if(instance == null) {
            instance = new RepoOperation();
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
            PreparedStatement rentalStatement = connection.prepareStatement(rentalSql);
            rentalStatement.setObject(1, from);
            rentalStatement.setObject(2, to);
            rentalStatement.setInt(3, customerId);
            rentalStatement.executeUpdate();
//            int key = rentalStatement.getGeneratedKeys().getInt(1);
//            rentalStatement.close();
//
//            for (Integer item : rentalBikes) {
//                addRentalBike(key, item);
//            }
//
//            for (Integer item : rentalAccessories)
//                addRentalAccessory(key, item);
//            
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
//    public void test() {
//        
//        try {
//            Statement statement = connection.createStatement();
//            var rs = statement.executeQuery("select * from accessory_types;");
//            
//            if(rs.next()) {
//                System.out.println(rs.getInt(1));
//                System.out.println(rs.getString(2));
//            }
//            
//            rs = statement.executeQuery("select * from accessories;");
//            
//            if(rs.next()) {
//                System.out.println(rs.getInt(1));
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getString(3));
//                System.out.println(rs.getString(4));
//            }
//
//
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
}
