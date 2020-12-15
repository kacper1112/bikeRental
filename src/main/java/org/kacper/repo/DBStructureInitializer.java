package org.kacper.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBStructureInitializer {

    private static final String createBikesTable =
            "create table if not exists bikes (" +
                    "id serial primary key not null," +
                    "name varchar(50) not null," +
                    "price_per_hour double precision null, " +
                    "make varchar(50) not null," +
                    "frame_size varchar(10) not null," +
                    "wheel_size varchar(10) not null," +
                    "suspension varchar(50)," +
                    "frame_number varchar(50));";

    private static final String createAccessoryTypesTable =
            "create table if not exists accessory_types (" +
                    "id serial primary key not null," +
                    "type varchar(30) unique not null);";

    private static final String createAccessoriesTable =
            "create table if not exists accessories (" +
                    "id serial primary key not null," +
                    "name varchar(50) not null," +
                    "price_per_hour double precision not null," +
                    "description varchar(200) not null," +
                    "type varchar(30) references accessory_types(type));";

    private static final String createCustomersTable =
            "create table if not exists customers (" +
                    "id serial primary key not null," +
                    "name varchar(40) not null," +
                    "surname varchar(40) not null," +
                    "pesel varchar(11) not null," +
                    "password varchar(40) not null," +
                    "phone varchar(15)," +
                    "email varchar(20)," +
                    "permanent_discount int);";

    private static final String createRentalsTable =
            "create table if not exists rentals (" +
                    "id serial primary key not null," +
                    "timeFrom timestamp not null," +
                    "timeTo timestamp not null," +
                    "customer_id int not null references customers(id));";

    private static final String createRentalBikesTable =
            "create table if not exists rental_bikes (" +
                    "rental_id int references rentals(id)," +
                    "bike_id int references bikes(id)," +
                    "primary key(rental_id, bike_id));";

    private static final String createRentalAccessoriesTable =
            "create table if not exists rental_accessories (" +
                    "rental_id int references rentals(id)," +
                    "accessory_id int references accessories(id)," +
                    "primary key(rental_id, accessory_id));";

    private static final String createEmployeesTable =
            "create table if not exists employees (" +
                    "id serial primary key not null," +
                    "name varchar(40) not null," +
                    "surname varchar(40) not null," +
                    "pesel varchar(40) not null," +
                    "password varchar(40) not null);";

    private static final String dropAllTables =
            "drop table if exists bikes cascade;" +
                    "drop table if exists accessories cascade;" +
                    "drop table if exists rental_bikes cascade;" +
                    "drop table if exists rental_accessories cascade;" +
                    "drop table if exists rentals cascade;" +
                    "drop table if exists customers cascade;" +
                    "drop table if exists employees cascade;" +
                    "drop table if exists accessory_types cascade;";

    public static Connection initialize(String url, String user, String password) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            //statement.executeUpdate(dropAllTables);

            statement.addBatch(createBikesTable);
            statement.addBatch(createAccessoryTypesTable);
            statement.addBatch(createAccessoriesTable);
            statement.addBatch(createCustomersTable);
            statement.addBatch(createRentalsTable);
            statement.addBatch(createRentalBikesTable);
            statement.addBatch(createRentalAccessoriesTable);
            statement.addBatch(createEmployeesTable);

            statement.executeBatch();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return connection;
    }
}
