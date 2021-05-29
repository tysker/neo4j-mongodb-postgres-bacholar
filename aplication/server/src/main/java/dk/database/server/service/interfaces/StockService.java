package dk.database.server.service.interfaces;

import dk.database.server.entities.Stock;

import java.sql.SQLException;
import java.util.Map;

public interface StockService {

    Map<Integer,Stock> getAllStocks() throws SQLException, ClassNotFoundException;
    Stock getStockById(int stockId) throws SQLException, ClassNotFoundException;
    Stock addStock(Stock stock) throws SQLException, ClassNotFoundException;
}
