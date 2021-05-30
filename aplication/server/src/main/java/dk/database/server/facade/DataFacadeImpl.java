package dk.database.server.facade;

import dk.database.server.domain.*;
import dk.database.server.entities.Keyword;
import dk.database.server.entities.Stock;
import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;
import dk.database.server.facade.interfaces.DataFacade;
import dk.database.server.service.KeywordServiceImpl;
import dk.database.server.service.StockServiceImpl;
import dk.database.server.service.UserServiceImpl;
import dk.database.server.service.interfaces.KeywordService;
import dk.database.server.service.interfaces.StockService;
import dk.database.server.service.interfaces.UserService;

import java.sql.SQLException;
import java.util.Map;

public class DataFacadeImpl implements DataFacade {

    private final UserService userService = new UserServiceImpl();
    private final KeywordService keywordService = new KeywordServiceImpl();
    private final StockService stockService = new StockServiceImpl();

    @Override
    public Map<Integer, User> getAllUsers() throws SQLException, ClassNotFoundException {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(int userId) throws SQLException, ClassNotFoundException {
        return userService.getUserById(userId);
    }

    @Override
    public UserKeyword getUserKeyword(int userId) throws SQLException, ClassNotFoundException {
        return userService.getUserKeyword(userId);
    }

    @Override
    public User addUser(UserCreation userCreation) throws SQLException, ClassNotFoundException {
        return userService.addUser(userCreation);
    }

    @Override
    public boolean applyStock(UserStockCreation userStockCreation) throws SQLException, ClassNotFoundException {
        return userService.applyStock(userStockCreation);
    }

    @Override
    public boolean applyKeyword(UserKeywordCreation userKeywordCreation) throws SQLException, ClassNotFoundException {
        return userService.applyKeyword(userKeywordCreation);
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
    public Keyword addKeyword(KeywordCreation keywordCreation) throws SQLException, ClassNotFoundException {
        return keywordService.addKeyword(keywordCreation);
    }

    @Override
    public Map<Integer, Stock> getAllStocks() throws SQLException, ClassNotFoundException {
        return stockService.getAllStocks();
    }

    @Override
    public Stock getStockById(int stockId) throws SQLException, ClassNotFoundException {
        return stockService.getStockById(stockId);
    }

    @Override
    public Stock addStock(StockCreation stockCreation) throws SQLException, ClassNotFoundException {
        return stockService.addStock(stockCreation);
    }
}
