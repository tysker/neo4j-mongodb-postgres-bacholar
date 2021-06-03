package dk.database.server.facade.interfaces;

import dk.ckmwn.dto.Article;
import dk.database.server.domain.*;
import dk.database.server.entities.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DataFacade {

    // User
    Map<Integer,User> getAllUsers() throws SQLException, ClassNotFoundException;
    User getUserById(int userId) throws SQLException, ClassNotFoundException;
    UserKeyword getUserKeyword(int userId) throws SQLException, ClassNotFoundException;
    User addUser(UserCreation userCreation) throws SQLException, ClassNotFoundException;
    boolean applyStock(UserStockCreation userStockCreation) throws SQLException, ClassNotFoundException;
    boolean applyKeyword(UserKeywordCreation userKeywordCreation) throws SQLException, ClassNotFoundException;
    boolean applyUserKeywordsStock(int userId, String keywordId, String stockId) throws SQLException, ClassNotFoundException;

    //Keywords
    Map<Integer, Keyword> getAllKeywords() throws SQLException, ClassNotFoundException ;
    Keyword getKeywordById(int keywordId) throws SQLException, ClassNotFoundException ;
    Keyword addKeyword(KeywordCreation keywordCreation) throws SQLException, ClassNotFoundException ;
    UserStockKeyword getKeywordByUserIdAndStockName(int userId, String stockName) throws SQLException, ClassNotFoundException;


    //Stock
    Map<Integer, Stock> getAllStocks() throws SQLException, ClassNotFoundException ;
    Stock getStockById(int stockId) throws SQLException, ClassNotFoundException ;
    Stock addStock(StockCreation stockCreation) throws SQLException, ClassNotFoundException ;
    List<dk.ckmwn.dto.Keyword> suggestKeywordsForStock(dk.ckmwn.dto.Stock stock, int width);

    //Article
    boolean createArticle(Article article);
    Article getArticle(String id);
}
