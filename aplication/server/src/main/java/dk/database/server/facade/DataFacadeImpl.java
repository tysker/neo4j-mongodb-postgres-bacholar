package dk.database.server.facade;

import dk.database.server.entities.Keyword;
import dk.database.server.entities.Stock;
import dk.database.server.entities.User;
import dk.database.server.facade.interfaces.DataFacade;
import dk.database.server.service.interfaces.KeywordService;
import dk.database.server.service.interfaces.StockService;
import dk.database.server.service.interfaces.UserService;
;
import java.sql.SQLException;
import java.util.Map;

public class DataFacadeImpl implements DataFacade {

    private UserService userService;
    private KeywordService keywordService;
    private StockService stockService;

    @Override
    public Map<Integer, User> getAllUsers() throws SQLException, ClassNotFoundException {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(int userId) throws SQLException, ClassNotFoundException {
        return userService.getUserById(userId);
    }

    @Override
    public Map<Integer, Keyword> getAllKeywords() throws SQLException, ClassNotFoundException {
        return keywordService.getAllKeywords();
    }

    @Override
    public Keyword getKeywordById(int keywordId) throws SQLException, ClassNotFoundException {
        return keywordService.getKeywordById(keywordId);
    }

    @Override
    public Map<Integer, Stock> getAllStocks() throws SQLException, ClassNotFoundException {
        return stockService.getAllStocks();
    }

    @Override
    public Stock getStockById(int stockId) throws SQLException, ClassNotFoundException {
        return stockService.getStockById(stockId);
    }
}
