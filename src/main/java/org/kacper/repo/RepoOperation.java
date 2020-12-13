package org.kacper.repo;

import org.kacper.Customer;
import org.kacper.Rental;
import org.kacper.rental_items.Accessory;
import org.kacper.rental_items.AccessoryType;
import org.kacper.rental_items.Bike;
import org.kacper.rental_items.RentalItem;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
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

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Bike> getAllBikes() {
        String sql = "select * from bikes;";
        List<Bike> bikes = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);
            
            while(rs.next()) {
                bikes.add(mapRowToBike(rs));
            }
            
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
                accessories.add(mapRowToAccessory(rs));
            }

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
                customers.add(mapRowToCustomer(rs));
            }
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
                bike = mapRowToBike(rs);
            }

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
                accessory = mapRowToAccessory(rs);
            }

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
                customer = mapRowToCustomer(rs);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return customer;
    }
    
    
    
    public List<Rental> getAllRentals() {
        String sql = "select * from rentals r " +
                //"left join rental_bikes rb on r.id=rb.rental_id " +
                //"left join rental_accessories ra on r.id=ra.rental_id " +
                "join customers c on r.customer_id=c.id;";
                //"left join bikes bk on rb.bike_id=bk.id " +
                //"left join accessories ac on ra.accessory_id=ac.id;";

        List<Rental> rentals = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            while(rs.next()) {
                //rentals.add(mapRowToRental(rs));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return rentals;
    }
    
    private Bike mapRowToBike(ResultSet rs) throws SQLException {

        return new Bike.Builder(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price_per_hour"),
                rs.getString("make"),
                rs.getString("frame_size"),
                rs.getString("wheel_size"))
                .withSuspension(rs.getString("suspension"))
                .withFrameNumber(rs.getString("frame_number"))
                .build();
    }
    
    private Accessory mapRowToAccessory(ResultSet rs) throws SQLException {
         
        return new Accessory(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price_per_hour"),
                rs.getString("description"),
                AccessoryType.valueOf(rs.getString("type"))
        );
    }
    
    private Customer mapRowToCustomer(ResultSet rs) throws SQLException {
        
        return new Customer.CustomerBuilder(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("pesel"))
                .withPhone(rs.getString("phone"))
                .withEmail(rs.getString("email"))
                .withDiscount(rs.getInt("permanent_discount"))
                .build();
    }
    
    private Rental mapRowToRental(ResultSet rs) throws SQLException {

        List<RentalItem> rentalItems = getAllRentalItemsFromRentalId(rs.getInt("id"));
        
        Rental rental = new Rental(
                rs.getInt("id"),
                rs.getObject("timeFrom", LocalDateTime.class),
                rs.getObject("timeTo", LocalDateTime.class),
                getCustomerById(rs.getInt("customer_id")),
                
        )
    }
    
    private List<RentalItem> getAllRentalItemsFromRentalId(int id) throws SQLException {
        String sql = "select * from rental_bikes rb where rental_id = " + id + "" +
                " join bikes b on bk.bike_id = b.id;";
        
        
    }
}
