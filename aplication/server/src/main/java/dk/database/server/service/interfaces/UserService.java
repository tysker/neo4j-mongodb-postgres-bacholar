package dk.database.server.service.interfaces;

import dk.database.server.domain.UserCreation;
import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;

import java.sql.SQLException;
import java.util.Map;

public interface UserService {

    Map<Integer,User> getAllUsers() throws SQLException, ClassNotFoundException;
    User getUserById(int userId) throws SQLException, ClassNotFoundException;
    UserKeyword getUserKeyword(int userId) throws SQLException, ClassNotFoundException;
    User addUser(UserCreation user) throws SQLException, ClassNotFoundException;
}
