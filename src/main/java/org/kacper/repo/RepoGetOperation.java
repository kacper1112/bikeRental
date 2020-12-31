package org.kacper.repo;

import org.kacper.Customer;
import org.kacper.Employee;
import org.kacper.Rental;
import org.kacper.rental_items.Accessory;
import org.kacper.rental_items.Bike;
import org.kacper.rental_items.RentalItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepoGetOperation {
    private static RepoGetOperation instance;
    private final Connection connection;

    private RepoGetOperation() {
        connection = DBConnectionFactory.getDBConnection(DBType.POSTGRES);
    }

    public static RepoGetOperation getInstance() {
        if(instance == null) {
            instance = new RepoGetOperation();
        }

        return instance;
    }

    public List<Bike> getAllBikes() {
        String sql = "select * from bikes;";
        List<Bike> bikes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            while(rs.next()) {
                bikes.add(RepoRowMapper.mapRowToBike(rs));
            }

            statement.close();

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return bikes;
    }

    public List<Accessory> getAllAccessories() {
        String sql = "select * from accessories;";
        List<Accessory> accessories = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            while(rs.next()) {
                accessories.add(RepoRowMapper.mapRowToAccessory(rs));
            }

            statement.close();

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return accessories;
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

    public Bike getBikeById(int id) {
        String sql = "select * from bikes where id=" + id + ";";
        Bike bike = null;

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            if(rs.next()) {
                bike = RepoRowMapper.mapRowToBike(rs);
            }

            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return bike;
    }

    public Accessory getAccessoryById(int id) {
        String sql = "select * from accesories where id=" + id + ";";
        Accessory accessory = null;

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            if(rs.next()) {
                accessory = RepoRowMapper.mapRowToAccessory(rs);
            }

            statement.close();

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return accessory;
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

    public Employee getEmployeeById(int id) {
        String sql = "select * from employees where id=" + id + ";";
        Employee employee = null;

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            if(rs.next()) {
                employee = RepoRowMapper.mapRowToEmployee(rs);
            }

            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
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
    
    public Rental getRentalById(int id) {
        String sql = "select * from rentals r " +
                "join customers c on r.customer_id = c.id " +
                "where r.id = " + id + ";";
        
        Rental result = null;
        
        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);
            
            if(rs.next()) {
                result = RepoRowMapper.mapRowToRental(rs);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }

    public List<Rental> getAllRentals() {
        String sql = "select * from rentals r " +
                "join customers c on r.customer_id=c.id;";


        List<Rental> rentals = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            while(rs.next()) {
                rentals.add(RepoRowMapper.mapRowToRental(rs));
            }

            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return rentals;
    }

    List<RentalItem> getAllRentalItemsFromRentalId(int id) throws SQLException {
        String bikeSql = "select b.* from rental_bikes rb " +
                " join bikes b on rb.bike_id = b.id" +
                " where rb.rental_id = " + id + ";";

        String accessorySql = "select a.* from rental_accessories ra" +
                " join accessories a on ra.accessory_id = a.id " +
                " where ra.rental_id = " + id + ";";

        List<RentalItem> rentalItems = new ArrayList<>();

        try {
            Statement bikeStatement = connection.createStatement();
            var rs = bikeStatement.executeQuery(bikeSql);

            while(rs.next()) {
                rentalItems.add(RepoRowMapper.mapRowToBike(rs));
            }

            bikeStatement.close();

            Statement accessoryStatement = connection.createStatement();
            rs = accessoryStatement.executeQuery(accessorySql);

            while(rs.next()) {
                rentalItems.add(RepoRowMapper.mapRowToAccessory(rs));
            }

            accessoryStatement.close();

        } catch(SQLException ex) {
            ex.printStackTrace();
        }


        return rentalItems;
    }
}
