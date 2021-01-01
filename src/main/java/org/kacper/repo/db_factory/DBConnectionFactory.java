package org.kacper.repo.db_factory;


import java.sql.Connection;

public class DBConnectionFactory {
    private static final DBType type = DBType.POSTGRES;
    
    
    public static Connection getDBConnection() {
        return type.getConstructor().get().connect();
    }
}
