package dk.database.server.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connection;

    /**
     *
     * @return Connection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection connect() throws SQLException, ClassNotFoundException {

        Connection connection = null;
        Class.forName("org.postgresql.Driver");
        String URL = "jdbc:postgresql://localhost:5438/postgres";
        String USER = "dao";
        String PASSWORD = "dao";

        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}
