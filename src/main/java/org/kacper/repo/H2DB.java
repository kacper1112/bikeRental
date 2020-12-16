package org.kacper.repo;

import java.sql.*;

public class H2DB implements IDBConnection {
    private static final String url = "jdbc:h2:mem:";
    private static final Connection connection = DBStructureInitializer.initialize(url, null, null);
    
    @Override
    public Connection connect() {
        return connection;
    }
}
