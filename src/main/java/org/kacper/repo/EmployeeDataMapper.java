package org.kacper.repo;

import org.kacper.Employee;
import org.kacper.repo.db_factory.DBConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDataMapper {
    private static EmployeeDataMapper instance;
    public final Connection connection;

    private EmployeeDataMapper() {
        connection = DBConnectionFactory.getDBConnection();
    }

    public static EmployeeDataMapper getInstance() {
        if(instance == null) {
            instance = new EmployeeDataMapper();
        }

        return instance;
    }

    public Employee getEmployeeById(int id) {
        String sql = "select * from employees where id=" + id + ";";
        Employee employee = null;

        try {
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery(sql);

            if(rs.next()) {
                employee = RepoRowMapper.mapRowToEmployee(rs);
            }

            statement.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }

    public void addEmployee(String name, String surname, String pesel, String password) {
        String sql =
                "insert into employees (name, surname, pesel, password) values" +
                        "(?,?,?,?);";
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
