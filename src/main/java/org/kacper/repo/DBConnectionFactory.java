package org.kacper.repo;


import java.sql.Connection;

public class DBConnectionFactory {
    public static Connection getDBConnection(DBType type) {
        return type.getConstructor().get().connect();
    }
}
