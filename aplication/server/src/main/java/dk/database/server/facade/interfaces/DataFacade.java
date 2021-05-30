package dk.database.server.facade.interfaces;

import dk.database.server.domain.KeywordCreation;
import dk.database.server.domain.StockCreation;
import dk.database.server.domain.UserCreation;
import dk.database.server.entities.Keyword;
import dk.database.server.entities.Stock;
import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;

import java.sql.SQLException;
import java.util.Map;

public interface DataFacade {

    // User
    Map<Integer,User> getAllUsers() throws SQLException, ClassNotFoundException;
    User getUserById(int userId) throws SQLException, ClassNotFoundException;
    UserKeyword getUserKeyword(int userId) throws SQLException, ClassNotFoundException;
    User addUser(UserCreation userCreation) throws SQLException, ClassNotFoundException;

    //Keywords
    Map<Integer, Keyword> getAllKeywords() throws SQLException, ClassNotFoundException ;
    Keyword getKeywordById(int keywordId) throws SQLException, ClassNotFoundException ;
    Keyword addKeyword(KeywordCreation keywordCreation) throws SQLException, ClassNotFoundException ;


    //Stock
    Map<Integer, Stock> getAllStocks() throws SQLException, ClassNotFoundException ;
    Stock getStockById(int stockId) throws SQLException, ClassNotFoundException ;
    Stock addStock(StockCreation stockCreation) throws SQLException, ClassNotFoundException ;
}
