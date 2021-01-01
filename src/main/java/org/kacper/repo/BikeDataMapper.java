package org.kacper.repo;

import org.kacper.rental_items.Bike;
import org.kacper.repo.db_factory.DBConnectionFactory;
import org.kacper.repo.db_factory.DBType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BikeDataMapper {
    private static BikeDataMapper instance;
    public final Connection connection;

    private BikeDataMapper() {
        connection = DBConnectionFactory.getDBConnection();
    }

    public static BikeDataMapper getInstance() {
        if(instance == null) {
            instance = new BikeDataMapper();
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
}
