package dk.database.server.facade;

import dk.ckmwn.contract.StorageManagement;
import dk.ckmwn.dto.Article;
import dk.ckmwn.impl.StorageManagementImpl;
import dk.database.server.domain.*;
import dk.database.server.entities.*;
import dk.database.server.facade.interfaces.DataFacade;
import dk.database.server.service.KeywordServiceImpl;
import dk.database.server.service.StockServiceImpl;
import dk.database.server.service.UserServiceImpl;
import dk.database.server.service.interfaces.KeywordService;
import dk.database.server.service.interfaces.StockService;
import dk.database.server.service.interfaces.UserService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataFacadeImpl implements DataFacade {

    private final UserService userService = new UserServiceImpl();
    private final KeywordService keywordService = new KeywordServiceImpl();
    private final StockService stockService = new StockServiceImpl();
    private final StorageManagement storageManagement = new StorageManagementImpl();

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
    public boolean applyUserKeywordsStock(int userId, String keywordId, String stockId) throws SQLException, ClassNotFoundException {
        boolean created = storageManagement.addKeywordToStock(new dk.ckmwn.dto.Stock(stockId), new dk.ckmwn.dto.Keyword(keywordId));
        if(created) {
            return userService.applyUserKeywordsStock(userId, keywordId, stockId);
        }
        return false;
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
    public UserStockKeyword getKeywordByUserIdAndStockName(int userId, String stockName) throws SQLException, ClassNotFoundException {
        return keywordService.getKeywordByUserIdAndStockName(userId, stockName);
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
        boolean created = storageManagement.createStock(new dk.ckmwn.dto.Stock(stockCreation.getStockName()));
        if(created) {
            return stockService.addStock(stockCreation);
        }
        return null;
    }

    @Override
    public List<dk.ckmwn.dto.Keyword> suggestKeywordsForStock(dk.ckmwn.dto.Stock stock, int width) {
        return storageManagement.suggestKeywordsForStock(stock, width);
    }

    @Override
    public boolean createArticle(Article article) {
        return storageManagement.createArticle(article);
    }

    @Override
    public Article getArticle(String id) {
        return storageManagement.getArticle(id);
    }
}
