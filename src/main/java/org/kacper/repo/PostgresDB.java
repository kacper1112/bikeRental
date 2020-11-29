package org.kacper.repo;

import java.sql.Connection;

public class PostgresDB implements IDBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/bikerentaldb";
    private static final String user = "postgres";
    private static final String password = "postgres";
    
    
    @Override
    public Connection connect() {
        return DBStructureInitializer.initialize(url, user, password);
    }
}
