package org.kacper.repo;

import org.kacper.rental_items.Accessory;
import org.kacper.repo.db_factory.DBConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccessoryDataMapper {
    private static AccessoryDataMapper instance;
    public final Connection connection;

    private AccessoryDataMapper() {
        connection = DBConnectionFactory.getDBConnection();
    }

    public static AccessoryDataMapper getInstance() {
        if(instance == null) {
            instance = new AccessoryDataMapper();
        }

        return instance;
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
}
