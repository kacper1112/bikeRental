package repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RepoInitializer {
    private static final String url = "jdbc:postgresql://localhost:5432/bikerentaldb";
    private static final String user = "postgres";
    private static final String password = "postgres";
    
    private static final String createBikesTable = 
            "create table bikes (" +
                    "id int primary key," +
                    "name varchar(50)," +
                    "frame_number varchar(50)," +
                    "frame_size varchar(10));";

    public static void main(String[] args) throws SQLException {
        RepoInitializer repoInitializer = new RepoInitializer();
        repoInitializer.createTables();
    }
    
    private void createTables() throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(createBikesTable);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
