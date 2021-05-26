package dk.database.server.service.interfaces;

import dk.database.server.entities.Stock;

import java.util.Map;

public interface StockService {

    Map<Integer,Stock> getAllStocks();
    Stock getStockById(int stockId);
}
