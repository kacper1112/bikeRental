package org.kacper.repo;

import org.kacper.UserType;
import org.kacper.repo.db_factory.DBConnectionFactory;
import org.kacper.repo.db_factory.DBType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Authenticator {
    private static Connection connection;
    
    public static UserType validateUser(String pesel, String password) {
        if(connection == null) {
            connection = DBConnectionFactory.getDBConnection();
        }
        
        String userSql = "select * from customers where pesel like '" + pesel + "' and " +
                "password like '" + password.hashCode() + "';";

        String employeeSql = "select * from employees where pesel like '" + pesel + "' and " +
                "password like '" + password.hashCode() + "';";

        UserType result = UserType.NOUSER;

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(userSql);

            if(rs.next()) {
                result = UserType.CUSTOMER;
            }
            statement.close();

            statement = connection.createStatement();
            rs = statement.executeQuery(employeeSql);

            if(rs.next()) {
                result = UserType.EMPLOYEE;
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
