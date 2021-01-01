package org.kacper.repo;

import org.kacper.Rental;
import org.kacper.rental_items.RentalItem;
import org.kacper.repo.db_factory.DBConnectionFactory;
import org.kacper.repo.db_factory.DBType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalDataMapper {
    private static RentalDataMapper instance;
    public final Connection connection;
    
    private RentalDataMapper() {
        connection = DBConnectionFactory.getDBConnection();
    }
    
    public static RentalDataMapper getInstance() {
        if(instance == null) {
            instance = new RentalDataMapper();
        }
        
        return instance;
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

    public List<RentalItem> getAllRentalItemsFromRentalId(int id) throws SQLException {
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
}
