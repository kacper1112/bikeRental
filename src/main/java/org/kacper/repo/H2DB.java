package org.kacper.repo;

import java.sql.*;

public class H2DB implements IDBConnection {
    private static final String url = "jdbc:h2:mem:";
    
    @Override
    public Connection connect() {
        return DBStructureInitializer.initialize(url, null, null);
    }
}
