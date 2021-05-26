package dk.database.server.service.interfaces;

import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {

    Map<Integer,User> getAllUsers();
    User getUserById(int userId) throws SQLException, ClassNotFoundException;
    UserKeyword getUserKeyword() throws SQLException, ClassNotFoundException;
}
