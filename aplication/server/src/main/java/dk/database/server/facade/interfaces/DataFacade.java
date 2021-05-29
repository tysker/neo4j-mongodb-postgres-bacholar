package dk.database.server.facade.interfaces;

import dk.database.server.entities.Keyword;
import dk.database.server.entities.Stock;
import dk.database.server.entities.User;

import java.sql.SQLException;
import java.util.Map;

public interface DataFacade {

    Map<Integer,User> getAllUsers() throws SQLException, ClassNotFoundException;
    User getUserById(int userId) throws SQLException, ClassNotFoundException;

    Map<Integer, Keyword> getAllKeywords() throws SQLException, ClassNotFoundException ;
    Keyword getKeywordById(int keywordId) throws SQLException, ClassNotFoundException ;

    Map<Integer, Stock> getAllStocks() throws SQLException, ClassNotFoundException ;
    Stock getStockById(int stockId) throws SQLException, ClassNotFoundException ;
}
