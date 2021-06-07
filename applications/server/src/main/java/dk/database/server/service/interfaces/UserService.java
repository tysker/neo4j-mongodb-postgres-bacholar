package dk.database.server.service.interfaces;

import dk.database.server.domain.UserCreation;
import dk.database.server.domain.UserKeywordCreation;
import dk.database.server.domain.UserStockCreation;
import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;
import dk.database.server.entities.UserStock;

import java.sql.SQLException;
import java.util.Map;

public interface UserService {

    Map<Integer,User> getAllUsers() throws SQLException, ClassNotFoundException;
    User getUserById(int userId) throws SQLException, ClassNotFoundException;
    UserKeyword getUserKeyword(int userId) throws SQLException, ClassNotFoundException;
    User addUser(UserCreation user) throws SQLException, ClassNotFoundException;
    boolean applyStock(UserStockCreation userStockCreation) throws SQLException, ClassNotFoundException;
    boolean applyKeyword(UserKeywordCreation userKeywordCreation) throws SQLException, ClassNotFoundException;
    boolean applyUserKeywordsStock(int userId, String keywordId, String stockId) throws SQLException, ClassNotFoundException;
}
