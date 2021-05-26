package dk.database.server.facade;

import dk.database.server.entities.Keyword;
import dk.database.server.entities.Stock;
import dk.database.server.entities.User;
import dk.database.server.facade.interfaces.DataFacade;
import dk.database.server.service.UserServiceImpl;
import dk.database.server.service.interfaces.UserService;
;
import java.sql.SQLException;
import java.util.Map;

public class DataFacadeImpl implements DataFacade {

    private UserService userService;

    @Override
    public Map<Integer, User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(int userId) throws SQLException, ClassNotFoundException {
        return userService.getUserById(userId);
    }

    @Override
    public Map<Integer, Keyword> getAllKeywords() {
        return null;
    }

    @Override
    public Keyword getKeywordById(int keywordId) {
        return null;
    }

    @Override
    public Map<Integer, Stock> getAllStocks() {
        return null;
    }

    @Override
    public Stock getStockById(int stockId) {
        return null;
    }
}
