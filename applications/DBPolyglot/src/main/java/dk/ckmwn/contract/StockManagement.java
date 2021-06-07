package dk.ckmwn.contract;

import dk.ckmwn.dto.Stock;

public interface StockManagement {

    boolean createStock(Stock stock);
    boolean deleteStock(Stock stock);
    Stock getStock(String symbol);
    boolean updateStock(String fromSymbol, String toSymbol);


}
