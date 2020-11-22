package org.kacper.repo;


public class DBConnectionFactory {
    public static DBConnection getDBConnection() {
        return new PostgresDB();
    }
}
