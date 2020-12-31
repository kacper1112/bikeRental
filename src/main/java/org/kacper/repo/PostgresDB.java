package org.kacper.repo;

import java.sql.Connection;

public class PostgresDB implements IDBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/bikerentaldb";
    private static final String user = "postgres";
    private static final String password = "postgres";
    private static final Connection connection = DBStructureInitializer.initialize(url, user, password);;
    
    @Override
    public Connection connect() {
        return connection;
    }
}
