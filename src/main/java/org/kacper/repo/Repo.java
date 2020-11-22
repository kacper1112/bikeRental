package org.kacper.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Repo {
    private static Repo instance;
    private final Connection connection;
    
    private Repo() {
        connection = DBConnectionFactory.getDBConnection().connect();
    }
    
    public static Repo getInstance() {
        if(instance == null) {
            instance = new Repo();
        }
        
        return instance;
    }
    
    public void addEmployee(String name, String surname, String pesel, String password) {
        String sql = 
                "insert into employees (name, surname, pesel, password) values" +
                        "(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, pesel);
            statement.setString(4, Integer.toString(password.hashCode()));
            statement.executeUpdate();
            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
