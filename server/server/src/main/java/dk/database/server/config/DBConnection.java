package dk.database.server.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connection;

    public Connection connect() throws SQLException, ClassNotFoundException {

        Connection connection = null;
        Class.forName("org.postgresql.Driver");
        String URL = "jdbc:postgresql://localhost:5432/dbd_exam";
        String USER = "postgres";
        String PASSWORD = "Tysker/3085";

        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}
