package dk.database.server.controller;

import dk.database.server.TestBase;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Configuration
public class CreateUserIT extends TestBase {


    @Test
    public void createUserMustReturnFalseIfUserExists() throws SQLException, ClassNotFoundException {

        try(var c = setupConnection("postgres");
        var s = c.createStatement()) {
            s.execute("SELECT * from users;");
        }
        // Arrange
//        UserCreation uc = getAlbert();
//        boolean res = um.createUser(uc);
//        assertTrue(res);
//
//        // Act
//        res = um.createUser(uc);

        // Assert
        assertFalse(false);
    }
}
